package com.googlecode.gwt.test.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

public class MockSampleView extends Composite {

    interface MockSampleViewUiBinder extends UiBinder<Widget, MockSampleView> {
    }

    private static MockSampleViewUiBinder uiBinder = GWT.create(MockSampleViewUiBinder.class);

    @UiField
    Button button;

    @UiField
    Label label;

    @UiField
    TextBox textBox;

    private final ClientGreeting clientGreeting;

    public MockSampleView(ClientGreeting clientGreeting) {
        this.clientGreeting = clientGreeting;
        initWidget(uiBinder.createAndBindUi(this));
        label.setVisible(false);
        button.setEnabled(false);
    }

    @UiHandler("button")
    void onClick(ClickEvent e) {
        label.setText(clientGreeting.greet(textBox.getText()));
        label.setVisible(true);
    }

    @UiHandler("textBox")
    void onKeyPress(KeyUpEvent e) {
        button.setEnabled(textBox.getText().trim().length() > 0);
        label.setVisible(false);
    }

}
