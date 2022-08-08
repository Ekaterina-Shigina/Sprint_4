package ru.yandex.practicum;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccountTest {

    private final String name;
    private final boolean expected;

    public AccountTest(String name, boolean expected){
        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "Проверка введеного имени на соответствие паттерну. Name = {0}")
    public static Object[] name(){
        return new Object[][]{
                {"Тимоти Шаламе", true},//валидное значение
                {"Т Ш", true},//валидное значение 3 символа
                {"Екатерина Шигинаааа", true},//валидное значение 19 символов
                {"Екатерина Шигинааааа", false},//более 19 символов
                {"Тимоти  Шаламе", false},//два пробела в середине значение
                {" Тимоти Шаламе", false},//пробел в начале
                {"Тимоти Шаламе ", false},//пробел в конц
                {"Ти", false},//два символа
                {"", false},//пустая строка
                {null, false},//null
                {"ТимотиШаламе", false},//без пробела
                {"ТимотиШаламе", false},//без пробела
        };

    }

    @DisplayName("Проверка введеного имени на соответствие паттерну")
    @Description("Ввод валидных и невалидных значений")
    @Test
    public void checkNameToEmbossTest(){

        Account account = new Account(name);
        boolean actual = account.checkNameToEmboss();
        assertEquals(expected, actual);

    }


}
