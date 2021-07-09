package nl.beyco.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.corda.core.serialization.ConstructorForDeserialization;
import net.corda.core.serialization.CordaSerializable;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Objects;

@CordaSerializable
public class Coffee {
    private String id;
    private String country;
    private String region;
    private boolean bulk;
    private String unit;
    private int quantity;
    private String species;
    private String process;
    private int minScreenSize;
    private int maxScreenSize;
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
    public Coffee(String id, String country, String region, boolean bulk,
                  String unit, int quantity, String species, String process,
                  int minScreenSize, int maxScreenSize, double cuppingScore, String sector,
                  LocalDateTime harvestAt, String qualitySegment, String[] certificates, String parentId) {
            this.id = id;
            this.country = country;
            this.region = region;
            this.bulk = bulk;
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
        return bulk;
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

    public Coffee copy() {
        return new Coffee(this.id, this.country, this.region, this.bulk, this.unit, this.quantity, this.species, this.process, this.minScreenSize, this.maxScreenSize,
                this.cuppingScore, this.sector, this.harvestAt, this.qualitySegment, this.certificates, this.parentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        Coffee other = (Coffee) obj;

        if(!Objects.deepEquals(
                new String[]{id, country, region,String.valueOf(bulk), unit, String.valueOf(quantity), species, process,
                        String.valueOf(minScreenSize), String.valueOf(maxScreenSize), String.valueOf(cuppingScore), sector, String.valueOf(harvestAt), qualitySegment},
                new String[]{other.id, other.country, other.region, String.valueOf(other.bulk), other.unit, String.valueOf(other.quantity), other.species, other.process,
                        String.valueOf(other.minScreenSize), String.valueOf(other.maxScreenSize), String.valueOf(other.cuppingScore), other.sector, String.valueOf(other.harvestAt), other.qualitySegment}
        )) {
            return false;
        }

        if(parentId != null && other.parentId != null) {
            if(!parentId.equals(other.parentId)) {
                return false;
            }
        }

        if(!Objects.deepEquals(certificates, other.certificates)) {
            return false;
        }

        return true;
    }
}
