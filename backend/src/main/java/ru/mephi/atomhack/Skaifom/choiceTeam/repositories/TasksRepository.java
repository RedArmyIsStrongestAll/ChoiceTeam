package ru.mephi.atomhack.Skaifom.choiceTeam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.Task;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {
    List<Task> findByExpeditionId(Integer idExpedition);
}
