package ru.sapteh.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private Date saleDate;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "ProductID")
    private Product product;
    @NonNull
    private int quantity;

    @Override
    public String toString() {
        return "ProductSale{" +
                "id=" + id +
                ", saleDate=" + saleDate +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
