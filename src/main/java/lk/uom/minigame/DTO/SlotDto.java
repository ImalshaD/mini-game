package lk.uom.minigame.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlotDto {
    private int id;
    private LocalTime start_time;
    private LocalTime end_time;
}
