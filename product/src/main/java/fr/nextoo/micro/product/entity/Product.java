package fr.nextoo.micro.product.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private Integer id;

    @Column(unique = true)
    private String label;

    @Column(name = "description")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
