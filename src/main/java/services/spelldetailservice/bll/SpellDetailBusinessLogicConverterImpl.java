package services.spelldetailservice.bll;

import objects.Player;
import objects.Spell;
import objects.Visibility;
import services.spelldetailservice.SpellDetailsRequest;
import services.spelldetailservice.SpellDetailsResponse;
import services.spelldetailservice.SpellDetailsVisibilityRequest;
import services.spelldetailservice.SpellDetailsVisibilityResponse;
import services.spelldetailservice.bll.bo.SpellAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityAndPlayerBo;
import services.spelldetailservice.bll.bo.SpellDetailsAndVisibilityBo;
import java.util.Map;

public class SpellDetailBusinessLogicConverterImpl implements SpellDetailBusinessLogicConverter {
    public SpellAndPlayerBo getSpellAndPlayerBoFromSpellDetailsRequest(SpellDetailsRequest spellDetailsRequest) {
        Spell spell = spellDetailsRequest.getSpell();
        Player player = spellDetailsRequest.getPlayer();
        return SpellAndPlayerBo
                .builder()
                .spell(spell)
                .player(player)
                .build();
    }

    public SpellDetailsAndVisibilityAndPlayerBo getSpellDetailsAndVisibilityAndPlayerBoFromSpellDetailsVisibilityRequest(SpellDetailsVisibilityRequest spellDetailsVisibilityRequest) {
        Spell spell = spellDetailsVisibilityRequest.getSpell();
        Map<String, Visibility> visibilityMap = spellDetailsVisibilityRequest.getVisibilityMap();
        Player player = spellDetailsVisibilityRequest.getPlayer();
        return SpellDetailsAndVisibilityAndPlayerBo
                .builder()
                .spell(spell)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public SpellDetailsResponse getSpellDetailsResponseFromSpellDetailsAndVisibilityBo(SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo) {
        Spell spell = spellDetailsAndVisibilityBo.getSpell();
        return SpellDetailsResponse
                .builder()
                .spell(spell)
                .build();
    }

    public SpellDetailsVisibilityResponse getSpellDetailsVisibilityResponseFromSpellDetailsAndVisibilityBo(SpellDetailsAndVisibilityBo spellDetailsAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = spellDetailsAndVisibilityBo.getVisibilityMap();
        return SpellDetailsVisibilityResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public SpellDetailsAndVisibilityBo getSpellDetailsAndVisibilityBoFromSpellDetailsAndVisibilityAndPlayerBo(SpellDetailsAndVisibilityAndPlayerBo spellDetailsAndVisibilityAndPlayerBo) {
        Spell spell = spellDetailsAndVisibilityAndPlayerBo.getSpell();
        Map<String, Visibility> visibilityMap = spellDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        return SpellDetailsAndVisibilityBo
                .builder()
                .spell(spell)
                .visibilityMap(visibilityMap)
                .build();
    }
}