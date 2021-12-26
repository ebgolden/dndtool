package application.armorreaderservice;

import application.armorreaderservice.module.ArmorReaderModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetArmorFromResourceTest {
    private String armorType;
    private GetArmorFromResource getArmorFromResource;

    @BeforeEach
    public void setup() {
        armorType = "Heavy Armor";
        Injector injector = Guice.createInjector(new ArmorReaderModule());
        getArmorFromResource = injector.getInstance(GetArmorFromResource.class);
    }

    @Test
    public void shouldReturnArmor() {
        ArmorFromResourceRequest armorFromResourceRequest = ArmorFromResourceRequest
                .builder()
                .armorType(armorType)
                .build();
        ArmorFromResourceResponse armorFromResourceResponse = getArmorFromResource.getArmorFromResourceResponse(armorFromResourceRequest);
        Assertions.assertNotNull(armorFromResourceResponse.getArmor(), "Armor null.");
    }
}