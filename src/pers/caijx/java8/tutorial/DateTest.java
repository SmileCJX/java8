package pers.caijx.java8.tutorial;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/8/21/021.
 */
public class DateTest {

    public static void main(String[] args){
//        clockTest();

//        TimezonesTest();

//        localTimeTest1();

//        localTimeTest2();

//        LocalDateTest1();

//        LocalDateTest2();

//        LocalDateTimeTest1();

        LocalDateTimeTest2();
    }

    //    Clock提供了对当前时间和日期的访问功能。Clock是对当前时区敏感的，并可用于
//    替代System.currentTimeMillis()方法来获取当前的毫秒时间。当前时间线上的时刻
//    可以用Instance类来表示。Instance也能够用于创建原先的java.util.Date对象。
    private static void clockTest() {
        Clock clock = Clock.systemDefaultZone();
        long miles = clock.millis();
        System.out.println(miles);

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);
    }

//    时区类可以用一个ZoneId来表示。时区类的对象可以通过静态工厂方法方便地获
//    取。时区类还定义了一个偏移量，用来在当前时刻或某时间与目标时区时间之间进
//    行转换。
    private static void TimezonesTest() {
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
    }

//    时区类可以用一个ZoneId来表示。时区类的对象可以通过静态工厂方法方便地获
//    取。时区类还定义了一个偏移量，用来在当前时刻或某时间与目标时区时间之间进
//    行转换。
    private static void localTimeTest1() {
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1.isBefore(now2));  //false

        long hoursBetween = ChronoUnit.HOURS.between(now1,now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1,now2);

        System.out.println(hoursBetween);
        System.out.println(minutesBetween);
    }

//    LocalTime是由多个工厂方法组成，其目的是为了简化对时间对象实例的创建和操
//    作，包括对时间字符串进行解析的操作。
    private static void localTimeTest2() {
        LocalTime late = LocalTime.of(23,59,59);
        System.out.println(late);

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37",germanFormatter);
        System.out.println(leetTime);
    }

//    本地时间表示了一个独一无二的时间，例如：2014-03-11。这个时间是不可变的，
//    与LocalTime是同源的。下面的例子演示了如何通过加减日，月，年等指标来计算
//    新的日期。记住，每一次操作都会返回一个新的时间对象。
    private static void LocalDateTest1() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println(today);
        System.out.println(tomorrow);
        System.out.println(yesterday);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY,4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);
    }

    //    解析字符串并形成LocalDate对象
    private static void LocalDateTest2() {
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);
        LocalDate xmas = LocalDate.parse("24.12.2014",germanFormatter);
        System.out.println(xmas);
    }

//    LocalDateTime表示的是日期-时间。它将刚才介绍的日期对象和时间对象结合起
//    来，形成了一个对象实例。LocalDateTime是不可变的，与LocalTime和LocalDate
//    的工作原理相同。我们可以通过调用方法来获取日期时间对象中特定的数据域
    private static void LocalDateTimeTest1() {
        LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER,31,23,59,59);

        DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
        System.out.println(dayOfWeek);

        Month month = sylvester.getMonth();
        System.out.println(month);

        long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(minuteOfDay);

        Instant instant = sylvester
                .atZone(ZoneId.systemDefault())
                .toInstant();
        Date legacyDate = Date.from(instant);
        System.out.println(legacyDate);
    }

//    格式化日期-时间对象就和格式化日期对象或者时间对象一样。除了使用预定义的格
//    式以外，我们还可以创建自定义的格式化对象，然后匹配我们自定义的格式。
    private static void LocalDateTimeTest2() {
        // TODO: 2017/8/21/021  有错
        DateTimeFormatter formatter =
                DateTimeFormatter
                        .ofPattern("MMM dd, yyyy - HH:mm");

        LocalDateTime parsed = LocalDateTime.parse("Nov 03,2014 - 07:13",formatter);
        String string = formatter.format(parsed);
        System.out.println(string);
    }

}
