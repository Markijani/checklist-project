package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.AnswerDto;
import ru.itgirl.checklistproject.model.dto.FormCreateDto;
import ru.itgirl.checklistproject.model.dto.FormDto;
import ru.itgirl.checklistproject.model.entity.Answer;
import ru.itgirl.checklistproject.model.entity.Form;
import ru.itgirl.checklistproject.model.repository.AnswerRepository;
import ru.itgirl.checklistproject.model.repository.FormRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormServiceImpl implements FormService {
    private final FormRepository formRepository;
    private final AnswerService answerService;
    private final AnswerRepository answerRepository;

    @Override
    public FormDto createForm(FormCreateDto formCreateDto) {
        Form form = Form.builder()
                .userName(formCreateDto.getUserName())
                .groupNum(formCreateDto.getGroupNum())
                .createdAt(LocalDateTime.now())
                .build();
        // сохраняем форму в первоначальном виде с именем ученицы и номером группы
        Form initial_form = formRepository.save(form);
        Long form_id = initial_form.getId();

        // сохраняем вопросы
        List<String> setOfQuestions = formCreateDto.getBeginner().getSetOfQuestions();
        setOfQuestions.addAll(formCreateDto.getTrainee().getSetOfQuestions());
        setOfQuestions.addAll(formCreateDto.getJunior().getSetOfQuestions());
        List<Integer> currentRangeValues = formCreateDto.getBeginner().getCurrentRangeValues();
        currentRangeValues.addAll(formCreateDto.getTrainee().getCurrentRangeValues());
        currentRangeValues.addAll(formCreateDto.getJunior().getCurrentRangeValues());
        int index_CRV = 0;
        for (String question : setOfQuestions) {
            answerService.createAnswer(form_id, question, currentRangeValues.get(index_CRV));
            index_CRV++;
        }

        // получаем сохраненные вопросы
        List<Answer> answers = answerRepository.findAnswerByFormId(form_id);

        // добавляем результат в форму
        int allValues = answers.stream().map(Answer::getValue).reduce(Integer::sum).orElseThrow();
        double result = ((double) allValues / ((answers.size() * 5))) * 100;
        initial_form.setResult((int) result);

        //обновляем форму результатом
        Form savedForm = formRepository.save(initial_form);

        return convertEntityToDto(savedForm);
    }

    @Override
    public List<FormDto> getAllForms() {
        List<Form> forms = formRepository.findAll();
        return forms.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public FormDto getFormById (Long id) {
        return convertEntityToDto(formRepository.findById(id).orElseThrow());
    }

    @Override
    public List<FormDto> getFormsByGroup(String group) {
        List<Form> forms = formRepository.findFormsByGroupBySql(group).orElseThrow();
        return forms.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<FormDto> getFormsByGroupAndName(String group, String name) {
        List<Form> forms = formRepository.findFormsByGroupAndNameBySql(group, name).orElseThrow();
        return forms.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteForm(Long id) {
        answerRepository.deleteAll(answerRepository.findAnswerByFormId(id));
        formRepository.deleteById(id);
    }

    private FormDto convertEntityToDto(Form form) {
        List<Answer> answers = answerRepository.findAnswerByFormId(form.getId());
        List<AnswerDto> answerDtos = answers.stream().map(answer -> AnswerDto.builder()
                .id(answer.getId())
                .value(answer.getValue())
                .question(answer.getQuestion().getText())
                .question_level(answer.getQuestion().getLevel().getName())
                .build()).toList();
        return FormDto.builder()
                .id(form.getId())
                .username(form.getUserName())
                .groupNum(form.getGroupNum())
                .createdAt(form.getCreatedAt().toString())
                .result(form.getResult())
                .answers(answerDtos)
                .build();
    }
}
