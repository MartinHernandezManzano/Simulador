public class Medio extends Futbolista implements PuedeTirar {

    public Medio(String nombre, int dorsal, String equipo) {
        super(nombre, dorsal, equipo, 0, 0, 0);
    }

    @Override
    public void tirar(){
        setTiros(getTiros() + 1);
    }

    @Override
    public String toString() {
        return "🎯 " + getDorsal() + ". " + getNombre() + " (" + getEquipo() + ")" +
                " | Goles: " + getGoles() + " | Pases: " + getPases() +
                " | Tiros: " + getTiros();
    }

}