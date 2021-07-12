package nl.beyco.webserver.controllers;

import javafx.util.Pair;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.business.ContractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractsController {
    private ContractService contractService;
    private static final Logger log = LogManager.getLogger(ContractsController.class);
    public ContractsController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> saveContract(@RequestHeader("issuerId") String issuerId, @Valid @RequestBody Contract contract) {
        log.info("Save contract endpoint was called by user with ID: " +issuerId+" at "+ LocalDateTime.now() + "to add contract with ID: "+contract.getId());
        contractService.saveContract(issuerId, contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<Pair<Contract, List<Addendum>>> getContract(@RequestHeader("issuerId") String issuerId, @PathVariable("contractId") String contractId) {
        log.info("Get contract endpoint was called by user with ID: " +issuerId+" at "+ LocalDateTime.now() + " requesting contract with ID: "+contractId);
        Pair<Contract, List<Addendum>> result = contractService.getContract(issuerId, contractId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{contractId}/addenda")
    public ResponseEntity<HttpStatus> addAddendum(@RequestHeader("issuerId") String issuerId, @PathVariable("contractId") String contractId,
                                                  @Valid @RequestBody Addendum addendum) {
        log.info("Add addendum endpoint was called by user with ID: " +issuerId+" at "+ LocalDateTime.now() + "to be added to contract with ID: "+contractId);
        contractService.addAddendum(issuerId, contractId, addendum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}