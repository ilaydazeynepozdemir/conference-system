package com.conference.system;

import com.conference.system.model.Presentation;
import com.conference.system.model.PresentationRequest;
import com.conference.system.model.PresentationResponse;
import com.conference.system.model.Track;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ConferenceController {
    private ConferenceService conferenceService = null;

    @Autowired
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping("test/{str}")
    public Map<Character, Integer> test(@PathVariable("str") String str) {
        log.info("Request: {}",str);
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            char chr = str.toLowerCase().charAt(i);
            Integer count = result.get(chr);
            if (count == null) {
                result.put(chr, 1);
            } else {
                result.put(chr, ++count);
            }
        }
        return result;
    }

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
