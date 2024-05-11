package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.*;

import java.util.List;

public interface FormService {
    FormDto createForm(FormCreateDto formCreateDto);

    FormDto updateForm(FormUpdateDto formUpdateDto);

    FormDto updateFormAnswId(FormUpdateDtoAnswId formUpdateDto);

    List<FormDto> getAllForms();

    FormDto getFormById(Long id);

    FormDto getFormByUid(String uid);

    FormDtoUser getFormByUidUser(String uid);

    void deleteForm(Long id);
}
