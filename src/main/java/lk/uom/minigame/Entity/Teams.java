package lk.uom.minigame.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teams {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String teamName;
    private String institute;
    private String whatsAppNumber;
}
