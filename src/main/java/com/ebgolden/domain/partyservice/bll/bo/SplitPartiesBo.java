package domain.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import common.Party;

@Builder
@Data
public class SplitPartiesBo {
    Party[] splitParties;
}