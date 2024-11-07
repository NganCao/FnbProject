package dataObject.Store;

import lombok.Data;

import java.util.List;

@Data
public class Tax {
    private List<Information> tax;

    @Data
    public class Information {
        private String name;
        private String percentage;
        private String taxType;
    }
}
