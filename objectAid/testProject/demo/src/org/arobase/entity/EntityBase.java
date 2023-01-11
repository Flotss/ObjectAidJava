package org.arobase.entity;

/****************************************************
 *    Copyright (c) 2023 — Arobase Team
 *<p>
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *</p>
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public abstract class EntityBase {

    private final String name;
    private final String description;

    public EntityBase(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }
}
