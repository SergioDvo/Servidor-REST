package domain.servicios;

import dao.DaoNewspaper;
import dao.modelo.Newspaper;
import jakarta.inject.Inject;


import java.util.List;

public class ServicesNewspaper {

    private final DaoNewspaper daoNewspaper;

    @Inject
    public ServicesNewspaper(DaoNewspaper daoNewspaper) {
        this.daoNewspaper = daoNewspaper;
    }


    public List<Newspaper> getNewspaperList() {
        return daoNewspaper.getAll();
    }


    public boolean addNewspaper(Newspaper newspaper) {
        if (daoNewspaper.getAll().contains(newspaper)) {
            return false;
        } else {
            daoNewspaper.save(newspaper);
            return true;
        }
    }

    public void deleteNewspaper(int id) {
        daoNewspaper.delete(id);
    }

    public boolean updateNewspaper(Newspaper newspaper) {
        return daoNewspaper.update(newspaper) > 0;
    }


}
