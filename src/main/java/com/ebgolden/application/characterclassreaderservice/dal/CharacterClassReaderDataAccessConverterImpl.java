package com.ebgolden.application.characterclassreaderservice.dal;

import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassBo;
import com.ebgolden.application.characterclassreaderservice.bll.bo.CharacterClassNameBo;
import com.ebgolden.application.characterclassreaderservice.dal.dao.CharacterClassDao;
import com.ebgolden.application.characterclassreaderservice.dal.dao.CharacterClassNameDao;
import com.ebgolden.common.CharacterClass;
import com.ebgolden.common.Die;
import org.apache.commons.text.WordUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CharacterClassReaderDataAccessConverterImpl implements CharacterClassReaderDataAccessConverter {
    public CharacterClassNameDao getCharacterClassNameDaoFromCharacterClassNameBo(CharacterClassNameBo characterClassNameBo) {
        String characterClassName = characterClassNameBo.getCharacterClassName();
        return CharacterClassNameDao
                .builder()
                .characterClassName(characterClassName)
                .build();
    }

    public CharacterClassBo getCharacterClassBoFromCharacterClassDao(CharacterClassDao characterClassDao) {
        String characterClassJson = characterClassDao.getCharacterClassJson();
        String characterClassName = characterClassJson.substring(1, characterClassJson.indexOf(":") - 1);
        characterClassJson = characterClassJson.substring(characterClassJson.indexOf(":") + 2);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(characterClassJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject classFeatures = ((JSONObject)jsonObject.get("Class Features"));
        JSONArray hitPoints = (JSONArray)(((JSONObject)classFeatures.get("Hit Points")).get("content"));
        String hitDiceString = (String)hitPoints.get(0);
        Die hitDie = parseHitDiceStringAndReturnDie(hitDiceString);
        String baseHitPointsString = (String)hitPoints.get(1);
        int baseHitPoints = parseBaseHitPointsStringAndReturnBaseHitPoints(baseHitPointsString);
        String averageHitPointsPerLevelString = (String)hitPoints.get(2);
        int averageHitPointsPerLevel = parseAverageHitPointsPerLevelStringAndReturnAverageHitPointsPerLevel(averageHitPointsPerLevelString);
        JSONArray proficiencies = (JSONArray)(((JSONObject)classFeatures.get("Proficiencies")).get("content"));
        String armorString = (String)proficiencies.get(0);
        String[] proficientArmor = parseProficiencyStringAndReturnArray(armorString);
        String weaponsString = (String)proficiencies.get(1);
        String[] proficientWeapons = parseProficiencyStringAndReturnArray(weaponsString);
        String toolsString = (String)proficiencies.get(2);
        String[] proficientTools = parseProficiencyStringAndReturnArray(toolsString);
        String savingThrowsString = (String)proficiencies.get(3);
        String[] savingThrows = parseProficiencyStringAndReturnArray(savingThrowsString);
        String skillsString = (String)proficiencies.get(4);
        skillsString = filterSkillsString(skillsString);
        String[] skills = parseProficiencyStringAndReturnArray(skillsString);
        JSONArray descriptionAndEquipment = (JSONArray)(((JSONObject)classFeatures.get("Equipment")).get("content"));
        String equipmentString = (String)((JSONArray)descriptionAndEquipment.get(1)).get(0);
        String[] equipment = parseEquipmentStringAndReturnArray(equipmentString);
        JSONObject levelTable = (JSONObject)(((JSONObject)classFeatures.get("The " + characterClassName)).get("table"));
        JSONArray proficiencyBonuses = (JSONArray)levelTable.get("Proficiency Bonus");
        int[] proficiencyBonusPerLevel = parseProficiencyBonusesAndReturnArray(proficiencyBonuses);
        JSONArray features = (JSONArray)levelTable.get("Features");
        String[] featuresPerLevel = parseFeaturesAndReturnArray(features);
        Map<String, Object[]> featuresPerLevelMap = parseFeaturesInLevelMapAndReturnMap(levelTable);
        Map<String, JSONArray> featureMap = parseFeaturesAndReturnMap(classFeatures, characterClassName);
        CharacterClass characterClass = CharacterClass
                .builder()
                .id(characterClassName)
                .hitDie(hitDie)
                .baseHitPoints(baseHitPoints)
                .averageHitPointsPerLevel(averageHitPointsPerLevel)
                .proficientArmor(proficientArmor)
                .proficientWeapons(proficientWeapons)
                .proficientTools(proficientTools)
                .savingThrows(savingThrows)
                .skills(skills)
                .equipment(equipment)
                .proficiencyBonusPerLevel(proficiencyBonusPerLevel)
                .featuresPerLevel(featuresPerLevel)
                .featuresPerLevelMap(featuresPerLevelMap)
                .featureMap(featureMap)
                .build();
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

    private Die parseHitDiceStringAndReturnDie(String hitDiceString) {
        int startIndexOfHitDie = hitDiceString.indexOf(":**") + 6;
        int endIndexOfHitDie = hitDiceString.indexOf(" per ");
        int numberOfSidesOfHitDie = Integer.parseInt(hitDiceString.substring(startIndexOfHitDie, endIndexOfHitDie));
        return Die
                .builder()
                .numberOfSides(numberOfSidesOfHitDie)
                .build();
    }

    private int parseBaseHitPointsStringAndReturnBaseHitPoints(String baseHitPointsString) {
        int startIndexOfBaseHitPoints = baseHitPointsString.indexOf(":**") + 4;
        int endIndexOfBaseHitPoints = baseHitPointsString.indexOf(" +");
        return Integer.parseInt(baseHitPointsString.substring(startIndexOfBaseHitPoints, endIndexOfBaseHitPoints));
    }

    private int parseAverageHitPointsPerLevelStringAndReturnAverageHitPointsPerLevel(String averageHitPointsPerLevelString) {
        int startIndexOfAverageHitPointsPerLevel = averageHitPointsPerLevelString.indexOf(" (or ") + 5;
        int endIndexOfAverageHitPointsPerLevel = averageHitPointsPerLevelString.indexOf(")");
        return Integer.parseInt(averageHitPointsPerLevelString.substring(startIndexOfAverageHitPointsPerLevel, endIndexOfAverageHitPointsPerLevel));
    }

    private String filterSkillsString(String skillsString) {
        return WordUtils
                .capitalize(skillsString)
                .replace(", ", ",")
                .replace("And ", "")
                .replace("Choose Two From ", "");
    }

    private String[] parseProficiencyStringAndReturnArray(String proficiencyString) {
        int startIndexOfProficientItem = proficiencyString.indexOf(":**") + 4;
        String proficientItemString = proficiencyString.substring(startIndexOfProficientItem);
        proficientItemString = WordUtils
                .capitalize(proficientItemString)
                .replace(", ", ",");
        String[] proficientItemArray = new String[] {};
        if (!proficientItemString.contains("None"))
            proficientItemArray = proficientItemString.split(",");
        return proficientItemArray;
    }

    private String[] parseEquipmentStringAndReturnArray(String equipmentString) {
        equipmentString = WordUtils
                .capitalize(equipmentString)
                .replace(", ", ",")
                .replace(",And ", ",")
                .replace("And A", "And")
                .replace(" Of ", " And ")
                .replace(" And ", "&")
                .replace("(*a*) ", "")
                .replace("(*a*)", "")
                .replace(",(*b*) ", "|")
                .replace(" Or (*b*) ", "|")
                .replace(" Or (*b*)", "|")
                .replace(",Or (*c*) ", "|")
                .replace(" A ", "")
                .replace("A ", "")
                .replace(" Any ", "")
                .replace("Any ", "")
                .replace(" An ", "")
                .replace("An ", "")
                .replace("Other ", "")
                .replace("One ", "1 ")
                .replace("Two ", "2 ")
                .replace("Three ", "3 ")
                .replace("Four ", "4 ")
                .replace("Five ", "5 ")
                .replace("Six ", "6 ")
                .replace("Seven ", "7 ")
                .replace("Eight ", "8 ")
                .replace("Nine ", "9 ");
        return equipmentString.split(",");
    }

    private int[] parseProficiencyBonusesAndReturnArray(JSONArray proficiencyBonuses) {
        int levelCount = proficiencyBonuses.size();
        int[] proficiencyBonusPerLevel = new int[levelCount];
        for (int proficiencyBonusIndex = 0; proficiencyBonusIndex < levelCount; ++proficiencyBonusIndex)
            proficiencyBonusPerLevel[proficiencyBonusIndex] = Integer.parseInt(((String) proficiencyBonuses.get(proficiencyBonusIndex)).split(Pattern.quote("+"))[1]);
        return proficiencyBonusPerLevel;
    }

    private String[] parseFeaturesAndReturnArray(JSONArray features) {
        int levelCount = features.size();
        String[] featuresPerLevel = new String[levelCount];
        for (int featuresIndex = 0; featuresIndex < levelCount; ++featuresIndex) {
            String featuresAtLevel = (String)features.get(featuresIndex);
            featuresAtLevel = WordUtils
                    .capitalize(featuresAtLevel)
                    .replace(", ", ",");
            featuresPerLevel[featuresIndex] = featuresAtLevel;
        }
        return featuresPerLevel;
    }

    private Map<String, Object[]> parseFeaturesInLevelMapAndReturnMap(JSONObject levelTable) {
        Map<String, Object[]> featuresPerLevelMap = new HashMap<>();
        Object[] keySet = levelTable.keySet().toArray();
        Arrays.stream(keySet).forEach(key -> {
            if (!key.equals("Level") && !key.equals("Proficiency Bonus") && !key.equals("Features")) {
                JSONArray featurePerLevel = (JSONArray)levelTable.get(key);
                int levelCount = featurePerLevel.size();
                Object[] featurePerLevelArray = new Object[levelCount];
                for (int featuresIndex = 0; featuresIndex < levelCount; ++featuresIndex) {
                    String featurePerLevelString = (String)featurePerLevel.get(featuresIndex);
                    if (featurePerLevelString.equals("Unlimited"))
                        featurePerLevelString = String.valueOf(Integer.MAX_VALUE);
                    Object featuresAtLevel;
                    if (featurePerLevelString.contains("+")) {
                        featurePerLevelString = featurePerLevelString.replace(" ft.", "");
                        featuresAtLevel = Integer.parseInt(featurePerLevelString.split(Pattern.quote("+"))[1]);
                    }
                    else if (featurePerLevelString.equals("-"))
                        featuresAtLevel = 0;
                    else if ((featurePerLevelString.length() == 3) && (featurePerLevelString.contains("st") || featurePerLevelString.contains("nd") || featurePerLevelString.contains("rd") || featurePerLevelString.contains("th")))
                        featuresAtLevel = Integer.parseInt(featurePerLevelString.substring(0, 1));
                    else if ((featurePerLevelString.length() >= 3) && ((featurePerLevelString.charAt(1) == 'd') || (featurePerLevelString.charAt(2) == 'd'))) {
                        int numberOfSides = Integer.parseInt(featurePerLevelString.split(Pattern.quote("d"))[1]);
                        featuresAtLevel = Die
                                .builder()
                                .numberOfSides(numberOfSides)
                                .build();
                    }
                    else featuresAtLevel = Integer.parseInt(featurePerLevelString);
                    featurePerLevelArray[featuresIndex] = featuresAtLevel;
                }
                featuresPerLevelMap.put((String) key, featurePerLevelArray);
            }
        });
        return featuresPerLevelMap;
    }

    @SuppressWarnings("unchecked")
    private Map<String, JSONArray> parseFeaturesAndReturnMap(JSONObject classFeatures, String characterClassName) {
        Map<String, JSONArray> featureMap = new HashMap<>();
        Object[] keySet = classFeatures.keySet().toArray();
        Arrays.stream(keySet).forEach(key -> {
            if (!key.equals("content") && !key.equals("Hit Points") && !key.equals("Proficiencies") && !key.equals("Equipment") && !key.equals("The " + characterClassName)) {
                JSONArray feature;
                try {
                    JSONObject featureContent = (JSONObject)classFeatures.get(key);
                    feature = (JSONArray)featureContent.get("content");
                } catch (Exception e) {
                    feature = new JSONArray();
                    feature.add(classFeatures.get(key));
                }
                featureMap.put((String) key, feature);
            }
        });
        return featureMap;
    }
}