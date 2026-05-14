package TallerJakartaEE.ModuloDeCarga.Dominio;

public enum EstadoCargador {
    EN_USO(1),
    DISPONIBLE(2),
    MANTENIMIENTO(3),
    FUERA_DE_SERVICIO(4);

    private final int id;
    EstadoCargador(int id) { this.id = id; }
    public int getId() { return id; }

    public static EstadoCargador getById(int id) {
        return switch (id) {
            case 1 -> EN_USO;
            case 2 -> DISPONIBLE;
            case 3 -> MANTENIMIENTO;
            case 4 -> FUERA_DE_SERVICIO;
            default -> throw new IllegalArgumentException("Tipo de Estado no soportado.");
        };
    }
}
