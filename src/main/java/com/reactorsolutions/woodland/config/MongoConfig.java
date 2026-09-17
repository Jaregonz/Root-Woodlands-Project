package com.reactorsolutions.woodland.config;

import com.reactorsolutions.woodland.model.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;

import java.util.Locale;

@Configuration
public class MongoConfig implements BeforeConvertCallback<Player> {

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Override
    public Player onBeforeConvert(Player entity, String collection) {
        if (entity.getAlias() != null) {
            entity.setAliasNormalized(entity.getAlias().toLowerCase(Locale.ROOT).trim());
        } else {
            entity.setAliasNormalized(null);
        }
        return entity;
    }
}
