package services.spellservice;

import com.google.inject.Inject;
import services.spellservice.bll.SpellBusinessLogic;
import services.spellservice.bll.SpellBusinessLogicConverter;
import services.spellservice.bll.bo.SpellAndVisibilityAndPlayerBo;
import services.spellservice.bll.bo.SpellAndVisibilityBo;

public class ChangeVisibilityOfSpellDetailsImpl implements ChangeVisibilityOfSpellDetails {
    @Inject
    private SpellBusinessLogicConverter spellBusinessLogicConverter;
    @Inject
    private SpellBusinessLogic spellBusinessLogic;

    public ChangeVisibilityOfSpellDetailsResponse getChangeVisibilityOfSpellDetailsResponse(ChangeVisibilityOfSpellDetailsRequest changeVisibilityOfUpdatedSpellRequest) {
        SpellAndVisibilityAndPlayerBo spellAndVisibilityAndPlayerBo = spellBusinessLogicConverter.getSpellAndVisibilityAndPlayerBoFromChangeVisibilityOfSpellDetailsRequest(changeVisibilityOfUpdatedSpellRequest);
        SpellAndVisibilityBo spellAndVisibilityBo = spellBusinessLogic.getSpellAndVisibilityBo(spellAndVisibilityAndPlayerBo);
        return spellBusinessLogicConverter.getChangeVisibilityOfSpellDetailsResponseFromSpellAndVisibilityBo(spellAndVisibilityBo);
    }
}