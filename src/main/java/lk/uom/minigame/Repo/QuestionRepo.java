package lk.uom.minigame.Repo;

import lk.uom.minigame.Entity.Question;
import lk.uom.minigame.Entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    public Question findByQuestionTypeAndQuestionNumberOfTheType(QuestionType questionType, int questionNumberOfTheType);
}
