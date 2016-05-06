package SeaTransport.view.Control;

import SeaTransport.ShipAdapter.VesselOrDeviceAdapter;

public interface DeviceManagerController extends VesselController {

    VesselOrDeviceAdapter createAdapter();
}
