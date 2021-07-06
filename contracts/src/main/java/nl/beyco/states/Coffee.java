package nl.beyco.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.corda.core.serialization.ConstructorForDeserialization;
import net.corda.core.serialization.CordaSerializable;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@CordaSerializable
public class Coffee {
    private String id;
    private String country;
    private String region;
    @JsonProperty("bulk")
    private boolean isBulk;
    private String unit;
    private int quantity;
    private String species;
    private String process;
    private String minScreenSize;
    private String maxScreenSize;
    private double cuppingScore;
    private String sector;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime harvestAt;
    private String qualitySegment;
    private String[] certificates;
    private String parentId;

    public Coffee() {

    }

    @ConstructorForDeserialization
    public Coffee(String id, String country, String region, boolean isBulk,
                  String unit, int quantity, String species, String process,
                  String minScreenSize, String maxScreenSize, double cuppingScore, String sector,
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

    public String getMinScreenSize() {
        return minScreenSize;
    }

    public String getMaxScreenSize() {
        return maxScreenSize;
    }

    public double getCuppingScore() {
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
