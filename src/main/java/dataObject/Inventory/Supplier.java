package dataObject.Inventory;

import lombok.Data;
import java.util.List;

@Data
public class Supplier {
    private List<Information> suppliers;

    @Data
    public class Information {
        private String name;
        private String code;
        private Address address;
        private String phoneNumber;
        private String email;

        @Data
        public class Address {
            private String countryName;
            private String address;
            private String city;
            private String district;
            private String ward;
        }
    }
}
