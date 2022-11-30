package dao.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Newspaper {

    private int id;
    private String nameNewspaper;
    private LocalDate releaseDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Newspaper newspaper = (Newspaper) o;
        return id == newspaper.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
