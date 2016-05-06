package SeaTransport.Ships;

public abstract class Ship extends Vessel {
    private int crew;
    private Shallop shallop;

    public int getCrew() {
        return crew;
    }

    public Shallop getShallop() {
        return shallop;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public void setShallop(Shallop shallop) {
        this.shallop = shallop;
    }
}
