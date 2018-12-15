package app.service;

import app.model.dao.CategoryDAO;
import app.model.Category;
import app.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    UserDAO userDAO;

    public String createCategory(Category category, String token) {
        if (token.equals(userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getId() + "-" + UtilsImplementation.TOKEN)) {
            if (userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getType().equals(UtilsImplementation.ADMIN)) {
                Category cat = new Category();
                cat.setName(category.getName());
                cat.setWritePermisson(category.getWritePermisson());
                categoryDAO.persistir(cat);
                return UtilsImplementation.SUCCESS;
            }
            return UtilsImplementation.PERMISSON_DENIED;
        }
        return UtilsImplementation.ACCESS_DENIED;
    }

}
