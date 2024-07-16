package ru.itgirl.checklistproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.checklistproject.model.dto.FormCreateDto;
import ru.itgirl.checklistproject.model.dto.FormDto;
import ru.itgirl.checklistproject.model.dto.FormDtoUser;
import ru.itgirl.checklistproject.model.dto.FormUpdateDtoAnswId;
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

    @PutMapping("form/updateAnswId")
    FormDto createForm(@RequestBody FormUpdateDtoAnswId formUpdateDto) {
        return formService.updateFormAnswId(formUpdateDto);
    }

    @GetMapping("/forms")
    List<FormDto> getFormsView() {
        return formService.getAllForms();
    }

    @GetMapping("/form/{id}")
    FormDto getFormsView(@PathVariable("id") Long id) {
        return formService.getFormById(id);
    }

    @GetMapping("/form/uid")
    FormDto getFormsByTokenAdmin(@RequestParam("uid") String token) {
        return formService.getFormByUid(token);
    }

    @GetMapping("/form/uidUser")
    FormDtoUser getFormsByTokenUser(@RequestParam("uid") String token) {
        return formService.getFormByUidUser(token);
    }

    @DeleteMapping("/form/delete/{id}")
    void deleteForm(@PathVariable("id") Long id) {
        formService.deleteForm(id);
    }

    @PutMapping("/form/removeResult/{id}")
    void removeResult(@PathVariable("id") Long id) {
        formService.removeResult(id);
    }
}
