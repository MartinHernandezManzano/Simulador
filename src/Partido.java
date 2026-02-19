import java.io.IOException;
import java.util.List;

// Clase que gestiona la simulación completa de un partido
public class Partido {
    private Equipo local;
    private Equipo visitante;
    private int contadorPasesLocal;      // pases consecutivos del local
    private int contadorPasesVisitante;   // pases consecutivos del visitante
    private int minuto;
    private boolean posesionLocal;        // true = local, false = visitante
    private EscritorPartido escritor;      // el que escribe el archivo

    public Partido(Equipo local, Equipo visitante, int numeroPartido) {
        this.local = local;
        this.visitante = visitante;
        this.contadorPasesLocal = 0;
        this.contadorPasesVisitante = 0;
        this.minuto = 0;
        this.posesionLocal = Futbolista.getRandom().nextBoolean();
        this.escritor = new EscritorPartido(local, visitante, numeroPartido);
    }

    // Método principal: ejecuta la simulación
    public void simular() {
        try {
            escritor.abrirArchivo();
            escritor.escribirCabecera(posesionLocal);

            // Bucle principal: 90 minutos, 5 acciones por minuto
            for (minuto = 1; minuto <= 90; minuto++) {
                for (int accion = 1; accion <= 5; accion++) {
                    realizarAccion();
                }
            }

            // Al final del partido, escribir estadísticas
            escritor.escribirEstadisticasFinales(local, visitante);
            escritor.cerrarArchivo();

            System.out.println("✅ Partido generado: partido" + escritor.getNumeroPartido() + ".txt");

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
        Futbolista jugador = elegirJugadorCampo(atacante);
        boolean paseExitoso = jugador.pasar();

        if (paseExitoso) {
            if (esLocal) {
                contadorPasesLocal++;
                if (contadorPasesLocal >= 10) {
                    realizarTiro(atacante, defensor, esLocal);
                    contadorPasesLocal = 0;
                }
            } else {
                contadorPasesVisitante++;
                if (contadorPasesVisitante >= 10) {
                    realizarTiro(atacante, defensor, esLocal);
                    contadorPasesVisitante = 0;
                }
            }
        } else {
            escritor.escribirPaseFallado(minuto, jugador, atacante);
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
        Futbolista tirador = elegirJugadorCampo(atacante);
        tirador.setTiros(tirador.getTiros() + 1);

        Portero portero = (Portero) buscarPortero(defensor);
        boolean gol = Futbolista.getRandom().nextInt(100) >= 70;  // 30% gol

        if (gol) {
            tirador.setGoles(tirador.getGoles() + 1);
            escritor.escribirGol(minuto, tirador, atacante);
            posesionLocal = !esLocal;
            if (esLocal) {
                contadorPasesLocal = 0;
                contadorPasesVisitante = 0;
            } else {
                contadorPasesVisitante = 0;
                contadorPasesLocal = 0;
            }
        } else {
            assert portero != null;
            portero.parar();
            portero.pasar();
            escritor.escribirParada(minuto, portero, defensor);
            posesionLocal = !esLocal;
            if (esLocal) {
                contadorPasesLocal = 0;
            } else {
                contadorPasesVisitante = 0;
            }
        }
    }

    private Futbolista elegirJugadorCampo(Equipo equipo) {
        List<Futbolista> jugadores = equipo.getJugadores();
        Futbolista elegido;
        do {
            int indice = Futbolista.getRandom().nextInt(jugadores.size());
            elegido = jugadores.get(indice);
        } while (elegido instanceof Portero);
        return elegido;
    }

    private Futbolista buscarPortero(Equipo equipo) {
        for (Futbolista j : equipo.getJugadores()) {
            if (j instanceof Portero) {
                return j;
            }
        }
        return null;
    }
}