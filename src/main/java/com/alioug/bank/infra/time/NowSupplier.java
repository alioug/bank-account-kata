package com.alioug.bank.infra.time;

import com.alioug.bank.domain.port.NowSupplierPort;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class NowSupplier implements NowSupplierPort {
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
