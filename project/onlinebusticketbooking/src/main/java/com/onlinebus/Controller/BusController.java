package com.onlinebus.Controller;

import com.onlinebus.Model.Bus;
import com.onlinebus.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/list")
    public String listBuses(Model model) {
        List<Bus> buses = busService.getAllBuses();
        model.addAttribute("buses", buses);
        return "buslist";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("bus", new Bus());
        return "busform";
    }

    @PostMapping("/save")
    public String saveBus(@ModelAttribute("bus") Bus bus) {
        busService.saveOrUpdateBus(bus);
        return "redirect:/buses/list";
    }

    @GetMapping("/delete/{busnumber}")
    public String deleteBus(@PathVariable("busnumber") String busNumber) {
        busService.deleteBusByBusNumber(busNumber);
        return "redirect:/buses/list"; 
    }
    
    @GetMapping("/search")
    public String searchBuses(@RequestParam("from") String from, @RequestParam("to") String to, Model model) {
        List<Bus> buses = busService.findByArrivalAndDestination(from, to);        
        buses.sort(Comparator.comparing(Bus::getArrivaltime, Comparator.nullsLast(Comparator.naturalOrder())));
        model.addAttribute("buses", buses);
        return "home"; 
    }
}
