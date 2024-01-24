package com.deliverease_group.webapp.models;

public enum Issue {


    NO_ACCESS(1),
    REFUSAL_BY_RECIPIENT(2),
    POOR_WEATHER(3),
    LOST_IN_TRANSIT(4),
    SECURITY_ISSUES(5),
    PARCEL_DAMAGE(6),
    LOST_PARCEL(7),
    OTHER(8);

    private final int value;
    Issue(int value){
        this.value = value;
    }
    public static Issue fromInteger(int value) {
        return switch (value) {
            case 1 -> NO_ACCESS;
            case 2 -> REFUSAL_BY_RECIPIENT;
            case 3 -> POOR_WEATHER;
            case 4 -> LOST_IN_TRANSIT;
            case 5 -> SECURITY_ISSUES;
            case 6 -> PARCEL_DAMAGE;
            case 7 -> LOST_PARCEL;
            case 8 -> OTHER;


            // handle other cases if needed
            default -> throw new IllegalArgumentException("Invalid Role value: " + value);
        };
    }
}


