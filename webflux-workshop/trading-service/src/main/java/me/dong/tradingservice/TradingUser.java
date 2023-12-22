package me.dong.tradingservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by ethan.kim on 2018. 6. 24..
 */
@Document
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "userName"})
public class TradingUser {

    @Id
    private String id;

    private String userName;

    private String fullName;

    public TradingUser(String id, String userName, String fullName) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
    }

    public TradingUser(String userName, String fullName) {
        this.userName = userName;
        this.fullName = fullName;
    }
}
