package services.spelldetailservice;

import com.google.inject.Inject;
import services.spelldetailservice.bll.SpellDetailBusinessLogic;
import services.spelldetailservice.bll.SpellDetailBusinessLogicConverter;
import services.spelldetailservice.bll.bo.SpellAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityBo;

public class GetSpellDetailsImpl implements GetSpellDetails {
    @Inject
    private SpellDetailBusinessLogicConverter spellDetailBusinessLogicConverter;
    @Inject
    private SpellDetailBusinessLogic spellDetailBusinessLogic;

    public SpellDetailsResponse getSpellDetailsResponse(SpellDetailsRequest spellDetailsRequest) {
        SpellAndPlayerBo spellAndPlayerBo = spellDetailBusinessLogicConverter.getSpellAndPlayerBoFromSpellDetailsRequest(spellDetailsRequest);
        SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo = spellDetailBusinessLogic.getSpellDetailsAndVisibilityBo(spellAndPlayerBo);
        return spellDetailBusinessLogicConverter.getSpellDetailsResponseFromSpellDetailsAndVisibilityBo(spellDetailsAndVisibilityBo);
    }
}