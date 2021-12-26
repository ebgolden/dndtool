package com.ebgolden.domain.partyservice.bll.bo;

import lombok.Builder;
import lombok.Data;
import com.ebgolden.common.Party;

@Builder
@Data
public class PartyBo {
    Party party;
}