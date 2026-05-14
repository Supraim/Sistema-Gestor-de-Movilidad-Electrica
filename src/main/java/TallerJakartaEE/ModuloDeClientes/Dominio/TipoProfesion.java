package TallerJakartaEE.ModuloDeClientes.Dominio;

public enum TipoProfesion {
    TAXI(1),
    UBER(2),
    CABIFY(3);

    private final int id;
    TipoProfesion(int id) {this.id = id; }
    public int getId() { return id; }

    public static TipoProfesion getById(int id){
        return switch (id) {
            case 1 -> TAXI;
            case 2 -> UBER;
            case 3 -> CABIFY;
            default -> throw new IllegalArgumentException("Profesion Invalida");
        };
    }
}
