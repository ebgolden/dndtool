package com.ebgolden.application.armorreaderservice.bll;

import com.ebgolden.application.armorreaderservice.ArmorFromResourceResponse;
import com.ebgolden.application.armorreaderservice.ArmorFromResourceRequest;
import com.ebgolden.application.armorreaderservice.bll.bo.ArmorBo;
import com.ebgolden.application.armorreaderservice.bll.bo.ArmorTypeBo;

public interface ArmorReaderBusinessLogicConverter {
    ArmorTypeBo getArmorTypeBoFromArmorFromResourceRequest(ArmorFromResourceRequest armorFromResourceRequest);

    ArmorFromResourceResponse getArmorFromResourceResponseFromArmorBo(ArmorBo armorBo);
}