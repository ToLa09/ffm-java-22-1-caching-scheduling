package ffmjava221.backend;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StockPriceService {

    private final WebClient webClient = WebClient.create("https://finnhub.io/api/v1/quote");

    public StockPrice getStockPriceApple(){
       ResponseEntity<StockPrice> responseEntity = webClient.get()
                .uri("?symbol=AAPL&token=cdpnvraad3ia8s05f5egcdpnvraad3ia8s05f5f0")
                .retrieve()
                .toEntity(StockPrice.class)
               .block();

        StockPrice responseBody;
        if(responseEntity != null){
            responseBody = responseEntity.getBody();
        } else throw new StockPriceResponseException("Response entity is null");

        if(responseBody != null){
            return responseBody;
        } else throw new StockPriceResponseException("Response body is null");
    }
}
