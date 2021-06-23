package com.template.webserver;

import com.template.webserver.dto.Addendum;
import com.template.webserver.dto.Contract;
import com.template.webserver.services.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ClientController {
    private final ContractService contractService;
    private final static Logger logger = LoggerFactory.getLogger(ClientController.class);

    public ClientController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/{issuerId}")
    private ResponseEntity<String> opslaanContract(@PathVariable("issuerId") String issuerId, @RequestBody Contract contract) {
        Contract result = contractService.opslaanContract(issuerId, contract);
        return result == null
                ? new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>("Succes", HttpStatus.OK);
    }

    @GetMapping("{issuerId}/{contractId}")
    private ResponseEntity<Contract> ophalenContract(@PathVariable("issuerId") String issuerId, @PathVariable("contractId") String contractId) {
        Contract result = contractService.ophalenContract(issuerId, contractId);
        return result == null
                ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("{issuerId}/{contractId}")
    private ResponseEntity<String> toevoegenAddendum(@PathVariable("issuerId") String issuerId, @PathVariable("contractId") String contractId,
                                       @RequestBody Addendum addendum) {
        Contract result = contractService.toevoegenAddendum(issuerId, contractId, addendum);
        return result == null
                ? new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>("Succes", HttpStatus.OK);
    }
}