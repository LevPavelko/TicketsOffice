package com.example.demo.service.place_service;

import com.example.demo.convert.ConvertToDTO;
import com.example.demo.convert.ConvertToEntity;
import com.example.demo.dao.place.PlaceRepository;
import com.example.demo.dto.PlaceDTO;
import com.example.demo.model.Place;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private ConvertToEntity convertToEntity;
    @Autowired
    private ConvertToDTO convertToDTO;

    @Override
    public void save(PlaceDTO placeDTO) {
        Place place = convertToEntity.convertPlaceDTOToEntity(placeDTO);
        placeRepository.save(place);
    }

    @Override
    public void update(PlaceDTO placeDTO) {
        Place place = convertToEntity.convertPlaceDTOToEntity(placeDTO);
        placeRepository.save(place);
    }

    @Override
    public void delete(PlaceDTO placeDTO) {
        Place place = convertToEntity.convertPlaceDTOToEntity(placeDTO);
        placeRepository.delete(place);
    }

    @Override
    public List<PlaceDTO> findAll() {
        List<Place> places = placeRepository.findAll();
        return places.stream()
                .map(convertToDTO::convertPlaceToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlaceDTO> findById(int id) {
        Optional<Place> place = placeRepository.findById(id);
        return place.map(convertToDTO::convertPlaceToDTO);
    }

    @Override
    public PlaceDTO findByNameAndAddress(String name,String address) {
        Place place = placeRepository.findByNameAndAddress(name, address);
        return convertToDTO.convertPlaceToDTO(place);


    }


}
