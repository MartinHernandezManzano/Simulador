import java.util.ArrayList;
import java.util.List;

// Equipo contiene a los jugadores y sus estadísticas globales
// Usamos ArrayList porque es flexible y fácil de recorrer
public class Equipo {
    private String nombre;
    private List<Futbolista> jugadores;  // polimorfismo: guardamos cualquier hijo de Futbolista
    private int golesTotales;
    private int pasesTotales;
    private int paradasTotales;
    private int tirosTotales;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();  // inicializamos la lista vacía
        this.golesTotales = 0;
        this.pasesTotales = 0;
        this.paradasTotales = 0;
        this.tirosTotales = 0;
    }

    // vamos añadiendo jugadores uno a uno hasta completar los 7
    public void agregarJugador(Futbolista jugador) {
        jugadores.add(jugador);
    }

    // getters básicos para acceder desde fuera
    public String getNombre() {
        return nombre;
    }

    public List<Futbolista> getJugadores() {
        return jugadores;
    }

    public int getGolesTotales() {
        return golesTotales;
    }

    public int getPasesTotales() {
        return pasesTotales;
    }

    public int getParadasTotales() {
        return paradasTotales;
    }
    public int getTirosTotales() {
        return tirosTotales;
    }

    // recorre todos los jugadores y suma sus estadísticas individuales
    // así mantenemos actualizados los totales del equipo
    public void actualizarEstadisticas() {
        golesTotales = 0;
        pasesTotales = 0;
        paradasTotales = 0;
        tirosTotales = 0;

        for (Futbolista j : jugadores) {
            golesTotales += j.getGoles();
            pasesTotales += j.getPases();
            paradasTotales += j.getParadas();
            tirosTotales += j.getTiros();
        }
    }

    // muestra la plantilla completa y los totales del equipo
    // útil para el archivo de estadísticas finales
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("⚽ EQUIPO: ").append(nombre).append("\n");
        sb.append("================================\n");
        for (Futbolista j : jugadores) {
            sb.append(j.toString()).append("\n");
        }
        sb.append("================================\n");
        sb.append("Goles: ").append(golesTotales);
        sb.append(" | Pases: ").append(pasesTotales);
        sb.append(" | Tiros: ").append(tirosTotales);
        sb.append(" | Paradas: ").append(paradasTotales).append("\n");

        return sb.toString();
    }
}