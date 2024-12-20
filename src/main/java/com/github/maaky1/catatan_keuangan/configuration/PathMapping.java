package com.github.maaky1.catatan_keuangan.configuration;

public class PathMapping {

    public static final String BASE_URL_APP = "/api";

    public static final String BASE_URL_CATEGORY = "/category";
    public static final String URL_CREATE_CATEGORY = "/create";
    public static final String URL_GET_ALL_CATEGORY = "/get-all";
    public static final String URL_GET_BY_ID_CATEGORY = "/get-by-id/{id}";
    public static final String URL_DELETE_BY_ID_CATEGORY = "/delete-by-id/{id}";

    public static final String BASE_URL_TRANSACTION = "/transaction";
    public static final String URL_CREATE_TRANSACTION = "/create";
    public static final String URL_GET_ALL_TRANSACTION = "/get-all";
    public static final String URL_GET_BY_ID_TRANSACTION = "/get-by-id/{id}";
    public static final String URL_DELETE_BY_ID_TRANSACTION = "/delete-by-id/{id}";
    public static final String URL_UPDATE_TRANSACTION = "/update-by-id/{id}";
    public static final String URL_FILTER_BY_TYPE_TRANSACTION = "/filter";
    public static final String URL_FILTER_BY_RANGE_TRANSACTION = "/data-range";
}
