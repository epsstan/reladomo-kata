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
import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.attribute.*;
import com.gs.fw.common.mithra.attribute.calculator.procedure.ObjectProcedure;
import com.gs.fw.common.mithra.behavior.txparticipation.*;
import com.gs.fw.common.mithra.cache.Cache;
import com.gs.fw.common.mithra.cache.bean.*;
import com.gs.fw.common.mithra.extractor.*;
import com.gs.fw.common.mithra.finder.*;
import com.gs.fw.common.mithra.finder.booleanop.*;
import com.gs.fw.common.mithra.finder.integer.*;
import com.gs.fw.common.mithra.finder.longop.*;
import com.gs.fw.common.mithra.finder.orderby.OrderBy;
import com.gs.fw.common.mithra.finder.string.*;
import com.gs.fw.common.mithra.extractor.NormalAndListValueSelector;
import com.gs.fw.common.mithra.list.NulledRelation;
import com.gs.fw.common.mithra.querycache.CachedQuery;
import com.gs.fw.common.mithra.querycache.QueryCache;
import com.gs.fw.common.mithra.portal.*;
import com.gs.fw.common.mithra.remote.*;
import com.gs.fw.common.mithra.transaction.MithraObjectPersister;
import com.gs.fw.common.mithra.util.TimestampPool;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import java.io.Serializable;
/**
* This file was automatically generated using Mithra 16.1.0. Please do not modify it.
* Add custom logic to its subclass instead.
*/
public class PersonFinder
{
	private static final String IMPL_CLASS_NAME_WITH_SLASHES = "com/petstore/domain/Person";
	private static final String BUSINESS_CLASS_NAME_WITH_DOTS = "com.petstore.domain.Person";
	private static final FinderMethodMap finderMethodMap;
	private static boolean isFullCache;
	private static boolean isOffHeap;
	private static volatile MithraObjectPortal objectPortal = new UninitializedPortal("com.petstore.domain.Person");
	private static final PersonSingleFinder<Person, Object, Person> finder = new PersonSingleFinder<Person, Object, Person>();
	private static ConstantStringSet[] constantStringSets = new ConstantStringSet[0];
	private static ConstantIntSet[] constantIntSets = new ConstantIntSet[0];
	private static ConstantShortSet[] constantShortSets = new ConstantShortSet[0];
	static
	{
		finderMethodMap = new FinderMethodMap(PersonFinder.PersonRelatedFinder.class);
		finderMethodMap.addNormalAttributeName("personId");
		finderMethodMap.addNormalAttributeName("firstName");
		finderMethodMap.addNormalAttributeName("lastName");
		finderMethodMap.addRelationshipName("pets");
	}

	public static Attribute[] allPersistentAttributes()
	{
		return finder.getPersistentAttributes();
	}

	public static List<RelatedFinder> allRelatedFinders()
	{
		return finder.getRelationshipFinders();
	}

	public static List<RelatedFinder> allDependentRelatedFinders()
	{
		return finder.getDependentRelationshipFinders();
	}

	public static ConstantStringSet zGetConstantStringSet(int index)
	{
		return constantStringSets[index];
	}

	public static ConstantIntSet zGetConstantIntSet(int index)
	{
		return constantIntSets[index];
	}

	public static ConstantShortSet zGetConstantShortSet(int index)
	{
		return constantShortSets[index];
	}

	public static Mapper zGetConstantJoin(int index)
	{
		return getConstantJoinPool()[index];
	}

	private static Mapper[] constantJoinPool;
	private static Mapper[] getConstantJoinPool()
	{
		if (constantJoinPool == null)
		{
			Mapper[] result = new Mapper[2];
			result[0] = PersonFinder.personId().constructEqualityMapper(PetFinder.personId());
			result[1] = PetFinder.personId().constructEqualityMapper(PersonFinder.personId());
			constantJoinPool = result;
		}

		return constantJoinPool;
	}

	public static SourceAttributeType getSourceAttributeType()
	{
		return null;
	}

	public static Person findOne(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, false);
	}

	public static Person findOneBypassCache(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, true);
	}

	public static PersonList findMany(com.gs.fw.finder.Operation operation)
	{
		return (PersonList) finder.findMany(operation);
	}

	public static PersonList findManyBypassCache(com.gs.fw.finder.Operation operation)
	{
		return (PersonList) finder.findManyBypassCache(operation);
	}

	private static Person findOne(com.gs.fw.finder.Operation operation, boolean bypassCache)
	{
		List found = getMithraObjectPortal().find((Operation) operation, bypassCache);
		return (Person) FinderUtils.findOne(found);
	}

	public static Person findByPrimaryKey(int personId)
	{
		return finder.findByPrimaryKey(personId);
	}

	private static final RelationshipHashStrategy forPrimaryKey = new PrimaryKeyRhs();
	private static final class PrimaryKeyRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			PersonData _castedTargetData = (PersonData) _targetData;
			if (_bean.getI1AsInteger() == _castedTargetData.getPersonId())
			{
				return true;
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.hash(_bean.getI1AsInteger());
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.hash(_bean.getI1AsInteger());
		}
	}

	public static Person zFindOneForRelationship(Operation operation)
	{
		List found = getMithraObjectPortal().findAsCachedQuery(operation, null, false, true, 0).getResult();
		return (Person) FinderUtils.findOne(found);
	}

	public static MithraObjectPortal getMithraObjectPortal()
	{
		return objectPortal.getInitializedPortal();
	}

	public static void clearQueryCache()
	{
		objectPortal.clearQueryCache();
	}

	public static void reloadCache()
	{
		objectPortal.reloadCache();
	}

	public static class PersonRelatedFinder<ParentOwnerType, ReturnType, ReturnListType extends List, OwnerType> extends AbstractRelatedFinder<Person, ParentOwnerType, ReturnType, ReturnListType, OwnerType>
	{
		private List<RelatedFinder> relationshipFinders;
		private List<RelatedFinder> dependentRelationshipFinders;
		private IntegerAttribute<ParentOwnerType> personId;
		private StringAttribute<ParentOwnerType> firstName;
		private StringAttribute<ParentOwnerType> lastName;
		private PersonPetsFinderSubclass<ParentOwnerType> pets;
		public PersonRelatedFinder()
		{
			super();
		}

		public PersonRelatedFinder(Mapper mapper)
		{
			super(mapper);
		}

		public String getFinderClassName()
		{
			return "com.petstore.domain.PersonFinder";
		}

		public RelatedFinder getRelationshipFinderByName(String relationshipName)
		{
			return PersonFinder.finderMethodMap.getRelationshipFinderByName(relationshipName, this);
		}

		public Attribute getAttributeByName(String attributeName)
		{
			return PersonFinder.finderMethodMap.getAttributeByName(attributeName, this);
		}

		public com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
		{
			return PersonFinder.finderMethodMap.getAttributeOrRelationshipSelectorFunction(attributeName, this);
		}

		public Attribute[] getPersistentAttributes()
		{
			Attribute[] attributes = new Attribute[3];
			attributes[0] = this.personId();
			attributes[1] = this.firstName();
			attributes[2] = this.lastName();
			return attributes;
		}

		public List<RelatedFinder> getRelationshipFinders()
		{
			if (relationshipFinders == null)
			{
				List<RelatedFinder> relatedFinders = new ArrayList<RelatedFinder>(1);
				relatedFinders.add(this.pets());
				relationshipFinders = Collections.unmodifiableList(relatedFinders);
			}

			return relationshipFinders;
		}

		public List<RelatedFinder> getDependentRelationshipFinders()
		{
			if (dependentRelationshipFinders == null)
			{
				List<RelatedFinder> dependentRelatedFinders = new ArrayList<RelatedFinder>(1);
				dependentRelatedFinders.add(this.pets());
				dependentRelationshipFinders = Collections.unmodifiableList(dependentRelatedFinders);
			}

			return dependentRelationshipFinders;
		}

		public Person findOne(com.gs.fw.finder.Operation operation)
		{
			return PersonFinder.findOne(operation, false);
		}

		public Person findOneBypassCache(com.gs.fw.finder.Operation operation)
		{
			return PersonFinder.findOne(operation, true);
		}

		public MithraList<? extends Person> findMany(com.gs.fw.finder.Operation operation)
		{
			return new PersonList((Operation) operation);
		}

		public MithraList<? extends Person> findManyBypassCache(com.gs.fw.finder.Operation operation)
		{
			PersonList result = (PersonList) this.findMany(operation);
			result.setBypassCache(true);
			return result;
		}

		public MithraList<? extends Person> constructEmptyList()
		{
			return new PersonList();
		}

		public int getSerialVersionId()
		{
			return 1303806557;
		}

		public boolean isPure()
		{
			return false;
		}

		public boolean isTemporary()
		{
			return false;
		}

		public int getHierarchyDepth()
		{
			return 0;
		}

		public IntegerAttribute<ParentOwnerType> personId()
		{
			IntegerAttribute<ParentOwnerType> result = this.personId;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("PERSON_ID","","personId",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,true, false) :
					new MappedIntegerAttribute(PersonFinder.personId(), this.mapper, this.zGetValueSelector());
				this.personId = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> firstName()
		{
			StringAttribute<ParentOwnerType> result = this.firstName;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("FIRST_NAME","","firstName",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,16,true, false) :
					new MappedStringAttribute(PersonFinder.firstName(), this.mapper, this.zGetValueSelector());
				this.firstName = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> lastName()
		{
			StringAttribute<ParentOwnerType> result = this.lastName;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("LAST_NAME","","lastName",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,24,true, false) :
					new MappedStringAttribute(PersonFinder.lastName(), this.mapper, this.zGetValueSelector());
				this.lastName = result;
			}

			return result;
		}

		public PetFinder.PetCollectionFinderForRelatedClasses<ParentOwnerType, PetList, Person> pets()
		{
			PersonPetsFinderSubclass<ParentOwnerType> result = this.pets;
			if (result == null)
			{
				Mapper newMapper = combineWithMapperIfExists(PersonFinder.zGetPersonPetsReverseMapper());
				newMapper.setToMany(true);
				result = new PersonPetsFinderSubclass<ParentOwnerType>(newMapper , this.zGetValueSelector() );
				this.pets = result;
			}

			return result;
		}

		public Attribute getSourceAttribute()
		{
			return null;
		}

		private Mapper combineWithMapperIfExists(Mapper newMapper)
		{
			if (this.mapper != null)
			{
				return new LinkedMapper(this.mapper, newMapper);
			}

			return newMapper;
		}

		public Attribute[] getPrimaryKeyAttributes()
		{
			return PersonFinder.getPrimaryKeyAttributes();
		}

		public VersionAttribute getVersionAttribute()
		{
			return null;
		}

		public MithraObjectPortal getMithraObjectPortal()
		{
			return PersonFinder.getMithraObjectPortal();
		}
	}

	public static class PersonCollectionFinder<ParentOwnerType, ReturnType extends List, OwnerType> extends PersonRelatedFinder<ParentOwnerType, ReturnType, PersonList, OwnerType>
	{
		public PersonCollectionFinder(Mapper mapper)
		{
			super(mapper);
		}
	}

	public static abstract class PersonCollectionFinderForRelatedClasses<ParentOwnerType, ReturnType extends List, OwnerType>
	extends PersonCollectionFinder<ParentOwnerType, ReturnType, OwnerType>
	implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public PersonCollectionFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	public static class PersonSingleFinder<ParentOwnerType, ReturnType, OwnerType> extends PersonRelatedFinder<ParentOwnerType, ReturnType, PersonList, OwnerType>
	implements ToOneFinder
	{
		public PersonSingleFinder(Mapper mapper)
		{
			super(mapper);
		}

		public PersonSingleFinder()
		{
			super(null);
		}

		public Operation eq(Person other)
		{
			return this.personId().eq(other.getPersonId())
			;
		}
		// this implementation uses private API. Do NOT copy to application code. Application code must use normal operations for lookups.
		public Person findByPrimaryKey(int personId)
		{
			Person _result = null;
			Operation _op = null;
			Object _related = null;
			{
				I3O3L3 _bean = I3O3L3.POOL.getOrConstruct();
				_bean.setI1AsInteger(personId);
				MithraObjectPortal _portal = this.getMithraObjectPortal();
				_related = _portal.getAsOneFromCacheForFind(_bean, _bean, forPrimaryKey, null, null);
				_bean.release();
			}

			if (!(_related instanceof NulledRelation)) _result = (Person) _related;
			if (_related == null)
			{
				_op = this.personId().eq(personId);
			}

			if (_op != null)
			{
				_result = this.findOne(_op);
			}

			return _result;
		}
	}

	public static abstract class PersonSingleFinderForRelatedClasses<ParentOwnerType, ReturnType, OwnerType> extends PersonSingleFinder<ParentOwnerType, ReturnType, OwnerType> implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public PersonSingleFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	private static Mapper petsReverseMapper = null;
	public static Mapper zGetPersonPetsReverseMapper()
	{
		if (petsReverseMapper == null)
		{
			petsReverseMapper = zConstructPersonPetsReverseMapper();
		}

		return petsReverseMapper;
	}

	private static Mapper petsMapper = null;
	public static Mapper zGetPersonPetsMapper()
	{
		if (petsMapper == null)
		{
			petsMapper = zConstructPersonPetsMapper();
		}

		return petsMapper;
	}

	private static Mapper petsPureReverseMapper = null;
	public static Mapper zGetPersonPetsPureReverseMapper()
	{
		if (petsPureReverseMapper == null)
		{
			petsPureReverseMapper = zConstructPersonPetsPureReverseMapper();
		}

		return petsPureReverseMapper;
	}

	private static Mapper zConstructPersonPetsPureReverseMapper()
	{
		Mapper petsMapper = PersonFinder.zGetConstantJoin(0);
		petsMapper.setName("pets");
		return petsMapper;
	}

	private static Mapper zConstructPersonPetsReverseMapper()
	{
		Mapper petsMapper = PersonFinder.zGetConstantJoin(0);
		petsMapper.setName("pets");
		return petsMapper;
	}

	private static Mapper zConstructPersonPetsMapper()
	{
		Mapper petsMapper = PersonFinder.zGetConstantJoin(1);
		petsMapper.setName("owner");
		return petsMapper;
	}

	/** maps to PERSON.PERSON_ID **/
	public static IntegerAttribute<Person> personId()
	{
		return finder.personId();
	}

	/** maps to PERSON.FIRST_NAME **/
	public static StringAttribute<Person> firstName()
	{
		return finder.firstName();
	}

	/** maps to PERSON.LAST_NAME **/
	public static StringAttribute<Person> lastName()
	{
		return finder.lastName();
	}

	public static PetFinder.PetCollectionFinderForRelatedClasses<Person, PetList, Person> pets()
	{
		return finder.pets();
	}

	public static class PersonPetsFinderSubclass<ParentOwnerType> extends PetFinder.PetCollectionFinderForRelatedClasses<ParentOwnerType, PetList, Person>
	{
		public PersonPetsFinderSubclass(Mapper mapper, NormalAndListValueSelector parentSelector )
		{
			super(mapper);
			this._parentSelector = (AbstractRelatedFinder) parentSelector;
			this._orderBy = null;
			this._type = SIMPLE_TO_MANY;
			this._name = "pets";
		}

		public DeepRelationshipAttribute copy()
		{
			return new PersonPetsFinderSubclass(zGetMapper(), (NormalAndListValueSelector) this._parentSelector
			);
		}

		public boolean isModifiedSinceDetachment(MithraTransactionalObject _obj)
		{
			return ((Person) _obj).isPetsModifiedSinceDetachment();
		}

		protected PetList plainValueOf(Person _obj)
		{
			return _obj.getPets();
		}

		protected PetList plainListValueOf(Object _obj)
		{
			return ((PersonList)_obj).getPets();
		}
	}

	public static Operation eq(Person other)
	{
		return finder.eq(other);
	}

	public static Operation all()
	{
		return new All(personId());
	}

	public static PersonSingleFinder<Person, Object, Person> getFinderInstance()
	{
		return finder;
	}

	public static Attribute[] getPrimaryKeyAttributes()
	{
		return new Attribute[] 
		{
			personId()
		}

		;
	}

	public static Attribute[] getImmutableAttributes()
	{
		return new Attribute[] 
		{
			personId()
			, personId()
		}

		;
	}

	public static AsOfAttribute[] getAsOfAttributes()
	{
		return null;
	}

	protected static void initializeIndicies(Cache cache)
	{
	}

	protected static void initializePortal(MithraObjectDeserializer objectFactory, Cache cache,
		MithraConfigurationManager.Config config)
	{
		initializeIndicies(cache);
		isFullCache = cache.isFullCache();
		isOffHeap = cache.isOffHeap();
		MithraObjectPortal portal;
		if (config.isParticipatingInTx())
		{
			portal = new MithraTransactionalPortal(objectFactory, cache, getFinderInstance(),
				config.getRelationshipCacheSize(), config.getMinQueriesToKeep(), null,
				null, null, 0,
				(MithraObjectPersister) objectFactory);
		}
		else
		{
			portal = new MithraReadOnlyPortal(objectFactory, cache, getFinderInstance(),
				config.getRelationshipCacheSize(), config.getMinQueriesToKeep(), null,
				null, null, 0,
				(MithraObjectPersister) objectFactory);
		}

		config.initializePortal(portal);
		objectPortal.destroy();
		objectPortal = portal;
	}

	protected static void initializeClientPortal(MithraObjectDeserializer objectFactory, Cache cache,
		MithraConfigurationManager.Config config)
	{
		initializeIndicies(cache);
		isFullCache = cache.isFullCache();
		isOffHeap = cache.isOffHeap();
		MithraObjectPortal portal;
		if (config.isParticipatingInTx())
		{
			portal = new MithraTransactionalPortal(objectFactory, cache, getFinderInstance(),
				config.getRelationshipCacheSize(), config.getMinQueriesToKeep(),
				null, null,
				null, 0,
				new RemoteMithraObjectPersister(config.getRemoteMithraService(), getFinderInstance(), false));
		}
		else
		{
			portal = new MithraReadOnlyPortal(objectFactory, cache, getFinderInstance(),
				config.getRelationshipCacheSize(), config.getMinQueriesToKeep(),
				null, null,
				null, 0,
				new RemoteMithraObjectPersister(config.getRemoteMithraService(), getFinderInstance(), false));
		}

		config.initializePortal(portal);
		objectPortal.destroy();
		objectPortal = portal;
	}

	public static boolean isFullCache()
	{
		return isFullCache;
	}

	public static boolean isOffHeap()
	{
		return isOffHeap;
	}

	public static Attribute getAttributeByName(String attributeName)
	{
		return finder.getAttributeByName(attributeName);
	}

	public static com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
	{
		return finder.getAttributeOrRelationshipSelector(attributeName);
	}

	public static RelatedFinder getRelatedFinderByName(String relationshipName)
	{
		return finder.getRelationshipFinderByName(relationshipName);
	}

	public static DoubleAttribute[] zGetDoubleAttributes()
	{
		DoubleAttribute[] result = new DoubleAttribute[0];
		return result;
	}

	public static BigDecimalAttribute[] zGetBigDecimalAttributes()
	{
		BigDecimalAttribute[] result = new BigDecimalAttribute[0];
		return result;
	}

	public static void zResetPortal()
	{
		objectPortal.destroy();
		objectPortal = new UninitializedPortal("com.petstore.domain.Person");
		isFullCache = false;
		isOffHeap = false;
	}

	public static void setTransactionModeFullTransactionParticipation(MithraTransaction tx)
	{
		tx.setTxParticipationMode(objectPortal, FullTransactionalParticipationMode.getInstance());
	}

	public static void setTransactionModeReadCacheUpdateCausesRefreshAndLock(MithraTransaction tx)
	{
		tx.setTxParticipationMode(objectPortal, ReadCacheUpdateCausesRefreshAndLockTxParticipationMode.getInstance());
	}

	public static void registerForNotification(MithraApplicationClassLevelNotificationListener listener)
	{
		getMithraObjectPortal().registerForApplicationClassLevelNotification(listener);
	}
}
