package simplebank.scratch;

import com.gs.fw.common.mithra.test.ConnectionManagerForTests;
import com.gs.fw.common.mithra.test.MithraTestResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Before;
import org.junit.Test;
import simplebank.domain.Customer;
import simplebank.domain.CustomerAccount;
import simplebank.domain.CustomerAccountList;
import simplebank.serialization.JacksonObjectMapperProvider;
import simplebank.web.SimpleBankServer;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by stanle on 2/18/17.
 */
public class SimpleBankAPITest
{
    private String testRuntimeConfigXML = "testconfig/SimpleBankTestRuntimeConfiguration.xml";

    @Before
    public void setup() throws Exception
    {
        intializeReladomoForTest();
        initializeApp();
    }

    private void intializeReladomoForTest()
    {
        MithraTestResource testResource = new MithraTestResource(testRuntimeConfigXML);
        ConnectionManagerForTests connectionManager = ConnectionManagerForTests.getInstance("test_db");
        testResource.createSingleDatabase(connectionManager, "testconfig/SimpleBankTestData.txt");
        testResource.setUp();
    }

    private void initializeApp() throws Exception
    {
        new SimpleBankServer(testRuntimeConfigXML).start();
    }

    @Test
    public void testGetCustomer()
    {
        WebTarget target = webTarget("/api/customer/1");
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Customer mickey = response.readEntity(Customer.class);
        assertEquals(1, mickey.getCustomerId());
        assertEquals("mickey", mickey.getFirstName());
        assertEquals("mouse", mickey.getLastName());

        CustomerAccountList mickeysAccounts = mickey.getAccounts();
        assertEquals(1, mickeysAccounts.size());
        CustomerAccount clubhouseAccount = mickeysAccounts.get(0);
        assertEquals(100, clubhouseAccount.getAccountId());
        assertEquals("mickey mouse club", clubhouseAccount.getAccountName());
        assertEquals("savings", clubhouseAccount.getAccountType());
        assertEquals(5000, clubhouseAccount.getBalance(), 0);
    }

    private WebTarget webTarget(String path) {
        Client client = ClientBuilder.newClient();
        client.register(JacksonFeature.class);
        client.register(JacksonObjectMapperProvider.class);
        return client.target("http://localhost:9998").path(path);
    }
}
