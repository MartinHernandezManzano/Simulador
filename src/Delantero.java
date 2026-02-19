public class Delantero extends Futbolista implements PuedeTirar {

    public Delantero(String nombre, int dorsal, String equipo) {
        super(nombre, dorsal, equipo, 0, 0, 0);
    }

    @Override
    public boolean tirar(Portero porteroRival) {
        setTiros(getTiros() + 1);

        if (Futbolista.getRandom().nextInt(100) < 30) {
            setGoles(getGoles() + 1);
            return true;
        } else {
            porteroRival.parar();
            porteroRival.pasar();
            return false;
        }
    }

    @Override
    public String toString() {
        return "⚽ " + getDorsal() + ". " + getNombre() + " (" + getEquipo() + ")" +
                " | Goles: " + getGoles() + " | Pases: " + getPases() +
                " | Tiros: " + getTiros();
    }
}