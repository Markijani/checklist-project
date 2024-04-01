package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.*;

import java.util.List;

public interface FormService {
    FormDto createForm(FormCreateDto formCreateDto);
    FormDto createFormAnswId(FormCreateDtoAnswId formCreateDto);

    FormDto updateForm(FormUpdateDto formUpdateDto);
    FormDto updateFormAnswId(FormUpdateDtoAnswId formUpdateDto);

    List<FormDto> getAllForms();

    FormDto getFormById(Long id);

    FormDto getFormByToken(String token);

    void deleteForm(Long id);
}
