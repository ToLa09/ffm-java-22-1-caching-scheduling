package ffmjava221.backend;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;

@Service
public class StockPriceService {
    private static final String STOCKPRICE_APPLE_CACHE = "STOCKPRICE_APPLE_CACHE";
    private final WebClient webClient = WebClient.create("https://finnhub.io/api/v1/quote");

    @Cacheable(STOCKPRICE_APPLE_CACHE)
    public StockPrice getStockPriceApple() throws InterruptedException {
       ResponseEntity<StockPrice> responseEntity = webClient.get()
                .uri("?symbol=AAPL&token=cdpnvraad3ia8s05f5egcdpnvraad3ia8s05f5f0")
                .retrieve()
                .toEntity(StockPrice.class)
               .block();
        Thread.sleep(3000);
        StockPrice responseBody;
        if(responseEntity != null){
            responseBody = responseEntity.getBody();
        } else throw new StockPriceResponseException("Response entity is null");

        if(responseBody != null){
            return responseBody;
        } else throw new StockPriceResponseException("Response body is null");
    }
    @CachePut(STOCKPRICE_APPLE_CACHE)
    @Scheduled(cron = "0 */5 * * * *")
    public StockPrice updateStockPriceApple() throws InterruptedException {
        System.out.println("Cache neu geladen" + STOCKPRICE_APPLE_CACHE + Instant.now());
        return getStockPriceApple();
    }
    
    
    @CacheEvict({STOCKPRICE_APPLE_CACHE})
    public String clearCache() {
        return "Cashes cleared";
    }
}
