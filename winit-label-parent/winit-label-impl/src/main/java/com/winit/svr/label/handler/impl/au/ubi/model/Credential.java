/*    */ package com.winit.svr.label.handler.impl.au.ubi.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ 
/*    */ @XmlRootElement(name="Credential")
/*    */ public class Credential
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String accessKey;
/*    */   private String secretKey;

/*    */ 
/*    */   public String getAccessKey()
/*    */   {
/* 22 */     return this.accessKey;
/*    */   }
/*    */ 
/*    */   public void setAccessKey(String accessKey) {
/* 26 */     this.accessKey = accessKey;
/*    */   }
/*    */ 
/*    */   public String getSecretKey() {
/* 30 */     return this.secretKey;
/*    */   }
/*    */ 
/*    */   public void setSecretKey(String secretKey) {
/* 34 */     this.secretKey = secretKey;
/*    */   }
/*    */ 

/*    */ }

/* Location:           C:\Users\kaizhou.chen\Desktop\smartparcel-2.0.0.jar
 * Qualified Name:     com.walltech.auth.vo.Credential
 * JD-Core Version:    0.6.2
 */