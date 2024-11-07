package com.fnb.utils.api.storeweb.pos.helpers;

import com.fnb.utils.helpers.JsonReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonAPIPosReader {
    private static APIPosService apiPosService;

    //all json file created by api
    private static String path = "src/main/java/com/fnb/utils/api/storeweb/";

    public static Map<String, String> getDistanceReader(String fileName) {
        String configFile = path + fileName;
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonArray branches = config.getAsJsonArray("branchesByCustomerAddress");
        Map<String, String> distances = new HashMap<>();
        String distance;
        for (int i = 0; i < branches.size(); i++) {
            distance = branches.get(i).getAsJsonObject().get("distance").getAsString();
            float result = (float) (Math.round((Float.parseFloat(distance) / 1000) * 10.0) / 10.0);
            distances.put(branches.get(i).getAsJsonObject().get("branchName").getAsString(), String.valueOf(result));
        }
        return distances;
    }

    public static class Branch {
        private String branchName;
        private String branchAddress;

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getBranchAddress() {
            return branchAddress;
        }

        public void setBranchAddress(String branchAddress) {
            this.branchAddress = branchAddress;
        }

        public String getBranchDistance() {
            return branchDistance;
        }

        public void setBranchDistance(String branchDistance) {
            this.branchDistance = branchDistance;
        }

        private String branchDistance;

        @Override
        public String toString() {
            return "Name: \"" + branchName + "\"\nAddress: \"" + branchAddress + "\"\nDistance: \"" + branchDistance + "\"";
        }
    }

    public static List<Branch> getAllBranches(String fileName) {
        List<Branch> branches = new ArrayList<>();
        Branch branch;
        String configFile = path + fileName;
        String address;
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonArray branchesByCustomerAddress = config.getAsJsonArray("branchesByCustomerAddress");
        String distance;
        for (int i = 0; i < branchesByCustomerAddress.size(); i++) {
            branch = new Branch();
            //round distance and parse from float to string
            distance = branchesByCustomerAddress.get(i).getAsJsonObject().get("distance").getAsString().trim();
            float result = (float) (Math.round((Float.parseFloat(distance) / 1000) * 10.0) / 10.0);
            branch.setBranchName(branchesByCustomerAddress.get(i).getAsJsonObject().get("branchName").getAsString().trim());
            address = branchesByCustomerAddress.get(i).getAsJsonObject().get("branchAddress").getAsString().trim();
            if (address.contains("  ")) address = address.replace("  ", " ");
            branch.setBranchAddress(address);
            branch.setBranchDistance(String.valueOf(result));
            branches.add(branch);
        }
        return branches;
    }

    public static class MenuItem {
        private String id;
        private String name;
        private String url;
        private Boolean hasChildren;

        public MenuItem() {
            this.childrenList = new ArrayList<>();
        }

        public List<ChildMenuItem> getChildrenList() {
            return childrenList;
        }

        public void addMenuChild(ChildMenuItem childMenuItem) {
            this.childrenList.add(childMenuItem);
        }

        private List<ChildMenuItem> childrenList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Boolean getHasChildren() {
            return hasChildren;
        }

        public void setHasChildren(Boolean hasChildren) {
            this.hasChildren = hasChildren;
        }

        @Override
        public String toString() {
            return "Menu: \n" + name;
        }

        public void showAll() {
            System.out.println("\tName: " + name + " - has child: " + hasChildren);
            if (hasChildren == true) {
                for (ChildMenuItem item : childrenList) {
                    System.out.println("\tChild: " + item.toString());
                }
            }
        }
    }

    public static class ChildMenuItem extends MenuItem {
        private String parentId;

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return super.toString() + "\nParentId: " + parentId;
        }
    }

    /**
     * Applying for submenu level 1
     *
     * @param filePath
     */
    public static List<MenuItem> getMenuHeaderList(String filePath) {
        String configFile = path + filePath;//path + filePath;
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonObject general = config.get("general").getAsJsonObject();
        JsonObject header = general.get("header").getAsJsonObject();
        JsonArray menuItems = header.getAsJsonArray("menuItems");
        JsonArray childMenuItems;
        MenuItem menuItem;
        ChildMenuItem childMenuItem;
        List<MenuItem> menuItemList = new ArrayList<>();
        for (int i = 0; i < menuItems.size(); i++) {
            menuItem = new MenuItem();
            menuItem.setId(menuItems.get(i).getAsJsonObject().get("id").getAsString());
            menuItem.setName(menuItems.get(i).getAsJsonObject().get("name").getAsString());
            childMenuItems = menuItems.get(i).getAsJsonObject().getAsJsonArray("children");
            if (childMenuItems.size() > 0) {
                menuItem.setHasChildren(true);
                for (int y = 0; y < childMenuItems.size(); y++) {
                    childMenuItem = new ChildMenuItem();
                    childMenuItem.setId(childMenuItems.get(y).getAsJsonObject().get("id").getAsString());
                    childMenuItem.setName(childMenuItems.get(y).getAsJsonObject().get("name").getAsString());
                    childMenuItem.setParentId(childMenuItems.get(y).getAsJsonObject().get("parentId").getAsString());
                    childMenuItem.setHasChildren(false); //default to run
                    menuItem.addMenuChild(childMenuItem);
                }
                menuItem.setHasChildren(true);
            } else {
                menuItem.setHasChildren(false);
            }
            menuItemList.add(menuItem);
        }
        return menuItemList;
    }

    //TODO will enhance after submenu multi level featured has finished
    public static List<MenuItem> getMenuHeaderList2(String filePath) {
        String configFile = path + filePath;//path + filePath;
        JsonObject config = JsonReader.readConfigFile(configFile);
        JsonObject general = config.get("general").getAsJsonObject();
        JsonObject header = general.get("header").getAsJsonObject();
        JsonArray menuItems = header.getAsJsonArray("menuItems");
        JsonArray childMenuItems;
        JsonArray childMultiItems;
        MenuItem menuItem;
        ChildMenuItem childMenuItem;
        List<MenuItem> menuItemList = new ArrayList<>();
        parentMenu:
        for (int i = 0; i < menuItems.size(); i++) {
            menuItem = new MenuItem();
            menuItem.setId(menuItems.get(i).getAsJsonObject().get("id").getAsString());
            menuItem.setName(menuItems.get(i).getAsJsonObject().get("name").getAsString());
            menuItem.setUrl(menuItems.get(i).getAsJsonObject().get("url").getAsString());
            childMenuItems = menuItems.get(i).getAsJsonObject().getAsJsonArray("children");

            if (childMenuItems.size() > 0) {
                childMenu:
                {
                    menuItem.setHasChildren(true);
                    for (int y = 0; y < childMenuItems.size(); y++) {
                        childMultiItems = childMenuItems.get(y).getAsJsonObject().getAsJsonArray("children");
                        System.out.println(childMenuItems.get(y).getAsJsonObject().get("name").getAsString() + " " + childMultiItems.size());
                        childMenuItem = new ChildMenuItem();
                        childMenuItem.setId(childMenuItems.get(y).getAsJsonObject().get("id").getAsString());
                        childMenuItem.setName(childMenuItems.get(y).getAsJsonObject().get("name").getAsString());
                        childMenuItem.setUrl(childMenuItems.get(y).getAsJsonObject().get("url").getAsString());
                        childMenuItem.setParentId(childMenuItems.get(y).getAsJsonObject().get("parentId").getAsString());
//                        int size = childMenuItems.get(y).getAsJsonObject().getAsJsonArray("children").size();
//                    if(size > 0) {
//                        continue childMenu;
//                    }
                        childMenuItem.setHasChildren(false);
                        menuItem.addMenuChild(childMenuItem);
                    }
                    menuItem.setHasChildren(true);
//                } else {
//                    menuItem.setHasChildren(false);
                }
            }

            menuItemList.add(menuItem);
        }
        System.out.println("----------1---------");
        int x = 0;
        for (MenuItem b : menuItemList) {
            System.out.println("************   " + x + "   ************");
            b.showAll();
            x++;
        }
        return menuItemList;
    }

    public static String getCurrencySymbol() {
        String configFile = path + "/" + apiPosService.getFilePath("storeConfig.json");
        JsonObject config = JsonReader.readConfigFile(configFile);
        return config.get("currencySymbol").getAsString();
    }

    public static String getCurrencyCode() {
        String configFile = path + "/" + apiPosService.getFilePath("storeConfig.json");
        JsonObject config = JsonReader.readConfigFile(configFile);
        String currencyCode = config.get("currencyCode").getAsString();
        if (currencyCode.equals("VND")) currencyCode = "VNĐ";
        return currencyCode;
    }
}
