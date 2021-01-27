package com.alioug.bank.domain.usecase;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class NowSupplier {
    private final DateTimeFormatter formatter;

    public NowSupplier() {
        formatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withZone(ZoneId.systemDefault());
    }

    public String get() {
        return formatter.format(Instant.now());
    }
}
