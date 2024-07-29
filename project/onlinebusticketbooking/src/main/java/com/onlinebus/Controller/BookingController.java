package com.onlinebus.Controller;

import com.onlinebus.Model.Booking;
import com.onlinebus.Model.Bus;
import com.onlinebus.Service.BookingService;
import com.onlinebus.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BusService busService;

    @GetMapping("/book")
    public String showBookingForm(@RequestParam("busnumber") String busnumber, Model model) {
        Optional<Bus> optionalBus = busService.getBusByBusNumber(busnumber);
        if (!optionalBus.isPresent()) {
            return "redirect:/error";
        }
        
        Bus bus = optionalBus.get();
        
        Booking booking = new Booking();
        booking.setBusnumber(bus.getBusnumber());
        booking.setBusname(bus.getBusname());
        booking.setArrival(bus.getArrival());
        booking.setDestination(bus.getDestination());
        booking.setArrivaltime(bus.getArrivaltime());
        booking.setDeparturetime(bus.getDeparturetime());
        booking.setFare(bus.getFare());
        
        model.addAttribute("booking", booking);
        return "bookingform"; 
    }

    @PostMapping("/book")
    public String bookBus(@ModelAttribute Booking booking, RedirectAttributes redirectAttributes) {
        bookingService.saveBooking(booking);
        redirectAttributes.addFlashAttribute("successMessage", "Booking Confirmed!");
        return "redirect:/home";
    }

    @GetMapping("/confirm")
    public String confirmBooking(@RequestParam("busnumber") String busNumber,
                                 @RequestParam("passengerName") String passengerName,
                                 @RequestParam("numberOfPassengers") int numberOfPassengers,
                                 @RequestParam("address") String address,
                                 @RequestParam("email") String email,
                                 @RequestParam("phoneNumber") String phoneNumber,
                                 Model model) {
        Booking booking = new Booking(busNumber, passengerName, numberOfPassengers, address, email, phoneNumber);
        bookingService.saveBooking(booking);
        model.addAttribute("message", "Booking Confirmed");
        return "bookingconfirmation"; 
    }

    @GetMapping("/view")
    public String viewBookings(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookinglist";
    }
}
