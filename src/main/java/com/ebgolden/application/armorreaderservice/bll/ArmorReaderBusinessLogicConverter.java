package application.armorreaderservice.bll;

import application.armorreaderservice.ArmorFromResourceResponse;
import application.armorreaderservice.ArmorFromResourceRequest;
import application.armorreaderservice.bll.bo.ArmorBo;
import application.armorreaderservice.bll.bo.ArmorTypeBo;

public interface ArmorReaderBusinessLogicConverter {
    ArmorTypeBo getArmorTypeBoFromArmorFromResourceRequest(ArmorFromResourceRequest armorFromResourceRequest);

    ArmorFromResourceResponse getArmorFromResourceResponseFromArmorBo(ArmorBo armorBo);
}