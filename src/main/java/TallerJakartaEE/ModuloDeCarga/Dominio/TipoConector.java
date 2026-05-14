package TallerJakartaEE.ModuloDeCarga.Dominio;

public enum TipoConector {
    NORMAL(1),
    RAPIDA(2);

    private final int id;
    TipoConector(int id) { this.id = id; }
    public int getId() { return id; }

    public static TipoConector getById(int id) {
        return switch (id) {
            case 1 -> NORMAL;
            case 2 -> RAPIDA;
            default -> throw new IllegalArgumentException("Tipo de Conector no soportado.");
        };
    }
}
