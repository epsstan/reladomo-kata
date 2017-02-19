package simplebank.serialization;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import simplebank.domain.Customer;
import simplebank.domain.CustomerAccount;
import simplebank.domain.CustomerAccountList;

import java.io.IOException;

/**
 * Created by stanle on 12/5/16.
 */
public class CustomerSerde
{
    public static class Serializer extends StdSerializer<Customer>
    {
        public Serializer()
        {
            this(null);
        }

        public Serializer(Class<Customer> t) {
            super(t);
        }

        @Override
        public void serialize(Customer customer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", customer.getCustomerId());
            jsonGenerator.writeStringField("firstName", customer.getFirstName());
            jsonGenerator.writeStringField("lastName", customer.getLastName());
            jsonGenerator.writeArrayFieldStart("accounts");
            CustomerAccountList accounts = customer.getAccounts();
            for (CustomerAccount account :accounts) {
                jsonGenerator.writeObject(account);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }
    }

    public static class Deserializer extends StdDeserializer<Customer>
    {
        public Deserializer()
        {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Customer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            int id = ((IntNode) treeNode.get("id")).intValue();
            String firstName = ((TextNode)treeNode.get("firstName")).asText();
            String lastName = ((TextNode)treeNode.get("lastName")).asText();
            Customer customer = new Customer(id, firstName, lastName);

            CustomerAccountList accounts = new CustomerAccountList();
            ArrayNode accountsNode = (ArrayNode)treeNode.get("accounts");
            for (JsonNode accountNode : accountsNode)
            {
                CustomerAccount account = new CustomerAccountSerde.Deserializer().deserialize(accountNode);
                accounts.add(account);
            }
            customer.setAccounts(accounts);
            return customer;
        }
    }
}
