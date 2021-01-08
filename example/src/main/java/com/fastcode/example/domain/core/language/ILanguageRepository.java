package com.fastcode.example.domain.core.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("languageRepository")
public interface ILanguageRepository extends JpaRepository<LanguageEntity, Integer>,QuerydslPredicateExecutor<LanguageEntity> {

}

