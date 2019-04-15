package com.donaldy.unit;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class NullTest {

    public static void main(String[] args) {

        Test test = null;

        System.out.println(test.getId());
    }
}

@NoArgsConstructor
@Getter
class Test {

    private int id;
    private String name;
}
