package com.softwaretest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SoftwareTestApplicationTests
{

    @Test
    void trueTest()
    {

        Assert.isTrue(true);
    }


    @Test
    void falseTest()
    {

        Assert.isTrue(false);
    }

}
