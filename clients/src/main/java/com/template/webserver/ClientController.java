package com.template.webserver;

import com.template.webserver.services.RPCService;
import net.corda.core.messaging.CordaRPCOps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ClientController {
    private final CordaRPCOps proxy;
    private final static Logger logger = LoggerFactory.getLogger(ClientController.class);

    public ClientController(RPCService rpc) {
        this.proxy = rpc.getProxy();
    }

    @GetMapping(value = "/templateendpoint", produces = "text/plain")
    private String templateendpoint() {
        return "Define an endpoint here.";
    }
}