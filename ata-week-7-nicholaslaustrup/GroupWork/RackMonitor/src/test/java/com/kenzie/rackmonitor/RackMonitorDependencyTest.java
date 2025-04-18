package com.kenzie.rackmonitor;

import com.kenzie.rackmonitor.Rack;
import com.kenzie.rackmonitor.RackMonitor;
import com.kenzie.rackmonitor.clients.warranty.Warranty;
import com.kenzie.rackmonitor.clients.warranty.WarrantyClient;
import com.kenzie.rackmonitor.clients.wingnut.WingnutClient;
import com.kenzie.rackmonitor.Server;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.rmi.ServerException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class RackMonitorDependencyTest {
    RackMonitor rackMonitor;
    @Mock
    WingnutClient wingnutClient;
    @Mock
    WarrantyClient warrantyClient;
    @Mock
    Rack mockRack;
    Server testServer = new Server("TEST0001");
    Map<Server, Double> unhealthyServerResult = new HashMap<>();
    Map<Server, Double> healthyServerResult = new HashMap<>();
    Map<Server, Double> shakyServerResult = new HashMap<>();

    @BeforeEach
    void setUp() throws Exception {
        initMocks(this);
        unhealthyServerResult.put(testServer, 0.75D);
        healthyServerResult.put(testServer, 0.99D);
        shakyServerResult.put(testServer, 0.85D);
        when(mockRack.getUnitForServer(testServer)).thenReturn(1);
        when(warrantyClient.getWarrantyForServer(testServer)).thenReturn(Warranty.nullWarranty());
        rackMonitor = new RackMonitor(new HashSet<>(Arrays.asList(mockRack)),
            wingnutClient, warrantyClient, 0.9D, 0.8D);
    }

    @AfterEach
    void verifyNoDependencyCalls() {
        verifyNoMoreInteractions(wingnutClient, warrantyClient);
    }

    @Test
    public void monitorRacks_withOneUnhealthyServer_replacesServer() throws Exception {
        // GIVEN
        // The rack is set up with a single unhealthy server
        when(mockRack.getHealth()).thenReturn(unhealthyServerResult);

        // WHEN
        rackMonitor.monitorRacks();

        // THEN
        // There were no exceptions
        verify(warrantyClient).getWarrantyForServer(any());
        verify(wingnutClient).requestReplacement(any(), eq(1), any());

        assertDoesNotThrow(() -> rackMonitor.arrangeReplacement(mockRack, testServer));
    }

    @Test
    public void monitorRacks_withOneShakyServer_inspectsServer() throws Exception {
        // GIVEN
        // The rack is set up with a single shaky server
        when(mockRack.getHealth()).thenReturn(shakyServerResult);

        // WHEN
        rackMonitor.monitorRacks();

        // THEN
        // There were no exceptions
        assertDoesNotThrow(() -> rackMonitor.arrangeInspection(mockRack, testServer));
        verify(warrantyClient, times(0)).getWarrantyForServer(any());
        verify(wingnutClient).requestInspection(any(), eq(1));
    }

    @Test
    public void monitorRacks_withOneHealthyServer_makesNoCalls() throws Exception {
        // GIVEN
        // The rack is set up with a single shaky server
        when(mockRack.getHealth()).thenReturn(healthyServerResult);

        // WHEN
        rackMonitor.monitorRacks();

        // THEN
        // There were no exceptions
        verify(warrantyClient, times(0)).getWarrantyForServer(any());
        verify(wingnutClient, times(0)).requestReplacement(any(), eq(1), any());

        assertDoesNotThrow(() -> rackMonitor.monitorRacks());
    }
@Test
    public void monitorRacks_withMixedServers_makesMultipleCalls() throws Exception {
        // GIVEN
        // The rack is set up with many servers with different statuses
        Server unhealthyServer1 = new Server("unhealthy1");
        Server unhealthyServer2 = new Server("unhealthy2");
        Server shakyServer1 = new Server("shaky1");
        Server healthyServer1 = new Server("healthy1");
        Map<Server, Double> serverHealthMap = new HashMap<>();
        serverHealthMap.put(unhealthyServer1, 0.1D);
        serverHealthMap.put(unhealthyServer2, 0.6D);
        serverHealthMap.put(shakyServer1, 0.85D);
        serverHealthMap.put(healthyServer1, 0.95D);
        when(mockRack.getHealth()).thenReturn(serverHealthMap);
        // The servers all have unit slots
        when(mockRack.getUnitForServer(unhealthyServer1)).thenReturn(3);
        when(mockRack.getUnitForServer(unhealthyServer2)).thenReturn(5);
        when(mockRack.getUnitForServer(shakyServer1)).thenReturn(1);
        when(mockRack.getUnitForServer(healthyServer1)).thenReturn(8);
        // The servers all have warranties
        when(warrantyClient.getWarrantyForServer(any())).thenReturn(Warranty.nullWarranty());

        // WHEN
        rackMonitor.monitorRacks();

        // THEN
        // Can we test the *number* of times a method was called,
        // instead of the exact calls?
        verify(warrantyClient, times(2)).getWarrantyForServer(any());
        verify(wingnutClient, times(1)).requestInspection(any(), anyInt());
        verify(wingnutClient, times(2)).requestReplacement(any(), anyInt(), any());
    }
}
