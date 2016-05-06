package SeaTransport.Ships;

import java.io.Serializable;

public abstract class Vessel implements Serializable {

    private int length;
    private int width;
    private int capacity;
    private int displacement;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }
}
