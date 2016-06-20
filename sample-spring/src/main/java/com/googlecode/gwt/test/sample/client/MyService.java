package com.googlecode.gwt.test.sample.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.googlecode.gwt.test.sample.shared.FooBean;

@RemoteServiceRelativePath("rpc/myService")
public interface MyService extends RemoteService {

    public FooBean createBean(String name);

}
