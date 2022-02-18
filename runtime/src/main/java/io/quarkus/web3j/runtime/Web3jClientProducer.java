package io.quarkus.web3j.runtime;

import javax.enterprise.inject.Produces;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class Web3jClientProducer {

    private volatile Web3jConfiguration config;

    void initialize(Web3jConfiguration config) {
        this.config = config;
    }

    @Produces
    public Web3j web3j() {
        return Web3j.build(new HttpService(config.url));
    }
}
