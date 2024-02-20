package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.itgirl.checklistproject.model.dto.AnswerCreateDto;
import ru.itgirl.checklistproject.model.dto.FormCreateDto;
import ru.itgirl.checklistproject.model.entity.Answer;
import ru.itgirl.checklistproject.model.entity.Form;
import ru.itgirl.checklistproject.model.repository.FormRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;

    private Form convertCreateDtoToEntity(FormCreateDto formCreateDto) {
        List<Answer> answers = new ArrayList<>();
        List <String> setOfQuestions = formCreateDto.getBeginner().getSetOfQuestions();
        setOfQuestions.addAll(formCreateDto.getTrainee().getSetOfQuestions());
        setOfQuestions.addAll(formCreateDto.getJunior().getSetOfQuestions());
        List <Integer> currentRangeValues = formCreateDto.getBeginner().getCurrentRangeValues();
        currentRangeValues.addAll(formCreateDto.getTrainee().getCurrentRangeValues());
        currentRangeValues.addAll(formCreateDto.getJunior().getCurrentRangeValues());
        for (String question:
                setOfQuestions) {
            Long question_id = questionrepo.findbyId;
            int answer_value = currentRangeValues.get(setOfQuestions.indexOf(question));
            AnswerCreateDto answerCreateDto = AnswerCreateDto
                    .builder()
                    .question_id(question_id)
                    .value(answer_value)
                    .build();
        }
        return Form.builder()
                .userName(formCreateDto.getUsername())
                .groupNum(formCreateDto.getGroupNum())
                .answers(answers)
                .createdAt(LocalDateTime.now())
                .build();
    };
}
