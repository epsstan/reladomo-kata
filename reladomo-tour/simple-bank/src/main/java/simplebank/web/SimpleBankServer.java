package simplebank.web;

import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import simplebank.serialization.JacksonObjectMapperProvider;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by stanle on 11/29/16.
 */
public class SimpleBankServer {

    private ResourceConfig config;

    public static void main(String[] args) throws Exception {
        String runtimeConfigXML = "reladomoxml/SimpleBankRuntimeConfiguration.xml";
        new SimpleBankServer(runtimeConfigXML).start();
    }

    public SimpleBankServer(String runtimeConfigXML) throws Exception {
        this.initReladomo(runtimeConfigXML);
    }

    protected void initReladomo(String runtimeConfigXML) throws Exception {
        MithraManager mithraManager = MithraManagerProvider.getMithraManager();
        mithraManager.setTransactionTimeout(60 * 1000);
        loadReladomoXML(runtimeConfigXML);
    }

    protected void loadReladomoXML(String fileName) throws Exception {
        InputStream stream = SimpleBankServer.class.getClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            throw new Exception("Failed to locate " + fileName + " in classpath");
        }
        MithraManagerProvider.getMithraManager().readConfiguration(stream);
        stream.close();
    }

    protected void initResources() {
        this.config = new ResourceConfig(CustomerResource.class);
        config.register(JacksonFeature.class);
        config.register(JacksonObjectMapperProvider.class);
    }

    public void start() throws IOException {
        initResources();
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        server.start();
    }
}
