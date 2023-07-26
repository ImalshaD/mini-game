package lk.uom.minigame.DTO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lk.uom.minigame.Entity.Teams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttemptDto {
    private int id;
    private int teamId;
    private String cookieCode;
    private int caseId1;
    private int caseId2;
    private int caseId3;
    private int caseId4;
    private int caseId5;
    private int lastCompletedTask;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long duration;
    private float score;
}
