package domain.turnqueueservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import common.Encounter;

@Builder
@Value
public class EncounterBo {
    Encounter encounter;
}