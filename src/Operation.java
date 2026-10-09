import java.time.LocalDate;
import java.util.UUID;

public class Operation {
    private final UUID id;

    private final TypeOperation type;

    private final double montant;

    private final LocalDate date;

    private final String description;

    public Operation(TypeOperation type, double montant, LocalDate date, String description) {
        this.type = type;
        this.montant = montant;
        this.date = date;
        this.description = description;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public TypeOperation getType() {
        return type;
    }

    public double getMontant() {
        return montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
