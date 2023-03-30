package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.StorePanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorePanelRepository extends JpaRepository<StorePanel, Byte>, JpaSpecificationExecutor<StorePanel> {
    Optional<StorePanel> findByCode(String code);
}
