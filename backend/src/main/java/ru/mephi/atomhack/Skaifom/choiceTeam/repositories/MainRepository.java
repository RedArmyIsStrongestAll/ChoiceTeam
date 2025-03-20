package ru.mephi.atomhack.Skaifom.choiceTeam.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.BacklogDTO;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.ExpeditionDTO;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.SubTaskDTO;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.TaskDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MainRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public ExpeditionDTO addExpedition(String name, String description) {
        String sql = "INSERT INTO Expeditions (name, description) VALUES (?, ?) RETURNING id";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, name, description);
        return new ExpeditionDTO(id, name, description);
    }

    @Transactional
    public List<Integer> getAllExpeditionIds() {
        String sql = "SELECT id FROM Expeditions";
        return jdbcTemplate.queryForList(sql, Integer.class);
    }

    @Transactional
    public List<TaskDTO> getTasksByExpeditionId(int expeditionId) {
        String sql = "SELECT id, idExpedition, name, description FROM Tasks WHERE idExpedition = ?";
        return jdbcTemplate.query(sql, new TaskRowMapper(), expeditionId);
    }

    @Transactional
    public List<SubTaskDTO> getSubTasksByTaskId(int taskId) {
        String sql = "SELECT id, idTask FROM SubTasks WHERE idTask = ?";
        return jdbcTemplate.query(sql, new SubTaskRowMapper(), taskId);
    }

    @Transactional
    public List<BacklogDTO> getAllBacklogEntries() {
        String sql = "SELECT id, name, type, difficult, manaStrat, manaMagic, manaBattle FROM Backlog";
        return jdbcTemplate.query(sql, new BacklogRowMapper());
    }

    @Transactional
    public SubTaskDTO addSubTaskToTask(int taskId, int backlogId) {
        String sql = "INSERT INTO SubTasks (idTask) VALUES (?) RETURNING id";
        int subTaskId = jdbcTemplate.queryForObject(sql, Integer.class, taskId);
        jdbcTemplate.update("INSERT INTO SubTaskResolution (id, idBackLogSubTask) VALUES (?, ?)", subTaskId, backlogId);
        return new SubTaskDTO(subTaskId, taskId);
    }

    @Transactional
    public TaskDTO addTaskToExpedition(int expeditionId, String name, String description) {
        String sql = "INSERT INTO Tasks (idExpedition, name, description) VALUES (?, ?, ?) RETURNING id";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, expeditionId, name, description);
        return new TaskDTO(id, expeditionId, name, description);
    }

    @Transactional
    public void removeSubTaskFromTask(int taskId, int subTaskId) {
        String sql = "DELETE FROM SubTaskResolution WHERE idBackLogSubTask = ? AND id = ?";
        jdbcTemplate.update(sql, subTaskId, taskId);
    }

    @Transactional
    public void removeTaskFromExpedition(int taskId) {
        String sql = "DELETE FROM Tasks WHERE id = ?";
        jdbcTemplate.update(sql, taskId);
    }

    @Transactional
    public void removeExpedition(int expeditionId) {
        String sql = "DELETE FROM Expeditions WHERE id = ?";
        jdbcTemplate.update(sql, expeditionId);
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
