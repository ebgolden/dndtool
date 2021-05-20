package services.turnqueueservice.bll.bo;

import lombok.Builder;
import lombok.Value;
import objects.Encounter;

@Builder
@Value
public class EncounterBo {
    Encounter encounter;
}