package commonobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum QueryType {
    @JsonProperty("pull")
    PULL("pull"),
    @JsonProperty("push")
    PUSH("push");

    @Getter
    private final String queryType;
}