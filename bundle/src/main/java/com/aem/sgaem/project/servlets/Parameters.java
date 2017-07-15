package com.aem.sgaem.project.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.sling.api.request.RequestParameter;


final class Parameters implements RequestParameter {

  private final String parameter;

  public Parameters(String parameter) {
    this.parameter = parameter;
  }

  public boolean isFormField() {
    return true;
  }

  public String getContentType() {
    return null;
  }

  public long getSize() {
    return parameter.length();
  }

  public byte[] get() {
    return parameter.getBytes();
  }

  public InputStream getInputStream() throws IOException {
    return null;
  }

  public String getFileName() {
    return null;
  }

  public String getName() {
    return getString();
  }

  public String getString() {
    return parameter;
  }

  public String getString(String s) throws UnsupportedEncodingException {
    return new String(parameter.getBytes(s));
  }
}
