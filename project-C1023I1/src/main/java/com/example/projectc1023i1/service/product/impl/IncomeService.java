package com.example.projectc1023i1.service.product.impl;

import com.example.projectc1023i1.Dto.product.income.IncomeDTO;
import com.example.projectc1023i1.repository.product.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
@Service
public class IncomeService {
    @Autowired
    private OrderRepository orderRepository;

    public IncomeDTO getTodayIncome() {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay(); // Bắt đầu của ngày hiện tại
        LocalDateTime endOfDay = startOfDay.plusDays(1); // Kết thúc của ngày hiện tại
        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfDay, endOfDay); // Thay đổi ở đây
        return new IncomeDTO(totalIncome);
    }

    public IncomeDTO getThisWeekIncome() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate().atStartOfDay(); // Bắt đầu của tuần
        LocalDateTime endOfToday = now.toLocalDate().atStartOfDay().plusDays(1); // Kết thúc của ngày hiện tại
        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfWeek, endOfToday); // Thay đổi ở đây
        return new IncomeDTO(totalIncome);
    }

    public IncomeDTO getThisMonthIncome() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).toLocalDate().atStartOfDay(); // Bắt đầu của tháng
        LocalDateTime endOfToday = now.toLocalDate().atStartOfDay().plusDays(1); // Kết thúc của ngày hiện tại
        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfMonth, endOfToday); // Thay đổi ở đây
        return new IncomeDTO(totalIncome);
    }

    public IncomeDTO getThisYearIncome() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfYear = now.withDayOfYear(1).toLocalDate().atStartOfDay(); // Bắt đầu của năm
        LocalDateTime endOfToday = now.toLocalDate().atStartOfDay().plusDays(1); // Kết thúc của ngày hiện tại
        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfYear, endOfToday); // Thay đổi ở đây
        return new IncomeDTO(totalIncome);
    }

    public IncomeDTO getIncomeByDateRange(LocalDateTime from, LocalDateTime to) {
        BigDecimal totalIncome = orderRepository.sumTotalByDate(from, to);
        return new IncomeDTO(totalIncome);
    }

}
