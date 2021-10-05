package application.armorreaderservice.bll;

import application.armorreaderservice.bll.bo.ArmorBo;
import application.armorreaderservice.bll.bo.ArmorTypeBo;

public interface ArmorReaderBusinessLogic {
    ArmorBo getArmorBo(ArmorTypeBo armorTypeBo);
}