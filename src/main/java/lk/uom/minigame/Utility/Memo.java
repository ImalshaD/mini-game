package lk.uom.minigame.Utility;

import jakarta.servlet.http.HttpServletRequest;
import lk.uom.minigame.DTO.LeaderBoardDto;
import lk.uom.minigame.Entity.Attempt;
import lk.uom.minigame.controllers.TeamController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Memo {
    private HashMap<String,AttemptInstance> dict;
    private static Memo instance = null;
    private Memo() {
       dict = new HashMap<String,AttemptInstance>();
    }
    public static Memo getInstance(){
        if(instance == null){
            instance = new Memo();
        }
        return instance;
    }
    public boolean exists(String teamName){
        return dict.containsKey(teamName);
    }
    public AttemptInstance getAttemptInstance(String teamName){
        if (!(dict.containsKey(teamName))) {
            return null;
        }
        return dict.get(teamName);
    }
    public void putAttemptInstance(String teamName,AttemptInstance attemptInstance){
        dict.put(teamName,attemptInstance);
    }
    public void removeEntry(String sessionID){
        dict.remove(sessionID);
    }
    public void removeEntry(HttpServletRequest request){
        removeEntry(SessionHandler.getSessionID(request));
    }
    public void cleanDict(){
        dict = new HashMap<>();
        System.gc();
    }

    public ArrayList<LeaderBoardDto> getOrder(){
        ArrayList<LeaderBoardDto> x = new ArrayList<>();
        for (AttemptInstance a : dict.values()){
            Attempt attempt = a.getAttempt();
            LeaderBoardDto leaderBoardDto = new LeaderBoardDto();
            leaderBoardDto.setTeamName(attempt.getTeam().getTeamName());
            leaderBoardDto.setScore(attempt.getScore());
            leaderBoardDto.setDuration(attempt.getDuration());
            float weightedMark = attempt.getScore()-(attempt.getDuration()/1800)*40;
            if (weightedMark<0){
                weightedMark =0;
            }
            leaderBoardDto.setCalcMean(weightedMark);
            x.add(leaderBoardDto);
        }
        Comparator<LeaderBoardDto> nameComparator = Comparator.comparing(LeaderBoardDto::getCalcMean);
        Collections.sort(x,nameComparator);
        return x;

    }
}

