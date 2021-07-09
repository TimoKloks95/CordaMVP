package nl.beyco.states;

import net.corda.core.serialization.CordaSerializable;

@CordaSerializable
public class ContractJsonWithAddendaJson {
    private String contractJson;
    private String[] addendaJson;

    public ContractJsonWithAddendaJson(String contractJson, String[] addendaJson) {
        this.contractJson = contractJson;
        this.addendaJson = addendaJson;
    }

    public String getContractJson() {
        return contractJson;
    }

    public String[] getAddendaJson() {
        return addendaJson;
    }
}
