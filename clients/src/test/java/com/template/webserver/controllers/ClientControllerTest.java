package com.template.webserver.controllers;

import com.template.webserver.dto.Contract;
import com.template.webserver.business.ContractService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClientControllerTest extends BaseTestCase {
    @Mock
    ContractService contractService;

    @InjectMocks
    ClientController sut;

    @Test
    public void opslaanContractValideContract() {
        //Arrange
        //when(sut.opslaanContract(any(String.class), any(Contract.class))).thenReturn();

        //Act
        //ResponseEntity<String> result = sut.opslaanContract("1", new Contract());
        //Assert

        //assertEquals("Succes", result.getBody());
    }

    @Test
    public void ophalenContractInvalideContract() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void toevoegenAddendum() {
        //Arrange

        //Act

        //Assert
    }
}