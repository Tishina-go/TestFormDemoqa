package com.example;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TformDemoqa {


        @BeforeAll
        static void setUp() {
            String browser = System.getProperty("browser", "firefox");
            Configuration.browser = browser;

            switch (browser) {
                case "firefox" -> WebDriverManager.firefoxdriver().setup();
                //case "edge"    -> WebDriverManager.edgedriver().setup();
                //case "opera"   -> WebDriverManager.operadriver().setup();
                //default        -> WebDriverManager.chromedriver().setup();
            }

            Configuration.browserSize = "1920x1080";
            Configuration.baseUrl = "https://demoqa.com";
            Configuration.pageLoadStrategy = "eager";
        }

    @Test
    @DisplayName("Успешное заполнение всех полей формы")
    public void fillAllRegistrationFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Evgeniy");
        $("#lastName").setValue("P");
        $("#userEmail").setValue("test@mail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9998887766");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1991");
        $$("div.react-datepicker__day").findBy(text("7")).click();

        $("#subjectsInput").setValue("ma");
        $("#react-select-2-option-0").click();
        $("#subjectsInput").setValue("p");
        $("#react-select-2-option-0").click();


        $("label[for=hobbies-checkbox-2]").click();
        $("label[for=hobbies-checkbox-3]").click();

        $("#uploadPicture").uploadFromClasspath("ig.png");

        $("#currentAddress").setValue("Test Address 1/2");

        $("#state").scrollIntoView(true).click();
        $("#react-select-3-option-2").click();
        $("#city").click();
        $("#react-select-4-option-0").click();

        $(".text-right").click(); // Отправка формы

        // Проверки
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Evgeniy P"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("test@mail.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9998887766"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("07 July,1991"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths, Physics"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading, Music"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("ig.png"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Test Address 1/2"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Haryana Karnal"));



        }
    }

