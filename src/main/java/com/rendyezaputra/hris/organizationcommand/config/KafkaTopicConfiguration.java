package com.rendyezaputra.hris.organizationcommand.config;

import com.rendyezaputra.hris.hriswebresources.event.jobfunction.*;
import com.rendyezaputra.hris.hriswebresources.event.team.*;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfiguration {

    private Map<String, String> getConfigs() {
        Map<String, String> configs = new HashMap<>();

        configs.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT);
        configs.put(TopicConfig.RETENTION_MS_CONFIG, "604800000");
        configs.put(TopicConfig.RETENTION_BYTES_CONFIG, "-1");
        configs.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "2097164");

        return configs;
    }

    @Bean
    public NewTopic jobFunctionCreatedEvent() {
        return TopicBuilder.name(JobFunctionCreatedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }

    @Bean
    public NewTopic jobFunctionUpdatedEvent() {
        return TopicBuilder.name(JobFunctionUpdatedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }

    @Bean
    public NewTopic jobFunctionActivatedEvent() {
        return TopicBuilder.name(JobFunctionActivatedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }

    @Bean
    public NewTopic jobFunctionDeactivatedEvent() {
        return TopicBuilder.name(JobFunctionDeactivatedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }

    @Bean
    public NewTopic jobFunctionDeletedEvent() {
        return TopicBuilder.name(JobFunctionDeletedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }

    @Bean
    public NewTopic teamCreatedEvent() {
        return TopicBuilder.name(TeamCreatedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }

    @Bean
    public NewTopic teamUpdatedEvent() {
        return TopicBuilder.name(TeamUpdatedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }

    @Bean
    public NewTopic teamActivatedEvent() {
        return TopicBuilder.name(TeamActivatedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }

    @Bean
    public NewTopic teamDeactivatedEvent() {
        return TopicBuilder.name(TeamDeactivatedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }

    @Bean
    public NewTopic teamDeletedEvent() {
        return TopicBuilder.name(TeamDeletedEvent.class.getSimpleName())
                .partitions(3)
                .configs(getConfigs())
                .build();
    }
}
