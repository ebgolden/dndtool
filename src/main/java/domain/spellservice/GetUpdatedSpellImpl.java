package domain.spellservice;

import com.google.inject.Inject;
import domain.spellservice.bll.SpellBusinessLogic;
import domain.spellservice.bll.SpellBusinessLogicConverter;
import domain.spellservice.bll.bo.SpellAndPlayerBo;
import domain.spellservice.bll.bo.SpellAndVisibilityBo;

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