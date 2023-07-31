package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Short> {
}
