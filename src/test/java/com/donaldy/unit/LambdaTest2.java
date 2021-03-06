package com.donaldy.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ToString
@Data
class Accounting {
    private int amount;
}

public class LambdaTest2 {

    @Test
    public void test() {

        List<Accounting> list = Arrays.asList(new Accounting(1), new Accounting(10), new Accounting(20));

        List<Accounting> newList = list.stream().filter(accounting -> accounting.getAmount() > 9)
                .map(accounting -> new Accounting(accounting.getAmount())).collect(Collectors.toList());

        System.out.println(newList.toString());

        newList.get(0).setAmount(12);

        System.out.println(newList.toString());

        System.out.println(list.toString());
    }

    @Test
    public void test2() {

        List<Accounting> list = Collections.emptyList();

        List<Accounting> list2 = Arrays.asList(new Accounting(1), new Accounting(10), new Accounting(20));

        List<Accounting> list1 = list.stream().map(dot -> new Accounting(10)).collect(Collectors.toList());

        System.out.println(list1.toString());

        List<Accounting> list3 = list2.stream().filter(dto -> dto.getAmount() > 100).collect(Collectors.toList());

        System.out.println(list3.toString());
    }
}
