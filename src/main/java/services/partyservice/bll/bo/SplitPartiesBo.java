package services.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import objects.Party;

@Builder
@Data
public class SplitPartiesBo {
    Party[] splitParties;
}