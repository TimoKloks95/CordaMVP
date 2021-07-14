package nl.beyco.webserver;

import nl.beyco.webserver.dto.Coffee;
import nl.beyco.webserver.dto.Condition;
import nl.beyco.webserver.dto.Contract;

import java.time.LocalDateTime;
import java.util.*;

public class TestData {
    private static Random random;
    private static final int UPPER_LIMIT = 10000;
    private static final String TEST_SELLER_ID = "1";
    private static final String TEST_BUYER_ID = "2";
    private static final LocalDateTime TEST_SELLER_SIGNED_CONTRACT = LocalDateTime.of(2021, 06, 10, 10, 00);
    private static final LocalDateTime TEST_BUYER_SIGNED_CONTRACT = LocalDateTime.of(2021, 06, 11, 10, 00);

    public static List<Contract> generateNTestContracts(int n) {
        ArrayList<Contract> contracts = new ArrayList<>();
        for(int i = 1; i < n + 1; i++) {
            contracts.add(getTestContract(Integer.toString(i)));
        }
        return contracts;
    }

    public static Contract getTestContract(String contractId) {
        random = new Random();
        Condition condition1 = new Condition(Integer.toString(random.nextInt(UPPER_LIMIT)), "testType", "testStatus", "testTitle", "testValue", LocalDateTime.of(2021, 05, 11, 10, 00), Integer.toString(random.nextInt(UPPER_LIMIT)));
        Condition condition2 = new Condition(Integer.toString(random.nextInt(UPPER_LIMIT)), "testType", "testStatus", "testTitle", "testValue", LocalDateTime.of(2021, 05, 11, 10, 00), Integer.toString(random.nextInt(UPPER_LIMIT)));
        ArrayList<Condition> conditions = new ArrayList<>(
                   Arrays.asList(condition1, condition2));
        ArrayList<Coffee> coffees = new ArrayList<>(
                Collections.singletonList(
                        new Coffee(Integer.toString(random.nextInt(UPPER_LIMIT)), "testCountry", "testRegion", "testUnit", "testSpecies", "testProcess", "testSector", LocalDateTime.of(2021, 05, 11, 10, 00),
                                "testQualitySegment", Integer.toString(random.nextInt(UPPER_LIMIT)), true, 10, 9, 16, 9.5, new String[] {"FirstCertificate", "SecondCertificate"})
                ));
        return new Contract(contractId, TEST_SELLER_ID, TEST_BUYER_ID, Integer.toString(random.nextInt(UPPER_LIMIT)), TEST_SELLER_SIGNED_CONTRACT, TEST_BUYER_SIGNED_CONTRACT, conditions, coffees);
    }

    public static String getContractJsonString() {
        return "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"sellerId\": \"1\",\n" +
                "  \"buyerId\": \"2\",\n" +
                "  \"offerId\": \"1\",\n" +
                "  \"sellerSignedAt\": \"2021-05-11T10:00\",\n" +
                "  \"buyerSignedAt\": \"2021-05-11T10:00\",\n" +
                "  \"conditions\": [\n" +
                "    {\n" +
                "      \"id\":\"1\",\n" +
                "      \"type\": \"test\",\n" +
                "      \"status\": \"test\",\n" +
                "      \"title\":\"test\",\n" +
                "      \"value\": \"test\",\n" +
                "      \"createdAt\": \"2021-05-11T10:00\",\n" +
                "      \"negotiationId\": \"1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"coffees\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"country\": \"test\",\n" +
                "      \"region\": \"test\",\n" +
                "      \"unit\": \"test\",\n" +
                "      \"species\": \"test\",\n" +
                "      \"process\": \"test\",\n" +
                "      \"sector\": \"test\",\n" +
                "      \"harvestAt\": \"2021-05-11T10:00\",\n" +
                "      \"qualitySegment\": \"test\",\n" +
                "      \"minScreenSize\": 9,\n" +
                "      \"maxScreenSize\": 16,\n" +
                "      \"parentId\": \"2\",\n" +
                "      \"bulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"cuppingScore\": 9.5,\n" +
                "      \"certificates\": [\n" +
                "        \"test\", \"test\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static Contract getContractForTestJson() {
        ArrayList<Condition> conditions = new ArrayList<>(
                Collections.singletonList(
                        new Condition("1", "test", "test", "test", "test", LocalDateTime.of(2021, 05, 11, 10, 00), "1")
                ));
        ArrayList<Coffee> coffees = new ArrayList<>(
                Collections.singletonList(
                        new Coffee("1", "test", "test", "test", "test", "test", "test", LocalDateTime.of(2021, 05, 11, 10, 00),
                                "test", "2", true, 10, 9, 16, 9.5, new String[] {"test", "test"})
                ));
        return new Contract("1", "1", "2", "1", LocalDateTime.of(2021, 05, 11, 10, 00), LocalDateTime.of(2021, 05, 11, 10, 00), conditions, coffees);
    }
}
