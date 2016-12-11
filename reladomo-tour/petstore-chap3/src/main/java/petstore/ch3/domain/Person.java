package petstore.ch3.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import petstore.ch3.serialization.PersonJsonUtils;

/********************************************************************************
* File        : $Source:  $
* Version     : $Revision:  $
* Date        : $Date:  $
* Modified by : $Author:  $
*******************************************************************************
*/
@JsonSerialize(using= PersonJsonUtils.Serializer.class)
@JsonDeserialize(using= PersonJsonUtils.Deserializer.class)
public class Person extends PersonAbstract
{
	public Person()
	{
		super();
		// You must not modify this constructor. Mithra calls this internally.
		// You can call this constructor. You can also add new constructors.
	}

	public Person(int id, String firstName, String lastName)
	{
		super();
		this.setPersonId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
}
