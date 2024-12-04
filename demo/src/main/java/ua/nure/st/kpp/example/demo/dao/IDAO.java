package ua.nure.st.kpp.example.demo.dao;

import ua.nure.st.kpp.example.demo.entity.Property;
import java.util.List;

public interface IDAO {
    void addProperty(Property property);  // Метод для добавления объекта недвижимости
    List<Property> getAllProperties();    // Метод для получения всех объектов недвижимости
    List<Property> getPropertiesByType(String type); // Метод для получения объектов по типу
    void deletePropertiesByName(String name); // Метод для удаления объектов по имени
}
