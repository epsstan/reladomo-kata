package petstore.ch3.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.BeforeClass;
import org.junit.Test;
import petstore.ch3.domain.Person;
import petstore.ch3.domain.PersonList;
import petstore.ch3.domain.Pet;
import petstore.ch3.domain.PetType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by stanle on 12/5/16.
 */
public class SerializationTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @BeforeClass
    public static void setup()
    {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Person.class, new PersonJsonUtils.Serializer());
        module.addDeserializer(Person.class, new PersonJsonUtils.Deserializer());

        module.addSerializer(Pet.class, new PetJsonUtils.Serializer());
        module.addDeserializer(Pet.class, new PetJsonUtils.Deserializer());

        module.addSerializer(PetType.class, new PetTypeJsonUtils.Serializer());
        module.addDeserializer(PetType.class, new PetTypeJsonUtils.Deserializer());

        OBJECT_MAPPER.registerModule(module);
    }

    @Test
    public void petTypeSerialization() throws IOException {
        PetType petType = new PetType(1, "dog");
        String serialized = OBJECT_MAPPER.writeValueAsString(petType);
        PetType deserialized = OBJECT_MAPPER.readValue(serialized, PetType.class);
        assertEquals(1, deserialized.getPetTypeId());
        assertEquals("dog", deserialized.getPetType());
    }

    @Test
    public void petSerialization() throws IOException {
        Pet pet = new Pet("fluffy", 10, new PetType(1, "dog"));
        String serialized = OBJECT_MAPPER.writeValueAsString(pet);
        Pet deserialized = OBJECT_MAPPER.readValue(serialized, Pet.class);

        assertEquals("fluffy", deserialized.getPetName());
        assertEquals(10, deserialized.getPetAge());

        assertEquals(1, deserialized.getPetType().getPetTypeId());
        assertEquals("dog", deserialized.getPetType().getPetType());
    }

    @Test
    public void personSerialization() throws IOException {
        Person person = new Person(1, "joe", "smith");
        Pet pet = new Pet("fluffy", 10, new PetType(2, "dog"));
        person.getPets().add(pet);

        String serialized = OBJECT_MAPPER.writeValueAsString(person);
        Person deserialized = OBJECT_MAPPER.readValue(serialized, Person.class);

        assertEquals(1, deserialized.getPersonId());
        assertEquals("joe", deserialized.getFirstName());
        assertEquals("smith", deserialized.getLastName());

        assertEquals(1, deserialized.getPets().size());
        assertEquals("fluffy", deserialized.getPets().get(0).getPetName());
        assertEquals(10, deserialized.getPets().get(0).getPetAge());
        assertEquals(2, deserialized.getPets().get(0).getPetType().getPetTypeId());
        assertEquals("dog", deserialized.getPets().get(0).getPetType().getPetType());
    }

    @Test
    public void personListSerialization() throws IOException {
        Person joe = new Person(1, "joe", "smith");
        Person alice = new Person(2, "alice", "smith");

        PersonList persons = new PersonList();
        persons.add(joe);
        persons.add(alice);

        String serialized = OBJECT_MAPPER.writeValueAsString(persons);
        PersonList deserialized = OBJECT_MAPPER.readValue(serialized, PersonList.class);
        assertEquals(2, deserialized.size());

        assertEquals(1, deserialized.get(0).getPersonId());
        assertEquals("joe", deserialized.get(0).getFirstName());
        assertEquals("smith", deserialized.get(0).getLastName());

        assertEquals(2, deserialized.get(1).getPersonId());
        assertEquals("alice", deserialized.get(1).getFirstName());
        assertEquals("smith", deserialized.get(1).getLastName());
    }
}
