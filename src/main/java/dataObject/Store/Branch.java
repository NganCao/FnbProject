package dataObject.Store;

import lombok.Data;

import java.util.List;

@Data
public class Branch {
    private List<Information> branch;

    @Data
    public class Information {
        private String name;
        private String phoneNumber;
        private String addressInfo;
        private Address address;

        @Data
        public class Address {
            private String address1;
            private Country country;
            private City city;
            private District district;
            private Ward ward;

            @Data
            public class Country {
                private String name;
            }

            @Data
            public class City {
                private String name;
            }

            @Data
            public class District {
                private String name;
                private String prefix;
            }

            @Data
            public class Ward {
                private String name;
                private String prefix;
            }
        }
    }
}
