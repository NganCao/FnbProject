package dataObject.Inventory;

import lombok.Data;
import java.util.List;

@Data
public class MaterialCategory {
    private List<Information> materialCategories;

    @Data
    public class Information {
        private String name;
        private String[] materials;
    }
}
