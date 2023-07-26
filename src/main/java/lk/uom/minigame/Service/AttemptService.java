package lk.uom.minigame.Service;

import jakarta.transaction.Transactional;
import lk.uom.minigame.DTO.AttemptDto;
import lk.uom.minigame.Entity.Attempt;
import lk.uom.minigame.Entity.Teams;
import lk.uom.minigame.Repo.AttemptRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AttemptService {
    private AttemptRepo attemptRepo;
    private ModelMapper modelMapper;

    @Autowired
    public AttemptService(AttemptRepo attemptRepo,ModelMapper modelMapper) {
        this.attemptRepo = attemptRepo;
        this.modelMapper = modelMapper;
    }

    public Attempt getAttemptByteamName(String teamName){
        return attemptRepo.findLatestAttemptByTeamName(teamName);
    }
    public boolean existsByteamName(String teamName){
        return attemptRepo.existsByTeamName(teamName);
    }

    public boolean saveAttempt(Attempt attempt){
        try {
            attemptRepo.save(attempt);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public  boolean saveAttempt(AttemptDto attemptDto){
        return saveAttempt(modelMapper.map(attemptDto,Attempt.class));
    }
}
