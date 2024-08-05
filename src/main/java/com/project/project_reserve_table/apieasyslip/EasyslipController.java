package com.project.project_reserve_table.apieasyslip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/paymentapi")
public class EasyslipController {

    @Autowired
    private EasyslipService easyslipService;

    @PostMapping("/api")
    public String verifySlip(@RequestParam("file") MultipartFile file) {
        return easyslipService.verifySlip(file);
    }
}

