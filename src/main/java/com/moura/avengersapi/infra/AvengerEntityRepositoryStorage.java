package com.moura.avengersapi.infra;

import com.moura.avengersapi.domain.avenger.Avenger;
import com.moura.avengersapi.domain.avenger.AvengerStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("avengerEntityRepositoryStorage")
public class AvengerEntityRepositoryStorage implements AvengerStorage {
    @Autowired
    private AvengerEntityRepository repository;

    @Override
    public List<Avenger> getAvengers() {
        List<Avenger> avengers = repository.findAll().stream()
                .map((avenger) -> avenger.toDomain()).collect(Collectors.toList());
        return avengers;
    }

    @Override
    public Avenger getAvengerById(Long id) {
        Optional<AvengerEntity> avenger = repository.findById(id);
        if (avenger.isPresent())
            return avenger.get().toDomain();
        return null;
    }

    @Override
    public Avenger createAvenger(Avenger avenger) {
        AvengerEntity entity = new AvengerEntity(
                avenger.getNick(),
                avenger.getPerson(),
                avenger.getDescription(),
                avenger.getHistory()
        );
        return repository.save(entity).toDomain();
    }

    @Override
    public Avenger updateAvenger(Avenger avenger) {
        if (repository.existsById(avenger.getId())) {
            AvengerEntity entity = new AvengerEntity(
                    avenger.getId(),
                    avenger.getNick(),
                    avenger.getPerson(),
                    avenger.getDescription(),
                    avenger.getHistory()
            );
            return repository.save(entity).toDomain();
        }
        return null;
    }

    @Override
    public boolean deleteAvenger(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
