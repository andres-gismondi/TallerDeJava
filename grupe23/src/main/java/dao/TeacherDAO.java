package dao;

import java.util.List;

public class TeacherDAO extends UserDAO{

    public void addPublication(BillboardDAO billboard, PublicationDAO publication){}

    public void deletePublication(BillboardDAO billboard, PublicationDAO publication){}

    public void modifyPublication(PublicationDAO publication){}

    public void enableCommentary(CommentaryDAO commentary){}

    public void enablePublicationCommentary(PublicationDAO publication){}

    public List<StudentDAO> interestedStudents(BillboardDAO billboard){ return null; }

    public void orderBillboard(BillboardDAO billboard){}

}
