package com.conference.system;

import com.conference.system.model.Presentation;
import com.conference.system.model.Track;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


public class ConvertUtil {
    private ConvertUtil() {
    }

    public static String convertToString(List<Track> tracks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Track track : tracks) {
            stringBuilder.append("Track ").append(track.getId()).append(":").append("\n");
            for (Presentation presentation : track) {
                stringBuilder.append(presentation.getStartTime()).append(" ").append(presentation.getName()).append(" ");
                if (presentation.getMinutes() != null) {
                    stringBuilder.append(presentation.getMinutes()).append("min");
                }
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public static List<Presentation> convertToList(String presentations) {
        Stream<String> lines = presentations.trim().lines();

        List<Presentation> result = new ArrayList<>();
        lines.forEach(line -> {
            Presentation presentation = new Presentation();

            if (line.trim().toLowerCase().contains("lightning")) {
                presentation.setName(line);
                presentation.setMinutes(5);
            } else {
                String[] presentationItems = line.trim().split(" ");
                if (presentationItems.length <= 1) {
                    throw new InvalidParameterException(ErrorMessage.invalidParameterError + line);
                }
                if (presentationItems[presentationItems.length - 1].contains("min")) {
                    try {
                        presentation.setMinutes(Integer.valueOf(presentationItems[presentationItems.length - 1].replace("min", "")));
                    } catch (NumberFormatException e) {
                        throw new InvalidParameterException(ErrorMessage.invalidParameterError + line);
                    }
                } else {
                    throw new InvalidParameterException(ErrorMessage.invalidParameterError + line);
                }
                line = line.replace(presentationItems[presentationItems.length - 1], "");
                presentation.setName(line);
            }
            result.add(presentation);
        });
        result.sort(new Comparator<Presentation>() {
            @Override
            public int compare(Presentation o1, Presentation o2) {
                return o2.getMinutes().compareTo(o1.getMinutes());
            }
        });
        return result;
    }
}
