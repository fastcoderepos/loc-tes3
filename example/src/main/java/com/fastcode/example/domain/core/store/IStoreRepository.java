package com.fastcode.example.domain.core.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("storeRepository")
public interface IStoreRepository extends JpaRepository<StoreEntity, Integer>,QuerydslPredicateExecutor<StoreEntity> {

}

