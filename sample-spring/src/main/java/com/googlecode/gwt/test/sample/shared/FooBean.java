package com.googlecode.gwt.test.sample.shared;

import java.io.Serializable;

public class FooBean implements Serializable {

  private static final long serialVersionUID = 1515974853779549420L;

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
