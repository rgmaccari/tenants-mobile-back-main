package com.maccari.tenant_mobile_backend.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class User {
    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;
}
