package com.btanabe.adaptivewebscraper.test.factories;

import com.btanabe.adaptivewebscraper.processors.HtmlTidier;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * Created by Brian on 4/15/16.
 */
public class FileTidiedDocumentFactory implements FactoryBean<Document> {

    @Setter(onMethod = @__({@Autowired}))
    private File inputFile;

    @Override
    public Document getObject() throws Exception {
        return Jsoup.parse(HtmlTidier.tidyHtmlAndConvertToXhtml(FileUtils.readFileToString(inputFile)), "UTF-8");
    }

    @Override
    public Class<?> getObjectType() {
        return Document.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
