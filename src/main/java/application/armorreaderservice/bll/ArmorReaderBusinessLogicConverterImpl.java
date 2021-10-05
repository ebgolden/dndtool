package application.armorreaderservice.bll;

import application.armorreaderservice.ArmorFromResourceResponse;
import application.armorreaderservice.ArmorFromResourceRequest;
import application.armorreaderservice.bll.bo.ArmorBo;
import application.armorreaderservice.bll.bo.ArmorTypeBo;
import common.Armor;

public class ArmorReaderBusinessLogicConverterImpl implements ArmorReaderBusinessLogicConverter {
    public ArmorTypeBo getArmorTypeBoFromArmorFromResourceRequest(ArmorFromResourceRequest armorFromResourceRequest) {
        String armorType = armorFromResourceRequest.getArmorType();
        return ArmorTypeBo
                .builder()
                .armorType(armorType)
                .build();
    }

    public ArmorFromResourceResponse getArmorFromResourceResponseFromArmorBo(ArmorBo armorBo) {
        Armor[] armor = armorBo.getArmor();
        return ArmorFromResourceResponse
                .builder()
                .armor(armor)
                .build();
    }
}