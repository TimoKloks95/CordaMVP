package nl.beyco.flows.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.corda.core.serialization.CordaSerializable;
import nl.beyco.states.BeycoContractState;

@CordaSerializable
public class SerializationHelper {
    public static BeycoContractState contractJsonToBeycoContractState(String contractJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.readValue(contractJson, BeycoContractState.class);
    }
}
