package ru.mephi.atomhack.Skaifom.choiceTeam.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mephi.atomhack.Skaifom.choiceTeam.dto.*;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.ExpeditionService;
import ru.mephi.atomhack.Skaifom.choiceTeam.services.TeamService;

import java.util.List;

@RestController
@Slf4j
public class MainController {

    private final ExpeditionService expeditionServiceImpl;
    private final TeamService teamServiceImpl;

    @Autowired
    public MainController(ExpeditionService expeditionServiceImpl, TeamService teamServiceImpl) {
        this.expeditionServiceImpl = expeditionServiceImpl;
        this.teamServiceImpl = teamServiceImpl;
    }

    //todo полный тест
    //todo развёртывание в докере

    @PostMapping("/expeditions")
    public ResponseEntity<ExpeditionDTO> addExpedition(@RequestParam String name, @RequestParam String description) {
        return expeditionServiceImpl.addExpedition(name, description)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/expeditions")
    public ResponseEntity<List<ExpeditionDTO>> getAllExpeditionIds() {
        return ResponseEntity.ok(expeditionServiceImpl.getAllExpeditionIds());
    }

    @GetMapping("/expedition/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam int idExpedition) {
        return ResponseEntity.ok(expeditionServiceImpl.getExpeditionTasks(idExpedition));
    }

    @GetMapping("/expedition/heroes")
    public ResponseEntity<List<HeroDTO>> getExpeditionHeroes(@RequestParam int idExpedition) {
        List<HeroDTO> heroes = expeditionServiceImpl.getHeroesByExpedition(idExpedition);
        return heroes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(heroes);
    }

    @GetMapping("/expedition/task/subtasks")
    public ResponseEntity<List<SubTaskDTO>> getSubtasks(@RequestParam int idTask) {
        return ResponseEntity.ok(expeditionServiceImpl.getSubtasks(idTask));
    }

    @GetMapping("/backlog")
    public ResponseEntity<List<BacklogDTO>> getBacklog() {
        return ResponseEntity.ok(expeditionServiceImpl.getBacklog());
    }

    @PostMapping("/expedition/task/subtask")
    public ResponseEntity<SubTaskDTO> addSubtask(@RequestParam int idTask, @RequestParam int backlogId) {
        return expeditionServiceImpl.addSubtask(idTask, backlogId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/expedition/task")
    public ResponseEntity<TaskDTO> addTask(@RequestParam int idExpedition, @RequestParam String name, @RequestParam String description) {
        return expeditionServiceImpl.addTaskToExpedition(idExpedition, name, description)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/expedition/task/subtask")
    public ResponseEntity<Void> deleteSubtask(@RequestParam int idTask, @RequestParam int idSubtask) {
        return expeditionServiceImpl.deleteSubtask(idTask, idSubtask) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/expedition/task")
    public ResponseEntity<Void> deleteTask(@RequestParam int idTask) {
        return expeditionServiceImpl.deleteTask(idTask) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/expedition")
    public ResponseEntity<Void> deleteExpedition(@RequestParam int idExpedition) {
        return expeditionServiceImpl.deleteExpedition(idExpedition) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/createTeam")
    public ResponseEntity<List<HeroDTO>> createTeam(@RequestParam int idExpedition) {
        return ResponseEntity.ok(teamServiceImpl.createTeam(idExpedition));
    }
}