package nl.beyco.webserver.helpers.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.beyco.webserver.TestData;
import nl.beyco.webserver.business.exceptions.BeycoParseException;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.helpers.BeycoObjectManipulator;
import nl.beyco.webserver.helpers.BeycoSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BeycoSerializerImplTest {

    @TestConfiguration
    static class BeycoSerializerImplTestContextConfiguration {
        @Bean
        public BeycoSerializerImpl beycoSerializer() {
            return new BeycoSerializerImpl();
        }
    }

    @Autowired
    @InjectMocks
    private BeycoSerializerImpl beycoSerializer;

    @MockBean
    private BeycoObjectManipulatorImpl objectManipulator;

    @Test
    public void toObjectSuccess() {
        String contractJson = TestData.getContractJsonString();
        Contract testContract = TestData.getContractForTestJson();

        when(beycoSerializer.toObject(contractJson, Contract.class))
                .thenReturn(testContract);

        Contract actualContract = (Contract) beycoSerializer.toObject(contractJson, Contract.class);
        assertEquals(testContract.getId(), actualContract.getId());
        assertEquals(testContract.getSellerId(), actualContract.getSellerId());
        assertEquals(testContract.getBuyerId(), actualContract.getBuyerId());
        assertEquals(testContract.getSellerSignedAt(), actualContract.getSellerSignedAt());
        assertEquals(testContract.getBuyerSignedAt(), actualContract.getBuyerSignedAt());
    }

    @Test(expected = BeycoParseException.class)
    public void toObjectFail() throws JsonProcessingException {
        String json = "Test";
        when(objectManipulator.toObject(json, Contract.class))
                .thenThrow(JsonProcessingException.class);

        Contract contract = (Contract) beycoSerializer.toObject(json, Contract.class);
    }


    @Test
    public void toJsonSuccess() throws JsonProcessingException {
        Contract contract = TestData.getContractForTestJson();
        String expected = "Success";
        when(objectManipulator.toJson(contract)).thenReturn(expected);
        String actual = beycoSerializer.toJson(contract);
        assertEquals(expected, actual);
    }

    @Test(expected = BeycoParseException.class)
    public void toJsonFail() throws JsonProcessingException{
        Contract contract = TestData.getContractForTestJson();
        when(objectManipulator.toJson(contract))
                .thenThrow(JsonProcessingException.class);

        String actual = beycoSerializer.toJson(contract);
    }
}