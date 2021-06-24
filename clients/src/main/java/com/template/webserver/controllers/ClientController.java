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

    @PostMapping("/{issuerId}")
    public ResponseEntity<HttpStatus> opslaanContract(@PathVariable("issuerId") String issuerId, @Valid @RequestBody Contract contract) {
        contractService.opslaanContract(issuerId, contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{issuerId}/{contractId}")
    public ResponseEntity<Contract> ophalenContract(@PathVariable("issuerId") String issuerId, @PathVariable("contractId") String contractId) {
        Contract result = contractService.ophalenContract(issuerId, contractId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("{issuerId}/{contractId}")
    public ResponseEntity<HttpStatus> toevoegenAddendum(@PathVariable("issuerId") String issuerId, @PathVariable("contractId") String contractId,
                                       @Valid @RequestBody Addendum addendum) {
        contractService.toevoegenAddendum(issuerId, contractId, addendum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}