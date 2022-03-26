package com.github.biyanwen.databasedocaid.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * spring boot 容器加载后自动监听
 *
 * @author byw
 * @date 2022/03/26
 */
@Component
public class AidCommandRunner implements CommandLineRunner {

    @Value("${spring.web.loginUrl}")
    private String loginUrl;

    @Value("${spring.auto.openurl}")
    private boolean isOpen;

    @Override
    public void run(String... args) {
        if (isOpen) {
            try {
                Runtime.getRuntime().exec("cmd /c start " + loginUrl);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
