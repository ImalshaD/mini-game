package lk.uom.minigame.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionArrayDto {
    private long remainingTime;
    private ArrayList<QuestionDto> qArray;
}
