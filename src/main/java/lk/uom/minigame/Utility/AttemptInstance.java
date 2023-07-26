package lk.uom.minigame.Utility;

import lk.uom.minigame.DTO.QuestionArrayDto;
import lk.uom.minigame.DTO.QuestionDto;
import lk.uom.minigame.Entity.Attempt;
import lk.uom.minigame.Entity.Question;
import lk.uom.minigame.Entity.Teams;
import lk.uom.minigame.Service.AttemptService;
import lk.uom.minigame.Service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class AttemptInstance {
    private Teams team;
    private Attempt attempt;
    private String cookieCode;
    private QuestionService questionService;
    @Autowired
    private AttemptService attemptService;
    private final RandomNumberGenerator randomNumberGenerator = RandomNumberGenerator.getInstance();
    private final ModelMapper modelMapper = new ModelMapper();
    private int rand;

    private Question generateQuestion(int questionType){
        int x = randomNumberGenerator.getRandomNumber();
        return questionService.getByTypeAndNumber(questionType,x);
    }
    public AttemptInstance(){

    }
    public AttemptInstance(Teams team,Attempt attempt,String cookieCode){
        this.team=team;
        this.attempt=attempt;
        this.cookieCode=cookieCode;
        this.rand = randomNumberGenerator.getRandomNumber();
    }
    @Deprecated
    public void init(Teams team,String cookieCode){
        this.team=team;
        this.cookieCode=cookieCode;
        this.attempt = new Attempt();
        this.attempt.setTeam(team);
        this.attempt.setCookieCode(cookieCode);
        this.attempt.setTask1(generateQuestion(1));
        this.attempt.setTask2(generateQuestion(2));
        this.attempt.setTask3(generateQuestion(3));
        this.attempt.setTask4(generateQuestion(4));
        this.attempt.setTask5(generateQuestion(5));
    }
    public void startAttempt(){
        if (attempt.getStartTime() != null) {
            this.attempt.startAttempt();
        }
    }
    public float endAttempt(){
        if (attempt.getEndTime() != null) {
            return attempt.endAttempt();
        }else{
            return attempt.getScore();
        }
    }
    public String getDecodedString(){
        return attempt.getHiddenString();
    }
    public String getEncodedString(){
        return  Encoder.encoded(getDecodedString(),rand);
    }
    public QuestionDto submitCurrentTask(){
        this.attempt.finishCurrentTask();
        return getCurrentQuestion();
    }

    public QuestionDto getCurrentQuestion(){
        int lastCompletedQuestion = this.attempt.getLastCompletedTask();
        Question currentQuestion = null;
        if (lastCompletedQuestion==0){
            currentQuestion = attempt.getTask1();
        } else if (lastCompletedQuestion==1) {
            currentQuestion = attempt.getTask2();
        }else if (lastCompletedQuestion==2) {
            currentQuestion = attempt.getTask3();
        }else if (lastCompletedQuestion==3) {
            currentQuestion = attempt.getTask4();
        }else if (lastCompletedQuestion==4) {
            currentQuestion = attempt.getTask5();
        }else if (lastCompletedQuestion==5) {
            String description = "ABCDEF translates to "+Encoder.encoded("ABCDE",rand)+"Your revelaed sequence trnaslates to:";
            return new QuestionDto(0,description,null,getDecodedString(),null);
        }
        return new QuestionDto(currentQuestion.getId(), currentQuestion.getDescription(),currentQuestion.getURL()
            , currentQuestion.getAnswer(), currentQuestion.getRevealLetter());
    }

    public long getRemainingTime(){
        LocalTime now = LocalTime.now();
        LocalDateTime startTime = attempt.getStartTime();
        if (startTime == null){
            return 1800;
        }
        Duration difference = Duration.between(now,LocalTime.of(startTime.getHour(), startTime.getMinute(), startTime.getSecond()));
        return 1800 - difference.getSeconds();
    }

    public long startTimeinMillis(){
        return attempt.getStartTime().atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

    public QuestionArrayDto getQArray(){
        ArrayList<QuestionDto> questionDtos = new ArrayList<>();
        questionDtos.add(modelMapper.map(attempt.getTask1(),QuestionDto.class));
        questionDtos.add(modelMapper.map(attempt.getTask2(),QuestionDto.class));
        questionDtos.add(modelMapper.map(attempt.getTask3(),QuestionDto.class));
        questionDtos.add(modelMapper.map(attempt.getTask4(),QuestionDto.class));
        questionDtos.add(modelMapper.map(attempt.getTask5(),QuestionDto.class));
        String description = "ABCDEF translates to "+Encoder.encoded("ABCDE",rand)+" Your revelaed sequence trnaslates to:";
        questionDtos.add(new QuestionDto(0,description,null,getEncodedString(),null));
        return new QuestionArrayDto(getRemainingTime(),questionDtos);
    }

    public Attempt getAttempt() {
        return attempt;
    }
    public void taskCompleted(){
        attempt.finishCurrentTask();
    }
}
