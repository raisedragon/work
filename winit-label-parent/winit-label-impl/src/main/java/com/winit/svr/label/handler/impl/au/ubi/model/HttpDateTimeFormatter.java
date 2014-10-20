/*    */ package com.winit.svr.label.handler.impl.au.ubi.model;
/*    */ 
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Locale;
/*    */ import java.util.TimeZone;
/*    */ 
/*    */ public class HttpDateTimeFormatter
/*    */ {
/* 16 */   public static final SimpleDateFormat RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
/*    */ 
/*    */   public static Date parseDateTime(String string)
/*    */   {
/* 26 */     if (string == null)
/* 27 */       return null;
/*    */     try
/*    */     {
/* 30 */       return RFC1123.parse(string);
/*    */     }
/*    */     catch (ParseException e) {
/* 33 */       throw new IllegalArgumentException(e);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String formatDateTime(Date dateTime) {
/* 38 */     if (dateTime == null) {
/* 39 */       return null;
/*    */     }
/* 41 */     return RFC1123.format(dateTime);
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 19 */     RFC1123.setTimeZone(TimeZone.getTimeZone("GMT"));
/*    */   }
/*    */ }

/* Location:           C:\Users\kaizhou.chen\Desktop\smartparcel-2.0.0.jar
 * Qualified Name:     com.walltech.sdk.core.HttpDateTimeFormatter
 * JD-Core Version:    0.6.2
 */