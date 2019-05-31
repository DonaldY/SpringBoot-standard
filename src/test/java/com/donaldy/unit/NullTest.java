package com.donaldy.unit;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class NullTest {

    public static void main(String[] args) {

        Test test = null;

        //System.out.println(test.getId());

        List<Integer> arr = new ArrayList<>();

        if (arr.isEmpty()) {

            return;
        }

        System.out.println(arr.get(0));

    }
}

@NoArgsConstructor
@Getter
class Test {

    private int id;
    private String name;
}
