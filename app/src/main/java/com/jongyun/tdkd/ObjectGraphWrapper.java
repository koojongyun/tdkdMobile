package com.jongyun.tdkd;

import dagger.ObjectGraph;

public class ObjectGraphWrapper {
    private static ObjectGraph objectGraph;

    public static void createObjectGraph(Object module) {
        objectGraph = ObjectGraph.create(module);
    }

    public static void inject(Object object) {
        objectGraph.inject(object);
    }
}
