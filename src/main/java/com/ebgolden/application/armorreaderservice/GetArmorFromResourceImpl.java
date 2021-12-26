package application.armorreaderservice;

import application.armorreaderservice.bll.ArmorReaderBusinessLogic;
import application.armorreaderservice.bll.ArmorReaderBusinessLogicConverter;
import application.armorreaderservice.bll.bo.ArmorBo;
import application.armorreaderservice.bll.bo.ArmorTypeBo;
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