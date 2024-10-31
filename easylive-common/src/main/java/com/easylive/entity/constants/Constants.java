package com.easylive.entity.constants;

public class Constants {

    public static final Integer ONE = 1;

    public static final Integer ZERO = 0;

    public static final Integer LENGTH_10 =10;

    public static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-zA-z])[\\da-zA-Z~!@#$%^&*_]{0,18}$";

    public static final Integer REDIS_KEY_EXPIRES_ONE_MIN = 60000;

    public static final Integer REDIS_KEY_EXPIRES_ONE_DAY = REDIS_KEY_EXPIRES_ONE_MIN * 60 * 24 ;

    public static final Integer TIME_SECONDS_DAY = REDIS_KEY_EXPIRES_ONE_DAY / 1000 ;

    public static final String REDIS_KEY_PREFIX = "easylive:";

    public static String REDIS_KEY_CHECK_CODE = REDIS_KEY_PREFIX + "checkcode";

    public static final String REDIS_KEY_TOKEN_WEB = REDIS_KEY_PREFIX + "token:web:";

    public static final String REDIS_KEY_TOKEN_ADMIN = REDIS_KEY_PREFIX + "token:admin:";

    public static final String TOKEN_WEB = "token";

    public static final String TOKEN_ADMIN = "adminToken";

}
