package ch01.refactor.domain;

import java.util.Map;

public record Plays(
        Map<String, Play> playMap
) {
    public Play getPlayById(String id) {
        return playMap.get(id);
    }
}
