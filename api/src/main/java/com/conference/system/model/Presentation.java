package com.conference.system.model;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Presentation {
    private String name;
    private Integer minutes;

    @Nullable
    private String time;
}
