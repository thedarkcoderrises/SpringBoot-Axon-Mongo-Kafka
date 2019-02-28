package tdcr.axon.config;

import com.mongodb.MongoClient;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    private static Logger LOG = LoggerFactory.getLogger(MongoConfig.class);

    @Bean
    public EventStorageEngine eventStore(MongoClient client) {
        DefaultMongoTemplate mongoTemplate =
                new DefaultMongoTemplate(client,"thedarkcoderrises",
                        "domainevents","snapshotevents");
        MongoEventStorageEngine storageEngine= new MongoEventStorageEngine(mongoTemplate);
        LOG.info("MongoDB initialized");
        return storageEngine;
    }
}
