package lk.uom.minigame.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Slots {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalTime start_time;
    private LocalTime end_time;
}
