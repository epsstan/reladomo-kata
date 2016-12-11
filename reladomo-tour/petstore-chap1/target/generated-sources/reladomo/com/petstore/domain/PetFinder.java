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
public class PetFinder
{
	private static final Timestamp processingDateInfinity = TimestampPool.getInstance().getOrAddToCache(com.petstore.util.TimestampProvider.getInfinityDate(), true);
	private static final Timestamp processingDateDefault = TimestampPool.getInstance().getOrAddToCache(com.petstore.util.TimestampProvider.getInfinityDate(), true);
	private static final String IMPL_CLASS_NAME_WITH_SLASHES = "com/petstore/domain/Pet";
	private static final String BUSINESS_CLASS_NAME_WITH_DOTS = "com.petstore.domain.Pet";
	private static final FinderMethodMap finderMethodMap;
	private static boolean isFullCache;
	private static boolean isOffHeap;
	private static volatile MithraObjectPortal objectPortal = new UninitializedPortal("com.petstore.domain.Pet");
	private static final PetSingleFinder<Pet, Object, Pet> finder = new PetSingleFinder<Pet, Object, Pet>();
	private static ConstantStringSet[] constantStringSets = new ConstantStringSet[0];
	private static ConstantIntSet[] constantIntSets = new ConstantIntSet[0];
	private static ConstantShortSet[] constantShortSets = new ConstantShortSet[0];
	static
	{
		finderMethodMap = new FinderMethodMap(PetFinder.PetRelatedFinder.class);
		finderMethodMap.addNormalAttributeName("petName");
		finderMethodMap.addNormalAttributeName("personId");
		finderMethodMap.addNormalAttributeName("petAge");
		finderMethodMap.addNormalAttributeName("petTypeId");
		finderMethodMap.addNormalAttributeName("processingDateFrom");
		finderMethodMap.addNormalAttributeName("processingDateTo");
		finderMethodMap.addNormalAttributeName("processingDate");
		finderMethodMap.addRelationshipName("petType");
		finderMethodMap.addRelationshipName("owner");
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
			result[0] = PetFinder.petTypeId().constructEqualityMapper(PetTypeFinder.petTypeId());
			result[1] = PetTypeFinder.petTypeId().constructEqualityMapper(PetFinder.petTypeId());
			constantJoinPool = result;
		}

		return constantJoinPool;
	}

	public static SourceAttributeType getSourceAttributeType()
	{
		return null;
	}

	public static Pet findOne(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, false);
	}

	public static Pet findOneBypassCache(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, true);
	}

	public static PetList findMany(com.gs.fw.finder.Operation operation)
	{
		return (PetList) finder.findMany(operation);
	}

	public static PetList findManyBypassCache(com.gs.fw.finder.Operation operation)
	{
		return (PetList) finder.findManyBypassCache(operation);
	}

	private static Pet findOne(com.gs.fw.finder.Operation operation, boolean bypassCache)
	{
		List found = getMithraObjectPortal().find((Operation) operation, bypassCache);
		return (Pet) FinderUtils.findOne(found);
	}

	public static Pet findByPrimaryKey(String petName, Timestamp processingDate)
	{
		return finder.findByPrimaryKey(petName, processingDate);
	}

	private static final RelationshipHashStrategy forPrimaryKey = new PrimaryKeyRhs();
	private static final class PrimaryKeyRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			PetData _castedTargetData = (PetData) _targetData;
			if (_bean.getO1AsString().equals(_castedTargetData.getPetName()))
			{
				return PetFinder.processingDate().dataMatches(_castedTargetData, _asOfDate0);
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.hash(_bean.getO1AsString());
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.offHeapHash(_bean.getO1AsString());
		}
	}

	public static Pet zFindOneForRelationship(Operation operation)
	{
		List found = getMithraObjectPortal().findAsCachedQuery(operation, null, false, true, 0).getResult();
		return (Pet) FinderUtils.findOne(found);
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

	public static class PetRelatedFinder<ParentOwnerType, ReturnType, ReturnListType extends List, OwnerType> extends AbstractRelatedFinder<Pet, ParentOwnerType, ReturnType, ReturnListType, OwnerType>
	{
		private List<RelatedFinder> relationshipFinders;
		private List<RelatedFinder> dependentRelationshipFinders;
		private StringAttribute<ParentOwnerType> petName;
		private IntegerAttribute<ParentOwnerType> personId;
		private IntegerAttribute<ParentOwnerType> petAge;
		private IntegerAttribute<ParentOwnerType> petTypeId;
		private TimestampAttribute<ParentOwnerType> processingDateFrom;
		private TimestampAttribute<ParentOwnerType> processingDateTo;
		private AsOfAttribute<ParentOwnerType> processingDate;
		private PetPetTypeFinderSubclass<ParentOwnerType> petType;
		private PetOwnerFinderSubclass<ParentOwnerType> owner;
		private transient AsOfAttribute[] asOfAttributes;
		public synchronized AsOfAttribute[] getAsOfAttributes()
		{
			if (asOfAttributes == null)
			{
				asOfAttributes = new AsOfAttribute[1];
				asOfAttributes[0] = this.processingDate();
			}

			return this.asOfAttributes;
		}

		public PetRelatedFinder()
		{
			super();
		}

		public PetRelatedFinder(Mapper mapper)
		{
			super(mapper);
		}

		public String getFinderClassName()
		{
			return "com.petstore.domain.PetFinder";
		}

		public RelatedFinder getRelationshipFinderByName(String relationshipName)
		{
			return PetFinder.finderMethodMap.getRelationshipFinderByName(relationshipName, this);
		}

		public Attribute getAttributeByName(String attributeName)
		{
			return PetFinder.finderMethodMap.getAttributeByName(attributeName, this);
		}

		public com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
		{
			return PetFinder.finderMethodMap.getAttributeOrRelationshipSelectorFunction(attributeName, this);
		}

		public Attribute[] getPersistentAttributes()
		{
			Attribute[] attributes = new Attribute[6];
			attributes[0] = this.petName();
			attributes[1] = this.personId();
			attributes[2] = this.petAge();
			attributes[3] = this.petTypeId();
			attributes[4] = this.processingDateFrom();
			attributes[5] = this.processingDateTo();
			return attributes;
		}

		public List<RelatedFinder> getRelationshipFinders()
		{
			if (relationshipFinders == null)
			{
				List<RelatedFinder> relatedFinders = new ArrayList<RelatedFinder>(2);
				relatedFinders.add(this.petType());
				relatedFinders.add(this.owner());
				relationshipFinders = Collections.unmodifiableList(relatedFinders);
			}

			return relationshipFinders;
		}

		public List<RelatedFinder> getDependentRelationshipFinders()
		{
			if (dependentRelationshipFinders == null)
			{
				List<RelatedFinder> dependentRelatedFinders = new ArrayList<RelatedFinder>(0);
				dependentRelationshipFinders = Collections.unmodifiableList(dependentRelatedFinders);
			}

			return dependentRelationshipFinders;
		}

		public Pet findOne(com.gs.fw.finder.Operation operation)
		{
			return PetFinder.findOne(operation, false);
		}

		public Pet findOneBypassCache(com.gs.fw.finder.Operation operation)
		{
			return PetFinder.findOne(operation, true);
		}

		public MithraList<? extends Pet> findMany(com.gs.fw.finder.Operation operation)
		{
			return new PetList((Operation) operation);
		}

		public MithraList<? extends Pet> findManyBypassCache(com.gs.fw.finder.Operation operation)
		{
			PetList result = (PetList) this.findMany(operation);
			result.setBypassCache(true);
			return result;
		}

		public MithraList<? extends Pet> constructEmptyList()
		{
			return new PetList();
		}

		public int getSerialVersionId()
		{
			return -1798792474;
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

		public StringAttribute<ParentOwnerType> petName()
		{
			StringAttribute<ParentOwnerType> result = this.petName;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("PET_NAME","","petName",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1,16,true, false) :
					new MappedStringAttribute(PetFinder.petName(), this.mapper, this.zGetValueSelector());
				this.petName = result;
			}

			return result;
		}

		public IntegerAttribute<ParentOwnerType> personId()
		{
			IntegerAttribute<ParentOwnerType> result = this.personId;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("PERSON_ID","","personId",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(PetFinder.personId(), this.mapper, this.zGetValueSelector());
				result.zSetOwningRelationship("owner");
				result.zSetOwningReverseRelationship("com.petstore.domain", "Person", "pets");
				this.personId = result;
			}

			return result;
		}

		public IntegerAttribute<ParentOwnerType> petAge()
		{
			IntegerAttribute<ParentOwnerType> result = this.petAge;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("PET_AGE","","petAge",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(PetFinder.petAge(), this.mapper, this.zGetValueSelector());
				this.petAge = result;
			}

			return result;
		}

		public IntegerAttribute<ParentOwnerType> petTypeId()
		{
			IntegerAttribute<ParentOwnerType> result = this.petTypeId;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("PET_TYPE_ID","","petTypeId",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(PetFinder.petTypeId(), this.mapper, this.zGetValueSelector());
				result.zSetOwningRelationship("petType");
				this.petTypeId = result;
			}

			return result;
		}

		public TimestampAttribute<ParentOwnerType> processingDateFrom()
		{
			TimestampAttribute<ParentOwnerType> result = this.processingDateFrom;
			if (result == null)
			{
				result = mapper == null ? SingleColumnTimestampAttribute.generate("IN_Z","","processingDateFrom",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1,TimestampAttribute.CONVERT_NONE,false,false,PetFinder.processingDateInfinity, (byte) 0, false) :
					new MappedTimestampAttribute(PetFinder.processingDateFrom(), this.mapper, this.zGetValueSelector());
				this.processingDateFrom = result;
			}

			return result;
		}

		public TimestampAttribute<ParentOwnerType> processingDateTo()
		{
			TimestampAttribute<ParentOwnerType> result = this.processingDateTo;
			if (result == null)
			{
				result = mapper == null ? SingleColumnTimestampAttribute.generate("OUT_Z","","processingDateTo",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1,TimestampAttribute.CONVERT_NONE,false,true,PetFinder.processingDateInfinity, (byte) 0, false) :
					new MappedTimestampAttribute(PetFinder.processingDateTo(), this.mapper, this.zGetValueSelector());
				this.processingDateTo = result;
			}

			return result;
		}

		public AsOfAttribute<ParentOwnerType> processingDate()
		{
			AsOfAttribute<ParentOwnerType> result = this.processingDate;
			if (result == null)
			{
				result = mapper == null ? AsOfAttribute.generate("processingDate",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,this.processingDateFrom(),this.processingDateTo(),PetFinder.processingDateInfinity,false,false,PetFinder.processingDateDefault,true,false) :
					new MappedAsOfAttribute(PetFinder.processingDate(), this.mapper, this.zGetValueSelector());
				this.processingDate = result;
			}

			return result;
		}

		public PetTypeFinder.PetTypeSingleFinderForRelatedClasses<ParentOwnerType, PetType, Pet> petType()
		{
			PetPetTypeFinderSubclass<ParentOwnerType> result = this.petType;
			if (result == null)
			{
				Mapper newMapper = combineWithMapperIfExists(PetFinder.zGetPetPetTypeReverseMapper());
				newMapper.setToMany(false);
				result = new PetPetTypeFinderSubclass<ParentOwnerType>(newMapper , this.zGetValueSelector() );
				this.petType = result;
			}

			return result;
		}

		public PersonFinder.PersonSingleFinderForRelatedClasses<ParentOwnerType, Person, Pet> owner()
		{
			PetOwnerFinderSubclass<ParentOwnerType> result = this.owner;
			if (result == null)
			{
				Mapper newMapper = combineWithMapperIfExists(PersonFinder.zGetPersonPetsMapper());
				newMapper.setToMany(false);
				result = new PetOwnerFinderSubclass<ParentOwnerType>(newMapper , this.zGetValueSelector() );
				this.owner = result;
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
			return PetFinder.getPrimaryKeyAttributes();
		}

		public VersionAttribute getVersionAttribute()
		{
			return null;
		}

		public MithraObjectPortal getMithraObjectPortal()
		{
			return PetFinder.getMithraObjectPortal();
		}
	}

	public static class PetCollectionFinder<ParentOwnerType, ReturnType extends List, OwnerType> extends PetRelatedFinder<ParentOwnerType, ReturnType, PetList, OwnerType>
	{
		public PetCollectionFinder(Mapper mapper)
		{
			super(mapper);
		}
	}

	public static abstract class PetCollectionFinderForRelatedClasses<ParentOwnerType, ReturnType extends List, OwnerType>
	extends PetCollectionFinder<ParentOwnerType, ReturnType, OwnerType>
	implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public PetCollectionFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	public static class PetSingleFinder<ParentOwnerType, ReturnType, OwnerType> extends PetRelatedFinder<ParentOwnerType, ReturnType, PetList, OwnerType>
	implements ToOneFinder
	{
		public PetSingleFinder(Mapper mapper)
		{
			super(mapper);
		}

		public PetSingleFinder()
		{
			super(null);
		}

		public Operation eq(Pet other)
		{
			return this.petName().eq(other.getPetName())
			;
		}
		// this implementation uses private API. Do NOT copy to application code. Application code must use normal operations for lookups.
		public Pet findByPrimaryKey(String petName, Timestamp processingDate)
		{
			Pet _result = null;
			Operation _op = null;
			Object _related = null;
			if (petName != null)
			{
				I3O3L3 _bean = I3O3L3.POOL.getOrConstruct();
				_bean.setO1(petName);
				MithraObjectPortal _portal = this.getMithraObjectPortal();
				_related = _portal.getAsOneFromCacheForFind(_bean, _bean, forPrimaryKey, processingDate, null);
				_bean.release();
			}

			if (!(_related instanceof NulledRelation)) _result = (Pet) _related;
			if (_related == null)
			{
				_op = this.petName().eq(petName).and(this.processingDate().eq(processingDate));
			}

			if (_op != null)
			{
				_result = this.findOne(_op);
			}

			return _result;
		}
	}

	public static abstract class PetSingleFinderForRelatedClasses<ParentOwnerType, ReturnType, OwnerType> extends PetSingleFinder<ParentOwnerType, ReturnType, OwnerType> implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public PetSingleFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	private static Mapper petTypeReverseMapper = null;
	public static Mapper zGetPetPetTypeReverseMapper()
	{
		if (petTypeReverseMapper == null)
		{
			petTypeReverseMapper = zConstructPetPetTypeReverseMapper();
		}

		return petTypeReverseMapper;
	}

	private static Mapper petTypeMapper = null;
	public static Mapper zGetPetPetTypeMapper()
	{
		if (petTypeMapper == null)
		{
			petTypeMapper = zConstructPetPetTypeMapper();
		}

		return petTypeMapper;
	}

	private static Mapper petTypePureReverseMapper = null;
	public static Mapper zGetPetPetTypePureReverseMapper()
	{
		if (petTypePureReverseMapper == null)
		{
			petTypePureReverseMapper = zConstructPetPetTypePureReverseMapper();
		}

		return petTypePureReverseMapper;
	}

	private static Mapper zConstructPetPetTypePureReverseMapper()
	{
		Mapper petTypeMapper = PetFinder.zGetConstantJoin(0);
		petTypeMapper.setName("petType");
		return petTypeMapper;
	}

	private static Mapper zConstructPetPetTypeReverseMapper()
	{
		Mapper petTypeMapper = PetFinder.zGetConstantJoin(0);
		petTypeMapper.setName("petType");
		return petTypeMapper;
	}

	private static Mapper zConstructPetPetTypeMapper()
	{
		Mapper petTypeMapper = PetFinder.zGetConstantJoin(1);
		petTypeMapper.setName("petType_Reverse");
		return petTypeMapper;
	}

	/** maps to PET.PET_NAME **/
	public static StringAttribute<Pet> petName()
	{
		return finder.petName();
	}

	/** maps to PET.PERSON_ID **/
	public static IntegerAttribute<Pet> personId()
	{
		return finder.personId();
	}

	/** maps to PET.PET_AGE **/
	public static IntegerAttribute<Pet> petAge()
	{
		return finder.petAge();
	}

	/** maps to PET.PET_TYPE_ID **/
	public static IntegerAttribute<Pet> petTypeId()
	{
		return finder.petTypeId();
	}

	/** maps to PET.IN_Z **/
	public static TimestampAttribute<Pet> processingDateFrom()
	{
		return finder.processingDateFrom();
	}

	/** maps to PET.OUT_Z **/
	public static TimestampAttribute<Pet> processingDateTo()
	{
		return finder.processingDateTo();
	}

	public static AsOfAttribute<Pet> processingDate()
	{
		return finder.processingDate();
	}

	public static PetTypeFinder.PetTypeSingleFinderForRelatedClasses<Pet, PetType, Pet> petType()
	{
		return finder.petType();
	}

	public static class PetPetTypeFinderSubclass<ParentOwnerType> extends PetTypeFinder.PetTypeSingleFinderForRelatedClasses<ParentOwnerType, PetType, Pet>
	{
		public PetPetTypeFinderSubclass(Mapper mapper, NormalAndListValueSelector parentSelector )
		{
			super(mapper);
			this._parentSelector = (AbstractRelatedFinder) parentSelector;
			this._orderBy = null;
			this._type = SIMPLE_TO_ONE;
			this._name = "petType";
		}

		public DeepRelationshipAttribute copy()
		{
			return new PetPetTypeFinderSubclass(zGetMapper(), (NormalAndListValueSelector) this._parentSelector
			);
		}

		protected PetType plainValueOf(Pet _obj)
		{
			return _obj.getPetType();
		}

		protected PetTypeList plainListValueOf(Object _obj)
		{
			return ((PetList)_obj).getPetTypes();
		}
	}

	public static PersonFinder.PersonSingleFinderForRelatedClasses<Pet, Person, Pet> owner()
	{
		return finder.owner();
	}

	public static class PetOwnerFinderSubclass<ParentOwnerType> extends PersonFinder.PersonSingleFinderForRelatedClasses<ParentOwnerType, Person, Pet>
	{
		public PetOwnerFinderSubclass(Mapper mapper, NormalAndListValueSelector parentSelector )
		{
			super(mapper);
			this._parentSelector = (AbstractRelatedFinder) parentSelector;
			this._orderBy = null;
			this._type = SIMPLE_TO_ONE;
			this._name = "owner";
		}

		public DeepRelationshipAttribute copy()
		{
			return new PetOwnerFinderSubclass(zGetMapper(), (NormalAndListValueSelector) this._parentSelector
			);
		}

		protected Person plainValueOf(Pet _obj)
		{
			return _obj.getOwner();
		}

		protected PersonList plainListValueOf(Object _obj)
		{
			return ((PetList)_obj).getOwners();
		}
	}

	public static Operation eq(Pet other)
	{
		return finder.eq(other);
	}

	public static Operation all()
	{
		return new All(petName());
	}

	public static PetSingleFinder<Pet, Object, Pet> getFinderInstance()
	{
		return finder;
	}

	public static Attribute[] getPrimaryKeyAttributes()
	{
		return new Attribute[] 
		{
			petName()
		}

		;
	}

	public static Attribute[] getImmutableAttributes()
	{
		return new Attribute[] 
		{
			petName()
			, petName()
		}

		;
	}

	public static AsOfAttribute[] getAsOfAttributes()
	{
		return new AsOfAttribute[] 
		{
			processingDate()
		}

		;
	}

	protected static void initializeIndicies(Cache cache)
	{
		cache.addIndex("0 Index", new Attribute[] 
		{
			personId()
		}

		);
		cache.addIndex("1 Index", new Attribute[] 
		{
			petTypeId()
		}

		);
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

		portal.setParentFinders(new RelatedFinder[] 
		{
			PersonFinder.getFinderInstance(),PetTypeFinder.getFinderInstance(),
		}

		);
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
				new RemoteMithraObjectPersister(config.getRemoteMithraService(), getFinderInstance(), true));
		}
		else
		{
			portal = new MithraReadOnlyPortal(objectFactory, cache, getFinderInstance(),
				config.getRelationshipCacheSize(), config.getMinQueriesToKeep(),
				null, null,
				null, 0,
				new RemoteMithraObjectPersister(config.getRemoteMithraService(), getFinderInstance(), true));
		}

		portal.setParentFinders(new RelatedFinder[] 
		{
			PersonFinder.getFinderInstance(),PetTypeFinder.getFinderInstance(),
		}

		);
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
		objectPortal = new UninitializedPortal("com.petstore.domain.Pet");
		isFullCache = false;
		isOffHeap = false;
	}

	public static void setTransactionModeFullTransactionParticipation(MithraTransaction tx)
	{
		tx.setTxParticipationMode(objectPortal, FullTransactionalParticipationMode.getInstance());
	}

	public static void setTransactionModeReadCacheWithOptimisticLocking(MithraTransaction tx)
	{
		tx.setTxParticipationMode(objectPortal, ReadCacheWithOptimisticLockingTxParticipationMode.getInstance());
	}

	public static void registerForNotification(MithraApplicationClassLevelNotificationListener listener)
	{
		getMithraObjectPortal().registerForApplicationClassLevelNotification(listener);
	}
}
