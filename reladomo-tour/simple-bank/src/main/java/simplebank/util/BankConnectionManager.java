package simplebank.util;

import com.gs.fw.common.mithra.bulkloader.BulkLoader;
import com.gs.fw.common.mithra.bulkloader.BulkLoaderException;
import com.gs.fw.common.mithra.connectionmanager.SourcelessConnectionManager;
import com.gs.fw.common.mithra.connectionmanager.XAConnectionManager;
import com.gs.fw.common.mithra.databasetype.DatabaseType;
import com.gs.fw.common.mithra.databasetype.Udb82DatabaseType;

import java.sql.Connection;
import java.util.TimeZone;

/**
 * Created by stanle on 11/23/16.
 */

public class BankConnectionManager implements SourcelessConnectionManager
{
    protected static BankConnectionManager instance;
    private XAConnectionManager xaConnectionManager;

    public static synchronized BankConnectionManager getInstance() {
        if (instance == null)
        {
            instance = new BankConnectionManager();
        }
        return instance;
    }

    protected BankConnectionManager() {
        this.createConnectionManager();
    }

    private void createConnectionManager() {
        this.xaConnectionManager = new XAConnectionManager();
        xaConnectionManager.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        xaConnectionManager.setHostName("my.db.host");
        xaConnectionManager.setPort(12345);
        xaConnectionManager.setJdbcUser("user1");
        xaConnectionManager.setJdbcPassword("password1");
        xaConnectionManager.setMaxWait(500);
        xaConnectionManager.setPoolName("pet store connection pool");
        xaConnectionManager.setPoolSize(10);
        xaConnectionManager.setInitialSize(1);
        xaConnectionManager.initialisePool();
    }

    @Override
    public Connection getConnection() {
        return xaConnectionManager.getConnection();
    }

    @Override
    public DatabaseType getDatabaseType() {
        return Udb82DatabaseType.getInstance();
    }

    @Override
    public TimeZone getDatabaseTimeZone() {
        return TimeZone.getTimeZone("America/New York");
    }

    @Override
    public String getDatabaseIdentifier() {
        return xaConnectionManager.getHostName() + ":" + xaConnectionManager.getPort();
    }

    @Override
    public BulkLoader createBulkLoader() throws BulkLoaderException {
        return null;
    }

}
