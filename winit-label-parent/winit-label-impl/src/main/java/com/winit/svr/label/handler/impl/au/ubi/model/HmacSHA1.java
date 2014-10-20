/*    */ package com.winit.svr.label.handler.impl.au.ubi.model;
/*    */ 
/*    */ import com.google.common.base.Charsets;
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.security.InvalidKeyException;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import javax.crypto.Mac;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ 
/*    */ public class HmacSHA1
/*    */ {
/*    */   private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
/*    */ 
/*    */   public static String calculate(String secretAccessKey, String data)
/*    */   {
/* 25 */     Preconditions.checkNotNull(secretAccessKey);
/* 26 */     Preconditions.checkNotNull(data);
/*    */     try {
/* 28 */       SecretKeySpec signingKey = new SecretKeySpec(secretAccessKey.getBytes(Charsets.UTF_8), "HmacSHA1");
/*    */ 
/* 30 */       Mac mac = Mac.getInstance("HmacSHA1");
/* 31 */       mac.init(signingKey);
/*    */ 
/* 33 */       byte[] rawHmac = mac.doFinal(data.getBytes(Charsets.UTF_8));
/* 34 */       return new String(Base64.encode(rawHmac), Charsets.UTF_8);
/*    */     }
/*    */     catch (NoSuchAlgorithmException e) {
/* 37 */       throw new IllegalStateException(e);
/*    */     }
/*    */     catch (InvalidKeyException e) {
/* 40 */       throw new IllegalStateException(e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\kaizhou.chen\Desktop\smartparcel-2.0.0.jar
 * Qualified Name:     com.walltech.sdk.core.HmacSHA1
 * JD-Core Version:    0.6.2
 */