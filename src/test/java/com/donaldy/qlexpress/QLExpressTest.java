package com.donaldy.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

/**
 * @author donald
 * @date 2024/12/10
 */
public class QLExpressTest {

    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a + b * c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);
    }

    @Test
    public void test() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        String expressQL = "function getUserInfo(int userId) {\r\n" +
                "	if(userId > 0){\r\n" +
                "		return \"获取到了用户的信息\"\r\n" +
                "	}else{\r\n" +
                "		return \"获取不到用户的信息\"\r\n" +
                "	}\r\n" +
                "}\r\n" +
                "return getUserInfo(uid)";
        DefaultContext<String, Object> params = new DefaultContext<>();
        params.put("uid", 1);

        Object result = runner.execute(expressQL, params, null, true, true);
        System.out.println(result);
    }
}
