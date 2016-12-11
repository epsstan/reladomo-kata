package petstore.ch2.app;

import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;

import java.io.InputStream;

/**
 * Created by stanle on 11/23/16.
 */
public class PetstoreApp {
    public static void main(String[] args) throws Exception {
        new PetstoreApp().start();
    }

    public PetstoreApp() throws Exception {
        this.initReladomo();
    }

    private void initReladomo() throws Exception {
        MithraManager mithraManager = MithraManagerProvider.getMithraManager();
        mithraManager.setTransactionTimeout(60 * 1000);
        loadReladomoXML("PetstoreRuntimeConfigutation.xml");
    }

    private void loadReladomoXML(String fileName) throws Exception
    {
        InputStream stream = PetstoreApp.class.getClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            throw new Exception("Failed to locate " + fileName + " in c;asspath");
        }
        MithraManagerProvider.getMithraManager().readConfiguration(stream);
        stream.close();
    }

    private void start() {
        //implement app logic
    }
}
