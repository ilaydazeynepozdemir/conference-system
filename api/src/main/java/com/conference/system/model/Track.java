package com.conference.system.model;

import lombok.Data;

import java.util.Map;
@Data
public class Track {
    private Long id;
    private Map<String, Presentation> presentationMap;
}
