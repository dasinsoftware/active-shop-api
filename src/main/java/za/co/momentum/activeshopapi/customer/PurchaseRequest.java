package za.co.momentum.activeshopapi.customer;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data @Builder
public class PurchaseRequest {
    @NotEmpty(message = "Please provide at least 1 product")
    @NotNull(message = "Please provide at least 1 product")
    Set<Long> productIds;
    @Tolerate
    public PurchaseRequest(){/*Lombok*/}
}
