import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    String generateDate(int daysToAdd, String pattern) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));

    }

    @Test
    public void registrationCardTest() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Самара");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[name='name']").setValue("Семенова Анна Аркадьевна");
        $("[name='phone']").setValue("+79256584856");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(Condition.exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.hidden, Duration.ofMillis(15));
    }

    @Test
    public void registrationCardListTest() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Са");
        $(byText("Самара")).click();
        $$("span.icon-button__content").find(Condition.visible).click();
        $("[data-day='1685998800000']").click();
        $("[name='name']").setValue("Семенова Анна Аркадьевна");
        $("[name='phone']").setValue("+79256584856");
        $("[data-test-id='agreement']").click();
        $$("[type='button']").find(Condition.exactText("Забронировать")).click();
        $(withText("06.06.2023")).shouldBe(Condition.hidden, Duration.ofMillis(15));

    }

}

