package trDefine.header;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import trDefine.connect.HANDSHAK_RQ;
import trDefine.connect.INITIATE_RQ;

import java.lang.reflect.Field;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestChannel extends AbsChannel {
    @SerializedName("INITIATE")
    public INITIATE_RQ initiate;

    @SerializedName("HANDSHAK")
    public HANDSHAK_RQ handshak;

    static public  RequestChannel make()
    {
        return new RequestChannel();
    }

    public static RequestChannel make(JsonElement packetData, String trCode) {
        RequestChannel channel = new RequestChannel();

        try {
            Class<?> channelClass = channel.getClass();
            Field field        = channelClass.getField(trCode.toLowerCase());
            field.setAccessible(true);
            field.set(channel, new Gson().fromJson(packetData, field.getType()));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return channel;
    }
}
