package petstore.ch2.domain;
import com.gs.fw.finder.Operation;
import java.util.*;
/********************************************************************************
* File        : $Source:  $
* Version     : $Revision:  $
* Date        : $Date:  $
* Modified by : $Author:  $
*******************************************************************************
*/
public class PetTypeList extends PetTypeListAbstract
{
	public PetTypeList()
	{
		super();
	}

	public PetTypeList(int initialSize)
	{
		super(initialSize);
	}

	public PetTypeList(Collection c)
	{
		super(c);
	}

	public PetTypeList(Operation operation)
	{
		super(operation);
	}
}
