package com.films.system.films.domain;

import com.films.system.common.domain.test.UuidMother;
import com.films.system.common.domain.valueobject.FilmId;

public class FilmIdMother {
    public static FilmId random() {
        return new FilmId(UuidMother.randomUUID());
    }

    public static FilmId from(String value) {
        return new FilmId(UuidMother.fromUUID(value));
    }
}
