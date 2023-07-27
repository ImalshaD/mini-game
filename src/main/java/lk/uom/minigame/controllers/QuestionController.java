package lk.uom.minigame.controllers;

import lk.uom.minigame.DTO.QuestionArrayDto;
import lk.uom.minigame.DTO.QuestionDto;
import lk.uom.minigame.DTO.TeamsDto;
import lk.uom.minigame.Entity.Attempt;
import lk.uom.minigame.Entity.Question;
import lk.uom.minigame.Entity.Teams;
import lk.uom.minigame.Service.AttemptService;
import lk.uom.minigame.Service.QuestionService;
import lk.uom.minigame.Service.TeamService;
import lk.uom.minigame.Utility.AttemptInstance;
import lk.uom.minigame.Utility.Memo;
import lk.uom.minigame.Utility.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "question")
@CrossOrigin
public class QuestionController {
    private TeamService teamService;
    private QuestionService questionService;
    private AttemptService attemptService;
    private final Memo memo = Memo.getInstance();
    private final RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.getInstance();
    @Autowired
    public QuestionController(TeamService teamService, QuestionService questionService,AttemptService attemptService) {
        this.teamService = teamService;
        this.questionService = questionService;
        this.attemptService = attemptService;
    }

    @GetMapping("/getQuestion/{teamName}")
    public QuestionArrayDto getQs(@PathVariable String teamName){
        System.out.println("Getiing questions "+teamName);
        if (memo.exists(teamName)){
            return  memo.getAttemptInstance(teamName).getQArray();
        }else{
            if (!teamService.existsByTeamName(teamName)){
                ArrayList<QuestionDto> arrayList = new ArrayList<>();
                QuestionDto q1 = new QuestionDto(1,"dummy","","Not allowed","A");
                QuestionDto q2 = new QuestionDto(1,"dummy","","Not allowed","A");
                QuestionDto q3 = new QuestionDto(1,"dummy","","Not allowed","A");
                QuestionDto q4 = new QuestionDto(1,"dummy","","Not allowed","A");
                QuestionDto q5 = new QuestionDto(1,"dummy","","Not allowed","A");
                QuestionDto q6 = new QuestionDto(1,"dummy","","Not allowed","A");
                arrayList.add(q1);
                arrayList.add(q2);
                arrayList.add(q3);
                arrayList.add(q4);
                arrayList.add(q5);
                arrayList.add(q6);
                return new QuestionArrayDto(1800,arrayList);
            }
            Teams team = teamService.getByteamName(teamName);
            Attempt attempt;
            if (!attemptService.existsByteamName(teamName)){
                attempt = new Attempt();
                int rand1 = randomNumberGenerator.getRandomNumber();
                attempt.setTask1(questionService.getByTypeAndNumber(1, rand1));
                int rand2 = randomNumberGenerator.getRandomNumber();
                attempt.setTask2(questionService.getByTypeAndNumber(2, rand2));
                int rand3 = randomNumberGenerator.getRandomNumber();
                attempt.setTask3(questionService.getByTypeAndNumber(3, rand3));
                int rand4 = randomNumberGenerator.getRandomNumber();
                attempt.setTask4(questionService.getByTypeAndNumber(4, rand4));
                int rand5 = randomNumberGenerator.getRandomNumber();
                attempt.setTask5(questionService.getByTypeAndNumber(5, rand5));
                attempt.setTeam(team);
                attemptService.saveAttempt(attempt);
            }
            attempt = attemptService.getAttemptByteamName(teamName);
            AttemptInstance attemptInstance = new AttemptInstance(team, attempt, "!!!");
            memo.putAttemptInstance(team.getTeamName(), attemptInstance);
            return  attemptInstance.getQArray();
        }
    }
    @PutMapping ("/submitSuccess")
    public boolean submit(@RequestBody TeamsDto teamsDto){
        if (memo.exists(teamsDto.getTeamName())){
            Attempt attempt = memo.getAttemptInstance(teamsDto.getTeamName()).getAttempt();
            attempt.finishCurrentTask();
            attemptService.saveAttempt(attempt);
            return true;
        }else{
            return false;
        }
    }


}
