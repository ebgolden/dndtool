package userinterface.armordashboard.module;

import application.armorreaderservice.GetArmorFromResource;
import application.armorreaderservice.GetArmorFromResourceImpl;
import com.google.inject.AbstractModule;

public class ArmorDashboardModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GetArmorFromResource.class).to(GetArmorFromResourceImpl.class);
    }
}