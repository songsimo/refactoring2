package ch01.before.entity;

import java.util.Map;

public record Plays(
        Map<String, Play> playMap
) {
    public Play getPlayById(String id) {
        return playMap.get(id);
    }
}
