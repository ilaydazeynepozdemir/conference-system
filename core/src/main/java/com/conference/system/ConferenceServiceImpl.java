package com.conference.system;

import com.conference.system.ConferenceService;
import com.conference.system.model.Presentation;
import com.conference.system.model.Track;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConferenceServiceImpl implements ConferenceService {
    @Override
    public List<Track> createConferencePlan(List<Presentation> presentations) {
        List<Track> result = new ArrayList<>();

        return null;
    }

    @Override
    public String printTrackList(List<Track> tracks) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Track track: tracks){
            stringBuilder.append("Track ").append(track.getId()).append(":\n");
            for(String presentationName : track.getPresentationMap().keySet()){
                Presentation presentation = track.getPresentationMap().get(presentationName);
                stringBuilder.append(presentation.getTime()).append(" ").append(presentation.getName()).append(" ");
                if(presentation.getMinutes() != null){
                    stringBuilder.append(presentation.getMinutes()).append("min");
                }
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
