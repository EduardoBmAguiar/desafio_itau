package com.webedu.desafio_itau.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Statistic {

    private Integer count = 0;
    private BigDecimal sum = BigDecimal.ZERO;
    private BigDecimal avg = BigDecimal.ZERO;
    private BigDecimal min = BigDecimal.valueOf(Double.MAX_VALUE);
    private BigDecimal max = BigDecimal.ZERO;

    public Statistic() {}

    public Statistic(Integer count, BigDecimal sum, BigDecimal avg, BigDecimal min, BigDecimal max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return count == statistic.count && Objects.equals(sum, statistic.sum) && Objects.equals(avg, statistic.avg) && Objects.equals(min, statistic.min) && Objects.equals(max, statistic.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, sum, avg, min, max);
    }
}
