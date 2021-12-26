package com.ebgolden.application.armorreaderservice.dal;

import com.ebgolden.application.armorreaderservice.dal.dao.ArmorDao;
import com.ebgolden.application.armorreaderservice.dal.dao.ArmorTypeDao;

public interface ArmorReaderDataAccess {
    ArmorDao getArmorDao(ArmorTypeDao armorTypeDao);
}