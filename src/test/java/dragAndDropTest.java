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
    void replaceElementActionsTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

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
    }

    @Test
    void replaceElementDragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));

        $("#column-a").dragAndDrop(to("#column-b"));
        $("#column-b").shouldHave(text("A"));

        $("#column-b").dragAndDrop(to("#column-a"));
        $("#column-a").shouldHave(text("A"));
    }
}
