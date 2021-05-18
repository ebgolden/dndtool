package services.partyservice.bll;

import com.google.inject.Inject;
import objects.Character;
import objects.DungeonMaster;
import objects.Party;
import objects.Player;
import services.partyservice.bll.bo.*;
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
        PartyBo filteredPartyBo = filterPartyBoForLeavingParty(partyBo, character);
        return partyBusinessLogicConverter.getLeftPartyBoFromPartyBo(filteredPartyBo);
    }

    public JoinedPartyBo getJoinedPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo) {
        PartyAndCharacterAndPlayerAndAcceptedByPartyBo filteredPartyAndCharacterAndPlayerAndAcceptedByPartyBo = filterPartyAndCharacterAndPlayerAndAcceptedByPartyBo(partyAndCharacterAndPlayerAndAcceptedByPartyBo);
        Character character = filteredPartyAndCharacterAndPlayerAndAcceptedByPartyBo.getCharacter();
        PartyAndCharacterDao partyAndCharacterDao = partyDataAccessConverter.getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerAndAcceptedByPartyBo(filteredPartyAndCharacterAndPlayerAndAcceptedByPartyBo);
        PartyDao partyDao = partyDataAccess.getPartyDao(partyAndCharacterDao);
        PartyBo partyBo = partyDataAccessConverter.getPartyBoFromPartyDao(partyDao);
        PartyBo filteredPartyBo = filterPartyBoForJoiningParty(partyBo, character);
        return partyBusinessLogicConverter.getJoinedPartyBoFromPartyBo(filteredPartyBo);
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

    PartyAndCharacterAndPlayerAndAcceptedByPartyBo filterPartyAndCharacterAndPlayerAndAcceptedByPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo) {
        Party party = partyAndCharacterAndPlayerAndAcceptedByPartyBo.getParty();
        Character character = partyAndCharacterAndPlayerAndAcceptedByPartyBo.getCharacter();
        Player player = partyAndCharacterAndPlayerAndAcceptedByPartyBo.getPlayer();
        boolean acceptedByParty = partyAndCharacterAndPlayerAndAcceptedByPartyBo.isAcceptedByParty();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        Character filteredCharacter = character;
        if ((!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) || !acceptedByParty)
            filteredCharacter = null;
        return PartyAndCharacterAndPlayerAndAcceptedByPartyBo
                .builder()
                .party(party)
                .character(filteredCharacter)
                .player(player)
                .acceptedByParty(acceptedByParty)
                .build();
    }

    private PartyBo filterPartyBoForLeavingParty(PartyBo partyBo, Character character) {
        PartyBo filteredPartyBo = partyBo;
        Party party = partyBo.getParty();
        if (isCharacterAndPartyAndPartyCharactersNotNull(character, party)) {
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
        else filteredPartyBo = isCharacterOrPartyNullAndReturnFilteredPartyBo(character, party, filteredPartyBo);
        return filteredPartyBo;
    }

    private PartyBo filterPartyBoForJoiningParty(PartyBo partyBo, Character character) {
        PartyBo filteredPartyBo = partyBo;
        Party party = partyBo.getParty();
        if (isCharacterAndPartyAndPartyCharactersNotNull(character, party)) {
            String characterId = character.getId();
            for (int characterIndex = 0; characterIndex < party.getCharacters().length; ++characterIndex) {
                Character partyCharacter = party.getCharacters()[characterIndex];
                String partyCharacterId = partyCharacter.getId();
                if (partyCharacterId.equals(characterId))
                    break;
                else if (characterIndex == (party.getCharacters().length - 1)) {
                    filteredPartyBo = PartyBo
                            .builder()
                            .build();
                }
            }
        }
        else filteredPartyBo = isCharacterOrPartyNullAndReturnFilteredPartyBo(character, party, filteredPartyBo);
        return filteredPartyBo;
    }

    private boolean isCharacterAndPartyAndPartyCharactersNotNull(Character character, Party party) {
        return (character != null) && (party != null) && (party.getCharacters() != null);
    }

    private PartyBo isCharacterOrPartyNullAndReturnFilteredPartyBo(Character character, Party party, PartyBo partyBo) {
        PartyBo filteredPartyBo = partyBo;
        if ((character == null) || (party == null)) {
            filteredPartyBo = PartyBo
                    .builder()
                    .build();
        }
        return filteredPartyBo;
    }
}