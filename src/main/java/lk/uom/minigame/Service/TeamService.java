package lk.uom.minigame.Service;

import jakarta.transaction.Transactional;
import lk.uom.minigame.DTO.TeamsDto;
import lk.uom.minigame.Entity.Teams;
import lk.uom.minigame.Repo.TeamRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@Transactional
public class TeamService {
    private TeamRepo teamRepo;
    private ModelMapper modelMapper;

    @Autowired
    public TeamService(TeamRepo teamRepo, ModelMapper modelMapper) {
        this.teamRepo = teamRepo;
        this.modelMapper = modelMapper;
    }

    public boolean saveTeam(TeamsDto teamsDto){
        try {
            teamRepo.save(modelMapper.map(teamsDto, Teams.class));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Teams getById(int teamID){
        if (teamRepo.existsById(teamID)){
            return teamRepo.getReferenceById(teamID);
        }else{
            throw new NoSuchElementException();
        }
    }
    public boolean existsByTeamName(String teamName){
        return teamRepo.existsByTeamName(teamName);
    }
    public Teams getByteamName(String teamName){
        if (teamRepo.existsByTeamName(teamName)){
            return teamRepo.findByTeamName(teamName);
        }else{
            throw new NoSuchElementException();
        }
    }
}
