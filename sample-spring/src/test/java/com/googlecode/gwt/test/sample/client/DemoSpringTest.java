package com.googlecode.gwt.test.sample.client;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.sample.server.FooBeanFactory;
import com.googlecode.gwt.test.sample.server.FooBeanFactorySimple;
import com.googlecode.gwt.test.spring.GwtSpringTest;
import com.googlecode.gwt.test.spring.GwtTestContextLoader;
import com.googlecode.gwt.test.utils.events.Browser;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static com.googlecode.gwt.test.assertions.GwtAssertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"}, loader = GwtTestContextLoader.class)
@GwtModule("com.googlecode.gwt.test.sample.SpringSample")
public class DemoSpringTest extends GwtSpringTest {

    @Autowired
    private FooBeanFactory fooBeanFactory;

    @Before
    public void beforeDemoSpringTest() {
        // Assert this test has been injected object it needs
        Assertions.assertThat(fooBeanFactory).isInstanceOf(FooBeanFactorySimple.class);
    }

    @Test
    public void click_button() {
        SpringSampleView view = new SpringSampleView();
        view.textBox.setText("Ben Linus");
        view.button.setEnabled(true);
        assertThat(view.label).isNotVisible();


        assertEquals("", view.label.getText());

        // Act
        Browser.click(view.button);

        // Assert
        assertThat(view.label).textEquals("Bean \"OCTO\" has been created");
    }
}
