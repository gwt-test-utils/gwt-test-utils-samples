package com.googlecode.gwt.test.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class RpcSampleView extends Composite {

    interface RpcSampleViewUiBinder extends UiBinder<Widget, RpcSampleView> {
    }

    private static RpcSampleViewUiBinder uiBinder = GWT.create(RpcSampleViewUiBinder.class);

    @UiField
    Button button;

    @UiField
    Label label;

    @UiField
    TextBox textBox;

    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    public RpcSampleView() {
        initWidget(uiBinder.createAndBindUi(this));
        label.setVisible(false);
        button.setEnabled(false);
        // add a debug id
        ensureDebugId("rpcSampleView");
    }

    @Override
    protected void onEnsureDebugId(String baseID) {
        button.ensureDebugId(baseID + "-button");
        label.ensureDebugId(baseID + "-label");
        textBox.ensureDebugId(baseID + "-textBox");
    }

    @UiHandler("button")
    void onClick(ClickEvent e) {
        greetingService.greetServer(textBox.getText(), new AsyncCallback<String>() {

            public void onFailure(Throwable caught) {
                label.setText("Server error: " + caught.getMessage());
                label.setVisible(true);
            }

            public void onSuccess(String result) {
                label.setText(result);
                label.setVisible(true);
            }
        });
    }

    @UiHandler("textBox")
    void onKeyPress(KeyUpEvent e) {
        button.setEnabled(textBox.getText().trim().length() > 0);
        label.setVisible(false);
    }

}
