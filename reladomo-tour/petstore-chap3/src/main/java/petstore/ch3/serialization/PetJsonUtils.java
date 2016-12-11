package petstore.ch3.serialization;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import petstore.ch3.domain.Pet;
import petstore.ch3.domain.PetType;

import java.io.IOException;

/**
 * Created by stanle on 12/5/16.
 */
public class PetJsonUtils {

    public static class Serializer extends StdSerializer<Pet> {
        public Serializer() {
            this(null);
        }

        public Serializer(Class<Pet> t) {
            super(t);
        }

        @Override
        public void serialize(Pet pet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", pet.getPetName());
            jsonGenerator.writeNumberField("age", pet.getPetAge());
            jsonGenerator.writeObjectField("type", pet.getPetType());
            jsonGenerator.writeEndObject();
        }
    }

    public static class Deserializer extends StdDeserializer<Pet> {
        public Deserializer() {
            this(null);
        }

        public Deserializer(Class<Pet> t) {
            super(t);
        }

        @Override
        public Pet deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            return deserialize(treeNode);
        }

        public Pet deserialize (TreeNode treeNode)
        {
            String name = ((TextNode) treeNode.get("name")).asText();
            int age = ((IntNode) treeNode.get("age")).intValue();
            TreeNode petTypeNode = treeNode.get("type");
            int petTypeId = ((IntNode) petTypeNode.get("id")).intValue();
            String petType = ((TextNode) petTypeNode.get("type")).asText();

            PetType type = new PetType(petTypeId, petType);
            return new Pet(name, age, type);
        }
    }
}
