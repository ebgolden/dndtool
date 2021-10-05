package common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Time {
    @JsonProperty("second")
    SECOND("second"),
    @JsonProperty("minute")
    MINUTE("minute"),
    @JsonProperty("hour")
    HOUR("hour"),
    @JsonProperty("day")
    DAY("day");

    @Getter
    private final String time;
}