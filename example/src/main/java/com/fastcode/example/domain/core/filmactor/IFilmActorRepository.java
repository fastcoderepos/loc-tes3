package com.fastcode.example.domain.core.filmactor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("filmActorRepository")
public interface IFilmActorRepository extends JpaRepository<FilmActorEntity, FilmActorId>,QuerydslPredicateExecutor<FilmActorEntity> {

}

