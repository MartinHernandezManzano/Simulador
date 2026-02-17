public class Main {
    public static void main(String[] args) {

        // CREAR EQUIPO LOCAL
        Equipo local = new Equipo("FC Barcelona");

        local.agregarJugador(new Portero("Joan García", 1, "FC Barcelona"));
        local.agregarJugador(new Defensa("Koundé", 23, "FC Barcelona"));
        local.agregarJugador(new Defensa("Cubarsí", 2, "FC Barcelona"));
        local.agregarJugador(new Medio("Pedri", 8, "FC Barcelona"));
        local.agregarJugador(new Medio("Gavi", 6, "FC Barcelona"));
        local.agregarJugador(new Delantero("Lamine Yamal", 19, "FC Barcelona"));
        local.agregarJugador(new Delantero("Raphinha", 11, "FC Barcelona"));

        // CREAR EQUIPO VISITANTE
        Equipo visitante = new Equipo("Real Madrid");

        visitante.agregarJugador(new Portero("Courtois", 1, "Real Madrid"));
        visitante.agregarJugador(new Defensa("Carvajal", 2, "Real Madrid"));
        visitante.agregarJugador(new Defensa("Rüdiger", 22, "Real Madrid"));
        visitante.agregarJugador(new Medio("Modric", 10, "Real Madrid"));
        visitante.agregarJugador(new Medio("Valverde", 15, "Real Madrid"));
        visitante.agregarJugador(new Delantero("Vinicius Jr.", 7, "Real Madrid"));
        visitante.agregarJugador(new Delantero("Mbappé", 9, "Real Madrid"));


        // Simular 3 partidos de ejemplo
        for (int i = 1; i <= 3; i++) {
            Partido partido = new Partido(local, visitante, i);
            partido.simular();
        }
    }
}