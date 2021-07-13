package nl.beyco.states;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.serialization.ConstructorForDeserialization;
import nl.beyco.contracts.BeycoContract;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@BelongsToContract(BeycoContract.class)
public class Addendum implements ContractState, LinearState {
    private String id;
    private String contractId;
    private String sellerId;
    private String buyerId;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime buyerSignedAt;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime sellerSignedAt;
    private List<Coffee> coffees;
    private List<Condition> conditions;
    private Party node;

    public Addendum() {

    }

    @ConstructorForDeserialization
    public Addendum(String id, String sellerId, String buyerId, LocalDateTime createdAt, LocalDateTime buyerSignedAt, LocalDateTime sellerSignedAt,
                    List<Coffee> coffees, List<Condition> conditions) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.createdAt = createdAt;
        this.buyerSignedAt = buyerSignedAt;
        this.sellerSignedAt = sellerSignedAt;
        this.coffees = coffees;
        this.conditions = conditions;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getContractId() {
        return contractId;
    }

    public LocalDateTime getBuyerSignedAt() {
        return buyerSignedAt;
    }

    public LocalDateTime getSellerSignedAt() {
        return sellerSignedAt;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setNode(Party node) {
        this.node = node;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(this);
    }

    @NotNull
    @Override
    public UniqueIdentifier getLinearId() {
        return new UniqueIdentifier(contractId);
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return Collections.singletonList(node);
    }
}
