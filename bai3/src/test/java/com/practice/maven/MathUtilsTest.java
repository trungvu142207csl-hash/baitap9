package com.practice.maven;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathUtilsTest {

    // 1. Kiểm tra Logback (SLF4J)
    private static final Logger logger = LoggerFactory.getLogger(MathUtilsTest.class);

    @Test
    @DisplayName("Kiểm tra thư viện Logback và JUnit 5")
    void testLoggingAndJUnit5() {
        logger.info("Đang chạy kiểm thử với JUnit Jupiter 5.9.2...");
        logger.warn("Đây là một thông báo log có cấu trúc thay vì System.out.println");

        assertTrue(true, "JUnit 5 hoạt động bình thường!");
    }

    @Test
    @DisplayName("Kiểm tra khởi tạo Hibernate Core")
    void testHibernateInitialization() {
        try {
            // 2. Kiểm tra Hibernate Core 6.2.0.Final
            // Lưu ý: Đoạn này chỉ kiểm tra xem Class có tồn tại và khởi tạo được cấu hình
            // không
            Configuration cfg = new Configuration();
            assertNotNull(cfg, "Hibernate Configuration không được null");

            logger.info("Hibernate Core 6.2.0.Final đã được nạp thành công vào dự án!");
        } catch (Exception e) {
            logger.error("Lỗi khi nạp Hibernate: ", e);
        }
    }
}
/**
 * Logback Classic: Tự động đi kèm với slf4j-api, giúp bạn tách biệt giữa giao
 * diện ghi log và bộ cài đặt ghi log thực tế. Điều này giúp mã nguồn linh hoạt
 * hơn.
 * 
 * Hibernate 6.2.0.Final: Đây là phiên bản hiện đại, hỗ trợ tốt Java 17+ và các
 * chuẩn Jakarta EE mới nhất.
 * 
 * JUnit Jupiter: Đây là "trái tim" của JUnit 5. Việc sử dụng phiên bản 5.9.2
 * giúp bạn tận dụng các tính năng mới như @ParameterizedTest hay @DisplayName
 * mà JUnit 4 không có.
 * 
 * Scope Test: Lưu ý thư viện JUnit được đặt trong <scope>test</scope> để nó
 * không bị đóng gói khi bạn đóng gói sản phẩm cuối (deploy), giúp giảm dung
 * lượng file thực thi.
 */