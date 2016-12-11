package petstore.ch3.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import petstore.ch3.serialization.PetJsonUtils;

/********************************************************************************
* File        : $Source:  $
* Version     : $Revision:  $
* Date        : $Date:  $
* Modified by : $Author:  $
*******************************************************************************
*/
@JsonSerialize(using=PetJsonUtils.Serializer.class)
@JsonDeserialize(using=PetJsonUtils.Deserializer.class)
public class Pet extends PetAbstract
{
	public Pet()
	{
		super();
		// You must not modify this constructor. Mithra calls this internally.
		// You can call this constructor. You can also add new constructors.
	}

	public Pet(String name, int age)
	{
		super();
		this.setPetName(name);
		this.setPetAge(age);
	}

	public Pet(String name, int age, PetType type)
	{
		super();
		this.setPetName(name);
		this.setPetAge(age);
		this.setPetType(type);
	}
}
