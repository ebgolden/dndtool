package com.ebgolden.domain.spellservice.bll;

import com.ebgolden.common.Player;
import com.ebgolden.common.Spell;
import com.ebgolden.common.Visibility;
import com.ebgolden.domain.spellservice.ChangeVisibilityOfSpellDetailsRequest;
import com.ebgolden.domain.spellservice.UpdatedSpellRequest;
import com.ebgolden.domain.spellservice.UpdatedSpellResponse;
import com.ebgolden.domain.spellservice.ChangeVisibilityOfSpellDetailsResponse;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndPlayerBo;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndVisibilityAndPlayerBo;
import com.ebgolden.domain.spellservice.bll.bo.SpellAndVisibilityBo;
import java.util.Map;

public class SpellBusinessLogicConverterImpl implements SpellBusinessLogicConverter {
    public SpellAndPlayerBo getSpellAndPlayerBoFromUpdatedSpellRequest(UpdatedSpellRequest updatedSpellRequest) {
        Spell spell = updatedSpellRequest.getSpell();
        Player player = updatedSpellRequest.getPlayer();
        return SpellAndPlayerBo
                .builder()
                .spell(spell)
                .player(player)
                .build();
    }

    public SpellAndVisibilityAndPlayerBo getSpellAndVisibilityAndPlayerBoFromChangeVisibilityOfSpellDetailsRequest(ChangeVisibilityOfSpellDetailsRequest changeVisibilityOfUpdatedSpellRequest) {
        Spell spell = changeVisibilityOfUpdatedSpellRequest.getSpell();
        Map<String, Visibility> visibilityMap = changeVisibilityOfUpdatedSpellRequest.getVisibilityMap();
        Player player = changeVisibilityOfUpdatedSpellRequest.getPlayer();
        return SpellAndVisibilityAndPlayerBo
                .builder()
                .spell(spell)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public UpdatedSpellResponse getUpdatedSpellResponseFromSpellAndVisibilityBo(SpellAndVisibilityBo spellAndVisibilityBo) {
        Spell spell = spellAndVisibilityBo.getSpell();
        return UpdatedSpellResponse
                .builder()
                .spell(spell)
                .build();
    }

    public ChangeVisibilityOfSpellDetailsResponse getChangeVisibilityOfSpellDetailsResponseFromSpellAndVisibilityBo(SpellAndVisibilityBo spellAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = spellAndVisibilityBo.getVisibilityMap();
        return ChangeVisibilityOfSpellDetailsResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public SpellAndVisibilityBo getSpellAndVisibilityBoFromSpellAndVisibilityAndPlayerBo(SpellAndVisibilityAndPlayerBo spellAndVisibilityAndPlayerBo) {
        Spell spell = spellAndVisibilityAndPlayerBo.getSpell();
        Map<String, Visibility> visibilityMap = spellAndVisibilityAndPlayerBo.getVisibilityMap();
        return SpellAndVisibilityBo
                .builder()
                .spell(spell)
                .visibilityMap(visibilityMap)
                .build();
    }
}