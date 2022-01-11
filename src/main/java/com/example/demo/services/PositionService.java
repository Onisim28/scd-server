package com.example.demo.services;

import com.example.demo.model.Position;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Page<Position> getAllPositionsByUserId(Long userId, Pageable pageable) {
        return positionRepository.findByUserId(userId, pageable);
    }

    public Optional<Position> createPosition(Long userId, Position position) {
        return userRepository.findById(userId).map(user -> {
            position.setUser(user);
            return positionRepository.save(position);
        });
    }

    public List<Position> getPositionsBetweenTwoDates (Long userId, Date startDateTime, Date endDateTime) {

        return positionRepository.getPositionsBetweenTwoDates(userId, startDateTime, endDateTime);
    }

}
