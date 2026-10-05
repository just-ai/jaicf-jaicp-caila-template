# Akane II — Enemy Design

Companion to [akane-ii-design.md](akane-ii-design.md) (§6 Enemies, §5 Wave System).

> All numbers (times, ranges, budgets) are **starting values for tuning**, marked **[TBD]** only where a decision is open. Names are working titles.

## Contents

1. [Shared rules](#1-shared-rules)
2. [Group AI: roles, tokens and flanking](#2-group-ai-roles-tokens-and-flanking)
3. [Roster](#3-roster)
4. [Modifiers](#4-modifiers)
5. [Wave composition](#5-wave-composition)
6. [Elite events (waves 5, 15, 25...)](#6-elite-events-waves-5-15-25)

---

## 1. Shared rules

### 1.1 Telegraph tiers

Every attack that can kill Akane uses one of four fixed telegraph tiers. The **tier sets the minimum warning time**, so players learn one set of timings for the whole roster. Modifiers and buffs never shorten a telegraph (see §4).

| Tier | Warning time | Used for |
|---|---|---|
| **Quick** | 0.35 s | Low-commitment attacks (grunt slashes, skirmisher cuts, snap thrusts) |
| **Standard** | 0.55 s | Most attacks (shots, dashes, thrusts, bombs) |
| **Heavy** | 0.80 s | Area attacks and slams (Tank slam, Archer volley, Hexer zones, Zipline Raider drops) |
| **Long** | 1.00 s | Lock-on attacks and ambushes (Sniper, Phantom) |

Every telegraph has **three signals** at once: a **visual** (an ink line, ring or flare in the reserved accent color), an **audio** cue unique to the enemy type, and a **pose** (the enemy's wind-up animation). This lets players read an attack with the screen off or off-center.

### 1.2 What every enemy has

- **Role and silhouette:** one clear job and a readable outline.
- **One-hit kill,** except the armored types, each with a defined counter.
- **Counter-play:** at least two answers using different tools (sword, gun, gadget, dash, Ink Step or shield).
- **Weak window:** a recovery after attacking in which the enemy can always be punished.
- **Shield rules:** whether it can be grabbed (see §3).
- **Stuck handling:** the shared failsafes in §2.4.
- **Head anchor and kill reactions:** every enemy has a head anchor for gun headshots and a small set of kill reactions (see the [Visual Briefs](akane-ii-visual-briefs.md) §10).

### 1.3 Budget costs and unlock waves

Each enemy has a **budget cost** for wave composition (see §5), and an **unlock wave** that replaces the schedule in the main doc.

| # | Enemy | Cost | Unlock wave | Set |
|---|---|---|---|---|
| 1 | Yakuza Guy | 1 | 1 | Early |
| 2 | Shooter | 3 | 2 | Early |
| 3 | Skirmisher | 3 | 3 | Early |
| 4 | Tank | 5 | 4 | Early |
| 5 | Lancer | 3 | 6 | Early |
| 6 | Archer | 3 | 7 | Early |
| 7 | Shieldbearer | 4 | 8 | Early |
| 8 | Cyber Ninja | 4 | 9 | Early |
| 9 | Bomber | 4 | 12 | Trickle |
| 10 | Zipline Raider | 4 | 14 | Trickle |
| 11 | Banner Caller | 5 | 17 | Trickle |
| 12 | Hexer | 5 | 21 | Trickle |
| 13 | Drone Handler | 6 | 23 | Trickle |
| 14 | Phantom | 5 | 27 | Trickle |
| 15 | Duelist | 7 | 32 | Trickle |
| 16 | Sniper | 6 | 38 | Trickle |

**Change from the main doc:** introducing eight types in five waves was too fast to teach. The early eight now arrive over waves 1–9 (about one new type per wave) and the trickle eight arrive over waves 12–38. Boss waves (10, 20, 30) and elite events (5, 15, 25) never introduce a new type.

---

## 2. Group AI: roles, tokens and flanking

### 2.1 Roles

Each enemy fills one role in a group. Roles are assigned by the group director, not by the individual enemy.

| Role | What it does | Typical enemies |
|---|---|---|
| **Anchor** | Engages from the front and draws attention | Yakuza Guy, Tank, Shieldbearer |
| **Pincer** | Takes a side or rear route to attack from another angle | Skirmisher, Yakuza Guy (when the group is 4+), Cyber Ninja |
| **Suppressor** | Pressures from range and restricts positions | Shooter, Archer, Sniper, Bomber |
| **Support** | Buffs or controls, and stays behind the line | Banner Caller, Hexer, Drone Handler |
| **Special** | Arrives by an unusual route | Zipline Raider, Phantom, Duelist |

A healthy group has at least one Anchor, so that Pincers and Suppressors have something to work around.

### 2.2 Attack tokens

Only a limited number of enemies may **commit** to an attack at once. An enemy asks the director for a token before it starts a telegraph.

| Token | Used by | Starting limit **[TBD]** |
|---|---|---|
| **Melee token** | Any melee attack | 2 in waves 1–10, rising to 4 by wave 40 |
| **Ranged token** | Shots, arrows, bombs, drones | 2 in waves 1–10, rising to 4 by wave 40 |
| **Heavy token** | Heavy and Long tier attacks | 1 at a time at all waves |

- A token is held from the start of the telegraph until the end of the recovery.
- **Stagger rule:** two telegraphs may not land within 0.25 s of each other unless they are from different directions, so overlapping attacks stay dodgeable.
- Enemies without a token circle, reposition or flank, never idle in place.
- Bosses use their own tokens and do not take from the wave pool.

### 2.3 Flanking

- **Trigger:** the director assigns a Pincer when the group has three or more enemies, or when Akane has been stationary for about 2 seconds.
- **Route choice:** a Pincer takes a path that differs from the direct path by at least 40%, using the map's alternate routes (side streets, ledges, vents), and avoids Akane's current line of sight where it can.
- **Timing:** a Pincer waits at a staging point until the Anchor engages, then closes in, so flanks arrive together.
- **Rear cue:** when an enemy is within about 6 tiles behind Akane, a **footstep audio cue** plays and a **screen-edge ink smear** appears (see the UI section of the run and narrative document). Flanking only works if it is perceivable.

### 2.4 Reliability failsafes (all enemies)

These replace the original game's bugs (enemies forgetting to attack or getting stuck).

| Problem | Failsafe |
|---|---|
| No progress for 3 s (blocked path) | Re-path. If it fails again, take the nearest valid node |
| No attack for 6 s while within range and visible | **Forced re-engagement:** the enemy requests a token with priority |
| Stuck for 8 s | Despawn out of sight, respawn at the nearest spawn point and re-enter |
| No valid attack position | Fall back to circling and re-evaluate every 1 s |
| Token never granted for 10 s | Escalate priority, then rotate another enemy out |
| Player stationary and out of reach | Suppressors target, and Pincers go for the flank |

Every enemy state has an exit. There are no dead ends.

---

## 3. Roster

Each entry lists: role, look, attack and telegraph, counters, weakness, AI behavior, shield rule, named elite and notes.

### 3.1 Yakuza Guy *(original)*

- **Role:** Anchor / fodder. **Cost 1. Wave 1.**
- **Look:** a footsoldier in a suit and sunglasses, with a short blade or baton.
- **Attack:** an overhead slash. **Quick** telegraph (the weapon raised, a short white flare).
- **Counters:** any sword hit. A dash through them. Ink Step to counter.
- **Weakness:** a long recovery (0.7 s) after a miss.
- **AI:** approaches in a loose group. In groups of four or more, two become Pincers. Low priority for tokens, so they feel like a crowd.
- **Shield:** can be grabbed. The default human shield.
- **Elite: Enforcer.** A two-hit combo, with a delayed second swing (0.5 s after the first). Teaches the second Ink Step window.

### 3.2 Shooter *(original)*

- **Role:** Suppressor. **Cost 3. Wave 2.**
- **Look:** a cybernetic sharpshooter with an eye scope and a rifle arm.
- **Attack:** an aimed shot. **Standard** telegraph: a thin white-hot laser line tracks Akane, then **locks** for the last 0.2 s and fires along the locked line. In the original, Shooters never missed, so lock-on is the telegraph: the line is guaranteed to hit if Akane is still on it at the end.
- **Counters:** step off the line before it locks. **Deflect** the shot with the sword. Break line of sight. Human shield.
- **Weakness:** after firing, a 0.8 s reload. Low mobility while aiming.
- **AI:** keeps 8–12 tiles away and prefers cover. Repositions after every shot. Won't fire into a human shield and instead repositions to flank.
- **Shield:** can be grabbed.
- **Elite: Marksman.** Fires two staggered shots (0.25 s apart) on one lock. Teaches double deflecting.

### 3.3 Skirmisher

- **Role:** Pincer. **Cost 3. Wave 3.**
- **Look:** a lean, fast figure in light armor with twin short blades. Moves with a quick, curved stride.
- **Attack:** a hit-and-run cut. **Quick** telegraph: the blades flick and a short white arc shows the cut direction.
- **Counters:** a quick turn and a sweep. Dash away. The gun at close range.
- **Weakness:** it rarely commits, so it dies to any hit. Passing through Akane's rear leaves a 0.5 s opening.
- **AI:** always tries to reach Akane's rear using a side route. Works in pairs, with one drawing attention and the other going around. Audible footsteps.
- **Shield:** can be grabbed.
- **Elite: Razor Skirmisher.** Throws caltrops as it passes, creating a ground hazard for 4 s.

### 3.4 Tank *(original)*

- **Role:** Anchor, armored. **Cost 5. Wave 4.**
- **Look:** a bulky enforcer in heavy plating, with a visible rear plate.
- **Attack:** a ground slam in front. **Heavy** telegraph: a white-hot ink fan on the ground.
- **Counters:** the original needed more than one slash. Here, the Tank dies to **one hit from behind** (rear plate), **two sword hits** from the front, a **Magnum** shot, a **Dragon Slash**, or **one hit after an EMP** strips the plating.
- **Weakness:** slow turning (about 0.6 s), so staying behind it works. 1.0 s of recovery after a slam.
- **AI:** advances steadily and ignores flanking positions. Its turn speed is the exploit.
- **Shield:** can't be grabbed.
- **Elite: Siege Tank.** Adds a straight-line charge (**Heavy** telegraph) that crashes into walls and stuns itself.

### 3.5 Lancer

- **Role:** Anchor, long reach. **Cost 3. Wave 6.**
- **Look:** a tall figure with a long spear, a white sash and a narrow stance.
- **Attack:** a long thrust (about 3 tiles). **Standard** telegraph: a thin line pointing along the thrust. **Snap thrust:** if Akane dashes toward it, it uses a **Quick** thrust, which is why careless dashes get punished.
- **Counters:** Ink Step inside the spear point. Dash sideways. Deflect the spear. Keep a gun shot ready.
- **Weakness:** after a thrust, the spear is extended for 0.7 s, so stepping in is safe.
- **AI:** holds mid-range and spaces itself from Akane. Prefers a melee token.
- **Shield:** can be grabbed.
- **Elite: Naginata Master.** A sweeping 180° arc with a **Heavy** telegraph, usable as a bait to jump over with the right boots.

### 3.6 Archer

- **Role:** Suppressor, high ground. **Cost 3. Wave 7.**
- **Look:** a figure on a rooftop or ledge with a long bow and a hooded cloak.
- **Attack:** an arrow volley. **Heavy** telegraph: a long arc line drawn to the landing point, with a visible flight time (0.4 s).
- **Counters:** move out of the arc. Deflect arrows. Use overhangs as cover. Close in by zipline or grapple.
- **Weakness:** almost no mobility. A 1.2 s reload after two volleys.
- **AI:** picks **perch nodes** on the map and fires from there. Relocates after two volleys, or if Akane gets within 5 tiles.
- **Shield:** can be grabbed if Akane reaches it.
- **Elite: Fire Archer.** Each arrow leaves a burning zone for 4 s.

### 3.7 Shieldbearer

- **Role:** Anchor, armored. **Cost 4. Wave 8.**
- **Look:** a broad figure with a large ink-black riot shield.
- **Attack:** a shield bash that pushes. **Standard** telegraph: the shield pulls back with a white flare.
- **Counters:** the front is protected from sword and bullets. Attack from the **side or back,** use a **Magnum** (pierces), the **Gravitational Beam** to pull it aside, or throw a **human shield** into it.
- **Weakness:** the bash leaves it open for 0.6 s, and the shield turns slowly.
- **AI:** advances with the shield up and protects Shooters and Archers behind it (a "cover" behavior).
- **Shield:** can be grabbed from behind only.
- **Elite: Riot Guard.** The shield discharges on bash, creating a short shock zone (**Heavy** telegraph).

### 3.8 Cyber Ninja *(original)*

- **Role:** Pincer, dasher. **Cost 4. Wave 9.**
- **Look:** a slim cybernetic figure with a glowing seam along the body and a long blade.
- **Attack:** a dash attack. **Standard** telegraph: a white-hot line along the path, then the dash. In the original, its defense was strong, so a front sword hit is **deflected** while it is guarding.
- **Counters:** a hit from the **side or back,** a hit during its **0.6 s recovery,** an **EMP** (which stops it), or a perfect Ink Step through the dash.
- **Weakness:** the recovery after a dash.
- **AI:** dashes in from the side, outside the camera's focus. Cooldown of 3 s between dashes.
- **Shield:** can't be grabbed while guarding. Can be grabbed during recovery.
- **Elite: Shadow Ninja.** A double dash, where the second dash re-aims after the first lands.

### 3.9 Bomber

- **Role:** Suppressor, area denial. **Cost 4. Wave 12.**
- **Look:** a hunched figure with a bandolier of glowing charges.
- **Attack:** lobs a delayed charge near Akane. **Standard** telegraph: a ring on the ground for 1.5 s, then the explosion.
- **Counters:** leave the ring. **Slash the charge back** at the Bomber (a sword hit returns it). Kill the Bomber to set off its pack and chain-explode neighbors.
- **Weakness:** low mobility and a volatile pack.
- **AI:** keeps 7–10 tiles away and leads its throws toward where Akane is heading. Prefers a ranged token.
- **Shield:** can be grabbed. A grabbed Bomber is a dangerous shield (its pack is volatile).
- **Elite: Demolition Bomber.** A cluster charge that splits into three smaller rings.

### 3.10 Zipline Raider

- **Role:** Special, aerial. **Cost 4. Wave 14.**
- **Look:** a figure on a hook-and-wire harness, carrying a short blade.
- **Attack:** a drop attack at the end of a zipline ride. **Heavy** telegraph: a shimmer on the wire and a whistling sound for 0.8 s, then a landing circle.
- **Counters:** step out of the landing circle. **Cut the zipline** with a sword hit on the anchor or shoot the Raider mid-ride (an easy target). Grapple up to intercept.
- **Weakness:** vulnerable while riding. 0.5 s of recovery after landing.
- **AI:** spawns at high anchors and rides the zipline closest to Akane. If no zipline is within reach, it climbs or runs as a plain melee enemy.
- **Shield:** can be grabbed after landing.
- **Elite: Rope Rider.** Arrives with a second Raider on a linked zipline, so two drops land in sequence.

### 3.11 Banner Caller

- **Role:** Support. **Cost 5. Wave 17.**
- **Look:** a figure carrying a tall banner with an ink-black emblem.
- **Attack:** none directly. A banner aura (5-tile radius) gives nearby enemies **+30% move speed** and **faster token cooldowns.** It never shortens telegraphs.
- **Counters:** a priority target. Kill it and the buff ends.
- **Weakness:** frail and slow.
- **AI:** stays 6–9 tiles behind the front line, keeps allies within its radius, and flees if Akane gets within 4 tiles.
- **Shield:** can be grabbed (and makes a flag-bearing shield).
- **Elite: Standard Bearer.** Also gives nearby enemies a one-hit **shield pop** (they ignore the first hit).

### 3.12 Hexer

- **Role:** Support, control. **Cost 5. Wave 21.**
- **Look:** a figure with a brush-like staff and a white mask.
- **Attack:** places an **ink zone** that slows Akane by 40% and blocks the dash. **Heavy** telegraph: the zone expands over 0.8 s, and lasts 6 s.
- **Counters:** avoid the zone. Kill the Hexer and its zones vanish. A **Hologram Decoy** draws its placement.
- **Weakness:** frail, with long casting recovery (1.0 s).
- **AI:** places zones in pairs to cut off Akane's routes, ideally while an Archer or Shooter has her pinned.
- **Shield:** can be grabbed.
- **Elite: Warlock.** Its zones also fire needles, with the same **Standard** telegraph.

### 3.13 Drone Handler

- **Role:** Support, summoner. **Cost 6. Wave 23.**
- **Look:** a figure with a wrist controller and three small drones that orbit.
- **Attack:** the drones attack one at a time. **Standard** telegraph on the drone (a white flash and a whine), then a dive.
- **Counters:** kill a drone in one hit (they are fragile). Kill the **Handler** and every drone falls. An **EMP** kills all drones at once.
- **Weakness:** the Handler is exposed and slow. The drones recover 0.4 s after a dive.
- **AI:** the Handler stays back and the drones orbit at about 3 tiles. Only one drone attacks at a time, using a ranged token.
- **Shield:** the Handler can be grabbed.
- **Elite: Swarm Master.** Controls five drones, and two can attack at once from different angles.

### 3.14 Phantom

- **Role:** Special, ambusher. **Cost 5. Wave 27.**
- **Look:** a cloaked figure with a shimmer around the body.
- **Attack:** an ambush strike from a hidden spot. **Long** telegraph: a shimmer, a whisper and a faint pulse for 1.0 s, then a short lunge.
- **Counters:** listen for the whisper. Move off the line. **EMP** reveals it. A perfect Ink Step punishes the lunge.
- **Weakness:** after the lunge, 0.8 s of recovery before it repositions.
- **AI:** uses **ambush nodes** in the Hidden Network, vents and signage. It strikes, then relocates to a new node.
- **Shield:** can't be grabbed while cloaked. Can be after a lunge.
- **Elite: Wraith.** Leaves a decoy after relocating. The decoy lunges too, with the same telegraph.

### 3.15 Duelist

- **Role:** Special, elite melee. **Cost 7. Wave 32.**
- **Look:** a poised figure in a long coat with a straight blade, in a ready stance.
- **Attack:** stays in a **counter stance** for 3 s. If Akane enters its range, it **parries** and answers with a riposte (**Standard** telegraph). Between stances it can lunge (**Standard**).
- **Counters:** wait for it to drop its stance. Use the gun or a Dragon Slash. **Perfect Ink Step** through the riposte opens it for a hit.
- **Weakness:** the end of the stance (3 s), and the recovery after a riposte (0.8 s).
- **AI:** reads Akane's swings, and never reacts to inputs faster than a human could. It reacts to the *start* of an approach, not button presses. Holds its ground and expects Akane to come to it.
- **Shield:** can't be grabbed while in stance.
- **Elite: Master Duelist.** Chains two ripostes in a row, with the second delayed.

### 3.16 Sniper

- **Role:** Suppressor, long range. **Cost 6. Wave 38.**
- **Look:** a figure with a long rifle and a laser scope, on a high perch.
- **Attack:** one lock-on shot. **Long** telegraph: a visible laser line tracks Akane for 1.0 s, then locks for 0.3 s and fires.
- **Counters:** break the line with cover, a **Sumi Bomb,** a human shield, or a deflect (difficult). Move off the line before it locks.
- **Weakness:** a 6 s cooldown between shots, and slow relocation.
- **AI:** picks the highest perch node with a clear line. Uses a heavy token.
- **Shield:** can be grabbed if Akane reaches it.
- **Elite: Twin Sniper.** Two crossing laser lines, with the same lock timing, so a path is left between them.

---

## 4. Modifiers

Generic modifiers can be applied to any enemy. They are used in elite events and in late waves.

| Modifier | Effect | Visual | Rules |
|---|---|---|---|
| **Hasted** | +25% move speed | A speed-streak trail | Telegraphs unchanged |
| **Armored** | Takes one extra hit | An ink plate on the body | Not on Tanks or Shieldbearers |
| **Volatile** | Small explosion on death | A glowing core | Harms nearby enemies as well |
| **Shrouded** | Partly cloaked | A shimmer | Only on enemies without Long telegraphs |
| **Vengeful** | On death, hastes a nearby enemy | A hatched ink brand | Never stacks on one target |

**Rules**

- A modifier **never shortens a telegraph, hides one, or removes a weak window.**
- Maximum **2 modifiers** per enemy, and never on bosses.
- Modified enemies cost **+1 to +2** budget.
- Named elites cost **+2** budget over the base type.

---

## 5. Wave composition

### 5.1 Budget

- **Wave budget:** `8 + 3 x wave` points **[TBD: tune]** (so wave 1 is 11, wave 10 is 38 and wave 30 is 98). Boss waves use a different set (the boss plus a small escort budget).
- A wave is filled from enemies whose unlock wave has passed, up to the budget, using the templates below.

### 5.2 Rules

- **Spotlight:** a newly unlocked type appears in its unlock wave in small numbers and again in the next two waves, so players meet it before it is mixed in.
- **Type limit:** at most 3 enemy types per wave before wave 20, and 5 afterwards.
- **Role balance:** every wave includes at least one Anchor and at least one Suppressor or Pincer after wave 3.
- **No-repeat guard:** the same composition template is not used twice in a row.
- **Spawns** follow §5.2 of the main doc (out of sight, weighted by zone).

### 5.3 Templates

| Band | Waves | Template idea |
|---|---|---|
| **Intro** | 1–9 | A new type plus Yakuza fodder. Teaches one thing per wave |
| **Mix** | 10–25 | Anchor + Suppressor + Pincer groups. Pairings (Shieldbearer + Shooter, Skirmisher + Archer) |
| **Pressure** | 26–40 | Large mixed groups, Support enemies, named elites in the budget |
| **Endless** | 41+ | Full roster, modifiers on a growing share of enemies, tokens at their maximum |

### 5.4 Enemy pairings to design for

| Pair | Why it works |
|---|---|
| Shieldbearer + Shooter | The shield covers the shooter, so the player must flank or pierce |
| Banner Caller + Skirmishers | Fast flankers, with an obvious priority target |
| Hexer + Archer | The zones pin Akane under volleys |
| Drone Handler + Yakuza crowd | Drones pick off the player during crowd fights |
| Sniper + Phantom | Long and short lines of danger from different distances |
| Duelist + anything | The Duelist's stance controls where the player can go |

---

## 6. Elite events (waves 5, 15, 25...)

An **elite event** replaces the normal wave at 5, 15, 25 and so on. It is short, has a theme, and gives a score bonus (see the run and scoring document).

| Event | Theme | Composition |
|---|---|---|
| **Gold Coat Patrol** | Named elites | 3 named elites (from unlocked types) + escorts. **Always the wave 5 event.** |
| **Ambush** | Attacks from behind | Skirmishers and Phantoms from the Hidden Network. Rear cues are essential |
| **Barrage** | Ranged pressure | Shooters, Archers and Shieldbearer cover. Tests deflecting |
| **Swarm** | Numbers | Many Yakuza Guys and a Drone Handler. A natural Dragon Slayer moment |
| **Gauntlet** | Modifiers | Standard enemies with Hasted, Armored or Volatile modifiers |
| **Highwire** | From above | Zipline Raiders and Archers on high perches |

**Rules**

- Events draw from the pool **without repeating until all have been seen** (only from events whose enemies are unlocked).
- An event never includes enemies that have not been unlocked.
- Event waves have a **normal pressure timer** (see §5.1 of the main doc), and clearing early gives a bonus.
- After wave 25, events may combine two themes.
- Events end with the usual breather.
