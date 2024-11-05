//package com.example.projectc1023i1.service.bill;
//
//
//import com.example.projectc1023i1.Dto.income.IncomeDTO;
//import com.example.projectc1023i1.repository.bill.IOrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.DayOfWeek;
//import java.time.LocalDate;
//import java.time.temporal.TemporalAdjusters;
//
//@Service
//public class IncomeService {
//
//    @Autowired
//    private IOrderRepository orderRepository;
//
//    public IncomeDTO getTodayIncome() {
//        LocalDate today = LocalDate.now();
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(today, today.plusDays(1)); // Thay đổi ở đây
//        return new IncomeDTO(totalIncome);
//    }
//
//    public IncomeDTO getThisWeekIncome() {
//        LocalDate today = LocalDate.now();
//        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfWeek, today.plusDays(1)); // Thay đổi ở đây
//        return new IncomeDTO(totalIncome);
//    }
//
//    public IncomeDTO getThisMonthIncome() {
//        LocalDate today = LocalDate.now();
//        LocalDate startOfMonth = today.withDayOfMonth(1);
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfMonth, today.plusDays(1)); // Thay đổi ở đây
//        return new IncomeDTO(totalIncome);
//    }
//
//    public IncomeDTO getThisYearIncome() {
//        LocalDate today = LocalDate.now();
//        LocalDate startOfYear = today.withDayOfYear(1);
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfYear, today.plusDays(1)); // Thay đổi ở đây
//        return new IncomeDTO(totalIncome);
//    }
//
//    public IncomeDTO getIncomeByDateRange(LocalDate from, LocalDate to) {
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(from, to);
//        return new IncomeDTO(totalIncome);
//    }
//}
