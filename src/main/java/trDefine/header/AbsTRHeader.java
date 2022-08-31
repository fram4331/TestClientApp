package trDefine.header;

abstract public class AbsTRHeader<T extends AbsChannel>
{
    abstract public boolean needAbbSign();

    /**
     * TR의 속한 카테고리를 리턴한다.
     * @return
     */
    abstract public String getCategory();

    /**
     * TR code를 리턴함.
     * @return
     */
    abstract public String getTRCode();

    /**
     * 암호화 여부 반환
     * @return true: 암호문 false:평문
     */
    abstract public boolean isEncrypt();

    abstract public T toChannel();
}
