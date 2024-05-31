package com.matheus.barber.enums;

public enum SchedulesEnum {
    SIX_AM("06:00"),
    SEVEN_AM("07:00"),
    EIGHT_AM("08:00"),
    NINE_AM("09:00"),
    TEN_AM("10:00"),
    ELEVEN_AM("11:00"),
    TWELVE_PM("12:00"),
    THIRTEEN_PM("13:00"),
    FOURTEEN_PM("14:00"),
    FIFTEEN_PM("15:00"),
    SIXTEEN_PM("16:00"),
    SEVENTEEN_PM("17:00"),
    EIGHTEEN_PM("18:00"),
    NINETEEN_PM("19:00"),
    TWENTY_PM("20:00"),
    TWENTY_ONE_PM("21:00"),
    TWENTY_TWO_PM("22:00"),
    TWENTY_THREE_PM("23:00"),
    TWENTY_FOUR_AM("00:00");

    private final String time;

    SchedulesEnum(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
