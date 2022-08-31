package trDefine.header;

import com.google.gson.annotations.SerializedName;

public class RequestHeader {
    public static final String VERSION = "1.2.0";

    /**
     * PIPS처리 아님
     */
    public static final int PIPS_NONE 	= 0;

    /**
     * PIPS처리 해야함
     */
    public static final int PIPS_OK 	= 1;

    /**
     * 플러스 계좌임
     */
    public static final int PIPS_PLUS 	= 2;

    public static interface StoredSource
    {
        public String getDeviceCode();
        public String getOrderNumber();
        public String getMac();
        public String getHandle();
    }
    public static RequestHeader make(StoredSource source, int pips)
    {
        RequestHeader requestHeader	= new RequestHeader();
        requestHeader.pips				= pips;
        requestHeader.deviceCode 		= source.getDeviceCode();
        requestHeader.orderNumber 		= source.getOrderNumber();
        requestHeader.mac 				= source.getMac();
        requestHeader.handle 			= source.getHandle();
        requestHeader.ver				= VERSION;
        return requestHeader;
    }

    @SerializedName("pips")
    public int pips;


    /**
     * 회원사별 매체코드
     * - 4byte
     * - INITIATE TR의 매체 코드로 내려 온것을 그대로 올려줌
     */
    @SerializedName("deviceCode")
    public String deviceCode;

    /**
     * 주문자 식별 번호
     * - 12byte
     */
    @SerializedName("orderNumber")
    public String orderNumber;

    /**
     * MAC Address
     * - 12byte
     */
    @SerializedName("mac")
    public String mac;

    /**
     * handle
     */
    @SerializedName("handle")
    public String handle;

    /**
     * 프로토콜 버전
     */
    @SerializedName("ver")
    public String ver;
}
