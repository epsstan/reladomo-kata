package com.petstore.domain;
import java.util.*;
import java.sql.Timestamp;
import java.sql.Date;
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
import com.gs.fw.common.mithra.finder.PrintablePreparedStatement;
import com.gs.fw.common.mithra.finder.RelatedFinder;
import com.gs.fw.common.mithra.cache.offheap.MithraOffHeapDataObject;
import com.gs.fw.common.mithra.cache.offheap.OffHeapDataStorage;
/**
* This file was automatically generated using Mithra 16.1.0. Please do not modify it.
* Add custom logic to its subclass instead.
*/
public class PetTypeData
implements MithraDataObject
{
	private String petType;
	private int petTypeId;
	public boolean isPetTypeNull()
	{
		return this.getPetType() == null;
	}

	public boolean isPetTypeIdNull()
	{
		return false;
	}

	public void zSerializeFullData(ObjectOutput out) throws IOException
	{
		zWriteNullBits(out);
		out.writeObject(this.petType);
		out.writeInt(this.petTypeId);
	}

	private void zWriteNullBits(ObjectOutput out) throws IOException
	{
	}

	public String getPetType()
	{
		return this.petType;
	}

	public int zGetPetTypeAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(petType);
	}

	public void setPetType(String value)
	{
		this.petType = StringPool.getInstance().getOrAddToCache(value, PetTypeFinder.isFullCache());
	}

	public void setPetTypeNull()
	{
		this.setPetType(null);
	}

	public int getPetTypeId()
	{
		return this.petTypeId;
	}

	public void setPetTypeId(int value)
	{
		this.petTypeId = value;
	}

	public void setPetTypeIdNull()
	{
		throw new RuntimeException("should never be called");
	}

	protected void copyInto(PetTypeData copy, boolean withRelationships)
	{
		copy.petType = this.petType;
		copy.petTypeId = this.petTypeId;
		if (withRelationships)
		{
		}
	}

	public void zDeserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.petType = StringPool.getInstance().getOrAddToCache((String)in.readObject(), PetTypeFinder.isFullCache());
		this.petTypeId = in.readInt();
	}

	public boolean hasSamePrimaryKeyIgnoringAsOfAttributes(MithraDataObject other)
	{
		if (this == other) return true;
		final PetTypeData otherData = (PetTypeData) other;
		if (getPetTypeId() != otherData.getPetTypeId())
		{
			return false;
		}

		return true;
	}

	public void zSerializePrimaryKey(ObjectOutput out) throws IOException
	{
		out.writeInt(this.petTypeId);
	}

	public void zDeserializePrimaryKey(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.petTypeId = in.readInt();
	}

	public void clearRelationships()
	{
		clearAllDirectRefs();
	}

	public void clearAllDirectRefs()
	{
	}

	public void zSerializeRelationships(ObjectOutputStream out) throws IOException
	{
	}

	public void zDeserializeRelationships(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
	}

	public MithraOffHeapDataObject zCopyOffHeap()
	{
		throw new RuntimeException("off heap no supported");
	}

	public void copyNonPkAttributes(MithraDataObject newData)
	{
		final PetTypeData petTypeData = (PetTypeData) newData;
		this.setPetType(petTypeData.getPetType());
	}

	public byte zGetDataVersion() 
	{
		return (byte)0; 
	}

	public void zSetDataVersion(byte version) 
	{
	}
	// only used by dated objects
	public void zIncrementDataVersion() 
	{
	}

	public boolean zNonPrimaryKeyAttributesChanged(MithraDataObject newData)
	{
		return this.zNonPrimaryKeyAttributesChanged(newData, 0.0);
	}

	public boolean zNonPrimaryKeyAttributesChanged(MithraDataObject newData, double toleranceForFloatingPointFields)
	{
		final PetTypeData other = (PetTypeData) newData;
		if (!isPetTypeNull() ? !getPetType().equals(other.getPetType()) : !other.isPetTypeNull())
		{
			return true;
		}

		return false;
	}

	public MithraDataObject copy()
	{
		PetTypeData copy = new PetTypeData();
		this.copyInto(copy, true);
		return copy;
	}

	public MithraDataObject copy(boolean withRelationships)
	{
		PetTypeData copy = new PetTypeData();
		this.copyInto(copy, withRelationships);
		return copy;
	}

	public String zGetPrintablePrimaryKey()
	{
		String result = "";
		result += "petTypeId: "+(""+getPetTypeId())+ " / ";
		return result;
	}

	public boolean zAsOfAttributesFromEquals(MithraDataObject other)
	{
		return true;
	}

	public boolean zAsOfAttributesChanged(MithraDataObject other)
	{
		return false;
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public String zReadDataClassName(ObjectInput in) throws IOException, ClassNotFoundException
	{
		return "com.petstore.domain.PetTypeData";
	}

	public boolean changed(MithraDataObject newData)
	{
		if(zNonPrimaryKeyAttributesChanged(newData))
		{
			return true;
		}
		else
		{
			return zAsOfAttributesChanged(newData);
		}
	}

	public boolean zHasSameNullPrimaryKeyAttributes(MithraDataObject newData)
	{
		return true;
	}

	public MithraObjectPortal zGetMithraObjectPortal(int hierarchyDepth)
	{
		return PetTypeFinder.getMithraObjectPortal();
	}

	public MithraObjectPortal zGetMithraObjectPortal()
	{
		return PetTypeFinder.getMithraObjectPortal();
	}

	public Number zGetIdentityValue()
	{
		return null;
	}

	public boolean zHasIdentity()
	{
		return false;
	}

	public void zSetIdentity(Number identityValue)
	{
	}

	public String zGetSerializationClassName()
	{
		return "com.petstore.domain.PetTypeData";
	}
}
