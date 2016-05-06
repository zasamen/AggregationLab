package SeaTransport.view.Control.Managers.Abstract;

import SeaTransport.ShipAdapter.VesselOrDeviceAdapter;

public interface DeviceManagerController extends VesselController {

    VesselOrDeviceAdapter createAdapter();
}
