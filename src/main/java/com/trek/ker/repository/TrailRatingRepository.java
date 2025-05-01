package com.trek.ker.repository;

import com.trek.ker.entity.Trail;
import com.trek.ker.entity.TrailRating;
import com.trek.ker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrailRatingRepository extends JpaRepository<TrailRating, Long> {
    List<TrailRating> findByTrail(Trail trail);

    List<TrailRating> findByUser(User user);
}
