package com.worldishes.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(nullable = false)
    private String link;
    private Boolean cover;

    public Image(UUID dishId, String link, Boolean cover) {
        this.dishId = dishId;
        this.link = link;
        this.cover = cover;
    }

    public Image() {
    }

    public Boolean getCover() {
        return cover;
    }

    public void setCover(Boolean cover) {
        this.cover = cover;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UUID getDishId() {
        return dishId;
    }

    public void setDishId(UUID dishId) {
        this.dishId = dishId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(dishId, image.dishId) && Objects.equals(link,
                image.link) && Objects.equals(cover, image.cover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dishId, link, cover);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", link='" + link + '\'' +
                ", cover=" + cover +
                '}';
    }
}
