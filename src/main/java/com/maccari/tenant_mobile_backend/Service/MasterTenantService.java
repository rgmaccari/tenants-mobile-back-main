package com.maccari.tenant_mobile_backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maccari.tenant_mobile_backend.Model.MasterTenant;
import com.maccari.tenant_mobile_backend.Repository.MasterTenantRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class MasterTenantService {
    @Autowired
    private MasterTenantRepository repository;

    public MasterTenant insert(MasterTenant masterTenant){
        return repository.save(masterTenant);
    }

    @Transactional
    public MasterTenant update(MasterTenant masterTenant){
        if(!repository.existsById(masterTenant.getId())){
            throw new EntityNotFoundException("Tenant " + masterTenant.getTenantId() + " não localizado.");
        }

        return repository.save(masterTenant);
    }

    public MasterTenant findById(Long id){
        Optional<MasterTenant> masterTenant = repository.findById(id);

        if(masterTenant.isEmpty()){
            throw new EntityNotFoundException("Tenant com o " + id + " não localizado.");
        }

        return masterTenant.get();
    }

    public List<MasterTenant> findAll(String tenantId){
        List<MasterTenant> tenantList;

        if(tenantId == null || tenantId.isBlank()){
            tenantList = repository.findAll();
        }else{
            tenantList = repository.findAllByTenantId(tenantId);
        }

        return tenantList;
    }

    @Transactional
    public void disableTenantById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Tenant com o id " + id + " não localizado.");
        }

        repository.disableTenantById(id);
    }

    @Transactional
    public void enableTenantById(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Tenant com o id " + id + " não localizado.");
        }

        repository.enableTenantById(id);
    }


    
}
