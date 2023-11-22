package com.films.system.films.domain;

import com.films.system.common.domain.test.UuidMother;
import com.films.system.common.domain.valueobject.ImageId;

public class ImageIdMother {
  public static ImageId random() {
    return new ImageId(UuidMother.randomUUID());
  }

  public static ImageId from(String value) {
    return new ImageId(UuidMother.fromUUID(value));
  }
}
