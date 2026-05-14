package TallerJakartaEE.ModuloDeCarga.Dominio;

public enum EstadoCarga {
    CARGANDO(1),
    COMPLETA(2),
    EXCEDIDA(3);

    private final int id;
    EstadoCarga(int id) { this.id = id; }
    public int getId() { return id; }

    public static EstadoCarga getById(int id) {
        return switch (id) {
            case 1 -> POR_COMPLETAR;
            case 2 -> COMPLETA;
            case 3 -> EXCEDIDA;
            default -> throw new IllegalArgumentException("Tipo de Estado de Carga no soportado.");
        };
    }
}
