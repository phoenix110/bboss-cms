package com.frameworkset.platform.cms.driver.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 
 * Utilities to get and set formated dates in OpenCms.<p>
 * 
 * @author Michael Emmerich 
 * 
 * @version $Revision: 1.17 $ 
 * 
 * @since 6.0.0 
 */
public final class CmsDateUtil implements java.io.Serializable {

    /** The "GMT" time zone, used when formatting http headers. */
    protected static final TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT");

    /** The default format to use when formatting http headers. */
    protected static final DateFormat HEADER_DEFAULT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

    /** The default format to use when formatting old cookies. */
    protected static final DateFormat OLD_COOKIE = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", Locale.US);

    /**
     * Hides the public constructor.<p>
     */
    private CmsDateUtil() {

        // noop
    }

    /**
     * Returns a formated date String from a Date value,
     * the formatting based on the provided options.<p>
     * 
     * @param date the Date object to format as String
     * @param format the format to use, see {@link DateFormat} for possible values
     * @param locale the locale to use
     * @return the formatted date 
     */
    public static String getDate(Date date, int format, Locale locale) {

        DateFormat df = DateFormat.getDateInstance(format, locale);
        return df.format(date);
    }

//    /**
//     * Returns a formated date String form a timestamp value,
//     * the formatting based on the OpenCms system default locale
//     * and the {@link DateFormat#SHORT} date format.<p>
//     * 
//     * @param time the time value to format as date
//     * @return the formatted date 
//     */
//    public static String getDateShort(long time) {
//
//        return getDate(new Date(time), DateFormat.SHORT, CmsLocaleManager.getDefaultLocale());
//    }

    /**
     * Returns a formated date and time String from a Date value,
     * the formatting based on the provided options.<p>
     * 
     * @param date the Date object to format as String
     * @param format the format to use, see {@link DateFormat} for possible values
     * @param locale the locale to use
     * @return the formatted date 
     */
    public static String getDateTime(Date date, int format, Locale locale) {

        DateFormat df = DateFormat.getDateInstance(format, locale);
        DateFormat tf = DateFormat.getTimeInstance(format, locale);
        StringBuffer buf = new StringBuffer();
        buf.append(df.format(date));
        buf.append(" ");
        buf.append(tf.format(date));
        return buf.toString();
    }

//    /**
//     * Returns a formated date and time String form a timestamp value,
//     * the formatting based on the OpenCms system default locale
//     * and the {@link DateFormat#SHORT} date format.<p>
//     * 
//     * @param time the time value to format as date
//     * @return the formatted date 
//     */
//    public static String getDateTimeShort(long time) {
//
//        return getDateTime(new Date(time), DateFormat.SHORT, CmsLocaleManager.getDefaultLocale());
//    }

    /**
     * Returns the number of days passed since a specific date.<p>
     * 
     * @param dateLastModified the date to compute the passed days from
     *  
     * @return the number of days passed since a specific date
     */
    public static int getDaysPassedSince(Date dateLastModified) {

        GregorianCalendar now = new GregorianCalendar();
        GregorianCalendar lastModified = (GregorianCalendar)now.clone();
        lastModified.setTimeInMillis(dateLastModified.getTime());
        return now.get(Calendar.DAY_OF_YEAR)
            - lastModified.get(Calendar.DAY_OF_YEAR)
            + (now.get(Calendar.YEAR) - lastModified.get(Calendar.YEAR))
            * 365;
    }

    /**
     * Returns a formated date and time String form a timestamp value based on the
     * HTTP-Header date format.<p>
     * 
     * @param time the time value to format as date
     * @return the formatted date 
     */
    public static String getHeaderDate(long time) {

        if (HEADER_DEFAULT.getTimeZone() != GMT_TIMEZONE) {
            // ensure GMT is used as time zone for the header generation
            HEADER_DEFAULT.setTimeZone(GMT_TIMEZONE);
        }

        return HEADER_DEFAULT.format(new Date(time));
    }

    /**
     * Returns a formated date and time String form a timestamp value based on the
     * (old) Netscape cookie date format.<p>
     * 
     * @param time the time value to format as date
     * @return the formatted date 
     */
    public static String getOldCookieDate(long time) {

        if (OLD_COOKIE.getTimeZone() != GMT_TIMEZONE) {
            // ensure GMT is used as time zone for the header generation
            OLD_COOKIE.setTimeZone(GMT_TIMEZONE);
        }

        return OLD_COOKIE.format(new Date(time));
    }

    /**
     * Parses a formated date and time string in HTTP-Header date format and returns the 
     * time value.<p>
     *  
     * @param timestamp the timestamp in HTTP-Header date format
     * @return time value as long
     * @throws ParseException if parsing fails
     */
    public static long parseHeaderDate(String timestamp) throws ParseException {

        return HEADER_DEFAULT.parse(timestamp).getTime();
    }
}