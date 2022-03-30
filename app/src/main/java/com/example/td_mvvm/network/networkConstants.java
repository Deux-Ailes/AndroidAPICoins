package com.example.td_mvvm.network;

public class networkConstants {
    public static final String BASE_URL =
            "https://coinranking1.p.rapidapi.com";
    public static final String OPTIONS =
            "/coins?orderBy=marketCap&orderDirection=desc&limit=10";

    public static final String HOST_HEADER_NAME =
            "x-rapidapi-host";
    public static final String HOST_HEADER_VALUE =
            "coinranking1.p.rapidapi.com";
    public static final String KEY_HEADER_NAME =
            "x-rapidapi-key";
    public static final String KEY_HEADER_VALUE =
            "c943e0f237msh7c19578a3d59636p1131e8jsnc67ebeb4fa26";
}
