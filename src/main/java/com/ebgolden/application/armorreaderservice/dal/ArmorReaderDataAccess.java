package application.armorreaderservice.dal;

import application.armorreaderservice.dal.dao.ArmorDao;
import application.armorreaderservice.dal.dao.ArmorTypeDao;

public interface ArmorReaderDataAccess {
    ArmorDao getArmorDao(ArmorTypeDao armorTypeDao);
}