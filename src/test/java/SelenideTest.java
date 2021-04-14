import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @Test
    void shouldValidResponse() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Москва");
        sleep(5000);
    }
}
