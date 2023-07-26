package lk.uom.minigame.Utility;

import lk.uom.minigame.Entity.Teams;

public class MemoChildTemp {

    public AttemptInstance getAttemptInstance(Teams team){
        AttemptInstance attemptInstance = new AttemptInstance();
        attemptInstance.init(team,"$12@");
        return attemptInstance;
    }
}
