package dataObject.Product;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategory {

    private List<Information> productCategory;

    @Data
    public class Information {
        private String name;
        private boolean isDisplayAllBranches;
        private List<BranchName> branchs;
        private List<ProductName> products;
        private String priority;

        @Data
        public class ProductName {
            private String name;
        }

        @Data
        public class BranchName {
            private String name;
        }
    }
}
