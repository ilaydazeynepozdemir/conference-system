package com.conference.system.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Presentation {
    @NotNull
    private String name;
    @Nullable
    private Integer minutes;
    @Nullable
    private String startTime;

    private boolean isUsed = false;

    public static Presentation networkEvent(String startTime) {
        Presentation presentation = new Presentation();
        presentation.setName("Networking Event");
        presentation.setStartTime(startTime);
        return presentation;
    }

    public static Presentation lunch() {
        Presentation presentation = new Presentation();
        presentation.setName("Lunch");
        presentation.setStartTime("12:00 PM");
        return presentation;
    }
}
