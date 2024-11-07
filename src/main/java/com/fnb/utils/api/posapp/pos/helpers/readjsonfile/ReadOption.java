package com.fnb.utils.api.posapp.pos.helpers.readjsonfile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadOption {
    public static final String FILE_PATH_READ_OPTION = "C:\\Users\\admin\\Documents\\Automation\\selenium\\resources\\data\\product\\option.json";

    public static void main(String[] args) {
            String s = "Đá viên (Vừa)";
        System.out.println(getQuotaOption(s));
    }

    //this func return Quota: 40
    public static String getQuotaOption(String s) {
        try {
            String optionName = getFirstPart(s);
            String optionLevelName = getSecondPart(s);
            String quota = readOptionDataFile(optionName, optionLevelName);
            if (quota != null) {
                return quota;
            } else {
                System.out.println("Không tìm thấy quota.");
                return "1";
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return "1";
    }

    public static String getFirstPart(String s) {
        if (s == null || s.isEmpty() || !s.contains(" (")) {
            return s;
        }
        return s.substring(0, s.indexOf(" ("));
    }

    public static String getSecondPart(String s) {
        if (s == null || s.isEmpty() || !s.contains("(") || !s.contains(")")) {
            return s;
        }
        int startIndex = s.indexOf("(") + 1;
        int endIndex = s.indexOf(")");
        if (startIndex >= endIndex) {
            return s;
        }
        return s.substring(startIndex, endIndex);
    }

    public static String readOptionDataFile(String optionName, String optionLevelName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(FILE_PATH_READ_OPTION);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        reader.close();

        JSONArray optionsArray = (JSONArray) jsonObject.get("options");
        if (optionsArray != null) {
            for (Object obj : optionsArray) {
                JSONObject option = (JSONObject) obj;
                if (optionName.equals(option.get("name"))) {
                    JSONArray optionLevelsArray = (JSONArray) option.get("optionLevels");
                    for (Object levelObj : optionLevelsArray) {
                        JSONObject level = (JSONObject) levelObj;
                        if (optionLevelName.equals(level.get("name"))) {
                            return (String) level.get("quota");
                        }
                    }
                }
            }
        }
        return null;
    }
}
