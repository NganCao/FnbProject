package com.fnb.web.store.theme2.pages.home;

import java.util.Map;

public class HomeDataTest {
    public static final String URL = "home";
    public static final String SHOPPING_CART = "shoppingCart";
    public static final String STOREWEB_PAGE = "storeWebPage";
    public static final String MY_PROFILE = "myProfile";
    public static final String MY_RESERVATIONS = "reserve";
    public static final String DEFAULT_LANGUAGE = "VI";
    //language
    public static final Map.Entry<String, String> VI = Map.entry("VI", "Tiếng Việt");
    public static final Map.Entry<String, String> EN = Map.entry("EN", "Tiếng Anh");
    public static final Map<String, String> LANGUAGE_MAP = Map.ofEntries(VI, EN);

}
