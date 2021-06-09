package domain.partyservice.bll;

import com.google.inject.Inject;
import common.Character;
import common.DungeonMaster;
import common.Party;
import common.Player;
import domain.partyservice.bll.bo.*;
import domain.partyservice.dal.PartyDataAccess;
import domain.partyservice.dal.PartyDataAccessConverter;
import domain.partyservice.dal.dao.*;

public class PartyBusinessLogicImpl implements PartyBusinessLogic {
    @Inject
    private PartyDataAccessConverter partyDataAccessConverter;
    @Inject
    private PartyDataAccess partyDataAccess;

    public PartyBo getPartyBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo) {
        PartyAndCharacterAndPlayerBo filteredPartyAndCharacterAndPlayerBo = filterPartyAndCharacterAndPlayerBo(partyAndCharacterAndPlayerBo);
        Character character = filteredPartyAndCharacterAndPlayerBo.getCharacter();
        PartyAndCharacterDao partyAndCharacterDao = partyDataAccessConverter.getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerBo(filteredPartyAndCharacterAndPlayerBo);
        PartyDao partyDao = partyDataAccess.getPartyDao(partyAndCharacterDao);
        PartyBo partyBo = partyDataAccessConverter.getPartyBoFromPartyDao(partyDao);
        return filterPartyBoForLeavingParty(partyBo, character);
    }

    public PartyBo getPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo) {
        PartyAndCharacterAndPlayerAndAcceptedByPartyBo filteredPartyAndCharacterAndPlayerAndAcceptedByPartyBo = filterPartyAndCharacterAndPlayerAndAcceptedByPartyBo(partyAndCharacterAndPlayerAndAcceptedByPartyBo);
        Character character = filteredPartyAndCharacterAndPlayerAndAcceptedByPartyBo.getCharacter();
        PartyAndCharacterDao partyAndCharacterDao = partyDataAccessConverter.getPartyAndCharacterDaoFromPartyAndCharacterAndPlayerAndAcceptedByPartyBo(filteredPartyAndCharacterAndPlayerAndAcceptedByPartyBo);
        PartyDao partyDao = partyDataAccess.getPartyDao(partyAndCharacterDao);
        PartyBo partyBo = partyDataAccessConverter.getPartyBoFromPartyDao(partyDao);
        return filterPartyBoForJoiningParty(partyBo, character);
    }

    public SplitPartiesBo getSplitPartiesBo(PartyAndSplitPartiesAndDungeonMasterBo partyAndSplitPartiesAndDungeonMasterBo) {
        PartyAndSplitPartiesDao partyAndSplitPartiesDao = partyDataAccessConverter.getPartyAndSplitPartiesDaoFromPartyAndSplitPartiesAndDungeonMasterBo(partyAndSplitPartiesAndDungeonMasterBo);
        SplitPartiesDao splitPartiesDao = partyDataAccess.getSplitPartiesDao(partyAndSplitPartiesDao);
        return partyDataAccessConverter.getSplitPartiesBoFromSplitPartiesDao(splitPartiesDao);
    }

    public PartyBo getPartyBo(PartiesAndDungeonMasterBo partiesAndDungeonMasterBo) {
        PartiesDao partiesDao = partyDataAccessConverter.getPartiesDaoFromPartiesAndDungeonMasterBo(partiesAndDungeonMasterBo);
        PartyDao partyDao = partyDataAccess.getPartyDao(partiesDao);
        return partyDataAccessConverter.getPartyBoFromPartyDao(partyDao);
    }

    private PartyAndCharacterAndPlayerBo filterPartyAndCharacterAndPlayerBo(PartyAndCharacterAndPlayerBo partyAndCharacterAndPlayerBo) {
        Party party = partyAndCharacterAndPlayerBo.getParty();
        Character character = partyAndCharacterAndPlayerBo.getCharacter();
        Player player = partyAndCharacterAndPlayerBo.getPlayer();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if (!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class))
            character = null;
        return PartyAndCharacterAndPlayerBo
                .builder()
                .party(party)
                .character(character)
                .player(player)
                .build();
    }

    private PartyAndCharacterAndPlayerAndAcceptedByPartyBo filterPartyAndCharacterAndPlayerAndAcceptedByPartyBo(PartyAndCharacterAndPlayerAndAcceptedByPartyBo partyAndCharacterAndPlayerAndAcceptedByPartyBo) {
        Party party = partyAndCharacterAndPlayerAndAcceptedByPartyBo.getParty();
        Character character = partyAndCharacterAndPlayerAndAcceptedByPartyBo.getCharacter();
        Player player = partyAndCharacterAndPlayerAndAcceptedByPartyBo.getPlayer();
        boolean acceptedByParty = partyAndCharacterAndPlayerAndAcceptedByPartyBo.isAcceptedByParty();
        String playerId = player.getId();
        String characterPlayerId = character.getPlayerId();
        if ((!playerId.equals(characterPlayerId) && (player.getClass() != DungeonMaster.class)) || !acceptedByParty)
            character = null;
        return PartyAndCharacterAndPlayerAndAcceptedByPartyBo
                .builder()
                .party(party)
                .character(character)
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