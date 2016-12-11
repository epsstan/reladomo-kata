package com.petstore.domain;
import java.math.BigDecimal;
import java.util.*;
import java.sql.Timestamp;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.attribute.*;
import com.gs.fw.common.mithra.util.*;
import com.gs.fw.common.mithra.notification.*;
import com.gs.fw.common.mithra.notification.listener.*;
import com.gs.fw.common.mithra.list.cursor.Cursor;
import com.gs.fw.common.mithra.bulkloader.*;
import java.util.*;
import java.sql.*;
import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.attribute.update.AttributeUpdateWrapper;
import com.gs.fw.common.mithra.behavior.*;
import com.gs.fw.common.mithra.behavior.state.DatedPersistenceState;
import com.gs.fw.common.mithra.bulkloader.BulkLoader;
import com.gs.fw.common.mithra.bulkloader.BulkLoaderException;
import com.gs.fw.common.mithra.cache.*;
import com.gs.fw.common.mithra.cache.offheap.*;
import com.gs.fw.common.mithra.connectionmanager.*;
import com.gs.fw.common.mithra.database.*;
import com.gs.fw.common.mithra.databasetype.*;
import com.gs.fw.common.mithra.finder.*;
import com.gs.fw.common.mithra.finder.asofop.AsOfOperation;
import com.gs.fw.common.mithra.finder.orderby.OrderBy;
import com.gs.fw.common.mithra.finder.integer.IntegerResultSetParser;
import com.gs.fw.common.mithra.querycache.CachedQuery;
import com.gs.fw.common.mithra.remote.RemoteMithraService;
import com.gs.fw.common.mithra.transaction.BatchUpdateOperation;
import com.gs.fw.common.mithra.transaction.UpdateOperation;
/**
* This file was automatically generated using Mithra 16.1.0. Please do not modify it.
* Add custom logic to its subclass instead.
*/
public abstract class PetDatabaseObjectAbstract extends MithraAbstractDatedTransactionalDatabaseObject
implements MithraDatedTransactionalDatabaseObject, MithraDatedTransactionalObjectFactory
{
	private SourcelessConnectionManager connectionManager;
	private SchemaManager schemaManager;
	private TablePartitionManager tablePartitionManager;
	private static final String COL_LIST_WITHOUT_PK = "PERSON_ID,PET_AGE,PET_TYPE_ID,IN_Z,OUT_Z";
	private static final String COL_LIST_WITHOUT_PK_WITH_ALIAS = "t0.PERSON_ID,t0.PET_AGE,t0.PET_TYPE_ID,t0.IN_Z,t0.OUT_Z";
	private static final String PK_WITH_ALIAS = "t0.PET_NAME = ?";
	private static final String PK_INDEX_COLS = "PET_NAME";
	protected PetDatabaseObjectAbstract()
	{
		super("Pet", "com.petstore.domain.PetFinder",
			6, 6,
			COL_LIST_WITHOUT_PK, COL_LIST_WITHOUT_PK_WITH_ALIAS,
			true, false, false,
			PK_WITH_ALIAS,
			PK_INDEX_COLS);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return PetFinder.getMithraObjectPortal();
	}

	public RelatedFinder getFinder()
	{
		return PetFinder.getFinderInstance();
	}

	public static PetData allocateOnHeapData()
	{
		return new PetData();
	}

	public static PetData allocateOffHeapData()
	{
		throw new RuntimeException("no off heap implementation");
	}

	public MithraDataObject deserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		MithraDataObject data = new PetData();
		data.zDeserializeFullData(in);
		return data;
	}

	public void deserializeAsOfAttributes(ObjectInput in, Timestamp[] asof) throws IOException, ClassNotFoundException
	{
		asof[0] = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestampWithInfinity(in, com.petstore.util.TimestampProvider.getInfinityDate()), PetFinder.isFullCache());
	}

	public MithraObject deserializeForRefresh(ObjectInput in) throws IOException, ClassNotFoundException
	{
		PetData data = new PetData();
		data.zDeserializePrimaryKey(in);
		Timestamp[] asof = new Timestamp[1];
		asof[0] = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestampWithInfinity(in, com.petstore.util.TimestampProvider.getInfinityDate()), PetFinder.isFullCache());
		return this.createObject(data, asof);
	}

	public Cache instantiateFullCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new FullDatedTransactionalCache(PetFinder.getPrimaryKeyAttributes(), PetFinder.getAsOfAttributes(), this, PetFinder.getImmutableAttributes());
		}
		else
		{
			result = new FullDatedCache(PetFinder.getPrimaryKeyAttributes(), PetFinder.getAsOfAttributes(), this, PetFinder.getImmutableAttributes());
		}

		initPortal(result, config);
		return result;
	}

	public Cache instantiatePartialCache(MithraConfigurationManager.Config config)
	{
		Cache result;
		if (config.isParticipatingInTx())
		{
			result = new PartialDatedTransactionalCache(PetFinder.getPrimaryKeyAttributes(), PetFinder.getAsOfAttributes(), this, PetFinder.getImmutableAttributes(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}
		else
		{
			result = new PartialDatedCache(PetFinder.getPrimaryKeyAttributes(), PetFinder.getAsOfAttributes(), this, PetFinder.getImmutableAttributes(), config.getCacheTimeToLive(), config.getRelationshipCacheTimeToLive());
		}

		initPortal(result, config);
		return result;
	}

	private void initPortal(Cache cache, MithraConfigurationManager.Config config)
	{
		if (config.isThreeTierClient())
		{
			PetFinder.initializeClientPortal(this, cache, config);
		}
		else
		{
			PetFinder.initializePortal(this, cache, config);
		}

		if (config.isParticipatingInTx())
		{
			Pet.zConfigFullTx();
		}
		else
		{
			Pet.zConfigNonTx();
		}
	}

	public List getSimulatedSequenceInitValues()
	{
		return null;
	}

	public Object getSourceAttributeValueForSelectedObjectGeneric(SqlQuery query, int queryNumber)
	{
		return null;
	}

	public Object getSourceAttributeValueFromObjectGeneric(MithraDataObject object)
	{
		return null;
	}

	public Object getSourceAttributeValueGeneric(SqlQuery query, MapperStackImpl mapperStack, int queryNumber)
	{
		return null;
	}

	public String getDatabaseIdentifierGenericSource (Object source)
	{
		return connectionManager.getDatabaseIdentifier();
	}

	public DatabaseType getDatabaseTypeGenericSource(Object source)
	{
		return connectionManager.getDatabaseType();
	}

	public TimeZone getDatabaseTimeZoneGenericSource(Object source)
	{
		return getDatabaseTimeZone();
	}

	public Connection getConnectionGenericSource(Object source)
	{
		return connectionManagerWrapper.getConnection();
	}

	public BulkLoader createBulkLoaderGenericSource(Object source) throws BulkLoaderException 
	{
		return connectionManager.createBulkLoader();
	}

	public MithraDataObject inflateDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflatePetData(rs, dt);
	}

	public void inflateNonPkDataGenericSource(MithraDataObject data, ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		inflateNonPkPetData(1, (PetData) data, rs, dt);
	}

	public MithraDataObject inflatePkDataGenericSource(ResultSet rs, Object source, DatabaseType dt)
	throws SQLException 
	{
		return inflatePetPkData(rs, dt);
	}

	public String getSchemaGenericSource(Object source)
	{
		if (this.schemaManager != null)
		{
			return this.schemaManager.getSchema(this.getDefaultSchema());
		}

		return this.getDefaultSchema();
	}

	public String getTableNameGenericSource(Object source) throws MithraDatabaseException
	{
		return getPetTableName();
	}

	public String getPetTableName() throws MithraDatabaseException
	{
		if (this.tablePartitionManager != null)
		{
			return this.tablePartitionManager.getTableName(this.getDefaultTableName());
		}

		return this.getDefaultTableName();
	}

	public void setPrimaryKeyAttributes(PreparedStatement stm, int pos, MithraDataObject dataObj,
		TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		PetData data = (PetData)dataObj;
		TimeZone conversionTimeZone = null;
		pos = setPrimaryKeyAttributesWithoutOptimistic(stm,pos,dataObj,databaseTimeZone,dt);
		if (PetFinder.getMithraObjectPortal().getTxParticipationMode().isOptimisticLocking())
		{
			conversionTimeZone = MithraTimestamp.DefaultTimeZone;
			dt.setTimestamp(stm, pos, data.getProcessingDateFrom(), false, conversionTimeZone);
			pos++;
		}
	}

	public int setPrimaryKeyAttributesWithoutOptimistic(PreparedStatement stm, int pos, MithraDataObject dataObj,
		TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		PetData data = (PetData)dataObj;
		TimeZone conversionTimeZone = null;
		stm.setString(pos++, data.getPetName());
		conversionTimeZone = MithraTimestamp.DefaultTimeZone;
		if (data.getProcessingDateTo().getTime() == PetFinder.processingDate().getInfinityDate().getTime())
		{
			conversionTimeZone = MithraTimestamp.DefaultTimeZone;
		}

		dt.setTimestamp(stm, pos, data.getProcessingDateTo(), false, conversionTimeZone);
		pos++;
		return pos;
	}

	public String getPrimaryKeyWhereSql()
	{
		if (PetFinder.getMithraObjectPortal().getTxParticipationMode().isOptimisticLocking())
		{
		}

		return "PET_NAME = ?";
	}

	public String getPrimaryKeyWhereSqlWithNullableAttribute(MithraDataObject dataObj)
	{
		return "";
	}

	public String getPrimaryKeyWhereSqlWithNullableAttributeWithDefaultAlias(MithraDataObject dataObj)
	{
		return "";
	}

	public String getColumnListWithPk(String databaseAlias)
	{
		if (databaseAlias.equals(SqlQuery.DEFAULT_DATABASE_ALIAS))
		{
			return "t0.PET_NAME,t0.PERSON_ID,t0.PET_AGE,t0.PET_TYPE_ID,t0.IN_Z,t0.OUT_Z";
		}

		StringBuffer result = new StringBuffer((databaseAlias.length()+15)*6);
		result.append(databaseAlias).append(".").append("PET_NAME");
		result.append(",").append(databaseAlias).append(".").append("PERSON_ID");
		result.append(",").append(databaseAlias).append(".").append("PET_AGE");
		result.append(",").append(databaseAlias).append(".").append("PET_TYPE_ID");
		result.append(",").append(databaseAlias).append(".").append("IN_Z");
		result.append(",").append(databaseAlias).append(".").append("OUT_Z");
		return result.toString();
	}

	public Object getConnectionManager()
	{
		return connectionManager;
	}

	public void setConnectionManager(Object connectionManager, ConnectionManagerWrapper wrapper)
	{
		this.connectionManager = (SourcelessConnectionManager)connectionManager;
		this.connectionManagerWrapper = wrapper;
	}

	public PetData inflatePetData(ResultSet rs, DatabaseType dt)
	throws SQLException
	{
		PetData data = inflatePetPkData(rs, dt);
		inflateNonPkPetData(2, data, rs, dt);
		return data;
	}

	public PetData inflatePetPkData(ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		PetData _data = new PetData();
		int _pos = 1;
		_data.setPetName(trimString(_rs.getString(_pos++)));
		return _data;
	}

	public void inflateNonPkPetData(int _pos, PetData _datax, ResultSet _rs, DatabaseType _dt)
	throws SQLException
	{
		{
			PetData _data = _datax;
			_data.setPersonId(_rs.getInt(_pos++));
			checkNullPrimitive(_rs, _data, "personId");
			_data.setPetAge(_rs.getInt(_pos++));
			checkNullPrimitive(_rs, _data, "petAge");
			_data.setPetTypeId(_rs.getInt(_pos++));
			checkNullPrimitive(_rs, _data, "petTypeId");
			Timestamp processingDateFromtimestampValue = _dt.getTimestampFromResultSet(_rs, _pos++, MithraTimestamp.DefaultTimeZone);
			_data.setProcessingDateFrom(processingDateFromtimestampValue);
			Timestamp processingDateTotimestampValue = _dt.getTimestampFromResultSet(_rs, _pos++, MithraTimestamp.DefaultTimeZone);
			if (processingDateTotimestampValue == null)
			{
				throw new MithraBusinessException("attribute ' processingDateTo ' is null in database but is not marked as nullable in mithra xml for primary key / "+_data.zGetPrintablePrimaryKey());
			}

			processingDateTotimestampValue = MithraTimestamp.zFixInfinity(processingDateTotimestampValue, MithraTimestamp.DefaultTimeZone,
				PetFinder.processingDate().getInfinityDate());
			_data.setProcessingDateTo(processingDateTotimestampValue);
		}
	}

	public DatabaseType getDatabaseType()
	{
		return connectionManager.getDatabaseType();
	}

	public TimeZone getDatabaseTimeZone()
	{
		return connectionManager.getDatabaseTimeZone();
	}

	protected String getSchema()
	{
		return this.getSchemaGenericSource(null);
	}

	public String getFullyQualifiedTableName()
	{
		String schema = this.getSchemaGenericSource(null);
		String tableName = getPetTableName();
		return this.getDatabaseType().getFullyQualifiedTableName(schema, tableName);
	}

	public void setSchemaManager(Object schemaManager)
	{
		if( schemaManager instanceof SchemaManager )
		{
			this.schemaManager = (SchemaManager) schemaManager;
		}
		else
		{
			throw new IllegalArgumentException( "Schema manager class " + schemaManager.getClass().getName()
			+ " does not implement SchemaManager.class" );
		}
	}

	public void setTablePartitionManager(Object tablePartitionManager)
	{
		if( tablePartitionManager instanceof TablePartitionManager )
		{
			this.tablePartitionManager = (TablePartitionManager) tablePartitionManager;
		}
		else
		{
			throw new IllegalArgumentException( "Table partition manager class " + tablePartitionManager.getClass().getName()
			+ " does not implement TablePartitionManager.class" );
		}
	}

	public String getTableName()
	{
		return this.getDefaultTableName();
	}

	public String getDefaultTableName()
	{
		return "PET";
	}

	public void setInsertAttributes(PreparedStatement stm, MithraDataObject dataObj,
		TimeZone databaseTimeZone, int pos, DatabaseType dt) throws SQLException
	{
		PetData data = (PetData)dataObj;
		TimeZone conversionTimeZone = null;
		if(data.isPetNameNull())
		{
			throwNullAttribute("petName");
		}

		stm.setString(pos++, data.getPetName());
		stm.setInt(pos++, data.getPersonId());
		stm.setInt(pos++, data.getPetAge());
		stm.setInt(pos++, data.getPetTypeId());
		if(data.isProcessingDateFromNull())
		{
			throwNullAttribute("processingDateFrom");
		}

		conversionTimeZone = MithraTimestamp.DefaultTimeZone;
		dt.setTimestamp(stm, pos, data.getProcessingDateFrom(), false, conversionTimeZone);
		pos++;
		if(data.isProcessingDateToNull())
		{
			throwNullAttribute("processingDateTo");
		}

		conversionTimeZone = MithraTimestamp.DefaultTimeZone;
		if (data.getProcessingDateTo().getTime() == PetFinder.processingDate().getInfinityDate().getTime())
		{
			conversionTimeZone = MithraTimestamp.DefaultTimeZone;
		}

		dt.setTimestamp(stm, pos, data.getProcessingDateTo(), false, conversionTimeZone);
		pos++;
	}

	public String getInsertFields()
	{
		return "PET_NAME,PERSON_ID,PET_AGE,PET_TYPE_ID,IN_Z,OUT_Z";
	}

	public String getInsertQuestionMarks()
	{
		return "?,?,?,?,?,?";
	}

	public String getOptimisticLockingWhereSql()
	{
		return "AND IN_Z = ?";
	}

	public Timestamp[] getAsOfDates() 
	{
		return new Timestamp[1];
	}

	public MithraDatedObject createObject(MithraDataObject data, Timestamp[] asOfDates)
	{
		Pet newObject = new Pet(asOfDates[0]
		);
		newObject.zSetFromPetData((PetData) data);
		return newObject;
	}

	public TemporalContainer createContainer(MithraTransaction tx)
	{
		return new AuditOnlyTransactionalDataContainer(tx.getPerPortalTemporalContainer(PetFinder.getMithraObjectPortal(),
			PetFinder.processingDate())
		);
	}

	public String getAsOfAttributeWhereSql(MithraDataObject data) 
	{
		String result = "";
		result += " AND ";
		result +="OUT_Z = ?";
		return result;
	}

	public int setPrimaryKeyAttributesWithoutDates(PreparedStatement stm, int pos, MithraDataObject dataObject, TimeZone databaseTimeZone, DatabaseType dt) throws SQLException
	{
		PetData data = (PetData)dataObject;
		TimeZone conversionTimeZone = null;
		stm.setString(pos++, data.getPetName());
		return pos;
	}

	public List getForDateRange(MithraDataObject obj, Timestamp start, Timestamp end) throws MithraDatabaseException
	{
		throw new RuntimeException("not implemented");
	}
}
