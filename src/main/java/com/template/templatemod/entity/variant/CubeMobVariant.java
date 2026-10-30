package com.template.templatemod.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum CubeMobVariant {
    DEFAULT(0),
    RED(1);

    private static final CubeMobVariant[] BY_ID = Arrays.stream(CubeMobVariant.values()).sorted(Comparator.comparingInt(
            CubeMobVariant::getID)).toArray(CubeMobVariant[]::new);

    private final int id;

    CubeMobVariant(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public static CubeMobVariant getById(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
