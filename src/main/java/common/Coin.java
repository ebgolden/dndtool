package common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Coin {
    @JsonProperty("cp")
    CP("cp"),
    @JsonProperty("sp")
    SP("sp"),
    @JsonProperty("ep")
    EP("ep"),
    @JsonProperty("gp")
    GP("gp"),
    @JsonProperty("pp")
    PP("pp");

    @Getter
    private final String coin;
}
