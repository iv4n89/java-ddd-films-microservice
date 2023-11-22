package com.films.system.films.infrastructure.repository;

import com.films.system.films.infrastructure.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilmJpaRepository extends JpaRepository<FilmEntity, UUID> {}
