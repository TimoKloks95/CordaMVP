package nl.beyco.webserver.controllers;

import javafx.util.Pair;
import nl.beyco.webserver.TestData;
import nl.beyco.webserver.business.ContractsService;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContractsController.class)
public class ContractsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    ContractsService contractsService;

    @Test
    public void saveContractValidModel() throws Exception {
        String contractJson = TestData.getContractJsonString();
        String issuerId = "1";

        doNothing().when(contractsService).saveContract(isA(String.class), isA(Contract.class));

        mvc.perform(post("/contracts")
                .header("issuerId", issuerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contractJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void saveContractInvalidString() throws Exception {
        String contractJson = TestData.getContractJsonStringMissingRequiredString();
        String issuerId = "1";

        mvc.perform(post("/contracts")
                .header("issuerId", issuerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contractJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveContractInvalidScreensize() throws Exception {
        String contractJson = TestData.getContractJsonStringInvalidScreensize();
        String issuerId = "1";

        mvc.perform(post("/contracts")
                .header("issuerId", issuerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contractJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void saveContractInvalidDateTime() throws Exception {
        String contractJson = TestData.getContractJsonStringInvalidDateTime();
        String issuerId = "1";

        mvc.perform(post("/contracts")
                .header("issuerId", issuerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contractJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addAddendumValidModel() throws Exception {
        String addendumJson = TestData.getAddendumJsonString();
        String issuerId = "1";

        doNothing().when(contractsService).addAddendum(isA(String.class), isA(String.class), isA(Addendum.class));

        mvc.perform(post("/contracts/1/addenda")
                .header("issuerId", issuerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addendumJson))
                .andExpect(status().isOk());
    }

    @Test
    public void addAddendumInvalidString() throws Exception {
        String addendumJson = TestData.getAddendumJsonStringMissingRequiredString();
        String issuerId = "1";

        mvc.perform(post("/contracts/1/addenda")
                .header("issuerId", issuerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addendumJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addAddendumInvalidScreensize() throws Exception {
        String addendumJson = TestData.getAddendumJsonStringInvalidScreenSize();
        String issuerId = "1";

        mvc.perform(post("/contracts/1/addenda")
                .header("issuerId", issuerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addendumJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addAddendumInvalidDateTime() throws Exception {
        String addendumJson = TestData.getAddendumJsonStringInvalidDateTime();
        String issuerId = "1";

        mvc.perform(post("/contracts/1/addenda")
                .header("issuerId", issuerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(addendumJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getContract() throws Exception {
        Contract contract = TestData.getContractForTestJson();
        Addendum addendum = TestData.getAddendumForTestJson();
        String issuerId = "1";

        Pair<Contract, List<Addendum>> expected = new Pair<>(contract, Collections.singletonList(addendum));
        when(contractsService.getContract(isA(String.class), isA(String.class))).thenReturn(expected);

        mvc.perform(get("/contracts/1")
                .header("issuerId", issuerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}