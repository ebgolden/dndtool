package domain.spellservice.bll;

import domain.spellservice.ChangeVisibilityOfSpellDetailsRequest;
import domain.spellservice.UpdatedSpellRequest;
import domain.spellservice.UpdatedSpellResponse;
import domain.spellservice.ChangeVisibilityOfSpellDetailsResponse;
import domain.spellservice.bll.bo.SpellAndPlayerBo;
import domain.spellservice.bll.bo.SpellAndVisibilityAndPlayerBo;
import domain.spellservice.bll.bo.SpellAndVisibilityBo;

public interface SpellBusinessLogicConverter {
    SpellAndPlayerBo getSpellAndPlayerBoFromUpdatedSpellRequest(UpdatedSpellRequest updatedSpellRequest);

    SpellAndVisibilityAndPlayerBo getSpellAndVisibilityAndPlayerBoFromChangeVisibilityOfSpellDetailsRequest(ChangeVisibilityOfSpellDetailsRequest changeVisibilityOfUpdatedSpellRequest);

    UpdatedSpellResponse getUpdatedSpellResponseFromSpellAndVisibilityBo(SpellAndVisibilityBo spellAndVisibilityBo);

    ChangeVisibilityOfSpellDetailsResponse getChangeVisibilityOfSpellDetailsResponseFromSpellAndVisibilityBo(SpellAndVisibilityBo spellAndVisibilityBo);

    SpellAndVisibilityBo getSpellAndVisibilityBoFromSpellAndVisibilityAndPlayerBo(SpellAndVisibilityAndPlayerBo spellAndVisibilityAndPlayerBo);
}