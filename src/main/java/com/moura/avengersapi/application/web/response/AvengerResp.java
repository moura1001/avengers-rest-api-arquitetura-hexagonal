package com.moura.avengersapi.application.web.response;

import com.moura.avengersapi.domain.avenger.Avenger;

public record AvengerResp(
        Long id,
        String nick,
        String person,
        String description,
        String history
) {
    public AvengerResp(Avenger avenger) {
        this(
                avenger.getId(),
                avenger.getNick(),
                avenger.getPerson(),
                avenger.getDescription(),
                avenger.getHistory()
        );
    }
}
