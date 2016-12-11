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
public abstract class PersonAbstract extends com.gs.fw.common.mithra.superclassimpl.MithraTransactionalObjectImpl
{
	private static byte MEMORY_STATE = PersistenceState.IN_MEMORY;
	private static byte PERSISTED_STATE = PersistenceState.PERSISTED;
	private static final Logger logger = LoggerFactory.getLogger(Person.class.getName());
	public PersonAbstract()
	{
		this.persistenceState = MEMORY_STATE;
	}

	public Person getDetachedCopy() throws MithraBusinessException
	{
		return (Person) super.getDetachedCopy();
	}

	public Person getNonPersistentCopy() throws MithraBusinessException
	{
		Person result = (Person) super.getNonPersistentCopy();
		result.persistenceState = MEMORY_STATE;
		return result;
	}

	public Person copyDetachedValuesToOriginalOrInsertIfNew()
	{
		return (Person) this.zCopyDetachedValuesToOriginalOrInsertIfNew();
	}

	public Person zFindOriginal()
	{
		PersonData data = (PersonData) this.currentData;
		Operation op;
		op = PersonFinder.personId().eq(data.getPersonId());
		return PersonFinder.findOne(op);
	}

	public boolean isModifiedSinceDetachmentByDependentRelationships()
	{
		if(this.isModifiedSinceDetachment()) return true;
		if(isPetsModifiedSinceDetachment()) return true;
		return false;
	}

	private Logger getLogger()
	{
		return logger;
	}

	public MithraDataObject zAllocateData()
	{
		return new PersonData();
	}

	protected void zSetFromPersonData( PersonData data )
	{
		super.zSetData(data);
		this.persistenceState = PERSISTED_STATE;
	}

	public void setFromPersonData( PersonData data )
	{
		super.zSetData(data);
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public boolean isFirstNameNull()
	{
		return ((PersonData) this.zSynchronizedGetData()).isFirstNameNull();
	}

	public String getFirstName()
	{
		PersonData data = (PersonData) this.zSynchronizedGetData();
		return data.getFirstName();
	}

	public void setFirstName(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 16)
		throw new MithraBusinessException("Attribute 'firstName' cannot exceed maximum length of 16: " + newValue);
		zSetString(PersonFinder.firstName(), newValue, false, false );
	}

	public boolean isLastNameNull()
	{
		return ((PersonData) this.zSynchronizedGetData()).isLastNameNull();
	}

	public String getLastName()
	{
		PersonData data = (PersonData) this.zSynchronizedGetData();
		return data.getLastName();
	}

	public void setLastName(String newValue)
	{
		if (newValue != null) newValue = newValue.trim();
		if (newValue != null && newValue.length() > 24)
		throw new MithraBusinessException("Attribute 'lastName' cannot exceed maximum length of 24: " + newValue);
		zSetString(PersonFinder.lastName(), newValue, false, false );
	}

	public boolean isPersonIdNull()
	{
		return ((PersonData) this.zSynchronizedGetData()).isPersonIdNull();
	}

	public int getPersonId()
	{
		PersonData data = (PersonData) this.zSynchronizedGetData();
		return data.getPersonId();
	}

	public void setPersonId(int newValue)
	{
		MithraDataObject d = zSetInteger(PersonFinder.personId(), newValue, true, false ,false);
		if (d == null) return;
		PersonData data = (PersonData) d;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		if (!_behavior.isPersisted())
		{
			PetList pets =
			(PetList ) data.getPets();
			if (pets != null)
			{
				pets.setPersonId(newValue);
			}
		}
	}

	protected void issuePrimitiveNullSetters(TransactionalBehavior behavior, MithraDataObject data)
	{
	}

	public void zPersistDetachedRelationships(MithraDataObject _data)
	{
		PersonData _newData = (PersonData) _data;
		{
			PetList pets =
			(PetList) _newData.getPets();
			if (pets != null)
			{
				pets.copyDetachedValuesToOriginalOrInsertIfNewOrDeleteIfRemoved();
			}
		}
	}

	public void zSetTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		if (_behavior.isDetached() && _behavior.isDeleted()) return;
		PersonData _newData = (PersonData) _behavior.getCurrentDataForRead(this);
		if (_newData.getPets() != null && !(_newData.getPets() instanceof NulledRelation))
		{
			((PetList)_newData.getPets()).zSetTxDetachedDeleted();
		}

		this.zSetTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	public void zSetNonTxDetachedDeleted()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PersonData _newData = (PersonData) _behavior.getCurrentDataForRead(this);
		if (_newData.getPets() != null && !(_newData.getPets() instanceof NulledRelation))
		{
			((PetList)_newData.getPets()).zSetNonTxDetachedDeleted();
		}

		this.zSetNonTxPersistenceState(PersistenceState.DETACHED_DELETED);
	}

	/**
	* Relationship Expression:<pre>
	this.personId = Pet.personId</pre>
	* @see Pet#getOwner() reverse relationship Pet.getOwner()
	* @return pets
	*/
	public PetList getPets()
	{
		PetList _result = null;
		Operation _op = null;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForReadWithWaitIfNecessary();
		PersonData _data = (PersonData) _behavior.getCurrentDataForRead(this);
		if (_behavior.isPersisted())
		{
			{
				{
					_op = PetFinder.personId().eq(_data.getPersonId());
				}
			}
		}
		else if (_behavior.isDetached())
		{
			_result = (PetList) _data.getPets();
			if (_result == null)
			{
				{
					Operation detachedOp = PetFinder.personId().eq(_data.getPersonId());
					_result = new PetList(detachedOp);
					_result.zSetForRelationship();
					if(_result != null)
					{
						_result = _result.getDetachedCopy();
					}

					_result.zSetAddHandler(new PetsAddHandlerInMemory());
				}

				_behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
				_data = (PersonData) _behavior.getCurrentDataForWrite(this);
				_data.setPets(_result);
				if (_result != null) _result.zSetParentContainerowner(this);
			}
		}
		else if (_behavior.isInMemory())
		{
			_result = (PetList) _data.getPets();
			if (_result == null)
			{
				_result = new PetList();
				_result.zSetAddHandler(new PetsAddHandlerInMemory());
				_behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
				_data = (PersonData) _behavior.getCurrentDataForWrite(this);
				_data.setPets(_result);
			}
		}

		if (_op != null)
		{
			_result = new PetList(_op);
			_result.zSetForRelationship();
			_result.zSetRemoveHandler(DeleteOnRemoveHandler.getInstance());
			_result.zSetAddHandler(new PetsAddHandlerPersisted());
		}

		return _result;
	}

	public void setPets(PetList pets)
	{
		PetList _pets = (PetList) pets;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PersonData _data = (PersonData) _behavior.getCurrentDataForWrite(this);
		if (_behavior.isInMemory())
		{
			if (_behavior.isDetached() && _pets != null)
			{
				_pets.zMakeDetached(PetFinder.personId().eq(_data.getPersonId()),
					_data.getPets());
			}

			_data.setPets(_pets);
			if (_pets != null)
			{
				_pets.setPersonId(_data.getPersonId());
				_pets.zSetParentContainerowner(this);
				_pets.zSetAddHandler(new PetsAddHandlerInMemory());
			}
			else if (_behavior.isDetached())
			{
				throw new MithraBusinessException("to-many relationships cannot be set to null. Use the clear() method on the list instead.");
			}
		}
		else if (_behavior.isPersisted())
		{
			_behavior.clearTempTransaction(this);
			_pets.zSetAddHandler(new PetsAddHandlerPersisted());
			PetList petsToDelete = new PetList();
			petsToDelete.addAll(this.getPets());
			for(int i=0;i < _pets.size(); i++)
			{
				Pet item = _pets.getPetAt(i);
				if (!petsToDelete.remove(item))
				{
					item.setPersonId(_data.getPersonId());
					item.cascadeInsert();
				}
			}

			petsToDelete.cascadeDeleteAll();
		}
		else throw new RuntimeException("not implemented");
	}

	public boolean isPetsModifiedSinceDetachment()
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForReadWithWaitIfNecessary();
		PersonData _data = (PersonData) _behavior.getCurrentDataForRead(this);
		PetList pets =
		(PetList) _data.getPets();
		if( pets != null)
		{
			return pets.isModifiedSinceDetachment();
		}

		return false;
	}

	protected void cascadeInsertImpl() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PersonData _data = (PersonData) _behavior.getCurrentDataForWrite(this);
		PetList pets =
		(PetList) _data.getPets();
		_behavior.insert(this);
		if (pets != null)
		{
			pets.cascadeInsertAll();
		}
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
		PersonFinder.PersonRelatedFinder parentFinder = (PersonFinder.PersonRelatedFinder) parentFinderGeneric;
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PersonData _newData = (PersonData) _behavior.getCurrentDataForWrite(this);
		{
			PetList pets =
			(PetList) _newData.getPets();
			RelatedFinder dependentFinder = parentFinder.pets();
			DeepRelationshipUtility.zAddToNavigationStats(dependentFinder, pets != null, navigationStats);
			if (pets != null)
			{
				pets.zCascadeAddNavigatedRelationshipsStats(dependentFinder, navigationStats);
			}
		}

		return navigationStats;
	}

	@Override
	public Map< RelatedFinder, StatisticCounter > zAddNavigatedRelationshipsStatsForDelete(RelatedFinder parentFinder, Map< RelatedFinder, StatisticCounter > navigationStats)
	{
		DeepRelationshipUtility.zAddAllDependentNavigationsStatsForDelete(parentFinder, navigationStats);
		return navigationStats;
	}

	@Override
	public Person zCascadeCopyThenInsert() throws MithraBusinessException
	{
		TransactionalBehavior _behavior = zGetTransactionalBehaviorForWriteWithWaitIfNecessary();
		PersonData _data = (PersonData) _behavior.getCurrentDataForWrite(this);
		PetList pets =
		(PetList) _data.getPets();
		Person original = (Person) _behavior.copyThenInsert(this);
		if (pets != null)
		{
			pets.zCascadeCopyThenInsertAll();
		}

		return original;
	}

	protected void cascadeDeleteImpl() throws MithraBusinessException
	{
		this.getPets().cascadeDeleteAll();
		this.delete();
	}

	public Cache zGetCache()
	{
		return PersonFinder.getMithraObjectPortal().getCache();
	}

	public MithraObjectPortal zGetPortal()
	{
		return PersonFinder.getMithraObjectPortal();
	}

	public Person getOriginalPersistentObject()
	{
		return this.zFindOriginal();
	}

	protected boolean issueUpdatesForNonPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateString(behavior, data, newData, PersonFinder.firstName(), false);
		changed |= zUpdateString(behavior, data, newData, PersonFinder.lastName(), false);
		return changed;
	}

	protected boolean issueUpdatesForPrimaryKeys(TransactionalBehavior behavior, MithraDataObject data, MithraDataObject newData)
	{
		boolean changed = false;
		changed |= zUpdateInteger(behavior, data, newData, PersonFinder.personId(), false);
		return changed;
	}

	public Object readResolve() throws ObjectStreamException
	{
		PersonAbstract result = (PersonAbstract) super.readResolve();
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

	protected class PetsAddHandlerInMemory implements DependentRelationshipAddHandler
	{
		public void addRelatedObject(MithraTransactionalObject relatedObject)
		{
			Pet item = (Pet) relatedObject;
			item.setPersonId(getPersonId());
			item.zSetParentContainerowner(PersonAbstract.this);
		}
	}

	protected class PetsAddHandlerPersisted implements DependentRelationshipAddHandler
	{
		public void addRelatedObject(MithraTransactionalObject relatedObject)
		{
			Pet item = (Pet) relatedObject;
			item.setPersonId(getPersonId());
			item.cascadeInsert();
		}
	}
}
