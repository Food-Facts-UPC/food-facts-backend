package org.foodfacts.restaurants.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

@Embeddable
public class Image {

    @Lob
    @Column(name = "image_data", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] data;

    public Image() {}

    public static Image of(byte[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("La imagen no puede estar vacía");
        }
        Image img = new Image();
        img.data = Arrays.copyOf(data, data.length);
        return img;
    }

    public byte[] getData() {
        return Arrays.copyOf(data, data.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        Image other = (Image) o;
        return Arrays.equals(data, other.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(data));
    }
}
