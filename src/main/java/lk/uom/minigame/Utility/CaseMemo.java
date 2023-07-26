package lk.uom.minigame.Utility;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class CaseMemo {
    private int caseNumber;
    private int hintNumber;
    private final int hintCount=10;

    public CaseMemo(int caseNumber, int hintNumber) {
        this.caseNumber = caseNumber;
        this.hintNumber = hintNumber;
    }

    public void nextHint(){
        hintNumber+=1;
        hintNumber%=(hintCount+1);
    }

}
