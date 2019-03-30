package com.marekdudek.trading_tools;

public enum FunctionalUtils
{
    ;

    public static <T> T error(final Throwable e)
    {
        throw new RuntimeException(e);
    }
}
