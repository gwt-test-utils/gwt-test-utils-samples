package com.googlecode.gwt.test.sample.server;

import com.googlecode.gwt.test.sample.shared.FooBean;
import org.springframework.stereotype.Service;


@Service
public class FooBeanFactorySimple implements FooBeanFactory {

  public FooBean createFooBean(String name) {
    FooBean fooBean = new FooBean();
    fooBean.setName(name);

    return fooBean;
  }

}
