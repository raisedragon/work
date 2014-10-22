 package com.winit.label.manager.impl.au.ubi.model;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;

public class NormalizedRequestHelper
{
  public static final String HEADERS_AUTHORIZATION = "Authorization";
  public static final String HEADERS_PREFIX = "X-WallTech-";
  public static final String HEADERS_DATE = "X-WallTech-Date";
  public static final String HEADERS_AUTHORIZATION_PREFIX = "WallTech ";
  public static final String HEADERS_AUTHORIZATION_SEPARATOR = ":";
  private static final char NEW_LINE = '\n';
  private static final String EMPTY_STRING = "";
  private static final Joiner NEW_LINE_JOINER = Joiner.on('\n').useForNull("");

  public static String normalize(NormalizedRequest request) {
    Preconditions.checkNotNull(request);
    List elements = Lists.newArrayList(new String[] { request.getMethod().toString(), date(request) });
    elements.add(request.getBaseAddress() + request.getPath());
    return NEW_LINE_JOINER.join(elements);
  }

  public static String normalize(String method, String date, String url) {
    List elements = Lists.newArrayList(new String[] { method, date, url });
    return NEW_LINE_JOINER.join(elements);
  }

  public static <T> T first(List<T> values) {
    if ((values != null) && (values.size() > 0)) {
      return values.get(0);
    }
    return null;
  }

  public static <T> T first(Map<String, List<T>> map, String key) {
    List values = (List)map.get(key);
    if ((values != null) && (values.size() > 0)) {
      return (T) values.get(0);
    }
    return null;
  }

  public static String date(NormalizedRequest request) {
    String date = (String)first(request.getHeaders(), "X-WallTech-Date");
    if (Strings.isNullOrEmpty(date)) {
      throw new IllegalStateException("Missing request timestamp");
    }
    return date;
  }
}