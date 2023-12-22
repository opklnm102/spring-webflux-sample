package me.dong.tradingservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by ethan.kim on 2018. 6. 24..
 */
@Controller
public class HomeController {

    private final TradingUserRepository tradingUserRepository;

    public HomeController(TradingUserRepository tradingUserRepository) {
        this.tradingUserRepository = tradingUserRepository;
    }

    @GetMapping(path = "/")
    public String home(Model model) {
        model.addAttribute("users", this.tradingUserRepository.findAll());
        return "index";
    }
}
