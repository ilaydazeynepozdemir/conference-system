package com.conference.system;

import com.conference.system.model.Presentation;
import com.conference.system.model.PresentationRequest;
import com.conference.system.model.PresentationResponse;
import com.conference.system.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidParameterException;
import java.util.List;

@RestController
public class ConferenceController {
    @Autowired
    private ConferenceService conferenceService;
    @PostMapping("plan")
    public PresentationResponse conferencePlan(@RequestBody PresentationRequest request) {
        PresentationResponse response = new PresentationResponse();
        try {
            ValidationUtil.requestValidation(request);
            List<Presentation> presentationList = ConvertUtil.convertToList(request.getPresentations());
            List<Track> trackList = conferenceService.createConferencePlan(presentationList);
            String conferencePlan = ConvertUtil.convertToString(trackList);
            response.setBody(conferencePlan);
            response.setStatus(HttpStatus.OK);
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            return response;
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
