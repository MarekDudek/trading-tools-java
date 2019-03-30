package com.marekdudek.trading_tools;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public final class DailyStockRecord
{
    @NonNull
    public final String symbol;
    @NonNull
    public final LocalDate date;
    @NonNull
    public final BigDecimal open;
    @NonNull
    public final BigDecimal high;
    @NonNull
    public final BigDecimal low;
    @NonNull
    public final BigDecimal close;
    @NonNull
    public final BigDecimal volume;
}
