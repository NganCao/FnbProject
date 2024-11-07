package dataObject.Integration;

import lombok.Data;

@Data
public class CustomerMembership {
    private String name;
    private Double accumulatedPoint;
    private Double discount;
    private Double maximumDiscount;
}
