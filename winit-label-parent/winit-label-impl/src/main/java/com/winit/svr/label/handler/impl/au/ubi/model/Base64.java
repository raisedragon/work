/*     */ package com.winit.svr.label.handler.impl.au.ubi.model;
/*     */ 
/*     */ public class Base64
/*     */ {
/*     */   private static final byte equalSign = 61;
/*   7 */   static char[] digits = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_' };
/*     */ 
/*     */   public static byte[] decode(byte[] data)
/*     */   {
/*  22 */     if (data.length == 0)
/*  23 */       return data;
/*  24 */     int lastRealDataIndex = data.length - 1;
/*  25 */     while (data[lastRealDataIndex] == 61) {
/*  26 */       lastRealDataIndex--;
/*     */     }
/*  28 */     int padBytes = data.length - 1 - lastRealDataIndex;
/*  29 */     int byteLength = data.length * 6 / 8 - padBytes;
/*  30 */     byte[] result = new byte[byteLength];
/*     */ 
/*  32 */     int dataIndex = 0;
/*  33 */     int resultIndex = 0;
/*  34 */     int allBits = 0;
/*     */ 
/*  36 */     int resultChunks = (lastRealDataIndex + 1) / 4;
/*  37 */     for (int i = 0; i < resultChunks; i++) {
/*  38 */       allBits = 0;
/*     */ 
/*  40 */       for (int j = 0; j < 4; j++) {
/*  41 */         allBits = allBits << 6 | decodeDigit(data[(dataIndex++)]);
/*     */       }
/*  43 */       for (int j = resultIndex + 2; j >= resultIndex; j--) {
/*  44 */         result[j] = ((byte)(allBits & 0xFF));
/*  45 */         allBits >>>= 8;
/*     */       }
/*  47 */       resultIndex += 3;
/*     */     }
/*     */ 
/*  51 */     switch (padBytes)
/*     */     {
/*     */     case 1:
/*  56 */       allBits = 0;
/*     */ 
/*  58 */       for (int j = 0; j < 3; j++) {
/*  59 */         allBits = allBits << 6 | decodeDigit(data[(dataIndex++)]);
/*     */       }
/*     */ 
/*  64 */       allBits <<= 6;
/*     */ 
/*  66 */       allBits >>>= 8;
/*     */ 
/*  68 */       for (int j = resultIndex + 1; j >= resultIndex; j--) {
/*  69 */         result[j] = ((byte)(allBits & 0xFF));
/*     */ 
/*  71 */         allBits >>>= 8;
/*     */       }
/*  73 */       break;
/*     */     case 2:
/*  78 */       allBits = 0;
/*     */ 
/*  80 */       for (int j = 0; j < 2; j++) {
/*  81 */         allBits = allBits << 6 | decodeDigit(data[(dataIndex++)]);
/*     */       }
/*     */ 
/*  86 */       allBits <<= 6;
/*  87 */       allBits <<= 6;
/*     */ 
/*  89 */       allBits >>>= 8;
/*  90 */       allBits >>>= 8;
/*  91 */       result[resultIndex] = ((byte)(allBits & 0xFF));
/*     */     }
/*     */ 
/*  96 */     return result;
/*     */   }
/*     */ 
/*     */   static int decodeDigit(byte data)
/*     */   {
/* 107 */     char charData = (char)data;
/* 108 */     if ((charData <= 'Z') && (charData >= 'A'))
/* 109 */       return charData - 'A';
/* 110 */     if ((charData <= 'z') && (charData >= 'a'))
/* 111 */       return charData - 'a' + 26;
/* 112 */     if ((charData <= '9') && (charData >= '0'))
/* 113 */       return charData - '0' + 52;
/* 114 */     switch (charData) {
/*     */     case '+':
/*     */     case '-':
/* 117 */       return 62;
/*     */     case '/':
/*     */     case '_':
/* 120 */       return 63;
/*     */     }
/* 122 */     throw new IllegalArgumentException("Invalid char to decode: " + data);
/*     */   }
/*     */ 
/*     */   public static byte[] encode(byte[] data)
/*     */   {
/* 135 */     int sourceChunks = data.length / 3;
/* 136 */     int len = (data.length + 2) / 3 * 4;
/* 137 */     byte[] result = new byte[len];
/* 138 */     int extraBytes = data.length - sourceChunks * 3;
/*     */ 
/* 140 */     int dataIndex = 0;
/* 141 */     int resultIndex = 0;
/* 142 */     int allBits = 0;
/* 143 */     for (int i = 0; i < sourceChunks; i++) {
/* 144 */       allBits = 0;
/*     */ 
/* 146 */       for (int j = 0; j < 3; j++) {
/* 147 */         allBits = allBits << 8 | data[(dataIndex++)] & 0xFF;
/*     */       }
/* 149 */       for (int j = resultIndex + 3; j >= resultIndex; j--) {
/* 150 */         result[j] = ((byte)digits[(allBits & 0x3F)]);
/*     */ 
/* 153 */         allBits >>>= 6;
/*     */       }
/* 155 */       resultIndex += 4;
/*     */     }
/*     */ 
/* 159 */     switch (extraBytes) {
/*     */     case 1:
/* 161 */       allBits = data[(dataIndex++)];
/* 162 */       allBits <<= 8;
/* 163 */       allBits <<= 8;
/*     */ 
/* 165 */       for (int j = resultIndex + 3; j >= resultIndex; j--) {
/* 166 */         result[j] = ((byte)digits[(allBits & 0x3F)]);
/*     */ 
/* 169 */         allBits >>>= 6;
/*     */       }
/*     */ 
/* 172 */       result[(result.length - 1)] = 61;
/* 173 */       result[(result.length - 2)] = 61;
/* 174 */       break;
/*     */     case 2:
/* 176 */       allBits = data[(dataIndex++)];
/* 177 */       allBits = allBits << 8 | data[(dataIndex++)] & 0xFF;
/*     */ 
/* 179 */       allBits <<= 8;
/*     */ 
/* 181 */       for (int j = resultIndex + 3; j >= resultIndex; j--) {
/* 182 */         result[j] = ((byte)digits[(allBits & 0x3F)]);
/*     */ 
/* 185 */         allBits >>>= 6;
/*     */       }
/*     */ 
/* 188 */       result[(result.length - 1)] = 61;
/*     */     }
/*     */ 
/* 191 */     return result;
/*     */   }
/*     */ }

/* Location:           C:\Users\kaizhou.chen\Desktop\smartparcel-2.0.0.jar
 * Qualified Name:     com.walltech.sdk.core.Base64
 * JD-Core Version:    0.6.2
 */