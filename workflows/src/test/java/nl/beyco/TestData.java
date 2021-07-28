package nl.beyco;

public class TestData {
    public static String getContractJson() {
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
                "      \"parentId\": \"2\",\n" +
                "      \"bulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"minScreenSize\": 9,\n" +
                "      \"maxScreenSize\": 16,\n" +
                "      \"cuppingScore\": 9.5,\n" +
                "      \"certificates\": [\n" +
                "        \"test\", \"test\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String getInvalidJson() {
        return "This JSON is invalid, because it doesn't match any object.";
    }

    public static String getAddendumJson() {
        return "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"sellerId\": \"1\",\n" +
                "    \"buyerId\": \"2\",\n" +
                "    \"createdAt\": \"2021-06-15T10:00\",\n" +
                "    \"buyerSignedAt\": \"2021-06-14T10:00\",\n" +
                "    \"sellerSignedAt\": \"2021-06-13T10:00\",\n" +
                "    \"coffees\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"country\": \"test\",\n" +
                "      \"region\": \"test\",\n" +
                "      \"unit\": \"test\",\n" +
                "      \"species\": \"test\",\n" +
                "      \"process\": \"test\",\n" +
                "      \"sector\": \"test\",\n" +
                "      \"harvestAt\": \"2021-05-11T10:00:00\",\n" +
                "      \"qualitySegment\": \"test\",\n" +
                "      \"parentId\": \"2\",\n" +
                "      \"bulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"minScreenSize\": 9,\n" +
                "      \"maxScreenSize\": 16,\n" +
                "      \"cuppingScore\": 9.5,\n" +
                "      \"certificates\": [\n" +
                "        \"test\", \"test\"\n" +
                "      ]\n" +
                "    }\n" +
                "    ],\n" +
                "    \"conditions\": [\n" +
                "        {\n" +
                "            \"id\": \"3\",\n" +
                "            \"type\": \"test\",\n" +
                "            \"status\": \"test\",\n" +
                "            \"title\": \"test\",\n" +
                "            \"value\": \"test\",\n" +
                "            \"createdAt\": \"2021-06-13T10:00\",\n" +
                "            \"negotiationId\": \"3\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public static String getSecondAddendumJson() {
        return "{\n" +
                "    \"id\": \"2\",\n" +
                "    \"sellerId\": \"1\",\n" +
                "    \"buyerId\": \"2\",\n" +
                "    \"createdAt\": \"2021-06-15T10:00\",\n" +
                "    \"buyerSignedAt\": \"2021-06-14T10:00\",\n" +
                "    \"sellerSignedAt\": \"2021-06-13T10:00\",\n" +
                "    \"coffees\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"country\": \"test\",\n" +
                "      \"region\": \"test\",\n" +
                "      \"unit\": \"test\",\n" +
                "      \"species\": \"test\",\n" +
                "      \"process\": \"test\",\n" +
                "      \"sector\": \"test\",\n" +
                "      \"harvestAt\": \"2021-05-11T10:00:00\",\n" +
                "      \"qualitySegment\": \"test\",\n" +
                "      \"parentId\": \"2\",\n" +
                "      \"bulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"minScreenSize\": 9,\n" +
                "      \"maxScreenSize\": 16,\n" +
                "      \"cuppingScore\": 9.5,\n" +
                "      \"certificates\": [\n" +
                "        \"test\", \"test\"\n" +
                "      ]\n" +
                "    }\n" +
                "    ],\n" +
                "    \"conditions\": [\n" +
                "        {\n" +
                "            \"id\": \"3\",\n" +
                "            \"type\": \"test\",\n" +
                "            \"status\": \"test\",\n" +
                "            \"title\": \"test\",\n" +
                "            \"value\": \"test\",\n" +
                "            \"createdAt\": \"2021-06-13T10:00\",\n" +
                "            \"negotiationId\": \"3\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public static String getAddendumJsonWithDifferentParticipants() {
        return "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"sellerId\": \"500\",\n" +
                "    \"buyerId\": \"2\",\n" +
                "    \"createdAt\": \"2021-06-15T10:00\",\n" +
                "    \"buyerSignedAt\": \"2021-06-14T10:00\",\n" +
                "    \"sellerSignedAt\": \"2021-06-13T10:00\",\n" +
                "    \"coffees\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"country\": \"test\",\n" +
                "      \"region\": \"test\",\n" +
                "      \"unit\": \"test\",\n" +
                "      \"species\": \"test\",\n" +
                "      \"process\": \"test\",\n" +
                "      \"sector\": \"test\",\n" +
                "      \"harvestAt\": \"2021-05-11T10:00:00\",\n" +
                "      \"qualitySegment\": \"test\",\n" +
                "      \"parentId\": \"2\",\n" +
                "      \"bulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"minScreenSize\": 9,\n" +
                "      \"maxScreenSize\": 16,\n" +
                "      \"cuppingScore\": 9.5,\n" +
                "      \"certificates\": [\n" +
                "        \"test\", \"test\"\n" +
                "      ]\n" +
                "    }\n" +
                "    ],\n" +
                "    \"conditions\": [\n" +
                "        {\n" +
                "            \"id\": \"3\",\n" +
                "            \"type\": \"test\",\n" +
                "            \"status\": \"test\",\n" +
                "            \"title\": \"test\",\n" +
                "            \"value\": \"test\",\n" +
                "            \"createdAt\": \"2021-06-13T10:00\",\n" +
                "            \"negotiationId\": \"3\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public static String getAddendumJsonWithSignedTimeBeforeContract() {
        return "{\n" +
                "    \"id\": \"1\",\n" +
                "    \"sellerId\": \"1\",\n" +
                "    \"buyerId\": \"2\",\n" +
                "    \"createdAt\": \"2021-06-15T10:00\",\n" +
                "    \"buyerSignedAt\": \"2021-06-14T10:00\",\n" +
                "    \"sellerSignedAt\": \"1900-06-13T10:00\",\n" +
                "    \"coffees\": [\n" +
                "    {\n" +
                "      \"id\": \"1\",\n" +
                "      \"country\": \"test\",\n" +
                "      \"region\": \"test\",\n" +
                "      \"unit\": \"test\",\n" +
                "      \"species\": \"test\",\n" +
                "      \"process\": \"test\",\n" +
                "      \"sector\": \"test\",\n" +
                "      \"harvestAt\": \"2021-05-11T10:00:00\",\n" +
                "      \"qualitySegment\": \"test\",\n" +
                "      \"parentId\": \"2\",\n" +
                "      \"bulk\": true,\n" +
                "      \"quantity\": 10,\n" +
                "      \"minScreenSize\": 9,\n" +
                "      \"maxScreenSize\": 16,\n" +
                "      \"cuppingScore\": 9.5,\n" +
                "      \"certificates\": [\n" +
                "        \"test\", \"test\"\n" +
                "      ]\n" +
                "    }\n" +
                "    ],\n" +
                "    \"conditions\": [\n" +
                "        {\n" +
                "            \"id\": \"3\",\n" +
                "            \"type\": \"test\",\n" +
                "            \"status\": \"test\",\n" +
                "            \"title\": \"test\",\n" +
                "            \"value\": \"test\",\n" +
                "            \"createdAt\": \"2021-06-13T10:00\",\n" +
                "            \"negotiationId\": \"3\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}
