package top.panson.irpc.framework.core.serialize;

/**
 * @Author linhao
 * @Date created in 6:20 下午 2022/1/17
 */
public interface SerializeFactory {


    /**
     * 序列化
     *
     * @param t
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T t);

    /**
     * 反序列化
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] data, Class<T> clazz);
}
