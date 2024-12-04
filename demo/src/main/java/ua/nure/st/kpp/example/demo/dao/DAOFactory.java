package ua.nure.st.kpp.example.demo.dao;

public class DAOFactory {

    public static IDAO getDAOInstance(TypeDAO type) {
        IDAO dao = null;
        
        // Здесь должна быть проверка типа, и возвращение соответствующего DAO
        if (type == TypeDAO.MySQL) {
            dao = new MySQLDAO();
        } else if (type == TypeDAO.COLLECTION) {
            dao = new CollectionLDAO();
        }

        return dao;
    }
}
