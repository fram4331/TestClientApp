package agent;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import trDefine.connect.HANDSHAK_RQ;
import trDefine.header.AppComHeader;
import trDefine.header.RequestChannel;
import trDefine.header.RequestHeader;

import java.nio.charset.StandardCharsets;

public class AppTrMake {

    static public byte[] mergeTR(byte[] head, byte[] body) {
        int destLength = head.length + body.length;
        byte[] dest = new byte[destLength];

        System.arraycopy(head, 0, dest, 0, head.length);
        System.arraycopy(body, 0, dest, head.length, body.length);
        return dest;
    }

    static public byte[] makeComHader(int iBodyLength, String trCode, byte function,
                                      byte encrypt) {
        byte[] buff = new byte[AppComHeader.LENGTH];
        AppComHeader comHead = new AppComHeader();
        comHead.writeComHeader(buff, iBodyLength + AppComHeader.LENGTH, function, encrypt, trCode.getBytes());

        return buff;
    }

    static public byte[] makeHandshak_RQ() {

        RequestHeader requestHeader = new RequestHeader();
        requestHeader.orderNumber = "192168123175";
        requestHeader.mac = "000000000000";
        requestHeader.deviceCode = "30F";
        requestHeader.handle = "4";
        requestHeader.ver = RequestHeader.VERSION;
        requestHeader.pips = 0;

        HANDSHAK_RQ handshakeRQ = new HANDSHAK_RQ();
        handshakeRQ.data = "AQIEATACBBSKRlK11eh/AW/pWXQ9GtoHIZYkgwEBIQALZQrk63kyLgnm3zTxch2CZklDswCIdMZPCHVuuKM4ZHPm8mY0AwnpocokaSmxE0eryxzPonkWLwSxEQPoSJCYCBQgZK4Lf2AlnqL697v4SmDhW8/Nb3fqiE41ujNyFZwcGzfEiiGgWDh/Z+OfEu639NXI+QpJ8p0Fr5OltDKIVJvIr3qQtgMTRmBf1faMNgyXZDhhUuPPy68jhnBb4BhTJu8sd0tLtwzzPwmA/29ynQ+d4iJFiEO4xy8XqJGsY+eWBLwRa0CmVwkm60SRJtNEjHoEGE6odzPlRLc/4zrASUCGLFMos0uh2kabCSEFlTIAWWWrS2zIbYZvMHnHamdVoMen2RVwu5ljj22jpywsOw99JYeF";
        RequestChannel requestChannel = handshakeRQ.toChannel();

        //RequestPacket requestPacket = RequestPacket.make(requestHeader, requestChannel, null);

        JsonObject responsePacketObject = new JsonObject();
        Gson gson                 = new Gson();

        responsePacketObject.add("requestHeader", gson.toJsonTree(requestHeader));
        responsePacketObject.add("channel", gson.toJsonTree(requestChannel));

        byte[] reuestBody = responsePacketObject.toString().getBytes(StandardCharsets.UTF_8);
        System.out.println("[Handshak_body]:" + new String(reuestBody));

        byte[] reuestTR = mergeTR(makeComHader(reuestBody.length, handshakeRQ.getTRCode(), AppComHeader.FUNCTION_RQRP, AppComHeader.ENCRYPT_NO), reuestBody);

        System.out.println("[makeHandshak_RQ]:" + new String(reuestTR));
        return reuestTR;
    }
}
