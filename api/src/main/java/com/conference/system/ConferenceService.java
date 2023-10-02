package com.conference.system;

import com.conference.system.model.Presentation;
import com.conference.system.model.Track;

import java.util.List;

public interface ConferenceService {
    List<Track> createConferencePlan(List<Presentation> presentations);

    String printTrackList(List<Track> tracks);
}
