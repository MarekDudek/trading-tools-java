package com.marekdudek.trading_tools;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public enum StockDailyRecordParser
{
    ;

    private static final String DailyRecordsFileHeader = "Symbol,Date,Open,High,Low,Close,Volume";

    public static boolean properStockDailyRecordsFileHeader(final String header)
    {
        return header.equals(DailyRecordsFileHeader);
    }

    static final DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.US);

    public static DailyStockRecord dailyStockRecord(final String line)
    {
        final String[] tokens = line.split(",");

        final String s = tokens[0];
        final LocalDate d = LocalDate.parse(tokens[1], DateFormat);
        final BigDecimal o = new BigDecimal(tokens[2]);
        final BigDecimal h = new BigDecimal(tokens[3]);
        final BigDecimal l = new BigDecimal(tokens[4]);
        final BigDecimal c = new BigDecimal(tokens[5]);
        final BigDecimal v = new BigDecimal(tokens[6]);

        return DailyStockRecord.
                builder().
                symbol(s).
                date(d).
                open(o).
                high(h).
                low(l).
                close(c).
                volume(v).
                build();
    }
}
