 
package  util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

 
@SuppressWarnings("restriction")
public class EncryptPassword {

  public String toEncryptMD5(String password) {
    try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
      digest.update(password.getBytes());
      BASE64Encoder base = new BASE64Encoder();
      return base.encode(digest.digest());
    } catch (NoSuchAlgorithmException ns) {
      ns.printStackTrace();
      return password;
    }
  }
}
