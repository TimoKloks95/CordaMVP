package nl.beyco.webserver.controllers;

import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.business.ContractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class ClientController {
    private ContractService contractService;
    private static final Logger log = LogManager.getLogger(ClientController.class);
    public ClientController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> saveContract(@RequestHeader("issuerId") String issuerId, @Valid @RequestBody Contract contract) {
        contractService.saveContract(issuerId, contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{contractId}")
    public ResponseEntity<Contract> getContract(@RequestHeader("issuerId") String issuerId, @PathVariable("contractId") String contractId) {
        log.info("issuerId: "+issuerId);
        Contract result = contractService.getContract(issuerId, contractId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("{contractId}")
    public ResponseEntity<HttpStatus> addAddendum(@RequestHeader("issuerId") String issuerId, @PathVariable("contractId") String contractId, @Valid @RequestBody Addendum addendum) {
        contractService.addAddendum(issuerId, contractId, addendum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}