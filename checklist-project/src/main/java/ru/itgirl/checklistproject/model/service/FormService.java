package ru.itgirl.checklistproject.model.service;

import ru.itgirl.checklistproject.model.dto.FormCreateDto;
import ru.itgirl.checklistproject.model.dto.FormDto;

import java.util.List;

public interface FormService {
    FormDto createForm(FormCreateDto formCreateDto);

    List<FormDto> getAllForms();
}
