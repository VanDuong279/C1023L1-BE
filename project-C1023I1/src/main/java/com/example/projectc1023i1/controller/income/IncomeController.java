//package com.example.projectc1023i1.controller.income;
//
//
//import com.example.projectc1023i1.Dto.income.IncomeDTO;
//import com.example.projectc1023i1.service.bill.IncomeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDate;
//
//@RestController
//@RequestMapping("/api/v1/income")
//public class IncomeController {
//
//    @Autowired
//    private IncomeService incomeService;
//
//    @GetMapping("/today")
//    public ResponseEntity<IncomeDTO> getTodayIncome() {
//        return ResponseEntity.ok(incomeService.getTodayIncome());
//    }
//
//    @GetMapping("/week")
//    public ResponseEntity<IncomeDTO> getThisWeekIncome() {
//        return ResponseEntity.ok(incomeService.getThisWeekIncome());
//    }
//
//    @GetMapping("/month")
//    public ResponseEntity<IncomeDTO> getThisMonthIncome() {
//        return ResponseEntity.ok(incomeService.getThisMonthIncome());
//    }
//
//    @GetMapping("/year")
//    public ResponseEntity<IncomeDTO> getThisYearIncome() {
//        return ResponseEntity.ok(incomeService.getThisYearIncome());
//    }
//
//    @GetMapping("/custom")
//    public ResponseEntity<IncomeDTO> getIncomeByDateRange(
//            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
//            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) { // Thay đổi ở đây
//        return ResponseEntity.ok(incomeService.getIncomeByDateRange(from, to));
//    }
//}
