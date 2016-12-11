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
public class PetTypeListAbstract extends DelegatingList<PetType> implements MithraTransactionalList<PetType>
{
	public PetTypeListAbstract()
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this);
	}

	public PetTypeListAbstract(int initialSize)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, initialSize);
	}

	public PetTypeListAbstract(Collection c)
	{
		super();
		this.setDelegated(AbstractTransactionalNonOperationBasedList.DEFAULT);
		AbstractTransactionalNonOperationBasedList.DEFAULT.init(this, c);
	}

	public PetTypeListAbstract(com.gs.fw.finder.Operation operation)
	{
		super(operation);
		this.setDelegated(AbstractTransactionalOperationBasedList.DEFAULT);
	}

	public PetType[] elements()
	{
		PetType[] result = new PetType[size()];
		this.getDelegated().toArray(this, result);
		return result;
	}

	public PetTypeList intersection(PetTypeList another)
	{
		return (PetTypeList)super.intersection(another);
	}

	public PetType getPetTypeAt(int index)
	{
		return (PetType)this.get(index);
	}

	public MithraObjectPortal getMithraObjectPortal()
	{
		return PetTypeFinder.getMithraObjectPortal();
	}

	public PetTypeList getNonPersistentCopy()
	{
		PetTypeList result = new PetTypeList();
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
	public MutableList<PetType> asGscList()
	{
		return ListAdapter.adapt(this);
	}

	public PetTypeList getDetachedCopy()
	{
		PetTypeList result = new PetTypeList();
		zDetachInto(result);
		return result;
	}

	public void zMakeDetached(Operation op, Object previousDetachedList)
	{
		super.zMakeDetached(op, previousDetachedList);
	}

	public void setPetType(String newValue)
	{
		zSetString(PetTypeFinder.petType(), newValue);
	}

	public void setPetTypeId(int newValue)
	{
		zSetInteger(PetTypeFinder.petTypeId(), newValue);
	}
}
