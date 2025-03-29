package com.kenzie.rackmonitor;

import com.kenzie.rackmonitor.*;
import com.kenzie.rackmonitor.clients.warranty.Warranty;
import com.kenzie.rackmonitor.clients.warranty.WarrantyClient;
import com.kenzie.rackmonitor.clients.warranty.WarrantyNotFoundException;
import com.kenzie.rackmonitor.clients.wingnut.WingnutClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.rmi.ServerException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class RackMonitorIncidentTest {
    RackMonitor rackMonitor;
    @Mock(name="wingnutClient")
    WingnutClient wingnutClient;
    @Mock(name="warrantyClient")
    WarrantyClient warrantyClient;
    @Mock(name="rack1")
    Rack rack1;
    Server unhealthyServer = new Server("TEST0001");
    Server shakyServer = new Server("TEST0067");
    Map<Server, Integer> rack1ServerUnits;

    @BeforeEach
    void setUp() {
        openMocks(this);
        rack1ServerUnits = new HashMap<>();
        rack1ServerUnits.put(unhealthyServer, 1);
        rackMonitor = new RackMonitor(new HashSet<>(Arrays.asList(rack1)),
            wingnutClient, warrantyClient, 0.9D, 0.8D);
    }

    @Test
    public void getIncidents_withOneUnhealthyServer_createsOneReplaceIncident() throws Exception {
        // GIVEN
        // The rack is set up with a single unhealthy server
        // We've reported the unhealthy server to Wingnut
        //GIVEN
        Map<Server, Double> healthMap = new HashMap<>();
        healthMap.put(unhealthyServer, 0.6D);
        Warranty warranty = new Warranty("DUMMY");
        //Setup Mock methods / returns
        when(rack1.getUnitForServer(unhealthyServer)).thenReturn(1);
        when(rack1.getHealth()).thenReturn(healthMap);
        when(warrantyClient.getWarrantyForServer(unhealthyServer)).thenReturn(warranty);
        //call main testing method
        rackMonitor.monitorRacks();
        //Save actual result
        Set<HealthIncident> actualIncidents = rackMonitor.getIncidents();
        //Create hardcode expected result
        HealthIncident expected =
                new HealthIncident(unhealthyServer, rack1, 1, RequestAction.REPLACE);
        //Assert actual contains the expected result
        assertTrue(actualIncidents.contains(expected),
            "Monitoring an unhealthy server should record a REPLACE incident!");
        verify(wingnutClient).requestReplacement(any(), anyInt(), any());
        verify(warrantyClient).getWarrantyForServer(any());
    }

    @Test
    public void getIncidents_withOneShakyServer_createsOneInspectIncident() throws Exception {
        // GIVEN
        // The rack is set up with a single shaky server
        rack1ServerUnits = new HashMap<>();
        rack1ServerUnits.put(shakyServer, 1);
        rack1 = new Rack("RACK01", rack1ServerUnits);
        rackMonitor = new RackMonitor(new HashSet<>(Arrays.asList(rack1)),
            wingnutClient, warrantyClient, 0.9D, 0.8D);
        // We've reported the shaky server to Wingnut
        rackMonitor.monitorRacks();

        // WHEN
        Set<HealthIncident> actualIncidents = rackMonitor.getIncidents();

        // THEN
        HealthIncident expected =
            new HealthIncident(shakyServer, rack1, 1, RequestAction.INSPECT);
        assertTrue(actualIncidents.contains(expected),
            "Monitoring a shaky server should record an INSPECT incident!");
        verify(wingnutClient).requestInspection(any(), anyInt());
        verify(warrantyClient, times(0)).getWarrantyForServer(any());
    }

    @Test
    public void getIncidents_withOneHealthyServer_createsNoIncidents() throws Exception {
        // GIVEN
        // monitorRacks() will find only healthy servers
        rack1ServerUnits = new HashMap<>();
        rack1 = new Rack("RACK01", rack1ServerUnits);
        rackMonitor = new RackMonitor(new HashSet<>(Arrays.asList(rack1)),
                wingnutClient, warrantyClient, 0.9D, 0.8D);

        // We've reported the shaky server to Wingnut
        rackMonitor.monitorRacks();
        // WHEN
        Set<HealthIncident> actualIncidents = rackMonitor.getIncidents();

        // THEN
        assertEquals(0, actualIncidents.size(),
            "Monitoring a healthy server should record no incidents!");
        verify(wingnutClient, times(0)).requestReplacement(any(), anyInt(), any());
        verify(warrantyClient, times(0)).getWarrantyForServer(any());
    }

    @Test
    public void monitorRacks_withOneUnhealthyServer_replacesServer() throws Exception {
        // GIVEN
        // The rack is set up with a single unhealthy server
        Map<Server, Double> healthMap = new HashMap<>();
        healthMap.put(unhealthyServer, 0.6D);
        Warranty warranty = new Warranty("DUMMY");
        //Setup Mock methods / returns
        when(rack1.getUnitForServer(unhealthyServer)).thenReturn(1);
        when(rack1.getHealth()).thenReturn(healthMap);
        when(warrantyClient.getWarrantyForServer(unhealthyServer)).thenReturn(warranty);
        // WHEN
        rackMonitor.monitorRacks();

        // THEN
        // There were no exceptions
        assertDoesNotThrow(() -> rackMonitor.arrangeReplacement(rack1, unhealthyServer));
        // call the warrantyClient for the server's Warranty
        verify(warrantyClient).getWarrantyForServer(unhealthyServer);
        verify(wingnutClient).requestReplacement(rack1, rack1.getUnitForServer(unhealthyServer),warranty);
    }

    @Test
    public void monitorRacks_withUnwarrantiedServer_throwsServerException() throws Exception {
        // GIVEN
        Server noWarrantyServer = new Server("TEST0052");
        Map<Server, Double> healthMap = new HashMap<>();
        healthMap.put(noWarrantyServer, 0.6D);

        //mock methods
        when(rack1.getUnitForServer(noWarrantyServer)).thenReturn(1);
        when(rack1.getHealth()).thenReturn(healthMap);
        when(warrantyClient.getWarrantyForServer(noWarrantyServer)).thenThrow(WarrantyNotFoundException.class);

        // WHEN and THEN
        assertThrows(RackMonitorException.class,
            () -> rackMonitor.monitorRacks(),
            "Monitoring a server with no warranty should throw exception!");

        verify(warrantyClient).getWarrantyForServer(any());
        verify(wingnutClient, times(0)).requestReplacement(any(), anyInt(), any());
    }
}
