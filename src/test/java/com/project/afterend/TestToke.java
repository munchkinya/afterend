package com.project.afterend;

import com.project.afterend.util.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestToke {

    @Test
    public void test() throws UnsupportedEncodingException {
       String token = JwtUtil.sign("lyt","lyt");
       System.out.println(token);System.out.println(JwtUtil.verify(token));
    }
}
