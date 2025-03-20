package ru.mephi.atomhack.Skaifom.choiceTeam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.SubTask;

import java.util.List;

@Repository
public interface SubTasksRepository extends JpaRepository<SubTask, Integer> {
    List<SubTask> findByTaskId(Integer idTask);
}
