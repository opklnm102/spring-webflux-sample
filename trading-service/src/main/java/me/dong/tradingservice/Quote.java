package me.dong.tradingservice;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ethan.kim on 2018. 6. 23..
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Quote {

    private static final MathContext MATH_CONTEXT = new MathContext(2);

    private String ticker;

    private BigDecimal price;

    private Instant instant;

    public Quote(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public Quote(String ticker, Double price) {
        this.ticker = ticker;
        this.price = new BigDecimal(price, MATH_CONTEXT);
    }
}
