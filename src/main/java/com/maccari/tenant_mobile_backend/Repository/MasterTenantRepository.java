package com.maccari.tenant_mobile_backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maccari.tenant_mobile_backend.Model.MasterTenant;

@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Long>{

    @Query("select m from MasterTenant m where m.tenantId like concat('%', :tenantId, '%')")
    public List<MasterTenant> findAllByTenantId(String tenantId);

    @Modifying
    @Query("update MasterTenant m set m.ativo = false where m.id = :id")
    public void disableTenantById(Long id);

    @Modifying
    @Query("update MasterTenant m set m.ativo = true where m.id = :id")
    public void enableTenantById(Long id);
    
}
