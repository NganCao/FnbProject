package dataObject.Store;

import lombok.Data;

import java.util.List;

@Data
public class AreaTable {
    private List<Information> areas;

    @Data
    public class Information {
        private String branch;
        private String areaName;
        private List<Table> tables;

        @Data
        public class Table {
            private String tableName;
            private String seat;
        }
    }
}
