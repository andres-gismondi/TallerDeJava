package app.model.dao;

import app.model.Billboard;

import java.util.List;

public interface BillboardDAO extends GenericDAO<Billboard>{
    public Billboard getBillboard(long id);

    public long getIdFromBillboard(String title);

    public Boolean billboardHasCategory(String title, String name);

    public Billboard getBillboardByTitle(String title);

    public Boolean billboardHasPublication(String title, String name);

    public List<Billboard> getBillboards();

}
