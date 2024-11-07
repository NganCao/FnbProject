package dataObject.Product;

import lombok.Data;

import java.util.List;

@Data
public class Topping {
    private List<Information> topping;

    @Data
    public class Information {
        private String name;
        private String image;
        private String price;
        private List<Platform> platforms;
        private String unit;
        private List<ProductInventoryData> productInventoryData;

        @Data
        public class Platform {
            private String name;
        }

        @Data
        public class ProductInventoryData {
            private String quantity;
            private String material;
        }
    }
}
