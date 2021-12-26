package application.armorreaderservice.dal;

import application.armorreaderservice.bll.bo.ArmorBo;
import application.armorreaderservice.bll.bo.ArmorTypeBo;
import application.armorreaderservice.dal.dao.ArmorDao;
import application.armorreaderservice.dal.dao.ArmorTypeDao;
import common.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ArmorReaderDataAccessConverterImpl implements ArmorReaderDataAccessConverter {
    public ArmorTypeDao getArmorTypeDaoFromArmorNameBo(ArmorTypeBo armorTypeBo) {
        String armorType = armorTypeBo.getArmorType();
        return ArmorTypeDao
                .builder()
                .armorType(armorType)
                .build();
    }

    public ArmorBo getArmorBoFromArmorDao(ArmorDao armorDao) {
        String armorJson = armorDao.getArmorJson();
        String armorType = armorJson.substring(1, armorJson.indexOf(":") - 1);
        armorJson = armorJson.substring(armorJson.indexOf(":") + 2);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject)jsonParser.parse(armorJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject classFeatures = ((JSONObject)jsonObject.get("table"));
        JSONArray armorNames = (JSONArray)classFeatures.get("Armor");
        int numberOfArmorTypes = armorNames.size();
        JSONArray armorCosts = (JSONArray)classFeatures.get("Cost");
        JSONArray baseArmorClasses = (JSONArray)classFeatures.get("Armor Class (AC)");
        JSONArray armorStrengths = (JSONArray)classFeatures.get("Strength");
        JSONArray armorStealth = (JSONArray)classFeatures.get("Stealth");
        JSONArray armorWeights = (JSONArray)classFeatures.get("Weight");
        Pair<Integer, Time> don = parseDonOrDoffAndReturnDonOrDoffAmountAndTimePair(classFeatures.get("Don"));
        Pair<Integer, Time> doff = parseDonOrDoffAndReturnDonOrDoffAmountAndTimePair(classFeatures.get("Doff"));
        Armor[] armor = new Armor[numberOfArmorTypes];
        for (int armorIndex = 0; armorIndex < numberOfArmorTypes; ++armorIndex) {
            String armorName = parseArmorNameAndReturnArmorNameString(armorNames.get(armorIndex));
            Pair<Integer, Coin> cost = parseArmorCostAndReturnCostAmountAndCoinPair(armorCosts.get(armorIndex));
            int baseArmorClass = parseBaseArmorClassAndReturnBaseArmorClassInteger(baseArmorClasses.get(armorIndex));
            int strength = parseStrengthAndReturnStrengthInteger(armorStrengths.get(armorIndex));
            Advantage stealth = parseStealthAndReturnStealthAdvantage(armorStealth.get(armorIndex));
            int weight = parseWeightAndReturnWeightInteger(armorWeights.get(armorIndex));
            armor[armorIndex] = Armor
                    .builder()
                    .id(armorName)
                    .armorType(armorType)
                    .cost(cost)
                    .baseArmorClass(baseArmorClass)
                    .strength(strength)
                    .stealth(stealth)
                    .weight(weight)
                    .don(don)
                    .doff(doff)
                    .build();
        }
        return ArmorBo
                .builder()
                .armor(armor)
                .build();
    }

    public ArmorDao getArmorDaoFromArmorJson(String armorJson) {
        return ArmorDao
                .builder()
                .armorJson(armorJson)
                .build();
    }

    private String parseArmorNameAndReturnArmorNameString(Object armorName) {
        return (String)armorName;
    }

    private Pair<Integer, Coin> parseArmorCostAndReturnCostAmountAndCoinPair(Object armorCost) {
        String[] armorCostAndUnit = ((String)armorCost).split(" ");
        int armorCostAmount = Integer.parseInt(removeNonNumericalCharacters(armorCostAndUnit[0]));
        Coin coin = Coin.valueOf(formatAsConstantName(armorCostAndUnit[1]));
        return new ImmutablePair<>(armorCostAmount, coin);
    }

    private int parseBaseArmorClassAndReturnBaseArmorClassInteger(Object baseArmorClass) {
        String baseArmorClassString = removeNonNumericalCharacters((String)baseArmorClass);
        return Integer.parseInt(baseArmorClassString);
    }

    private int parseStrengthAndReturnStrengthInteger(Object strength) {
        String armorStrengthString = removeNonNumericalCharacters((String)strength);
        int armorStrengthNumber = 0;
        if (armorStrengthString.length() > 1)
            armorStrengthNumber = Integer.parseInt(armorStrengthString);
        return armorStrengthNumber;
    }

    private Advantage parseStealthAndReturnStealthAdvantage(Object stealth) {
        String armorStealthString = (String)stealth;
        return Advantage.valueOf(formatAsConstantName(armorStealthString));
    }

    private int parseWeightAndReturnWeightInteger(Object weight) {
        String armorWeightString = removeNonNumericalCharacters((String)weight);
        return Integer.parseInt(armorWeightString);
    }

    private String removeNonNumericalCharacters(String stringToFix) {
        return stringToFix.replaceAll("[^\\d]", "");
    }

    private String formatAsConstantName(String stringToFormat) {
        return stringToFormat.toUpperCase();
    }

    private Pair<Integer, Time> parseDonOrDoffAndReturnDonOrDoffAmountAndTimePair(Object donOrDoff) {
        String armorDon = (String)donOrDoff;
        String[] armorDonString = armorDon.split(" ");
        int armorDonNumber = 0;
        Time donTime = Time.SECOND;
        if (armorDonString.length > 1) {
            armorDonNumber = Integer.parseInt(armorDonString[0]);
            if (armorDonString[1].charAt(armorDonString[1].length() - 1) == 's')
                armorDonString[1] = armorDonString[1].substring(0, armorDonString[1].length() - 1);
            donTime = Time.valueOf(armorDonString[1].toUpperCase());
        }
        return new ImmutablePair<>(armorDonNumber, donTime);
    }
}