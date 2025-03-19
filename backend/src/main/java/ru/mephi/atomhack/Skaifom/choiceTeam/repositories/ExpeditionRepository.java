package ru.mephi.atomhack.Skaifom.choiceTeam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.ExpeditionEntity;

@Repository
public interface ExpeditionRepository extends JpaRepository<ExpeditionEntity, Long> {
}