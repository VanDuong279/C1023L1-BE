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
    public List<IncomeDTO> mapIncomeResultsHour(List<Object[]> results) {
        return results.stream()
                .map(record -> {
                    // Kiểm tra giá trị của record[0] trước khi chuyển đổi
                    System.out.println("Received record: " + Arrays.toString(record));

                    // Lấy giá trị giờ từ record[0] và kiểm tra giới hạn giờ
                    Integer hour = (record[0] instanceof Number) ? ((Number) record[0]).intValue() : null;
                    if (hour == null || hour < 0 || hour > 23) { // Giới hạn giờ từ 0 đến 23
                        System.out.println("Invalid or out-of-range hour value: " + hour);
                        return null; // Bỏ qua bản ghi không hợp lệ
                    }

                    // Kiểm tra và chuyển đổi record[1] thành totalIncome nếu hợp lệ
                    Double totalIncome = (record[1] instanceof Number) ? ((Number) record[1]).doubleValue() : null;
                    if (totalIncome == null) {
                        System.out.println("Invalid total income value: null or not a number");
                        return null; // Bỏ qua bản ghi không hợp lệ
                    }

                    // Sử dụng LocalDateTime với giờ lấy từ record[0]
                    return new IncomeDTO(
                            LocalDateTime.of(LocalDate.now(), LocalTime.of(hour, 0)),
                            totalIncome
                    );
                })
                .filter(Objects::nonNull) // Bỏ qua các bản ghi null
                .collect(Collectors.toList());
    }

        // Phương thức tính toán thu nhập theo khoảng thời gian từ ngày đến ngày
        public List<IncomeDTO> getIncomeByRange(LocalDateTime fromDate, LocalDateTime toDate) {
            // Lấy khoảng thời gian theo số ngày chênh lệch giữa từ ngày và đến ngày
            long daysDifference = java.time.Duration.between(fromDate, toDate).toDays();

            List<Object[]> results;
            if (daysDifference == 0) {
                // Thống kê theo giờ nếu cùng một ngày
                results = orderRepository.getIncomeByRange(fromDate, toDate);
                return mapIncomeResultsHour(results);
            } else if (daysDifference >= 2 && daysDifference <= 31) {
                // Thống kê theo ngày nếu khoảng cách từ 2 đến 31 ngày
                results = orderRepository.getIncomeByRange(fromDate, toDate);
                return mapIncomeResults(results);
            } else if (daysDifference >= 32 && daysDifference <= 366) {
                // Thống kê theo tháng nếu khoảng cách từ 32 đến 366 ngày
                results = orderRepository.getIncomeByRange(fromDate, toDate);
                return mapIncomeResultsByMonth(results);
            } else {
                // Thống kê theo năm nếu khoảng cách trên 366 ngày
                results = orderRepository.getIncomeByRange(fromDate, toDate);
                return mapIncomeResultsByYear(results);
            }
        }

        // Phương thức để map kết quả thống kê theo tháng
        public List<IncomeDTO> mapIncomeResultsByMonth(List<Object[]> results) {
            return results.stream()
                    .map(record -> {
                        Integer month = (record[0] instanceof Number) ? ((Number) record[0]).intValue() : null;
                        if (month == null || month < 1 || month > 12) {
                            System.out.println("Invalid month value: " + month);
                            return null;
                        }

                        Double totalIncome = (record[1] instanceof Number) ? ((Number) record[1]).doubleValue() : null;
                        if (totalIncome == null) {
                            System.out.println("Invalid total income value: null or not a number");
                            return null;
                        }

                        return new IncomeDTO(
                                LocalDateTime.of(LocalDate.now().withMonth(month).withDayOfMonth(1), LocalTime.MIDNIGHT),
                                totalIncome
                        );
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        // Phương thức để map kết quả thống kê theo năm
        public List<IncomeDTO> mapIncomeResultsByYear(List<Object[]> results) {
            return results.stream()
                    .map(record -> {
                        Integer year = (record[0] instanceof Number) ? ((Number) record[0]).intValue() : null;
                        if (year == null) {
                            System.out.println("Invalid year value: " + year);
                            return null;
                        }

                        Double totalIncome = (record[1] instanceof Number) ? ((Number) record[1]).doubleValue() : null;
                        if (totalIncome == null) {
                            System.out.println("Invalid total income value: null or not a number");
                            return null;
                        }

                        return new IncomeDTO(
                                LocalDateTime.of(LocalDate.of(year, 1, 1), LocalTime.MIDNIGHT),
                                totalIncome
                        );
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }



    public List<IncomeDTO> getIncomeByHourToday() {
        List<Object[]> results = orderRepository.getIncomeByHourToday();
        return mapIncomeResultsHour(results);
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
