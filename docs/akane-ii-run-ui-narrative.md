# Akane II — Run Structure, Scoring, Onboarding, HUD and Narrative

Companion to [akane-ii-design.md](akane-ii-design.md) (§5, §8, §9, §10).

> All numbers are starting values for tuning. Narrative text is **draft copy** and follows the light-continuity approach in the main doc (§9). Original-game facts used here are unverified (see Appendix B of the main doc).

## Contents

1. [Run structure](#1-run-structure)
2. [Scoring](#2-scoring)
3. [Difficulty: Trials](#3-difficulty-trials)
4. [Onboarding](#4-onboarding)
5. [HUD and UI](#5-hud-and-ui)
6. [Narrative content](#6-narrative-content)

---

## 1. Run structure

### 1.1 Menu

The main menu offers: **Arcade, Tutorial, Armory (loadout and unlock progress), Codex, Leaderboards, Options.** **Boss Rush** and **Time Attack** appear after the player defeats Katsuro once (see the story and modes document). The player can go straight to Arcade without touching anything else.

### 1.2 The run loop

1. **Loadout.** The last-used loadout is selected by default. One button changes it (the Armory view). The gadget slot shows a **mod chip** when the equipped gadget has a mod (see the loadout document §3.2). Challenge progress shows next to each locked item.
2. **Start.** Akane begins at the center of the Neon Plaza. The first wave starts after a 3-second beat.
3. **Waves.** Waves advance by the hybrid rule (clear or pressure timer). Each wave is followed by a **breather** of about 5 seconds.
4. **Pickups** appear during breathers: **ammo caches** and **gadget charge shards** that refill active gadgets. There are no health pickups or power-ups (see main doc §8).
5. **Boss waves** (every 10) and **elite events** (waves 5, 15, 25...) interrupt the normal flow.
6. **Death.** One hit ends the run. A short ink death sequence plays (at most 1.5 s).
7. **Run summary** (skippable with one button).
8. **Restart.** One button starts a new run with the same loadout within about 2 seconds. A second button opens the Armory.

### 1.3 Run summary

Shows: **final score,** waves reached, kills, best Flow tier, longest combo, bosses defeated, secrets found, new unlocks and challenge progress, and **personal bests.** It is compact and skippable, so the restart loop stays fast.

### 1.4 Rules

- There is no mid-run save, because runs are arcade-style. Suspending on Switch simply pauses the run **[TBD: confirm platform behavior]**.
- Runs are not seeded for sharing at launch (out of scope).
- Quitting a run mid-way ends it and records the score.

---

## 2. Scoring

### 2.1 Formula

```
Run score = sum over events of ( base points x Flow multiplier ) + flat bonuses
```

**Base points per kill** = `50 x enemy budget cost` (see the enemies document), so a Yakuza Guy gives 50 and a Duelist gives 350. Named elites use their base cost +2. Bosses use their own values.

**Flow multiplier** (see main doc §3.8):

| Flow tier | Multiplier **[TBD]** |
|---|---|
| None | x1.0 |
| Flow I | x1.5 |
| Flow II | x2.0 |
| Flow III | x3.0 |

### 2.2 Style bonuses (flat, not multiplied)

| Action | Bonus **[TBD]** |
|---|---|
| Perfect Ink Step | +150 |
| Deflect kill | +100 |
| Human shield kill | +100 |
| Thrown shield, per extra enemy beyond the first | +50 |
| Zipline kill | +100 |
| Kill from behind | +50 |
| Dragon Slayer, per enemy beyond the fifth | +25 |
| Boss phase break | +300 |

**Variety rule:** repeating the same style action within 5 seconds gives half the bonus each time, so players are rewarded for mixing tools and not spamming one.

### 2.3 Wave and event bonuses

| Event | Bonus **[TBD]** |
|---|---|
| Early clear (before the pressure timer) | `100 x wave x (remaining timer fraction)` |
| Elite event cleared | Score from kills is **x1.5,** plus a flat 500 x event tier |
| Boss defeated | `2,000 x boss number`, with a speed bonus for finishing under par time |
| Secret found | 200-500 (once per run) |

### 2.4 Trial multipliers

Active Trials (see §3) multiply the final run score.

### 2.5 Worked example

A mid-run moment on wave 12: Akane is at Flow II and kills a Shooter (cost 3, base 150) with a deflected shot.

- Base: 150 x 2.0 = **300.**
- Style: deflect kill = **+100** (flat).
- Total for the kill: **400.**

If the same deflect kill happened at no Flow, it would score 150 + 100 = 250. The streak is worth a lot, but a single style action is still worth doing.

### 2.6 Leaderboards

- **Score board** and **Waves reached board.**
- Trial runs carry a **tag** and are listed on filtered boards, so the main boards remain comparable.
- Runs that use **assist options** (see main doc §10.3) are kept off the main boards.
- The boards are not split by input device.

---

## 3. Difficulty: Trials

**One difficulty curve,** designed to be fair. After the player defeats **Katsuro once,** they unlock **Trials**: optional challenge modifiers they can enable before a run, for a score multiplier and a leaderboard tag.

| Trial | Effect | Multiplier **[TBD]** |
|---|---|---|
| **Rush Hour** | Enemies move 20% faster, and the pressure timer is 15% shorter | x1.3 |
| **Dry Chamber** | The gun starts with 3 rounds, and sword kills refill half as much | x1.25 |
| **Glass Edge** | The Ink Step window is 30% smaller | x1.3 |
| **Full House** | The wave budget is 30% larger | x1.4 |
| **Blind Ink** | A smaller vision radius with fog at the edges (telegraphs stay visible) | x1.2 |
| **Bare Hands** | No gadget allowed | x1.15 |

**Rules**

- Up to three Trials at once. The multipliers combine, capped at **x3.0.**
- Trials never change telegraph timings or hide telegraphs.
- Assist options and Trials can't be used together.
- Trials do not affect unlock challenges, except where a challenge says so.

---

## 4. Onboarding

**Approach (as in the original):** a **separate, optional tutorial** available from the main menu, and arcade mode that can be started **cold.** Nothing is hidden, and nothing is forced.

### 4.1 Tutorial ("Dojo")

An optional, menu-accessible set of short lessons. Each is skippable and replayable.

**Framing (decided):** the original's optional tutorial was a flashback, set about 23 years before the main game, with Akane as a child training under Ishikawa. Akane II's Dojo keeps that framing: the lessons are memories of the dojo, each opened by one line of text from Ishikawa (see the copy deck §9). The practice range is a neutral sandbox with no story text.

| Lesson | Teaches |
|---|---|
| Movement | Moving, jumping, dashing and charges |
| Sword | Slashing, combos and deflecting |
| Gun | Aiming, ammo and the refill rule |
| Ink Step | The precise dodge, its window and its reward |
| Human shield | Grabbing, hit limit, throwing |
| Flow | The combo, the tiers and the decay |
| Specials | Dragon Slash and Dragon Slayer |
| Traversal | Ziplines, launch points and climbing |
| Gadgets | Trying any gadget on a dummy |

After the lessons, the Dojo includes a **practice range:** a sandbox where the player picks any unlocked item, any enemy and any unlocked boss rehearsal (a dummy-based version of each boss's moves), at any time.

### 4.2 Arcade onboarding (when starting cold)

- **First-encounter cards.** The first time an enemy type appears, a small card shows its name, a one-line hint and its telegraph color. Cards can be turned off in options.
- **Teaching waves.** Waves 1-9 introduce one new enemy per wave (see the enemies document), so the game teaches itself.
- **First boss prompt.** Before the first boss, a short line states the phase rule: "Land one clean hit in the opening to end a phase."
- **Contextual hints** appear once for key mechanics (e.g., Ink Step after the first Shooter, human shield when surrounded), and fade quickly. They can be turned off.

### 4.3 Gadget and item teaching

- Each item's loadout card shows a **short description, strength, weakness and one tip.**
- Unlocking an item opens a **mini-demo** in the Armory.

---

## 5. HUD and UI

**Style:** a **minimal brush-drawn HUD.** Small ink-stroke elements at the edges of the screen, hiding when not needed, so the combat plane stays clear.

### 5.1 Elements

| Element | Location | Behavior |
|---|---|---|
| **Dash charges** | Near Akane (two ink dots under her feet) | Always visible while recharging; fade when full |
| **Flow tier and aura** | Around Akane, plus a small pip row at the top left | The aura grows with the tier. A pulse warns before a tier drops |
| **Special meters** (Dragon Slash, Dragon Slayer) | Left edge, two thin vertical ink strips | Glow when ready |
| **Ammo** | Bottom right, as ink drops | Shows the remaining rounds or charges |
| **Gadget** | Bottom right, beside the ammo | The icon and cooldown ring |
| **Score and combo** | Top right | Small. The combo number appears only while a combo is active |
| **Wave and pressure timer** | Top center | A brush-stroke bar that shrinks. Hidden on boss waves |
| **Boss phase pips** | Top center (replaces the timer) | Pips for the remaining phases |
| **Boss name** | Top center, briefly | At the start of the fight |
| **Off-screen threat indicators** | Screen edges | Ink smears (see 5.2) |

### 5.2 Off-screen threat indicators

- **Rear and flank cue:** an ink smear at the screen edge in the direction of a threat, with a matching audio cue. It appears for enemies about to attack from off-screen or from behind.
- **Types:** melee threats show as short wide smears, ranged threats as thin long ones, and **lock-on** attacks (Sniper) as a pulsing line.
- **Boss cue:** the Hunter and the Kite use a distinct larger smear.
- Smears are subtle enough to stay out of the way, and bright enough to notice.

### 5.3 Rules

- The HUD **auto-hides** elements that have not changed for about 3 seconds, except critical ones (dash charges, Flow, ammo when low).
- HUD scale, opacity and a high-contrast mode are adjustable.
- A **colorblind-safe palette** is available for threat indicators.
- The telegraph accent colors in the world are never affected by the HUD.

### 5.4 Menu UI

Menus use the same brush-drawn identity: ink-stroke selection, a calm layout and quick navigation on gamepad, keyboard and mouse.

---

## 6. Narrative content

All player-facing story and flavor text now lives in the [Copy Deck](akane-ii-copy-deck.md), which is the source of truth. It contains:

- the intro card and the four story beats (with the post-100 rotation),
- boss title cards and codex entries,
- enemy codex entries and first-encounter cards,
- the 12 lore fragments,
- the Dojo Memory script,
- the tutorial (Dojo) lines,
- item flavor lines for all 28 items,
- zone cards, event announcements and death lines,
- character codex entries.

**Tone:** dry noir with a little bite. Akane speaks rarely, in at most about 8 words. The Dojo tutorial is a flashback to the dojo, with one line of text from Ishikawa per lesson.
