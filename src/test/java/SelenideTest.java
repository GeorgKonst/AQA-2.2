import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofMillis;

public class SelenideTest {
    int random = (int) ( Math.random()* (30 - 3 + 1) + 3 );
    String date = LocalDate.now().plusDays(random).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Test
    void shouldValidResponse() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue("Тестов Тестович");
        $("[name='phone']").setValue("+79109999999");
        $(".checkbox").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}