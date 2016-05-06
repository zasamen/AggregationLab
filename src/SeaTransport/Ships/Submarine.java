package SeaTransport.Ships;

public class Submarine extends Ship {
    private String torpedoType;
    private int count;

    public String getTorpedoType() {
        return torpedoType;
    }

    public int getCount() {
        return count;
    }

    public void setTorpedoType(String torpedoType) {
        this.torpedoType = torpedoType;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
