package dataObject.Integration;

import lombok.Data;

@Data
public class MaterialInventoryHistory {
    private String previousQuantity;
    private String change;
    private String ingredient;
    private String baseUnitName;
    private String branchName;
    private String category;
    private String reference;
    private String remain;
}
