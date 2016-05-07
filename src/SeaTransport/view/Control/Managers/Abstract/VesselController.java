package SeaTransport.view.Control.Managers.Abstract;

public interface VesselController {

    boolean checkFields();

    Object[] getFields();

    void setDownStreamObject(Object downStreamObject);
}
