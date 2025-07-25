package com.maccari.tenant_mobile_backend.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.maccari.tenant_mobile_backend.DTO.MasterTenantDTO;
import com.maccari.tenant_mobile_backend.Model.MasterTenant;
import com.maccari.tenant_mobile_backend.Service.MasterTenantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tenants")
public class MasterTenantController {
    @Autowired
    private MasterTenantService service;


    @GetMapping("/findAll")
    public ResponseEntity<List<MasterTenant>> findAll(@RequestParam(value = "tenantId", required = false) String tenantId){
        List<MasterTenant> tenantList = service.findAll(tenantId);
        return ResponseEntity.ok(tenantList);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> disableTenantById(@RequestParam("id") Long id){
        service.disableTenantById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find")
    public ResponseEntity<MasterTenant> findById(@RequestParam("id") Long id){
        MasterTenant masterTenant = service.findById(id);
        return ResponseEntity.ok(masterTenant);
    }

    @PutMapping("{id}")
    public ResponseEntity<MasterTenant> update(@PathVariable Long id, @RequestBody @Valid MasterTenantDTO masterTenantDto){
        MasterTenant masterTenant = new MasterTenant(id, masterTenantDto);
        masterTenant = service.update(masterTenant);
        return ResponseEntity.ok(masterTenant);
    }

    @PostMapping("/")
    public ResponseEntity<MasterTenant> insert(@RequestBody @Valid MasterTenantDTO masterTenantDto, UriComponentsBuilder builder){
        MasterTenant masterTenant = new MasterTenant(masterTenantDto);

        masterTenant = service.insert(masterTenant);

        URI uri = builder.path("/tenants/{id}").buildAndExpand(masterTenant.getId()).toUri();
        return ResponseEntity.created(uri).body(masterTenant);
    }

    @PutMapping("/enable")
    public ResponseEntity<Void> enableTenantById(@RequestParam("id") Long id){
        service.enableTenantById(id);
        return ResponseEntity.noContent().build();
    }
    
}
