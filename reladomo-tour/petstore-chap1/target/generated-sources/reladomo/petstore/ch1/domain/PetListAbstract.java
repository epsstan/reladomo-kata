package petstore.ch1.domain;
import java.util.*;
import com.gs.fw.common.mithra.MithraList;
import com.gs.fw.common.mithra.MithraManager;
import com.gs.fw.common.mithra.MithraManagerProvider;
import com.gs.fw.common.mithra.finder.*;
import com.gs.fw.common.mithra.list.*;
import com.gs.fw.finder.OrderBy;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.list.mutable.ListAdapter;
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
/**
* This file was automatically generated using Mithra 16.1.0. Please do not modify it.
* Add custom logic to its subclass instead.
*/
// Generated from templates/transactional/ListAbstract.jsp
public class PetListAbstract extends DelegatingList<Pet> implements MithraTransactionalList<Pet>
{
	public PetListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public PetListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public PetListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public PetListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public Pet[] elements()
	{
		Pet[] result = new Pet[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public PetList intersection(PetList another)
	{
		return (PetList)super.intersection(another);
	}

	public Pet getPetAt(int index)
	{
		return (Pet)this.get(index);
	}

	/**
	* Relationship Expression:<pre>
	this.petTypeId = PetType.petTypeId</pre>
	* @return The pet type
	*/
	public PetTypeList getPetTypes()
	{
		return (PetTypeList) this.getDelegated().resolveRelationship(this, PetFinder.petType());
	}

	/**
	* Relationship Expression:<pre>
	Person.personId = this.personId</pre>
	* @see Person#getPets() reverse relationship Person.getPets()
	* @return The owner
	*/
	public PersonList getOwners()
	{
		return (PersonList) this.getDelegated().resolveRelationship(this, PetFinder.owner());
	}

	public void zSetParentContainerowner(PersonAbstract parent)
	{
		for (int i = 0; i < this.size(); i++)
		{
			Pet item = this.getPetAt(i);
			item.zSetParentContainerowner(parent);
		}
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return PetFinder.getMithraObjectPortal();
	}

	public PetList getNonPersistentCopy()
	{
		PetList result = new PetList();
		zCopyNonPersistentInto(result);
		return result;
	}

	public MithraTransactionalList getNonPersistentGenericCopy()
	{
		return this.getNonPersistentCopy();
	}

	/**
	* Return a view of this list that implements GS Collections MutableList API.
	* Since the returned list will be operation-based, it is effectively read-only,
	* so mutating methods will throw a RuntimeException.
	* (Implemented by a light-weight adapter, not a copy)
	*/
	public MutableList<Pet> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public PetList getDetachedCopy()
	{
		PetList result = new PetList();
		zDetachInto(result);
		return result;
	}

	public void zMakeDetached(Operation op, Object previousDetachedList)
	{
		super.zMakeDetached(op, previousDetachedList);
	}

	public void setPersonId(int newValue)
	{
		zSetInteger(PetFinder.personId(), newValue);
	}

	public void setPetAge(int newValue)
	{
		zSetInteger(PetFinder.petAge(), newValue);
	}

	public void setPetName(String newValue)
	{
		zSetString(PetFinder.petName(), newValue);
	}

	public void setPetTypeId(int newValue)
	{
		zSetInteger(PetFinder.petTypeId(), newValue);
	}
}
