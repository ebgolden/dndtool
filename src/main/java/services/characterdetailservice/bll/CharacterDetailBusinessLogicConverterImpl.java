package services.characterdetailservice.bll;

import objects.CharacterObject;
import objects.Player;
import services.characterdetailservice.CharacterDetailsRequest;
import services.characterdetailservice.CharacterDetailsResponse;
import services.characterdetailservice.CharacterDetailsVisibilityRequest;
import services.characterdetailservice.CharacterDetailsVisibilityResponse;
import services.characterdetailservice.bll.bo.CharacterAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityAndPlayerBo;
import services.characterdetailservice.bll.bo.CharacterDetailsAndVisibilityBo;

public class CharacterDetailBusinessLogicConverterImpl implements CharacterDetailBusinessLogicConverter {
    public CharacterAndPlayerBo getCharacterAndPlayerBoFromCharacterDetailsRequest(CharacterDetailsRequest characterDetailsRequest) {
        CharacterObject character = characterDetailsRequest.getCharacter();
        Player player = characterDetailsRequest.getPlayer();
        return CharacterAndPlayerBo
                .builder()
                .character(character)
                .player(player)
                .build();
    }

    public CharacterDetailsResponse getCharacterDetailsResponseFromCharacterDetailsBo(CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo) {
        CharacterObject character = characterDetailsAndVisibilityBo.getCharacter();
        return CharacterDetailsResponse
                .builder()
                .character(character)
                .build();
    }

    public CharacterDetailsVisibilityResponse getCharacterDetailsVisibilityResponseFromCharacterDetailsBo(CharacterDetailsAndVisibilityBo characterDetailsAndVisibilityBo) {
        String visibilityJson = characterDetailsAndVisibilityBo.getVisibilityJson();
        return CharacterDetailsVisibilityResponse
                .builder()
                .visibilityJson(visibilityJson)
                .build();
    }

    public CharacterDetailsAndVisibilityAndPlayerBo getCharacterDetailsAndVisibilityAndPlayerBoFromCharacterDetailsVisibilityRequest(CharacterDetailsVisibilityRequest characterDetailsVisibilityRequest) {
        CharacterObject characterObject = characterDetailsVisibilityRequest.getCharacter();
        String visibilityJson = characterDetailsVisibilityRequest.getVisibilityJson();
        Player player = characterDetailsVisibilityRequest.getPlayer();
        return CharacterDetailsAndVisibilityAndPlayerBo
                .builder()
                .character(characterObject)
                .visibilityJson(visibilityJson)
                .player(player)
                .build();
    }

    public CharacterDetailsAndVisibilityBo getCharacterDetailsAndVisibilityBoFromCharacterDetailsAndVisibilityAndPlayerBo(CharacterDetailsAndVisibilityAndPlayerBo characterDetailsAndVisibilityAndPlayerBo) {
        CharacterObject character = characterDetailsAndVisibilityAndPlayerBo.getCharacter();
        String visibilityJson = characterDetailsAndVisibilityAndPlayerBo.getVisibilityJson();
        return CharacterDetailsAndVisibilityBo
                .builder()
                .character(character)
                .visibilityJson(visibilityJson)
                .build();
    }
}