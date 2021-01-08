package com.fastcode.example.domain.core.customuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("customUserRepository")
public interface ICustomUserRepository extends JpaRepository<CustomUserEntity, CustomUserId>,QuerydslPredicateExecutor<CustomUserEntity> {

}

