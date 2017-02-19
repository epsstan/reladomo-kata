package simplebank.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import simplebank.domain.Customer;
import simplebank.domain.CustomerAccount;

import javax.ws.rs.ext.ContextResolver;

public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {

    final ObjectMapper defaultObjectMapper;

    public JacksonObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }

    private static ObjectMapper createDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Customer.class, new CustomerSerde.Serializer());
        module.addDeserializer(Customer.class, new CustomerSerde.Deserializer());
        module.addSerializer(CustomerAccount.class, new CustomerAccountSerde.Serializer());
        module.addDeserializer(CustomerAccount.class, new CustomerAccountSerde.Deserializer());
        mapper.registerModule(module);

        return mapper;
    }

}