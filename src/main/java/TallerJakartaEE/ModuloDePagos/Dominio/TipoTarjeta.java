package TallerJakartaEE.ModuloDePagos.Dominio;

public enum TipoTarjeta {
    DEBITO(1),
    CREDITO(2);

    private final int id;
    TipoTarjeta(int id) { this.id = id; }
    public int getId() { return id; }

    public static TipoTarjeta getById(int id) {
        return switch (id) {
            case 1 -> DEBITO;
            case 2 -> CREDITO;
            default -> throw new IllegalArgumentException("Tipo de Tarjeta invalida.");
        };
    }
}
