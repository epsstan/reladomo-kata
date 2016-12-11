package petstore.ch3.serialization;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import petstore.ch3.domain.Person;
import petstore.ch3.domain.Pet;
import petstore.ch3.domain.PetList;

import java.io.IOException;

/**
 * Created by stanle on 12/5/16.
 */
public class PersonJsonUtils
{
    public static class Serializer extends StdSerializer<Person>
    {
        public Serializer()
        {
            this(null);
        }

        public Serializer(Class<Person> t) {
            super(t);
        }

        @Override
        public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", person.getPersonId());
            jsonGenerator.writeStringField("firstName", person.getFirstName());
            jsonGenerator.writeStringField("lastName", person.getLastName());
            jsonGenerator.writeArrayFieldStart("pets");
            //jsonGenerator.writeStartArray
            // ();
            PetList pets = person.getPets();
            for (Pet pet :pets) {
                jsonGenerator.writeObject(pet);
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }
    }

    public static class Deserializer extends StdDeserializer<Person>
    {
        public Deserializer()
        {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Person deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            int id = ((IntNode) treeNode.get("id")).intValue();
            String firstName = ((TextNode)treeNode.get("firstName")).asText();
            String lastName = ((TextNode)treeNode.get("lastName")).asText();
            Person person = new Person(id, firstName, lastName);

            ArrayNode petsNode = (ArrayNode)treeNode.get("pets");
            for (JsonNode petNode : petsNode)
            {
                Pet pet = new PetJsonUtils.Deserializer().deserialize(petNode);
                person.getPets().add(pet);
            }
            return person;
        }
    }
}
