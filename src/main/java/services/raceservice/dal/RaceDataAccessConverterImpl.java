package services.raceservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commonobjects.Race;
import services.raceservice.bll.bo.RaceBo;
import services.raceservice.dal.dao.RaceDao;

public class RaceDataAccessConverterImpl implements RaceDataAccessConverter {
    public RaceDao getRaceDaoFromRaceBo(RaceBo raceBo) {
        Race race = raceBo.getRace();
        ObjectMapper objectMapper = new ObjectMapper();
        String raceJson = "{}";
        try {
            raceJson = objectMapper.writeValueAsString(race);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return RaceDao
                .builder()
                .raceJson(raceJson)
                .build();
    }

    public RaceBo getRaceBoFromRaceDao(RaceDao raceDao) {
        String raceJson = raceDao.getRaceJson();
        if (raceJson == null)
            raceJson = "{}";
        Race race = null;
        if (!raceJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            race = Race
                    .builder()
                    .build();
            try {
                race = objectMapper.readValue(raceJson, Race.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return RaceBo
                .builder()
                .race(race)
                .build();
    }

    public RaceDao getRaceDaoFromRaceJson(String raceJson) {
        return RaceDao
                .builder()
                .raceJson(raceJson)
                .build();
    }
}