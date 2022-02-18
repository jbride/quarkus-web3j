package io.quarkus.web3j.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.arc.processor.DotNames;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.IndexDependencyBuildItem;
import io.quarkus.deployment.builditem.nativeimage.RuntimeInitializedClassBuildItem;
import io.quarkus.web3j.runtime.Web3jClientProducer;
import io.quarkus.web3j.runtime.Web3jConfiguration;
import io.quarkus.web3j.runtime.Web3jRecorder;

class QuarkusWeb3jProcessor {

    private static final String FEATURE = "quarkus-web3j";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    IndexDependencyBuildItem indexExternalDependency() {
        return new IndexDependencyBuildItem("org.web3j", "core");
    }

    @BuildStep
    public void registerRuntimeInitializedClasses(BuildProducer<RuntimeInitializedClassBuildItem> producer) {
        producer.produce(new RuntimeInitializedClassBuildItem("org.web3j.protocol.Web3j"));
    }

    @BuildStep
    void declareExtensionCDIBeans(CombinedIndexBuildItem index, 
                BuildProducer<AdditionalBeanBuildItem> additionalBeans) {
        
        additionalBeans.produce(
            new AdditionalBeanBuildItem.Builder()
                .addBeanClass(Web3jClientProducer.class)
                .setUnremovable()
                .setDefaultScope(DotNames.APPLICATION_SCOPED)
                .build()
        );
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void configureProducer(Web3jRecorder recorder, 
                    BeanContainerBuildItem beanContainerBuildItem,
                    Web3jConfiguration configuration) {
            recorder.update(beanContainerBuildItem.getValue(), configuration);
    }

}
