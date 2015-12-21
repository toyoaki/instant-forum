package com.toyo.instantforum.web.controller.data;

import java.util.ArrayList;
import java.util.Collection;

import com.toyo.instantforum.data.model.Answer;

public class Answers extends ArrayList<Answer> {

    private static final long serialVersionUID = -1338209281353285275L;

    public Answers() {
	super();
    }

    public Answers(Collection<? extends Answer> c) {
	super(c);
    }

    public Answers(int initialCapacity) {
	super(initialCapacity);
    }

}
