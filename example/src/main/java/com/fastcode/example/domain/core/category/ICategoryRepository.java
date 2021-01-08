package com.fastcode.example.domain.core.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("categoryRepository")
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Integer>,QuerydslPredicateExecutor<CategoryEntity> {

}

