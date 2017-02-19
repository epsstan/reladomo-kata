package simplebank.serialization;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import simplebank.domain.CustomerAccount;

import java.io.IOException;

/**
 * Created by stanle on 12/5/16.
 */
public class CustomerAccountSerde {

    public static class Serializer extends StdSerializer<CustomerAccount> {
        public Serializer() {
            this(null);
        }

        public Serializer(Class<CustomerAccount> t) {
            super(t);
        }

        @Override
        public void serialize(CustomerAccount account, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField("id", account.getAccountId());
            jsonGenerator.writeStringField("name", account.getAccountName());
            jsonGenerator.writeStringField("type", account.getAccountType());
            jsonGenerator.writeNumberField("balance", account.getBalance());
            jsonGenerator.writeEndObject();
        }
    }

    public static class Deserializer extends StdDeserializer<CustomerAccount> {
        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<CustomerAccount> t) {
            super(t);
        }

        @Override
        public CustomerAccount deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            return deserialize(treeNode);
        }

        public CustomerAccount deserialize (TreeNode treeNode)
        {
            int id = ((IntNode) treeNode.get("id")).intValue();
            String name = ((TextNode) treeNode.get("name")).asText();
            String type = ((TextNode) treeNode.get("type")).asText();
            double balance = ((DoubleNode) treeNode.get("balance")).doubleValue();

            CustomerAccount customerAccount = new CustomerAccount();
            customerAccount.setAccountId(id);
            customerAccount.setAccountName(name);
            customerAccount.setAccountType(type);
            customerAccount.setBalance(balance);

            return customerAccount;
        }
    }
}
