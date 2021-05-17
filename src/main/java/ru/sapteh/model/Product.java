package ru.sapteh.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String title;
    @NonNull
    private double cost;
    private String description;
    @NonNull
    private String mainImagePath;
    @NonNull
    private int isActive;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ManufacturerID")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "product")
    Set<ProductSale> productSales;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", decimal=" + cost +
                ", description='" + description + '\'' +
                ", mainImagePath='" + mainImagePath + '\'' +
                ", isActive=" + isActive +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
