package com.challenge.darien.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor(){
        return Optional.of("Adminstrator");
    }
}
