package main;

import config.AppConfig;
import config.PersistenceConfig;
import config.SpringWebApp;
import dao.*;
import model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PersistenceConfig.class, SpringWebApp.class} )
@WebAppConfiguration
public class BillboardTest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    BillboardDAO billboardDAO;

    @Autowired
    PublicationDAO publicationDAO;

    @Test
    public void createYActualizarBillboardWithPublicationAndCommentaryAndCategory(){
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
        //UserDAO user = DaoFactory.getUserDAO();
        userDAO.persistir(p);

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
        cartelera.addCategory(cc);
        cartelera.setDescription("Cartelera de primer año");
        cartelera.addPublication(publicacion);
        cartelera.setTitle("JTTPS");

        //BillboardDAO billboardDAO = DaoFactory.getBillboardDAO();
        billboardDAO.persistir(cartelera);
        //testeo que la cartelera se creo en la base de datos.

        Billboard billboard =billboardDAO.recuperar((long)1);

        Assert.assertEquals (true, billboardDAO.getBillboard(billboardDAO.recuperar((long)1)).getDescription().equals("Cartelera de primer año"));

        //PublicationDAO publicationDAO = DaoFactory.getPublicationDAO();

        //pido la cartelera de la bd
        Billboard carteleraActualizar = billboardDAO.getBillboard(billboardDAO.recuperar((long)1));
        Commentary comentarioActualizar = carteleraActualizar.getPublication(publicationDAO.recuperar((long)1)).getCommentary((long)1);
        comentarioActualizar.setBody("comentario actualizadooo");
        //persisto la cartelera actualizada
        billboardDAO.actualizar(carteleraActualizar);
        Assert.assertEquals(true, billboardDAO.getBillboard(billboardDAO.recuperar((long)1)).getPublication(publicationDAO.recuperar((long)1)).getCommentary(1).getBody().equals("comentario actualizadooo"));
    }

    @Test
    public void deleteBillboard(){


        //BillboardDAO billboardDAO = DaoFactory.getBillboardDAO();

        Billboard cartelera = billboardDAO.getBillboard(billboardDAO.recuperar((long)1));
        Publication publication = new Publication();
        List<Publication> publications = cartelera.getPublications();
        for (Publication pub : publications) {
            if(pub.getBody().equals("el body de una publicacion")){
                pub.removeAllCommentaries();
            }
        }
        billboardDAO.borrar(cartelera);

        Assert.assertEquals(null, billboardDAO.getBillboard(billboardDAO.recuperar((long)1)));
    }

}
