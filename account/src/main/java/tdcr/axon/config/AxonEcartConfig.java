package tdcr.axon.config;

import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AxonEcartConfig {
//    @Bean
    public EventStorageEngine storageEngine() {
        return new InMemoryEventStorageEngine();
    }
}