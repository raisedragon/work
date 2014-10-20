/*     */ package com.winit.svr.label.handler.impl.au.ubi.model;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ 
/*     */ public class NormalizedRequest
/*     */ {
/*     */   private Method method;
/*     */   private String baseAddress;
/*     */   private String path;
/*     */   private Object entity;
/*  27 */   private Map<String, List<String>> parameters = Maps.newLinkedHashMap();
/*     */ 
/*  29 */   private Map<String, List<String>> headers = Maps.newLinkedHashMap();
/*     */ 
/*  31 */   private List<String> acceptTypes = Lists.newArrayList();
/*     */ 
/*  33 */   private List<String> contentTypes = Lists.newArrayList();
/*     */ 
/*     */   public Method getMethod() {
/*  36 */     return this.method;
/*     */   }
/*     */ 
/*     */   public void setMethod(Method method) {
/*  40 */     this.method = method;
/*     */   }
/*     */ 
/*     */   public NormalizedRequest withPath(String path) {
/*  44 */     Preconditions.checkNotNull(path);
/*  45 */     setPath(path);
/*  46 */     return this;
/*     */   }
/*     */ 
/*     */   public String getPath() {
/*  50 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String path) {
/*  54 */     this.path = path;
/*     */   }
/*     */ 
/*     */   public Object getEntity() {
/*  58 */     return this.entity;
/*     */   }
/*     */ 
/*     */   public void setEntity(Object entity) {
/*  62 */     this.entity = entity;
/*     */   }
/*     */ 
/*     */   public NormalizedRequest withParameter(String name, Object value) {
/*  66 */     Preconditions.checkNotNull(name);
/*  67 */     if (value != null) {
/*  68 */       List list = (List)getParameters().get(name);
/*  69 */       if (null == list) {
/*  70 */         list = Lists.newArrayList();
/*  71 */         getParameters().put(name, list);
/*     */       }
/*  73 */       ((List)getParameters().get(name)).add(value.toString());
/*     */     }
/*  75 */     return this;
/*     */   }
/*     */ 
/*     */   public NormalizedRequest withParameters(Map<String, Object> parameters) {
/*  79 */     Preconditions.checkNotNull(parameters);
/*  80 */     for (Map.Entry each : parameters.entrySet()) {
/*  81 */       withParameter((String)each.getKey(), each.getValue());
/*     */     }
/*  83 */     return this;
/*     */   }
/*     */ 
/*     */   public NormalizedRequest withHeader(String name, Object value) {
/*  87 */     Preconditions.checkNotNull(name);
/*  88 */     if (value != null) {
/*  89 */       List list = (List)getHeaders().get(name);
/*  90 */       if (null == list) {
/*  91 */         list = Lists.newArrayList();
/*  92 */         getHeaders().put(name, list);
/*     */       }
/*  94 */       ((List)getHeaders().get(name)).add(value.toString());
/*     */     }
/*  96 */     return this;
/*     */   }
/*     */ 
/*     */   public NormalizedRequest withHeaders(Map<String, Object> headers) {
/* 100 */     Preconditions.checkNotNull(headers);
/* 101 */     for (Map.Entry each : headers.entrySet()) {
/* 102 */       withHeader((String)each.getKey(), each.getValue());
/*     */     }
/* 104 */     return this;
/*     */   }
/*     */ 
/*     */   public NormalizedRequest withMethod(Method method) {
/* 108 */     setMethod(method);
/* 109 */     return this;
/*     */   }
/*     */ 
/*     */   public NormalizedRequest withEntity(Object entity) {
/* 113 */     setEntity(entity);
/* 114 */     return this;
/*     */   }
/*     */ 
/*     */   public NormalizedRequest withContentType(String contentType) {
/* 118 */     this.contentTypes.add(contentType);
/* 119 */     return this;
/*     */   }
/*     */ 
/*     */   public NormalizedRequest withAcceptType(String acceptType) {
/* 123 */     this.acceptTypes.add(acceptType);
/* 124 */     return this;
/*     */   }
/*     */ 
/*     */   public List<String> getContentTypes() {
/* 128 */     return this.contentTypes;
/*     */   }
/*     */ 
/*     */   public void setContentTypes(List<String> contentTypes) {
/* 132 */     this.contentTypes = contentTypes;
/*     */   }
/*     */ 
/*     */   public List<String> getAcceptTypes() {
/* 136 */     return this.acceptTypes;
/*     */   }
/*     */ 
/*     */   public void setAcceptTypes(List<String> acceptTypes) {
/* 140 */     this.acceptTypes = acceptTypes;
/*     */   }
/*     */ 
/*     */   public Map<String, List<String>> getParameters() {
/* 144 */     return this.parameters;
/*     */   }
/*     */ 
/*     */   public void setParameters(Map<String, List<String>> parameters) {
/* 148 */     this.parameters = parameters;
/*     */   }
/*     */ 
/*     */   public Map<String, List<String>> getHeaders() {
/* 152 */     return this.headers;
/*     */   }
/*     */ 
/*     */   public void setHeaders(Map<String, List<String>> headers) {
/* 156 */     this.headers = headers;
/*     */   }
/*     */ 
/*     */   public String getBaseAddress() {
/* 160 */     return this.baseAddress;
/*     */   }
/*     */ 
/*     */   public void setBaseAddress(String baseAddress) {
/* 164 */     this.baseAddress = baseAddress;
/*     */   }
/*     */ }

/* Location:           C:\Users\kaizhou.chen\Desktop\smartparcel-2.0.0.jar
 * Qualified Name:     com.walltech.sdk.core.NormalizedRequest
 * JD-Core Version:    0.6.2
 */