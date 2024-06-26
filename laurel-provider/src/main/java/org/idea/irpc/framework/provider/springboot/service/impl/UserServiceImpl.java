package top.panson.irpc.framework.provider.springboot.service.impl;

import top.panson.irpc.framework.interfaces.UserService;
import top.panson.irpc.framework.spring.starter.common.IRpcService;

/**
 * @Author linhao
 * @Date created in 10:46 下午 2022/3/7
 */
@IRpcService
public class UserServiceImpl implements UserService {

    @Override
    public void test() {
        System.out.println("test");
    }
}
