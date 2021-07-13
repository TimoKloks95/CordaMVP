package nl.beyco.webserver.business.impl;

import javafx.util.Pair;
import nl.beyco.states.ContractJsonWithAddendaJson;
import nl.beyco.webserver.blockchain.RPCInteractionService;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.business.ContractsService;
import nl.beyco.webserver.helpers.BeycoSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ContractsServiceImpl implements ContractsService {
    @Autowired
    private RPCInteractionService RPCInteractionService;

    @Autowired
    private BeycoSerializer beycoSerializer;

    @Override
    public void saveContract(String issuerId, Contract contract) {
        String contractJson = beycoSerializer.toJson(contract);
        RPCInteractionService.startSaveContractFlow(issuerId, contractJson);
    }

    @Override
    public Pair<Contract, List<Addendum>> getContract(String issuerId, String contractId) {
        ContractJsonWithAddendaJson result = RPCInteractionService.startGetContractFlow(issuerId, contractId);
        Contract contract = (Contract) beycoSerializer.toObject(result.getContractJson(), Contract.class);

        List<Addendum> addenda = new LinkedList<>();
        for(int i=0; i<result.getAddendaJson().length; i++) {
            addenda.add((Addendum) beycoSerializer.toObject(result.getAddendaJson()[i], Addendum.class));
        }
        return new Pair<>(contract, addenda);
    }

    @Override
    public void addAddendum(String issuerId, String contractId, Addendum addendum) {
        String addendumJson = beycoSerializer.toJson(addendum);
        RPCInteractionService.startAddAddendumFlow(issuerId, contractId, addendumJson);
    }
}
