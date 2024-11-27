package com.easylive.entity.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${project.folder:}")
    private String projectFolder;

    @Value("${admin.account:}")
    private String adminAccount;

    @Value("${admin.password:}")
    private String adminPassword;

    @Value("${showFFmegLog:true:}")
    private Boolean showFFmegLog;

    public String getProjectFolder() {
        return projectFolder;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public Boolean getShowFFmegLog() {
        return showFFmegLog;
    }
}
