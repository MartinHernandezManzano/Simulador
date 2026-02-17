public class Portero extends Futbolista implements PuedeParar {

    public Portero(String nombre, int dorsal, String equipo) {
        super(nombre, dorsal, equipo, 0, 0, 0);
    }

    @Override
    public void parar() {
        setParadas(getParadas() + 1);
    }

    @Override
    public String toString() {
        return "🧤 " + getDorsal() + ". " + getNombre() + " (" + getEquipo() + ")" +
                " | Paradas: " + getParadas() + " | Pases: " + getPases();
    }
}