package com.toyo.instantforum.web.controller.data;

import java.util.ArrayList;
import java.util.Collection;

import com.toyo.instantforum.data.model.Question;

public class Questions extends ArrayList<Question> {

    private static final long serialVersionUID = 5563490889240268252L;

    public Questions() {
	super();
    }

    public Questions(Collection<? extends Question> c) {
	super(c);
    }

    public Questions(int initialCapacity) {
	super(initialCapacity);
    }

}
