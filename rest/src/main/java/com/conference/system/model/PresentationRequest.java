package com.conference.system.model;

import lombok.Data;

import java.util.List;

@Data
public class PresentationRequest {
    private List<Presentation> presentationList;
}
