package com.onlinebus;

import com.onlinebus.Controller.BusController;
import com.onlinebus.Model.Bus;
import com.onlinebus.Service.BusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BusControllerTest {

    @InjectMocks
    private BusController busController;

    @Mock
    private BusService busService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListBuses() {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("1234", "Bus A", "City A", "City B", "10:00", "11:00", 100.0));
        buses.add(new Bus("5678", "Bus B", "City C", "City D", "12:00", "13:00", 150.0));

        when(busService.getAllBuses()).thenReturn(buses);

        String result = busController.listBuses(model);

        verify(model, times(1)).addAttribute("buses", buses);
        assertEquals("buslist", result);
    }

    @Test
    void testShowAddForm() {
        String result = busController.showAddForm(model);

        verify(model, times(1)).addAttribute(eq("bus"), any(Bus.class)); // Use eq() for the string argument
        assertEquals("busform", result);
    }

    @Test
    void testSaveBus() {
        Bus bus = new Bus("1234", "Bus A", "City A", "City B", "10:00", "11:00", 100.0);

        String result = busController.saveBus(bus);

        verify(busService, times(1)).saveOrUpdateBus(bus);
        assertEquals("redirect:/buses/list", result);
    }

    @Test
    void testDeleteBus() {
        String result = busController.deleteBus("1234");

        verify(busService, times(1)).deleteBusByBusNumber("1234");
        assertEquals("redirect:/buses/list", result);
    }

    @Test
    void testSearchBuses() {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus("1234", "Bus A", "City A", "City B", "10:00", "11:00", 100.0));
        buses.add(new Bus("5678", "Bus B", "City A", "City C", null, "13:00", 150.0)); // One bus with null arrival time

        when(busService.findByArrivalAndDestination("City A", "City B")).thenReturn(buses);

        String result = busController.searchBuses("City A", "City B", model);

        verify(model, times(1)).addAttribute("buses", buses);
        assertEquals("home", result);
    }
}
