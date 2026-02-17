import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

// Clase que gestiona la simulación completa de un partido
// Sigue la lógica que hemos diseñado: 3 acciones por minuto, 10 pases seguidos = tiro
public class Partido {
    private Equipo local;
    private Equipo visitante;
    private int contadorPasesLocal;      // pases consecutivos del local
    private int contadorPasesVisitante;   // pases consecutivos del visitante
    private int minuto;
    private boolean posesionLocal;        // true = local, false = visitante
    private BufferedWriter writer;
    private int numeroPartido;             // para nombrar el archivo partido1.txt, partido2.txt...

    public Partido(Equipo local, Equipo visitante, int numeroPartido) {
        this.local = local;
        this.visitante = visitante;
        this.numeroPartido = numeroPartido;
        this.contadorPasesLocal = 0;
        this.contadorPasesVisitante = 0;
        this.minuto = 0;
        // posesión inicial aleatoria
        this.posesionLocal = Futbolista.getRandom().nextBoolean();
    }

    // Método principal: ejecuta la simulación y guarda el archivo
    public void simular() {
        try {
            // Crear archivo partido1.txt, partido2.txt...
            writer = new BufferedWriter(new FileWriter("partido" + numeroPartido + ".txt"));

            escribirEnArchivo("=== " + local.getNombre() + " vs " + visitante.getNombre() + " ===\n");
            escribirEnArchivo("Comienza el partido. Posesión inicial: " +
                    (posesionLocal ? local.getNombre() : visitante.getNombre()) + "\n\n");

            // Bucle principal: 90 minutos, 3 acciones por minuto
            for (minuto = 1; minuto <= 90; minuto++) {
                for (int accion = 1; accion <= 5; accion++) {
                    realizarAccion();
                }
            }

            // Al final del partido, escribir estadísticas
            escribirEstadisticasFinales();

            writer.close();
            System.out.println("✅ Partido generado: partido" + numeroPartido + ".txt");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    // Una acción puede ser: pase, tiro (si se llega a 10 pases) o cambio de posesión
    private void realizarAccion() throws IOException {
        if (posesionLocal) {
            procesarPaseEquipo(local, visitante, true);
        } else {
            procesarPaseEquipo(visitante, local, false);
        }
    }

    // Procesa un pase del equipo atacante
    private void procesarPaseEquipo(Equipo atacante, Equipo defensor, boolean esLocal) throws IOException {
        // 1. Elegir un jugador que no sea portero para dar el pase
        Futbolista jugador = elegirJugadorCampo(atacante);

        // 2. Intentar el pase
        boolean paseExitoso = jugador.pasar();

        if (paseExitoso) {
            // Pase completado: incrementar contador del equipo atacante
            if (esLocal) {
                contadorPasesLocal++;
                // Comprobar si llegamos a 10 pases seguidos
                if (contadorPasesLocal >= 10) {
                    realizarTiro(atacante, defensor, esLocal);
                    contadorPasesLocal = 0;  // Se reinicia tras el tiro
                }
            } else {
                contadorPasesVisitante++;
                if (contadorPasesVisitante >= 10) {
                    realizarTiro(atacante, defensor, esLocal);
                    contadorPasesVisitante = 0;
                }
            }
        } else {
            // Pase fallado: cambio de posesión
            escribirEnArchivo("Minuto " + minuto + ": Pase fallado de " + jugador.getNombre() +
                    " (" + atacante.getNombre() + "). Cambia la posesión.\n");

            // Cambiar posesión y reiniciar contadores
            posesionLocal = !esLocal;
            if (esLocal) {
                contadorPasesLocal = 0;
            } else {
                contadorPasesVisitante = 0;
            }
        }
    }

    // Realiza un tiro a puerta
    private void realizarTiro(Equipo atacante, Equipo defensor, boolean esLocal) throws IOException {
        // Elegir tirador (cualquier jugador de campo)
        Futbolista tirador = elegirJugadorCampo(atacante);

        // Buscar portero rival
        Portero portero = (Portero) buscarPortero(defensor);

        escribirEnArchivo("Minuto " + minuto + ": " + tirador.getNombre() +
                " (" + atacante.getNombre() + ") DISPARA... ");

        // El portero intenta parar
        boolean gol = Futbolista.getRandom().nextInt(100) >= 70;  // 30% gol

        if (gol) {
            // Gol
            tirador.setGoles(tirador.getGoles() + 1);
            escribirEnArchivo("¡GOOOOL! " + tirador.getNombre() + " (" + atacante.getNombre() + ") marca.\n");

            // Cambio de posesión (el equipo que recibe el gol saca de centro)
            posesionLocal = !esLocal;

            // Reiniciar contadores
            if (esLocal) {
                contadorPasesLocal = 0;
                contadorPasesVisitante = 0;
            } else {
                contadorPasesVisitante = 0;
                contadorPasesLocal = 0;
            }

        } else {
            // Parada
            portero.parar();  // Esto incrementa sus paradas
            escribirEnArchivo("¡PARADÓN de " + portero.getNombre() + " (" + defensor.getNombre() + ")!\n");

            // El portero inicia el contraataque: la posesión cambia al defensor
            posesionLocal = !esLocal;

            // Reiniciar contadores
            if (esLocal) {
                contadorPasesLocal = 0;
            } else {
                contadorPasesVisitante = 0;
            }
        }
    }

    // Devuelve un jugador de campo aleatorio (excluye porteros)
    private Futbolista elegirJugadorCampo(Equipo equipo) {
        List<Futbolista> jugadores = equipo.getJugadores();
        Futbolista elegido;
        do {
            int indice = Futbolista.getRandom().nextInt(jugadores.size());
            elegido = jugadores.get(indice);
        } while (elegido instanceof Portero);  // repetir si es portero

        return elegido;
    }

    // Busca el portero de un equipo
    private Futbolista buscarPortero(Equipo equipo) {
        for (Futbolista j : equipo.getJugadores()) {
            if (j instanceof Portero) {
                return j;
            }
        }
        return null;  // No debería pasar, todos los equipos tienen portero
    }

    // Escribe en el archivo y también mostramos en consola para seguimiento
    private void escribirEnArchivo(String texto) throws IOException {
        writer.write(texto);
        // Opcional: también lo mostramos por consola si quieres ver el progreso
        // System.out.print(texto);
    }

    // Calcula y escribe las estadísticas finales
    private void escribirEstadisticasFinales() throws IOException {
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
}