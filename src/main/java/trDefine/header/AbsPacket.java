package trDefine.header;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import trDefine.header.HeaderItem;

abstract public class AbsPacket {
    protected ArrayList<HeaderItem> items;
    protected HashMap<String, HeaderItem> nameAccess;
    protected AbsPacket parent;
    @Getter
    protected byte[] packetData;
    protected int PACKET_LENGTH = 0;
    protected int startOffset;

    abstract public void initPacketSchema();
    abstract public void setPacketData(byte[] src);

    public AbsPacket() {
        System.out.println("AbsPacket() >>>>>>>>>");
        this.items = new ArrayList<>();
        this.nameAccess = new HashMap<>();
        this.parent = null;
        this.packetData = null;
        this.startOffset = 0;

        initPacketSchema();
    }

    public AbsPacket(AbsPacket parentTable) {
        System.out.println("AbsPacket(AbsPacket parentTable) >>>>>>>>>");
        this.items = new ArrayList<>();
        this.nameAccess = new HashMap<>();
        this.parent = parentTable;
        this.startOffset = parent.startOffset + parent.PACKET_LENGTH;

        initPacketSchema();
    }

    /**
     * 부모 테이블을 지정한다.
     * 파라메터로 null이 들어오면 0으로 초기화 한다.
     * @param parentTable : 앞에 붙는 head
     */
    public void setParent(AbsPacket parentTable)
    {
        parent = parentTable;
        if (parentTable == null)
        {
            this.startOffset = 0;
        }
        else
        {
            this.startOffset = parent.startOffset + parent.PACKET_LENGTH;
        }
    }

    public void addField(HeaderItem item) {
        item.setOffset(PACKET_LENGTH);
        this.items.add(item);
        if (nameAccess.containsKey(item.getName())) {
            throw new RuntimeException(
                    "Duplicated item name:[" + item.getName() + "]");
        }
        nameAccess.put(item.getName(), item);
        PACKET_LENGTH = PACKET_LENGTH + item.getLength();
    }

    public HeaderItem getItem(String name)
    {
        return nameAccess.get(name);
    }

    public HeaderItem getItem(int index)
    {
        return this.items.get(index);
    }

    public int getOffset(String name)
    {
        return nameAccess.get(name).getOffset();
    }

    public int getOffset(int index)
    {
        return this.items.get(index).getOffset();
    }

    public int getLenght(String name)
    {
        return nameAccess.get(name).getLength();
    }

    public int getLenght(int index)
    {
        return this.items.get(index).getLength();
    }
    /**
     * 전체 패킷에서 index에 해당되는 Byte Value를 리턴
     * @param index - 패킷의 몇번째 Item인지
     * @return byte - index에 해당되는 Item의 값
     */
    public byte getByteValue(int index) {
        byte rValue = 0;
        System.out.println("getByteValue >>>>>>>>> ");
        if (packetData==null) {
            System.out.println("getByteValue : packetData NULL >>>>>>>>> ");
            return rValue;
        }
        if (index>=items.size()) {
            System.out.println("getByteValue : index Overflow >>>>>>>>> ");
            return rValue;
        }

        if (items.get(index).getType()!=HeaderItem.FT_BYTE) {
            return rValue;
        }

        System.out.println("getByteValue >>>>>>>>> 1:" + getOffset(index));
        rValue = this.packetData[getOffset(index)];
        return rValue;
    }

    public byte[] getBytesValue(int index) {
        System.out.println("getBytesValue >>>>>>>>> ");
        if (packetData==null) {
            System.out.println("getBytesValue : packetData NULL >>>>>>>>> ");
            return null;
        }
        if (index>=items.size()) {
            System.out.println("getBytesValue : index Overflow >>>>>>>>> ");
            return null;
        }

        if (!((items.get(index).getType()==HeaderItem.FT_STRING) || (items.get(index).getType()==HeaderItem.FT_BYTES))) {
            System.out.println("getBytesValue : Type Fail >>>>>>>>> ");
            return null;
        }
        int bytesLength = getLenght(index);
        byte[] rValue = new byte[bytesLength];

        System.out.println("getByteValue >>>>>>>>> offset:" + getOffset(index) + ",length:" + bytesLength);
        System.arraycopy(this.packetData, getOffset(index), rValue, 0, bytesLength);
        System.out.println("getByteValue >>>>>>>>> value:" + (new String(rValue)));
        return rValue;
    }

    /**
     * Items로 저장된 전체 값을 String으로 나열하여 반환
     * @return String - 나열된 값
     */
    public String getPacketDataString() {
        if (packetData==null) {
            System.out.println("getByteValue : packetData NULL >>>>>>>>> ");
            return null;
        }
        return new String(packetData);
    }
}
