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
import java.util.Objects;
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
    public IncomeDTO getIncomeByDateRange(LocalDateTime from, LocalDateTime to) {
        Double totalIncome = orderRepository.sumTotalByDate(from, to);
        return new IncomeDTO(totalIncome);
    }

    public List<IncomeDTO> mapIncomeResults(List<Object[]> results) {
        return results.stream()
                .map(record -> {
                    // Kiểm tra giá trị của record[0] trước khi chuyển đổi
                    System.out.println("Received record: " + Arrays.toString(record));

                    // Kiểm tra và chuyển đổi record[0] thành ngày nếu hợp lệ
                    Integer day = (record[0] instanceof Number) ? ((Number) record[0]).intValue() : null;
                    if (day == null || day < 1 || day > 31) { // Kiểm tra giới hạn ngày
                        System.out.println("Invalid or out-of-range day value: " + day);
                        return null; // Bỏ qua bản ghi không hợp lệ
                    }

                    // Kiểm tra và chuyển đổi record[1] thành totalIncome nếu hợp lệ
                    Double totalIncome = (record[1] instanceof Number) ? ((Number) record[1]).doubleValue() : null;
                    if (totalIncome == null) {
                        System.out.println("Invalid total income value: null or not a number");
                        return null; // Bỏ qua bản ghi không hợp lệ
                    }

                    return new IncomeDTO(
                            LocalDateTime.of(LocalDate.now().withDayOfMonth(day), LocalTime.MIDNIGHT),
                            totalIncome
                    );
                })
                .filter(Objects::nonNull) // Bỏ qua các bản ghi null
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
//    public List<IncomeDTO> getIncomeByDateRange(LocalDateTime from, LocalDateTime to) {
//        List<Object[]> results = orderRepository.getIncomeByDateRange(from, to);
//        return mapIncomeResults(results);
//    }

}
