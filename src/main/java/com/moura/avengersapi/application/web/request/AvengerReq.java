package com.moura.avengersapi.application.web.request;

import com.moura.avengersapi.domain.avenger.Avenger;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AvengerReq(
        @NotEmpty @Size(min = 3, max = 32) String nick,
        @NotEmpty @Size(min = 3, max = 128) String person,
        @NotEmpty @Size(min = 8, max = 512) String description,
        @NotEmpty @Size(min = 8, max = 2048) String history
) {
    public Avenger toEntity() {
        return new Avenger(
                this.nick,
                this.person,
                this.description,
                this.history
        );
    }

    public Avenger toEntity(Long id) {
        return new Avenger(
                id,
                this.nick,
                this.person,
                this.description,
                this.history
        );
    }
}
