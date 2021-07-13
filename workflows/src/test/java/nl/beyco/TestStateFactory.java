package nl.beyco;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.corda.core.contracts.ContractState;

public abstract class TestStateFactory {
    public abstract ContractState generateTestState();
    public abstract String stateToJson() throws JsonProcessingException;
}
