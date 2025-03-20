package ru.mephi.atomhack.Skaifom.choiceTeam.services;

import ru.mephi.atomhack.Skaifom.choiceTeam.dto.HeroDTO;

import java.util.List;

public interface TeamService {
    List<HeroDTO> createTeam(int idExpedition);
}
