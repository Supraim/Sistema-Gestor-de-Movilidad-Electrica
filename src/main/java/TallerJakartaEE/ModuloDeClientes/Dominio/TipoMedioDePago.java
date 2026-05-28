package TallerJakartaEE.ModuloDeClientes.Dominio;

public enum TipoMedioDePago {
    TARJETA(1),
    CUENTA_UTE(2);

    private final int id;
    TipoMedioDePago(int id) { this.id = id; }
    public int getId() { return id; }

    public static TipoMedioDePago getById(int id) {
        return switch (id) {
            case 1 -> TARJETA;
            case 2 -> CUENTA_UTE;
            default -> throw new IllegalArgumentException("Medio de pago invalido.");
        };
    }
}
