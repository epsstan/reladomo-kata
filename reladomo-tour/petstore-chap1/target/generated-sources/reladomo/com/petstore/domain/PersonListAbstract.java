package com.petstore.domain;
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
public class PersonListAbstract extends DelegatingList<Person> implements MithraTransactionalList<Person>
{
	public PersonListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public PersonListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public PersonListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public PersonListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public Person[] elements()
	{
		Person[] result = new Person[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public PersonList intersection(PersonList another)
	{
		return (PersonList)super.intersection(another);
	}

	public Person getPersonAt(int index)
	{
		return (Person)this.get(index);
	}

	/**
	* Relationship Expression:<pre>
	this.personId = Pet.personId</pre>
	* @see Pet#getOwner() reverse relationship Pet.getOwner()
	* @return pets
	*/
	public PetList getPets()
	{
		return (PetList) this.getDelegated().resolveRelationship(this, PersonFinder.pets());
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return PersonFinder.getMithraObjectPortal();
	}

	public PersonList getNonPersistentCopy()
	{
		PersonList result = new PersonList();
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
	public MutableList<Person> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public PersonList getDetachedCopy()
	{
		PersonList result = new PersonList();
		zDetachInto(result);
		return result;
	}

	public void zMakeDetached(Operation op, Object previousDetachedList)
	{
		super.zMakeDetached(op, previousDetachedList);
	}

	protected void generateAndSetPrimaryKeys()
	{
		zGenerateAndSetPrimaryKeysForSingleSource((SequenceAttribute)PersonFinder.personId(), false, "Person", "com.petstore.util.ObjectSequenceObjectFactory", null);
	}

	public void setFirstName(String newValue)
	{
		zSetString(PersonFinder.firstName(), newValue);
	}

	public void setLastName(String newValue)
	{
		zSetString(PersonFinder.lastName(), newValue);
	}

	public void setPersonId(int newValue)
	{
		zSetInteger(PersonFinder.personId(), newValue);
	}

	protected void zCascadeDeleteRelationships()
	{
	}
}
