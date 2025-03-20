package ru.mephi.atomhack.Skaifom.choiceTeam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.atomhack.Skaifom.choiceTeam.entity.Hero;

import java.util.List;

@Repository
public interface HeroesRepository extends JpaRepository<Hero, Integer> {
//    List<Hero> findByIdExpedition(Integer idExpedition);
//    List<Hero> findByExpeditionId(Integer idExpedition);
}