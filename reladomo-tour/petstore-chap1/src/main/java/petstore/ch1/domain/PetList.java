package petstore.ch1.domain;
import com.gs.fw.finder.Operation;
import java.util.*;
/********************************************************************************
* File        : $Source:  $
* Version     : $Revision:  $
* Date        : $Date:  $
* Modified by : $Author:  $
*******************************************************************************
*/
public class PetList extends PetListAbstract
{
	public PetList()
	{
		super();
	}

	public PetList(int initialSize)
	{
		super(initialSize);
	}

	public PetList(Collection c)
	{
		super(c);
	}

	public PetList(Operation operation)
	{
		super(operation);
	}
}
