package ua.nure.st.kpp.example.demo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.st.kpp.example.demo.dao.DAOFactory;
import ua.nure.st.kpp.example.demo.dao.IDAO;
import ua.nure.st.kpp.example.demo.dao.TypeDAO;  // Добавьте этот импорт
import ua.nure.st.kpp.example.demo.entity.Property;
import ua.nure.st.kpp.example.demo.form.AddPropertyForm;
import ua.nure.st.kpp.example.demo.form.DeletePropertyForm;
import ua.nure.st.kpp.example.demo.form.GetPropertyByTypeForm;

@Controller
public class PropertiesController {

    private IDAO dao = DAOFactory.getDAOInstance(TypeDAO.MySQL);  // Убедитесь, что вы получаете правильный тип DAO

    @RequestMapping(value = { "/", "/properties" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String showAllProperties(Model model) {
        List<Property> list = dao.getAllProperties();
        model.addAttribute("allProperties", list);
        return "propertiesPage";
    }

    @RequestMapping(value = { "/addProperty" }, method = RequestMethod.GET)
    public String showAddPropertyView(Model model) {
        AddPropertyForm addPropertyForm = new AddPropertyForm();
        model.addAttribute("addPropertyForm", addPropertyForm);
        return "addPropertyPage";
    }

    @RequestMapping(value = { "/addProperty" }, method = RequestMethod.POST)
    public String addProperty(Model model, AddPropertyForm addPropertyForm) {
        dao.addProperty(new Property(addPropertyForm.getName(), addPropertyForm.getType(),
                addPropertyForm.getAddress(), addPropertyForm.getPrice()));
        return "redirect:/properties";
    }

    @RequestMapping(value = { "/deletePropertiesByName" }, method = RequestMethod.GET)
    public String showDeletePropertyView(Model model) {
        DeletePropertyForm deletePropertyForm = new DeletePropertyForm();
        model.addAttribute("deletePropertyForm", deletePropertyForm);
        return "deletePropertiesByNamePage";
    }

    @RequestMapping(value = { "/deletePropertiesByName" }, method = RequestMethod.POST)
    public String deleteProperty(Model model, DeletePropertyForm deletePropertyForm) {
        dao.deletePropertiesByName(deletePropertyForm.getName());
        return "redirect:/properties";
    }

    @RequestMapping(value = { "/propertiesByType" }, method = RequestMethod.GET)
    public String showGetPropertiesByTypeView(Model model) {
        GetPropertyByTypeForm getPropertyByTypeForm = new GetPropertyByTypeForm();
        model.addAttribute("getPropertyByTypeForm", getPropertyByTypeForm);
        return "getPropertiesByTypePage";
    }

    @RequestMapping(value = { "/propertiesByType" }, method = RequestMethod.POST)
    public String getPropertiesByType(Model model, GetPropertyByTypeForm getPropertyByTypeForm) {
        List<Property> list = dao.getPropertiesByType(getPropertyByTypeForm.getType());
        model.addAttribute("allProperties", list);
        return "propertiesPage";
    }
}
