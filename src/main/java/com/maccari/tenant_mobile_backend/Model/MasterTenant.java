package com.maccari.tenant_mobile_backend.Model;

import com.maccari.tenant_mobile_backend.DTO.MasterTenantDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "master_tenant", schema = "externo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterTenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password", length = 30)
    private String password;
    
    @Column(name = "tenant_id", length = 30)
    private String tenantId;
    
    @Column(name = "url", length = 256)
    private String url;
    
    @Column(name = "username", length = 30)
    private String username;
    
    @Column(name = "empresa", length = 70)
    private String empresa;
    
    @Column(name = "ativo", columnDefinition="boolean default true")
    private Boolean ativo;

    public MasterTenant(MasterTenantDTO dto){
        this.password = dto.getPassword();
        this.tenantId = dto.getTenantId();
        this.url = dto.getUrl();
        this.username = dto.getUsername();
        this.empresa = dto.getEmpresa();
        this.ativo = true;
    }

    public MasterTenant(Long id, MasterTenantDTO dto){
        this.id = id;
        this.password = dto.getPassword();
        this.tenantId = dto.getTenantId();
        this.url = dto.getUrl();
        this.username = dto.getUsername();
        this.empresa = dto.getEmpresa();
        this.ativo = true;
    }

}
