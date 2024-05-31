package com.matheus.barber.enums;

public enum SchedulesEnum {
    SIX_AM("06:00"),
    SIX_THIRTY_AM("06:30"),
    SEVEN_AM("07:00"),
    SEVEN_THIRTY_AM("07:30"),
    EIGHT_AM("08:00"),
    EIGHT_THIRTY_AM("08:30"),
    NINE_AM("09:00"),
    NINE_THIRTY_AM("09:30"),
    TEN_AM("10:00"),
    TEN_THIRTY_AM("10:30"),
    ELEVEN_AM("11:00"),
    ELEVEN_THIRTY_AM("11:30"),
    TWELVE_PM("12:00"),
    TWELVE_THIRTY_PM("12:30"),
    THIRTEEN_PM("13:00"),
    THIRTEEN_THIRTY_PM("13:30"),
    FOURTEEN_PM("14:00"),
    FOURTEEN_THIRTY_PM("14:30"),
    FIFTEEN_PM("15:00"),
    FIFTEEN_THIRTY_PM("15:30"),
    SIXTEEN_PM("16:00"),
    SIXTEEN_THIRTY_PM("16:30"),
    SEVENTEEN_PM("17:00"),
    SEVENTEEN_THIRTY_PM("17:30"),
    EIGHTEEN_PM("18:00"),
    EIGHTEEN_THIRTY_PM("18:30"),
    NINETEEN_PM("19:00"),
    NINETEEN_THIRTY_PM("19:30"),
    TWENTY_PM("20:00"),
    TWENTY_THIRTY_PM("20:30"),
    TWENTY_ONE_PM("21:00"),
    TWENTY_ONE_THIRTY_PM("21:30"),
    TWENTY_TWO_PM("22:00"),
    TWENTY_TWO_THIRTY_PM("22:30"),
    TWENTY_THREE_PM("23:00"),
    TWENTY_THREE_THIRTY_PM("23:30"),
    TWENTY_FOUR_AM("00:00");

    private final String time;

    SchedulesEnum(String time) {
        this.time = time;
    }

    public static SchedulesEnum fromString(String time) {
        for (SchedulesEnum schedule : SchedulesEnum.values()) {
            if (schedule.getTime().equals(time)) {
                return schedule;
            }
        }
        throw new IllegalArgumentException("No enum constant for time: " + time);
    }


    public String getTime() {
        return time;
    }
}
