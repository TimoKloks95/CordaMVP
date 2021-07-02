package nl.beyco.states;

import net.corda.core.serialization.ConstructorForDeserialization;
import net.corda.core.serialization.CordaSerializable;

import java.time.LocalDateTime;

@CordaSerializable
public class CoffeeState {
    private final String id;
    private final String country;
    private final String region;
    private final boolean isBulk;
    private final String unit;
    private final int quantity;
    private final String species;
    private final String process;
    private final int minScreenSize;
    private final int maxScreenSize;
    private final int cuppingScore;
    private final String sector;
    private final LocalDateTime harvestAt;
    private final String qualitySegment;
    private final String[] certificates;
    private final String parentId;

    @ConstructorForDeserialization
    public CoffeeState(String id, String country, String region, boolean isBulk,
                       String unit, int quantity, String species, String process,
                       int minScreenSize, int maxScreenSize, int cuppingScore, String sector,
                       LocalDateTime harvestAt, String qualitySegment, String[] certificates, String parentId) {
            this.id = id;
            this.country = country;
            this.region = region;
            this.isBulk = isBulk;
            this.unit = unit;
            this.quantity = quantity;
            this.species = species;
            this.process = process;
            this.minScreenSize = minScreenSize;
            this.maxScreenSize = maxScreenSize;
            this.cuppingScore = cuppingScore;

            this.sector = sector;
            this.harvestAt = harvestAt;
            this.qualitySegment = qualitySegment;
            this.certificates = certificates;
            this.parentId = parentId;
        }

    public String getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public boolean isBulk() {
        return isBulk;
    }

    public String getUnit() {
        return unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSpecies() {
        return species;
    }

    public String getProcess() {
        return process;
    }

    public int getMinScreenSize() {
        return minScreenSize;
    }

    public int getMaxScreenSize() {
        return maxScreenSize;
    }

    public int getCuppingScore() {
        return cuppingScore;
    }

    public String getSector() {
        return sector;
    }

    public LocalDateTime getHarvestAt() {
        return harvestAt;
    }

    public String getQualitySegment() {
        return qualitySegment;
    }

    public String[] getCertificates() {
        return certificates;
    }

    public String getParentId() {
        return parentId;
    }
}
