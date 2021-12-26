package domain.partyservice;

import lombok.Builder;
import lombok.Data;
import common.Party;

@Builder
@Data
public class LeavePartyResponse {
    Party party;
}