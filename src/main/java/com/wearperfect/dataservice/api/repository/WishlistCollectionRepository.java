package com.wearperfect.dataservice.api.repository;

import com.wearperfect.dataservice.api.entity.WishlistCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revisions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistCollectionRepository extends JpaRepository<WishlistCollection, Long>, RevisionRepository<WishlistCollection, Long, Float> {
    Page<WishlistCollection> findByUserId(Long userId, Pageable pageable);
}
