package util;

public class TypeHelper {
    final private static char[] HEXTAB =
            {
                    '0', '1', '2', '3',	'4', '5', '6', '7',
                    '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
            };

    /**
     * byteArray를 Hex String으로 변환
     * @param bytes : 변환하고자하는 Byte Array
     * @return : Hex로 변환된 String
     */
    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }

    /**
     * byteArray를 Hex String으로 변환
     * @param bytes : 변환하고자하는 Byte Array
     * @param offset : 변환 시작 위치
     * @param convLen : 변환할 길이
     * @return : Hex로 변환된 String
     */
    public static String byteArrayToHexString(byte[] bytes, int offset, int convLen) {
        int inx = 0;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            //이동하고자 하는 길이(offset)만큼 이동이 하고 변환.
            if(offset > inx)
            {
                inx++;
                continue;
            }
            sb.append(String.format("%02x", b&0xff));
            count++;
            //변환하고자 하는 길이(convLen)만큼 변환이 되면 빠져나간다.
            if (count>=convLen)
                break;
        }
        return sb.toString();
    }

    static public String intToLeftZeroFillString(int totalLength, int value) {
        return String.format("%0" + totalLength + "d", value);
    }
    static public String intToLeftSpaceFillString(int totalLength, int value) {
        return String.format("%" + totalLength + "d", value);
    }
    static public String intToRightZeroFillString(int totalLength, int value) {
        return String.format("%-0" + totalLength + "d", value);
    }
    static public String intToRightSpaceFillString(int totalLength, int value) {
        return String.format("%-" + totalLength + "d", value);
    }
    static public String intToAmountString(int totalLength, int value) {
        return String.format("%," + totalLength + "d", value);
    }
    static public String intToAmountString(int value) {
        return String.format("%,d", value);
    }
    static public String longToAmountString(int totalLength, long value) {
        return String.format("%," + totalLength + "d", value);
    }
    static public String longToAmountString(long value) {
        return String.format("%,d", value);
    }

    static public String stringLeftSpaceFillString(int totalLength, String value) {
        return String.format("%" + totalLength + "s", value);
    }

    static public String stringRightSpaceFillString(int totalLength, String value) {
        return String.format("%-" + totalLength + "s", value);
    }

    static public String stringLeftCharFillString(int totalLength, char fillChar, String value) {
        return String.format("%" + totalLength + "s", value).replace(' ', fillChar);
    }

    static public String stringRightCharFillString(int totalLength, char fillChar, String value) {
        return String.format("%-" + totalLength + "s", value).replace(' ', fillChar);
    }

    static public boolean stringCopyBytes(byte[] dest, int pos, int totalLength, String value, char fillChar)
    {
        if (value.length() != totalLength) {
            for (int i=0; i<totalLength; i++)
                dest[pos+i] = (byte)fillChar;
            return false;
        }
        System.arraycopy(value.getBytes(), 0, dest, pos, totalLength);
        return true;
    }

    static public boolean intToLeftZeroFillBytes(byte[] dest, int pos, int totalLength, int value, char errFillChar) {
        String sValue = String.format("%0" + totalLength + "d", value);
        return TypeHelper.stringCopyBytes(dest, pos, totalLength, sValue, errFillChar);
    }

    static public boolean stringToLeftCharFillBytes(byte[] dest, int pos, int totalLength, String value, char fillChar) {
        String sValue = String.format("%" + totalLength + "s", value).replace(' ', fillChar);
        return TypeHelper.stringCopyBytes(dest, pos, totalLength, sValue, fillChar);
    }

    static public boolean stringToRightCharFillBytes(byte[] dest, int pos, int totalLength, String value, char fillChar) {
        String sValue = String.format("%-" + totalLength + "s", value).replace(' ', fillChar);
        return TypeHelper.stringCopyBytes(dest, pos, totalLength, sValue, fillChar);
    }

    static public byte[] stringToRightCharFillBytes(int totalLength, String value, char fillChar) {
        byte[] targetBuf = new byte[totalLength];
        String sValue = String.format("%-" + totalLength + "s", value).replace(' ', fillChar);
        TypeHelper.stringCopyBytes(targetBuf, 0, totalLength, sValue, fillChar);
        return targetBuf;
    }
}
