package ru.saraev.pages.base_page;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.PageObjectFactory;
import com.codeborne.selenide.impl.Plugins;

public abstract class BasePage {

    public BasePage() {
        PageObjectFactory pageObjectFactory = Plugins.inject(PageObjectFactory.class);
        pageObjectFactory.page(WebDriverRunner.driver(), this);
    }
}
