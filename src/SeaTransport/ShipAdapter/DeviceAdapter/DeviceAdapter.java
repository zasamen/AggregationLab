package SeaTransport.ShipAdapter.DeviceAdapter;

import SeaTransport.ShipAdapter.VesselOrDeviceAdapter;
import SeaTransport.Ships.VesselComponent.Device;

public interface DeviceAdapter extends VesselOrDeviceAdapter{
    Device getDevice();
}
