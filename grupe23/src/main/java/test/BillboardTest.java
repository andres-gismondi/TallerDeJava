package test;

import dao.*;
import model.*;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BillboardTest {
    @Test
    public void createBillboardWithPublicationAndCommentaryAndCategory(){
        //creando categoria
        Category cc = new Category();
        cc.setName("Profesores");
        cc.setWritePermisson(true);

        //creando usuario
        User p = new Admin();
        p.setFirstName("Penelope");
        p.setLastName("Cruz");
        p.setEmail("bruceLee@gmail.com");
        p.setType("admmin");
        p.addCategory(cc);
        UserDAO user = DaoFactory.getUserDAO();
        user.persistir(p);

        //creando comentario
        Commentary comentario = new Commentary();
        comentario.setBody("primer comentario");
        comentario.setCreator(p);
        comentario.setDate(new Timestamp(System.currentTimeMillis()));
        comentario.setTitle("Coment");
        //crear segundo comentario
        Commentary comentario2 = new Commentary();
        comentario2.setBody("segundo comentario");
        comentario2.setCreator(p);
        comentario2.setDate(new Timestamp(System.currentTimeMillis()));
        comentario2.setTitle("Coment");

        //crear publicacion
        Publication publicacion = new Publication();
        publicacion.setBody("el body de una publicacion");
        publicacion.setCreator(p);
        publicacion.setDate(new Timestamp(System.currentTimeMillis()));
        publicacion.setEnableComments(true);
        publicacion.addComentary(comentario);
        publicacion.addComentary(comentario2);


        //creando cartelera
        Billboard cartelera = new Billboard();
        //Category ca = new Category();
        CategoryDAO catDao = DaoFactory.getCategoryDAO();
        cartelera.addCategory(catDao.getByName("Profesores"));
        cartelera.setDescription("Cartelera de primer año");
        cartelera.addPublication(publicacion);
        cartelera.setTitle("JTTPS");

        BillboardDAO billboardDAO = DaoFactory.getBillboardDAO();
        billboardDAO.persistir(cartelera);
    }

    @Test
    public void deleteBillboard(){


        BillboardDAO billboardDAO = DaoFactory.getBillboardDAO();

        Billboard cartelera = billboardDAO.getByName("Cartelera de primer año");
        Publication publication = new Publication();
        List<Publication> publications = cartelera.getPublications();
        for (Publication pub : publications) {
            if(pub.getBody().equals("el body de una publicacion")){
                pub.removeAllCommentaries();
            }
        }
        billboardDAO.borrar(cartelera);
    }
}
