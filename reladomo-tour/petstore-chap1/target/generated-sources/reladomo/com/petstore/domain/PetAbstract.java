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
import com.gs.fw.common.mithra.MithraObject;
import com.gs.fw.common.mithra.MithraDataObject;
import com.gs.fw.common.mithra.cache.Cache;
import com.gs.fw.common.mithra.extractor.*;
import com.gs.fw.common.mithra.finder.*;
import com.gs.fw.common.mithra.list.*;
import com.gs.fw.common.mithra.transaction.MithraObjectPersister;
import java.util.Arrays;
import java.util.HashSet;
import com.gs.fw.common.mithra.behavior.*;
import com.gs.fw.common.mithra.behavior.txparticipation.MithraOptimisticLockException;
import com.gs.fw.common.mithra.behavior.state.DatedPersistenceState;
import com.gs.fw.common.mithra.behavior.state.DatedPersistedState;
import com.gs.fw.common.mithra.attribute.update.*;
import com.gs.fw.common.mithra.util.StatisticCounter;
/**
* This file was automatically generated using Mithra 16.1.0. Please do not modify it.
* Add custom logic to its subclass instead.
*/
// Generated from templates/datedtransactional/Abstract.jsp
public abstract class PetAbstract extends com.gs.fw.common.mithra.superclassimpl.MithraDatedTransactionalObjectImpl implements Serializable
{
	private static TemporalDirector temporalDirector = new AuditOnlyTemporalDirector(
		PetFinder.processingDate()
	, PetFinder.zGetDoubleAttributes(),PetFinder.zGetBigDecimalAttributes());
	protected transient Timestamp processingDate;
	private static final Logger logger = LoggerFactory.getLogger(Pet.class.getName());
	private static byte MEMORY_STATE = DatedPersistenceState.IN_MEMORY;
	private static byte PERSISTED_STATE = DatedPersistenceState.PERSISTED;
	private static final RelationshipHashStrategy forpetType = new PetTypeRhs();
	private static final RelationshipHashStrategy forowner = new OwnerRhs();
	private static final class PetTypeRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			PetData _castedSrcData = (PetData) _srcData;
			PetTypeData _castedTargetData = (PetTypeData) _targetData;
			if (_castedSrcData.getPetTypeId() == _castedTargetData.getPetTypeId())
			{
				return true;
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			PetData _castedSrcData = (PetData) _srcData;
			return HashUtil.hash(_castedSrcData.getPetTypeId());
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			return computeHashCodeFromRelated(_srcObject, _srcData);
		}
	}

	private static final class OwnerRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			PetData _castedSrcData = (PetData) _srcData;
			PersonData _castedTargetData = (PersonData) _targetData;
			if (_castedSrcData.getPersonId() == _castedTargetData.getPersonId())
			{
				return true;
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			PetData _castedSrcData = (PetData) _srcData;
			return HashUtil.hash(_castedSrcData.getPersonId());
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			return computeHashCodeFromRelated(_srcObject, _srcData);
		}
	}

	public PetAbstract(Timestamp processingDate
	)
	{
		this.processingDate = TimestampPool.getInstance().getOrAddToCache(processingDate, PetFinder.isFullCache());
		this.persistenceState = MEMORY_STATE;
	}

	public PetData zSynchronizedGetData()
	{
		return (PetData) super.zSynchronizedGetData();
	}

	protected boolean checkAsOfAttributesForRefresh(MithraDataObject current)
	{
		boolean refresh = !PetFinder.processingDate().dataMatches(current, this.processingDate);
		return refresh;
	}

	public TemporalDirector zGetTemporalDirector()
	{
		return temporalDirector;
	}

	public void insertUntil(Timestamp exclusiveUntil) throws MithraBusinessException
	{
		throw new MithraBusinessException("insertUntil is only supported for dated objects with a business date");
	}

	public void terminateUntil(Timestamp exclusiveUntil) throws MithraBusinessException
	{
		throw new MithraBusinessException("terminateUntil is only supported for dated objects with a business date");
	}

	protected void cascadeInsertImpl() throws MithraBusinessException
	{
		DatedTransactionalBehavior _behavior = zGetTransactionalBehaviorForWrite();
		_behavior.insert(this);
	}

	@Override
	public Map< RelatedFinder, StatisticCounter > zAddNavigatedRelationshipsStats(RelatedFinder finder, Map< RelatedFinder, StatisticCounter > navigationStats)
	{
		DatedTransactionalBehavior _behavior = zGetTransactionalBehaviorForWrite();
		_behavior.addNavigatedRelationshipsStats(this, finder, navigationStats);
		return navigationStats;
	}

	@Override
	public Map< RelatedFinder, StatisticCounter > zAddNavigatedRelationshipsStatsForUpdate(RelatedFinder parentFinderGeneric, Map< RelatedFinder, StatisticCounter > navigationStats)
	{
		return navigationStats;
	}

	@Override
	public Map< RelatedFinder, StatisticCounter > zAddNavigatedRelationshipsStatsForDelete(RelatedFinder parentFinder, Map< RelatedFinder, StatisticCounter > navigationStats)
	{
		return navigationStats;
	}

	public Pet zCascadeCopyThenInsert() throws MithraBusinessException
	{
		DatedTransactionalBehavior _behavior = zGetTransactionalBehaviorForWrite();
		Pet original = (Pet) _behavior.copyThenInsert(this);
		return original;
	}

	public void cascadeInsertUntil(Timestamp exclusiveUntil) throws MithraBusinessException
	{
		this.insertUntil(exclusiveUntil);
	}

	protected DatedTransactionalState zCreateDatedTransactionalState(TemporalContainer container, MithraDataObject data, MithraTransaction threadTx)
	{
		return new DatedTransactionalState(threadTx,
			this.persistenceState, container, data, null,
			PetFinder.processingDate().getInfinityDate().equals(this.processingDate) || threadTx.isInFuture(this.processingDate.getTime()));
	}

	public Pet getNonPersistentCopy() throws MithraBusinessException
	{
		DatedTransactionalBehavior behavior = this.zGetTransactionalBehavior();
		MithraDataObject data = behavior.getCurrentDataForRead(this);
		MithraDataObject newData = data.copy(!behavior.isPersisted());
		Timestamp[] asOfAttributes = new Timestamp[1];
		asOfAttributes[0] = this.processingDate;
		PetAbstract result = (PetAbstract)
		((MithraDatedObjectFactory) this.zGetPortal().getMithraDatedObjectFactory()).createObject(newData, asOfAttributes);
		result.zSetNonTxPersistenceState(MEMORY_STATE);
		if (result.transactionalState != null)
		{
			result.zSetTxPersistenceState(DatedPersistenceState.IN_MEMORY);
		}

		return (Pet ) result;
	}

	public Pet zFindOriginal()
	{
		PetData data = (PetData) this.currentData;
		Operation op;
		op = PetFinder.petName().eq(data.getPetName());
		op = op.and(PetFinder.processingDate().eq(this.getProcessingDate()));
		return PetFinder.findOne(op);
	}

	public Pet getDetachedCopy() throws MithraBusinessException
	{
		DatedTransactionalBehavior behavior = this.zGetTransactionalBehavior();
		Timestamp[] asOfAttributes = new Timestamp[1];
		asOfAttributes[0] = this.processingDate;
		PetAbstract result = (PetAbstract) behavior.getDetachedCopy(this, asOfAttributes);
		if (result.transactionalState != null)
		{
			result.zSetTxPersistenceState(DatedPersistenceState.DETACHED);
		}

		return (Pet ) result;
	}

	public boolean isModifiedSinceDetachmentByDependentRelationships()
	{
		if(this.isModifiedSinceDetachment()) return true;
		return false;
	}

	public Pet copyDetachedValuesToOriginalOrInsertIfNewImpl(MithraTransaction tx)
	{
		DatedTransactionalBehavior behavior = this.zGetTransactionalBehaviorForWrite();
		return (Pet) behavior.updateOriginalOrInsert(this);
	}

	public Pet copyDetachedValuesToOriginalOrInsertIfNew()
	{
		return (Pet) this.zCopyDetachedValuesToOriginalOrInsertIfNew();
	}

	public Object readResolve() throws ObjectStreamException
	{
		if (this.persistenceState == DatedPersistenceState.PERSISTED)
		{
			PetData data = (PetData) this.currentData;
			Operation op = PetFinder.processingDate().eq(this.processingDate);
			op = op.and(PetFinder.petName().eq(data.getPetName()));
			return PetFinder.findOne(op);
		}

		return this;
	}

	public boolean zHasSameNullPrimaryKeyAttributes(MithraTransactionalObject other)
	{
		return true;
	}

	public boolean isPersonIdNull()
	{
		return this.zSynchronizedGetData().isPersonIdNull();
	}

	public boolean isPetAgeNull()
	{
		return this.zSynchronizedGetData().isPetAgeNull();
	}

	public boolean isPetNameNull()
	{
		return this.zSynchronizedGetData().isPetNameNull();
	}

	public boolean isPetTypeIdNull()
	{
		return this.zSynchronizedGetData().isPetTypeIdNull();
	}

	public boolean isProcessingDateFromNull()
	{
		return this.zSynchronizedGetData().isProcessingDateFromNull();
	}

	public boolean isProcessingDateToNull()
	{
		return this.zSynchronizedGetData().isProcessingDateToNull();
	}

	public int getPersonId()
	{
		return this.zSynchronizedGetData().getPersonId();
	}

	public void setPersonId(int newValue)
	{
		zSetInteger(PetFinder.personId(), newValue, false ,false);
	}

	public int getPetAge()
	{
		return this.zSynchronizedGetData().getPetAge();
	}

	public void setPetAge(int newValue)
	{
		zSetInteger(PetFinder.petAge(), newValue, false ,false);
	}

	public String getPetName()
	{
		return this.zSynchronizedGetData().getPetName();
	}

	public void setPetName(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 16)
		{
			throw new MithraBusinessException("Attribute 'petName' cannot exceed maximum length of 16: " + newValue);
		}

		zSetString(PetFinder.petName(), newValue, true );
	}

	public int getPetTypeId()
	{
		return this.zSynchronizedGetData().getPetTypeId();
	}

	public void setPetTypeId(int newValue)
	{
		zSetInteger(PetFinder.petTypeId(), newValue, false ,false);
	}

	public Timestamp getProcessingDateFrom()
	{
		return this.zSynchronizedGetData().getProcessingDateFrom();
	}

	public void setProcessingDateFrom(Timestamp newValue)
	{
		zSetTimestamp(PetFinder.processingDateFrom(), newValue, false );
	}

	public Timestamp getProcessingDateTo()
	{
		return this.zSynchronizedGetData().getProcessingDateTo();
	}

	public void setProcessingDateTo(Timestamp newValue)
	{
		zSetTimestamp(PetFinder.processingDateTo(), newValue, false );
	}

	protected void issuePrimitiveNullSetters(DatedTransactionalBehavior behavior, MithraDataObject data, boolean mustCheckCurrent)
	{
	}

	public Timestamp getProcessingDate()
	{
		return this.processingDate;
	}

	public void zPersistDetachedRelationships(MithraDataObject _data)
	{
		PetData _newData = (PetData) _data;
	}

	public void zSetTxDetachedDeleted()
	{
		DatedTransactionalBehavior behavior = this.zGetTransactionalBehavior();
		if (behavior.isDetached() && behavior.isDeleted()) return;
		PetData _newData = (PetData) behavior.getCurrentDataForRead(this);
		this.zSetTxPersistenceState(DatedPersistenceState.DETACHED_DELETED);
	}

	public void zSetNonTxDetachedDeleted()
	{
		DatedTransactionalBehavior behavior = this.zGetTransactionalBehavior();
		PetData _newData = (PetData) behavior.getCurrentDataForRead(this);
		this.zSetNonTxPersistenceState(DatedPersistenceState.DETACHED_DELETED);
	}

	/**
	* Relationship Expression:<pre>
	this.petTypeId = PetType.petTypeId</pre>
	* @return The pet type
	*/
	public PetType getPetType()
	{
		PetType _result = null;
		Operation _op = null;
		DatedTransactionalBehavior _behavior = zGetTransactionalBehavior();
		PetData _data = (PetData) _behavior.getCurrentDataForRead(this);
		MithraObjectPortal _portal = null;
		if (_behavior.isPersisted())
		{
			{
				_portal = PetTypeFinder.getMithraObjectPortal();
				Object _related = _portal.getAsOneFromCache(this, _data, forpetType, null, null);
				if (!(_related instanceof NulledRelation)) _result = (PetType) _related;
				if (_related == null)
				{
					_op = PetTypeFinder.petTypeId().eq(_data.getPetTypeId());
				}
			}
		}
		else if (_behavior.isDetached())
		{
			{
				{
					Operation detachedOp = PetTypeFinder.petTypeId().eq(_data.getPetTypeId());
					_result = PetTypeFinder.zFindOneForRelationship(detachedOp);
				}
			}
		}
		else if (_behavior.isInMemory())
		{
			_result = (PetType) _data.getPetType();
			if (_result == null)
			{
				{
					_op = PetTypeFinder.petTypeId().eq(_data.getPetTypeId());
				}
			}
		}

		if (_op != null)
		{
			_result = PetTypeFinder.zFindOneForRelationship(_op);
		}

		return _result;
	}

	public void setPetType(PetType petType)
	{
		PetType _petType = (PetType) petType;
		DatedTransactionalBehavior _behavior = zGetTransactionalBehaviorForWrite();
		PetData _data = (PetData) _behavior.getCurrentDataForWrite(this);
		if (_behavior.isInMemory())
		{
			_data.setPetType(_petType);
			if (_petType == null)
			{
				this.setPetTypeId(0);
			}
			else
			{
				this.setPetTypeId(
					_petType.getPetTypeId());
			}
		}
		else if (_behavior.isPersisted())
		{
			if (_petType == null)
			{
				this.setPetTypeId(0);
			}
			else
			{
				this.setPetTypeId(
					_petType.getPetTypeId());
			}
		}
		else throw new RuntimeException("not implemented");
	}

	/**
	* Relationship Expression:<pre>
	Person.personId = this.personId</pre>
	* @see Person#getPets() reverse relationship Person.getPets()
	* @return The owner
	*/
	public Person getOwner()
	{
		Person _result = null;
		Operation _op = null;
		DatedTransactionalBehavior _behavior = zGetTransactionalBehavior();
		PetData _data = (PetData) _behavior.getCurrentDataForRead(this);
		MithraObjectPortal _portal = null;
		if (_behavior.isPersisted())
		{
			{
				_portal = PersonFinder.getMithraObjectPortal();
				Object _related = _portal.getAsOneFromCache(this, _data, forowner, null, null);
				if (!(_related instanceof NulledRelation)) _result = (Person) _related;
				if (_related == null)
				{
					_op = PersonFinder.personId().eq(_data.getPersonId());
				}
			}
		}
		else if (_behavior.isDetached())
		{
			if (_data.getOwner() instanceof NulledRelation)
			{
				return null;
			}

			_result = (Person) _data.getOwner();
			if (_result == null)
			{
				{
					Operation detachedOp = PersonFinder.personId().eq(_data.getPersonId());
					_result = PersonFinder.zFindOneForRelationship(detachedOp);
					if(_result != null)
					{
						_result = _result.getDetachedCopy();
					}
				}

				_behavior = zGetTransactionalBehaviorForWrite();
				_data = (PetData) _behavior.getCurrentDataForWrite(this);
				_data.setOwner(_result);
			}
		}
		else if (_behavior.isInMemory())
		{
			_result = (Person) _data.getOwner();
			if (_result == null)
			{
				{
					_op = PersonFinder.personId().eq(_data.getPersonId());
				}
			}
		}

		if (_op != null)
		{
			_result = PersonFinder.zFindOneForRelationship(_op);
		}

		return _result;
	}

	public void setOwner(Person owner)
	{
		Person _owner = (Person) owner;
		DatedTransactionalBehavior _behavior = zGetTransactionalBehaviorForWrite();
		PetData _data = (PetData) _behavior.getCurrentDataForWrite(this);
		if (_behavior.isInMemory())
		{
			Object _prev = _data.getOwner();
			if (_behavior.isDetached() && _prev != null)
			{
				((DelegatingList)((Person)_prev).getPets()).zMarkMoved( (Pet) this);
			}

			_data.setOwner(_owner);
			_owner.getPets().add((Pet) this);
		}
		else if (_behavior.isPersisted())
		{
			if (_owner == null)
			{
				this.setPersonId(0);
			}
			else
			{
				this.setPersonId(
					_owner.getPersonId());
			}
		}
		else throw new RuntimeException("not implemented");
	}

	public void zSetParentContainerowner(PersonAbstract parent)
	{
		DatedTransactionalBehavior _behavior = zGetTransactionalBehaviorForWrite();
		PetData _data = (PetData) _behavior.getCurrentDataForWrite(this);
		if (_behavior.isInMemory())
		{
			_data.setOwner(parent);
		}
	}

	public Cache zGetCache()
	{
		return PetFinder.getMithraObjectPortal().getCache();
	}

	private Logger getLogger()
	{
		return logger;
	}

	public MithraDataObject zAllocateData()
	{
		return new PetData();
	}

	public MithraDataObject zRefreshWithLock(boolean lock)
	{
		PetData data = (PetData) PetFinder.getMithraObjectPortal().refreshDatedObject(this, lock);
		if (data == null)
		{
			throw new MithraDeletedException("Pet has been deleted.");
		}

		return data;
	}

	public void setFromPetData( PetData data )
	{
		this.zSetData(data);
	}

	protected void zSetFromPetData( PetData data )
	{
		this.zSetData(data);
		this.zSetNonTxPersistenceState(PERSISTED_STATE);
	}

	public MithraTransactionalDatabaseObject zGetDatabaseObject()
	{
		return (MithraTransactionalDatabaseObject) PetFinder.getMithraObjectPortal().getDatabaseObject();
	}

	public MithraObjectPortal zGetPortal()
	{
		return PetFinder.getMithraObjectPortal();
	}

	public Pet getOriginalPersistentObject()
	{
		return this.zFindOriginal();
	}

	public void copyNonPrimaryKeyAttributesFrom(PetAbstract from) throws MithraBusinessException
	{
		this.copyNonPrimaryKeyAttributesFrom((MithraTransactionalObject)from);
	}

	protected void copyNonPrimaryKeyAttributesFromImpl(MithraTransactionalObject f, MithraTransaction tx) throws MithraBusinessException
	{
		PetAbstract from = (PetAbstract) f;
		PetData newData = from.zSynchronizedGetData();
		this.setPersonId(newData.getPersonId());
		this.setPetAge(newData.getPetAge());
		this.setPetTypeId(newData.getPetTypeId());
	}

	protected void zCheckOptimisticLocking(MithraTransaction tx, MithraDataObject d, MithraDataObject nd)
	{
		PetData newData = (PetData) d;
		PetData data = (PetData) nd;
		if (PetFinder.getMithraObjectPortal().getTxParticipationMode(tx).isOptimisticLocking()
		&& !tx.retryOnOptimisticLockFailure() && !newData.getProcessingDateFrom().equals(data.getProcessingDateFrom()))
		{
			throw new MithraOptimisticLockException("Optimistic lock failure. "+data.zGetPrintablePrimaryKey());
		}
	}

	protected boolean issueUpdates(DatedTransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateInteger(behavior, data, newData, PetFinder.personId(), false);
		changed |= zUpdateInteger(behavior, data, newData, PetFinder.petAge(), false);
		changed |= zUpdateString(behavior, data, newData, PetFinder.petName(), false);
		changed |= zUpdateInteger(behavior, data, newData, PetFinder.petTypeId(), false);
		return changed;
	}

	public void cascadeTerminate()
	{
		this.terminate();
	}

	public void cascadeTerminateUntil(Timestamp exclusiveUntil) throws MithraBusinessException
	{
		this.terminateUntil(exclusiveUntil);
	}

	public void zSerializePrimaryKey(ObjectOutput out) throws IOException
	{
		PetData data = ((PetData)this.zGetCurrentOrTransactionalData());
		data.zSerializePrimaryKey(out);
		MithraTimestamp.writeTimezoneInsensitiveTimestampWithInfinity(out, processingDate, com.petstore.util.TimestampProvider.getInfinityDate());
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public void zSerializeFullData(ObjectOutput out) throws IOException
	{
		PetData data = ((PetData)this.zGetCurrentDataWithCheck());
		data.zSerializeFullData(out);
		MithraTimestamp.writeTimezoneInsensitiveTimestampWithInfinity(out, processingDate, com.petstore.util.TimestampProvider.getInfinityDate());
	}

	public void zSerializeFullTxData(ObjectOutput out) throws IOException
	{
		PetData data = ((PetData)this.zGetTxDataForRead());
		data.zSerializeFullData(out);
		MithraTimestamp.writeTimezoneInsensitiveTimestampWithInfinity(out, processingDate, com.petstore.util.TimestampProvider.getInfinityDate());
	}

	protected void zSerializeAsOfAttributes(java.io.ObjectOutputStream out) throws IOException
	{
		MithraTimestamp.writeTimezoneInsensitiveTimestampWithInfinity(out, processingDate, com.petstore.util.TimestampProvider.getInfinityDate());
	}

	protected void zDeserializeAsOfAttributes(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		this.processingDate = TimestampPool.getInstance().getOrAddToCache(MithraTimestamp.readTimezoneInsensitiveTimestampWithInfinity(in, com.petstore.util.TimestampProvider.getInfinityDate()), PetFinder.isFullCache());
	}

	public boolean zDataMatches(Object data, Timestamp[] asOfDates)
	{
		PetData localData = (PetData) data;
		MithraDataObject thisData = this.zGetCurrentOrTransactionalData();
		return thisData != null && localData.hasSamePrimaryKeyIgnoringAsOfAttributes(thisData)
		&& this.processingDate.equals(asOfDates[0])
		;
	}

	protected static void zConfigNonTx()
	{
		MEMORY_STATE = DatedPersistenceState.IN_MEMORY_NON_TRANSACTIONAL;
		PERSISTED_STATE = DatedPersistenceState.PERSISTED_NON_TRANSACTIONAL;
	}

	protected static void zConfigFullTx()
	{
		MEMORY_STATE = DatedPersistenceState.IN_MEMORY;
		PERSISTED_STATE = DatedPersistenceState.PERSISTED;
	}
}
