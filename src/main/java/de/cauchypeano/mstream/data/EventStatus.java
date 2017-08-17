package de.cauchypeano.mstream.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EventStatus {
    @JsonProperty("idle")
    IDLE,
    @JsonProperty("running")
    RUNNING,
    @JsonProperty("finished")
    FINISHED,
    @JsonProperty("repaired")
    REPAIRED,
    @JsonProperty("errored")
    ERRORED;
}
