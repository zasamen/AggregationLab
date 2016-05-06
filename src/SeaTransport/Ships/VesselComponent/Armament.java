package SeaTransport.Ships.VesselComponent;

public class Armament extends Device {
    private String model;
    private int caliber;

    public String getModel() {
        return model;
    }

    public int getCaliber() {
        return caliber;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCaliber(int caliber) {
        this.caliber = caliber;
    }
}
