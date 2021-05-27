package services.dicedetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Die;
import objects.Player;
import services.dicedetailservice.bll.bo.DiceAndPlayerBo;
import services.dicedetailservice.bll.bo.DiceDetailsBo;
import services.dicedetailservice.dal.dao.DiceAndPlayerDao;
import services.dicedetailservice.dal.dao.DiceDetailsDao;

public class DiceDetailDataAccessConverterImpl implements DiceDetailDataAccessConverter {
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

    public DiceDetailsBo getDiceDetailsBoFromDiceDetailsDao(DiceDetailsDao diceDetailsDao) {
        String diceDetailsJson = diceDetailsDao.getDiceDetailsJson();
        if (diceDetailsJson == null)
            diceDetailsJson = "{}";
        ObjectMapper objectMapper = new ObjectMapper();
        Die[] dice;
        try {
            dice = objectMapper.readValue(diceDetailsJson, Die[].class);
        } catch (JsonProcessingException e) {
            dice = new Die[] {};
        }
        return DiceDetailsBo
                .builder()
                .dice(dice)
                .build();
    }

    public DiceDetailsDao getDiceDetailsDaoFromDiceDetailsJson(String diceDetailsJson) {
        return DiceDetailsDao
                .builder()
                .diceDetailsJson(diceDetailsJson)
                .build();
    }
}