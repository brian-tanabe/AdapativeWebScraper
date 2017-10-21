package com.btanabe.adaptivewebscraper.test.integration.store;

import com.btanabe.adaptivewebscraper.models.EspnNflProjectionModel;
import com.btanabe.adaptivewebscraper.store.DatabaseInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;


@ContextConfiguration("classpath:spring-configuration/unit-testing-configuration.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseInterfaceTests {

    // TODO FIGURE OUT WHY THE TESTCONTEXTLISTENER CLASSES CAN'T HANDLE AUTOWIREING THIS BEAN
    //    @Autowired
    //    @Qualifier("testDatabaseConfiguration")
    private DatabaseInterface databaseInterface;

    @Autowired
    @Qualifier("espnPlayerProjectionsPageEddieLacy")
    private EspnNflProjectionModel eddieLacyProjectionModel;

    @Before
    public void clearDatabase() throws Exception {
        databaseInterface = new DatabaseInterface("./src/test/resources/hibernate-configuration/hibernate-testing.cfg.xml");
        databaseInterface.deleteAllObjectsOfType(EspnNflProjectionModel.class);
    }

    @Test
    public void shouldBeAbleToInsertEspnNbaProjectionModelsIntoTheProperTable() throws Exception {
        databaseInterface.saveOrUpdate(eddieLacyProjectionModel);
    }

    @Test
    public void shouldBeAbleToReadFromEspnNbaProjectionsDatabase() throws Exception {
        databaseInterface.saveOrUpdate(eddieLacyProjectionModel);
        assertThat(databaseInterface.getAllObjectsOfType(EspnNflProjectionModel.class), hasItem(eddieLacyProjectionModel));
    }
}
