package petstore.ch3.web;

import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;

/**
 * Created by stanle on 11/29/16.
 */

public class TestPetstoreApp extends PetstoreApp {

    public TestPetstoreApp() throws Exception
    {
        super();
    }

    public static void main(String[] args) throws Exception {
        new TestPetstoreApp().start();
    }

    @Override
    protected void initReladomo() throws Exception {
        MithraManager mithraManager = MithraManagerProvider.getMithraManager();
        mithraManager.setTransactionTimeout(60 * 1000);
        loadReladomoXML("reladomoxml/PetstoreTestRuntimeConfiguration.xml");
    }
}
