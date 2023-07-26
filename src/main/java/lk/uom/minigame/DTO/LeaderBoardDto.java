package lk.uom.minigame.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaderBoardDto {
    private String teamName;
    private long duration;
    private float score;
    private float calcMean;
}
