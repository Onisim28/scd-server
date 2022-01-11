package com.example.demo.repository;

import com.example.demo.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

    Page<Position> findByUserId(Long userId, Pageable pageable);

    Optional<Position> findByIdAndUserId(Long id, Long userId);

    @Query("select p from Position p INNER join p.user u where u.id = :userId and " +
            "p.creation_date >= :startDateTime and p.creation_date <= :endDateTime")
    List<Position> getPositionsBetweenTwoDates(
            @Param("userId") Long userId,
            @Param("startDateTime") Date startDateTime,
            @Param("endDateTime") Date endDateTime);
}
