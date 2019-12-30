package fr.nextoo.micro.order.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private Long id;
    private Double price;
    @Column(name = "user_id", nullable = false)
    private Long userId;

}
