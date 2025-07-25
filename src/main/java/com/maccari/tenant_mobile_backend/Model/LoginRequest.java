package com.maccari.tenant_mobile_backend.Model;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
