package loja.productservice.entity.products;

import jakarta.persistence.*;
import loja.productservice.entity.category.CategoryModel;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false, scale = 2)
    private BigDecimal preco;
    private int quantity;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private CategoryModel category;
}
