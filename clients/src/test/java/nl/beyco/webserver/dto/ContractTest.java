package nl.beyco.webserver.dto;

import nl.beyco.webserver.business.exceptions.BeycoParseException;
import nl.beyco.webserver.helpers.impl.BeycoSerializerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ContractTest {

    @TestConfiguration
    static class ContractTestContextConfiguration {
        @Bean
        public Contract contract() {
            return new Contract();
        }
    }

    @Autowired
    @InjectMocks
    private Contract sut;

    @MockBean
    private BeycoSerializerImpl serializer;

    @Test
    public void toJsonSuccess() {
        String expected = "Success";
        when(serializer.toJson(sut))
                .thenReturn(expected);
        String actual = sut.toJson();
        assertEquals(expected, actual);
    }

    @Test(expected = BeycoParseException.class)
    public void toJsonFail() {
        when(serializer.toJson(sut))
                .thenThrow(BeycoParseException.class);
        String actual = sut.toJson();
    }
}