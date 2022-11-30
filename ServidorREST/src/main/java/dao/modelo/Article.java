package dao.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Article {

    private int id;
    private String name_article;
    private String description;
    private int id_newspaper;

    private int id_type;

    public Article(int id, String name_article, String description, int id_newspaper, int id_type) {
        this.id = id;
        this.name_article = name_article;
        this.description = description;
        this.id_newspaper = id_newspaper;
        this.id_type = id_type;
    }

}
