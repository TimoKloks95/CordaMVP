> #CordaMVP
> ## Description
>
> Blockchain application written in Corda version 4.8 (LTS). The purpose of this application is to save contracts, get contracts and add addenda to contracts within the Beyco coffee platform. This software consists out of the following components:
>
>> ## A client written in Spring boot 2.0.2
>> - The Beyco back end will call the client via HTTP to interact with the blockchain
>> - The client connects to the corda blockchain via RPC
>
>> ## A corda blockchain 
>> - The corda blockchain consists out of one peer node and one notary node
>> - The peer node runs the beyco cordapp
>> - The beyco cordapp defines:
>>	- States: facts that will be saved in the blockchain 
>>	- Contracts: rules that will be checked on the added states
>>	- Flows: sequence of steps to complete an interaction with the blockchain
>> - The notary node checks if transaction states comply with contract rules 
>
> ## Requirements
> To run this application the following things have to be installed on your Windows machine:
> - Java JDK 8 
> - IntelliJ IDEA 
> - Git
> - Gradle version 5.6.4
>
> ## Deployment
> - After the project is opened in IntelliJ the nodes with the cordapps have to be build by executing: "gradlew.bat deployNodes" (Windows)
> - The nodes can in turn be run by executing: "runnodes.bat" in the build\nodes folder.
> - The client can be started by running the Starter class found in the clients folder. The port can be defined in the resoures/application.properties file.  
> 
> ## Tests
> The tests are run with JUnit 4.2
> For the flow tests the Quasar agent is used. Add flag: "-javaagent:../lib/quasar.jar" to the run config of the class. And make sure that the quasar.jar is added to the lib folder in the build.gradle file.