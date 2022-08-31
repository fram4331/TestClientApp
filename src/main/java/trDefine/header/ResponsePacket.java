package trDefine.header;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponsePacket {
    /**
     * 데이터 헤더
     * @see trDefine.header.RequestHeader
     */
    @SerializedName("responseHeader")
    public ResponseHeader responseHeader;

    /**
     * 회원사 영역
     */
    @SerializedName("membersArea")
    public JsonElement membersArea;

    /**
     * Response에 대한 Error 상태
     * @see trDefine.header.ResponseError
     */
    @SerializedName("error")
    public ResponseError error;

    /**
     * Toast메세지
     * @ToastMsg
     */
    @SerializedName("toastMsg")
    public ToastMsg toastMsg;

    /**
     * 실제 TR Data
     */
    @SerializedName("channel")
    public ResponseChannel channel;

    /**
     * packet만들기
     * @return
     */
    public static ResponsePacket make(ResponseHeader resHeader, ResponseChannel channel,
                                      JsonElement membersArea, ResponseError error, ToastMsg toastMsg)
    {
        ResponsePacket packet 	= new ResponsePacket();
        packet.channel 			= channel;
        packet.responseHeader 	= resHeader;
        packet.membersArea		= membersArea;
        packet.error			= error;
        packet.toastMsg			= toastMsg;
        return packet;
    }

    /**
     * packet만들기
     * @return
     */
    public static ResponsePacket make(ResponseChannel channel, ResponseError error, ToastMsg toastMsg)
    {
        ResponsePacket packet 	= new ResponsePacket();
        packet.responseHeader	= null;
        packet.channel 			= channel;
        packet.error			= error;
        packet.toastMsg			= toastMsg;
        return packet;
    }
}
