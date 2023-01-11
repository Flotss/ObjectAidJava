package org.arobase;

import org.arobase.entity.Character;

/****************************************************
 *    Copyright (c) 2023 — Arobase Team
 *<p>
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *</p>
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public class Main {

    public static void main(String[] args) {
        final var vincent = new Character("Vincent Thomas", "Guerrier");
        final var ogre = new Character("Ogre", "Je suis un ogre");

        vincent.kill(ogre);
    }

}
