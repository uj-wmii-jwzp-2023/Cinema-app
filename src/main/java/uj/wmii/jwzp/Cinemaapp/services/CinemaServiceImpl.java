package uj.wmii.jwzp.Cinemaapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.repositories.CinemaRepository;

@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    private final CinemaRepository repository;

    public CinemaServiceImpl(CinemaRepository repository) {
        this.repository = repository;
    }
}
