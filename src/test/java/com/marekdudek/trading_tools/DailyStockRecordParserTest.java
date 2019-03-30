package com.marekdudek.trading_tools;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.marekdudek.trading_tools.StockDailyRecordParser.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class DailyStockRecordParserTest
{
    @Test
    void test() throws IOException
    {
        final BufferedReader reader =
                new BufferedReader(
                        new FileReader("./src/test/resources/NYSE_19990101.csv")
                );

        final String header = reader.readLine();
        checkArgument(
                properStockDailyRecordsFileHeader(header)
        );

        String line = reader.readLine();
        final List<DailyStockRecord> records = new ArrayList<>();
        while (line != null)
        {
            final DailyStockRecord record = dailyStockRecord(line);
            records.add(record);
            line = reader.readLine();
        }

        reader.close();

        // then
        assertThat(records, hasSize(1098));
    }

    @Test
    void proper_header()
    {
        assertTrue(
                properStockDailyRecordsFileHeader("Symbol,Date,Open,High,Low,Close,Volume")
        );
    }

    @Test
    void parsing_date()
    {
        assertThat(
                LocalDate.parse("01-Jan-1999", DateFormat),
                is(
                        LocalDate.of(1999, Month.JANUARY, 1)
                )
        );
    }

    @Test
    void parsing_record()
    {
        // given
        final String line = "AA,05-Jan-1999,56.0625,57.375,55.9725,56.4375,696600";
        // when
        final DailyStockRecord record = dailyStockRecord(line);
        // then
        assertThat(
                record,
                is(
                        DailyStockRecord.
                                builder().
                                symbol("AA").
                                date(LocalDate.of(1999, Month.JANUARY, 5)).
                                open(new BigDecimal("56.0625")).
                                high(new BigDecimal("57.375")).
                                low(new BigDecimal("55.9725")).
                                close(new BigDecimal("56.4375")).
                                volume(new BigDecimal("696600")).
                                build()
                )
        );
    }
}
