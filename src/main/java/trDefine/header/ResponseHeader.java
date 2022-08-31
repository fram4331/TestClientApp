package trDefine.header;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseHeader {
    public static final String CURRENT_VERSION = "1.2.4";

    public static ResponseHeader make(String handle)
    {
        ResponseHeader dataHeader = new ResponseHeader();
        dataHeader.handle 		= handle;
        dataHeader.ver			= CURRENT_VERSION;
        return dataHeader;
    }

    public static ResponseHeader make()
    {
        ResponseHeader dataHeader = new ResponseHeader();
        dataHeader.ver			= CURRENT_VERSION;
        return dataHeader;
    }

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
