package lk.uom.minigame.DTO;

import lk.uom.minigame.Entity.Attempt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttemptArrayDto {
    private ArrayList<Attempt> attemptArrayList;
}
