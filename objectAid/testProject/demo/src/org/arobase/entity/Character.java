package org.arobase.entity;

/****************************************************
 *    Copyright (c) 2023 — Arobase Team
 *<p>
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *</p>
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public class Character extends EntityBase implements Killable<Character> {

    public Character(String name, String description) {
        super(name, description);
    }

    @Override
    public void onKill(final Character character) {
        System.out.printf("%s killed %s%n", character.name(), name());
    }

    @Override
    public void kill(EntityBase victim) {
        System.out.printf("%s killed %s%n", name(), victim.name());
    }
}
