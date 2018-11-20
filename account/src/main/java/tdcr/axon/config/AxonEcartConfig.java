package tdcr.axon.config;

import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tdcr.axon.command.domain.User;

@Configuration
public class AxonEcartConfig {
    @Bean
    public EventStorageEngine storageEngine() {
        return new InMemoryEventStorageEngine();
    }



    @Bean
    public EventSourcingRepository<User> accountRepository(EventStore eventStore) {
        EventSourcingRepository<User> repository = new EventSourcingRepository<User>(User.class, eventStore);
        return repository;
    }
}