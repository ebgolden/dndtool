package com.ebgolden.application.armorreaderservice.bll;

import com.ebgolden.application.armorreaderservice.ArmorFromResourceResponse;
import com.ebgolden.application.armorreaderservice.ArmorFromResourceRequest;
import com.ebgolden.application.armorreaderservice.bll.bo.ArmorBo;
import com.ebgolden.application.armorreaderservice.bll.bo.ArmorTypeBo;
import com.ebgolden.common.Armor;

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