package application.armorreaderservice.dal;

import application.armorreaderservice.dal.dao.ArmorDao;
import application.armorreaderservice.dal.dao.ArmorTypeDao;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public class ArmorReaderDataAccessImpl implements ArmorReaderDataAccess {
    @Inject
    @Named("armorDirectory")
    private String armorDirectory;
    @Inject
    @Named("armorSuffix")
    private String armorSuffix;
    @Inject
    private ArmorReaderDataAccessConverter armorReaderDataAccessConverter;

    public ArmorDao getArmorDao(ArmorTypeDao armorTypeDao) {
        String armorType = armorTypeDao.getArmorType();
        String armorJson;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(armorDirectory + armorType + armorSuffix));
            armorJson = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            armorJson = "{}";
        }
        return armorReaderDataAccessConverter.getArmorDaoFromArmorJson(armorJson);
    }
}