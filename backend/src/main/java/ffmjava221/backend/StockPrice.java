package ffmjava221.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StockPrice(@JsonProperty("c") double price) {
}
