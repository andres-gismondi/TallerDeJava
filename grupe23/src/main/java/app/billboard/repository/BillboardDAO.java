package app.billboard.repository;

import app.application.repository.GenericDAO;
import app.billboard.model.Billboard;

import java.util.List;

public interface BillboardDAO extends GenericDAO<Billboard> {
    public Billboard getBillboard(long id);

    public long getIdFromBillboard(String title);

    public Boolean billboardHasCategory(String title, String name);

    public Billboard getBillboardByTitle(String title);

    public Boolean billboardHasPublication(String title, String name);

    public List<Billboard> getBillboards();

}
