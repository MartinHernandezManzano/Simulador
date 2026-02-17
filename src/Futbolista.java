import java.util.Random;

//clase padre de la que heredarán las distintas posiciones
public abstract class Futbolista {
    private String nombre;
    private int dorsal;
    private String equipo;
    private int pases;
    private int goles;
    private int paradas; //para porteros
    private int tiros;

    /*objeto "permanente" para no crear uno nuevo en cada llamada,
    dejamos una sola instancia*/
    private static final Random random = new Random();

    //método genérico que implementarán todas las clases hijas
    public boolean pasar() {
        boolean exito = random.nextInt(100) < 85;
        //este metodo representa un pase con un 85% de prob de éxito
        if (exito) {
            this.pases++;
        }
        return exito;
    }

    //getters, setters, constructor y toString
    public Futbolista(String nombre, int dorsal, String equipo, int pases, int goles, int paradas) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.equipo = equipo;
        this.pases = pases;
        this.goles = goles;
        this.paradas = paradas;
        this.tiros = 0;
    }

    public int getTiros() {
        return tiros;
    }

    public void setTiros(int tiros) {
        this.tiros = tiros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getPases() {
        return pases;
    }

    public void setPases(int pases) {
        this.pases = pases;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getParadas() {
        return paradas;
    }

    public void setParadas(int paradas) {
        this.paradas = paradas;
    }

    public static Random getRandom() {
        return random;
    }



    @Override
    public String toString() {
        return "⚽ " + dorsal + ". " + nombre + " (" + equipo + ") | ⚽ G: " + goles +
                " | 🎯 P: " + pases + " | 🧤 Par: " + paradas;
    }
}
