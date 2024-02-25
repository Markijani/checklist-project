package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.checklistproject.model.dto.FormCreateDto;
import ru.itgirl.checklistproject.model.dto.FormDto;
import ru.itgirl.checklistproject.model.service.FormService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FormController {
    private final FormService formService;

    @PostMapping("form/create")
    FormDto createForm(@RequestBody FormCreateDto formCreateDto) {
        return formService.createForm(formCreateDto);
    }

    @GetMapping("/forms")
    List<FormDto> getFormsView() {
        return formService.getAllForms();
    }
}
