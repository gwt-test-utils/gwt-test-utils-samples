package com.googlecode.gwt.test.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

public class SampleView extends Composite {

    interface SampleViewUiBinder extends UiBinder<Widget, SampleView> {
    }

    private static SampleViewUiBinder uiBinder = GWT.create(SampleViewUiBinder.class);

    @UiField
    Button button;

    @UiField
    Label label;

    @UiField
    TextBox textBox;

    public SampleView() {
        initWidget(uiBinder.createAndBindUi(this));
        label.setVisible(false);
        button.setEnabled(false);
        // add a debug id
        ensureDebugId("sampleView");
    }

    @Override
    protected void onEnsureDebugId(String baseID) {
        button.ensureDebugId(baseID + "-button");
        label.ensureDebugId(baseID + "-label");
        textBox.ensureDebugId(baseID + "-textBox");
    }

    @UiHandler("button")
    void onClick(ClickEvent e) {
        label.setText("Hello " + textBox.getText());
        label.setVisible(true);
    }

    @UiHandler("textBox")
    void onKeyPress(KeyUpEvent e) {
        button.setEnabled(textBox.getText().trim().length() > 0);
        label.setVisible(false);
    }

}
