package ru.he.components.resolvers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeResolverLocalDateTimeBased implements TimeResolverLocalDateTime {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
