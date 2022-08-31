package trDefine.connect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import trDefine.header.AbsTRHeader;
import trDefine.header.RequestChannel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HANDSHAK_RQ extends AbsTRHeader<RequestChannel> {
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
     * HandShake Data
     */
    @SerializedName("data")
    public String data;

    @Override
    public RequestChannel toChannel() {
        RequestChannel channel = RequestChannel.make();
        channel.handshak = this;
        return channel;
    }
}
