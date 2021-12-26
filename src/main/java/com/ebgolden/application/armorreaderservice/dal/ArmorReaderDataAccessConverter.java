package com.ebgolden.application.armorreaderservice.dal;

import com.ebgolden.application.armorreaderservice.bll.bo.ArmorBo;
import com.ebgolden.application.armorreaderservice.bll.bo.ArmorTypeBo;
import com.ebgolden.application.armorreaderservice.dal.dao.ArmorDao;
import com.ebgolden.application.armorreaderservice.dal.dao.ArmorTypeDao;

public interface ArmorReaderDataAccessConverter {
    ArmorTypeDao getArmorTypeDaoFromArmorNameBo(ArmorTypeBo armorTypeBo);

    ArmorBo getArmorBoFromArmorDao(ArmorDao armorDao);

    ArmorDao getArmorDaoFromArmorJson(String armorJson);
}