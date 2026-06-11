package TallerJakartaEE.ModuloDePagos.Dominio;

import lombok.Getter;

@Getter
public enum TipoTarjeta {
    MASTER(1),
    VISA(2),
    AMEX(3);

    private final int id;
    TipoTarjeta(int id) { this.id = id; }

    public static TipoTarjeta getById(int id) {
        return switch (id) {
            case 1 -> MASTER;
            case 2 -> VISA;
            case 3 -> AMEX;
            default -> throw new IllegalArgumentException("Tipo de Tarjeta invalida.");
        };
    }
}
