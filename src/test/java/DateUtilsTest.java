import org.example.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DateUtilsTest {
    private DateUtils testDate;

    @BeforeAll
    public void initClass() {
        testDate = new DateUtils();
    }

    @Test
    public void testIsLeapYearDivisibleBy400() {
        Assertions.assertTrue(testDate.isLeapYear(400));
    }

    @Test
    public void testIsLeapYearIndivisibleBy400() {
        Assertions.assertFalse(testDate.isLeapYear(200));
    }

    @Test
    public void testIsLeapYearIndivisibleBy100() {
        Assertions.assertTrue(testDate.isLeapYear(4));
    }

    @Test
    public void testIsLeapYearIndivisibleBy4() {
        Assertions.assertFalse(testDate.isLeapYear(3));
    }

    @Test
    public void getDaysInMonth() {
        Assertions.assertEquals(31, testDate.getDaysInMonth(1, 1));
        Assertions.assertEquals(28, testDate.getDaysInMonth(1, 2));
    }

    @Test
    public void testGetDaysInInvalidMonth() {
        Throwable exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    testDate.getDaysInMonth(1000, 13);
                }
        );

        Assertions.assertEquals("Invalid month value: 13", exception.getMessage());
    }

    @Test
    public void testGetDaysInFebruaryLeapYear() {
        Assertions.assertEquals(29, testDate.getDaysInMonth(4, 2));
    }
}
