package com.template.webserver.controllers;

import com.template.webserver.dto.Addendum;
import com.template.webserver.dto.Contract;
import com.template.webserver.business.ContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest extends BaseTestCase {
    @Autowired
    private MockMvc mvc;

    @MockBean
    ContractService contractService;

    @Test
    public void opslaanContractValideModel() throws Exception {
        //Arrange
        String contractJson = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"sellerId\": \"1\",\n" +
                "  \"buyerId\": \"2\",\n" +
                "  \"offerId\": \"1\",\n" +
                "  \"sellerSignedAt\": \"11 mei 2021\",\n" +
                "  \"buyerSignedAt\": \"11 mei 2021\",\n" +
                "  \"condities\": [\n" +
                "    {\n" +
                "      \"id\":\"1\",\n" +
                "      \"type\": \"test\",\n" +
                "      \"status\": \"test\",\n" +
                "      \"title\":\"test\",\n" +
                "      \"createdAt\": \"11 mei 2021\",\n" +
                "      \"negotiationId\": \"1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"koffies\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"country\": \"test\",\n" +
                "      \"region\": \"test\",\n" +
                "      \"unit\": \"test\",\n" +
                "      \"species\": \"test\",\n" +
                "      \"process\": \"test\",\n" +
                "      \"sector\": \"test\",\n" +
                "      \"harvestAt\": \"11 mei 2021\",\n" +
                "      \"qualitySegment\": \"test\",\n" +
                "      \"parentId\": \"2\",\n" +
                "      \"isBulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"minScreenSize\": 1,\n" +
                "      \"maxScreenSize\": 5,\n" +
                "      \"cuppingScore\": 10,\n" +
                "      \"certificates\": [\n" +
                "        \"test\", \"test\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"addenda\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"createdAt\": \"5 mei 2021\",\n" +
                "      \"buyerSignedAt\": \"6 mei 2021\",\n" +
                "      \"sellerSignedAt\": \"6 mei 2021\",\n" +
                "      \"conditions\": [\n" +
                "        {\n" +
                "          \"id\":\"2\",\n" +
                "          \"type\": \"test\",\n" +
                "          \"status\": \"test\",\n" +
                "          \"title\": \"test\",\n" +
                "          \"createdAt\": \"11 mei 2021\",\n" +
                "          \"negotiationId\": \"1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        doNothing().when(contractService).opslaanContract(isA(String.class), isA(Contract.class));

        //Act & Assert
        mvc.perform(post("/1")
                .contentType(MediaType.APPLICATION_JSON).content(contractJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void opslaanContractInvalideStringInModel() throws Exception {
        //Arrange - Id van contract is hier leeg
        String contractJson = "{\n" +
                "  \"id\": \"\",\n" +
                "  \"sellerId\": \"1\",\n" +
                "  \"buyerId\": \"2\",\n" +
                "  \"offerId\": \"1\",\n" +
                "  \"sellerSignedAt\": \"11 mei 2021\",\n" +
                "  \"buyerSignedAt\": \"11 mei 2021\",\n" +
                "  \"condities\": [\n" +
                "    {\n" +
                "      \"id\":\"1\",\n" +
                "      \"type\": \"test\",\n" +
                "      \"status\": \"test\",\n" +
                "      \"title\":\"test\",\n" +
                "      \"createdAt\": \"11 mei 2021\",\n" +
                "      \"negotiationId\": \"1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"koffies\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"country\": \"test\",\n" +
                "      \"region\": \"test\",\n" +
                "      \"unit\": \"test\",\n" +
                "      \"species\": \"test\",\n" +
                "      \"process\": \"test\",\n" +
                "      \"sector\": \"test\",\n" +
                "      \"harvestAt\": \"11 mei 2021\",\n" +
                "      \"qualitySegment\": \"test\",\n" +
                "      \"parentId\": \"2\",\n" +
                "      \"isBulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"minScreenSize\": 1,\n" +
                "      \"maxScreenSize\": 5,\n" +
                "      \"cuppingScore\": 10,\n" +
                "      \"certificates\": [\n" +
                "        \"test\", \"test\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"addenda\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"createdAt\": \"5 mei 2021\",\n" +
                "      \"buyerSignedAt\": \"6 mei 2021\",\n" +
                "      \"sellerSignedAt\": \"6 mei 2021\",\n" +
                "      \"conditions\": [\n" +
                "        {\n" +
                "          \"id\":\"2\",\n" +
                "          \"type\": \"test\",\n" +
                "          \"status\": \"test\",\n" +
                "          \"title\": \"test\",\n" +
                "          \"createdAt\": \"11 mei 2021\",\n" +
                "          \"negotiationId\": \"1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        doNothing().when(contractService).opslaanContract(isA(String.class), isA(Contract.class));

        //Act & Assert
        mvc.perform(post("/1")
                .contentType(MediaType.APPLICATION_JSON).content(contractJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void opslaanContractInvalideNestedIntInModel() throws Exception {
        //Arrange - Id is hier leeg
        String contractJson = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"sellerId\": \"1\",\n" +
                "  \"buyerId\": \"2\",\n" +
                "  \"offerId\": \"1\",\n" +
                "  \"sellerSignedAt\": \"11 mei 2021\",\n" +
                "  \"buyerSignedAt\": \"11 mei 2021\",\n" +
                "  \"condities\": [\n" +
                "    {\n" +
                "      \"id\":\"1\",\n" +
                "      \"type\": \"test\",\n" +
                "      \"status\": \"test\",\n" +
                "      \"title\":\"test\",\n" +
                "      \"createdAt\": \"11 mei 2021\",\n" +
                "      \"negotiationId\": \"1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"koffies\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"country\": \"test\",\n" +
                "      \"region\": \"test\",\n" +
                "      \"unit\": \"test\",\n" +
                "      \"species\": \"test\",\n" +
                "      \"process\": \"test\",\n" +
                "      \"sector\": \"test\",\n" +
                "      \"harvestAt\": \"11 mei 2021\",\n" +
                "      \"qualitySegment\": \"test\",\n" +
                "      \"parentId\": \"2\",\n" +
                "      \"isBulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"minScreenSize\": 1,\n" +
                "      \"maxScreenSize\": 5,\n" +
                "      \"cuppingScore\": -2,\n" +
                "      \"certificates\": [\n" +
                "        \"test\", \"test\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"addenda\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"createdAt\": \"5 mei 2021\",\n" +
                "      \"buyerSignedAt\": \"6 mei 2021\",\n" +
                "      \"sellerSignedAt\": \"6 mei 2021\",\n" +
                "      \"conditions\": [\n" +
                "        {\n" +
                "          \"id\":\"2\",\n" +
                "          \"type\": \"test\",\n" +
                "          \"status\": \"test\",\n" +
                "          \"title\": \"test\",\n" +
                "          \"createdAt\": \"11 mei 2021\",\n" +
                "          \"negotiationId\": \"1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        doNothing().when(contractService).opslaanContract(isA(String.class), isA(Contract.class));

        //Act & Assert
        mvc.perform(post("/1")
                .contentType(MediaType.APPLICATION_JSON).content(contractJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void ophalenContract() throws Exception {
        //Arrange
        Contract contract = new Contract("1234", "1", "2", "1", "5-mei-2021", "5 mei 2021",
                null, null, null);
        when(contractService.ophalenContract(isA(String.class), isA(String.class))).thenReturn(contract);

        //Act & Assert
        mvc.perform(get("/1/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void toevoegenAddendumValideModel() throws Exception {
        //Arrange
        String addendumJson = "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"createdAt\": \"5 mei 2021\",\n" +
                "    \"buyerSignedAt\": \"6 mei 2021\",\n" +
                "    \"sellerSignedAt\": \"6 mei 2021\",\n" +
                "    \"conditions\": null\n" +
                "}";

        doNothing().when(contractService).toevoegenAddendum(isA(String.class), isA(String.class), isA(Addendum.class));

        //Act & Assert
        mvc.perform(patch("/1/1")
                .contentType(MediaType.APPLICATION_JSON).content(addendumJson))
                .andExpect(status().isOk());
    }

    @Test
    public void toevoegenAddendumInvalideStringInModel() throws Exception {
        //Arrange
        String addendumJson = "{\n" +
                "    \"id\": \"\",\n" +
                "    \"createdAt\": \"5 mei 2021\",\n" +
                "    \"buyerSignedAt\": \"6 mei 2021\",\n" +
                "    \"sellerSignedAt\": \"6 mei 2021\",\n" +
                "    \"conditions\": null\n" +
                "}";

        doNothing().when(contractService).toevoegenAddendum(isA(String.class), isA(String.class), isA(Addendum.class));

        //Act & Assert
        mvc.perform(patch("/1/1")
                .contentType(MediaType.APPLICATION_JSON).content(addendumJson))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    public void toevoegenAddendumInvalideStringInNestedModel() throws Exception {
//        //Arrange
//        String addendumJson = "{\n" +
//                "    \"id\": \"1\",\n" +
//                "    \"createdAt\": \"5 mei 2021\",\n" +
//                "    \"buyerSignedAt\": \"6 mei 2021\",\n" +
//                "    \"sellerSignedAt\": \"6 mei 2021\",\n" +
//                "    \"conditions\": [\n" +
//                "        {\n" +
//                "            \"id\":\"\",\n" +
//                "            \"type\": \"\",\n" +
//                "            \"status\": \"test\",\n" +
//                "            \"title\":\"test\",\n" +
//                "            \"createdAt\": \"11 mei 2021\",\n" +
//                "            \"negotiationId\": \"1\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//
//        doNothing().when(contractService).toevoegenAddendum(isA(String.class), isA(String.class), isA(Addendum.class));
//
//        //Act & Assert
//        mvc.perform(patch("/1/1")
//                .contentType(MediaType.APPLICATION_JSON).content(addendumJson))
//                .andExpect(status().isBadRequest());
//    }
}