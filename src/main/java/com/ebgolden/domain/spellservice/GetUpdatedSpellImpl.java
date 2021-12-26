package com.ebgolden.domain.spellservice;

import com.google.inject.Inject;
import com.ebgolden.domain.spellservice.bll.SpellBusinessLogic;
import com.ebgolden.domain.spellservice.bll.SpellBusinessLogicConverter;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndPlayerBo;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndVisibilityBo;

public class GetUpdatedSpellImpl implements GetUpdatedSpell {
    @Inject
    private SpellBusinessLogicConverter spellBusinessLogicConverter;
    @Inject
    private SpellBusinessLogic spellBusinessLogic;

    public UpdatedSpellResponse getUpdatedSpellResponse(UpdatedSpellRequest updatedSpellRequest) {
        SpellAndPlayerBo spellAndPlayerBo = spellBusinessLogicConverter.getSpellAndPlayerBoFromUpdatedSpellRequest(updatedSpellRequest);
        SpellAndVisibilityBo spellAndVisibilityBo = spellBusinessLogic.getSpellAndVisibilityBo(spellAndPlayerBo);
        return spellBusinessLogicConverter.getUpdatedSpellResponseFromSpellAndVisibilityBo(spellAndVisibilityBo);
    }
}