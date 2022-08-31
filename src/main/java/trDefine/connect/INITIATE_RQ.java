package trDefine.connect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import trDefine.header.AbsTRHeader;
import trDefine.header.RequestChannel;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class INITIATE_RQ extends AbsTRHeader<RequestChannel>
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
        return "INITIATE";
    }

    /**
     * 증플 회원 UID
     */
    @SerializedName("uid")
    public String uid;

    /**
     * 증플 회원 key
     */
    @SerializedName("acceToken")
    public String acceToken;

    /**
     * 앱 버전
     */
    @SerializedName("appVersion")
    public String appVersion;

    /**
     * 단말 모델명
     */
    @SerializedName("model")
    public String model;

    /**
     * 단말 OS
     */
    @SerializedName("os")
    public String os;

    @Override
    public RequestChannel toChannel() {
        RequestChannel channel = RequestChannel.make();
        channel.initiate = this;
        return channel;
    }
}
