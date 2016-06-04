package com.btanabe.adaptivewebscraper.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Brian on 6/4/16.
 */
public class DatabaseInterface {
    private SessionFactory factory;
    private final Session session;

    public DatabaseInterface(final String hibernateConfigurationFilePath) throws MalformedURLException {
        factory = new Configuration().configure(new File(hibernateConfigurationFilePath).toURI().toURL()).buildSessionFactory();
        session = factory.openSession();
    }

    public void closeConnection() {
        session.flush();
        session.close();
    }

    public <T> void saveOrUpdate(List<T> listOfObjectsToStore) {
        listOfObjectsToStore.forEach(this::saveOrUpdate);
    }

    public <T> void saveOrUpdate(T objectToStore) {
        session.saveOrUpdate(objectToStore);
    }

    public <T> List<T> getAllObjectsOfType(Class<T> clazz) {
        session.flush();
        return session.createCriteria(clazz).list();
    }

    public <T> void deleteObject(T objectToDelete) {
        session.delete(objectToDelete);
    }

    public <T> void deleteAllObjectsOfType(Class<T> clazz) {
        getAllObjectsOfType(clazz).forEach(session::delete);
    }
}
