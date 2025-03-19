package ru.mephi.atomhack.Skaifom.choiceTeam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.HeroTeamEntity;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<HeroTeamEntity, Long> {
    List<HeroTeamEntity> findByIdExpedition(Long idExpedition);
}