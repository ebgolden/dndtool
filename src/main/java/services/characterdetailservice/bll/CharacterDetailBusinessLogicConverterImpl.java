package services.characterdetailservice.bll;

import objects.CharacterObject;
import objects.Player;
import services.characterdetailservice.CharacterDetailsRequest;
import services.characterdetailservice.CharacterDetailsResponse;
import services.characterdetailservice.bll.bo.CharacterAndPlayerBo;
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
}