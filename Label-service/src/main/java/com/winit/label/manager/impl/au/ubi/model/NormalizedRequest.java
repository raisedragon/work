 package com.winit.label.manager.impl.au.ubi.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NormalizedRequest
{
  private Method method;
  private String baseAddress;
  private String path;
  private Object entity;
  private Map<String, List<String>> parameters = Maps.newLinkedHashMap();

  private Map<String, List<String>> headers = Maps.newLinkedHashMap();

  private List<String> acceptTypes = Lists.newArrayList();

  private List<String> contentTypes = Lists.newArrayList();

  public Method getMethod() {
    return this.method;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public NormalizedRequest withPath(String path) {
    Preconditions.checkNotNull(path);
    setPath(path);
    return this;
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Object getEntity() {
    return this.entity;
  }

  public void setEntity(Object entity) {
    this.entity = entity;
  }

  public NormalizedRequest withParameter(String name, Object value) {
    Preconditions.checkNotNull(name);
    if (value != null) {
      List list = (List)getParameters().get(name);
      if (null == list) {
        list = Lists.newArrayList();
        getParameters().put(name, list);
      }
      ((List)getParameters().get(name)).add(value.toString());
    }
    return this;
  }

  public NormalizedRequest withParameters(Map<String, Object> parameters) {
    Preconditions.checkNotNull(parameters);
    for (Map.Entry each : parameters.entrySet()) {
      withParameter((String)each.getKey(), each.getValue());
    }
    return this;
  }

  public NormalizedRequest withHeader(String name, Object value) {
    Preconditions.checkNotNull(name);
    if (value != null) {
      List list = (List)getHeaders().get(name);
      if (null == list) {
        list = Lists.newArrayList();
        getHeaders().put(name, list);
      }
      ((List)getHeaders().get(name)).add(value.toString());
    }
    return this;
  }

  public NormalizedRequest withHeaders(Map<String, Object> headers) {
    Preconditions.checkNotNull(headers);
    for (Map.Entry each : headers.entrySet()) {
      withHeader((String)each.getKey(), each.getValue());
    }
    return this;
  }

  public NormalizedRequest withMethod(Method method) {
    setMethod(method);
    return this;
  }

  public NormalizedRequest withEntity(Object entity) {
    setEntity(entity);
    return this;
  }

  public NormalizedRequest withContentType(String contentType) {
    this.contentTypes.add(contentType);
    return this;
  }

  public NormalizedRequest withAcceptType(String acceptType) {
    this.acceptTypes.add(acceptType);
    return this;
  }

  public List<String> getContentTypes() {
    return this.contentTypes;
  }

  public void setContentTypes(List<String> contentTypes) {
    this.contentTypes = contentTypes;
  }

  public List<String> getAcceptTypes() {
    return this.acceptTypes;
  }

  public void setAcceptTypes(List<String> acceptTypes) {
    this.acceptTypes = acceptTypes;
  }

  public Map<String, List<String>> getParameters() {
    return this.parameters;
  }

  public void setParameters(Map<String, List<String>> parameters) {
    this.parameters = parameters;
  }

  public Map<String, List<String>> getHeaders() {
    return this.headers;
  }

  public void setHeaders(Map<String, List<String>> headers) {
    this.headers = headers;
  }

  public String getBaseAddress() {
    return this.baseAddress;
  }

  public void setBaseAddress(String baseAddress) {
    this.baseAddress = baseAddress;
  }
}