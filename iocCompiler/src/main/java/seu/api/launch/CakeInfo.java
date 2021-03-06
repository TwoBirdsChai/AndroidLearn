package seu.api.launch;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class CakeInfo {
    private final String moduleName;
    public final String FILE_FULL_NAME;
    public CakeInfo(String moduleName) {
        this.moduleName = moduleName;
        FILE_FULL_NAME = PACKAGE_NAME + "." + CLASS_NAME + "$$" + moduleName;
    }
    public String getModuleName() {
        return moduleName;
    }
    Map<String, String> map = new HashMap<>();
    public void putPackageName(String key, String classFullName) {
        map.put(key, classFullName);
    }

    public String getPackageName(String key) {
        return map.get(key);
    }

    public final String PACKAGE_NAME = "seu.com.util";//所有生成的java都放在同一个文件加下面。
    public final String CLASS_NAME = "LaunchUtil";
    public String getJavaCode() {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(PACKAGE_NAME).append(";\n\n");

        sb.append("import java.util.HashMap;\n");
        sb.append("import java.util.Map;\n");
        sb.append("import seu.annotation.router.MergeLaunch;\n");
        sb.append("import seu.api.compiler.LaunchInjector;\n\n");

        sb.append("@MergeLaunch\n");
        sb.append("public class ").append(CLASS_NAME + "$$" + moduleName).append(" implements LaunchInjector {\n");
        sb.append("public static Map<String, Class<?>> map = new HashMap<String, Class<?>>();\n");
        sb.append("static{\n");
        for (String key: map.keySet()) {
            String classFullName = map.get(key);
            sb.append("map.put(\"" + key + "\", " + classFullName + ".class);\n");
        }
        sb.append("}\n");
        sb.append("public Class<?> getPackageName(String key) {\n");
        sb.append("return map.get(key);\n");
        sb.append("}\n");
        sb.append("public Map<String, Class<?>> getMap() {\n");
        sb.append("return map;\n");
        sb.append("}\n");
        sb.append("}\n");
        return sb.toString();
    }
}
