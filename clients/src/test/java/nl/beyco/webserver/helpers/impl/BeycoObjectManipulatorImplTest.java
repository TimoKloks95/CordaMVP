package nl.beyco.webserver.helpers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nl.beyco.webserver.TestData;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.helpers.BeycoObjectManipulator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BeycoObjectManipulatorImplTest {
    @TestConfiguration
    static class BeycoObjectManipulatorImplTestContextConfiguration {
        @Bean
        public BeycoObjectManipulatorImpl beycoObjectManipulator() {
            return new BeycoObjectManipulatorImpl();
        }
    }

    @Autowired
    @InjectMocks
    private BeycoObjectManipulatorImpl objectManipulator;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private ObjectWriter writer;

    @Test
    public void contractStringToContractObjectSuccess() throws JsonProcessingException {
        String contractString = TestData.getContractJsonString();
        Contract testContract = TestData.getContractForTestJson();

        when(mapper.readValue(contractString, Contract.class))
                .thenReturn(testContract);
        Contract actualContract = objectManipulator.toObject(contractString, Contract.class);
        assertEquals(testContract.getId(), actualContract.getId());
        assertEquals(testContract.getSellerId(), actualContract.getSellerId());
        assertEquals(testContract.getBuyerId(), actualContract.getBuyerId());
        assertEquals(testContract.getSellerSignedAt(), actualContract.getSellerSignedAt());
        assertEquals(testContract.getBuyerSignedAt(), actualContract.getBuyerSignedAt());
    }

    @Test
    public void randomStringToContractObjectFail() {
        String randomString = "RandomString";
        try {
            Contract testContract = objectManipulator.toObject(randomString, Contract.class);
            fail("Expected exception was not thrown.");
        } catch(JsonProcessingException e) {
        }
    }

    @Test
    public void contractObjectToJsonStringSuccess() throws JsonProcessingException {
        Contract contract = TestData.getContractForTestJson();
        String expectedString = TestData.getContractJsonString();
        when(writer.writeValueAsString(contract))
                .thenReturn(expectedString);
        String actualString = objectManipulator.toJson(contract);
        assertEquals(expectedString.replaceAll("\\s", ""), actualString.replaceAll("\\s", ""));
    }
}