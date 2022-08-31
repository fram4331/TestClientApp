package trDefine.connect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import trDefine.header.AbsTRHeader;
import trDefine.header.ResponseChannel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HANDSHAK_RP extends AbsTRHeader<ResponseChannel>
{
    @Override
    public boolean needAbbSign()
    {
        return false;
    }

    @Override
    public boolean isEncrypt()
    {
        return false;
    }

    @Override
    public String getCategory()
    {
        return "connect";
    }

    @Override
    public String getTRCode()
    {
        return "HANDSHAK";
    }

    /**
     * Handshake를 하기 위한 데이터
     */
    @SerializedName("data")
    public String data;

    static
    public HANDSHAK_RP make(String data)
    {
        HANDSHAK_RP instance = new HANDSHAK_RP();
        instance.data = data;
        return instance;
    }

    @Override
    public ResponseChannel toChannel()
    {
        ResponseChannel channel = ResponseChannel.make();
        channel.handshak = this;
        return channel;
    }
}
