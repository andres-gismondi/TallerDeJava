package dao;

import model.Billboard;

public interface BillboardDAO extends GenericDAO<Billboard>{
    public Billboard getByName(Billboard billboard);
}
