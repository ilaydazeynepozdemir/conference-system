package com.conference.system;

import com.conference.system.ConferenceService;
import com.conference.system.model.Presentation;
import com.conference.system.model.Track;
import org.antlr.v4.runtime.misc.Pair;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ConferenceServiceImpl implements ConferenceService {
    @Override
    public List<Track> createConferencePlan(List<Presentation> presentations) {
        List<Track> result = new ArrayList<>();
        int presentationCount = 0;
        int trackCount = 1;
        while (presentations.size() > presentationCount) {
            Pair<Track,Integer> pairObj = createTrack(presentations, trackCount);
            Track track = pairObj.a;
            result.add(track);
            presentationCount += pairObj.b;
            ++trackCount;
        }
        return result;
    }

    private Pair<Track, Integer> createTrack(List<Presentation> presentations, Integer trackCount) {
        int presentationCount = 0;
        Date firstSessionCurrentStartTime = new Time(9, 0, 0);
        Date secondSessionCurrentStartTime = new Time(13, 0, 0);
        //09.00AM - 12.00PM
        //3 hours

        //lunch

        //01.00PM - 05.00PM
        //min 4 hours + networking

        Integer firstSessionMin = 180;//min
        Integer secondSessionMin = 240; //min
        Track track = new Track(trackCount);

        List<Presentation> firstSessionPresentations = new ArrayList<>();
        List<Presentation> secondSessionPresentations = new ArrayList<>();
        Pair<Integer, Date> result;
        for (int i = 0; i < presentations.size(); ++i) {
            Presentation presentation = presentations.get(i);
            if (firstSessionMin <= 0 && secondSessionMin <= 0) {
                break;
            }
            if (!presentation.isUsed()) {
                if (firstSessionMin >= presentation.getMinutes()
                        && firstSessionPresentations.size() <= secondSessionPresentations.size()) {
                    result = updateInformations(firstSessionMin, presentation, firstSessionPresentations, firstSessionCurrentStartTime);
                    firstSessionMin = result.a;
                    firstSessionCurrentStartTime = result.b;
                } else if (secondSessionMin >= presentation.getMinutes()) {
                    result = updateInformations(secondSessionMin, presentation, secondSessionPresentations, secondSessionCurrentStartTime);
                    secondSessionMin = result.a;
                    secondSessionCurrentStartTime = result.b;
                }
            }
        }
        presentationCount += firstSessionPresentations.size();
        presentationCount += secondSessionPresentations.size();

        if (!DateTimeUtil.getTime(secondSessionCurrentStartTime).equals("04:00 PM")) {
            secondSessionPresentations.add(Presentation.networkEvent(DateTimeUtil.getTime(secondSessionCurrentStartTime)));
        }
        firstSessionPresentations.add(Presentation.lunch());

        track.addAll(firstSessionPresentations);
        track.addAll(secondSessionPresentations);
        return new Pair<>(track, presentationCount);
    }

    private Pair<Integer, Date> updateInformations(Integer sessionMin, Presentation presentation,
                                                   List<Presentation> sessionPresentationList,
                                                   Date currentStartTime) {
        sessionMin -= presentation.getMinutes(); //update
        presentation.setStartTime(DateTimeUtil.getTime(currentStartTime));
        presentation.setUsed(true);
        sessionPresentationList.add(presentation);
        //update time
        currentStartTime = DateTimeUtil.addMinutes(currentStartTime, presentation.getMinutes()); //update
        return new Pair<>(sessionMin, currentStartTime);
    }
}
