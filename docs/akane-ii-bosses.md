# Akane II — Boss Move Lists

Companion to [akane-ii-design.md](akane-ii-design.md) (§7 Bosses). Phase structure, vulnerability windows, boss tiers and evolution tables live in §7.1 and §7.5 of the main doc. This document adds the **move lists**, **intro and kill moments**, and **par times**.

> Times and distances are starting values for tuning. Telegraph tiers (Quick 0.35 s, Standard 0.55 s, Heavy 0.8 s, Long 1.0 s) are defined in [akane-ii-enemies.md](akane-ii-enemies.md). Boss attacks may use longer telegraphs, never shorter.

## Contents

1. [Boss rules](#1-boss-rules)
2. [Par times (for the Flow stall clock)](#2-par-times-for-the-flow-stall-clock)
3. [Katsuro](#3-katsuro)
4. [The Debt Collector](#4-the-debt-collector)
5. [The Hunter](#5-the-hunter)
6. [The Crimson Kite](#6-the-crimson-kite)
7. [The Demolisher](#7-the-demolisher)
8. [The Floodgate Warden](#8-the-floodgate-warden)
9. [Tier 5 and tier 6 moves](#9-tier-5-and-tier-6-moves-wave-150-and-200)
10. [Intro and kill moments](#10-intro-and-kill-moments)

---

## 1. Boss rules

- Every attack has a telegraph (visual, audio and pose) with a fixed duration.
- Each boss has a **move list** per phase. Moves are chosen by a simple pattern director that avoids repeating the same move three times in a row, and always inserts a **recovery beat** between heavy moves.
- **A vulnerability window** opens when a boss commits to a marked move (listed in the "Window" column). Windows last 1.0-1.5 s unless noted.
- A hit that lands in a window ends the **phase.** The boss's next phase starts after a short transition (1.5 s), during which Akane is safe and the boss does not attack.
- Boss attacks do not use enemy attack tokens.

---

## 2. Par times (for the Flow stall clock)

A boss's par time is the **expected time per phase** for a competent first attempt. The Flow stall clock (see main doc §3.8) starts after **par plus a buffer of 50%** with no progress, and every phase break resets it.

| Boss | Phase 1 | Phase 2 | Phase 3 | Notes |
|---|---|---|---|---|
| Katsuro | 25 s | 30 s | 30 s | Phases 2-3 appear as he evolves |
| Debt Collector | 30 s | 35 s | 30 s | |
| Hunter | 45 s | 45 s | 35 s | Longer, because stalking is part of the design |
| Crimson Kite | 35 s | 40 s | 30 s | |
| Demolisher | 40 s | 45 s | 35 s | Includes time to reach the cab |
| Floodgate Warden | 40 s | 40 s | 35 s | Tied to tide cycles |

---

## 3. Katsuro

*Standard Duel. Tests reading dashes and Ink Step. Gains moves by wave tier.*

### 3.1 Move list

| Move | Phase | Telegraph | Description | Counter | Window |
|---|---|---|---|---|---|
| **Blood Line** | 1+ | Standard (0.55 s) | A single dash along a pink line, ending in a slash | Step off the line. **Perfect Ink Step** through it staggers him | The skid after a missed dash (1.0 s) |
| **Return Cut** | 1+ | Standard | A dash, then a reverse dash back along the same line | Ink Step the first, dodge the second | The end of the reverse dash |
| **Crescent Slash** | 1+ | Quick (0.35 s) | A close-range arc slash after a short step | Back off, or deflect | None (a pressure move) |
| **Pistol Volley** | 2+ | Standard | Three shots in a fan, 0.3 s apart, from a distance | Deflect (one at a time), or sidestep | A reload after the third shot (1.0 s) |
| **Quickstep** | 2+ | Quick | A fast sidestep into a slash | Hold ground and deflect | None |
| **Seven Rivers** | 3+ | The full path drawn first (1.2 s), then each dash 0.25 s apart | A **multi-dash:** four dashes along a pre-drawn star-like path, ending in a heavy slash. The path never reaches the arena edge | Stay in the gaps between lines. A perfect Ink Step through any line staggers him | The long recovery after the heavy slash (1.5 s) |
| **Mirror Step** | 4+ | Standard | After a dash, he reappears behind Akane on a previously drawn line | Read the earlier line | After the reappear |

### 3.2 Phase pattern

- **Tier 1:** Phase 1, Duelist (Blood Line, Return Cut, Crescent Slash). Phase 2, Duelist quickened (the same moves, faster).
- **Tier 2** (wave 30+): Phase 1, Duelist. Phase 2, **Gunslinger** (adds Pistol Volley and Quickstep). Phase 3, Duelist quickened.
- **Tier 3** (wave 60+): Phase 1, Duelist. Phase 2, Gunslinger. Phase 3, **Master** (adds Seven Rivers).
- **Tier 4** (wave 100+): the same three phases with Mirror Step, recombined patterns and slightly tighter windows.
- **Tiers 5 and 6** (wave 150+ and 200+): Twin Rivers and Final Draw (see §9).

### 3.3 Design notes

- Every dash line is visible before he moves, so it is always dodgeable.
- Seven Rivers shows its **whole path first,** like a brush drawing, so the player can plan a route through it. This is the signature moment of the fight.

---

## 4. The Debt Collector

*Standard Duel. Tests deflecting and gun reading. Does not evolve.*

### 4.1 Move list

| Move | Phase | Telegraph | Description | Counter | Window |
|---|---|---|---|---|---|
| **Single Payment** | 1+ | Standard | One aimed coin round, flies slowly | **Deflect it back** | A deflected shot that hits him opens the window |
| **Installment Fan** | 1+ | Standard | A fan of five rounds | Weave between them, or deflect two | The cylinder spin after the fan (2.0 s) |
| **Late Fee** | 2+ | Heavy (0.8 s) | Marks floor tiles that explode after a delay | Leave the marked tiles | None |
| **Drone Audit** | 2+ | Standard | Ledger drones fire crossing lines | Destroy drones with a hit, or avoid | The pause when the drones are cut down (1.5 s) |
| **Foreclosure** | 3+ | Heavy | A full-screen barrage in a readable rhythm, in three waves | Move between waves | The pause as his ledger empties (1.5 s) |

### 4.2 Design notes

- A Rebi katana or deflect-focused loadout is rewarded, but dodging remains valid.
- The barrage is built from rhythm, so the player can learn it.

---

## 5. The Hunter

*Roaming. Tests awareness and positioning. Gains moves by wave tier.*

### 5.1 Move list

| Move | Phase | Telegraph | Description | Counter | Window |
|---|---|---|---|---|---|
| **Shadow Lunge** | 1+ | Long (1.0 s) with a shimmer and a whisper from his direction | A lunge from behind | Move off the line, or Ink Step | The recovery pose after a miss (1.0 s) |
| **Cloak Step** | 1+ | None (a reposition) | He moves to a new position, fully cloaked | Listen for the whisper | None |
| **Decoy Echo** | 2+ | Long | Cloaked decoys also lunge | Find the real one, or **EMP** to reveal | A hit on the real one during its recovery |
| **Wire Sweep** | 3+ | Heavy (0.8 s) | A wire whip sweeps a wide arc, cutting routes | Jump or dash through the gap | The end of the sweep (1.0 s) |
| **Wire Trap** *(Tier 2+)* | any | Standard | Strings wire across a zipline or corridor, which triggers when touched | Avoid or cut it | None |
| **Spotter Drones** *(Tier 3+)* | any | Standard | Releases drones that reveal Akane's location | Destroy the drones | None |

### 5.2 Design notes

- He **never enters Shrine Heights,** so the player always has a place where the fight is on more even terms.
- Directional audio is required (see main doc §7.5).
- The pattern director never has him lunge twice within 2 seconds.

---

## 6. The Crimson Kite

*Roaming. Tests vertical movement. Does not evolve.*

### 6.1 Move list

| Move | Phase | Telegraph | Description | Counter | Window |
|---|---|---|---|---|---|
| **Strafing Dive** | 1+ | Heavy (0.8 s): a lime line from the sky | A dive along a marked line, crashing into the roof | Step aside | The **crash stagger** (1.5 s), when the core is exposed |
| **Rotor Sweep** | 1+ | Standard | A low sweep across a rooftop | Jump over, or dash | None |
| **Mine Drop** | 2+ | Standard | Drops mines that explode after 1.5 s | Leave the rings | None |
| **Drone Release** | 2+ | Standard | Releases small drones | Kill them in one hit | The Kite lands to recharge (1.5 s), at close range |
| **Storm Dive** | 3+ | Heavy | Rapid dives across the map in readable lines (three in a row) | Move between lines | The final dive's crash (2.0 s) |

### 6.2 Design notes

- The window is always on the ground (the crash), so players without vertical tools can still win.
- A **Grapple Anchor,** **Updraft Fan** or **Geta Springs** offers an additional angle during the perch.

---

## 7. The Demolisher

*Arena-shifting. Tests route planning. Gains moves by wave tier.*

### 7.1 Move list

| Move | Phase | Telegraph | Description | Counter | Window |
|---|---|---|---|---|---|
| **Wrecking Swing** | 1+ | Heavy (0.8 s): an orange circle and chain creak | A wrecking ball slams a marked area, destroying roof sections | Leave the circle | The ball **embeds** in the roof (1.5 s), exposing the cab |
| **Chain Sweep** | 1+ | Standard | A low horizontal sweep of the chain | Jump or dash | None |
| **Platform Collapse** | 2+ | Heavy | Collapses an entire platform after a warning rumble | Leave the platform | None |
| **Zipline Snap** | 2+ | Standard | Cuts a zipline | Use another route | None |
| **Cab Reload** | 2+ | None | The crane arm lowers to reload | Reach the cab (by zipline) | The reload (2.0 s) |
| **Last Swing** | 3+ | Heavy | Wide swings across what remains of the roof | Time the gaps | The arm sticks after a wide miss (1.5 s) |
| **Grabber Claw** *(Tier 2+)* | 2+ | Standard | A claw pulls a zipline down and drags Akane toward the ball | Cut or dodge the claw | The claw retracting |
| **Scaffold Drop** *(Tier 3+)* | 3+ | Heavy | Drops unstable scaffolding as hazard platforms | Avoid, or use as footing briefly | None |

### 7.2 Design notes

- Persistent damage is the signature, so each destroyed section must keep at least two routes between zones (see the map spec §8).
- The cab is reached by zipline or grapple, never only by one route.

---

## 8. The Floodgate Warden

*Arena-shifting. Tests timing and rhythm. Does not evolve.*

### 8.1 Move list

| Move | Phase | Telegraph | Description | Counter | Window |
|---|---|---|---|---|---|
| **High Tide** | 1+ | Heavy: a bell tone and a teal wave line | The channel floods, and water kills on contact when deep | Take the walkways, slides, or high routes | The **drain** exposes the pump station (2.0 s) |
| **Pump Cannon** | 1+ | Standard | A cannon shot along a marked line | Step aside, or deflect | None |
| **Undertow** | 2+ | Standard | Currents push Akane along the channel | Brace, or use debris | The pause between currents (1.5 s) |
| **Debris Surge** | 2+ | Heavy | Floating debris slams across the channel | Ride or avoid | None |
| **Gate Cycle** | 3+ | Heavy | Gates open and close, reshaping routes. The tide cycles faster | Learn the rhythm | The moment all gates close (1.5 s) |

### 8.2 Design notes

- The Warden is passive by design: the environment provides the pace.
- Every tide state is visible on his chest gauge, so the rhythm is readable.

---

## 9. Tier 5 and tier 6 moves (wave 150+ and 200+)

The three bosses that gain moves by tier (see the main doc §7.1 for the tier table) get one extra move each at **Tier 5** (waves 150-199) and **Tier 6** (waves 200+). Par times increase by 10% per tier from Tier 4. Tiers 5 and 6 are expected to be rare, and exist for the top of the leaderboards.

| Boss | Tier 5 move | Tier 6 move |
|---|---|---|
| **Katsuro** | **Twin Rivers:** two Seven Rivers paths drawn at once, with a shared safe gap | **Final Draw:** the full path is drawn in reverse after the strike, forcing a second read |
| **The Hunter** | **Wire Web:** a net of wires strung across a zone, with a visible gap | **Silent Pair:** a decoy Hunter that lunges in sync with the real one |
| **The Demolisher** | **Double Ball:** two wrecking balls with staggered swings | **Foundation Break:** a floor-wide collapse with a marked safe island |

All new moves follow the usual telegraph rules (Heavy tier or longer), and each opens a normal vulnerability window.

---

## 10. Intro and kill moments

### 10.1 Intro

- The fight starts with a **2-second brush title card:** the boss's name and a one-line epithet. The player is in control the whole time.
- Other enemies clear from the arena (they fade out in ink) before the card appears.
- The boss's theme begins as the card fades.

### 10.2 Kill moment

- The final phase-ending hit triggers a **freeze-frame ink slash** (0.4 s), then the boss dissolves into an ink splash.
- The score tally appears briefly, then the usual breather begins.
- Flow decay is reset (see main doc §3.8).

### 10.3 Boss lines

These are the title-card epithets. The final text, department tags and codex entries are in the [Copy Deck](akane-ii-copy-deck.md) §5.


| Boss | Epithet on the title card |
|---|---|
| Katsuro | *"The one who never stays dead."* |
| The Debt Collector | *"Everything is owed."* |
| The Hunter | *"You will not hear it twice."* |
| The Crimson Kite | *"The sky belongs to the Yakuza."* |
| The Demolisher | *"Nothing stands that he has not marked."* |
| The Floodgate Warden | *"The tide keeps the ledger."* |
