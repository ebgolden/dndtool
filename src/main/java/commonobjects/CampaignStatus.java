package commonobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CampaignStatus {
    @JsonProperty("setup")
    SETUP("setup"),
    @JsonProperty("running")
    RUNNING("running");

    @Getter
    private final String campaignStatus;
}