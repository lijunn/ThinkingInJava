import java.util.Arrays;

/**
 * @author : LiJun
 * @date : 2023-03-15 17:36
 **/
public class ZipList {
    private byte[] zlBytes; // 压缩列表的字节数组
    private int zlLen; // 压缩列表的长度

    // 压缩列表节点结构体
    private static class ZlEntry {
        private byte[] prevLen; // 前置节点长度
        private byte[] encoding; // 节点值的编码方式
        private byte[] content; // 节点值

        public ZlEntry(byte[] prevLen, byte[] encoding, byte[] content) {
            this.prevLen = prevLen;
            this.encoding = encoding;
            this.content = content;
        }
    }

    public ZipList() {
        zlBytes = new byte[32]; // 初始化为 32 字节
        zlLen = 0;
    }

    // 在压缩列表末尾插入一个节点
    public void push(byte[] value) {
        // 计算新节点的长度
        int entryLen = 1; // 节点头部占用 1 字节
        if (value.length < 254) {
            entryLen += 1 + value.length;
        } else {
            entryLen += 5 + value.length;
        }

        // 扩容压缩列表
        int newLen = zlLen + entryLen;
        if (newLen > zlBytes.length) {
            byte[] newBytes = new byte[Math.max(newLen, 2 * zlBytes.length)];
            System.arraycopy(zlBytes, 0, newBytes, 0, zlLen);
            zlBytes = newBytes;
        }

        // 将新节点插入压缩列表尾部
        ZlEntry entry = new ZlEntry(new byte[1], new byte[1], value);
        System.arraycopy(entry.prevLen, 0, zlBytes, zlLen, entry.prevLen.length);
        zlLen += entry.prevLen.length;
        System.arraycopy(entry.encoding, 0, zlBytes, zlLen, entry.encoding.length);
        zlLen += entry.encoding.length;
        if (entry.content.length < 254) {
            zlBytes[zlLen++] = (byte) entry.content.length;
        } else {
            zlBytes[zlLen++] = (byte) 254;
            zlBytes[zlLen++] = (byte) (entry.content.length & 0xFF);
            zlBytes[zlLen++] = (byte) ((entry.content.length >> 8) & 0xFF);
            zlBytes[zlLen++] = (byte) ((entry.content.length >> 16) & 0xFF);
        }
        System.arraycopy(entry.content, 0, zlBytes, zlLen, entry.content.length);
        zlLen += entry.content.length;
    }

    // 获取压缩列表中指定索引处的节点值
    public byte[] get(int index) {
        int pos = 0;
        int i = 0;
        while (i < index && pos < zlLen) {
            byte[] prevLen = getPrevLen(pos);
            byte[] encoding = getEncoding(pos + prevLen.length);
            int contentLen = getContentLen(pos + prevLen.length + encoding
                    .length); pos += prevLen.length + encoding.length + contentLen; i++; } if (i == index && pos < zlLen) { byte[] prevLen = getPrevLen(pos); byte[] encoding = getEncoding(pos + prevLen.length); int contentLen = getContentLen(pos + prevLen.length + encoding.length); byte[] content = getContent(pos + prevLen.length + encoding.length, contentLen); return content; } else { return null; } }


    // 获取压缩列表中指定位置处节点的前置节点长度
    private byte[] getPrevLen(int pos) {
        byte b = zlBytes[pos];
        if (b < (byte) 254) {
            return new byte[] { b };
        } else {
            return Arrays.copyOfRange(zlBytes, pos + 1, pos + 5);
        }
    }

    // 获取压缩列表中指定位置处节点的值编码方式
    private byte[] getEncoding(int pos) {
        return new byte[] { zlBytes[pos] };
    }

    // 获取压缩列表中指定位置处节点的值长度
    private int getContentLen(int pos) {
        byte b = zlBytes[pos];
        if (b < (byte) 254) {
            return b;
        } else {
            return ((int) zlBytes[pos + 1] & 0xFF) |
                    (((int) zlBytes[pos + 2] & 0xFF) << 8) |
                    (((int) zlBytes[pos + 3] & 0xFF) << 16);
        }
    }

    // 获取压缩列表中指定位置处节点的值
    private byte[] getContent(int pos, int len) {
        return Arrays.copyOfRange(zlBytes, pos, pos + len);
    }
}
