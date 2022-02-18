package io.quarkus.web3j.runtime;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(phase = ConfigPhase.RUN_TIME)
public class Web3jConfiguration {
	/**
	 * Web3j URL to use.
	 */
	@ConfigItem
	public String url;
}
