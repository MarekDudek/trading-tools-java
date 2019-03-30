package com.marekdudek.trading_tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.google.common.base.Preconditions.checkArgument;
import static com.marekdudek.trading_tools.FunctionalUtils.error;

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

    public static List<DailyStockRecord> dailyFile(final String path)
    {
        try (
                final BufferedReader reader =
                        new BufferedReader(
                                new FileReader("./src/test/resources/NYSE_19990101.csv")
                        )
        )
        {
            final String header = reader.readLine();
            checkArgument(
                    properStockDailyRecordsFileHeader(header)
            );

            final List<DailyStockRecord> records = new ArrayList<>();

            String line = reader.readLine();
            while (line != null)
            {
                final DailyStockRecord record = dailyStockRecord(line);
                records.add(record);
                line = reader.readLine();
            }

            return records;
        }
        catch (final IOException e)
        {
            return error(e);
        }
    }
}
