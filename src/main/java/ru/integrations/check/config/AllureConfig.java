package ru.integrations.check.config;

import java.io.IOException;
import java.util.Properties;

public class AllureConfig {
    public boolean ALLURE_ENABLE;
    public String ALLURE_FOLDER;
    public String ALLURE_TOKEN;
    public String ALLURE_ENDPOINT;
    public Integer ALLURE_PROJECT_ID;
    public String ALLURE_LAUNCH_NAME;
    public String ALLURE_JOB_RUN_UID;
    public String ALLURE_CI_TYPE;
    private final Properties prop = new Properties();

    public AllureConfig() {
        init();
        initEnv();
    }

    public void init() {
        try {
            prop.load(AllureConfig.class.getClassLoader().getResourceAsStream("allureSetting.properties"));
            ALLURE_FOLDER = prop.getProperty("allure.folder");
            ALLURE_TOKEN = prop.getProperty("allure.token");
            ALLURE_ENDPOINT = prop.getProperty("allure.endpoint");
            if (ALLURE_PROJECT_ID == null || ALLURE_PROJECT_ID == 0) {
                if (prop.getProperty("allure.projectId") != null) {
                    ALLURE_PROJECT_ID = Integer.valueOf(prop.getProperty("allure.projectId"));
                }
            }
            ALLURE_CI_TYPE = prop.getProperty("allure.ciType");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initEnv() {
        String launch = prop.getProperty("allure.env.launch");
        if (System.getenv("ALLURE_ENABLE") != null) {
            ALLURE_ENABLE = Boolean.parseBoolean(System.getenv("ALLURE_ENABLE"));
        }
        if (System.getenv(launch) != null) {
            ALLURE_LAUNCH_NAME = System.getenv(launch);
            ALLURE_JOB_RUN_UID = System.getenv(launch);
        }
    }
}
