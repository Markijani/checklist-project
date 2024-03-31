package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.FormCreateDto;
import ru.itgirl.checklistproject.model.dto.FormDto;
import ru.itgirl.checklistproject.model.dto.FormUpdateDto;

import java.util.List;

public interface FormService {
    FormDto createForm(FormCreateDto formCreateDto);

    FormDto updateForm(FormUpdateDto formUpdateDto);

    List<FormDto> getAllForms();

    FormDto getFormById(Long id);

    FormDto getFormByToken(String token);

    void deleteForm(Long id);
}
