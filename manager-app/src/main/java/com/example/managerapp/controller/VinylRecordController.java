package com.example.managerapp.controller;

import com.example.managerapp.controller.payload.UpdateVinylRecordPayload;
import com.example.managerapp.entity.VinylRecord;
import com.example.managerapp.service.VinylRecordService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/catalog/records/{recordId}")
public class VinylRecordController {

    private final VinylRecordService vinylRecordService;

    private final MessageSource messageSource;

    @ModelAttribute("record")
    public VinylRecord record(@PathVariable("recordId") UUID recordId) {
        return this.vinylRecordService.getRecord(recordId).orElseThrow(() -> new NoSuchElementException("catalog.errors.record.not_found"));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String get() {
        return "catalog/records/record";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editRecord() {
        return "catalog/records/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateVinylRecordInfo(@ModelAttribute("record") VinylRecord vinylRecord, UpdateVinylRecordPayload updateVinylRecordPayload) {
        this.vinylRecordService.updateRecord(vinylRecord.getId(), updateVinylRecordPayload);
        return "redirect:/catalog/records/".concat(String.valueOf(vinylRecord.getId()));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteRecord(@ModelAttribute("record") VinylRecord vinylRecord) {
        this.vinylRecordService.deleteRecord(vinylRecord.getId());
        return "redirect:/catalog/records/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model, Locale locale) {
        model.addAttribute("error", this.messageSource.getMessage(exception.getMessage(), new Object[0], exception.getMessage(), locale));
        return "/errors/404";
    }

    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException(BadRequestException exception, Model model, HttpServletResponse response, Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error", this.messageSource.getMessage(exception.getMessage(), new Object[0], exception.getMessage(), locale));
        return "/errors/404";
    }
}
