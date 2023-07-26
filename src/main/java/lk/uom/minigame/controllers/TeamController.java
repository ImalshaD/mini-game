package lk.uom.minigame.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lk.uom.minigame.DTO.AttemptDto;
import lk.uom.minigame.DTO.ResponseDto;
import lk.uom.minigame.DTO.TeamsDto;
import lk.uom.minigame.Entity.Attempt;
import lk.uom.minigame.Entity.Teams;
import lk.uom.minigame.Service.AttemptService;
import lk.uom.minigame.Service.QuestionService;
import lk.uom.minigame.Service.TeamService;
import lk.uom.minigame.Utility.AttemptInstance;
import lk.uom.minigame.Utility.Memo;
import lk.uom.minigame.Utility.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="team")
@CrossOrigin
public class TeamController {

    private TeamService teamService;
    private final RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.getInstance();
    private final Memo memo = Memo.getInstance();
    private QuestionService questionService;
    private AttemptService attemptService;
    @Autowired
    public TeamController(TeamService teamService,QuestionService questionService,AttemptService attemptService) {
        this.teamService = teamService;
        this.questionService= questionService;
        this.attemptService= attemptService;
    }

    @PostMapping("/saveTeam")
    public ResponseDto<TeamsDto> saveTeam(@RequestBody TeamsDto teamsDto, HttpServletRequest request){
        if (teamService.existsByTeamName(teamsDto.getTeamName())){
            Teams team = teamService.getByteamName(teamsDto.getTeamName());
            teamsDto.setId(team.getId());
        }
        boolean response =teamService.saveTeam(teamsDto);
        ResponseDto<TeamsDto> responseDto = new ResponseDto<>();
        if (response){
            responseDto.setStatusCode(1);
            if (!memo.exists(teamsDto.getTeamName())) {
                Teams team = teamService.getByteamName(teamsDto.getTeamName());
                Attempt attempt;
                if (!attemptService.existsByteamName(teamsDto.getTeamName())){
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
                attempt = attemptService.getAttemptByteamName(teamsDto.getTeamName());
                AttemptInstance attemptInstance = new AttemptInstance(team, attempt, "!!!");
                memo.putAttemptInstance(team.getTeamName(), attemptInstance);
            }
        }else{
            responseDto.setStatusCode(2);
        }
        responseDto.setData(teamsDto);
        return  responseDto;
    }
    @GetMapping("/existsByteamName")
    public boolean existbyTeamName(@RequestBody TeamsDto teamsDto){
        return teamService.existsByTeamName(teamsDto.getTeamName());
    }
}
