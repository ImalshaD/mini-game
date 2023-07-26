package lk.uom.minigame.Utility;

import jakarta.servlet.http.HttpServletRequest;
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

    public ArrayList<Attempt> getOrder(){
        ArrayList<Attempt> x = new ArrayList<>();
        for (AttemptInstance a : dict.values()){
            x.add(a.getAttempt());
        }
        Collections.sort(x, new Comparator<Attempt>() {
            @Override
            public int compare(Attempt attempt1, Attempt attempt2) {
                // Sort by score in descending order
                int scoreComparison = Float.compare(attempt2.getScore(), attempt1.getScore());

                // If scores are equal, sort by duration in ascending order
                if (scoreComparison == 0) {
                    return Long.compare(attempt1.getDuration(), attempt2.getDuration());
                }

                return scoreComparison;
            }
        });
        return x;
    }
}

