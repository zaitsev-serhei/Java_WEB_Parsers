package org.vavilon_learn;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private String category;
    private float price;
    private List<String> tags;
    private Metric metrics;
    private LocalDate released;

    private class Metric {
        private int sold;
        private double rating;

        public Metric() {
        }

        public int getSold() {
            return sold;
        }

        public void setSold(int sold) {
            this.sold = sold;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        @Override
        public String toString() {
            return "Metric{" +
                    "sold=" + sold +
                    ", rating=" + rating +
                    '}';
        }
    }


    public Product() {
        this.tags = Collections.emptyList();
        this.metrics = new Metric();
    }

    public Product(Long id,
                   String name,
                   String category,
                   Float price,
                   List<String> tags,
                   Metric metrics,
                   LocalDate released) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.tags = tags;
        this.metrics = metrics;
        this.released = released;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Metric getMetrics() {
        return metrics;
    }
    public int getSold(){
        return this.getMetrics().sold;
    }

    public void setMetrics(Metric metrics) {
        this.metrics = metrics;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public void setRating(Double rating){
        this.getMetrics().rating=rating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", tags=" + tags +
                ", metrics=" + metrics +
                ", releaseDate=" + released +
                '}';
    }
}
