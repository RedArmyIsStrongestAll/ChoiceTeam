package ru.mephi.atomhack.Skaifom.choiceTeam.repositories.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.atomhack.Skaifom.choiceTeam.dto.*;
import ru.mephi.atomhack.Skaifom.choiceTeam.repositories.MainRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class MainRepositoryImpl implements MainRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MainRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Optional<ExpeditionDTO> addExpedition(String name, String description) {
        try {
            String sql = "INSERT INTO Expeditions (name, description) VALUES (?, ?) RETURNING id";
            int id = jdbcTemplate.queryForObject(sql, Integer.class, name, description);
            return Optional.of(new ExpeditionDTO(id, name, description));
        } catch (Exception e) {
            log.error("Error adding expedition", e);
            return Optional.empty();
        }
    }

    @Transactional
    public List<ExpeditionDTO> getAllExpeditionIds() {
        try {
            String sql = "SELECT id, name, description FROM Expeditions";
            return jdbcTemplate.query(sql, (rs, rowNum) -> new ExpeditionDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description")
            ));
        } catch (Exception e) {
            log.error("Error retrieving expeditions", e);
            return Collections.emptyList();
        }
    }

    @Transactional
    public List<TaskDTO> getTasksByExpeditionId(int expeditionId) {
        try {
            String sql = "SELECT id, idExpedition, name, description FROM Tasks WHERE idExpedition = ?";
            return jdbcTemplate.query(sql, new TaskRowMapper(), expeditionId);
        } catch (Exception e) {
            log.error("Error retrieving tasks for expedition", e);
            return Collections.emptyList();
        }
    }

    @Transactional
    public List<HeroDTO> getHeroesByExpeditionId(int expeditionId) {
        String sql = """
                    SELECT h.id, h.type, h.level, h.mana 
                    FROM Heroes h
                    JOIN HeroResolution hr ON h.id = hr.idHero
                    WHERE hr.idExpedition = ?
                """;
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) ->
                    new HeroDTO(
                            rs.getInt("id"),
                            rs.getString("type"),
                            rs.getInt("level"),
                            rs.getInt("mana")
                    ), expeditionId);
        } catch (Exception e) {
            log.error("Ошибка при получении героев экспедиции {}: {}", expeditionId, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Transactional
    public List<SubTaskDTO> getSubTasksByTaskId(int taskId) {
        try {
            String sql = "SELECT id, idTask FROM SubTasks WHERE idTask = ?";
            return jdbcTemplate.query(sql, new SubTaskRowMapper(), taskId);
        } catch (Exception e) {
            log.error("Error retrieving subtasks", e);
            return Collections.emptyList();
        }
    }

    @Transactional
    public List<BacklogDTO> getAllBacklogEntries() {
        try {
            String sql = "SELECT id, name, type, difficult, manaStrat, manaMagic, manaBattle FROM Backlog";
            return jdbcTemplate.query(sql, new BacklogRowMapper());
        } catch (Exception e) {
            log.error("Error retrieving backlog entries", e);
            return Collections.emptyList();
        }
    }

    @Transactional
    public Optional<SubTaskDTO> addSubTaskToTask(int taskId, int backlogId) {
        try {
            String sql = "INSERT INTO SubTasks (idTask) VALUES (?) RETURNING id";
            int subTaskId = jdbcTemplate.queryForObject(sql, Integer.class, taskId);
            jdbcTemplate.update("INSERT INTO SubTaskResolution (id, idBackLogSubTask) VALUES (?, ?)", subTaskId, backlogId);
            return Optional.of(new SubTaskDTO(subTaskId, taskId));
        } catch (Exception e) {
            log.error("Error adding subtask", e);
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<TaskDTO> addTaskToExpedition(int expeditionId, String name, String description) {
        try {
            String sql = "INSERT INTO Tasks (idExpedition, name, description) VALUES (?, ?, ?) RETURNING id";
            int id = jdbcTemplate.queryForObject(sql, Integer.class, expeditionId, name, description);
            return Optional.of(new TaskDTO(id, expeditionId, name, description));
        } catch (Exception e) {
            log.error("Error adding task to expedition", e);
            return Optional.empty();
        }
    }

    @Transactional
    public void removeSubTaskFromTask(int taskId, int subTaskId) {
        try {
            String sql = "DELETE FROM SubTaskResolution WHERE idBackLogSubTask = ? AND id = ?";
            jdbcTemplate.update(sql, subTaskId, taskId);
        } catch (Exception e) {
            log.error("Error removing subtask", e);
        }
    }

    @Transactional
    public void removeTaskFromExpedition(int taskId) {
        try {
            String sql = "DELETE FROM Tasks WHERE id = ?";
            jdbcTemplate.update(sql, taskId);
        } catch (Exception e) {
            log.error("Error removing task from expedition", e);
        }
    }

    @Transactional
    public void removeExpedition(int expeditionId) {
        try {
            String sql = "DELETE FROM Expeditions WHERE id = ?";
            jdbcTemplate.update(sql, expeditionId);
        } catch (Exception e) {
            log.error("Error removing expedition", e);
        }
    }

    @Transactional
    public void addHeroToExpedition(int expeditionId, int heroId) {
        try {
            String sqlHeroResolution = "INSERT INTO HeroResolution (idExpedition, idHero) VALUES (?, ?)";
            jdbcTemplate.update(sqlHeroResolution, expeditionId, heroId);
        } catch (Exception e) {
            log.error("Error adding hero to expedition", e);
        }
    }


    private static class TaskRowMapper implements RowMapper<TaskDTO> {
        @Override
        public TaskDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TaskDTO(rs.getInt("id"), rs.getInt("idExpedition"), rs.getString("name"), rs.getString("description"));
        }
    }

    private static class SubTaskRowMapper implements RowMapper<SubTaskDTO> {
        @Override
        public SubTaskDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SubTaskDTO(rs.getInt("id"), rs.getInt("idTask"));
        }
    }

    private static class BacklogRowMapper implements RowMapper<BacklogDTO> {
        @Override
        public BacklogDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BacklogDTO(rs.getInt("id"), rs.getString("name"), rs.getString("type"),
                    rs.getString("difficult"), rs.getInt("manaStrat"), rs.getInt("manaMagic"), rs.getInt("manaBattle"));
        }
    }
}
