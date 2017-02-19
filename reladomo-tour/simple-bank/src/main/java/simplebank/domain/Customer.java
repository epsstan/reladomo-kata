package simplebank.domain;
/********************************************************************************
* File        : $Source:  $
* Version     : $Revision:  $
* Date        : $Date:  $
* Modified by : $Author:  $
*******************************************************************************
*/
public class Customer extends CustomerAbstract
{
	public Customer()
	{
		super();
		// You must not modify this constructor. Mithra calls this internally.
		// You can call this constructor. You can also add new constructors.
	}

	public Customer(int customerId, String firstName, String lastName)
	{
		super();
		this.setCustomerId(customerId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
}
