package services.partyservice;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LeavePartyResponse {
    boolean leftParty;
}