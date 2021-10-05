package userinterface.armordashboard;

import application.armorreaderservice.ArmorFromResourceRequest;
import application.armorreaderservice.GetArmorFromResource;
import application.armorreaderservice.module.ArmorReaderModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ArmorDashboard {
    private static ArmorFromResourceRequest armorFromResourceRequest;
    private static Field[] armorFromResourceRequestFields;
    private static JComponent[] graphicalComponents;

    public ArmorDashboard() {

    }

    public static void main(String[] args) {
        JFrame dashboardFrame = new JFrame();
        dashboardFrame.setVisible(true);
        Injector injector = Guice.createInjector(new ArmorReaderModule());
        GetArmorFromResource getArmorFromResource = injector.getInstance(GetArmorFromResource.class);
        armorFromResourceRequest = ArmorFromResourceRequest
                .builder()
                .build();
        armorFromResourceRequestFields = getRequestFields(armorFromResourceRequest);
        graphicalComponents = getGraphicalComponentsFromFields(armorFromResourceRequestFields);
        JButton submit = new JButton("Submit");
        populateDashboardFrame(dashboardFrame, graphicalComponents, submit);
        submit.addActionListener(e -> {
            armorFromResourceRequest = createRequestFromGraphicalComponents(graphicalComponents);
            System.out.println(armorFromResourceRequest.getArmorType());
            getArmorFromResource.getArmorFromResourceResponse(armorFromResourceRequest);
        });
    }

    private static Field[] getRequestFields(ArmorFromResourceRequest armorFromResourceRequest) {
        return armorFromResourceRequest.getClass().getFields();
    }

    private static JComponent[] getGraphicalComponentsFromFields(Field[] armorFromResourceRequestFields) {
        List<JComponent> graphicalComponents = new ArrayList<>();
        for (Field field : armorFromResourceRequestFields) {
            Class<?> fieldClass = field.getType();
            String fieldName = field.getName();
            JComponent graphicalComponent = null;
            if ((fieldClass == String.class) || (fieldClass == Integer.class) || (fieldClass == Double.class) || (fieldClass == Float.class))
                graphicalComponent = new JTextField();
            else if (fieldClass == Boolean.class)
                graphicalComponent = new JCheckBox();
            else if (!fieldClass.isPrimitive())
                graphicalComponent = new JMenu();
            if (graphicalComponent != null) {
                graphicalComponent.setName(fieldName);
            }
            graphicalComponents.add(graphicalComponent);
        }
        return graphicalComponents.toArray(new JComponent[] {});
    }

    private static void populateDashboardFrame(JFrame dashboardFrame, JComponent[] graphicalComponents, JButton submit) {
        for (JComponent graphicalComponent : graphicalComponents)
            dashboardFrame.add(graphicalComponent);
        dashboardFrame.add(submit);
    }

    private static ArmorFromResourceRequest createRequestFromGraphicalComponents(JComponent[] graphicalComponents) {
        ArmorFromResourceRequest armorFromResourceRequest = null;
        try {
            for (int graphicalComponentIndex = 0; graphicalComponentIndex < graphicalComponents.length; ++graphicalComponentIndex) {
                JComponent graphicalComponent = graphicalComponents[graphicalComponentIndex];
                Field field = armorFromResourceRequestFields[graphicalComponentIndex];
                Class<?> fieldClass = field.getType();
                if ((fieldClass == String.class)) {
                    String value = ((JTextField) graphicalComponent).getText();
                    armorFromResourceRequestFields[graphicalComponentIndex].set(armorFromResourceRequestFields[graphicalComponentIndex], value);
                } else if (fieldClass == Integer.class) {
                    int value = Integer.parseInt(((JTextField) graphicalComponent).getText());
                    armorFromResourceRequestFields[graphicalComponentIndex].set(armorFromResourceRequestFields[graphicalComponentIndex], value);
                } else if (fieldClass == Double.class) {
                    double value = Double.parseDouble(((JTextField) graphicalComponent).getText());
                    armorFromResourceRequestFields[graphicalComponentIndex].set(armorFromResourceRequestFields[graphicalComponentIndex], value);
                } else if (fieldClass == Float.class) {
                    float value = Float.parseFloat(((JTextField) graphicalComponent).getText());
                    armorFromResourceRequestFields[graphicalComponentIndex].set(armorFromResourceRequestFields[graphicalComponentIndex], value);
                } else if (fieldClass == Boolean.class) {
                    boolean value = ((JCheckBox) graphicalComponent).isSelected();
                    armorFromResourceRequestFields[graphicalComponentIndex].set(armorFromResourceRequestFields[graphicalComponentIndex], value);
                } else if (!fieldClass.isPrimitive()) {
                    Object value = ((JMenu) graphicalComponent).getSelectedObjects();
                    armorFromResourceRequestFields[graphicalComponentIndex].set(armorFromResourceRequestFields[graphicalComponentIndex], value);
                }
            }
            armorFromResourceRequest = ArmorFromResourceRequest
                    .builder()
                    .armorType(armorFromResourceRequestFields[0].get(armorFromResourceRequestFields[0]).toString())
                    .build();
        }
        catch (Exception ignored) {}
        return armorFromResourceRequest;
    }
}