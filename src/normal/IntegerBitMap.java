package normal;
/**
 * 自定义容量的bitMap
 * 实现了基础的增删查
 **/
public class IntegerBitMap {
    private final byte[] bitMap;
    /**
     * 可以容纳的上限为cap*8
     **/
    public IntegerBitMap(int cap) {
        bitMap = new byte[cap / 8 + 1];
    }
    
    /**
     * 得到在第几个字节
     * 比如 15 / 8 = 1
     * 在第2个字节的位置（下标从0开始）
     **/
    private int getPosition(int num) {
        return num >> 3;
    }
    
    private int getBitSite(int num) {
        return num % 8;
    }

    /**
     * 测试及说明
     **/
    public static void main(String[] args) {
        // 只存一个字节的
        IntegerBitMap ibm = new IntegerBitMap(1);
        // 存入一个3 字节变为：0000 1000
        ibm.add(3);
        // 再存入一个6 字节变为：0010 1000
        ibm.add(6);
        // 相当于 0010 1000 & 0000 0100 ！= 0
        ibm.contains(3);
        // 相当于 0010 1000 & 1111 1011;
        ibm.del(3);
        // 超出容量，添加失败
        System.out.println(ibm.add(9));
    }
    
    /**
     * 存入一个3 字节变为：0000 1000
     **/
    public boolean add(int num) {
        int position = getPosition(num);
        if(position >= bitMap.length) {
            return false;
            // todo 可以做扩容处理
            // todo 复制旧数组的值
            // bitMap = new byte[num / 8 + 1];
        }
        // 1先左移，然后再进行或 这样相应位置就为1了
        bitMap[position] = (byte) (bitMap[position] | 1 << getBitSite(num));
        return  true;
    }
    public boolean del(int num) {
        int position = getPosition(num);
        if(position >= bitMap.length) {
            return false;
        }
        bitMap[position] = (byte) (bitMap[position] & ~(1 << getBitSite(num)));
        return true;
    }
    public boolean contains(int num) {
        int position = getPosition(num);
        if(position >= bitMap.length) {
            return false;
        }
        return (bitMap[position] & 1 << getBitSite(num)) != 0;
    }

}
