package ru.mephi.atomhack.Skaifom.choiceTeam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.Expedition;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.SubTask;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.Task;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.ExpeditionsRepository;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.HeroesRepository;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.SubTasksRepository;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.TasksRepository;

import java.util.List;

@Service
public class ExpeditionService {

    @Autowired
    private ExpeditionsRepository expeditionsRepository;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private SubTasksRepository subTasksRepository;

    @Autowired
    private HeroesRepository heroesRepository;

    public Integer addExpedition() {
        Expedition expedition = new Expedition();
        return expeditionsRepository.save(expedition).getId();
    }

    public List<Expedition> getAllExpeditions() {
        return expeditionsRepository.findAll();
    }

    public List<Task> getExpedition(Integer idExpedition) {
        return tasksRepository.findByExpeditionId(idExpedition);
    }

    public List<SubTask> getSubtasks(Integer idTask) {
        return subTasksRepository.findByTaskId(idTask);
    }

    public Integer addTask(Task task) {
        return tasksRepository.save(task).getId();
    }

    public List<SubTask> getLibrary() {
        return subTasksRepository.findAll();
    }

    public Integer addSubtask(Integer idTask, SubTask subtask) {
        Task task = tasksRepository.findById(idTask).orElseThrow();
        subtask.setTask(task);
        return subTasksRepository.save(subtask).getId();
    }

    public void deleteSubtask(Integer idSubtask) {
        subTasksRepository.deleteById(idSubtask);
    }

    public void deleteTask(Integer idTask) {
        tasksRepository.deleteById(idTask);
    }

    public void deleteExpedition(Integer idExpedition) {
        expeditionsRepository.deleteById(idExpedition);
    }
}

