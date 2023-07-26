package lk.uom.minigame.Service;


import lk.uom.minigame.Entity.QuestionType;
import lk.uom.minigame.Repo.QuestionTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class QuestionTypeService {

    private QuestionTypeRepo questionTypeRepo;

    @Autowired
    public QuestionTypeService(QuestionTypeRepo questionTypeRepo) {
        this.questionTypeRepo = questionTypeRepo;
    }

    public QuestionType getById(int typeId) throws NoSuchElementException{
        if (questionTypeRepo.existsById(typeId)) {
            return questionTypeRepo.getReferenceById(typeId);
        }else{
            throw (new NoSuchElementException());
        }
    }
}
