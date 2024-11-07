package dataObject.Product;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SpecificCombo {
    private List<Information> specificCombo;

    @Data
    public class Information {
        private String name;
        private String startDate;
        private String endDate;
        private String image;
        private String description;
        private String[] branchNames;
        private String sellingPrice;
        private String[] comboProductNames;
        private boolean isShowAllBranches;
    }
}
