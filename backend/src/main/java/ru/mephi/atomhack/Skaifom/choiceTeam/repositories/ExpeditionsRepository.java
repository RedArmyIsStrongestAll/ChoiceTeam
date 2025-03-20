package ru.mephi.atomhack.Skaifom.choiceTeam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.Expedition;

@Repository
public interface ExpeditionsRepository extends JpaRepository<Expedition, Integer> {
}