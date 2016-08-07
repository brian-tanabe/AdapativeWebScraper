package com.btanabe.adaptivewebscraper.tasks;

import com.btanabe.adaptivewebscraper.factories.ValueExtractorFactory;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.nodes.Document;
import org.springframework.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

/**
 * Created by Brian on 5/7/16.
 */
@Slf4j
@AllArgsConstructor
public class DocumentParserTask<OutputClazz> implements Callable<OutputClazz> {
    private ListeningExecutorService executorService;
    private Document document;
    private Map<ValueExtractorFactory, String> outputObjectSetterMethodNameMappedToItsValueExtractorFactory;
    private Class<OutputClazz> outputClazzPath;

    @Override
    public OutputClazz call() throws Exception {
        Map<ListenableFuture, String> futures = Maps.newHashMapWithExpectedSize(outputObjectSetterMethodNameMappedToItsValueExtractorFactory.size());
        outputObjectSetterMethodNameMappedToItsValueExtractorFactory.keySet().forEach(valueExtractorFactory -> {
            futures.put(executorService.submit(valueExtractorFactory.createValueExtractor(document)), outputObjectSetterMethodNameMappedToItsValueExtractorFactory.get(valueExtractorFactory));
        });

        OutputClazz outputObject = ClassUtils.getConstructorIfAvailable(outputClazzPath).newInstance();
        futures.forEach((future, setterMethodName) -> {
            try {
                final Object value = ((Stream) future.get()).findFirst().get();
                ClassUtils.getMethod(outputClazzPath, setterMethodName, value.getClass()).invoke(outputObject, value);
            } catch (Exception error) {
                log.error(String.format("OutputObject=[%s] threw an exception when trying to call method=[%s]", outputObject, setterMethodName));
                log.error(ExceptionUtils.getStackTrace(error));
                error.printStackTrace();
            }
        });

        return outputObject;
    }
}