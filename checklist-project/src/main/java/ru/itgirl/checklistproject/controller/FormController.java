package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/form/{id}")
    FormDto getFormsView(@PathVariable("id") Long id) {
        return formService.getFormById(id);
    }

    @GetMapping("/forms/group")
    List<FormDto> getFormsByGroup(@RequestParam("group") String group) {
        return formService.getFormsByGroup(group);
    }

    @GetMapping("/forms/groupAndName")
    List<FormDto> getFormsByGroupAndName(@RequestParam("group") String group, @RequestParam("name") String name) {
        return formService.getFormsByGroupAndName(group, name);
    }

    @DeleteMapping("/form/delete/{id}")
    void deleteBook(@PathVariable("id") Long id) {
        formService.deleteForm(id);
    }
}
