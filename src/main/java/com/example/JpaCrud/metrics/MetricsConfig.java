package com.example.JpaCrud.metrics;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
public class MetricsConfig {
    private final Counter userCreatedCounter;
    private final Counter userDeletedCounter;

    public MetricsConfig(MeterRegistry meterRegistry) {
        userCreatedCounter = Counter.builder("custom.users.created")
                .description("Number of users created")
                .register(meterRegistry);

        userDeletedCounter = Counter.builder("custom.users.deleted")
                .description("Number of users deleted")
                .register(meterRegistry);
    }

    public void incrementUserCreated() {
        userCreatedCounter.increment();
    }

    public void incrementUserDeleted() {
        userDeletedCounter.increment();
    }
}
