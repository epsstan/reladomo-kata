package petstore.ch3.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import petstore.ch3.serialization.PetTypeJsonUtils;

/********************************************************************************
* File        : $Source:  $
* Version     : $Revision:  $
* Date        : $Date:  $
* Modified by : $Author:  $
*******************************************************************************
*/
@JsonSerialize(using=PetTypeJsonUtils.Serializer.class)
@JsonDeserialize(using=PetTypeJsonUtils.Deserializer.class)
public class PetType extends PetTypeAbstract
{
	public PetType()
	{
		super();
		// You must not modify this constructor. Mithra calls this internally.
		// You can call this constructor. You can also add new constructors.
	}

	public PetType(int id, String name)
	{
		super();
		this.setPetTypeId(id);
		this.setPetType(name);
	}
}
