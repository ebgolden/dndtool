package com.ebgolden.domain.diceservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ebgolden.common.Die;
import com.ebgolden.common.Player;
import com.ebgolden.domain.diceservice.bll.bo.DiceAndPlayerBo;
import com.ebgolden.domain.diceservice.bll.bo.DiceBo;
import com.ebgolden.domain.diceservice.dal.dao.DiceAndPlayerDao;
import com.ebgolden.domain.diceservice.dal.dao.DiceDao;

public class DiceDataAccessConverterImpl implements DiceDataAccessConverter {
    public DiceAndPlayerDao getDiceAndPlayerDaoFromDiceAndPlayerBo(DiceAndPlayerBo diceAndPlayerBo) {
        Die[] dice = diceAndPlayerBo.getDice();
        Player player = diceAndPlayerBo.getPlayer();
        ObjectMapper objectMapper = new ObjectMapper();
        String diceJson = "{}";
        String playerJson = "{}";
        try {
            diceJson = objectMapper.writeValueAsString(dice);
            playerJson = objectMapper.writeValueAsString(player);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String diceAndPlayerJson = "{}";
        if (!diceJson.equals("{}") && !diceJson.equals("null") && !playerJson.equals("{}") && !playerJson.equals("null"))
            diceAndPlayerJson = "{" +
                    "\"dice\":" +
                    diceJson +
                    ",\"player\":" +
                    playerJson +
                    "}";
        return DiceAndPlayerDao
                .builder()
                .diceAndPlayerJson(diceAndPlayerJson)
                .build();
    }

    public DiceBo getDiceBoFromDiceDao(DiceDao diceDao) {
        String diceJson = diceDao.getDiceJson();
        if (diceJson == null)
            diceJson = "{}";
        ObjectMapper objectMapper = new ObjectMapper();
        Die[] dice;
        try {
            dice = objectMapper.readValue(diceJson, Die[].class);
        } catch (JsonProcessingException e) {
            dice = new Die[] {};
        }
        return DiceBo
                .builder()
                .dice(dice)
                .build();
    }

    public DiceDao getDiceDaoFromDiceJson(String diceJson) {
        return DiceDao
                .builder()
                .diceJson(diceJson)
                .build();
    }
}