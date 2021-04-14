import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.conditions.CssValue;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofMillis;

public class SelenideTest {
    int random = (int) ( Math.random()* (30 - 3 + 1) + 3 );
    String date = LocalDate.now().plusDays(random).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    @Test
    void shouldValidForm() {
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

    @Test
    void shouldEmptyForm() {
        open("http://localhost:9999/");
        $(".button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotValidCity() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Ясногорск");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue("Тестов Тестович");
        $("[name='phone']").setValue("+79109999999");
        $(".checkbox").click();
        $(".button").click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldEmptyCity() {
        open("http://localhost:9999/");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue("Тестов Тестович");
        $("[name='phone']").setValue("+79109999999");
        $(".checkbox").click();
        $(".button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotValidDate() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        $("[name='name']").setValue("Тестов Тестович");
        $("[name='phone']").setValue("+79109999999");
        $(".checkbox").click();
        $(".button").click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotValidTel() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue("Тестов Тестович");
        $("[name='phone']").setValue("+7910999999");
        $(".checkbox").click();
        $(".button").click();
        $(withText("Телефон указан неверно. Должно быть 11 цифр")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotClickCheckbox() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Москва");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date);
        $("[name='name']").setValue("Тестов Тестович");
        $("[name='phone']").setValue("+79109999999");
        $(".button").click();
        $(withText("Я соглашаюсь")).shouldBe(Condition.cssValue("color", "rgba(255, 92, 92, 1)"));
    }
}