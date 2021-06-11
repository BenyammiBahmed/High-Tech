package com.example.server.Services;

import org.bouncycastle.crypto.digests.MD4Digest;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {
public String Crype(String s){
    MD4Digest d = new MD4Digest();
    d.update (s.getBytes(), 0, s.getBytes().length);
    byte[] o = new byte[d.getDigestSize()];
    d.doFinal (o, 0);
     s = new String(o);
   return s;
}

}
