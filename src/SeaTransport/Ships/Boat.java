package SeaTransport.Ships;

public abstract class Boat extends Vessel {
    private Shallop shallop;

    public Shallop getShallop() {
        return shallop;
    }

    public void setShallop(Shallop shallop) {
        this.shallop = shallop;
    }
}
