package agent;

import io.netty.channel.ChannelHandlerContext;

import trDefine.header.AppComHeader;
import trDefine.header.RequestPacket;
import trDefine.header.ResponsePacket;

import java.nio.charset.Charset;

public class ChannelAgent {
    private byte[] appInBytes = null;
    private String myHandleID;

    private String trCode;

    private AppComHeader appComInHeader = null;

    private RequestPacket requestPacket = null;

    private byte[] appOutBytes = null;

    private ResponsePacket responsePacket = null;

    private ChannelHandlerContext appChannelCtx = null;

    public byte[] getAppInBytes() {
        return appInBytes;
    }

    public void setAppInBytes(byte[] appInBytes) {
        this.appInBytes = appInBytes;
    }

    public String getMyHandleID() {
        return myHandleID;
    }

    public void setMyHandleID(String myHandleID) {
        this.myHandleID = myHandleID;
    }

    public String getTrCode() {
        return trCode;
    }

    public void setTrCode(String trCode) {
        this.trCode = trCode;
    }

    public ChannelHandlerContext getAppChannelCtx() {
        return appChannelCtx;
    }

    public void setAppChannelCtx(ChannelHandlerContext appChannelCtx) {
        this.appChannelCtx = appChannelCtx;
    }

    public RequestPacket getRequestPacket() {
        return requestPacket;
    }

    public ResponsePacket getResponsePacket() {
        return responsePacket;
    }

    public byte[] getAppOutBytes() {
        return appOutBytes;
    }

    public void setAppOutBytes(byte[] appOutBytes) {
        this.appOutBytes = appOutBytes;
    }

    //======= 초기 작업 함수들
    public void InitAppComHeader(byte[] appInBytes) {
        MemoryRelease();
        setAppInBytes(appInBytes);
        appComInHeader = new AppComHeader();
        appComInHeader.setPacketData(appInBytes);
    }

    //===== Buffer 값 확인 함수들
    public byte getAppComHeaderFunction() {
        byte returnByte = 0;
        if (appInBytes==null) return returnByte;
        if (appComInHeader==null) return returnByte;
        return appComInHeader.getByteValue(AppComHeader.FD_FUNCTION);
    }

    public byte getAppComHeaderEncrypt() {
        byte returnByte = 0;
        if (appInBytes==null) return returnByte;
        if (appComInHeader==null) return returnByte;
        return appComInHeader.getByteValue(AppComHeader.FD_ENCRYPT);
    }

    public String getAppTrCode() {
        if (appInBytes==null) return "";
        if (appComInHeader==null) return "";

        this.trCode = new String(appComInHeader.getBytesValue(AppComHeader.FD_TRCODE));
        return this.trCode;
    }

    public boolean IsEmptyInBuff() {
        return (appInBytes == null);
    }

    public boolean IsEmptyComHeader() {
        return (appComInHeader==null);
    }

    public String channelBufferToString(Charset charset)
    {
        if (appInBytes != null) return (new String(appInBytes, charset));
        else return "";
    }

    //========  메모리 초기화 관련 함수
    public void MemoryReleaseInBuff() {
        appInBytes = null;
        requestPacket = null;
        appOutBytes = null;
        responsePacket = null;
    }
    public void MemoryReleaseComHeader() {
        appComInHeader = null;
    }

    public void MemoryRelease() {
        MemoryReleaseInBuff();
        MemoryReleaseComHeader();
    }

    public void Close()
    {
        MemoryReleaseInBuff();
        MemoryReleaseComHeader();
        trCode = null;
    }

    @Override
    public String toString() {
        return myHandleID;
    }
}
