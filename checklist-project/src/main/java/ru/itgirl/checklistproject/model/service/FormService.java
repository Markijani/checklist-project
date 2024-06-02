package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.FormCreateDto;
import ru.itgirl.checklistproject.model.dto.FormDto;
import ru.itgirl.checklistproject.model.dto.FormDtoUser;
import ru.itgirl.checklistproject.model.dto.FormUpdateDtoAnswId;

import java.util.List;

public interface FormService {
    FormDto createForm(FormCreateDto formCreateDto);

    FormDto updateFormAnswId(FormUpdateDtoAnswId formUpdateDto);

    List<FormDto> getAllForms();

    FormDto getFormById(Long id);

    FormDto getFormByUid(String uid);

    FormDtoUser getFormByUidUser(String uid);

    void deleteForm(Long id);
}
