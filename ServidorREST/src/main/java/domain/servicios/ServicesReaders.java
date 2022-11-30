package domain.servicios;

import dao.DaoReadArticle;
import dao.DaoReaders;
import dao.DaoSubscribe;
import dao.modelo.Reader;
import dao.modelo.Subscription;
import jakarta.inject.Inject;


import java.time.LocalDate;
import java.util.List;

public class ServicesReaders {

    private final DaoReaders daoReaders;
    private final DaoSubscribe daoSubscribe;
    private final DaoReadArticle daoReadArticle;

    @Inject
    public ServicesReaders(DaoReaders daoReaders, DaoSubscribe daoSubscribe, DaoReadArticle daoReadArticle) {
        this.daoReaders = daoReaders;
        this.daoSubscribe = daoSubscribe;
        this.daoReadArticle = daoReadArticle;
    }

    public void addReader(Reader r) {
        daoReaders.save(r);
    }

    public void updateReader(Reader r) {
        daoReaders.update(r);
    }

    public Reader getReaderById(int id) {
        return daoReaders.getById(id);
    }

    public List<Reader> getReadersList() {
        return daoReaders.getAll();
    }


    public List<Reader> getReadersListByArticleType(int type) {
        return daoReaders.getAll(type);
    }

    public void addReaderArticle(int idReader, int idArticle, int rating) {
        daoReadArticle.saveReadArticle(idReader, idArticle, rating);
    }

    public List<Reader> getReadersListByNewspaperDate() {
        return daoReaders.readersByNewspaperQuery();
    }

    public void deleteReader(int id) {
        daoReaders.delete(id);
    }

    public List<Reader> getReadersListByNewspaper(int idnewspaper) {
        return daoReaders.getAllNewpaper(idnewspaper);
    }

    public int counterReaders(int idArticle) {
        return daoReaders.counterReadersQuery(idArticle);
    }

    public List<Subscription> getSubscriptionsList(int idReader) {
        return daoSubscribe.getAll(idReader);
    }

    public void addSubscription(Subscription subscription) {
        daoSubscribe.save(subscription);
    }

    public void cancelSubscription(Subscription subscription) {
        subscription.setCancellationDate(LocalDate.now());
        daoSubscribe.update(subscription);
    }

}
