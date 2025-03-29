package com.kenzie.executorservices.ringupdatescheck.checker;

import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesRequest;
import com.kenzie.executorservices.ringupdatescheck.model.customer.GetCustomerDevicesResponse;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.GetDeviceSystemInfoResponse;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.RingDeviceFirmwareVersion;
import com.kenzie.executorservices.ringupdatescheck.customer.CustomerService;
import com.kenzie.executorservices.ringupdatescheck.devicecommunication.RingDeviceCommunicatorService;
import com.kenzie.executorservices.ringupdatescheck.model.devicecommunication.UpdateDeviceFirmwareRequest;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Utility object for checking version status of devices, and updating
 * them if necessary.
 *
 * For instructional purposes, two implementations of the same logic
 * will be created: checkDevicesIteratively, and checkDevicesConcurrently.
 */
public class DeviceChecker {
    private final CustomerService customerService;
    private final RingDeviceCommunicatorService ringDeviceCommunicatorService;

    /**
     * Constructs a DeviceChecker with the provided dependencies.
     *
     * PARTICIPANTS: Do not change the signature of this constructor
     * @param customerService The CustomerService client to use for Customer operations
     * @param ringDeviceCommunicatorService The RingDeviceCommunicatorService client to use for
     *                                      device communication operations
     */
    public DeviceChecker(CustomerService customerService, RingDeviceCommunicatorService ringDeviceCommunicatorService) {
        this.customerService = customerService;
        this.ringDeviceCommunicatorService = ringDeviceCommunicatorService;
    }

    /**
     * Iteratively checks all devices for the given customer.
     * @param customerId The customer to check devices for
     * @param version The firmware version that we want all devices updated to
     * @return The number of devices that were checked
     */
    public int checkDevicesIteratively(final String customerId, RingDeviceFirmwareVersion version) {
        // PARTICIPANTS: implement in Phase 2
// Use the CustomerService class to retrieve all devices the given customer uses.
        GetCustomerDevicesResponse devices = fromRequestToResponse(customerId);
        int counter = 0;

//Iterate through your list of devices, creating a DeviceCheckTask for each one and explicitly running it inside your loop.
        for (String deviceId : devices.getDeviceIds()) {
            DeviceCheckTask task = new DeviceCheckTask(new DeviceChecker(customerService, ringDeviceCommunicatorService), deviceId, version);

            task.run();
            counter++;
        }
//Return the total number of devices found and checked.
        return counter;
    }

    /**
     * Concurrently checks all devices for the given customer.
     * @param customerId The customer to check devices for
     * @param version The firmware version that we want all devices updated to
     * @return The number of devices that were checked
     */
    public int checkDevicesConcurrently(final String customerId, RingDeviceFirmwareVersion version) {
        GetCustomerDevicesResponse response = fromRequestToResponse(customerId);
        ExecutorService executor = Executors.newCachedThreadPool();
        int counter = 0;

        for (String deviceId : response.getDeviceIds()) {
            DeviceCheckTask task = new DeviceCheckTask(new DeviceChecker(customerService, ringDeviceCommunicatorService), deviceId, version);

            executor.submit(task);
            counter++;
        }
        executor.shutdown();

        return counter;
    }

    /**
     * Updates the device to the specified version.
     * @param deviceId The device identifier of the device to update
     * @param version The version the device should be updated to
     */
    public void updateDevice(final String deviceId, final RingDeviceFirmwareVersion version) {
        UpdateDeviceFirmwareRequest request = new UpdateDeviceFirmwareRequest();
        request.setDeviceId(deviceId);
        request.setVersion(version);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> ringDeviceCommunicatorService.updateDeviceFirmware(request));
        System.out.println(String.format("[DeviceChecker] Updating device %s to version %s", deviceId, version));
        executor.shutdown();
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public RingDeviceCommunicatorService getRingDeviceCommunicatorService() {
        return ringDeviceCommunicatorService;
    }


    //HELPER METHODS
    private GetCustomerDevicesResponse fromRequestToResponse(String customerId) {
        GetCustomerDevicesRequest request = new GetCustomerDevicesRequest();
        request.setCustomerId(customerId);
        return customerService.getCustomerDevices(request);
    }
}
