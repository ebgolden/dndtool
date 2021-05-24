package services.spelldetailservice.bll;

import services.spelldetailservice.bll.bo.SpellAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityBo;

public interface SpellDetailBusinessLogic {
    SpellDetailsAndVisibilityBo getSpellDetailsAndVisibilityBo(SpellAndPlayerBo spellAndPlayerBo);

    SpellDetailsAndVisibilityBo getSpellDetailsAndVisibilityBo(SpellDetailsAndVisibilityAndPlayerBo spellDetailsAndVisibilityAndPlayerBo);
}