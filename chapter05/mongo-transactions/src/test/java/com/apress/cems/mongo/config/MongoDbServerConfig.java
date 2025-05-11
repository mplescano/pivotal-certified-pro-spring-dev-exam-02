package com.apress.cems.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MongoDbServerConfig {

    @Value("${db.host}")
    private String host;

    @Value("${db.port}")
    private Integer port;

    @Bean(destroyMethod = "stop")
    public MongodExecutable mongodExecutable1() throws IOException {
        Storage storage = new Storage(null, "rs0", 0);
        ImmutableMongodConfig mongodConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .replication(storage)
                .cmdOptions(ImmutableMongoCmdOptions.builder().useNoJournal(false).build())
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();

        MongodExecutable executable = starter.prepare(mongodConfig);
        executable.start();

        try (MongoClient client = MongoClients.create("mongodb://" + host + ":" + port)) {
            client.getDatabase("admin").runCommand(new Document("replSetInitiate", new Document()));
        }

        return executable;
    }
}
