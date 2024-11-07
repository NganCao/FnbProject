package constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String TEST_RESOURCES_DIR = PROJECT_PATH + File.separator + "src/test/resources";

    public static final String CONFIG_PROPERTIES_PATH =
            TEST_RESOURCES_DIR + File.separator + "config" + File.separator + "config.properties";
    public static final String EXPORT_VIDEO_PATH = "ExportData/Videos";
}
