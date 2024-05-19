package ru.itgirl.checklistproject.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itgirl.checklistproject.model.dto.*;
import ru.itgirl.checklistproject.model.entity.Answer;
import ru.itgirl.checklistproject.model.entity.Level;
import ru.itgirl.checklistproject.model.entity.Question;
import ru.itgirl.checklistproject.model.entity.Suggestion;
import ru.itgirl.checklistproject.model.repository.AnswerRepository;
import ru.itgirl.checklistproject.model.repository.LevelRepository;
import ru.itgirl.checklistproject.model.repository.QuestionRepository;
import ru.itgirl.checklistproject.model.repository.SuggestionRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    private final QuestionRepository questionRepository;
    private final SuggestionRepository suggestionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public List<LevelDto> getAllLevelsAndQuestions() {
        List<Level> levels = levelRepository.findAll();
        return levels.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public LevelDto getLevelById (Long id){
        Optional <Level> level = levelRepository.findById(id);
        if (level.isPresent()) {
        return convertEntityToDto(level.get());}
        else {
         throw new NoSuchElementException("Level with this id does not exist");
        }
    }

    @Override
    public LevelDto updateLevel(LevelUpdateDto levelUpdateDto) {
        Level level = levelRepository.findById(levelUpdateDto.getId()).orElseThrow(() -> new NoSuchElementException("Level with this id does not exist"));
        // delete all old questions
        for (Question oldQuestion : level.getQuestions()) {
        questionRepository.deleteById(oldQuestion.getId());
        }
        // update level
        HashSet <Question> newQuestions = new HashSet<>();
        HashSet <Suggestion> newSuggestions = new HashSet<>();
        if (levelUpdateDto.getName() != null) {
            level.setName(levelUpdateDto.getName());
        }
        if (levelUpdateDto.getQuestions() != null) {
            for (Question question : levelUpdateDto.getQuestions()) {
                question.setLevel(level);
                Question saved = questionRepository.save(question);
                HashSet <Answer> answers = new HashSet<>();
                for (Answer answer: question.getAnswers()) {
                    answer.setQuestion(saved);
                    answers.add(answer);
                    answerRepository.save(answer);
                }
                saved.setAnswers(answers);
                newQuestions.add(questionRepository.save(saved));
            }
        }
        if (levelUpdateDto.getSuggestions() != null) {
            //delete old suggestions
            for (Suggestion suggestion: level.getSuggestions()) {
                suggestionRepository.deleteById(suggestion.getId());
            }
            // add new
            for (Suggestion newSuggestion: levelUpdateDto.getSuggestions()) {
                newSuggestion.setLevel(level);
                suggestionRepository.save(newSuggestion);
                newSuggestions.add(newSuggestion);
            }
        }
        level.setQuestions(newQuestions);
        level.setSuggestions(newSuggestions);
        Level updatedLevel = levelRepository.save(level);
        return convertEntityToDto(updatedLevel);
    }

    private LevelDto convertEntityToDto(Level level) {
        return LevelDto.builder()
                .id(level.getId())
                .name(level.getName())
                .questions(level.getQuestions().stream()
                        .map(question -> QuestionDto.builder()
                                .id(question.getId())
                                .text(question.getText())
                                .answers(question.getAnswers().stream()
                                        .map(answer -> AnswerDto.builder()
                                                .id(answer.getId())
                                                .answerText(answer.getText())
                                                .correct(answer.isCorrect())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .suggestions(level.getSuggestions().stream()
                        .map(suggestion -> SuggestionDto.builder()
                                .links(level.getSuggestions().stream().map(Suggestion::getName).collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
