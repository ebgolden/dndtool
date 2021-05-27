package services.partyservice;

import lombok.Builder;
import lombok.Data;
import objects.Party;

@Builder
@Data
public class LeavePartyResponse {
    Party party;
}