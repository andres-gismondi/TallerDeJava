package app.model.dao;

import app.model.Commentary;

public interface CommentaryDAO extends GenericDAO<Commentary> {

    public Commentary getCommentary(Commentary commentary);

}
