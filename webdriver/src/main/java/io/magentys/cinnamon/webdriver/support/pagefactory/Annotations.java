package io.magentys.cinnamon.webdriver.support.pagefactory;

import io.magentys.cinnamon.webdriver.ByKey;
import io.magentys.cinnamon.webdriver.Timeout;
import io.magentys.cinnamon.webdriver.conditions.Condition;
import io.magentys.cinnamon.webdriver.support.FindByKey;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.ByAll;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static io.magentys.cinnamon.webdriver.Timeouts.defaultTimeout;
import static io.magentys.cinnamon.webdriver.conditions.ElementConditions.present;

public class Annotations extends AbstractAnnotations {
    
    private final Field field;

    public Annotations(final Field field) {
        this.field = field;
    }

    public boolean isLookupCached() {
        return (field.getAnnotation(CacheLookup.class) != null);
    }

    public By buildBy() {
        By ans = null;

        FindAll findAll = field.getAnnotation(FindAll.class);
        if (findAll != null) {
            ans = buildBysFromFindByOneOf(findAll);
        }

        FindBy findBy = field.getAnnotation(FindBy.class);
        if (ans == null && findBy != null) {
            ans = buildByFromFindBy(findBy);
        }

        FindByKey findByKey = field.getAnnotation(FindByKey.class);
        if (ans == null && findByKey != null) {
            ans = buildByFromFindByKey(findByKey);
        }

        if (ans == null) {
            ans = buildByFromDefault();
        }

        if (ans == null) {
            throw new IllegalArgumentException("Cannot determine how to locate element " + field);
        }

        return ans;
    }

    // TODO Read condition annotations applied to field.
    protected Condition<WebElement> buildCondition() {
        return present;
    }

    // TODO Add timeout value to find annotation.
    protected Timeout buildTimeout() {
        return defaultTimeout();
    }

    protected By buildByFromDefault() {
        return new ByIdOrName(field.getName());
    }

    protected By buildByFromFindByKey(FindByKey findByKey) {
        return ByKey.locatorKey(findByKey.value());
    }

    protected By buildBysFromFindByOneOf(FindAll findBys) {
        this.assertValidFindAll(findBys);
        FindBy[] findByArray = findBys.value();
        By[] byArray = new By[findByArray.length];

        for(int i = 0; i < findByArray.length; ++i) {
            byArray[i] = this.buildByFromFindBy(findByArray[i]);
        }

        return new ByAll(byArray);
    }

    protected By buildByFromFindBy(FindBy findBy) {
        this.assertValidFindBy(findBy);
        By ans = this.buildByFromShortFindBy(findBy);
        if (ans == null) {
            ans = this.buildByFromLongFindBy(findBy);
        }

        return ans;
    }

    private void assertValidFindAll(FindAll findBys) {
        FindBy[] var2 = findBys.value();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            FindBy findBy = var2[var4];
            this.assertValidFindBy(findBy);
        }

    }

    private void assertValidFindBy(FindBy findBy) {
        if (findBy.how() != null && findBy.using() == null) {
            throw new IllegalArgumentException("If you set the 'how' property, you must also set 'using'");
        } else {
            Set<String> finders = new HashSet();
            if (!"".equals(findBy.using())) {
                finders.add("how: " + findBy.using());
            }

            if (!"".equals(findBy.className())) {
                finders.add("class name:" + findBy.className());
            }

            if (!"".equals(findBy.css())) {
                finders.add("css:" + findBy.css());
            }

            if (!"".equals(findBy.id())) {
                finders.add("id: " + findBy.id());
            }

            if (!"".equals(findBy.linkText())) {
                finders.add("link text: " + findBy.linkText());
            }

            if (!"".equals(findBy.name())) {
                finders.add("name: " + findBy.name());
            }

            if (!"".equals(findBy.partialLinkText())) {
                finders.add("partial link text: " + findBy.partialLinkText());
            }

            if (!"".equals(findBy.tagName())) {
                finders.add("tag name: " + findBy.tagName());
            }

            if (!"".equals(findBy.xpath())) {
                finders.add("xpath: " + findBy.xpath());
            }

            if (finders.size() > 1) {
                throw new IllegalArgumentException(String.format("You must specify at most one location strategy. Number found: %d (%s)", finders.size(), finders.toString()));
            }
        }
    }

    protected By buildByFromLongFindBy(FindBy findBy) {
        How how = findBy.how();
        String using = findBy.using();
        switch(how) {
            case CLASS_NAME:
                return By.className(using);
            case CSS:
                return By.cssSelector(using);
            case ID:
            case UNSET:
                return By.id(using);
            case ID_OR_NAME:
                return new ByIdOrName(using);
            case LINK_TEXT:
                return By.linkText(using);
            case NAME:
                return By.name(using);
            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(using);
            case TAG_NAME:
                return By.tagName(using);
            case XPATH:
                return By.xpath(using);
            default:
                throw new IllegalArgumentException("Cannot determine how to locate element ");
        }
    }

    protected By buildByFromShortFindBy(FindBy findBy) {
        if (!"".equals(findBy.className())) {
            return By.className(findBy.className());
        } else if (!"".equals(findBy.css())) {
            return By.cssSelector(findBy.css());
        } else if (!"".equals(findBy.id())) {
            return By.id(findBy.id());
        } else if (!"".equals(findBy.linkText())) {
            return By.linkText(findBy.linkText());
        } else if (!"".equals(findBy.name())) {
            return By.name(findBy.name());
        } else if (!"".equals(findBy.partialLinkText())) {
            return By.partialLinkText(findBy.partialLinkText());
        } else if (!"".equals(findBy.tagName())) {
            return By.tagName(findBy.tagName());
        } else {
            return !"".equals(findBy.xpath()) ? By.xpath(findBy.xpath()) : null;
        }
    }

    protected Field getField() {
        return field;
    }
}
