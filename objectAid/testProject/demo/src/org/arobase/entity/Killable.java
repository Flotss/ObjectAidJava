package org.arobase.entity;

/****************************************************
 *    Copyright (c) 2023 — Arobase Team
 *<p>
 *    This project may be licensed somewhere,
 *    you may want to check the license file in the GitHub root repository.
 *</p>
 *    All rights reserved to the Arobase team members.
 ****************************************************/

public interface Killable<TKiller> {

    void onKill(final TKiller killer);

    void kill(final EntityBase victim);
}
