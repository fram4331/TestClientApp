package trDefine.header;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockInfo {
    /**
     * 종목 숏코드
     *
     */
    @SerializedName("shortCode")
    public String shortCode;

    /**
     * 종목 풀코드
     */
    @SerializedName("fullCode")
    public String fullCode;

    /**
     * 장구분
     * 0:Index, 1: Kospi, 2:Kosdaq
     *
     */
    @SerializedName("marketType")
    public String marketType;

    /**
     * 상품구분
     * 0:Stock 1:ETF
     *
     */
    @SerializedName("commodityType")
    public String commodityType;

    ////////////////// 해외주식 /////////////////////

    /**
     * 종목 코드 (ex: US.AAPL)
     * @see RequestHeader

     @SerializedName("code")
     public String code;
     */
    /**
     * 국가 코드(US)
     * @see RequestHeader

     @SerializedName("countryCode")
     public String countryCode;
     */
    /**
     * 종목 심볼코드 (AAPL)
     * @see RequestHeader

     @SerializedName("symbol")
     public String symbol;


     @SerializedName("market")
     public String market;
     */
    /**
     * packet만들기
     *
     * @return
     */
    public static StockInfo make(String shortCode, String fullCode, String marketType, String commodityType) {
        StockInfo packet = new StockInfo();
        packet.shortCode = shortCode;
        packet.fullCode = fullCode;
        packet.marketType = marketType;
        packet.commodityType = commodityType;
        return packet;
    }

    /**
     * 해외주식 packet만들기
     *
     * @return

    public static StockInfo make(String code, String countryCode, String symbol) {
    StockInfo packet 		= new StockInfo();
    packet.code = code;
    packet.countryCode = countryCode;
    packet.symbol = symbol;
    return packet;
    }
     */

    /**
     * 해외주식 packet만들기
     *
     * @return

    public static StockInfo make(String code, String countryCode, String symbol, String market) {
    StockInfo packet 		= new StockInfo();
    packet.code = code;
    packet.countryCode = countryCode;
    packet.symbol = symbol;
    packet.market = market;
    return packet;
    }
     */
}
