public class Defensa extends Futbolista implements PuedeTirar {

    public Defensa(String nombre, int dorsal, String equipo) {
        super(nombre, dorsal, equipo, 0, 0, 0);
    }

    @Override
    public boolean tirar(Portero porteroRival) {
        setTiros(getTiros() + 1);

        if (Futbolista.getRandom().nextInt(100) < 30) {
            setGoles(getGoles() + 1);
            return true;  // gol
        } else {
            porteroRival.parar();    // portero suma parada
            porteroRival.pasar();     // portero inicia contraataque
            return false;  // parada
        }
    }

    @Override
    public String toString() {
        return "🛡️ " + getDorsal() + ". " + getNombre() + " (" + getEquipo() + ")" +
                " | Goles: " + getGoles() + " | Pases: " + getPases() +
                " | Tiros: " + getTiros();
    }
}