package dao;

import model.Billboard;

public interface BillboardDAO extends GenericDAO<Billboard>{
    public Billboard getBillboard(long id);

    public long getIdFromBillboard(String title);

    public Boolean billboardHasCategory(String title, String name);

    public Billboard getBillboardByTitle(String title);

    public Boolean billboardHasPublication(String title, String name);
}
