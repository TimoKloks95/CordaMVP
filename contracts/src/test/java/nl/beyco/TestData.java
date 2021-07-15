package nl.beyco;

import nl.beyco.states.Addendum;
import nl.beyco.states.BeycoContractState;
import nl.beyco.states.Coffee;
import nl.beyco.states.Condition;
import java.time.LocalDateTime;
import java.util.Collections;

public class TestData {
    public static BeycoContractState getValidContract() {
        return new BeycoContractState("1", "1", "2", "1", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), Collections.singletonList(getValidCoffee()),
                Collections.singletonList(getValidCondition()));
    }

    public static BeycoContractState getInvalidContractWithEmptyIdField() {
        return new BeycoContractState("", "1", "2", "1", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), Collections.singletonList(getValidCoffee()),
                Collections.singletonList(getValidCondition()));
    }

    public static BeycoContractState getInvalidContractWithSignedTimeAfterCurrentTime() {
        return new BeycoContractState("1", "1", "2", "1", LocalDateTime.now().plusYears(1),
                LocalDateTime.of(2021, 05, 16, 10, 00), Collections.singletonList(getValidCoffee()),
                Collections.singletonList(getValidCondition()));
    }

    public static BeycoContractState getInvalidContractWithNoCoffee() {
        return new BeycoContractState("1", "1", "2", "1", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), Collections.emptyList(),
                Collections.singletonList(getValidCondition()));
    }

    public static BeycoContractState getInvalidContractWithNoCondition() {
        return new BeycoContractState("1", "1", "2", "1", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), Collections.singletonList(getValidCoffee()),
                Collections.emptyList());
    }

    public static BeycoContractState getInvalidContractWithInvalidCoffee() {
        return new BeycoContractState("1", "1", "2", "1", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), Collections.singletonList(getInvalidCoffee()),
                Collections.singletonList(getValidCondition()));
    }

    public static BeycoContractState getInvalidContractWithInvalidCondition() {
        return new BeycoContractState("1", "1", "2", "1", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), Collections.singletonList(getValidCoffee()),
                Collections.singletonList(getInvalidCondition()));
    }

    public static Addendum getValidAddendum() {
        return new Addendum("1", "1", "2", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), LocalDateTime.of(2021, 05, 17, 10, 00),
                Collections.singletonList(getValidCoffee()), Collections.singletonList(getValidCondition()));
    }

    public static Addendum getInvalidAddendumWithEmptyIdField() {
        return new Addendum("", "1", "2", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), LocalDateTime.of(2021, 05, 17, 10, 00),
                Collections.singletonList(getValidCoffee()), Collections.singletonList(getValidCondition()));
    }

    public static Addendum getInvalidAddendumWithSignedTimeAfterCurrentTime() {
        return new Addendum("1", "1", "2", LocalDateTime.now().plusYears(1),
                LocalDateTime.of(2021, 05, 16, 10, 00), LocalDateTime.of(2021, 05, 17, 10, 00),
                Collections.singletonList(getValidCoffee()), Collections.singletonList(getValidCondition()));
    }

    public static Addendum getInvalidAddendumWithNoCondition() {
        return new Addendum("1", "1", "2", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), LocalDateTime.of(2021, 05, 17, 10, 00),
                Collections.singletonList(getValidCoffee()), Collections.emptyList());
    }

    public static Addendum getInvalidAddendumWithInvalidCoffee() {
        return new Addendum("1", "1", "2", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), LocalDateTime.of(2021, 05, 17, 10, 00),
                Collections.singletonList(getInvalidCoffee()), Collections.singletonList(getValidCondition()));
    }

    public static Addendum getInvalidAddendumWithInvalidCondition() {
        return new Addendum("1", "1", "2", LocalDateTime.of(2021, 05, 15, 10, 00),
                LocalDateTime.of(2021, 05, 16, 10, 00), LocalDateTime.of(2021, 05, 17, 10, 00),
                Collections.singletonList(getValidCoffee()), Collections.singletonList(getInvalidCondition()));
    }

    private static Coffee getValidCoffee() {
        return new Coffee("1", "TestCountry", "TestRegion", true, "TestUnit", 10,
                "TestSpecies", "TestProcess", 10, 16, 9.5, "TestSector", LocalDateTime.of(2021, 03, 12, 10, 10),
                "TestQualitySegment", new String[] {"FirstCertificate", "SecondCertificate"},
                "2");
    }

    private static Coffee getInvalidCoffee() {
        return new Coffee("1", "TestCountry", "TestRegion", true, "TestUnit", 10,
                "TestSpecies", "TestProcess", 10, 500, 9.5, "TestSector", LocalDateTime.of(2021, 03, 12, 10, 10),
                "TestQualitySegment", new String[] {"FirstCertificate", "SecondCertificate"},
                "2");
    }

    private static Condition getValidCondition() {
        return new Condition("1", "TestType", "TestStatus", "TestTitle", "TestValue",
                LocalDateTime.of(2021, 06, 15, 10, 10), "2");
    }

    private static Condition getInvalidCondition() {
        return new Condition("", "TestType", "TestStatus", "TestTitle", "TestValue",
                LocalDateTime.of(2021, 06, 15, 10, 10), "2");
    }

    public static String getContractJson() {
        return "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"sellerId\": \"1\",\n" +
                "  \"buyerId\": \"2\",\n" +
                "  \"offerId\": \"1\",\n" +
                "  \"sellerSignedAt\": \"2021-05-15T10:00\",\n" +
                "  \"buyerSignedAt\": \"2021-05-16T10:00\",\n" +
                "  \"conditions\": [\n" +
                "    {\n" +
                "      \"id\":\"1\",\n" +
                "      \"type\": \"TestType\",\n" +
                "      \"status\": \"TestStatus\",\n" +
                "      \"title\":\"TestTitle\",\n" +
                "      \"value\": \"TestValue\",\n" +
                "      \"createdAt\": \"2021-06-15T10:00\",\n" +
                "      \"negotiationId\": \"2\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"coffees\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"country\": \"TestCountry\",\n" +
                "      \"region\": \"TestRegion\",\n" +
                "      \"unit\": \"TestUnit\",\n" +
                "      \"species\": \"TestSpecies\",\n" +
                "      \"process\": \"TestProcess\",\n" +
                "      \"sector\": \"TestSector\",\n" +
                "      \"harvestAt\": \"2021-05-11T10:00:00\",\n" +
                "      \"qualitySegment\": \"TestQualitySegment\",\n" +
                "      \"parentId\": \"2\",\n" +
                "      \"bulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"minScreenSize\": 10,\n" +
                "      \"maxScreenSize\": 16,\n" +
                "      \"cuppingScore\": 9.5,\n" +
                "      \"certificates\": [\n" +
                "        \"FirstCertificate\", \"SecondCertificate\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getAddendumJson() {
        return "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"sellerId\": \"1\",\n" +
                "    \"buyerId\": \"2\",\n" +
                "    \"createdAt\": \"2021-05-15T10:00\",\n" +
                "    \"buyerSignedAt\": \"2021-05-16T10:00\",\n" +
                "    \"sellerSignedAt\": \"2021-05-17T10:00\",\n" +
                "    \"coffees\": [\n" +
                "        {\n" +
                "          \"id\": \"1\",\n" +
                "          \"country\": \"TestCountry\",\n" +
                "          \"region\": \"TestRegion\",\n" +
                "          \"unit\": \"TestUnit\",\n" +
                "          \"species\": \"TestSpecies\",\n" +
                "          \"process\": \"TestProcess\",\n" +
                "          \"sector\": \"TestSector\",\n" +
                "          \"harvestAt\": \"2021-05-11T10:00:00\",\n" +
                "          \"qualitySegment\": \"TestQualitySegment\",\n" +
                "          \"parentId\": \"2\",\n" +
                "          \"bulk\": true,\n" +
                "          \"quantity\": 10,\n" +
                "          \"minScreenSize\": 10,\n" +
                "          \"maxScreenSize\": 16,\n" +
                "          \"cuppingScore\": 9.5,\n" +
                "          \"certificates\": [\n" +
                "            \"FirstCertificate\", \"SecondCertificate\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "    \"conditions\": [\n" +
                "        {\n" +
                "          \"id\":\"1\",\n" +
                "          \"type\": \"TestType\",\n" +
                "          \"status\": \"TestStatus\",\n" +
                "          \"title\":\"TestTitle\",\n" +
                "          \"value\": \"TestValue\",\n" +
                "          \"createdAt\": \"2021-06-15T10:00\",\n" +
                "          \"negotiationId\": \"2\"\n" +
                "        }\n" +
                "      ]\n" +
                "}";
    }
}
