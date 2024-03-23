package com.moura.avengersapi.domain.avenger;

import java.util.List;

public interface AvengerStorage {
    List<Avenger> getAvengers();

    Avenger getAvengerById(Long id);

    Avenger createAvenger(Avenger avenger);

    Avenger updateAvenger(Avenger avenger);

    void deleteAvenger(Long id);
}
