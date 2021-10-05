package application.armorreaderservice.dal;

import application.armorreaderservice.bll.bo.ArmorBo;
import application.armorreaderservice.bll.bo.ArmorTypeBo;
import application.armorreaderservice.dal.dao.ArmorDao;
import application.armorreaderservice.dal.dao.ArmorTypeDao;

public interface ArmorReaderDataAccessConverter {
    ArmorTypeDao getArmorTypeDaoFromArmorNameBo(ArmorTypeBo armorTypeBo);

    ArmorBo getArmorBoFromArmorDao(ArmorDao armorDao);

    ArmorDao getArmorDaoFromArmorJson(String armorJson);
}