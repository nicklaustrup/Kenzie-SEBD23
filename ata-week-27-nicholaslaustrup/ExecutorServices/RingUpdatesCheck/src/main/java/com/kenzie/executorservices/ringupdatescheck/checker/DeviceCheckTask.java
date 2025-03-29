package com.kenzie.executorservices.ringupdatescheck.checker;

import com.kenzie.executorservices.ringupdatescheck.devicecommunication.RingDeviceCommunicatorService;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.GetDeviceSystemInfoRequest;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.GetDeviceSystemInfoResponse;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceFirmwareVersion;
import com.kenzie.executorservices.ringupdatescheck.util.RingDeviceFirmwareVersionComparator;

/**
 * A task to check a single device's version against a desired latest
 * version, requesting a firmware update if appropriate.
 *
 * PARTICIPANTS: Implement this class in Phase 1
 */
public class DeviceCheckTask implements Runnable{
    private RingDeviceCommunicatorService ringDeviceCommunicatorService;
    private DeviceChecker deviceChecker;
    private String deviceId;
    private RingDeviceFirmwareVersion version;

    /**
     * Constructs a DeviceCheckTask with the given dependencies and parameters.
     *
     * PARTICIPANTS: If you add constructor parameters, add them AFTER the DeviceChecker
     * argument. If you add parameters before the DeviceChecker, your tests will fail.
     *
     * @param deviceChecker The DeviceChecker to use while executing this task
     */
    public DeviceCheckTask(DeviceChecker deviceChecker, String deviceId, RingDeviceFirmwareVersion version) {
        this.ringDeviceCommunicatorService = deviceChecker.getRingDeviceCommunicatorService();
        this.deviceChecker = deviceChecker;
        this.deviceId = deviceId;
        this.version = version;
    }

    public void run(){
        //Calls the RingDeviceCommunicatorService to determine the device's firmware version number.
        GetDeviceSystemInfoRequest getDeviceSystemInfoRequest = new GetDeviceSystemInfoRequest();
        getDeviceSystemInfoRequest.setDeviceId(deviceId);
        GetDeviceSystemInfoResponse response = ringDeviceCommunicatorService.getDeviceSystemInfo(getDeviceSystemInfoRequest);

        //Decides if that version is out of date by comparing the device's version to a latestVersion.
        RingDeviceFirmwareVersionComparator comparator = new RingDeviceFirmwareVersionComparator();

        //Calls deviceChecker.updateDevice() if the device needs updating.
        int compareResults = comparator.compare(response.getSystemInfo().getDeviceFirmwareVersion(), version);
        if (compareResults < 0) {
            deviceChecker.updateDevice(deviceId, version);
        }



    }
}
