import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class dragAndDropTest {
    @BeforeAll
    static void setUp () {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void replaceElementTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        // Проверка перетаскивание квадрата А к квадрату B в правую сторону
        actions().moveToElement($("#column-a"))
                .clickAndHold().moveToElement($("#column-b"))
                .release().perform();
        $("#column-b").shouldHave(text("A"));

        // Проверка перетаскивание квадрата А к квадрату B в левую сторону
        actions().moveToElement($("#column-b"))
                .clickAndHold().moveToElement($("#column-a"))
                .release().perform();
        $("#column-a").shouldHave(text("A"));

        // Пример использования вместо actions() dragAndDrop()
        $("#column-a").dragAndDrop(to("#column-b"));
        $("#column-b").shouldHave(text("A"));

        $("#column-b").dragAndDrop(to("#column-a"));
        $("#column-a").shouldHave(text("A"));
    }
}
