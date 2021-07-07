package nl.beyco.webserver.controllers;

import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.business.ContractService;
import nl.beyco.webserver.dto.ToAddAddendum;
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

    @PostMapping("")
    public ResponseEntity<HttpStatus> saveContract(@Valid @RequestBody Contract contract) {
        contractService.saveContract(contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{contractId}")
    public ResponseEntity<Contract> getContract(@RequestBody String issuerId, @PathVariable("contractId") String contractId) {
        Contract result = contractService.getContract(issuerId, contractId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PatchMapping("{contractId}")
    public ResponseEntity<HttpStatus> addAddendum(@PathVariable("contractId") String contractId, @Valid @RequestBody ToAddAddendum addendum) {
        contractService.addAddendum(contractId, addendum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}