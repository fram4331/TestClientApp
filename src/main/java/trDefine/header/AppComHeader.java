package trDefine.header;

import io.netty.buffer.ByteBuf;
import util.TypeHelper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class AppComHeader extends AbsPacket {
    public static final Charset defaultSet  = StandardCharsets.UTF_8;

    //-----------------Length---------------------------
    public static final int LEN_LENGTH      = 6;
    public static final int LEN_FUNCTION    = 1;
    public static final int LEN_DESTINATION = 5;
    public static final int LEN_ENCRYPT     = 1;
    public static final int LEN_TRCODE      = 8;
    public static final int LEN_SESSION     = 8;

    /**
     * 증권사쪽에서는 사용안함.
     */
    public static final int LEN_ISKOSCOM    = 1;
    public static final int LEN_RESERVED    = 10;

    public static final int LENGTH          = 40;

    //-----------------Field Indexs------------------------------
    public static final int FD_LENGTH       = 0;
    public static final int FD_FUNCTION     = 1;
    public static final int FD_DESTINATION  = 2;
    public static final int FD_ENCRYPT      = 3;
    public static final int FD_TRCODE       = 4;
    public static final int FD_SESSION      = 5;
    public static final int FD_ISKOSCOM     = 6;
    public static final int FD_RESERVED     = 7;

    public static final byte FUNCTION_RQRP      = 'R';  //RQRP형태의 일반적인 패킷
    public static final byte FUNCTION_BROADCAST = 'B';  //서버에서 클라이언트로만 보내는 브로드캐스팅
    public static final byte FUNCTION_HEARTBEAT = 'H';  //10초에 한번씩 보내는 Heartbit
    public static final byte FUNCTION_SERVERIP  = 'S';  //Server IP를 리턴한다.

    public static final byte ENCRYPT_YES        = '1';  //암호문
    public static final byte ENCRYPT_NO         = '0';  //평문

    public static final byte ISKOSCOM_YES       = 'K';  //Koscom
    public static final byte ISKOSCOM_NO        = 'S';  //Stock Firm

    public final static byte[] DEF_DESTINATION  = {'0','0','0','0','0'};
    public final static byte[] DEF_SESSION      = {' ', ' ', ' ', ' ', ' ', ' ', ' ',' '};
    public static final byte[] DEF_RESERVED     = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    public static final byte[] DEST_SVR     = {'h', 'd', 's', 'v', 'r'};

    public AppComHeader() {
        super();
    }

    @Override
    public void initPacketSchema() {
        System.out.println("AppComHeader:initPacketSchema() >>>>>>>>>");
        addField(new HeaderItem("Length", LEN_LENGTH, HeaderItem.FT_INT_STRING));
        addField(new HeaderItem("Function", LEN_FUNCTION, HeaderItem.FT_BYTE));
        addField(new HeaderItem("Destnation", LEN_DESTINATION, HeaderItem.FT_STRING));
        addField(new HeaderItem("Encrypt", LEN_ENCRYPT, HeaderItem.FT_BYTE));
        addField(new HeaderItem("TRCode", LEN_TRCODE, HeaderItem.FT_STRING));
        addField(new HeaderItem("Session", LEN_SESSION, HeaderItem.FT_STRING));
        addField(new HeaderItem("IsStockFirm", LEN_ISKOSCOM, HeaderItem.FT_BYTE));
        addField(new HeaderItem("Reserved", LEN_RESERVED, HeaderItem.FT_STRING));
    }

    @Override
    public void setPacketData(byte[] src) {
        packetData = null;
        packetData = new byte[AppComHeader.LENGTH];
        System.arraycopy(src, 0, packetData, 0, AppComHeader.LENGTH);
        System.out.println("AppComHeader:setPacketData() >>>>>>>>>" + (new String(packetData)));
    }

    public void writeComHeader(byte[] buffer, int packetLength, byte function,
                               byte encrypt, byte[] trcode)
    {
        TypeHelper.intToLeftZeroFillBytes(buffer, 0, LEN_LENGTH, (packetLength - LEN_LENGTH), '0');
        buffer[getOffset(FD_FUNCTION)] = function;
        System.arraycopy(DEF_DESTINATION, 0, buffer, getOffset(FD_DESTINATION), LEN_DESTINATION);
        buffer[getOffset(FD_ENCRYPT)] = encrypt;
        System.arraycopy(trcode, 0, buffer, getOffset(FD_TRCODE), LEN_TRCODE);
        System.arraycopy(DEF_SESSION, 0, buffer, getOffset(FD_SESSION), LEN_SESSION);
        buffer[getOffset(FD_ISKOSCOM)] = ISKOSCOM_NO;
        System.arraycopy(DEF_RESERVED, 0, buffer, getOffset(FD_RESERVED), LEN_RESERVED);
    }

    public void release()
    {
        items.clear();
        items = null;
        nameAccess.clear();
        nameAccess = null;
        packetData = null;
    }

    public static int parsePacketLength(ByteBuf inBuffer) throws IOException
    {
        System.out.println("AppComHeader:parsePacketLength(ByteBuf inBuffer) >>>>>>>>>");
        if (inBuffer.readableBytes() >= LEN_LENGTH) {
            byte[] lengthBuf = new byte[LEN_LENGTH];
            inBuffer.getBytes(inBuffer.readerIndex(), lengthBuf);
            return Integer.parseInt(new String(lengthBuf)) + LEN_LENGTH;
        }
        return 0;
    }

}
