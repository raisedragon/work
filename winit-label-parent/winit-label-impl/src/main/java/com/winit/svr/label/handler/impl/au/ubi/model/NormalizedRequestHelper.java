/*    */ package com.winit.svr.label.handler.impl.au.ubi.model;
/*    */ 
/*    */ import com.google.common.base.Joiner;
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.common.base.Strings;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class NormalizedRequestHelper
/*    */ {
/*    */   public static final String HEADERS_AUTHORIZATION = "Authorization";
/*    */   public static final String HEADERS_PREFIX = "X-WallTech-";
/*    */   public static final String HEADERS_DATE = "X-WallTech-Date";
/*    */   public static final String HEADERS_AUTHORIZATION_PREFIX = "WallTech ";
/*    */   public static final String HEADERS_AUTHORIZATION_SEPARATOR = ":";
/*    */   private static final char NEW_LINE = '\n';
/*    */   private static final String EMPTY_STRING = "";
/* 32 */   private static final Joiner NEW_LINE_JOINER = Joiner.on('\n').useForNull("");
/*    */ 
/*    */   public static String normalize(NormalizedRequest request) {
/* 35 */     Preconditions.checkNotNull(request);
/* 36 */     List elements = Lists.newArrayList(new String[] { request.getMethod().toString(), date(request) });
/* 37 */     elements.add(request.getBaseAddress() + request.getPath());
/* 38 */     return NEW_LINE_JOINER.join(elements);
/*    */   }
/*    */ 
/*    */   public static String normalize(String method, String date, String url) {
/* 42 */     List elements = Lists.newArrayList(new String[] { method, date, url });
/* 43 */     return NEW_LINE_JOINER.join(elements);
/*    */   }
/*    */ 
/*    */   public static <T> T first(List<T> values) {
/* 47 */     if ((values != null) && (values.size() > 0)) {
/* 48 */       return values.get(0);
/*    */     }
/* 50 */     return null;
/*    */   }
/*    */ 
/*    */   public static <T> T first(Map<String, List<T>> map, String key) {
/* 54 */     List values = (List)map.get(key);
/* 55 */     if ((values != null) && (values.size() > 0)) {
/* 56 */       return (T) values.get(0);
/*    */     }
/* 58 */     return null;
/*    */   }
/*    */ 
/*    */   public static String date(NormalizedRequest request) {
/* 62 */     String date = (String)first(request.getHeaders(), "X-WallTech-Date");
/* 63 */     if (Strings.isNullOrEmpty(date)) {
/* 64 */       throw new IllegalStateException("Missing request timestamp");
/*    */     }
/* 66 */     return date;
/*    */   }
/*    */ }

/* Location:           C:\Users\kaizhou.chen\Desktop\smartparcel-2.0.0.jar
 * Qualified Name:     com.walltech.sdk.core.NormalizedRequestHelper
 * JD-Core Version:    0.6.2
 */