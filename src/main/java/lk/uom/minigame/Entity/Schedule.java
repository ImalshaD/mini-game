package lk.uom.minigame.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    @OneToOne
    @JoinColumn(name="teamId",referencedColumnName = "id")
    private Teams team;
    @ManyToOne
    @JoinColumn(name="slotId",referencedColumnName = "id")
    private Slots slot;
}
