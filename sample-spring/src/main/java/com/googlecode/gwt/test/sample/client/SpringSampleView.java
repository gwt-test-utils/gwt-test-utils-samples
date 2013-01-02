package com.googlecode.gwt.test.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.test.sample.shared.FooBean;

public class SpringSampleView extends Composite {

    interface SpringSampleViewUiBinder extends UiBinder<Widget, SpringSampleView> {
    }
    private static SpringSampleViewUiBinder uiBinder = GWT.create(SpringSampleViewUiBinder.class);
    @UiField
    Button button;
    @UiField
    Label label;
    @UiField
    TextBox textBox;
//   private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    private MyServiceAsync service = GWT.create(MyService.class);

    public SpringSampleView() {
        initWidget(uiBinder.createAndBindUi(this));
        label.setVisible(false);
        button.setEnabled(false);
    }

    @UiHandler("button")
    void onClick(ClickEvent e) {
        label.setVisible(true);
        
        AsyncCallback<FooBean> callback = new AsyncCallback<FooBean>() {
            public void onFailure(Throwable caught) {
                // Show the RPC error message to the user
                label.setText("Failure : " + caught.getMessage());
            }

            public void onSuccess(FooBean result) {
                label.setText("Bean \"" + result.getName() + "\" has been created");
            }
        };

        // Make the call. Control flow will continue immediately and later
        // 'callback' will be invoked when the RPC completes.
        service.createBean("OCTO", callback);
    }

    @UiHandler("textBox")
    void onKeyPress(KeyUpEvent e) {
        button.setEnabled(textBox.getText().trim().length() > 0);
        label.setVisible(false);
    }
}
