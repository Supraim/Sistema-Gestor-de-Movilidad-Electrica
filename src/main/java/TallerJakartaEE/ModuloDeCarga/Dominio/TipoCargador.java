package TallerJakartaEE.ModuloDeCarga.Dominio;

public enum TipoCargador {
    TIPO1(1),
    TIPO2(2);

    private final int id;
    TipoCargador(int id) { this.id = id; }
    public int getId() { return id; }

    public static TipoCargador getById(int id) {
        return switch (id) {
            case 1 -> TIPO1;
            case 2 -> TIPO2;
            default -> throw new IllegalArgumentException("Tipo de Cargador invalido.");
        };
    }
}
