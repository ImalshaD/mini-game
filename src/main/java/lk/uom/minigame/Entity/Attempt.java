package lk.uom.minigame.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name="teamId",referencedColumnName = "id")
    private Teams team;
    private String cookieCode;
    @ManyToOne @JoinColumn(name="caseId1",referencedColumnName = "id")
    private Question task1;
    @ManyToOne @JoinColumn(name="caseId2",referencedColumnName = "id")
    private Question task2;
    @ManyToOne @JoinColumn(name="caseId3",referencedColumnName = "id")
    private Question task3;
    @ManyToOne @JoinColumn(name="caseId4",referencedColumnName = "id")
    private Question task4;
    @ManyToOne @JoinColumn(name="caseId5",referencedColumnName = "id")
    private Question task5;
    private int lastCompletedTask;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long duration;
    private float score;

    public float endAttempt(){
        if (startTime.equals(null)){
            return 0;
        }
        this.endTime = LocalDateTime.now();
        this.duration = Duration.between(this.startTime,this.endTime).getSeconds();
        this.score = lastCompletedTask*20;
        return this.score;
    }
    public void startAttempt(){
        this.startTime = LocalDateTime.now();
    }

    public String getHiddenString(){
        return task1.getRevealLetter()+task2.getRevealLetter()+task3.getRevealLetter()+task4.getRevealLetter()+task5.getRevealLetter();
    }

    public void finishCurrentTask(){
        this.lastCompletedTask++;
    }
}
