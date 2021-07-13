package nl.beyco.webserver.business.impl;

//
//@RunWith(SpringRunner.class)
//public class RPCServiceImplTest {
//    @TestConfiguration
//    static class RPCServiceImplTestContextConfiguration {
//
//        @Bean
//        public RPCService rpcService() {
//            RPCService rpcService = mock(RPCServiceImpl.class);
//            CordaRPCClient rpcClient = mock(CordaRPCClient.class);
//            CordaRPCConnection rpcConnection = mock(CordaRPCConnection.class);
//            CordaRPCOps rpcOps = mock(CordaRPCOps.class);
//            rpcService.setRpcClient(rpcClient);
//
//            when(rpcClient.start(isA(String.class), isA(String.class)))
//                    .thenReturn(rpcConnection);
//
//            when(rpcConnection.getProxy()).thenReturn(rpcOps);
//            return rpcService;
//        }
//
//        @Bean
//        public ClientConfiguration clientConfiguration() {
//            ClientConfiguration configuration = mock(ClientConfiguration.class);
//            when(configuration.getHost()).thenReturn("testhost");
//            when(configuration.getPort()).thenReturn("8080");
//            when(configuration.getUsername()).thenReturn("testuser");
//            when(configuration.getPassword()).thenReturn("testpass");
//            return configuration;
//        }
//    }
//
//    @Autowired
//    private RPCService rpcService;
//
//    @Test
//    public void verbindMetBlockchainSucces() {
//        verify(rpcService).connectToBlockchainNode();
//    }
//}