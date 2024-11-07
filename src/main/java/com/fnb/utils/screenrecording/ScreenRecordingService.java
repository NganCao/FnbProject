//package com.fnb.utils.screenrecording;
//
//import com.fnb.utils.PropertyUtils;
//import enums.ConfigProperties;
//import lombok.AccessLevel;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
//public class ScreenRecordingService {
//    public static void startRecording() {
//        if (PropertyUtils.getPropertyValue(ConfigProperties.RECORD_SCREEN).equalsIgnoreCase("yes")) {
//            ScreenRecordingUtils.startScreenRecording();
//        }
//    }
//
//    public static void stopRecording(String methodName) {
//        if (PropertyUtils.getPropertyValue(ConfigProperties.RECORD_SCREEN).equalsIgnoreCase("yes")) {
//            ScreenRecordingUtils.stopScreenRecording(methodName);
//        }
//
//    }
//}
