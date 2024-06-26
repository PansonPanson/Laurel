package top.panson.irpc.framework.core.serialize.jdk;

import top.panson.irpc.framework.core.serialize.SerializeFactory;

import java.io.*;

/**
 * @Author linhao
 * @Date created in 7:04 下午 2022/1/17
 */
public class JdkSerializeFactory implements SerializeFactory {


    @Override
    public <T> byte[] serialize(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream output = new ObjectOutputStream(os);
            output.writeObject(t);
            //bugfix 解决readObject时候出现的eof异常
            output.writeObject(null);
            output.flush();
            output.close();
            data = os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        try {
            ObjectInputStream input = new ObjectInputStream(is);
            Object result = input.readObject();
            return ((T) result);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
