package lk.uom.minigame.Utility;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class MemoChild {

    private boolean flag;

    private HashMap<String,AttemptInstance> dict;
    private static MemoChild instance = null;
    private final long expirationTimeInSeconds = 3600;
    private ScheduledExecutorService scheduler;
    private MemoChild(){
        scheduler = Executors.newSingleThreadScheduledExecutor();
        flag = false;
    }
    public MemoChild getInstance(){
        if (instance==null){
            instance = new MemoChild();
        }
        return instance;
    }

    public void cleanupmemory(){
        flag = true;
        long currentTime =System.currentTimeMillis();
        dict.entrySet().removeIf(entry -> currentTime - entry.getValue().startTimeinMillis() >= expirationTimeInSeconds*1000);
        flag = false;
    }
    public void put(String teamName,AttemptInstance attemptInstance){
        while (flag){
        }
        dict.put(teamName,attemptInstance);
    }
    public void remove(String teamName){
        while (flag){
        }
        if (dict.containsKey(teamName)){
            dict.remove(teamName);
        }
    }
    public boolean exists(String teamName){
        while (flag){
        }
        return dict.containsKey(teamName);
    }
    public AttemptInstance get(String teamName){
        while (flag){
        }
        if (exists(teamName)){
            return dict.get(teamName);
        }else{
            return null;
        }
    }
}
