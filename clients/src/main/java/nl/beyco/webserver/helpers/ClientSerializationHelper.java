package nl.beyco.webserver.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import org.springframework.stereotype.Service;

@Service
public class ClientSerializationHelper {
    private ObjectMapper objectMapper;
    private ObjectWriter writer;
    public ClientSerializationHelper() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        writer = objectMapper.writer().withDefaultPrettyPrinter();
    }

    public Contract contractJsonToContract(String contractJson) throws JsonProcessingException {
        return objectMapper.readValue(contractJson, Contract.class);
    }

    public String contractToContractJson(Contract contract) throws JsonProcessingException {
        return writer.writeValueAsString(contract);
    }

    public Addendum addendumJsonToAddendum(String addendumJson) throws JsonProcessingException {
        return objectMapper.readValue(addendumJson, Addendum.class);
    }

    public String addendumToAddendumJson(Addendum addendum) throws JsonProcessingException {
        return writer.writeValueAsString(addendum);
    }
}
