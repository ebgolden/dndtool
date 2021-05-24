package services.spelldetailservice.bll;

import services.spelldetailservice.SpellDetailsRequest;
import services.spelldetailservice.SpellDetailsResponse;
import services.spelldetailservice.SpellDetailsVisibilityRequest;
import services.spelldetailservice.SpellDetailsVisibilityResponse;
import services.spelldetailservice.bll.bo.SpellAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityBo;

public interface SpellDetailBusinessLogicConverter {
    SpellAndPlayerBo getSpellAndPlayerBoFromSpellDetailsRequest(SpellDetailsRequest spellDetailsRequest);

    SpellDetailsAndVisibilityAndPlayerBo getSpellDetailsAndVisibilityAndPlayerBoFromSpellDetailsVisibilityRequest(SpellDetailsVisibilityRequest spellDetailsVisibilityRequest);

    SpellDetailsResponse getSpellDetailsResponseFromSpellDetailsAndVisibilityBo(SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo);

    SpellDetailsVisibilityResponse getSpellDetailsVisibilityResponseFromSpellDetailsAndVisibilityBo(SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo);

    SpellDetailsAndVisibilityBo getSpellDetailsAndVisibilityBoFromSpellDetailsAndVisibilityAndPlayerBo(SpellDetailsAndVisibilityAndPlayerBo spellDetailsAndVisibilityAndPlayerBo);
}