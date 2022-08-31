package trDefine.header;

import com.google.gson.annotations.SerializedName;
import trDefine.connect.HANDSHAK_RP;
import trDefine.connect.INITIATE_RP;

public class ResponseChannel extends AbsChannel
{
    //증권사 설정 정보를 내려 보내줌
    @SerializedName("INITIATE")
    public INITIATE_RP initiate;

    //E2E 암호화 모듈 handShake
    @SerializedName("HANDSHAK")
    public HANDSHAK_RP handshak;

    public static ResponseChannel make()
    {
        return new ResponseChannel();
    }
}
