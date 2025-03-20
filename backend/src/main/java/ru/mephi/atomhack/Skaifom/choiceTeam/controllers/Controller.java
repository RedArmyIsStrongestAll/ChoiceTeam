package ru.mephi.atomhack.Skaifom.choiceTeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.Expedition;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.Hero;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.SubTask;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.Task;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.ExpeditionService;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.TeamService;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private ExpeditionService expeditionService;

    @Autowired
    private TeamService teamService;

    @GetMapping("/")
    public void test() {
        expeditionService.getAllExpeditions().get(0).getTasks();
        System.out.println("asf");
        System.out.println("asf");
        System.out.println("asf");
    }

    @PostMapping("/expedition")
    public Integer addExpedition() {
        return expeditionService.addExpedition();
    }

    @GetMapping("/expeditions")
    public List<Expedition> getAllExpeditions() {
        return expeditionService.getAllExpeditions();
    }

    @GetMapping("/expedition/tasks")
    public List<Task> getAllTasks(@RequestParam Integer idExpedition) {
        return expeditionService.getExpedition(idExpedition);
    }

    @GetMapping("/expedition/task/subtasks")
    public List<SubTask> getSubtasks(@RequestParam Integer idTask) {
        return expeditionService.getSubtasks(idTask);
    }

    @PostMapping("/expedition/task")
    public Integer addTask(@RequestBody Task taskDTO) {
        return expeditionService.addTask(taskDTO);
    }

    @GetMapping("/library")
    public List<SubTask> getLibrary() {
        return expeditionService.getLibrary();
    }

    @PostMapping("/library/subtasks")
    public Integer addSubtask(@RequestParam Integer idTask, @RequestBody SubTask subtaskDTO) {
        return expeditionService.addSubtask(idTask, subtaskDTO);
    }

    @DeleteMapping("/library/subtasks")
    public void deleteSubtask(@RequestParam Integer idSubtask) {
        expeditionService.deleteSubtask(idSubtask);
    }

    @DeleteMapping("/expedition/task")
    public void deleteTask(@RequestParam Integer idTask) {
        expeditionService.deleteTask(idTask);
    }

    @DeleteMapping("/expedition")
    public void deleteExpedition(@RequestParam Integer idExpedition) {
        expeditionService.deleteExpedition(idExpedition);
    }

    @GetMapping("/createTeam")
    public List<Hero> createTeam(@RequestParam Integer idExpedition)  {
        return teamService.createTeam();
    }
}
