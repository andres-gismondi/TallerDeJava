package dao;

import model.Commentary;

public interface CommentaryDAO extends GenericDAO<Commentary> {

    public Commentary getCommentary(Commentary commentary);

}
