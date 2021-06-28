package com.template.webserver.controllers;

import com.template.webserver.dto.Addendum;
import com.template.webserver.dto.Contract;
import com.template.webserver.business.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class ClientController {
    private ContractService contractService;
    public ClientController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("{issuerId}")
    public ResponseEntity<HttpStatus> saveContract(@PathVariable("issuerId") String issuerId, @Valid @RequestBody Contract contract) {
        contractService.saveContract(issuerId, contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{issuerId}/{contractId}")
    public ResponseEntity<Contract> getContract(@PathVariable("issuerId") String issuerId, @PathVariable("contractId") String contractId) {
        Contract result = contractService.getContract(issuerId, contractId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("{issuerId}/{contractId}")
    public ResponseEntity<HttpStatus> addAddendum(@PathVariable("issuerId") String issuerId, @PathVariable("contractId") String contractId,
                                                  @Valid @RequestBody Addendum addendum) {
        contractService.addAddendum(issuerId, contractId, addendum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}