package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    public void asIs(Day day) {
        int numLetters;
        switch (day) {
            case MONDAY:
            case FRIDAY:
            case SUNDAY:
                numLetters = 6;
                break;
            case TUESDAY:
                System.out.println(7);
                break;
            case THURSDAY:
            case SATURDAY:
                System.out.println(8);
                break;
            case WEDNESDAY:
                System.out.println(9);
                break;
        }
    }

    public void toBe(Day day) {
        int numLetters =
            switch (day) {
                case MONDAY, FRIDAY, SUNDAY -> 6;
                case TUESDAY -> 7;
                case THURSDAY, SATURDAY -> 8;
                case WEDNESDAY -> 9;
                default -> {
                    int x = day.toString().length();
                    yield x;
                }
            };
    }

    public String formatter(Object obj) {
        String formatted = "unknown";
        if (obj == null) {
            System.out.println("Oops!");
            return "";
        }

        if (obj instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (obj instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (obj instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (obj instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

    public String formatterx(Object obj) {
        return switch (obj) {
            case null -> {
                System.out.println("Oops!");
                yield "";
            }
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> obj.toString();
        };
    }

    sealed interface CardClassification permits Suit, Tarot {
    }

    public enum Suit implements CardClassification {CLUBS, DIAMONDS, HEARTS, SPADES}

    final class Tarot implements CardClassification {
    }

    void exhaustiveSwitchWithoutEnumSupport(CardClassification cardClassification) {
        switch (cardClassification) {
            case Suit s when s == Suit.CLUBS -> System.out.println("clubs");
            case Suit s when s == Suit.DIAMONDS -> System.out.println("diamonds");
            case Suit s when s == Suit.HEARTS -> System.out.println("hearts");
            case Suit s -> System.out.println("spades");
            case Tarot t -> System.out.println("tarot");
        }
    }

    void testX() {

    }
}
