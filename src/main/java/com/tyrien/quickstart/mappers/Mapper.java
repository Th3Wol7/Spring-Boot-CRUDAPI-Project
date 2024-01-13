package com.tyrien.quickstart.mappers;
/*This class encapsulate all the logic for mapping in our application*/

public interface Mapper<A, B> {
    B mapTo(A a);

    A mapFrom(B b);
}
