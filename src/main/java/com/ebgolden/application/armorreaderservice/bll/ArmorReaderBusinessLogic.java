package com.ebgolden.application.armorreaderservice.bll;

import com.ebgolden.application.armorreaderservice.bll.bo.ArmorBo;
import com.ebgolden.application.armorreaderservice.bll.bo.ArmorTypeBo;

public interface ArmorReaderBusinessLogic {
    ArmorBo getArmorBo(ArmorTypeBo armorTypeBo);
}