package petstore.ch1.domain;
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
import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.behavior.*;
import com.gs.fw.common.mithra.cache.Cache;
import com.gs.fw.common.mithra.extractor.*;
import com.gs.fw.common.mithra.finder.*;
import com.gs.fw.common.mithra.list.*;
import com.gs.fw.common.mithra.behavior.state.PersistenceState;
import com.gs.fw.common.mithra.attribute.update.*;
import com.gs.fw.common.mithra.transaction.MithraObjectPersister;
import java.util.Arrays;
import java.util.HashSet;
/**
* This file was automatically generated using Mithra 16.1.0. Please do not modify it.
* Add custom logic to its subclass instead.
*/
// Generated from templates/transactional/Abstract.jsp
public abstract class PetAbstract extends com.gs.fw.common.mithra.superclassimpl.MithraTransactionalObjectImpl
{
	private static byte MEMORY_STATE = PersistenceState.IN_MEMORY;
	private static byte PERSISTED_STATE = PersistenceState.PERSISTED;
	private static final Logger logger = LoggerFactory.getLogger(Pet.class.getName());
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

	public PetAbstract()
	{
		this.persistenceState = MEMORY_STATE;
	}

	public Pet getDetachedCopy() throws MithraBusinessException
	{
		return (Pet) super.getDetachedCopy();
	}

	public Pet getNonPersistentCopy() throws MithraBusinessException
	{
		Pet result = (Pet) super.getNonPersistentCopy();
		result.persistenceState = MEMORY_STATE;
		return result;
	}

	public Pet copyDetachedValuesToOriginalOrInsertIfNew()
	{
		return (Pet) this.zCopyDetachedValuesToOriginalOrInsertIfNew();
	}

	public Pet zFindOriginal()
	{
		PetData data = (PetData) this.currentData;
		Operation op;
		op = PetFinder.petName().eq(data.getPetName());
		return PetFinder.findOne(op);
	}

	public boolean isModifiedSinceDetachmentByDependentRelationships()
	{
		if(this.isModifiedSinceDetachment()) return true;
		return false;
	}

	private Logger getLogger()
	{
		return logger;
	}

	public MithraDataObject zAllocateData()
	{
		return new PetData();
	}

	protected void zSetFromPetData( PetData data )
	{
		super.zSetData(data);
		this.persistenceState = PERSISTED_STATE;
	}

	public void setFromPetData( PetData data )
	{
		super.zSetData(data);
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public boolean isPersonIdNull()
	{
		return ((PetData) this.zSynchronizedGetData()).isPersonIdNull();
	}

	public int getPersonId()
	{
		PetData data = (PetData) this.zSynchronizedGetData();
		return data.getPersonId();
	}

	public void setPersonId(int newValue)
	{
		zSetInteger(PetFinder.personId(), newValue, false, false ,false);
	}

	public boolean isPetAgeNull()
	{
		return ((PetData) this.zSynchronizedGetData()).isPetAgeNull();
	}

	public int getPetAge()
	{
		PetData data = (PetData) this.zSynchronizedGetData();
		return data.getPetAge();
	}

	public void setPetAge(int newValue)
	{
		zSetInteger(PetFinder.petAge(), newValue, false, false ,false);
	}

	public boolean isPetNameNull()
	{
		return ((PetData) this.zSynchronizedGetData()).isPetNameNull();
	}

	public String getPetName()
	{
		PetData data = (PetData) this.zSynchronizedGetData();
		return data.getPetName();
	}

	public void setPetName(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 16)
		throw new MithraBusinessException("Attribute 'petName' cannot exceed maximum length of 16: " + newValue);
		zSetString(PetFinder.petName(), newValue, true, false );
	}

	public boolean isPetTypeIdNull()
	{
		return ((PetData) this.zSynchronizedGetData()).isPetTypeIdNull();
	}

	public int getPetTypeId()
	{
		PetData data = (PetData) this.zSynchronizedGetData();
		return data.getPetTypeId();
	}

	public void setPetTypeId(int newValue)
	{
		zSetInteger(PetFinder.petTypeId(), newValue, false, false ,false);
	}

	protected void issuePrimitiveNullSetters(TransactionalBehavior behavior, MithraDataObject data)
	{
	}

	public void zPersistDetachedRelationships(MithraDataObject _data)
	{
		PetData _newData = (PetData) _data;
	}

	public void zSetTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		if (_behavior.isDetached() && _behavior.isDeleted()) return;
		PetData _newData = (PetData) _behavior.getCurrentDataForRead(this);
		this.zSetTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	public void zSetNonTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PetData _newData = (PetData) _behavior.getCurrentDataForRead(this);
		this.zSetNonTxPersistenceState(PersistenceState.DETACHED_DELETED);
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
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForReadWithWaitIfNecessary();
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
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
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
				this.setPetTypeId(_petType.getPetTypeId());
			}
		}
		else if (_behavior.isPersisted())
		{
			_behavior.clearTempTransaction(this);
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
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForReadWithWaitIfNecessary();
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
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PetData _data = (PetData) _behavior.getCurrentDataForWrite(this);
		if (_behavior.isInMemory())
		{
			Object _prev = _data.getOwner();
			if (_behavior.isDetached() && _prev != null)
			{
				((DelegatingList)((Person)_prev).getPets()).zMarkMoved( (Pet) this);
			}

			_data.setOwner(_owner);
			_owner.getPets().add( (Pet) this);
		}
		else if (_behavior.isPersisted())
		{
			_behavior.clearTempTransaction(this);
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
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PetData _data = (PetData) _behavior.getCurrentDataForWrite(this);
		_behavior.clearTempTransaction(this);
		if (_behavior.isInMemory())
		{
			_data.setOwner(parent);
		}
	}

	protected void cascadeInsertImpl() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		_behavior.insert(this);
	}

	@Override
	public Map< RelatedFinder, StatisticCounter > zAddNavigatedRelationshipsStats(RelatedFinder finder, Map< RelatedFinder, StatisticCounter > navigationStats)
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
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

	@Override
	public Pet zCascadeCopyThenInsert() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		Pet original = (Pet) _behavior.copyThenInsert(this);
		return original;
	}

	protected void cascadeDeleteImpl() throws MithraBusinessException
	{
		this.delete();
	}

	public Cache zGetCache()
	{
		return PetFinder.getMithraObjectPortal().getCache();
	}

	public MithraObjectPortal zGetPortal()
	{
		return PetFinder.getMithraObjectPortal();
	}

	public Pet getOriginalPersistentObject()
	{
		return this.zFindOriginal();
	}

	protected boolean issueUpdatesForNonPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateInteger(behavior, data, newData, PetFinder.personId(), false);
		changed |= zUpdateInteger(behavior, data, newData, PetFinder.petAge(), false);
		changed |= zUpdateInteger(behavior, data, newData, PetFinder.petTypeId(), false);
		return changed;
	}

	protected boolean issueUpdatesForPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateString(behavior, data, newData, PetFinder.petName(), false);
		return changed;
	}

	public Object readResolve() throws ObjectStreamException
	{
		PetAbstract result = (PetAbstract) super.readResolve();
		if (result.persistenceState == PersistenceState.PERSISTED)
		{
			result.persistenceState = PERSISTED_STATE;
		}
		else if (result.persistenceState == PersistenceState.IN_MEMORY)
		{
			result.persistenceState = MEMORY_STATE;
		}

		return result;
	}

	protected static void zConfigNonTx()
	{
		MEMORY_STATE = PersistenceState.IN_MEMORY_NON_TRANSACTIONAL;
		PERSISTED_STATE = PersistenceState.PERSISTED_NON_TRANSACTIONAL;
	}

	protected static void zConfigFullTx()
	{
		MEMORY_STATE = PersistenceState.IN_MEMORY;
		PERSISTED_STATE = PersistenceState.PERSISTED;
	}
}
