package petstore.ch3.web;

import com.gs.fw.common.mithra.test.ConnectionManagerForTests;
import com.gs.fw.common.mithra.test.MithraTestResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import petstore.ch3.domain.Person;
import petstore.ch3.domain.PersonFinder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by stanle on 11/29/16.
 */
public class PersonAPITest {

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

    @Test
    public void testCreate()
    {
        WebTarget target = getWebTarget("/api/person");
        Form form = new Form();
        form.param("personId", "100");
        form.param("firstName", "mickey");
        form.param("lastName", "mouse");

        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.form(form));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        Person person = PersonFinder.findOne(PersonFinder.personId().eq(100));
        assertEquals("mickey", person.getFirstName());
        assertEquals("mouse", person.getLastName());
    }

    @Test
    public void testGet()
    {
        WebTarget target = getWebTarget("/api/person/1");
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Person person = response.readEntity(Person.class);
        assertEquals(1, person.getPersonId());
        assertEquals("joe", person.getFirstName());
        assertEquals("smith", person.getLastName());
    }

    @Test
    public void testUpdate()
    {
        Person person = PersonFinder.findOne(PersonFinder.personId().eq(2));
        assertEquals(2, person.getPersonId());
        assertEquals("minnie", person.getFirstName());
        assertEquals("mouse", person.getLastName());

        WebTarget target = getWebTarget("/api/person/2");
        Form form = new Form();
        form.param("firstName", "daisy");
        form.param("lastName", "duck");

        Response response = target.request().put(Entity.form(form));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        person = PersonFinder.findOne(PersonFinder.personId().eq(2));
        assertEquals("daisy", person.getFirstName());
        assertEquals("duck", person.getLastName());
    }

    @Test
    public void testDelete()
    {
        Person person = PersonFinder.findOne(PersonFinder.personId().eq(1));
        assertNotNull(person);

        WebTarget target = getWebTarget("/api/person/1");
        Response response = target.request().delete();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        person = PersonFinder.findOne(PersonFinder.personId().eq(1));
        assertNull(person);
    }

    private WebTarget getWebTarget(String path) {
        Client client = ClientBuilder.newClient();
        client.register(JacksonFeature.class);
        return client.target("http://localhost:9998").path(path);
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
