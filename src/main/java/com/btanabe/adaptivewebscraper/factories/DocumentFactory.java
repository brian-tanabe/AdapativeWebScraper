package com.btanabe.adaptivewebscraper.factories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Brian on 5/4/16.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class DocumentFactory implements FactoryBean<Document> {

    @Setter(onMethod = @__({@Autowired}))
    @NonNull
    private String documentText;

    @Override
    public Document getObject() throws Exception {
        return Jsoup.parse(documentText);
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
