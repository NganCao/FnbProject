package dataObject.Product;

import lombok.Data;

import java.util.List;

@Data
public class Options {
    private List<Information> options;

    @Data
    public class Information {
        private String name;

        private String materialName;

        private List<optionLevels> optionLevels;

        @Data
        public class optionLevels {
            private String name;

            private String quota;

        }
    }
}
