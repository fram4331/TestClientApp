package trDefine.header;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestPacket {
    /**
     * 데이터 헤더
     */
    @SerializedName("requestHeader")
    public RequestHeader requestHeader;

    /**
     * 회원사 영역
     */
    @SerializedName("membersArea")
    public JsonElement membersArea;

    /**
     * 축약 서명시 서명 데이터
     * - 서명 데이터를 base64로 인코딩 해서 올림
     */
    @SerializedName("signData")
    public String signData;

    /**
     * 축약 서명시 원본 데이터
     */
    @SerializedName("originalData")
    public String originalData;

    /**
     * 공인인증서 축약 서명에 대한 공개키
     * - 공개 키를 base64로 인코딩 해서 올림
     * - 사용 여부를 INITIATE TR에서 선택 할 수 있음
     */
    @SerializedName("publicKey")
    public String publicKey;

    /**
     * 실제 TR Data
     */
    @SerializedName("channel")
    public RequestChannel channel;

    /**
     * packet만들기
     * @return
     */
    public static RequestPacket make(RequestHeader rqHeader, RequestChannel channel, JsonElement membersArea)
    {
        RequestPacket packet 	= new RequestPacket();
        packet.channel 			= channel;
        packet.requestHeader	= rqHeader;
        packet.membersArea		= membersArea;
        return packet;
    }

    public static RequestPacket make(RequestFullPacket fullPacket)
    {
        RequestPacket packet 	= new RequestPacket();
        packet.requestHeader	= fullPacket.requestHeader;
        packet.membersArea		= fullPacket.membersArea;
        packet.signData         = fullPacket.signData;
        packet.originalData     = fullPacket.originalData;
        packet.publicKey        = fullPacket.publicKey;
        if (fullPacket.channel != null) {
            String trCode = fullPacket.channel.entrySet().iterator().next().getKey();
            packet.channel = RequestChannel.make(fullPacket.channel.get(trCode), trCode);
        }
        return packet;
    }
}
