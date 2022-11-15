package ffmjava221.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StockPriceController {

    private final StockPriceService service;


    @GetMapping("/stockPriceApple")
    public StockPrice getStockPriceApple () throws InterruptedException {
        return service.getStockPriceApple();
    }

}
