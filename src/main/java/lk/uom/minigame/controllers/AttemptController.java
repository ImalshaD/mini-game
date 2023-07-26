package lk.uom.minigame.controllers;

import lk.uom.minigame.DTO.TeamsDto;
import lk.uom.minigame.Service.AttemptService;
import lk.uom.minigame.Utility.AttemptInstance;
import lk.uom.minigame.Utility.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "attempt")
@CrossOrigin
public class AttemptController {
    private Memo memo;
    private AttemptService attemptService;

    @Autowired
    public AttemptController(AttemptService attemptService){
        this.attemptService = attemptService;
        this.memo = Memo.getInstance();
    }
    @PutMapping("/startAttempt")
    public void startAttempt(TeamsDto teamsDto){
        AttemptInstance x = memo.getAttemptInstance(teamsDto.getTeamName());
        x.startAttempt();
        attemptService.saveAttempt(x.getAttempt());

    }
    @PutMapping("/endAttempt")
    public float endAttempt(TeamsDto teamsDto){
        AttemptInstance x = memo.getAttemptInstance(teamsDto.getTeamName());
        float score = x.endAttempt();
        attemptService.saveAttempt(x.getAttempt());
        return score;
    }
}
