package io.quarkus.web3j.deployment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.QuarkusUnitTest;
import io.quarkus.web3j.runtime.Web3jConfiguration;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.inject.Inject;

import org.web3j.protocol.Web3j;

public class QuarkusWeb3jTest {

    @RegisterExtension
    static final QuarkusUnitTest config = new QuarkusUnitTest()
        .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class))
        .withConfigurationResource("application.properties");

    @Inject
    Web3jConfiguration web3jConfig;

    @Inject
    Web3j web3j;

    @Test
    public void checkUrlIsLoaded() {
        assertEquals(web3jConfig.url, "http://localhost:8545");
    }

    @Test
    public void checkWeb3jExists() {
        assertNotNull(web3j);
    }

}
