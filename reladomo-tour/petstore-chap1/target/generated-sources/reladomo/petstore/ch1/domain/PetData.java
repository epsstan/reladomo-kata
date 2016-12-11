package petstore.ch1.domain;
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
public class PetData
implements MithraDataObject
{
	private Object[] _relationships;
	private int personId;
	private int petAge;
	private String petName;
	private int petTypeId;
	public boolean isPersonIdNull()
	{
		return false;
	}

	public boolean isPetAgeNull()
	{
		return false;
	}

	public boolean isPetNameNull()
	{
		return this.getPetName() == null;
	}

	public boolean isPetTypeIdNull()
	{
		return false;
	}

	public void zSerializeFullData(ObjectOutput out) throws IOException
	{
		zWriteNullBits(out);
		out.writeInt(this.personId);
		out.writeInt(this.petAge);
		out.writeObject(this.petName);
		out.writeInt(this.petTypeId);
	}

	private void zWriteNullBits(ObjectOutput out) throws IOException
	{
	}

	public int getPersonId()
	{
		return this.personId;
	}

	public void setPersonId(int value)
	{
		this.personId = value;
	}

	public void setPersonIdNull()
	{
		throw new RuntimeException("should never be called");
	}

	public int getPetAge()
	{
		return this.petAge;
	}

	public void setPetAge(int value)
	{
		this.petAge = value;
	}

	public void setPetAgeNull()
	{
		throw new RuntimeException("should never be called");
	}

	public String getPetName()
	{
		return this.petName;
	}

	public int zGetPetNameAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(petName);
	}

	public void setPetName(String value)
	{
		this.petName = StringPool.getInstance().getOrAddToCache(value, PetFinder.isFullCache());
	}

	public void setPetNameNull()
	{
		this.setPetName(null);
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

	protected void copyInto(PetData copy, boolean withRelationships)
	{
		copy.personId = this.personId;
		copy.petAge = this.petAge;
		copy.petName = this.petName;
		copy.petTypeId = this.petTypeId;
		if (withRelationships)
		{
			if (_relationships != null)
			{
				copy._relationships = new Object[2];
				System.arraycopy(_relationships, 0, copy._relationships, 0, _relationships.length);
			}
		}
	}

	public void zDeserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.personId = in.readInt();
		this.petAge = in.readInt();
		this.petName = StringPool.getInstance().getOrAddToCache((String)in.readObject(), PetFinder.isFullCache());
		this.petTypeId = in.readInt();
	}

	public boolean hasSamePrimaryKeyIgnoringAsOfAttributes(MithraDataObject other)
	{
		if (this == other) return true;
		final PetData otherData = (PetData) other;
		if (!isPetNameNull() ? !getPetName().equals(otherData.getPetName()) : !otherData.isPetNameNull())
		{
			return false;
		}

		return true;
	}

	public void zSerializePrimaryKey(ObjectOutput out) throws IOException
	{
		out.writeObject(this.petName);
	}

	public void zDeserializePrimaryKey(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.petName = StringPool.getInstance().getOrAddToCache((String)in.readObject(), PetFinder.isFullCache());
	}

	public void clearRelationships()
	{
		_relationships = null;
		clearAllDirectRefs();
	}

	public void clearAllDirectRefs()
	{
	}

	public Object getPetType()
	{
		if (_relationships != null)
		{
			return _relationships[1];
		}

		return null;
	}

	public void setPetType(Object related)
	{
		if (_relationships == null)
		{
			_relationships = new Object[2];
		}

		_relationships[1] = related;
	}

	public Object getOwner()
	{
		if (_relationships != null)
		{
			return _relationships[0];
		}

		return null;
	}

	public void setOwner(Object related)
	{
		if (_relationships == null)
		{
			_relationships = new Object[2];
		}

		_relationships[0] = related;
	}

	public void zSerializeRelationships(ObjectOutputStream out) throws IOException
	{
		if (_relationships == null)
		{
			out.writeInt(0);
			return;
		}

		out.writeInt(_relationships.length);
		for(int i=0;i<_relationships.length;i++)
		{
			out.writeObject(_relationships[i]);
		}
	}

	public void zDeserializeRelationships(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		int total = in.readInt();
		if(total > 0)
		{
			_relationships = new Object[total];
			for(int i=0;i<total;i++)
			{
				_relationships[i] = in.readObject();
			}
		}
	}

	public MithraOffHeapDataObject zCopyOffHeap()
	{
		throw new RuntimeException("off heap no supported");
	}

	public void copyNonPkAttributes(MithraDataObject newData)
	{
		final PetData petData = (PetData) newData;
		this.setPersonId(petData.getPersonId());
		this.setPetAge(petData.getPetAge());
		this.setPetTypeId(petData.getPetTypeId());
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
		final PetData other = (PetData) newData;
		if ( getPersonId() != other.getPersonId())
		{
			return true;
		}

		if ( getPetAge() != other.getPetAge())
		{
			return true;
		}

		if ( getPetTypeId() != other.getPetTypeId())
		{
			return true;
		}

		return false;
	}

	public MithraDataObject copy()
	{
		PetData copy = new PetData();
		this.copyInto(copy, true);
		return copy;
	}

	public MithraDataObject copy(boolean withRelationships)
	{
		PetData copy = new PetData();
		this.copyInto(copy, withRelationships);
		return copy;
	}

	public String zGetPrintablePrimaryKey()
	{
		String result = "";
		result += "petName: "+"'"+getPetName()+"'"+ " / ";
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
		return "petstore.ch1.domain.PetData";
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
		return PetFinder.getMithraObjectPortal();
	}

	public MithraObjectPortal zGetMithraObjectPortal()
	{
		return PetFinder.getMithraObjectPortal();
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
		return "petstore.ch1.domain.PetData";
	}
}
