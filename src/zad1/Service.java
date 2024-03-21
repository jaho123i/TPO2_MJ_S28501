/**
 *
 *  @author Matłosz Jan S28501
 *
 */

package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Service {
    private static Map<String, Locale> map = new HashMap<>();
    String currency;
    String isoCode;

    URL openWeatherLink;
    String country;
    String key;


    public Service(String country) {
        try {
            openWeatherLink = new URL("https://api.openweathermap.org/data/3.0/onecall?lat=33.44&lon=-94.04&appid=8ea227a52fe7f73c97008e283e5a12e4");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        for (Locale locale : Locale.getAvailableLocales()) {
            map.put(locale.getDisplayCountry(), locale);
        }
        this.country = country;

        key = "2867373a2d746742247a6ad1755c8376";
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }
        isoCode = String.valueOf(map.get(country));
        System.out.println(isoCode);

        try {
            currency = Currency.getInstance(new Locale("", isoCode)).getCurrencyCode();
    } catch (Exception e) {
            System.err.println("ZŁE PAŃSTWO!");
        }
    }
    public String getWeather(String warsaw) {
        return null;
    }
    public Double getRateFor(String usd) {
        return null;
    }
    public Double getNBPRate() {
        System.out.println(Currency.getInstance(map.get(country)).getCurrencyCode());
        try
        {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/b/" + Currency.getInstance(map.get(country)).getCurrencyCode().toLowerCase() + "/");
            System.out.println(readURL(url));
        }
        catch (MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static String readURL(URL url)
    {
        String substring = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            String line;
            while((line = in.readLine()) != null)
                substring += line;
        }
        catch (IOException exception)
        {
            System.out.println(exception);
        }
        return substring;
    }
}
