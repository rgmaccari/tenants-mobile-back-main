package com.maccari.tenant_mobile_backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterTenantDTO {
    @NotBlank
    private String password;

    @NotBlank
    private String tenantId;

    @NotBlank
    private String url;
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String empresa;
    
    //@NotNull
    private Boolean ativo;
}
