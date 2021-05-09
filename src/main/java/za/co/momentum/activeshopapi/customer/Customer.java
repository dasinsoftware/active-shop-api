package za.co.momentum.activeshopapi.customer;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Data @Builder
@Entity
public class Customer {
    @Id
    private Long id;
    private String name;
    @Min(0L)
    private Long points;
    @Tolerate
    public Customer() {/* Lombok */ }
}
