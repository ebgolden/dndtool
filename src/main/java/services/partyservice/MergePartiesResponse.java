package services.partyservice;

import lombok.Builder;
import lombok.Data;
import commonobjects.Party;

@Builder
@Data
public class MergePartiesResponse {
    Party party;
}