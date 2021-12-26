package com.ebgolden.domain.spellservice;

import com.google.inject.Inject;
import com.ebgolden.domain.spellservice.bll.SpellBusinessLogic;
import com.ebgolden.domain.spellservice.bll.SpellBusinessLogicConverter;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndVisibilityAndPlayerBo;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndVisibilityBo;

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