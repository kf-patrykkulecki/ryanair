package helpers;

import org.openqa.selenium.By;

import java.text.MessageFormat;

public class ElementLocator {

    private ElementType type;
    private String locator;

    public ElementLocator(ElementType type, String locator) {
        this.type = type;
        this.locator = locator;
    }

    public ElementType getType() {
        return type;
    }

    public String getLocator() {
        return locator;
    }

    public By ToBy() {
        By by = null;
        switch (type) {
            case Id:
                by = By.id(locator);
                break;
            case CssSelector:
                by = By.cssSelector(locator);
                break;
            case XPath:
                by = By.xpath(locator);
                break;
        }
        return by;
    }

    public ElementLocator Format(Object param){
        return new ElementLocator(this.type, String.format(this.locator, param));
    }
}
