package com.btanabe.adaptivewebscraper.store;

import com.btanabe.adaptivewebscraper.models.Model;
import com.google.common.eventbus.Subscribe;
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
    private final Session session;
    private SessionFactory factory;

    public DatabaseInterface(final String hibernateConfigurationFilePath) throws MalformedURLException {
        factory = new Configuration().configure(new File(hibernateConfigurationFilePath).toURI().toURL()).buildSessionFactory();
        session = factory.openSession();
    }

    public void closeConnection() {
        session.flush();
        session.close();
    }

    @Subscribe
    public void saveOrUpdate(List<Model> listOfObjectsToStore) {
        listOfObjectsToStore.forEach(this::saveOrUpdate);
    }

    @Subscribe
    public void saveOrUpdate(final Model objectToStore) {
        session.saveOrUpdate(objectToStore);
    }

    public <T extends Model> List<T> getAllObjectsOfType(Class<T> clazz) {
        session.flush();
        return session.createCriteria(clazz).list();
    }

    public void deleteObject(Model objectToDelete) {
        session.delete(objectToDelete);
    }

    public <T extends Model> void deleteAllObjectsOfType(Class<T> clazz) {
        getAllObjectsOfType(clazz).forEach(session::delete);
    }
}
