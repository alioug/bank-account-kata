package com.alioug.bank.infra.time;

public class NowSupplierMock extends NowSupplier {
    @Override
    public String get() {
        return "01/01/2021 00:00";
    }
}
