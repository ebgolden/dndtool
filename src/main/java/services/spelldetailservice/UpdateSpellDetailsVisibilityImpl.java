package services.spelldetailservice;

import com.google.inject.Inject;
import services.spelldetailservice.bll.SpellDetailBusinessLogic;
import services.spelldetailservice.bll.SpellDetailBusinessLogicConverter;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityBo;

public class UpdateSpellDetailsVisibilityImpl implements UpdateSpellDetailsVisibility {
    @Inject
    private SpellDetailBusinessLogicConverter spellDetailBusinessLogicConverter;
    @Inject
    private SpellDetailBusinessLogic spellDetailBusinessLogic;

    public SpellDetailsVisibilityResponse getSpellDetailsVisibilityResponse(SpellDetailsVisibilityRequest spellDetailsVisibilityRequest) {
        SpellDetailsAndVisibilityAndPlayerBo spellDetailsAndVisibilityAndPlayerBo = spellDetailBusinessLogicConverter.getSpellDetailsAndVisibilityAndPlayerBoFromSpellDetailsVisibilityRequest(spellDetailsVisibilityRequest);
        SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo = spellDetailBusinessLogic.getSpellDetailsAndVisibilityBo(spellDetailsAndVisibilityAndPlayerBo);
        return spellDetailBusinessLogicConverter.getSpellDetailsVisibilityResponseFromSpellDetailsAndVisibilityBo(spellDetailsAndVisibilityBo);
    }
}