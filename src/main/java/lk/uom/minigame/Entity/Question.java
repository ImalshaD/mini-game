package lk.uom.minigame.Entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne @JoinColumn(name="typeId",referencedColumnName = "id")
    private QuestionType questionType;
    private int questionNumberOfTheType;
    private String description;
    private String URL;
    private String answer;
    private String revealLetter;
}
