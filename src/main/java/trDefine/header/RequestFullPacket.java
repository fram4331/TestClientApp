package trDefine.header;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestFullPacket {
    /**
     * 데이터 헤더
     * @see RequestHeader
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
    public JsonObject channel;
}
