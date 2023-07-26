package lk.uom.minigame.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDto {
    private int id;
    private String description;
    private String URL;
    private String answer;
    private String revealLetter;
}
