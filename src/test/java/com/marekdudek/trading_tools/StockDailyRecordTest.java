package com.marekdudek.trading_tools;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.Month.JANUARY;

final class StockDailyRecordTest
{
    @Test
    void stock_daily_record_can_be_fluently_built()
    {
        // when
        StockDailyRecord.builder().
                symbol("AA").
                date(LocalDate.of(1999, JANUARY, 1)).
                open(new BigDecimal(55.92)).
                high(new BigDecimal(55.92)).
                low(new BigDecimal(55.92)).
                close(new BigDecimal(55.92)).
                volume(new BigDecimal(0)).
                build();
        // then it doesn't break
    }
}
