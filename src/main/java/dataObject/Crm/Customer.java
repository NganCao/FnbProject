package dataObject.Crm;

import dataObject.Inventory.Material;
import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private List<Information> customers;

    @Data
    public static class Information {
        private String firstName;
        private String lastName;
        private Address address;
        private String phone;
        private String email;
        private String birthDay;
        private String gender;
        private List<String> tags;
        private String rank;
        private String rewardPoint;
        private String totalOrder;
        private String totalMoney;

        @Data
        public static class Address {
            private String country;
            private String address;
            private String city;
            private String district;
            private String ward;
        }
    }
}
