package com.mybookingapp.mybookingapp.movie;
import com.mybookingapp.mybookingapp.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}