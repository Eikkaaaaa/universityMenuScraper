package com.eikka.universityMenuScraper.helpers.unica;

import com.eikka.universityMenuScraper.components.Prices;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class UnicaExtractor {

    public static String extractOpeningHours(Elements element) {
        Element openingDate = element.selectFirst("h4");

        assert openingDate != null;
        String[] day = openingDate.text().split(" ");
        String today = day[0];

        Element openingHours = element.selectFirst("p");
        if (openingHours == null) {
            return null;
        }
        return openingHours.text().replace("Lunch served " , today + ": ").replace("–", "-").trim();
    }

    public static Set<String> extractAllergens(Element element){

        String allergenString = element.select("div[class=\"meal-item--name-container\"] > p").html();
        List<String> allergenList = Arrays.asList(allergenString.split(","));

        allergenList.replaceAll(s -> allergenMatcher(s.trim()));

        return new HashSet<>(allergenList);
    }

    private static String allergenMatcher(String singleAllergen){

        return switch (singleAllergen) {
            case "G" -> "Gluten-free";
            case "L" -> "Lactose-free";
            case "VL" -> "Low lactose";
            case "M" -> "Dairy-free";
            case "Veg" -> "Suitable for vegans";
            case "VS" -> "Contains fresh garlic";
            case "A" -> "Contains allergens";
            default -> "";
        };
    }

    public static String extractMealName(Element meal, String station) {
        StringBuilder sb = new StringBuilder();
        String singleMeal = Objects.requireNonNull(meal.select("span").first()).text();
        sb.append(singleMeal).append(" [").append(station).append("]");
        return sb.toString();
    }

    public static Prices extractPrices(String priceString){

        Pattern checkForNumbers = Pattern.compile("^(?:-[1-9](?:\\d{0,2}(?:,\\d{3})+|\\d*)|(?:0|[1-9](?:\\d{0,2}(?:,\\d{3})+|\\d*)))(?:.\\d+|)$");

        Prices prices = new Prices();
        ArrayList<String> parsedPrices = new ArrayList<>(List.of(priceString.split("/")));

        parsedPrices.removeIf(priceElement -> priceElement.contains("g"));
        parsedPrices.replaceAll(s -> s.replace(",", "."));
        parsedPrices.replaceAll(s -> s.replace("€", ""));
        parsedPrices.replaceAll(s -> s.replace(" ", ""));

        boolean isNumber =  false;
        try {
            isNumber = checkForNumbers.matcher(parsedPrices.getFirst()).matches();
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            Logger.getLogger(UnicaExtractor.class.getName()).log(Level.WARNING, e.getMessage());
        }

        if(isNumber){
            switch (parsedPrices.size()) {
                case 1:
                    prices.setStudents(Float.parseFloat(parsedPrices.getFirst()));
                    prices.setResearcherStudents(Float.parseFloat(parsedPrices.getFirst()));
                    prices.setStaff(Float.parseFloat(parsedPrices.getFirst()));
                    prices.setOthers(Float.parseFloat(parsedPrices.getFirst()));
                    break;
                case 2:
                    prices.setStudents(Float.parseFloat(parsedPrices.getFirst()));
                    prices.setResearcherStudents(Float.parseFloat(parsedPrices.get(1)));
                    prices.setStaff(Float.parseFloat(parsedPrices.get(1)));
                    prices.setOthers(Float.parseFloat(parsedPrices.get(1)));
                    break;
                case 3:
                    prices.setStudents(Float.parseFloat(parsedPrices.getFirst()));
                    prices.setResearcherStudents(Float.parseFloat(parsedPrices.get(1)));
                    prices.setStaff(Float.parseFloat(parsedPrices.get(2)));
                    prices.setOthers(Float.parseFloat(parsedPrices.get(2)));
                    break;
                case 4:
                    prices.setStudents(Float.parseFloat(parsedPrices.getFirst()));
                    prices.setResearcherStudents(Float.parseFloat(parsedPrices.get(1)));
                    prices.setStaff(Float.parseFloat(parsedPrices.get(2)));
                    prices.setOthers(Float.parseFloat(parsedPrices.get(3)));
                    break;

            }
        }

        return prices;
    }
}
