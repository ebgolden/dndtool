package application.characterclassreaderservice;

import application.characterclassreaderservice.module.CharacterClassReaderModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetCharacterClassFromResourceTest {
    private String characterClassName;
    private GetCharacterClassFromResource getCharacterClassFromResource;

    @BeforeEach
    public void setup() {
        characterClassName = "Fighter";
        Injector injector = Guice.createInjector(new CharacterClassReaderModule());
        getCharacterClassFromResource = injector.getInstance(GetCharacterClassFromResource.class);
    }

    @Test
    public void shouldReturnCharacterClass() {
        CharacterClassFromResourceRequest characterClassFromResourceRequest = CharacterClassFromResourceRequest
                .builder()
                .characterClassName(characterClassName)
                .build();
        CharacterClassFromResourceResponse characterClassFromResourceResponse = getCharacterClassFromResource.getCharacterClassFromResourceResponse(characterClassFromResourceRequest);
        for (String equipment : characterClassFromResourceResponse.getCharacterClass().getEquipment())
            System.out.println(equipment);
        Assertions.assertNotNull(characterClassFromResourceResponse.getCharacterClass(), "CharacterClass null.");
    }
}