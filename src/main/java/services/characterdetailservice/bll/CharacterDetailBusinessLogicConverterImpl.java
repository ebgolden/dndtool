package services.characterdetailservice.bll;

import objects.Character;
import objects.Player;
import objects.Visibility;
import services.characterdetailservice.CharacterDetailsRequest;
import services.characterdetailservice.CharacterDetailsResponse;
import services.characterdetailservice.CharacterDetailsVisibilityRequest;
import services.characterdetailservice.CharacterDetailsVisibilityResponse;
import services.characterdetailservice.bll.bo.CharacterAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityBo;
import java.util.Map;

public class CharacterDetailBusinessLogicConverterImpl implements CharacterDetailBusinessLogicConverter {
    public CharacterAndPlayerBo getCharacterAndPlayerBoFromCharacterDetailsRequest(CharacterDetailsRequest characterDetailsRequest) {
        Character character = characterDetailsRequest.getCharacter();
        Player player = characterDetailsRequest.getPlayer();
        return CharacterAndPlayerBo
                .builder()
                .character(character)
                .player(player)
                .build();
    }

    public CharacterDetailsResponse getCharacterDetailsResponseFromCharacterDetailsBo(CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo) {
        Character character = characterDetailsAndVisibilityBo.getCharacter();
        return CharacterDetailsResponse
                .builder()
                .character(character)
                .build();
    }

    public CharacterDetailsVisibilityResponse getCharacterDetailsVisibilityResponseFromCharacterDetailsAndVisibilityBo(CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo) {
        Map<String, Visibility> visibilityMap = characterDetailsAndVisibilityBo.getVisibilityMap();
        return CharacterDetailsVisibilityResponse
                .builder()
                .visibilityMap(visibilityMap)
                .build();
    }

    public CharacterDetailsAndVisibilityAndPlayerBo getCharacterDetailsAndVisibilityAndPlayerBoFromCharacterDetailsVisibilityRequest(CharacterDetailsVisibilityRequest characterDetailsVisibilityRequest) {
        Character character = characterDetailsVisibilityRequest.getCharacter();
        Map<String, Visibility> visibilityMap = characterDetailsVisibilityRequest.getVisibilityMap();
        Player player = characterDetailsVisibilityRequest.getPlayer();
        return CharacterDetailsAndVisibilityAndPlayerBo
                .builder()
                .character(character)
                .visibilityMap(visibilityMap)
                .player(player)
                .build();
    }

    public CharacterDetailsAndVisibilityBo getCharacterDetailsAndVisibilityBoFromCharacterDetailsAndVisibilityAndPlayerBo(CharacterDetailsAndVisibilityAndPlayerBo characterDetailsAndVisibilityAndPlayerBo) {
        Character character = characterDetailsAndVisibilityAndPlayerBo.getCharacter();
        Map<String, Visibility> visibilityMap = characterDetailsAndVisibilityAndPlayerBo.getVisibilityMap();
        return CharacterDetailsAndVisibilityBo
                .builder()
                .character(character)
                .visibilityMap(visibilityMap)
                .build();
    }
}