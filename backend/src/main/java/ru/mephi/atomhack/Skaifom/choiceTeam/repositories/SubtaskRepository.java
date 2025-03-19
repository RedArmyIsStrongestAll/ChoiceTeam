package ru.mephi.atomhack.Skaifom.choiceTeam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.SubtaskEntity;

import java.util.List;

@Repository
public interface SubtaskRepository extends JpaRepository<SubtaskEntity, Long> {
    List<SubtaskEntity> findByIdTask(Long idTask);
}
