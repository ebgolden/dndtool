package application.armorreaderservice.bll;

import application.armorreaderservice.bll.bo.ArmorBo;
import application.armorreaderservice.bll.bo.ArmorTypeBo;
import application.armorreaderservice.dal.ArmorReaderDataAccess;
import application.armorreaderservice.dal.ArmorReaderDataAccessConverter;
import application.armorreaderservice.dal.dao.ArmorDao;
import application.armorreaderservice.dal.dao.ArmorTypeDao;
import com.google.inject.Inject;

public class ArmorReaderBusinessLogicImpl implements ArmorReaderBusinessLogic {
    @Inject
    private ArmorReaderDataAccessConverter armorReaderDataAccessConverter;
    @Inject
    private ArmorReaderDataAccess armorReaderDataAccess;

    public ArmorBo getArmorBo(ArmorTypeBo armorTypeBo) {
        ArmorTypeDao armorTypeDao = armorReaderDataAccessConverter.getArmorTypeDaoFromArmorNameBo(armorTypeBo);
        ArmorDao armorDao = armorReaderDataAccess.getArmorDao(armorTypeDao);
        if (armorDao.getArmorJson().equals("{}"))
            return ArmorBo
                    .builder()
                    .build();
        return armorReaderDataAccessConverter.getArmorBoFromArmorDao(armorDao);
    }
}