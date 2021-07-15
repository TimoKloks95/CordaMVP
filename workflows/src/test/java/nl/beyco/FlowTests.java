package nl.beyco;

//public class FlowTests {
//    private MockNetwork network;
//    private StartedMockNode a;
//    private StartedMockNode b;
//
//    @Before
//    public void setup() {
//        network = new MockNetwork(new MockNetworkParameters().withCordappsForAllNodes(ImmutableList.of(
//                TestCordapp.findCordapp("nl.beyco.contracts"),
//                TestCordapp.findCordapp("nl.beyco.flows"))));
//        a = network.createPartyNode(null);
//        b = network.createPartyNode(null);
//        network.runNetwork();
//    }
//
//    @After
//    public void tearDown() {
//        network.stopNodes();
//    }
//
//    @Test
//    public void dummyTest() {
//        Initiator flow = new Initiator(b.getInfo().getLegalIdentities().get(0));
//        Future<SignedTransaction> future = a.startFlow(flow);
//        network.runNetwork();
//
//        //successful query means the state is stored at node b's vault. Flow went through.
//        QueryCriteria inputCriteria = new QueryCriteria.VaultQueryCriteria().withStatus(Vault.StateStatus.UNCONSUMED);
//        TemplateState state = b.getServices().getVaultService()
//                .queryBy(TemplateState.class,inputCriteria).getStates().get(0).getState().getData();
//    }
//}
