import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Clase encargada de escribir la crónica y estadísticas del partido
public class EscritorPartido {
    private BufferedWriter writer;
    private int numeroPartido;
    private Equipo local;
    private Equipo visitante;

    public EscritorPartido(Equipo local, Equipo visitante, int numeroPartido) {
        this.local = local;
        this.visitante = visitante;
        this.numeroPartido = numeroPartido;
    }

    public void abrirArchivo() throws IOException {
        writer = new BufferedWriter(new FileWriter("partido" + numeroPartido + ".txt"));
    }

    public void cerrarArchivo() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    public void escribirCabecera(boolean posesionLocal) throws IOException {
        writer.write("=== " + local.getNombre() + " vs " + visitante.getNombre() + " ===\n");
        writer.write("Comienza el partido. Posesión inicial: " +
                (posesionLocal ? local.getNombre() : visitante.getNombre()) + "\n\n");
    }

    public void escribirPaseFallado(int minuto, Futbolista jugador, Equipo equipo) throws IOException {
        writer.write("Minuto " + minuto + ": Pase fallado de " + jugador.getNombre() +
                " (" + equipo.getNombre() + "). Cambia la posesión.\n");
    }

    public void escribirGol(int minuto, Futbolista tirador, Equipo equipo) throws IOException {
        writer.write("Minuto " + minuto + ": " + tirador.getNombre() +
                " (" + equipo.getNombre() + ") DISPARA... ");
        writer.write("¡GOOOOL! " + tirador.getNombre() + " (" + equipo.getNombre() + ") marca.\n");
    }

    public void escribirParada(int minuto, Portero portero, Equipo equipo) throws IOException {
        writer.write("Minuto " + minuto + ": DISPARO... ");
        writer.write("¡PARADÓN de " + portero.getNombre() + " (" + equipo.getNombre() + ")!\n");
    }

    public void escribirEstadisticasFinales(Equipo local, Equipo visitante) throws IOException {
        local.actualizarEstadisticas();
        visitante.actualizarEstadisticas();

        writer.write("\n====================================\n");
        writer.write("=== ESTADÍSTICAS FINALES ===\n");
        writer.write("====================================\n\n");

        writer.write(local.toString());
        writer.write("\n");
        writer.write(visitante.toString());

        writer.write("\n====================================\n");
        writer.write("RESULTADO: " + local.getGolesTotales() + " - " + visitante.getGolesTotales());
        writer.write("\n====================================\n");
    }

    public int getNumeroPartido() {
        return numeroPartido;
    }
}