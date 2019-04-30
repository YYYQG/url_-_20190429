package com.xxx.util;

import java.util.UUID;

public class UuidGenerator implements ShortUrlGenerator {
    @Override
    public String convert(String str) {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13)
                + uuid.substring(14, 18) + uuid.substring(19, 23)
                + uuid.substring(24);
    }
}
