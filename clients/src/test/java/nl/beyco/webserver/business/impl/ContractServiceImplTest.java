package nl.beyco.webserver.business.impl;

import static org.mockito.ArgumentMatchers.any;
//
//@RunWith(SpringRunner.class)
//public class ContractServiceImplTest {
//    @TestConfiguration
//    static class ContractServiceImplTestContextConfiguration {
//        @Bean
//        public ContractService contractService() {
//            return new ContractServiceImpl();
//        }
//    }
//
//    @Autowired
//    private ContractService contractService;
//
//    @MockBean
//    private RPCService rpcService;
//
//    @Test
//    public void opslaanContract() throws Exception {
//        //Arrange
//        Contract testContract = new Contract("1", "1", "2", "1", "12 mei 2021",
//                "12 mei 2021", null, null, null);
//        CordaRPCOps testProxy = mock(CordaRPCOps.class);
//        when(rpcService.getProxy()).thenReturn(testProxy);
//        when(testProxy.startTrackedFlowDynamic(OpslaanContractFlow.class, isA(String.class), isA(Contract.class)))
//        .thenReturn(null);
//
//        //Act
//        contractService.opslaanContract("1", testContract);
//
//        //Assert
//        verify(rpcService).getProxy();
//        verify(testProxy).startTrackedFlowDynamic(OpslaanContractFlow.class, isA(String.class), isA(Contract.class));

//    }
//
//    @Test
//    public void ophalenContract() {
//        //Arrange
//
//        //Act
//
//        //Assert
//    }
//
//    @Test
//    public void toevoegenAddendum() {
//        //Arrange
//        CordaRPCOps testProxy = mock(CordaRPCOps.class);
//
//        //Act
//
//        //Assert
//    }
//}