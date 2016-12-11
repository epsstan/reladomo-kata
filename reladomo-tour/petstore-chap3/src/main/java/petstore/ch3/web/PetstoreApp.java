package petstore.ch3.web;

import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by stanle on 11/29/16.
 */
public class PetstoreApp {

    private ResourceConfig config;

    public static void main(String[] args) throws Exception {
        new PetstoreApp().start();
    }

    public PetstoreApp() throws Exception {
        this.initReladomo();
    }

    protected void initReladomo() throws Exception {
        MithraManager mithraManager = MithraManagerProvider.getMithraManager();
        mithraManager.setTransactionTimeout(60 * 1000);
        loadReladomoXML("reladomoxml/PetstoreRuntimeConfiguration.xml");
    }

    protected void loadReladomoXML(String fileName) throws Exception {
        InputStream stream = PetstoreApp.class.getClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            throw new Exception("Failed to locate " + fileName + " in classpath");
        }
        MithraManagerProvider.getMithraManager().readConfiguration(stream);
        stream.close();
    }

    protected void initResources() {
        this.config = new ResourceConfig(PersonResource.class);
        config.register(JacksonFeature.class);
    }

    protected void start() throws IOException {
        initResources();
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        server.start();
    }
}
