package com.summerdev.travelstoragemanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDateFormat {

    private static final SimpleDateFormat  YEAR_MONTH = new SimpleDateFormat("yyyy-MM");
    private static final SimpleDateFormat  SHORT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    public static String YEAR_MONTH(Date inputDate) { return YEAR_MONTH.format(inputDate); }
    public static String SHORT_DATE(Date inputDate) { return SHORT_DATE.format(inputDate); }
}
