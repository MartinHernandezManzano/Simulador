public class Defensa extends Futbolista implements PuedeTirar {

    public Defensa(String nombre, int dorsal, String equipo) {
        super(nombre, dorsal, equipo, 0, 0, 0);
    }

    @Override
    public void tirar() {
        setTiros(getTiros() + 1);

        if (Futbolista.getRandom().nextInt(100) < 30) {
            setGoles(getGoles() + 1);
            System.out.println("⚽ ¡GOL de " + getNombre() + " (" + getEquipo() + ")");
        } else {
            System.out.println("💥 Tiro de " + getNombre() + " - Ataja el portero");
        }
    }

    @Override
    public String toString() {
        return "🛡️ " + getDorsal() + ". " + getNombre() + " (" + getEquipo() + ")" +
                " | Goles: " + getGoles() + " | Pases: " + getPases();
    }
}