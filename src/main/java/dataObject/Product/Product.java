package dataObject.Product;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    List<Information> product;

    @Data
    public class Information {
        private String name;
        private String image;
        private List<Price> price;
        private List<Platform> platforms;
        private String tax;
        private String unit;
        private List<productInventoryData> productInventoryData;
        private List<Option> option;
        private List<ToppingName> topping;
        private String productCategoryName;

        @Data
        public static class productInventoryData {
            private String name;

            private List<ListProductPriceMaterial> listProductPriceMaterials;

            @Data
            public class ListProductPriceMaterial {
                private String priceName;

                private String material;

                private String quantity;

                private String position;
            }
        }

        @Data
        public static class Price {
            public Price(String priceName, String priceValue, String position) {
                this.priceName = priceName;
                this.priceValue = priceValue;
                this.position = position;
            }

            private String priceName;

            private String priceValue;

            private String position;
        }

        @Data
        public static class Platform {
            public Platform(String name) {
                this.name = name;
            }
            private String name;
        }

        @Data
        public class Option {
            private String name;
        }

        @Data
        public class ToppingName {
            private String name;
        }
    }
}
