package petstore.ch3.web;

import com.gs.fw.common.mithra.test.ConnectionManagerForTests;
import com.gs.fw.common.mithra.test.MithraTestResource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import petstore.ch3.domain.Person;
import petstore.ch3.domain.PersonFinder;
import petstore.ch3.domain.PetType;

import static org.junit.Assert.assertEquals;

/**
 * Created by stanle on 11/29/16.
 */
public class PersonAPITest2 {

    private static final Logger logger = LoggerFactory.getLogger(PersonAPITest2.class);
    private static TestPetstoreApp app;
    private static MithraTestResource mithraTestResource;

    @BeforeClass
    public static void setup() throws Exception
    {
        initReladomoTestResource();
        startApp();
    }

    private static void startApp() throws Exception
    {
        app = new TestPetstoreApp();
        app.start();
    }

    //@Test
    public void testDetached()
    {
        Person p = PersonFinder.findByPrimaryKey(1);
        assertEquals("joe", p.getFirstName());

        p.setFirstName("joey");
        assertEquals("joey", PersonFinder.findByPrimaryKey(1).getFirstName());

        Person detached = p.getDetachedCopy();
        detached.setFirstName("joesph");

        assertEquals("joey", PersonFinder.findByPrimaryKey(1).getFirstName());
    }

    @Test
    public void testDetachedList()
    {
        Person p = PersonFinder.findByPrimaryKey(1);
        assertEquals("joe", p.getFirstName());

        p.setFirstName("joey");
        p.getPets().get(0).setPetType(new PetType(3, "bird"));
        assertEquals("joey", PersonFinder.findByPrimaryKey(1).getFirstName());
        assertEquals("bird", PersonFinder.findByPrimaryKey(1).getPets().get(0).getPetType().getPetType());

        Person detached = p.getDetachedCopy();
        detached.setFirstName("joesph");
        detached.getPets().get(0).setPetType(new PetType(1, "dog"));

        logger.info("-------------");
        assertEquals("joey", PersonFinder.findByPrimaryKey(1).getFirstName());
        assertEquals("bird", PersonFinder.findByPrimaryKey(1).getPets().get(0).getPetType().getPetType());
    }

    private static void initReladomoTestResource()
    {
        String xmlFile = "reladomoxml/PetstoreTestRuntimeConfiguration.xml";
        mithraTestResource = new MithraTestResource(xmlFile);
        ConnectionManagerForTests connMgrForTestDb = ConnectionManagerForTests.getInstance("test_db");
        mithraTestResource.createSingleDatabase(connMgrForTestDb, "testdata/test.txt");
        mithraTestResource.setUp();
    }

    @AfterClass
    public static void shutdown()
    {
    }

}
