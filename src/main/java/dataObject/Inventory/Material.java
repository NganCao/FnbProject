package dataObject.Inventory;

import lombok.Data;

import java.util.List;

@Data
public class Material {
    private List<Information> material;

    @Data
    public class Information {
        private String name;
        private String image;
        private String unit;
        private String costPerUnit;
        private String minQuantity;
        private List<Branch> branch;

        @Data
        public class Branch {
            private String name;

            private String quantity;
        }
    }
}
