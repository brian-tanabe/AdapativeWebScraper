package com.btanabe.adaptivewebscraper.factories;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.Setter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Brian on 8/26/16.
 */
@Setter
public class ThreadPoolFactory {
    private Integer queueCapacity = 1000;
    private Integer corePoolSize = 100;
    private Integer maximumPoolSize = 1000;
    private Integer keepAliveTime = 60;
    private ListeningExecutorService executorService;

    private static ThreadPoolFactory instance;

    private ThreadPoolFactory() {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(queueCapacity, true);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
        executor.prestartAllCoreThreads();

        executorService = MoreExecutors.listeningDecorator(executor);
    }

    public static ListeningExecutorService getInstance() throws Exception {
        if (instance == null) {
            instance = new ThreadPoolFactory();
        }

        return instance.getObject();
    }

    public ListeningExecutorService getObject() throws Exception {
        return executorService;
    }
}
