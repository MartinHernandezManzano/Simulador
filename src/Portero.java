public class Portero extends Futbolista implements PuedeParar {

    public Portero(String nombre, int dorsal, String equipo) {
        super(nombre, dorsal, equipo, 0, 0, 0);
    }

    @Override
    public void parar() {
        if (Futbolista.getRandom().nextInt(100) < 70) {
            setParadas(getParadas() + 1);
            System.out.println("🧤 " + getNombre() + " detiene el balón");
        } else {
            System.out.println("🥅 Gol - " + getNombre() + " no pudo parar");
        }
    }

    @Override
    public String toString() {
        return "🧤 " + getDorsal() + ". " + getNombre() + " (" + getEquipo() + ")" +
                " | Paradas: " + getParadas() + " | Pases: " + getPases();
    }
}