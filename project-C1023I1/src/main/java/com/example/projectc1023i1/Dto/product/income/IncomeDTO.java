package com.example.projectc1023i1.Dto.product.income;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class IncomeDTO {
    private LocalDateTime dayCreate;
    private Double totalIncome;

    public IncomeDTO() {
    }

    public IncomeDTO(LocalDateTime dayCreate, Double totalIncome) {
        this.dayCreate = dayCreate;
        this.totalIncome = totalIncome;
    }

    public IncomeDTO(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public LocalDateTime getDayCreate() {
        return dayCreate;
    }

    public void setDayCreate(LocalDateTime dayCreate) {
        this.dayCreate = dayCreate;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIcome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }
}
