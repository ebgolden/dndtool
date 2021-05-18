package services.partyservice.bll;

import com.google.inject.Inject;
import objects.Character;
import objects.DungeonMaster;
import objects.Party;
import objects.Player;
import services.partyservice.bll.bo.LeftPartyBo;
import services.partyservice.bll.bo.PartyBo;
import services.partyservice.bll.bo.PartyAndCharacterAndPlayerBo;
import services.partyservice.dal.PartyDataAccess;
import services.partyservice.dal.PartyDataAccessConverter;
import services.partyservice.dal.dao.PartyAndCharacterDao;
import services.partyservice.dal.dao.PartyDao;

public class PartyBusinessLogicImpl implements PartyBusinessLogic {
    @Inject
    private PartyDataAccessConverter partyDataAccessConverter;
    @Inject
    private PartyDataAccess partyDataAccess;
    @Inject
    private PartyBusinessLogicConverter partyBusinessLogicConverter;

    public LeftPartyBo getLeftPartyBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo) {
        PartyAndCharacterAndPlayerBo filteredPartyAndCharacterAndPlayerBo = filterPartyAndCharacterAndPlayerBo(partyAndCharacterAndPlayerBo);
        Character character = filteredPartyAndCharacterAndPlayerBo.getCharacter();
        PartyAndCharacterDao partyAndCharacterDao = partyDataAccessConverter.getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerBo(filteredPartyAndCharacterAndPlayerBo);
        PartyDao partyDao = partyDataAccess.getPartyDao(partyAndCharacterDao);
        PartyBo partyBo = partyDataAccessConverter.getPartyBoFromPartyDao(partyDao);
        PartyBo filteredPartyBo = filterPartyBo(partyBo, character);
        return partyBusinessLogicConverter.getLeftPartyBoFromPartyBo(filteredPartyBo);
    }

    private PartyAndCharacterAndPlayerBo filterPartyAndCharacterAndPlayerBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo) {
        Party party = partyAndCharacterAndPlayerBo.getParty();
        Character character = partyAndCharacterAndPlayerBo.getCharacter();
        Player player = partyAndCharacterAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        Character filteredCharacter = character;
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class))
            filteredCharacter = null;
        return PartyAndCharacterAndPlayerBo
                .builder()
                .party(party)
                .character(filteredCharacter)
                .player(player)
                .build();
    }

    private PartyBo filterPartyBo(PartyBo partyBo, Character character) {
        PartyBo filteredPartyBo = partyBo;
        Party party = partyBo.getParty();
        if ((character != null) && (party != null) && (party.getCharacters() != null)) {
            String characterId = character.getId();
            for (Character partyCharacter : party.getCharacters()) {
                String partyCharacterId = partyCharacter.getId();
                if (partyCharacterId.equals(characterId)) {
                    filteredPartyBo = PartyBo
                            .builder()
                            .build();
                    break;
                }
            }
        }
        else if ((character == null) || (party == null)) {
            filteredPartyBo = PartyBo
                    .builder()
                    .build();
        }
        return filteredPartyBo;
    }
}