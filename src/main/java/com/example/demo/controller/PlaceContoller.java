package com.example.demo.controller;


import com.example.demo.dao.place.PlaceRepository;
import com.example.demo.dto.PlaceDTO;
import com.example.demo.model.Place;
import com.example.demo.service.place_service.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlaceContoller {
    @Autowired
    private PlaceServiceImpl placeService;

    @RequestMapping("/createPlace")
    public String createPlace(Model model) {
        model.addAttribute("place", new PlaceDTO());
        return "/createPlace";
    }

    @PostMapping("/createPlaceForm")
    public String createPlace( Model model, PlaceDTO placeDTO) {
        if(placeDTO.getAddress() == null || placeDTO.getName() == null) {
            model.addAttribute("place", placeDTO);
            model.addAttribute("message", "Every fields have to be filled.");
            return "createPlace";
        }
        placeService.save(placeDTO);
        return "redirect:/places";
    }

    @RequestMapping("/places")
    public String places(Model model) {
        model.addAttribute("places", placeService.findAll());
        return "places";
    }



}
