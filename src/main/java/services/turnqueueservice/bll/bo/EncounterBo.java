package services.turnqueueservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import commonobjects.Encounter;

@Builder
@Value
public class EncounterBo {
    Encounter encounter;
}