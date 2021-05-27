package services.spellservice;

import com.google.inject.Inject;
import services.spellservice.bll.SpellBusinessLogic;
import services.spellservice.bll.SpellBusinessLogicConverter;
import services.spellservice.bll.bo.SpellAndPlayerBo;
import services.spellservice.bll.bo.SpellAndVisibilityBo;

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