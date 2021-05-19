package services.racedetailservice.dal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Race;
import services.racedetailservice.bll.bo.RaceBo;
import services.racedetailservice.bll.bo.RaceDetailsBo;
import services.racedetailservice.dal.dao.RaceDao;
import services.racedetailservice.dal.dao.RaceDetailsDao;

public class RaceDetailDataAccessConverterImpl implements RaceDetailDataAccessConverter {
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

    public RaceDetailsBo getRaceDetailsBoFromRaceDetailsDao(RaceDetailsDao raceDetailsDao) {
        String raceDetailsJson = raceDetailsDao.getRaceDetailsJson();
        if (raceDetailsJson == null)
            raceDetailsJson = "{}";
        Race race = null;
        if (!raceDetailsJson.equals("{}")) {
            ObjectMapper objectMapper = new ObjectMapper();
            race = Race
                    .builder()
                    .build();
            try {
                race = objectMapper.readValue(raceDetailsJson, Race.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return RaceDetailsBo
                .builder()
                .race(race)
                .build();
    }

    public RaceDetailsDao getRaceDetailsDaoFromLatestJsonObject(String latestJsonObject) {
        return RaceDetailsDao
                .builder()
                .raceDetailsJson(latestJsonObject)
                .build();
    }
}