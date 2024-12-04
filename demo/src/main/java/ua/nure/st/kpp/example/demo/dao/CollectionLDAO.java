package ua.nure.st.kpp.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ua.nure.st.kpp.example.demo.entity.Property;

public class CollectionLDAO implements IDAO {

    private static List<Property> properties = new ArrayList<Property>();

    static {
        // Добавляем несколько примеров недвижимости
        properties.add(new Property("House1", "House", "123 Main St", 50000));
        properties.add(new Property("Apartment1", "Apartment", "456 Oak St", 35000));
        properties.add(new Property("Villa", "Villa", "789 Palm St", 100000));
    }

    @Override
    public void addProperty(Property property) {
        properties.add(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return properties;
    }

    @Override
    public List<Property> getPropertiesByType(String type) {
        return properties.stream()
                         .filter(property -> property.getType().equalsIgnoreCase(type))
                         .collect(Collectors.toList());
    }

    @Override
    public void deletePropertiesByName(String name) {
        properties = properties.stream()
                               .filter(property -> !property.getName().equalsIgnoreCase(name))
                               .collect(Collectors.toList());
    }
}

