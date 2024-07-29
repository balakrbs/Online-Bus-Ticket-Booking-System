package com.onlinebus;


import com.onlinebus.Controller.BookingController;
import com.onlinebus.Model.Booking;
import com.onlinebus.Model.Bus;
import com.onlinebus.Service.BookingService;
import com.onlinebus.Service.BusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private BookingService bookingService;

    @Mock
    private BusService busService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowBookingForm() {
        Bus bus = new Bus("1234", "Bus A", "City A", "City B", "10:00", "11:00", 100.0);
        when(busService.getBusByBusNumber("1234")).thenReturn(Optional.of(bus));

        String result = bookingController.showBookingForm("1234", model);

        verify(model, times(1)).addAttribute(eq("booking"), any(Booking.class)); 
        assertEquals("bookingform", result);
    }

    @Test
    void testBookBus() {
        Booking booking = new Booking("1234", "John Doe", 2, "123 Main St", "john@example.com", "1234567890");

        String result = bookingController.bookBus(booking, redirectAttributes);

        verify(bookingService, times(1)).saveBooking(booking);
        verify(redirectAttributes, times(1)).addFlashAttribute(anyString(), anyString());
        assertEquals("redirect:/home", result);
    }

    @Test
    void testConfirmBooking() {
        Booking booking = new Booking("1234", "John Doe", 2, "123 Main St", "john@example.com", "1234567890");

        String result = bookingController.confirmBooking("1234", "John Doe", 2, "123 Main St", "john@example.com", "1234567890", model);

        verify(bookingService, times(1)).saveBooking(any(Booking.class));
        verify(model, times(1)).addAttribute("message", "Booking Confirmed");
        assertEquals("bookingconfirmation", result);
    }

    @Test
    void testViewBookings() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking("1234", "John Doe", 2, "123 Main St", "john@example.com", "1234567890"));
        bookings.add(new Booking("5678", "Jane Smith", 1, "456 Oak St", "jane@example.com", "0987654321"));

        when(bookingService.getAllBookings()).thenReturn(bookings);

        String result = bookingController.viewBookings(model);

        verify(model, times(1)).addAttribute("bookings", bookings);
        assertEquals("bookinglist", result);
    }
}


