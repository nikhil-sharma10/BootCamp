package com.bootcampProject.BootcampProject.auditor;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditoAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}
