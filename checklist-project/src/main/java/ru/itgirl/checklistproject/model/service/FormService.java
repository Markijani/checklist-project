package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.*;

import java.util.List;

public interface FormService {
    FormDto createForm(FormCreateDto formCreateDto);

    FormDto updateForm(FormUpdateDto formUpdateDto);

    FormDto updateFormAnswId(FormUpdateDtoAnswId formUpdateDto);

    List<FormDto> getAllForms();

    FormDto getFormById(Long id);

    FormDto getFormByToken(String token);

    FormDtoUser getFormByTokenUser(String token);

    void deleteForm(Long id);
}
