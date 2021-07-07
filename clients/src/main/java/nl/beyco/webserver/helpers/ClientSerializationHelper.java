package nl.beyco.webserver.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.beyco.webserver.dto.Contract;
import org.springframework.stereotype.Service;

@Service
public class ClientSerializationHelper {
    private ObjectMapper objectMapper;
    public ClientSerializationHelper() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    public Contract contractJsonToContract(String contractJson) throws JsonProcessingException {
        return objectMapper.readValue(contractJson, Contract.class);
    }

    public String contractToContractJson(Contract contract) throws JsonProcessingException {
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(contract);
    }
}
