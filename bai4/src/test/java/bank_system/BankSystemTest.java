package bank_system;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class BankSystemTest {
    private static final Logger logger = LoggerFactory.getLogger(BankSystemTest.class);
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    @BeforeEach
    void setUp() {
        // Khởi tạo dữ liệu mẫu trước mỗi bài test
        checkingAccount = new CheckingAccount(12345L, 1000.0);
        savingsAccount = new SavingsAccount(67890L, 10000.0);
    }

    @Test
    @DisplayName("Test nạp tiền và kiểm tra log INFO")
    void testDepositLogging() {
        logger.info("Bắt đầu test nạp tiền cho CheckingAccount...");
        checkingAccount.deposit(500.0);

        assertEquals(1500.0, checkingAccount.getBalance());
        assertFalse(checkingAccount.getTransactionList().isEmpty());
        logger.info("Nạp tiền thành công. Số dư hiện tại: {}", checkingAccount.getBalance());
    }

    @Test
    @DisplayName("Test rút tiền quá hạn mức và kiểm tra log WARN/ERROR")
    void testWithdrawSavingsLimit() {
        logger.info("Bắt đầu test rút tiền quá hạn mức (1000$) của tài khoản tiết kiệm...");

        // Theo quy định trong SavingsAccount, rút > 1000$ sẽ lỗi
        savingsAccount.withdraw(2000.0);

        // Số dư không đổi vì giao dịch thất bại
        assertEquals(10000.0, savingsAccount.getBalance());
        logger.warn("Giao dịch rút 2000$ thất bại theo đúng thiết kế (Hạn mức 1000$).");
    }

    @Test
    @DisplayName("Test ngoại lệ số dư không đủ")
    void testInsufficientFunds() {
        logger.info("Bắt đầu test rút tiền khi số dư không đủ...");
        checkingAccount.withdraw(2000.0);

        assertEquals(1000.0, checkingAccount.getBalance());
        logger.error("Đã chặn giao dịch rút tiền vượt quá số dư hiện có.");
    }

    @Test
    @DisplayName("Test tính toàn vẹn của lịch sử giao dịch")
    void testTransactionHistory() {
        checkingAccount.deposit(200.0);
        checkingAccount.withdraw(100.0);

        String history = checkingAccount.getTransactionHistory();
        assertNotNull(history);
        assertTrue(history.contains("Nạp tiền vãng lai"));
        assertTrue(history.contains("Rút tiền vãng lai"));

        logger.info("Lịch sử giao dịch: \n{}", history);
    }

    @Test
    @DisplayName("Test lỗi đường dẫn cứng - It works on my machine")
    void testHardcodedPath() {
        // SỬ DỤNG ĐƯỜNG DẪN CỨNG KIỂU WINDOWS (Dấu \)
        String path = "target\\test-files\\data.txt";

        java.io.File file = new java.io.File(path);
        // Bài test này sẽ CHẠY ĐƯỢC trên windows-latest
        // Nhưng sẽ THẤT BẠI trên ubuntu và macos vì chúng dùng dấu /
        assertNotNull(file.getPath());
        logger.info("Đang kiểm tra đường dẫn: {}", file.getPath());
    }
    @Test
    void testDuongDanLoi() {
    // Dấu gạch chéo ngược \ chỉ chạy trên Windows
    java.io.File file = new java.io.File("target\\test.txt");
    // Trên Linux/macOS, lệnh này có thể không ném lỗi ngay nhưng đường dẫn sẽ bị sai
    assertTrue(file.getPath().contains("\\")); 
}
}