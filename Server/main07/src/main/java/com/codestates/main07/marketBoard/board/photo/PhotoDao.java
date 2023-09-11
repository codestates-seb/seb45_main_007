package com.codestates.main07.marketBoard.board.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoDao extends JpaRepository<Photo, Long> {
    public void save(PhotoDto photoDto);
}
