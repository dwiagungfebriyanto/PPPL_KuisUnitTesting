import org.example.BankAccount;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankAccountTest {
    private BankAccount testAccount;
    private BankAccount recipientAccount;

    @BeforeEach
    public void initMethod() {
        testAccount = new BankAccount("123456789", 100000);
        recipientAccount = new BankAccount("987654321", 0);
    }

    @AfterEach
    public void cleanMethod() {
        testAccount = null;
        recipientAccount = null;
    }

    @Test
    public void testGetAccountNumber() {
        // objek testAccount
        Assertions.assertNotNull(testAccount.getAccountNumber());
        Assertions.assertEquals("123456789", testAccount.getAccountNumber());
        // objek recipientAccount
        Assertions.assertNotNull(recipientAccount.getAccountNumber());
        Assertions.assertEquals("987654321", recipientAccount.getAccountNumber());
    }

    @Test
    public void testGetBalance() {
        // objek testAccount
        Assertions.assertNotEquals(0, testAccount.getBalance());
        Assertions.assertEquals(100000, testAccount.getBalance());
        // objek recipientAccount
        Assertions.assertEquals(0, recipientAccount.getBalance());
    }

    @Test
    public void testDeposit() {
        testAccount.deposit(50000);

        Assertions.assertNotEquals(100000, testAccount.getBalance());
        Assertions.assertEquals(150000, testAccount.getBalance());
    }

    @Test
    public void testDepositNegativeAmount() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    testAccount.deposit(-10);
                }
        );

        Assertions.assertEquals("Deposit amount cannot be negative.", exception.getMessage());
    }

    @Test
    public void testWithdraw() {
        testAccount.withdraw(50000);

        Assertions.assertNotEquals(100000, testAccount.getBalance());
        Assertions.assertEquals(50000, testAccount.getBalance());
    }

    @Test
    public void testWithdrawInsuficientFunds() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    testAccount.withdraw(150000);
                }
        );

        Assertions.assertEquals("Insufficient funds for withdraw", exception.getMessage());
    }

    @Test
    public void testTransferFunds() {
        testAccount.transferFunds(recipientAccount, 50000);

        Assertions.assertEquals(50000, testAccount.getBalance());
        Assertions.assertEquals(50000, recipientAccount.getBalance());
    }

    @Test
    public void testTransferInsuficientFunds() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    testAccount.transferFunds(recipientAccount, 150000);
                }
        );

        Assertions.assertEquals("Insufficient funds for transfer.", exception.getMessage());
    }
}
