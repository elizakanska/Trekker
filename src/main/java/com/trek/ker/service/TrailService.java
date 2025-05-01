package com.trek.ker.service;

import com.trek.ker.entity.Trail;
import com.trek.ker.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrailService {
    @Autowired
    private TrailRepository trailRepo;

    public List<Trail> getAllTrails() {
        return trailRepo.findAll();
    }

    public Trail getTrailById(Long id) {
        return trailRepo.findById(id).orElse(null);
    }

    public Trail saveTrail(Trail trail) {
        return trailRepo.save(trail);
    }

    public void deleteTrail(Long id) {
        trailRepo.deleteById(id);
    }
}
