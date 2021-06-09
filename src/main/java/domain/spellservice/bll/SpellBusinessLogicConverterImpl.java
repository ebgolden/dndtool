package domain.spellservice.bll;

import common.Player;
import common.Spell;
import common.Visibility;
import domain.spellservice.ChangeVisibilityOfSpellDetailsRequest;
import domain.spellservice.UpdatedSpellRequest;
import domain.spellservice.UpdatedSpellResponse;
import domain.spellservice.ChangeVisibilityOfSpellDetailsResponse;
import domain.spellservice.bll.bo.SpellAndPlayerBo;
import domain.spellservice.bll.bo.SpellAndVisibilityAndPlayerBo;
import domain.spellservice.bll.bo.SpellAndVisibilityBo;
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