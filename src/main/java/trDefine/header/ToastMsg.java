package trDefine.header;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ToastMsg {
    @SerializedName("text")
    public String text;

    static
    public ToastMsg make(String text)
    {
        ToastMsg instance = new ToastMsg();
        instance.text = text;
        return instance;
    }
}
