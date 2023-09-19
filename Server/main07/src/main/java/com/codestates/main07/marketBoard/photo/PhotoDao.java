package com.codestates.main07.marketBoard.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoDao extends JpaRepository<Photo, Long> {
    void save(PhotoDto photoDto);
}
