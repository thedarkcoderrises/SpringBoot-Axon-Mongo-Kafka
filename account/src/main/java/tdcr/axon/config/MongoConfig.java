package tdcr.axon.config;

import com.mongodb.MongoClient;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public EventStorageEngine eventStore(MongoClient client) {
        DefaultMongoTemplate mongoTemplate =
                new DefaultMongoTemplate(client,"thedarkcoderrises",
                        "domainevents","snapshotevents");
        return new MongoEventStorageEngine(mongoTemplate);
    }
}
