package lk.uom.minigame.Service;

import ch.qos.logback.core.model.Model;
import jakarta.transaction.Transactional;
import lk.uom.minigame.Entity.Question;
import lk.uom.minigame.Entity.QuestionType;
import lk.uom.minigame.Repo.QuestionRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
public class QuestionService {

    private QuestionRepo questionRepo;
    private ModelMapper modelMapper;

    private QuestionTypeService questionTypeService;

    @Autowired
    public QuestionService(QuestionRepo questionRepo, ModelMapper modelMapper, QuestionTypeService questionTypeService) {
        this.questionRepo = questionRepo;
        this.modelMapper = modelMapper;
        this.questionTypeService = questionTypeService;
    }
    public Question getByTypeAndNumber(int typeID,int number){
        try {
            QuestionType questionType = questionTypeService.getById(typeID);
            return questionRepo.findByQuestionTypeAndQuestionNumberOfTheType(questionType,number);
        }catch (Exception e){
            throw e;
        }
    }
}
