package com.ebgolden.domain.characterclassservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebgolden.common.CharacterClass;
import com.ebgolden.domain.characterclassservice.bll.bo.CharacterClassBo;
import com.ebgolden.domain.characterclassservice.dal.dao.CharacterClassDao;

public class CharacterClassDataAccessConverterImpl implements CharacterClassDataAccessConverter {
    public CharacterClassDao getCharacterClassDaoFromCharacterClassBo(CharacterClassBo characterClassBo) {
        CharacterClass characterClass = characterClassBo.getCharacterClass();
        ObjectMapper objectMapper = new ObjectMapper();
        String characterClassJson = "{}";
        try {
            characterClassJson = objectMapper.writeValueAsString(characterClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return CharacterClassDao
                .builder()
                .characterClassJson(characterClassJson)
                .build();
    }

    public CharacterClassBo getCharacterClassBoFromCharacterClassDao(CharacterClassDao characterClassDao) {
        String characterClassJson = characterClassDao.getCharacterClassJson();
        if (characterClassJson == null)
            characterClassJson = "{}";
        CharacterClass characterClass = null;
        if (!characterClassJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            characterClass = CharacterClass
                    .builder()
                    .build();
            try {
                characterClass = objectMapper.readValue(characterClassJson, CharacterClass.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return CharacterClassBo
                .builder()
                .characterClass(characterClass)
                .build();
    }

    public CharacterClassDao getCharacterClassDaoFromCharacterClassJson(String characterClassJson) {
        return CharacterClassDao
                .builder()
                .characterClassJson(characterClassJson)
                .build();
    }
}