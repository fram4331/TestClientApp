package trDefine.header;

public class HeaderItem {
    public static final int FT_STRING				= 0;	// NSString

    /**
     * 10byte 이하(부호포함)의 Integer형 String
     */
    public static final int FT_INT_STRING           = 1;	// 문자열 형태의 숫자를 숫자로

    /**
     * 10byte 이하(부호포함) 의 Float형 String
     */
    public static final int FT_FLOAT_STRING         = 2;	//   "

    /**
     * 19byte 이하(부호포함)의 Double형의 String
     */
    public static final int FT_DOUBLE_STRING        = 3;	//   "

    /**
     * 19byte 이하(부호포함)의 Long형 String
     */
    public static final int FT_LONG_STRING          = 4;	// 문자열 형태의 숫자를 숫자로

    /**
     * Integer, 기본은 bigendian, littleEndian으로 바꾸려면 bootstrap에서 변경해야함
     */
    public static final int FT_INTEGER				= 5;	// Integer
    public static final int FT_LONG				    = 6;	// Long
    public static final int FT_DOUBLE				= 7;	// Double
    public static final int FT_FLOAT				= 8;	// NSNumber 4Byte float
    public static final int FT_HEXSTR				= 9;	// NSString 이지만 Hex형태의 값을 리턴
    public static final int FT_BYTES				= 10;	// NSData리턴 !!사이즈 0;을 넘기면 이하 모든 데이타를 복사함
    public static final int FT_BYTE 				= 11;	// Byte
    public static final int FT_STRINTFORSUBTABLE 	= 12;	// 반복 카운트(Integer_String), 숫자로 변환해서 이하 Array로 만들어서 넣어서 돌림.
    public static final int FT_INTFORSUBTABLE		= 13;	// 반복 카운트(Integer), 숫자로 변환해서 이하 Array로 만들어서 넣어서 돌림.
    public static final int FT_SUBTABLE				= 14;	// subTable
    public static final int FT_SUBTABLEWITHCOUNT	= 15;	// 테이블이 반복된다. 반복 카운트를 넣어줘야 한다.

    /**
     * 길이의 제한을 두지 않는 Int String
     * ※가급적 사용하지 말고 길이에 맞춰서 FT_INT_STRING를 사용 해야 한다.
     * 속도 차이가 3배에서 20배가량 난다.
     */
    public static final int FT_INT_STRINGEX			= 16;	//

    /**
     * 길이의 제한을 두지 않는 Long String
     * ※가급적 사용하지 말고 길이에 맞춰서 FT_LONG_STRING를 사용 해야 한다.
     * 속도 차이가 3배에서 20배가량 난다.
     */
    public static final int FT_LONG_STRINGEX		= 17;	//


    public static final int FT_FILLER 				= 18;	// 무시 하는 필드, 자동 parse로 하면 NSObject를 리턴한다.
    public static final int FT_NONE					= 19;

    public static final int FT_INTEGEREX			= 20;	// 신전문 Integer
    public static final int FT_DOUBLEEX			= 21;	// 신전문 Double

    private String name;
    private int length;
    int	offset;
    int	type;

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getOffset() {
        return offset;
    }

    public int getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setType(int type) {
        this.type = type;
    }


    public HeaderItem() { }

    public HeaderItem(String name, int length) {
        this.name = name;
        this.length = length;
        this.offset = 0;
        this.type = FT_NONE;
    }

    public HeaderItem(String name, int length, int type) {
        this.name = name;
        this.length = length;
        this.offset = 0;
        this.type = type;
    }

    public HeaderItem(String name, int length, int offset, int type) {
        this.name = name;
        this.length = length;
        this.offset = offset;
        this.type = type;
    }
}
