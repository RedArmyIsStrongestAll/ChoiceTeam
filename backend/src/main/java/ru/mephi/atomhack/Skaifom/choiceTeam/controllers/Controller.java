package ru.mephi.atomhack.Skaifom.choiceTeam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mephi.atomSkills25.commandA.choiceGame.entity.*;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.*;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.ExpeditionService;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.GameService;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.PlayerService;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.RatingService;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private ExpeditionService expeditionService;

    @Autowired
    private TeamService teamService;

    @PostMapping("/expedition")
    public Long addExpedition() {
        return expeditionService.addExpedition();
    }

    @GetMapping("/expeditions")
    public List<ExpeditionDTO> getAllExpeditions() {
        return expeditionService.getAllExpeditions();
    }

    @GetMapping("/expedition/tasks")
    public List<TaskDTO> getExpedition(@RequestParam Long idExpedition) {
        return expeditionService.getExpedition(idExpedition);
    }

    @GetMapping("/expedition/task/subtasks")
    public List<SubtaskDTO> getSubtasks(@RequestParam Long idTask) {
        return expeditionService.getSubtasks(idTask);
    }

    @PostMapping("/expedition/task")
    public Long addTask(@RequestBody TaskDTO taskDTO) {
        return expeditionService.addTask(taskDTO);
    }

    @GetMapping("/library")
    public List<SubtaskDTO> getLibrary() {
        return expeditionService.getLibrary();
    }

    @PostMapping("/expedition/task/subtask")
    public Long addSubtask(@RequestParam Long idTask, @RequestBody SubtaskDTO subtaskDTO) {
        return expeditionService.addSubtask(idTask, subtaskDTO);
    }

    @DeleteMapping("/expedition/task/subtask")
    public void deleteSubtask(@RequestParam Long idSubtask) {
        expeditionService.deleteSubtask(idSubtask);
    }

    @DeleteMapping("/expedition/task")
    public void deleteTask(@RequestParam Long idTask) {
        expeditionService.deleteTask(idTask);
    }

    @DeleteMapping("/expedition")
    public void deleteExpedition(@RequestParam Long idExpedition) {
        expeditionService.deleteExpedition(idExpedition);
    }

    @GetMapping("/createTeam")
    public List<TeamDTO> createTeam(RequestParam Long idExpedition) {
        return teamService.createTeam();
    }
}
