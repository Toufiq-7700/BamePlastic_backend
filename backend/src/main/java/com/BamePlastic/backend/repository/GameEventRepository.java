package com.BamePlastic.backend.repository;

import com.BamePlastic.backend.entity.GameEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GameEventRepository extends JpaRepository<GameEvent, Long> {

    List<GameEvent> findByGameSessionId(Long sessionId);

    List<GameEvent> findByGameSessionIdAndResolvedFalse(Long sessionId);
}
