package lk.uom.minigame.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamsDto {
    private int id;
    private String teamName;
    private String institute;
    private String whatsAppNumber;
}
