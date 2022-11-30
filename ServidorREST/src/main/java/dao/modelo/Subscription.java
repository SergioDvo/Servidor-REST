package dao.modelo;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Subscription {


    private int idReader;

    private int idNewspaper;

    private LocalDate signingDate;

    private LocalDate cancellationDate;

    public Subscription(int idReader, int idNewspaper, LocalDate signingDate, LocalDate cancellationDate) {
        this.idReader = idReader;
        this.idNewspaper = idNewspaper;
        this.signingDate = signingDate;
        this.cancellationDate = cancellationDate;
    }
}
