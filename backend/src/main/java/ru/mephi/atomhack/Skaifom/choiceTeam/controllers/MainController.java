package ru.mephi.atomhack.Skaifom.choiceTeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.*;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.ExpeditionService;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.TeamService;

import java.util.List;

@RestController
public class MainController {

    //todo всё через интерфейсы

    @Autowired
    private ExpeditionService expeditionService;

    @Autowired
    private TeamService teamService;

    //todo тестирование всех методов

    @PostMapping("/expedition")
    public ExpeditionDTO addExpedition(@RequestParam String name, @RequestParam String description) {
        return expeditionService.addExpedition(name, description);
    }

    @GetMapping("/expeditions")
    public List<Integer> getAllExpeditionIds() {
        return expeditionService.getAllExpeditionIds();
    }

    @GetMapping("/expedition/tasks")
    public List<TaskDTO> getAllTasks(@RequestParam int idExpedition) {
        return expeditionService.getExpeditionTasks(idExpedition);
    }

    @GetMapping("/expedition/task/subtasks")
    public List<SubTaskDTO> getSubtasks(@RequestParam int idTask) {
        return expeditionService.getSubtasks(idTask);
    }

    @GetMapping("/backlog")
    public List<BacklogDTO> getBacklog() {
        return expeditionService.getBacklog();
    }

    @PostMapping("/expedition/task/subtask")
    public SubTaskDTO addSubtask(@RequestParam int idTask, @RequestParam int backlogId) {
        return expeditionService.addSubtask(idTask, backlogId);
    }

    @PostMapping("/expedition/task")
    public TaskDTO addTask(@RequestParam int idExpedition, @RequestParam String name, @RequestParam String description) {
        return expeditionService.addTaskToExpedition(idExpedition, name, description);
    }

    @DeleteMapping("/expedition/task/subtask")
    public void deleteSubtask(@RequestParam int idTask, @RequestParam int idSubtask) {
        expeditionService.deleteSubtask(idTask, idSubtask);
    }

    @DeleteMapping("/expedition/task")
    public void deleteTask(@RequestParam int idTask) {
        expeditionService.deleteTask(idTask);
    }

    @DeleteMapping("/expedition")
    public void deleteExpedition(@RequestParam int idExpedition) {
        expeditionService.deleteExpedition(idExpedition);
    }

    @GetMapping("/createTeam")
    public List<HeroDTO> createTeam(@RequestParam int idExpedition) {
        //todo тут алгоритм
        return teamService.createTeam();
    }
}
