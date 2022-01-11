package com.example.demo.controller;

import com.example.demo.model.Position;
import com.example.demo.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("positions/")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping("/{userId}")
    @CrossOrigin(origins = "http://localhost:3000/")
    public Page<Position> getAllPositionsByUserId(@PathVariable(value = "userId") Long userId,
                                                  Pageable pageable) {
        return positionService.getAllPositionsByUserId(userId, pageable);
    }

    @PostMapping("/addPosition/{userId}")
    public Optional<Position> createPosition(@PathVariable(value = "userId") Long userId,
                                             @Valid @RequestBody Position position) {
        return positionService.createPosition(userId, position);
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("/getPositionsBetweenTwoDates/{userId}/{startDateAndTime}/{endDateAndTime}")
    public List<Position> createPosition(@PathVariable(value = "userId") Long userId,
            @PathVariable(value = "startDateAndTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startDateAndTime,
                                         @PathVariable(value = "endDateAndTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endDateAndTime
    ) throws ParseException {
        System.out.println(startDateAndTime);
        System.out.println(endDateAndTime);
        System.out.println(userId);
        return positionService.getPositionsBetweenTwoDates(userId, startDateAndTime, endDateAndTime);

    }


}
