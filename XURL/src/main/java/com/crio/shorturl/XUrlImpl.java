package com.crio.shorturl;

import java.util.*;
import java.util.Map.Entry;

public class XUrlImpl implements XUrl{
    HashMap<String, String> url = new HashMap<String, String>();
    HashMap<String, Integer> urlcount = new HashMap<String, Integer>();
    static final String c = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String registerNewUrl(String longUrl){
        if (url.containsKey(longUrl))
          return url.get(longUrl);
        
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append("http://short.url/");
        for (int i = 0; i < 9; i++)
            sb.append(c.charAt(rnd.nextInt(c.length())));
           
        url.put(longUrl, sb.toString());
        return sb.toString();
    }

    public String registerNewUrl(String longUrl, String shortUrl){
        if (url.containsValue(shortUrl))
            return null;

        url.put(longUrl, shortUrl);
        return shortUrl; 
    }

    public String getUrl(String shortUrl){
        if(url.containsValue(shortUrl))
        {
            for( Entry<String, String> entry : url.entrySet()){
                if(entry.getValue().equals(shortUrl))
                {
                int count =(urlcount.get(entry.getKey())== null) ? 0 : urlcount.get(entry.getKey());
                urlcount.put(entry.getKey(), count + 1);
                return entry.getKey();
                }
            }
        }
        return null;
    }

    public String delete(String longUrl){
        return url.remove(longUrl);
    }

    public Integer getHitCount(String longUrl){
        return (urlcount.get(longUrl)==null? 0: urlcount.get(longUrl));
    }
}