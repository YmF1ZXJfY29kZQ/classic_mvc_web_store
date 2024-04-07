package com.example.managerapp.controller;

import com.example.managerapp.controller.payload.CreateVinylRecordPayload;
import com.example.managerapp.entity.VinylRecord;
import com.example.managerapp.service.VinylRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalog/records")
public class VinylRecordListController {

    private final VinylRecordService vinylRecordService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getAllRecords(Model model) {
        model.addAttribute("records", vinylRecordService.getAllRecords());
        return "catalog/records/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String addNewRecord() {
        return "catalog/records/new_record";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid CreateVinylRecordPayload createVinylRecordPayload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", createVinylRecordPayload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
            return "catalog/records/new_record";
        } else {
            VinylRecord vinylRecord = this.vinylRecordService.createNewRecord(createVinylRecordPayload);
            return "redirect:/catalog/records/".concat(String.valueOf(vinylRecord.getId()));
        }
    }
}
