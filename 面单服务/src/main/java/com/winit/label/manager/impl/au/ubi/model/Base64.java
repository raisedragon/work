package com.winit.label.manager.impl.au.ubi.model;

public class Base64
{
  private static final byte equalSign = 61;
  static char[] digits = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_' };

  public static byte[] decode(byte[] data)
  {
    if (data.length == 0)
      return data;
    int lastRealDataIndex = data.length - 1;
    while (data[lastRealDataIndex] == 61) {
      lastRealDataIndex--;
    }
    int padBytes = data.length - 1 - lastRealDataIndex;
    int byteLength = data.length * 6 / 8 - padBytes;
    byte[] result = new byte[byteLength];

    int dataIndex = 0;
    int resultIndex = 0;
    int allBits = 0;

    int resultChunks = (lastRealDataIndex + 1) / 4;
    for (int i = 0; i < resultChunks; i++) {
      allBits = 0;

      for (int j = 0; j < 4; j++) {
        allBits = allBits << 6 | decodeDigit(data[(dataIndex++)]);
      }
      for (int j = resultIndex + 2; j >= resultIndex; j--) {
        result[j] = ((byte)(allBits & 0xFF));
        allBits >>>= 8;
      }
      resultIndex += 3;
    }

    switch (padBytes)
    {
    case 1:
      allBits = 0;

      for (int j = 0; j < 3; j++) {
        allBits = allBits << 6 | decodeDigit(data[(dataIndex++)]);
      }

      allBits <<= 6;

      allBits >>>= 8;

      for (int j = resultIndex + 1; j >= resultIndex; j--) {
        result[j] = ((byte)(allBits & 0xFF));

        allBits >>>= 8;
      }
      break;
    case 2:
      allBits = 0;

      for (int j = 0; j < 2; j++) {
        allBits = allBits << 6 | decodeDigit(data[(dataIndex++)]);
      }

      allBits <<= 6;
      allBits <<= 6;

      allBits >>>= 8;
      allBits >>>= 8;
      result[resultIndex] = ((byte)(allBits & 0xFF));
    }

    return result;
  }

  static int decodeDigit(byte data)
  {
    char charData = (char)data;
    if ((charData <= 'Z') && (charData >= 'A'))
      return charData - 'A';
    if ((charData <= 'z') && (charData >= 'a'))
      return charData - 'a' + 26;
    if ((charData <= '9') && (charData >= '0'))
      return charData - '0' + 52;
    switch (charData) {
    case '+':
    case '-':
      return 62;
    case '/':
    case '_':
      return 63;
    }
    throw new IllegalArgumentException("Invalid char to decode: " + data);
  }

  public static byte[] encode(byte[] data)
  {
    int sourceChunks = data.length / 3;
    int len = (data.length + 2) / 3 * 4;
    byte[] result = new byte[len];
    int extraBytes = data.length - sourceChunks * 3;

    int dataIndex = 0;
    int resultIndex = 0;
    int allBits = 0;
    for (int i = 0; i < sourceChunks; i++) {
      allBits = 0;

      for (int j = 0; j < 3; j++) {
        allBits = allBits << 8 | data[(dataIndex++)] & 0xFF;
      }
      for (int j = resultIndex + 3; j >= resultIndex; j--) {
        result[j] = ((byte)digits[(allBits & 0x3F)]);

        allBits >>>= 6;
      }
      resultIndex += 4;
    }

    switch (extraBytes) {
    case 1:
      allBits = data[(dataIndex++)];
      allBits <<= 8;
      allBits <<= 8;

      for (int j = resultIndex + 3; j >= resultIndex; j--) {
        result[j] = ((byte)digits[(allBits & 0x3F)]);

        allBits >>>= 6;
      }

      result[(result.length - 1)] = 61;
      result[(result.length - 2)] = 61;
      break;
    case 2:
      allBits = data[(dataIndex++)];
      allBits = allBits << 8 | data[(dataIndex++)] & 0xFF;

      allBits <<= 8;

      for (int j = resultIndex + 3; j >= resultIndex; j--) {
        result[j] = ((byte)digits[(allBits & 0x3F)]);

        allBits >>>= 6;
      }

      result[(result.length - 1)] = 61;
    }

    return result;
  }
}