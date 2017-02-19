package simplebank.scratch;

import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.factory.Lists;
import com.gs.collections.impl.factory.primitive.IntSets;
import com.gs.fw.common.mithra.attribute.IntegerAttribute;
import com.gs.fw.common.mithra.attribute.StringAttribute;
import com.gs.fw.common.mithra.finder.Operation;
import simplebank.domain.Customer;
import simplebank.domain.CustomerAccount;
import simplebank.domain.CustomerFinder;
import simplebank.domain.CustomerList;

/**
 * Created by stanle on 2/17/17.
 */
public class Scratch {

    public static void main(String[] args) {

/*
        Customer mickey = new Customer();
        mickey.setCustomerId(123);
        mickey.setFirstName("Mickey");
        mickey.setLastName("Mouse");
        mickey.insert();
*/


/*
        Customer mickey = new Customer();
        mickey.setCustomerId(123);
        mickey.setFirstName("Mickey");
        mickey.setLastName("Mouse");

        CustomerAccount savingsAccount = new CustomerAccount();
        savingsAccount.setAccountId(1000);
        savingsAccount.setBalance(100);
        savingsAccount.setAccountType("savings");

        mickey.getAccounts().add(savingsAccount);

        mickey.cascadeInsert();
*/

    }

    private static void foo1()
    {
        StringAttribute<Customer> firstNameAttribute = CustomerFinder.firstName();
        Operation firstNameOperation = firstNameAttribute.eq("Mickey");

        IntegerAttribute<Customer> idAttribute = CustomerFinder.customerId();
        Operation idOperation = idAttribute.in(IntSets.immutable.of(123, 456, 789));


        CustomerList mickeys = CustomerFinder.findMany(firstNameOperation);
    }

    private static void foo2()
    {
        CustomerList customers = CustomerFinder.findMany(CustomerFinder.firstName().eq("mickey"));
        MutableList<Object> mickeysAccounts = Lists.mutable.empty();
        for (Customer customer : customers)
        {
            for (CustomerAccount account : customer.getAccounts())
            {
                mickeysAccounts.add(account.getAccountId());
            }
        }
    }
}
