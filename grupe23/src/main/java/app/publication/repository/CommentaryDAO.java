package app.publication.repository;

import app.application.repository.GenericDAO;
import app.publication.model.Commentary;

public interface CommentaryDAO extends GenericDAO<Commentary> {

    public Commentary getCommentary(Commentary commentary);

}
