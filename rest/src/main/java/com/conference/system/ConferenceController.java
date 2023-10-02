package com.conference.system;

import com.conference.system.model.PresentationRequest;
import com.conference.system.model.PresentationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceController {
    @PostMapping
    public ResponseEntity<PresentationResponse> save(@RequestBody PresentationRequest request) {
        try {
            PresentationResponse response = new PresentationResponse();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
