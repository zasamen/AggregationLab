package SeaTransport.Ships.VesselComponent;

public class Aircraft extends Device{
    private int weight;
    private String Model;

    public int getWeight() {
        return weight;
    }

    public String getModel() {
        return Model;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setModel(String model) {
        Model = model;
    }
}
