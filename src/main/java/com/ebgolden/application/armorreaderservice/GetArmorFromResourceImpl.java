package com.ebgolden.application.armorreaderservice;

import com.ebgolden.application.armorreaderservice.bll.ArmorReaderBusinessLogic;
import com.ebgolden.application.armorreaderservice.bll.ArmorReaderBusinessLogicConverter;
import com.ebgolden.application.armorreaderservice.bll.bo.ArmorBo;
import com.ebgolden.application.armorreaderservice.bll.bo.ArmorTypeBo;
import com.google.inject.Inject;

public class GetArmorFromResourceImpl implements GetArmorFromResource {
    @Inject
    private ArmorReaderBusinessLogicConverter armorReaderBusinessLogicConverter;
    @Inject
    private ArmorReaderBusinessLogic armorReaderBusinessLogic;

    public ArmorFromResourceResponse getArmorFromResourceResponse(ArmorFromResourceRequest armorFromResourceRequest) {
        ArmorTypeBo armorTypeBo = armorReaderBusinessLogicConverter.getArmorTypeBoFromArmorFromResourceRequest(armorFromResourceRequest);
        ArmorBo armorBo = armorReaderBusinessLogic.getArmorBo(armorTypeBo);
        return armorReaderBusinessLogicConverter.getArmorFromResourceResponseFromArmorBo(armorBo);
    }
}