package io.quarkus.web3j.runtime;

import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class Web3jRecorder {

	public void update(BeanContainer beanContainer, Web3jConfiguration configuration) {
		Web3jClientProducer producer = beanContainer.instance(Web3jClientProducer.class);
		producer.initialize(configuration);
	}

}
