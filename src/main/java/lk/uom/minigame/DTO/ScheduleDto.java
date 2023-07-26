package lk.uom.minigame.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleDto {
    private int id;
    private LocalDate date;
    private int team;
    private int slotId;
}
