package services.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import commonobjects.Party;

@Builder
@Data
public class PartyBo {
    Party party;
}