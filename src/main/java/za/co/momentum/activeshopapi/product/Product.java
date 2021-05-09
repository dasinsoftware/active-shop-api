package za.co.momentum.activeshopapi.product;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Data @Builder
@Entity
public class Product{
    @Id
    private Long code;
    private String name;
    @Min(0L)
    private Long pointsCost;
    @Tolerate
    public Product(){/* Lombok */}
}
