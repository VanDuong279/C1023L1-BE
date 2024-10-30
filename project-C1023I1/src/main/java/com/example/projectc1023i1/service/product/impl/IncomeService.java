package com.example.projectc1023i1.service.product.impl;

import com.example.projectc1023i1.Dto.product.income.IncomeDTO;
import com.example.projectc1023i1.repository.product.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeService {
    @Autowired
    private OrderRepository orderRepository;

//    public IncomeDTO getTodayIncome() {
//        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay(); // Bắt đầu của ngày hiện tại
//        LocalDateTime endOfDay = startOfDay.plusDays(1); // Kết thúc của ngày hiện tại
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfDay, endOfDay); // Thay đổi ở đây
//        return new IncomeDTO(totalIncome);
//    }
//
//    public IncomeDTO getThisWeekIncome() {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate().atStartOfDay(); // Bắt đầu của tuần
//        LocalDateTime endOfToday = now.toLocalDate().atStartOfDay().plusDays(1); // Kết thúc của ngày hiện tại
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfWeek, endOfToday); // Thay đổi ở đây
//        return new IncomeDTO(totalIncome);
//    }
//
//    public IncomeDTO getThisMonthIncome() {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime startOfMonth = now.withDayOfMonth(1).toLocalDate().atStartOfDay(); // Bắt đầu của tháng
//        LocalDateTime endOfToday = now.toLocalDate().atStartOfDay().plusDays(1); // Kết thúc của ngày hiện tại
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfMonth, endOfToday); // Thay đổi ở đây
//        return new IncomeDTO(totalIncome);
//    }
//
//    public IncomeDTO getThisYearIncome() {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime startOfYear = now.withDayOfYear(1).toLocalDate().atStartOfDay(); // Bắt đầu của năm
//        LocalDateTime endOfToday = now.toLocalDate().atStartOfDay().plusDays(1); // Kết thúc của ngày hiện tại
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(startOfYear, endOfToday); // Thay đổi ở đây
//        return new IncomeDTO(totalIncome);
//    }
//
//    public IncomeDTO getIncomeByDateRange(LocalDateTime from, LocalDateTime to) {
//        BigDecimal totalIncome = orderRepository.sumTotalByDate(from, to);
//        return new IncomeDTO(totalIncome);
//    }

    public List<IncomeDTO> mapIncomeResults(List<Object[]> results) {
        return results.stream()
                .map(record -> {
                    // Kiểm tra giá trị của record[0] trước khi chuyển đổi
                    System.out.println("Received record: " + Arrays.toString(record));

                    // Đảm bảo record[0] có kiểu Number và không null
                    if (record[0] == null || !(record[0] instanceof Number)) {
                        throw new IllegalArgumentException("Invalid day value: null or not a number");
                    }

                    int day = ((Number) record[0]).intValue();
                    // Log giá trị ngày để kiểm tra
                    System.out.println("Received day: " + day);

                    // Kiểm tra ngày hợp lệ
                    if (day < 1 || day > 31) { // Kiểm tra lại giới hạn nếu cần thiết
                        throw new IllegalArgumentException("Invalid day value: " + day);
                    }

                    // Đảm bảo record[1] có kiểu Number và không null
                    if (record[1] == null || !(record[1] instanceof Number)) {
                        throw new IllegalArgumentException("Invalid total income value: null or not a number");
                    }

                    return new IncomeDTO(
                            LocalDateTime.of(LocalDate.now().withDayOfMonth(day), LocalTime.MIDNIGHT), // period
                            ((Number) record[1]).doubleValue() // totalIncome
                    );
                })
                .collect(Collectors.toList());
    }
    public List<IncomeDTO> getIncomeByHourToday() {
        List<Object[]> results = orderRepository.getIncomeByHourToday();
        return mapIncomeResults(results);
    }

    public List<IncomeDTO> getIncomeByDayInMonth() {
        List<Object[]> results = orderRepository.getIncomeByDayInMonth();
        return mapIncomeResults(results);
    }

    public List<IncomeDTO> getIncomeByDayInWeek() {
        List<Object[]> results = orderRepository.getIncomeByDayInWeek();
        return mapIncomeResults(results);
    }

    public List<IncomeDTO> getIncomeByMonthInYear() {
        List<Object[]> results = orderRepository.getIncomeByMonthInYear();
        return mapIncomeResults(results);
    }
    public List<IncomeDTO> getIncomeByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Object[]> results = orderRepository.getIncomeByDateRange(startDate, endDate);
        return mapIncomeResults(results);
    }

}
