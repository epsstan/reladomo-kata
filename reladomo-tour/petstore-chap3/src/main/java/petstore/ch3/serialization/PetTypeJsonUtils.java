package petstore.ch3.serialization;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import petstore.ch3.domain.PetType;

import java.io.IOException;

/**
 * Created by stanle on 12/5/16.
 */
public class PetTypeJsonUtils {

    public static class Serializer extends StdSerializer<PetType> {
        public Serializer() {
            this(null);
        }

        public Serializer(Class<PetType> t) {
            super(t);
        }

        @Override
        public void serialize(PetType petType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", petType.getPetTypeId());
            jsonGenerator.writeStringField("type", petType.getPetType());
            jsonGenerator.writeEndObject();
        }
    }


    public static class Deserializer extends StdDeserializer<PetType> {

        public Deserializer()
        {
            this(null);
        }

        public Deserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public PetType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            int id = ((IntNode)treeNode.get("id")).intValue();
            String type = ((TextNode)treeNode.get("type")).asText();
            return new PetType(id, type);
        }
    }
}
