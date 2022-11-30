package dao.modelo;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadArticles {

    private int id;
    private int idReader;
    private int idArticle;

    private int rating;

    public ReadArticles(int idReader, int idArticle) {
        this.idReader = idReader;
        this.idArticle = idArticle;
    }
}
