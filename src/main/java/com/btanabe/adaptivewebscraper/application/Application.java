package com.btanabe.adaptivewebscraper.application;

import com.btanabe.adaptivewebscraper.collectors.RecordCollector;
import com.btanabe.adaptivewebscraper.store.DatabaseInterface;
import com.btanabe.adaptivewebscraper.utilities.ClasspathToFileSystemResource;
import com.google.common.collect.Lists;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by Brian on 6/5/16.
 */
public class Application {
    private static final Logger log = LogManager.getLogger(Application.class);

    private List<RecordCollector> recordCollectorList;
    private ListeningExecutorService executorService;
    private AsyncEventBus eventBus;
    private DatabaseInterface databaseInterface;

    public static void main(String[] args) throws Exception {
        Application application = new Application();
        application.updateDatabase();
    }

    public void updateDatabase() throws Exception {
        loadDependencies();
        validateDependencies();
        configureEventBus();
        waitForAllCollectorsToFinish(runAllRecordCollectors());
        shutdownThreadPool();
        closeDatabaseInterface();
        logResults();
    }

    private void loadDependencies() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("./spring-configuration/application-configuration.xml");
        recordCollectorList = (List<RecordCollector>) context.getBean("recordCollectorList");
        executorService = (ListeningExecutorService) context.getBean("listeningExecutorService");
        eventBus = (AsyncEventBus) context.getBean("collectedRecordsEventBus");
        File tempHibernateConfigurationFile = ClasspathToFileSystemResource.classpathToTempFileSystemFile(new ClassPathResource("/hibernate-configuration/hibernate-production-nfl.cfg.xml"));

        databaseInterface = new DatabaseInterface(tempHibernateConfigurationFile);
    }

    private void validateDependencies() {
        Objects.requireNonNull(recordCollectorList);
        Objects.requireNonNull(executorService);
        Objects.requireNonNull(eventBus);
        Objects.requireNonNull(databaseInterface);
    }

    private void configureEventBus() {
        eventBus.register(databaseInterface);
    }

    private List<ListenableFuture<Void>> runAllRecordCollectors() {

        List<ListenableFuture<Void>> futureList = Lists.newArrayList();
        for (RecordCollector collector : recordCollectorList) {
            futureList.add(executorService.submit(collector));
        }

        return futureList;
    }

    private void waitForAllCollectorsToFinish(List<ListenableFuture<Void>> futureList) {
        for (ListenableFuture<Void> future : futureList) {
            try {
                future.get();
            } catch (Exception exception) {
                log.error(ExceptionUtils.getMessage(exception));
                log.error(ExceptionUtils.getStackTrace(exception));
                System.err.println(ExceptionUtils.getStackTrace(exception));
            }
        }
    }

    private void shutdownThreadPool() throws InterruptedException {
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    private void closeDatabaseInterface() {
        databaseInterface.closeConnection();
    }

    private void logResults() {
        log.info("DONE UPDATING DATABASE");
    }
}
