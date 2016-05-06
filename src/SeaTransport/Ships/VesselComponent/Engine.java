package SeaTransport.Ships.VesselComponent;

public class Engine extends Device{
    private int volume;
    private int power;
    private int consumption;

    public int getVolume() {
        return volume;
    }

    public int getPower() {
        return power;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
}
