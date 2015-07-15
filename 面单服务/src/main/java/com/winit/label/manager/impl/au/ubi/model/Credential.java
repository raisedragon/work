package com.winit.label.manager.impl.au.ubi.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Credential")
public class Credential
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String accessKey;
  private String secretKey;


  public String getAccessKey()
  {
    return this.accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSecretKey() {
    return this.secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }


}