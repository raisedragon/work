 package com.winit.label.manager.impl.au.ubi.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class HttpDateTimeFormatter
{
  public static final SimpleDateFormat RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);

  public static Date parseDateTime(String string)
  {
    if (string == null)
      return null;
    try
    {
      return RFC1123.parse(string);
    }
    catch (ParseException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static String formatDateTime(Date dateTime) {
    if (dateTime == null) {
      return null;
    }
    return RFC1123.format(dateTime);
  }

  static
  {
    RFC1123.setTimeZone(TimeZone.getTimeZone("GMT"));
  }
}