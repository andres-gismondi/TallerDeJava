import dao.DaoFactory;
import dao.PublicationDAO;
import dao.UserDAO;
import dao.UserDAOHibernateJPA;
import model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        //se crea categoria
        Category c = new Category();
        c.setName("Teacher");
        c.setWritePermisson(true);

        //se crea a un usuario
        User p = new Admin();
        p.setFirstName("Andy");
        p.setLastName("Kato");
        p.setEmail("bruceLee@gmail.com");
        p.setType("User");
        p.addCategory(c);

        //crear publicacion
        Publication publicacion = new Publication();
        publicacion.setBody("el body de una publicacion");
        publicacion.setCreator(p);
        publicacion.setDate(new Timestamp(System.currentTimeMillis()));
        publicacion.setEnableComments(true);
        List<Commentary> comentarios = new ArrayList<>();

        //crear comentario
        Commentary comentario = new Commentary();
        comentario.setBody("primer comentario");
        comentario.setCreator(p);
        comentario.setDate(new Timestamp(System.currentTimeMillis()));
        comentario.setTitle("Coment");
        comentario.setPublication(publicacion);
        //crear segundo comentario
        Commentary comentario2 = new Commentary();
        comentario2.setBody("primer comentario");
        comentario2.setCreator(p);
        comentario2.setDate(new Timestamp(System.currentTimeMillis()));
        comentario2.setTitle("Coment");
        comentario2.setPublication(publicacion);


        comentarios.add(comentario);
        comentarios.add(comentario2);
        publicacion.setCommentaries(comentarios);


        //Publicacion DAO
        PublicationDAO publicationDAO = DaoFactory.getPublicationDAO();
        publicationDAO.persistir(publicacion);

        //se le pide a daoFactory un usuarioDAO
        UserDAO user = DaoFactory.getUserDAO();

        //se agrega un usuario a la bd
        user.persistir(p);

        //se listan los usuarios
        List<User> users = user.listar();
        //User users = user.getUser("bruce");
        for (User user1 : users) {
            System.out.println(user1.getFirstName());

        }

        //se borra un usuario. este no anda, hay que consultar.
       user.borrar(10);

        //actualizando usuario
        User actualizar = user.recuperar(1);
        actualizar.setEmail("pepito@pepas.com");
        user.actualizar(actualizar);

    }
}


