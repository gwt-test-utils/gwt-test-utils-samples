package com.googlecode.gwt.test.sample.server;

import com.googlecode.gwt.test.sample.client.MyService;
import com.googlecode.gwt.test.sample.shared.FooBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyServiceImpl implements MyService {

  private static final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);

  @Autowired
  private FooBeanFactory fooBeanFactory;

  public FooBean createBean(String name) {
    FooBean fb = fooBeanFactory.createFooBean(name);

    logger.info(FooBean.class.getSimpleName() + " instance create with '"
        + name + "'");

    return fb;
  }

}
