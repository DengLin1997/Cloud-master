package com.ruoyi.eims;

import com.ruoyi.eims.domain.SalesOrder;
import com.ruoyi.eims.mapper.SalesOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test01 {

    @Autowired
    private SalesOrderMapper salesOrderMapper;

    @Test
    public void test01() {
        System.out.println("test01");
    }

    @Test
    public void test02(){
        List<SalesOrder> salesOrders = salesOrderMapper.selectSalesOrderList(null);
        System.out.println(salesOrders);
    }

    @Test
    public void test03(){
        SalesOrder salesOrder = salesOrderMapper.selectSalesOrderById(1L);
        System.out.println(salesOrder);
    }
}
