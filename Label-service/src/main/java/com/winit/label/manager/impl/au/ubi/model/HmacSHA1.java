 package com.winit.label.manager.impl.au.ubi.model;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSHA1
{
  private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

  public static String calculate(String secretAccessKey, String data)
  {
    Preconditions.checkNotNull(secretAccessKey);
    Preconditions.checkNotNull(data);
    try {
      SecretKeySpec signingKey = new SecretKeySpec(secretAccessKey.getBytes(Charsets.UTF_8), "HmacSHA1");

      Mac mac = Mac.getInstance("HmacSHA1");
      mac.init(signingKey);

      byte[] rawHmac = mac.doFinal(data.getBytes(Charsets.UTF_8));
      return new String(Base64.encode(rawHmac), Charsets.UTF_8);
    }
    catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    }
    catch (InvalidKeyException e) {
      throw new IllegalStateException(e);
    }
  }
}