# Akane II — Design Document

**Studio:** Ludic Studios
**Protagonist:** Sugahara Akane
**Status:** Draft v1.9 (pre-production)
**Scope:** Design only. No implementation is covered here.

> **Companion documents** (detail for the sections below):
> - [Enemy Design](akane-ii-enemies.md): telegraph tiers, group AI, the 16 enemies, modifiers, wave composition and elite events.
> - [Loadout Detail](akane-ii-loadout.md): katana, gun, gadget and boots stats, unlock challenges and balance checks.
> - [Map Design](akane-ii-map.md): the full map write-up: zones, routes, traversal, spawns and zone heat, hazards, destruction, events, secrets, navigation and boss arenas.
> - [Boss Move Lists](akane-ii-bosses.md): attacks, windows, par times and intro and kill moments.
> - [Run, Scoring, Onboarding, HUD and Narrative](akane-ii-run-ui-narrative.md).
> - [Visual Briefs](akane-ii-visual-briefs.md): sprite scale, color rules, and briefs for Akane, the 16 enemies, the 6 bosses, the zones, effects and kill animations.
> - [Audio Design](akane-ii-audio.md): adaptive music, boss themes, sound effects, vocals, gameplay audio cues and the mix.
> - [Copy Deck](akane-ii-copy-deck.md): the final draft of all player-facing story and flavor text.
> - [Story, Endgame, Modes and Cosmetics](akane-ii-story-and-modes.md): the story premise and cast, milestone beats, Overdrive tiers, Boss Rush and Time Attack, and the cosmetics roster.
>
> Names for enemies, bosses, moves and zones are **working titles**. Items marked **[TBD]** need a decision or input from the team (several depend on the original *Akane*).
>
> Original-game facts were gathered from web search summaries (the Akane fan wiki, store pages and reviews). The wiki pages themselves could not be opened, so item names are reliable but **item behaviors, unlock conditions and the full gadget list are not**. Every remix in §3 is a new design, not a description of the original.

---

## Contents

1. [Art Direction](#1-art-direction)
2. [Vision and Pillars](#2-vision-and-pillars)
3. [Core Combat and Movement](#3-core-combat-and-movement)
   - [Loadout](#36-loadout)
   - [Flow (combo benefits)](#38-flow-combo-benefits)
4. [The Arena Map](#4-the-arena-map)
5. [Wave System](#5-wave-system)
6. [Enemies](#6-enemies)
7. [Bosses](#7-bosses)
8. [Progression and Scoring](#8-progression-and-scoring)
9. [Story and Presentation](#9-story-and-presentation)
10. [Platforms and Input](#10-platforms-and-input)
11. [Risks and Open Questions](#11-risks-and-open-questions)
12. [Post-launch and Scope](#12-post-launch-and-scope)

Appendices: [A. Decisions Log](#appendix-a-decisions-log) · [B. Original-Game Verification Checklist](#appendix-b-original-game-verification-checklist)

---

## 1. Art Direction

A highly unique pixel art style that blends **Japanese ink wash (sumi-e)** with **modern pixel sprite animation**, with some inspiration from *Dead Cells*. Production details (a 640 x 360 base resolution, Akane at about 48 px, hand-drawn sprites with brush-stroke shading, role-based enemy silhouettes, and color rules) are in the [Visual Briefs](akane-ii-visual-briefs.md), including the environment quality bar taken from *Dead Cells* (dynamic lighting with hand-drawn normal maps, gradient-map color grading, layered parallax, fake volumetrics and dense particles).

**Goals**

- Ink wash sensibility: tonal washes, bold brush-stroke silhouettes, deliberate negative space, and color used sparingly as an accent.
- Modern pixel animation: high frame counts, smooth interpolation-feeling motion, strong anticipation and follow-through, and impactful hit-stop and screen response (the *Dead Cells* influence).
- Readability over beauty when the two conflict. A one-hit-kill game is only fair if the player can read every threat at a glance.

**Readability rules (non-negotiable)**

- Enemies and projectiles always sit on a **higher-contrast layer** than the background. Backgrounds use soft, low-contrast washes; threats use hard ink edges.
- **Attack telegraphs** use a consistent visual language across all enemies and bosses (for example, a brush-stroke flare in a reserved accent color before any lethal attack). That accent color is used for nothing else.
- Background parallax and ink-bleed effects are kept out of the combat plane.
- Akane has a distinct silhouette and a reserved palette, visible even among 10+ enemies.

**Decided art rules**

- **Night and rain:** the whole game is one rainy night in 2121. Lighting deepens with the wave band (see the story and modes document), and dawn never arrives. Zone hues and telegraphs must stay readable in every lighting band.
- **Kill animations:** gore is bright red as in the original, with **full gore only.** Sword kills use a procedural slice along the real cut, gun kills are headshots, and kills never lock Akane (no finishers). Full details are in the [Visual Briefs](akane-ii-visual-briefs.md) §10.
- **Ink effects:** *bold on kills, restrained elsewhere.* Kills get a satisfying ink splash. Hits and movement get subtle effects, so the combat plane stays readable in crowded waves and on Switch.
- **Palette:** a **shared ink wash base** for the whole game, with **one accent hue per zone** (for example, neon pink for the Plaza and teal for the Canals). Zone accents must never clash with the reserved telegraph and boss accent colors.
- **Cyberpunk and ink wash together:** the ink wash is **how Akane sees and remembers the city**. Neon and technology show through as bright ink accents. This also connects to the original's flashback ending and gives Ink Step and the ink effects a story reason.

---

## 2. Vision and Pillars

*Akane II* is an arcade action game about fast, lethal, stylish combat. Akane and every enemy die in one hit, so mastery comes from movement, positioning and timing.

**Pillars**

1. **One-hit-kill momentum.** Every fight is fast and decisive. Death is quick, and restarting is quicker.
2. **A map worth mastering.** One large, vertical, multi-path level that rewards exploring and experimenting with routes.
3. **Fair but smart opposition.** Enemies flank, cooperate and adapt, but always telegraph lethal attacks. No bugs, no frozen enemies, no cheap deaths.
4. **Expressive movement.** Dash, precise dodge, traversal and human shields should all feel responsive and give players room to show style.
5. **Ink and motion.** The game should look and feel unlike any other pixel art game.

**What changed from *Akane***

| *Akane* | *Akane II* |
|---|---|
| Single-floor arcade arena | One huge, vertical, multi-path level |
| 4 basic enemy types | ~16 enemy types |
| 1 boss | Multiple bosses across 3 types |
| Enemies could forget to attack or bug out | Robust, readable AI with flanking |
| Flow | The combo grants three tiers of small defensive-leaning bonuses (dash recharge, special meter, Ink Step window and ammo). Falls off one tier at a time |
| Boss combo rules | Decay paused for intros and transitions, a stall clock that slowly decays Flow when a fight stalls, reset on phase breaks, reset on boss death |
| Dash | Dash plus precise dodge plus human shields |

**Not in scope:** a campaign or story mode. It was cut because it did not fit the arcade design. Bosses live inside arcade mode instead.

---

## 3. Core Combat and Movement

### 3.1 Toolkit

Akane keeps the original structure. In the first game, equipment came in five types: **katanas**, **guns**, **gadgets**, **cigarettes** (cosmetic ink styles for her special attacks) and **boots** (which change the dash). *Akane II* keeps all five slots and remixes the contents (see [3.6 Loadout](#36-loadout)).

- **Sword:** primary close-range kill tool. Can also deflect bullets (the original rewarded this with an unlock for deflecting 25 enemies' shots in one run).
- **Gun:** ranged option on a limited ammo budget that sword kills refill.
- **Gadget:** **one** equipped gadget (the original allowed up to two). With a single slot, the gadget is a defining choice for the run, so every gadget must be strong on its own.
- **Specials:** the original's **Dragon Slash** (a dash that kills everything in its path) and **Dragon Slayer** (a screen-clearing attack). They return in §3.7.

### 3.2 Feel and responsiveness

The original's core was strong, so Akane II tunes it rather than redesigns it.

- **Input buffering and cancel windows:** attacks, dashes and dodges can be buffered and cancel out of recovery frames. Inputs should rarely feel dropped.
- **Coyote time and jump forgiveness** for the new vertical map.
- **Hit-stop and screen response** are short, consistent and tunable. Kills feel crunchy without costing momentum.
- **Animation priority:** responsiveness beats animation completeness. Anticipation frames can be skipped by actions the player intentionally cancels into.
- **Target:** input-to-visible-response within ~2 frames at 60 FPS **[TBD: validate in prototyping]**.

### 3.3 Dash (carried over)

A short, fast movement burst. Keeps its role as the player's basic repositioning and gap-closing tool. Limited by **charges**: Akane has **2 dash charges** that recharge over time (about 1.5 seconds each **[TBD: tune]**). A successful Ink Step refunds one charge, which is why precise play lets players chain dashes.

### 3.4 Precise dodge: *Ink Step* (working title)

The new, more precise defensive move that adds depth beyond the dash.

- **How it works:** a tight-window dodge timed against an incoming attack. Success triggers a short slow-motion beat, a brush-stroke afterimage and a reward.
- **Reward:** refunds the dash, and opens a brief counter-attack window or a safe reposition. It never grants invulnerability beyond the dodge itself.
- **Design constraint:** attack timing must be consistent. Every enemy telegraph has a fixed, learnable duration so the window is fair across the roster.
- **Risk:** a failed dodge is a normal hit, which means death. Dash remains the safe option. Ink Step is the high-skill, high-reward one.
- **Direction:** one dodge move whose direction comes from the player's input (the stick or movement keys), including up and down on the vertical map. No separate dodge types to learn.

### 3.5 Human shields

Akane grabs an enemy and uses them as a shield.

- **Grab:** a short-range grab that targets standard-sized enemies. Not usable on bosses or heavy enemy types.
- **Effect:** the held enemy absorbs hits from the front. Enemy projectiles that strike the shield kill it; melee hits from enemies may also hit the shield rather than Akane, depending on enemy type.
- **Hold limit:** the shield absorbs **3 hits, then breaks**. Heavy fire therefore wears it down faster, and there is no timer.
- **Standing still can be punished, softly.** In the original you could stand still, aim and gun down a crowd, but you had to keep moving to keep up the combo. The same holds here: combo scoring decays when Akane stays put (see §8), so a stationary shield works but costs score.
- **Mobility:** Akane moves at reduced speed and cannot dash while holding a shield. She can still attack with the gun or release the shield.
- **Release options:** **throw** (the enemy becomes a projectile that damages others on impact) or **drop**.
- **Abuse prevention:** enemies with area attacks, armored enemies and some special types ignore or break the shield. The grab has a cooldown.
- **Enemy response:** AI treats shield-holding as a state. Ranged enemies reposition for a flank rather than shooting into the shield.
- **Why it works:** it creates a new risk/reward question for crowded fights without breaking the one-hit-kill rhythm.

### 3.6 Loadout

Akane picks one item per slot **before a run**. Every item is a **sidegrade**, never a strict upgrade, and every item has a clear strength and a clear cost. A few items are strange on purpose, but all must be viable for a high score and none may be required.

Original item names that are known are kept where it helps continuity. Their behaviors below are new designs. Full stats, unlock challenges and balance checks are in the [Loadout Detail](akane-ii-loadout.md) document.

#### Katanas (7)

The original had three: the default blade, **Rebi** and **Tadus**.

| Katana | Idea | Strength | Cost |
|---|---|---|---|
| **Kuro** (default) | Balanced blade | Fast, reliable, forgiving. The baseline every other item is tuned against | No special trick |
| **Rebi** | Reflector. Wide deflect arc | Sends bullets back at the shooter. Rewards deflect kills | Slower recovery on a missed swing |
| **Tadus** | Thrown blade | Throw it down a line and recall it, killing both ways. Great for range | Akane is unarmed while it is out |
| **Nodachi** | Oversized blade | Huge reach and a lingering ink arc after each swing | Slow, committed swings |
| **Twin Tantō** | Dual short blades | Very fast chains, hits both sides. Best for crowd density | Very short range |
| **Echo Blade** *(strange)* | Delayed second slash | Each swing is repeated in place about 1 second later, so kills can be set up and traps placed | Needs planning. Weak in panicked fights |
| **Kusarigama** *(strange)* | Chain-sickle | Pulls an enemy into range, or pulls Akane toward a ledge or enemy | Weak up close, aim-dependent |

#### Guns (6)

The original had six: **Patron v26** (starter), **Inquisitor M103**, **Vicious S36**, **Magnum XT5**, a **Double Barrel Shotgun** and the **Gravitational Beam Emitter**. Rule for all guns: **sword kills refill ammo**, so melee and gun feed each other.

| Gun | Idea | Strength | Cost |
|---|---|---|---|
| **Patron v26** (default) | Reliable sidearm | Accurate, forgiving, quick to refill | Single target |
| **Inquisitor M103** | Three-round burst | Reliable on mid-range targets and flyers | Burst delay can miss moving targets |
| **Vicious S36** *(strange)* | Spray SMG with heavy recoil | **Recoil pushes Akane backward**, so firing doubles as a mobility tool | Burns ammo fast, hard to aim |
| **Magnum XT5** | One heavy piercing round | Pierces lines of enemies, shields and some armor | Slow, very limited ammo |
| **Double Barrel** | Close-range cone | Clears a cone of enemies | Knocks Akane back. Short range |
| **Gravitational Beam Emitter** *(strange)* | Pulls enemies into a clump | A setup tool that holds enemies for about 1.5 seconds, for the sword or a Dragon Slash to finish | No direct kill |

#### Gadgets (11)

**One gadget slot**, chosen before a run. The original had **11** gadgets, and we match that count. The five confirmed by search (**Cyber Gloves**, **Stabilizer Bracelet**, **Adrenaline Shot**, **Magnetic Pulse Emitter**, **Katana Gun**) are kept by name, and the other six are new designs. The remaining original gadgets could not be found, so this roster is a full remix.

The roster covers four families, and **every gadget is an active or always-on tool strong enough to carry a run on its own**. Three are deliberate oddballs; the rest are practical.

**Weapon augments (4)**

| Gadget | Idea | Strength | Cost |
|---|---|---|---|
| **Katana Gun** | Gun mounted on the sword | Every swing also fires a shot along the swing arc | Drains ammo twice as fast |
| **Magnetic Pulse Emitter** | Short EMP | Disables cybernetic enemies (Shooters, Cyber Ninjas, Drone Handlers) and strips Tank armor | Short range, slow recharge |
| **Stabilizer Bracelet** | Steady frame | Widens the Ink Step window, refunds both dash charges on a successful Ink Step, and removes gun recoil | Passive. Rewards skill, gives no active power |
| **Adrenaline Shot** | Kill-fueled burst | Kill chains trigger a brief slow-motion burst with faster attacks | Weak when you are not chaining |

**Traversal (2)**

| Gadget | Idea | Strength | Cost |
|---|---|---|---|
| **Grapple Anchor** | Hook launcher | Grapples ledges, zipline anchors and enemies. Fast vertical movement on the new map | Slow to reuse, needs a target |
| **Updraft Fan** *(oddball)* | Deployable wind pad | Places a fan that launches Akane (and thrown items) upward. Lets players **build their own shortcut** on the map | Limited charges, the fan is destructible, and enemies can use it too |

**AI manipulation (3)**

| Gadget | Idea | Strength | Cost |
|---|---|---|---|
| **Hologram Decoy** *(oddball)* | Projects a fake Akane | Enemies target the decoy for a few seconds, which breaks flanking and pulls attackers apart | Long cooldown. Smart enemies (Duelist) are not fooled |
| **Sumi Bomb** | Ink cloud | Breaks enemy line of sight, so enemies lose tracking and bunch up | Also blocks Akane's view |
| **Lure Beacon** | Thrown noise device | Draws enemies to a spot for a few seconds, setting up Dragon Slash lines and Gravitational Beam clumps | Does not affect bosses |

**Human shield synergy (2)**

| Gadget | Idea | Strength | Cost |
|---|---|---|---|
| **Cyber Gloves** | Reinforced grip | Human shields last longer, can be held while dashing, and throws hit harder | Helps nothing if no enemy can be grabbed (Tanks, bosses) |
| **Marionette Wire** *(oddball)* | Wires a grabbed enemy | The captured enemy becomes a **controllable puppet** that walks ahead of Akane as a mobile shield and then detonates or drops on release | Short duration. Akane stays exposed from behind and the sides |

**Design check for a one-slot system:** each gadget must have a clear "go-to" situation (what it excels at), a clear weakness, and at least one gadget-specific high score strategy. Playtests should compare gadget usage and the top runs. No gadget should be required to clear any wave or boss.

#### Gadget mods (6)

Six gadgets have an optional **mod**, chosen on the same loadout screen as the katana, gun and gadget. A mod is a **swap** (it replaces part of the gadget, never adds power). Four mods are the original game's gadget effects ("Original Spec" for Cyber Gloves, Stabilizer Bracelet, Magnetic Pulse Emitter and Katana Gun), and two are new designs for Akane II (**Swing Line** for the Grapple Anchor and **Overload** for the Hologram Decoy). Full details and unlock challenges are in the [Loadout Detail](akane-ii-loadout.md) §3.2.

#### Boots (4)

In the original, boots changed the dash and movement.

| Boots | Effect |
|---|---|
| **Standard** | Baseline dash |
| **Geta Springs** | The dash becomes a leap with a vertical component. Excellent for the new map |
| **Rail Skates** | A longer, slidier dash that carries momentum. Harder to steer |
| **Silent Tabi** *(strange)* | Short, quiet dash with a shorter cooldown. Enemies lose track of Akane more easily after dashing |

#### Cigarettes

**Cosmetic only**, as in the original: they change the look of Dragon Slash and Dragon Slayer (ink black, crimson, fire, rain) and have no effect on gameplay.

#### Unlocking

Items unlock through **mastery challenges tied to the item itself** (for example, deflect bullets to unlock the Double Barrel, as in the original), to teach the item's skill. Unlocks widen the player's options and never raise their power.

### 3.7 Specials: Dragon Slash and Dragon Slayer

Both are meter-driven, so they reward aggression. Meter values are starting points **[TBD: tune in playtests]**.

- **Dragon Slash:** a fast dash that kills every standard enemy in its path.
  - **Meter:** about **15 kills** (or equivalent style actions such as deflects, Ink Steps and shield kills).
  - **Bosses:** counts as an ordinary strike. It ends a phase only if it lands in a vulnerability window (see §7.1).
- **Dragon Slayer:** the screen-clearing special. In a large map it kills every standard enemy **within a large radius and on screen**, not the whole map.
  - **Meter:** about **40 kills** or equivalent style actions. Does not carry between runs, and the meter does not fill from boss waves alone.
  - **Bosses:** it does not kill a boss outright. If the boss is in a vulnerability window, Dragon Slayer counts as **one phase-ending hit**. Outside a window it only clears the boss's summoned and escort enemies.
  - **Why:** it stays useful during boss waves without trivializing them, and the player still has to read the boss to use it.
- **Cigarettes** change only the look of both specials.

### 3.8 Flow (combo benefits)

**Problem in the original:** after the combo-gated unlocks were done, a combo had no benefit. It was actually safer to have no combo and keep your distance, which encouraged conservative play. Flow gives the combo a small mechanical reason to exist, so that aggression is safer and more rewarding.

**How the combo builds**

- Kills, style actions (perfect Ink Steps, deflect kills, zipline kills) and human shield kills and throws all add to the combo. The shield must never break a combo.
- The combo decays over time, and decays faster while Akane stands still (see §8).

**Tiers and bonuses**

Bonuses are small, temporary and defensive-leaning. They make aggressive movement safer instead of adding raw power. There is no extra damage and no invulnerability.

| Tier | Combo threshold **[TBD]** | Bonus (cumulative) **[TBD: tune values]** |
|---|---|---|
| **Flow I** | 5 | Dash charges recharge faster |
| **Flow II** | 15 | Special meter (Dragon Slash and Dragon Slayer) charges faster |
| **Flow III** | 30 | Ink Step window slightly wider, and sword kills refill extra ammo |

**Falling off.** When the combo decays, Akane loses **one tier at a time**, and the combo count drops to the base of the lower tier. A single mistake or pause never throws away the whole run's momentum. Dying still ends the run.

**Feedback.** The ink aura around Akane grows with each tier (bold on kills, subtle elsewhere, see §1). A clear audio and visual pulse warns a moment before a tier drops.

**During boss fights**

- Decay is **paused** while a boss is introduced and while a boss wave is in transition.
- During the fight, a **stall clock** measures time since the player last made progress. It starts after a grace period set per boss from the boss's par time for each phase, plus a buffer **[TBD]**.
- Every **phase break resets the stall clock**. A boss's own slow pacing, such as the Hunter's stalking or the Warden's tide cycle, is accounted for in the par time and never punishes the player.
- If the stall clock runs out, Flow **decays slowly**, losing one tier at long intervals. This nudges players to push toward the next window instead of waiting.
- The clock **pauses** during boss-driven downtime: phase transitions, cutscenes and any time the boss can't be engaged.
- **Boss death resets the decay timer**, matching the original, and the player's Flow tier is kept going into the next wave.
- Boss hits and phase breaks count as combo actions.

**Risks**

- **Snowballing:** kept small by making the bonuses recharge speeds, not damage.
- **Combo anxiety:** mitigated by the one-tier-at-a-time fall-off.
- **Uniform top runs:** playtest to check that Flow doesn't force a single optimal style.

---

## 4. The Arena Map

One large, vertical single-level map that replaces the original single floor. It is the centerpiece of arcade mode.

### 4.1 Design goals

- **Multiple approaches:** every major area has at least two ways in (ground, high route, hidden route).
- **Verticality:** multiple tiers connected by climbing, ledges, ziplines and drops.
- **Secrets:** hidden routes, optional rooms and traversal shortcuts reward curiosity.
- **Fast traversal:** ziplines and similar tools let players cross the map quickly, and let enemies and bosses use them as well, in controlled cases.
- **Readable at a glance:** each region has a distinct silhouette and landmark so players can orient quickly.

### 4.2 Structure

The full map design (scale and camera, zone designs, route graph, traversal, zone heat, hazards, destruction tiers, scripted events, secrets, navigation aids and the graybox checklist) is in the [Map Design](akane-ii-map.md) document.

The map is a single **vertical Mega-Tokyo tower district**, divided into **5 zones**. Five is manageable for art, AI navigation and testing. Zones are listed from the ground up, plus the hidden network that runs through all of them.

| Zone | Theme and role | Traversal | Suited boss |
|---|---|---|---|
| **Neon Plaza** | Ground-level hub of food stalls and neon, with wide sightlines. The main starting area | Wide, flat, several exits | Standard Duel |
| **Underpass Canals** | Lower tier of tunnels, canals and flood gates. Tight corridors and ambush spots | Narrow routes, water crossings, slides | Floodgate Warden |
| **Rooftop Signage** | Mid to upper tier of rooftops, giant signs and bridges. The main zipline network | Ziplines, climbable walls, long jumps | Demolisher, Crimson Kite, Hunter |
| **Shrine Heights** | Highest tier. A rooftop shrine and the open arena at the top | Steep climbs, updrafts, long-range ziplines | Standard Duel (Katsuro), Crimson Kite |
| **Hidden Network** | Maintenance shafts, vents and secret rooms threading through every zone. Holds secrets and shortcuts | Hidden entrances found by exploring | None (secrets only) |

**Rules**

- Every zone connects to at least two others, so there is never a single required route.
- Each zone has its own landmark silhouette and a limited color accent, kept inside the ink wash palette (see §1).
- Each zone contains at least one open, boss-friendly area.
- A full vertical crossing (Plaza to Shrine Heights) takes about 30–45 seconds with traversal tools **[TBD: tune in prototyping]**.

### 4.3 Traversal tools

- **Ziplines:** one-way or two-way. They can be cut or disabled by certain enemies or bosses.
- **Climbable walls and ledges:** short vertical shortcuts.
- **Launch points:** a **few fixed updrafts and springs** on key routes, so every player has some vertical options. The Updraft Fan gadget still adds more, in places of the player's choosing.
- **Drops and slides:** fast descent.
- **Shortcut gates:** locked in one direction, openable from the other side.

### 4.4 Secrets

- Hidden paths, breakable walls and fake floors.
- Environmental hints: ink stains, odd brush marks, sound cues.
- Rewards are **non-power** items: score bonuses, cosmetics unlocks and lore fragments (see §8).
- There are 12 secrets, all available every run, with hints that get subtler as the player finds more (see the [Map Design](akane-ii-map.md) §10).

### 4.5 Map-level risks

- **Camera and readability** at large scale and across vertical tiers: the camera should look ahead in the direction of travel and show enough above and below. **[TBD: prototype early.]**
- **Navigation for AI** is a hard requirement (see §6.3). Every traversal tool needs an AI-usable representation.

---

## 5. Wave System

Enemies arrive in **waves** across the whole map. The game is infinite, with difficulty rising over time.

### 5.1 Structure

- **Hybrid advancement.** A wave is a group of enemies. The next wave starts when the current one is **cleared**, or when a **pressure timer** expires, whichever comes first.
  - **Early clear:** clearing a wave before the timer gives a speed bonus to score, and no waiting.
  - **Timer expiry:** the next wave spawns on top of whatever remains. This stops hiding and slow luring on a huge map.
  - **Straggler help:** when about 80% of a wave is dead, the remaining enemies are marked and path toward the player, so the player isn't hunting across the whole map.
  - **Timer length** scales with wave size and map distance **[TBD: tune in playtests]**.
  - **Boss waves** have no timer. They end when the boss dies.
- A short **breather** between waves for pickups, route changes and positioning.
- **Boss waves** every **10 waves**, see §7. An **elite event** at waves 5, 15, 25 and so on (a short wave of elite or modified enemies with a score bonus) keeps the gap between bosses from feeling empty.
- Difficulty scales through enemy count, enemy mix, spawn pressure and elite or modified enemies. Individual enemy lethality does not scale, because everything is already one-hit.

### 5.2 Spawning on a large map

- Enemies **spawn out of sight** at spawn points in different zones and route toward the player. They never spawn in the player's view.
- Every zone is live from wave 1, and spawns follow Akane (out of sight, within a short travel time), so no zone is a free camping spot. See the [Map Design](akane-ii-map.md) §8.
- Spawn points are chosen to **encourage use of the whole map**. The game weights spawns away from the player's last N seconds of positions, so camping a corner is not optimal.
- Wave composition draws from a **budget system**: each enemy type has a cost, and each wave has a budget. Higher waves unlock more expensive types.
- **Pressure rules:** a cap on simultaneous active attackers (see §6.3) keeps fights fair even when there are many enemies.

### 5.3 Pacing

- Early waves: small groups, easy to read. Teaches enemy types one at a time.
- Mid waves: mixed groups, flanking begins, ranged and melee combined.
- Late waves: large mixed groups, elites, modifiers and bosses.

---

### 5.4 Pacing and difficulty targets

These targets anchor all tuning. They are starting values **[TBD: confirm in playtests]**.

**Wave length.** A typical wave takes about **60 seconds** to clear. The pressure timer is set at about 1.5 times the expected clear time (about 90 seconds at typical pacing). Breathers are about 5 seconds. A boss wave takes 2-3 minutes.

**Where players should end up**

| Player | Typical death wave | Typical run length | What they see |
|---|---|---|---|
| **New player** | 8-12 | 10-15 min | The early enemies, and often the first Katsuro fight |
| **Regular player** | 25-40 | 30-50 min | The mid-game roster, 2-3 bosses, the wave 25 story beat |
| **Expert** | 100+ | About 2 hours | The whole roster, Overdrive, and the wave 100 story beat |

**Run timeline (approximate)**

| Wave | Time into the run | Milestone |
|---|---|---|
| 10 | 12 min | First boss (Katsuro, Tier 1) |
| 25 | 30 min | Story beat 1. Elite event |
| 50 | About 1 hour | Story beat 2. Overdrive I begins |
| 75 | About 1.5 hours | Story beat 3 |
| 100 | About 2 hours | Story beat 4 (the Tsukumo reveal). Boss Tier 4 |

**Sanity checks on unlock thresholds** (using the wave budget of `8 + 3 x wave` and the scoring formula)

- *Score 100,000 in a run* is reachable at about wave 25-30 with good Flow. That is a regular player's goal.
- *5,000 total kills* is about 10 regular runs.
- *Reach wave 15* (Wildfire) is early. *Reach wave 50* (Ash) is a high-skill goal.

**Design consequences**

- The first boss at wave 10 is meant to be seen by new players, so Tier 1 Katsuro must be learnable on a first attempt.
- The difficulty ramp from wave 1 to 25 must be gentle enough for new players to reach wave 8-12, and steep enough that regulars are challenged by wave 25-40.
- Story beats at waves 25, 50, 75 and 100 land at about 30 minutes, 1 hour, 1.5 hours and 2 hours.

## 6. Enemies

### 6.1 Roster overview

The original had 4 basic enemy types (Yakuza Guy, Shooter, Tank and Cyber Ninja) plus a single boss. *Akane II* aims for **around 16**. With the campaign gone, all 16 appear in arcade mode and are introduced progressively: **8 are available from early waves, and the other 8 trickle in as the run progresses** (unlock waves in the table below).

The roster is organised by **role**, so each enemy does a distinct job and combines interestingly with others.

| # | Enemy | Role | Behavior summary | Unlock |
|---|---|---|---|---|
| 1 | **Yakuza Guy** (original) | Grunt / melee | The common footsoldier. Easy to read and fast to kill | Wave 1 |
| 2 | **Shooter** (original) | Ranged | A cybernetic sharpshooter. The original never missed, so every shot has a lock-on telegraph | Wave 2 |
| 3 | Skirmisher | Flanker | Fast, circles to the player's back. Low commitment attacks | Wave 3 |
| 4 | **Tank** (original) | Armored | The only original enemy needing more than one slash. Dies to one hit from behind | Wave 4 |
| 5 | Lancer | Melee, long reach | Thrusts from range. Punishes careless dashes | Wave 6 |
| 6 | Archer | Ranged, high ground | Prefers elevated spots. Telegraphed arrow arcs | Wave 7 |
| 7 | Shieldbearer | Armored | Front-blocking shield. Must be flanked, pierced or pulled aside | Wave 8 |
| 8 | **Cyber Ninja** (original) | Dasher | Deadly dash attacks and a strong guard. A natural fit for Ink Step | Wave 9 |
| 9 | Bomber | Area denial | Throws delayed charges that zone the ground | Wave 12 |
| 10 | Zipline Raider | Traversal | Uses ziplines and ledges to arrive from above | Wave 14 |
| 11 | Banner Caller | Support | Buffs nearby enemies' speed. Priority target | Wave 17 |
| 12 | Hexer | Control | Places lingering ink zones that restrict movement | Wave 21 |
| 13 | Drone Handler | Summoner | Controls a few small drones. Killing the handler disables them | Wave 23 |
| 14 | Phantom | Ambusher | Appears from hidden spots, attacks, then repositions | Wave 27 |
| 15 | Duelist | Elite melee | Counter stance and ripostes. Tests Ink Step | Wave 32 |
| 16 | Sniper | Long-range elite | Long lock-on shot. Forces cover and verticality | Wave 38 |

The first 8 types (the four originals plus Skirmisher, Lancer, Archer and Shieldbearer) are the early set, introduced one per wave over waves 1-9. The other 8 trickle in between waves 12 and 38. Boss waves and elite events never introduce a new type. Full designs, telegraph tiers, budgets, group AI and wave composition are in the [Enemy Design](akane-ii-enemies.md) document.

### 6.2 Design rules for every enemy

- **One-hit kill.** Every standard enemy dies in one hit, except armored types (the Tank, Shieldbearer) which have a defined counter (flank, break or dodge-counter).
- **Telegraphed lethal attacks.** Every attack that can kill Akane has a visible telegraph with fixed timing, using the shared telegraph language from §1.
- **Single clear role** and a recognizable silhouette.
- **Counter-play:** each enemy has at least one intended answer using sword, gun, gadget, dash, Ink Step or a human shield.
- **Combinations:** designed to pair. For example, Banner Caller plus Skirmishers, or Hexer plus Archers.

### 6.3 Enemy AI

The original's enemy bugs (forgetting to attack, getting stuck) are a **bug class to eliminate**, and flanking is a **feature to add**.

**Reliability requirements**

- Enemies must always be in a defined state with defined transitions. No state is a dead end.
- **Stuck detection:** if an enemy cannot make progress (blocked path, no valid attack position, idle too long), a recovery behavior triggers: re-path, teleport-free repositioning, or despawn and respawn out of sight.
- Each enemy has a **forced re-engagement timer**, so it can never idle indefinitely while the player is nearby and visible.
- Pathfinding supports every traversal tool in §4.3, including ziplines, climbs and drops.

**Group behavior**

- **Attack tokens:** only a limited number of enemies may attack at once (scaled by wave), the rest circle, reposition or flank. This is the main fairness tool against being swarmed.
- **Flanking:** a group assigns roles. Some engage from the front, and others path around to approach from the sides and back, using the map's alternate routes.
- **Telegraph stagger:** simultaneous attacks are offset so overlapping telegraphs are always dodgeable.
- **Audio and visual cue** for off-screen or behind-the-player threats, since flanking only works if the player can perceive it.
- **Shield awareness:** AI responds to Akane holding a human shield (see §3.5).
- **Use of the map:** archers take high ground, zipline raiders arrive from above, and phantoms use secret passages.

**Difficulty tuning knobs**

- Token count, reaction time before telegraph, flank aggressiveness and spawn rate, all adjustable per wave band.

---

## 7. Bosses

*Akane* had one boss, **Katsuro**, who spawned after every 100 kills, cleared the other enemies from the arena, and got stronger and smarter each time he was defeated (adding a pistol, faster dashes and a rapid multi-dash into a heavy slash). It was fun but became stale. *Akane II* has **multiple bosses across three types**, appearing inside arcade mode.

### 7.1 Boss waves

- A boss arrives at **every 10th wave** (waves 10, 20, 30 and so on), replacing the normal wave. Like the original, the other enemies are cleared so the boss gets the player's full attention. This keeps the original's rhythm of a boss every N kills but adapts it to waves.
- **No consecutive repeats:** a boss rotation ensures the same boss doesn't appear back-to-back. **Katsuro opens the rotation at wave 10** and returns every third boss; the other five fill the remaining slots. A full cycle of six bosses spans 60 waves, so most runs will see a handful of them.
- **Boss tiers by wave band:** bosses are tuned by the wave they appear on (see "Boss tiers" below). Only three bosses (Katsuro, the Hunter and the Demolisher) gain new moves per tier. The other three keep their moves and get slightly tighter windows.
- **Reward:** a large score bonus, and a clear breather before the next wave.
- **Flow during boss fights:** combo decay is paused at first and sets in slowly if a fight stalls, then resets on the boss's death (see §3.8).
- **Akane still dies in one hit.** Boss attacks are lethal and follow the telegraph rules.
- **Phased weak points.** Each boss has **2–3 phases**. A phase ends when Akane lands **one clean hit** during a **vulnerability window**, which the boss opens by committing to a big attack, finishing a pattern or exposing a weak point. After the last phase, the boss dies.
  - There is no health bar. The player sees phase pips instead.
  - Windows are short, telegraphed and fair, but not guaranteed to be easy to reach.
  - Valid hits include the sword, gun shots on exposed weak points, a thrown Tadus, and Dragon Slayer (see §3.7). Every boss has at least two valid ways to land a hit.
  - Each new phase changes the boss's attack pattern and, for arena-shifting bosses, the map.

**Boss tiers.** Instead of counting how many times a boss has appeared, every boss uses the tier that matches the current wave. This makes sure players see evolution at whatever wave a boss shows up, and it ties the bosses to the difficulty curve.

| Tier | Waves | Notes |
|---|---|---|
| **Tier 1** | 10-29 | Base moves. The first Katsuro fight |
| **Tier 2** | 30-59 | The first evolution for the three evolving bosses |
| **Tier 3** | 60-99 | Their signature moves arrive |
| **Tier 4** | 100+ (to 149) | All moves, recombined, with slightly tighter windows |
| **Tier 5** | 150-199 | One extra move for each evolving boss |
| **Tier 6** | 200+ | A second extra move for each evolving boss |

The non-evolving bosses (the Debt Collector, the Crimson Kite and the Floodgate Warden) reduce their vulnerability windows by about 5% per tier (with a floor) and recombine their patterns. Tiers 5 and 6 are expected to be rare, and exist for the very top of the leaderboards.

### 7.2 Boss types

**Standard Duel bosses**
A classic, pattern-based fight in an open area. Provide contrast and a pure skill test. Suited to Ink Step and precise play.

**Roaming bosses**
Hunt Akane across the map, using the same traversal tools. They force the player to use ziplines, verticality and route knowledge, and prevent camping.

**Arena-shifting bosses**
Alter the map during the fight: collapse a bridge, flood a tunnel, cut a zipline, or open a new route. The environment is part of the fight, and the map visibly changes during a run.

### 7.3 Roster (working titles)

| Boss | Type | Concept |
|---|---|---|
| **Katsuro** | Standard Duel | The original boss, returning as the player's **Nemesis**. Keeps his evolving behavior (more dashes, a pistol, a multi-dash slash), so he is the only boss who adapts across a run |
| **The Debt Collector** | Standard Duel | Ranged and zoning duel with gun patterns. Tests Ink Step timing |
| **The Hunter** | Roaming | A cloaked Cyber Ninja elite who stalks Akane through the map, using ziplines and ambushes |
| **The Crimson Kite** | Roaming | A fast aerial drone-mech that dives from above, with high-ground control |
| **The Demolisher** | Arena-shifting | Operates a wrecking rig that collapses sections of rooftops and bridges, shrinking safe ground |
| **The Floodgate Warden** | Arena-shifting | Floods and drains the Underpass, shifting routes between phases |

**Initial target:** 6 bosses (2 per type), with more bosses planned for the paid expansion (see §12). Full designs are in §7.5.

### 7.4 Boss design rules

- **New moves are limited to three bosses** (Katsuro, the Hunter, the Demolisher), by wave tier (see "Boss tiers" in §7.1). Every run starts at Tier 1, so runs always begin fair.
- **One skill per boss.** Each boss is built around one clear test of the toolkit (see §7.5), so the fights rotate through different skills.
- Every boss attack follows the shared telegraph language (§1).
- No boss can be defeated by a single exploit (for example, only human shields). Multiple valid approaches are expected.
- Bosses never spawn or move in ways that make traversal tools unusable for long. Cut ziplines come back or have alternatives.
- Roaming bosses must always be **perceivable**: audio and visual cues indicate their direction when off-screen.

### 7.5 Boss designs

Move lists, par times and intro and kill moments are in the [Boss Move Lists](akane-ii-bosses.md) document.

All bosses are **grounded Yakuza cyberpunk**: lieutenants, enforcers and hired killers from Katsuro's network, so they fit the original's setting. Each tests **one skill**, and each has a signature telegraph sound that can be recognized with the screen off.

**Fight length target:** about 60–90 seconds for a first-time appearance, **[TBD: tune in playtests]**. A phase is ended by one clean hit in a vulnerability window (§7.1).

**Appearance order.** Katsuro always opens at wave 10 and returns every third boss (waves 10, 40, 70 and so on). The other five fill the remaining slots in a shuffled order that shows every one of them before any repeats.

| Boss | Type | Tests | Zone | Gains moves by tier? |
|---|---|---|---|---|
| Katsuro | Standard Duel | Reading dashes, Ink Step | Shrine Heights (top arena) | **Yes** |
| The Debt Collector | Standard Duel | Deflecting and gun reading | Neon Plaza | No |
| The Hunter | Roaming | Awareness and positioning | Rooftop Signage, Underpass Canals, Hidden Network | **Yes** |
| The Crimson Kite | Roaming | Vertical movement | Rooftop Signage and Shrine Heights | No |
| The Demolisher | Arena-shifting | Route planning | Rooftop Signage | **Yes** |
| The Floodgate Warden | Arena-shifting | Timing and rhythm | Underpass Canals | No |

---

#### Katsuro, the Nemesis

*Standard Duel · Tests: reading dashes and Ink Step · Evolves*

The original boss. He stalks Akane across Mega-Tokyo and appears whenever the Yakuza's patience runs out. He is a swordsman first, and his dashes are his whole language.

- **Look:** a lean silhouette in a dark coat. His accent color is a **hot pink** slash trail, as in the original. His sword drags a line of ink behind it.
- **Telegraph:** a pink ink line shows each dash path, and a low drum hit marks the commit.
- **Arena:** the open top of Shrine Heights, with little cover, so the fight is purely about dashes and spacing.

**Phases and windows**

The phase list depends on the tier:

- **Tier 1:** Phase 1, **the Duelist** (single dashes and slashes), then Phase 2, **the Duelist, quickened** (the same moves, faster). *Window:* he skids to a stop after a missed dash.
- **Tier 2:** Phase 1, the Duelist. Phase 2, **the Gunslinger** (adds a pistol between dashes; *window:* after he reloads, or after a **perfect Ink Step** through a dash, which staggers him). Phase 3, the Duelist, quickened.
- **Tier 3 and later:** Phase 1, the Duelist. Phase 2, the Gunslinger. Phase 3, **the Master** (a rapid multi-dash that ends in a heavy slash; *window:* the long recovery after the final slash).

**Evolution by tier**

| Tier | Waves | What he has |
|---|---|---|
| 1 | 10-29 | Two phases: single dashes, and faster dashes. No gun |
| 2 | 30-59 | Adds the pistol phase |
| 3 | 60-99 | Adds the multi-dash finisher (Seven Rivers) |
| 4 | 100-149 | All moves, plus Mirror Step, tighter windows and combos that chain the phases in new orders |
| 5 | 150-199 | Adds **Twin Rivers** |
| 6 | 200+ | Adds **Final Draw** |

**Why it works:** each dash has a fixed, learnable timing, so the fight is a rhythm duel. It is the cleanest Ink Step test in the game. Katsuro never cheats the telegraph rules, because that is what makes the nemesis fight fair.

---

#### The Debt Collector

*Standard Duel · Tests: deflecting and gun reading · Does not evolve*

A loan-shark enforcer in a long coat with a ledger tablet, who never shows up to a fight without a collection list. His rifle-arm fires numbered rounds, and every shot is "owed".

- **Look:** a heavy coat, a floating ledger drone, a cybernetic arm cannon. Accent color: gold coin-yellow, used only for his bullets and telegraphs.
- **Telegraph:** a coin-spin sound and a yellow ink line show where each shot lands.
- **Arena:** the Neon Plaza, whose stalls give the player cover and sightlines.

**Phases and windows**

1. **Phase 1, Interest.** Slow shots in fan patterns. Deflecting a shot back at him staggers him. *Window:* a deflected shot hits him, or he reloads (a 2-second cylinder spin).
2. **Phase 2, Collection.** Adds marked floor tiles that explode after a delay and drones that fire crossing lines. *Window:* after the drones are cut down, he steps back to recalculate.
3. **Phase 3, Default.** A full-screen barrage of numbered shots in a readable rhythm. *Window:* the pause when he empties his ledger.

**Tested skill:** deflecting and reading bullet lines. A **Rebi** katana makes deflection forgiving, while other loadouts need to dodge more. The **Double Barrel** or **Magnum** can punish his reload.

**Why it works:** it is a ranged duel that rewards the sword's deflect, so it doesn't force players to rely on the gun.

---

#### The Hunter

*Roaming · Tests: awareness and positioning · Evolves*

A cloaked Cyber Ninja elite hired to end Akane quietly. There's no arena. He stalks her across the map and decides when to strike.

- **Look:** a nearly invisible silhouette, shimmering cloak lines, a thin wire-whip. Accent color: pale violet, used only for his cloak flicker and strikes.
- **Telegraph:** an audio whisper and a shimmer in the direction he is coming from, a clear half-second before a strike. Directional audio is **required** for this fight to be fair.
- **Zones:** roams across the Rooftop Signage, Underpass Canals and Hidden Network. Does not enter Shrine Heights.

**Behavior and windows**

1. **Phase 1, Stalk.** He cloaks, trails Akane, and lunges from behind. *Window:* after a missed lunge, he is stuck in a recovery pose for about 1 second.
2. **Phase 2, Decoys.** He leaves cloaked decoys that also lunge. *Window:* hitting the real one during its recovery, or revealing him with an **EMP**.
3. **Phase 3, Cornered.** He stops hiding and fights in the open, with wire sweeps that cut off routes. *Window:* the end of each wire sweep.

**Evolution by tier**

| Tier | Waves | What he adds |
|---|---|---|
| 1 | 10-29 | Stalking, lunges, decoys and wire sweeps |
| 2 | 30-59 | **Wire traps** strung across ziplines and corridors, which cut ziplines when triggered |
| 3 | 60-99 | **Spotter drones** that reveal Akane's position anywhere on the map, forcing her to destroy them |
| 4 | 100-149 | All moves, tighter windows and recombined patterns |
| 5 | 150-199 | **Wire Web:** a net of wires across a zone, with a visible gap |
| 6 | 200+ | **Silent Pair:** a decoy Hunter that lunges in sync with the real one |

**Tested skill:** staying aware and using the whole map. The best answers are the **Hologram Decoy**, **Sumi Bomb**, **EMP** and good routes through the Hidden Network.

**Why it works:** most bosses ask you to fight in an arena. The Hunter turns the map into a hunting ground and forces the player to use it.

---

#### The Crimson Kite

*Roaming · Tests: vertical movement · Does not evolve*

A Yakuza-owned combat drone-mech piloted remotely from a safe room. It rules the air above the rooftops, and ground players are targets.

- **Look:** a red-and-white winged frame, long rotor blades like brush strokes. Accent color: **lime,** used only for dive lines and its core (red is reserved for gore and the logo).
- **Telegraph:** a rising whine, then a lime ink line drawn from the sky to the target spot. The line holds for a fixed duration before the dive.
- **Zones:** Rooftop Signage and Shrine Heights.

**Phases and windows**

1. **Phase 1, Strafing.** Dives along telegraphed lines. After each dive it crashes briefly into the roof. *Window:* the crash stagger, when its core is exposed to a sword or gun hit.
2. **Phase 2, Perch.** Perches on signage and drops mines and small drones. *Window:* when it lands to reload, at close range, reachable with a **Grapple Anchor**, **Updraft Fan** or **Geta Springs**.
3. **Phase 3, Storm.** Rapid dives across the whole map in readable lines. *Window:* the final dive leaves a long crash that opens its core.

**Tested skill:** going vertical. Anyone without a vertical tool must use ziplines, climbs and the environment, so the fight is winnable with any loadout.

**Why it works:** it makes the new map's verticality matter in a boss fight.

---

#### The Demolisher

*Arena-shifting · Tests: route planning · Evolves*

A demolition-crew boss who operates a wrecking rig mounted on a crane. He's tearing the Rooftop Signage zone down to flush Akane out.

- **Look:** a heavy exo-suit pilot in a crane cab, a swinging wrecking ball on a chain. Accent color: hazard orange, used only for impact zones and the ball.
- **Telegraph:** a warning klaxon, an orange ink circle on the ground, and a chain-tension creak before each swing.
- **Zone:** the Rooftop Signage, the main zipline network.

**Phases and windows**

1. **Phase 1, Teardown.** Wrecking-ball swings that destroy roof sections. *Window:* the ball embeds itself in the roof, exposing the cab to a sword or gun hit.
2. **Phase 2, Collapse.** Collapses entire platforms and cuts ziplines, so Akane must find new routes. *Window:* the crane arm lowers to reload, if Akane can reach the cab by zipline.
3. **Phase 3, Last Swing.** The ground is mostly gone. Wide swings across what's left, readable and fast. *Window:* the arm stuck after a wide miss.

**Persistent map damage:** destroyed rooftops **stay broken for the rest of the run**, which opens some routes and closes others. Every route must keep the map connected (see risks).

**Evolution by tier**

| Tier | Waves | What he adds |
|---|---|---|
| 1 | 10-29 | Wrecking ball and roof collapses |
| 2 | 30-59 | A **grabber claw** that pulls ziplines down and drags Akane toward the ball |
| 3 | 60-99 | **Rebuilt hazard platforms**: he drops scaffolding that is intentionally unstable |
| 4 | 100-149 | All moves, tighter windows and recombined patterns |
| 5 | 150-199 | **Double Ball:** two wrecking balls with staggered swings |
| 6 | 200+ | **Foundation Break:** a floor-wide collapse with a marked safe island |

**Tested skill:** reading the terrain and planning the next two moves. It is the strongest fight for players who like exploring.

**Why it works:** it makes the map itself a boss, and its persistent damage shows the player that their run is changing the world.

---

#### The Floodgate Warden

*Arena-shifting · Tests: timing and rhythm · Does not evolve*

The keeper of the old floodgates under the district, who sells control of the canals to the Yakuza. He floods the Underpass to drown anyone who trespasses.

- **Look:** a bulky figure in a waterproof exo-rig, a pump-cannon, and a tide gauge on his chest. Accent color: deep teal, used only for the water level and his gauge.
- **Telegraph:** a rising bell tone, a teal wave line, and a visible water gauge, all showing the next tide.
- **Zone:** the Underpass Canals.

**Phases and windows**

1. **Phase 1, High Tide.** The canal floods and drains in a fixed, readable rhythm. Water kills on contact when it's too deep. Slides and high routes avoid it. *Window:* during the drain, when his pump station is exposed.
2. **Phase 2, Undertow.** Adds currents that push Akane and floating debris to ride. *Window:* between two currents, when he pauses to recharge.
3. **Phase 3, Flood Gates.** The tide cycles faster, and gates open and close to reshape routes. *Window:* the moment the gates all close.

**Tested skill:** learning a rhythm and moving through changing terrain on time. The **Geta Springs** and **Rail Skates** boots help, and the **Updraft Fan** opens new high routes.

**Why it works:** it's a puzzle-like fight where the boss is almost passive, and the pace comes from the environment.

---

#### Cross-boss design checks

- **Coverage:** between them the bosses test reading (Katsuro), deflecting (Debt Collector), awareness (Hunter), vertical movement (Kite), route planning (Demolisher) and rhythm (Warden). No two bosses test the same skill.
- **Loadout fairness:** every boss has at least two valid ways to land a hit, and none requires a specific katana, gun or gadget.
- **Telegraph audit:** each boss's accent color is used only for its own attacks, and each has a distinct audio motif.
- **Map integrity:** any persistent map damage must preserve at least two routes between every pair of zones **[TBD: needs level design review]**.

---

## 8. Progression and Scoring

The scoring formula, bonuses, Trials (optional challenge modifiers) and run structure are in the [Run, Scoring, Onboarding, HUD and Narrative](akane-ii-run-ui-narrative.md) document.

**Pure skill during play.** Every run starts equal, with no power progression within a run. Between runs, the player unlocks **sidegrade items** (see §3.6), a system that existed in the original, where equipment unlocked through achievements.

- **Reward:** score and leaderboards.
- **Score** comes from kills, combos, style actions (Ink Steps, human shield kills, zipline kills, bullet deflects), speed and boss clears.
- **Unlocks** come from mastery challenges. They add options, never power.
- **Secrets** give score bonuses, cosmetic unlocks and lore fragments. They must not provide power advantages.
- **Combo scoring** decays when Akane stands still, and kills, style actions and movement refill it. It lets players stand and shoot, but rewards keeping the pace up. The combo also drives **Flow** tiers (§3.8) **[TBD: tune the decay]**.
- **Cosmetics** (no gameplay effect, never at the cost of readability): **outfits**, **cigarette ink styles** and **sword trails and kill effects**, unlocked through milestones and challenges.
- **Leaderboards:** a main **score** board (kills, combos, style, speed) and a separate **waves reached** board. Not split by input device.

---

## 9. Story and Presentation

There is no campaign. Story is delivered lightly, with **light continuity** from the original *Akane*.

**What the original established** (from search summaries, **[TBD: verify against the game]**)

- Setting: **Mega-Tokyo, 2121** (the first game's year). Akane has angered the Yakuza and made her "Last Stand" against them.
- Her master, **Ishikawa**, was a Yakuza who killed five oyabuns in 2099, including the Sugahara family. Akane sought him out **for revenge,** trained under him for about a year (2098-2099), developed her own **Dragon Slayer** technique in secret, and killed him in a duel (the Final Scene). She learned his **Dragon Slash** from him.
- The original's "Final Scene" (unlocked by collecting all equipment) is a **flashback, set in the past,** of Akane confronting Ishikawa as a child and defeating him in a duel. This is confirmed.
- The original's optional **tutorial** is also a flashback: it takes place about **23 years before the main game,** with Akane as a child training under Ishikawa (per search summaries). **Akane was an adult during the main gameplay of the first game, and she is an adult in Akane II.** Her childhood appears only in flashbacks.

**Akane II continuity approach**

- Akane II is set in **Mega-Tokyo in 2121, on the same night as the first game,** right after her Last Stand, in a district run by **Oyabun Tsukumo,** and Akane has come to **finish the fight.** The original has no definitive ending and Akane is alive, so the story says she survived, refers to the Last Stand only as "the Last Stand" or "earlier tonight," and adds nothing more.
- **Katsuro** returns as her Nemesis: rebuilt by the Yakuza after every defeat, and obsessed with learning her. Tsukumo never appears in a fight and is revealed by name at wave 100.
- Story beats play at waves 25, 50, 75 and 100. Details, cast, voice rules, Overdrive tiers, modes and cosmetics are in the [Story, Endgame, Modes and Cosmetics](akane-ii-story-and-modes.md) document.
- New bosses are lieutenants or hired killers from the same network, so they reuse the setting without needing a new plot.
- Story is told through short intro text, boss and enemy flavor text, environmental details and lore fragments found in secret rooms.
- The ink wash style is the way Akane remembers and sees the city (see §1).
- Menus, UI and audio should carry the same ink wash identity as the art.

**Audio direction:** **traditional instruments over an electronic pulse.** Shamisen, taiko and shakuhachi sit over synth bass and drums, matching the ink wash and cyberpunk mix.

- **Adaptive layers** intensify with the wave band and calm during the breather.
- **Boss themes:** a unique theme per boss, built from stems that add a layer for each phase, with a stinger at each phase break. Details, the full cue list and the mix are in the [Audio Design](akane-ii-audio.md) document.
- **Gameplay audio** has strong cues for telegraphs and for off-screen or behind-the-player threats.

---

## 10. Platforms and Input

HUD layout and onboarding are in the [Run, Scoring, Onboarding, HUD and Narrative](akane-ii-run-ui-narrative.md) document.

### 10.1 Platforms

- **PC, Nintendo Switch 2 and the original Nintendo Switch.** The original *Akane* released on PC, then Switch. Akane II targets **Switch 2 natively** and keeps the **original Switch as the low tier,** because the original Switch hardware is still widely owned and Switch 2 plays original Switch games through backward compatibility.
- **Hardware context** (from published specs): Switch 2 has 12 GB of RAM (about 9 GB for games), an Ampere GPU of about 1.7 TFLOPs handheld and 3.1 TFLOPs docked, and 6 CPU cores for games, with a 1080p handheld screen and up to 4K docked. The original Switch has 4 GB of RAM in total and a far weaker GPU. So the original Switch is **the floor** every piece of content must run on, and Switch 2 has headroom for PC-like budgets.
- **Three budget tiers** (see the [Map Design](akane-ii-map.md) §15 and [Visual Briefs](akane-ii-visual-briefs.md) §9.6):

| Tier | Target |
|---|---|
| **PC** | The highest caps |
| **Switch 2** | PC-mid budgets, 1080p handheld |
| **Original Switch** | The low caps (the floor), 720p handheld |

- **PC is meant to run on modest hardware.** This is a 2D game rendered at 640 x 360, comparable in scope to *Dead Cells* (whose [Steam minimum](https://www.systemrequirementslab.com/cyri/requirements/dead-cells/16012) is an i5-class CPU, 2 GB of RAM and a GTX 450-class GPU). PC graphics presets (High, Medium, Low, Potato) are in the [Visual Briefs](akane-ii-visual-briefs.md) §9.6b. The real pressure points are the CPU (AI, navigation, destruction) and memory, not the GPU.
- **Frame rate:**
  - **PC:** an **uncapped** option, plus selectable caps (such as 60, 120 and 144) and a vsync option.
  - **Both Switches:** a **stable 60 FPS** target (frame-time consistency matters more than peak numbers). If the frame time runs over budget, the game lowers cosmetic load first (particles, lights, fog), and never gameplay entities, telegraphs or hazards.
  - **Gameplay is frame-rate independent.** The simulation runs at a fixed 60 Hz step and rendering interpolates between steps. Ink Step windows, telegraph times, hit-stop, attack timings and scoring must be identical at every frame rate, so uncapped play gives smoother visuals but no gameplay or leaderboard advantage.
  - **Rendering approach (decided): a sub-pixel camera with a higher internal output.** Sprites and tiles are drawn at the **output resolution** with each art pixel scaled by a whole number (2x at 720p, 3x at 1080p, 4x at 1440p, 6x at 4K), so pixels stay square and uniform. Positions and the camera are interpolated between the 60 Hz simulation steps to **output-pixel precision,** so motion is smooth at any refresh rate without shimmer. Soft effects (lighting, fog, bloom) render at the base or half resolution and are upscaled, which keeps the cost low. Details are in the [Visual Briefs](akane-ii-visual-briefs.md) §1.5.
- The large map, higher enemy counts and ink-wash effects need a **performance budget** from day one, especially on the original Switch. Budgets to set early: maximum active enemies, AI update cost, effect density and map streaming **[TBD]**.
- Sources: [Tom's Hardware](https://www.tomshardware.com/pc-components/gpus/nintendo-switch-2-official-specs-confirm-gpu-similar-to-a-mobile-rtx-2050), [TweakTown](https://www.tweaktown.com/news/105247/nintendo-switch-2-specs-confirmed-cpu-gpu-memory-and-restricted-performance/index.html) and the [Digital Foundry specs summary](https://www.resetera.com/threads/digital-foundry-nintendo-switch-2-confirmed-specs-cpu-gpu-memory-system-reservation-more-6-cpu-cores-for-games-9gb-ram-for-games.1188981/).
- Steam Deck compatibility should come for free from the controller-first design.

### 10.2 Input

**Gamepad-first, with keyboard and mouse equally satisfying.** The game is designed around a controller, but the keyboard and mouse scheme is not a port. It is tuned to feel just as good.

- **Gamepad:** twin-stick aiming for the gun. Light aim assist that is adjustable and can be turned off.
- **Keyboard and mouse:** mouse aiming for the gun, with movement and abilities on the keyboard. No aim assist, and no penalties.
- **Shared tuning:** Ink Step windows, buffering and cancel timings are identical on both schemes. Neither scheme gets an advantage in score or leaderboards.
- **Rebinding:** full remapping on both.
- **Gadget and special inputs** must be reachable without leaving movement or aim, on both schemes.
- **Leaderboards** are not split by input device **[TBD: confirm after playtests show whether aiming creates a gap]**.

### 10.3 Accessibility (all ship at launch)

- **Hold/toggle and one-handed layouts:** hold-versus-toggle for every held input, and a one-handed preset on both gamepad and keyboard/mouse.
- **Adjustable telegraph visibility:** larger or higher-contrast telegraphs and an audio-cue volume slider. Timing is never changed by these options.
- **Hit-stop and screen shake sliders**, including a reduced-flash mode.
- **Assist options:** optional aids such as wider Ink Step windows or slower game speed. Clearly marked, and runs that use them are kept off the main leaderboards.

---

## 11. Risks and Open Questions

### Risks

1. **Flanking versus one-hit kills.** Enemies surrounding a player who dies in one hit can feel cheap. *Mitigation:* attack tokens, telegraph stagger, directional cues.
2. **Human shield abuse.** Could trivialize crowds or ranged enemies. *Mitigation:* hold limits, ignore rules for some enemies, cooldown, speed penalty.
3. **Large map readability and camera.** Verticality and scale can hurt clarity. *Mitigation:* early prototyping, strict contrast rules, camera look-ahead.
4. **AI navigation complexity.** Traversal tools multiply path states. *Mitigation:* build navigation and stuck recovery first, before enemy variety.
5. **Content scope.** 16 enemies, 6 bosses and a large map is a lot of animation and balancing. *Mitigation:* roles first, shared telegraph language, ship boss roster in stages.
6. **Boss repetition.** Arcade runs repeat bosses, and a 10-wave cadence means each boss is seen rarely but must be strong. *Mitigation:* rotation, escalation on return, map-changing bosses.
7. **Precise dodge tuning.** Must be learnable and not mandatory. *Mitigation:* dash stays viable, window and reward tuned through playtests.
8. **Original Switch performance.** A large vertical map with many AI-driven enemies and broad destruction is demanding on 4 GB of RAM. *Mitigation:* the original Switch is the floor, with budget tiers, performance budgets from day one, and simple AI LODs for distant enemies. Switch 2 has far more headroom.
9. **Input parity.** Mouse aiming can out-perform stick aiming. *Mitigation:* tune enemy telegraphs and windows to be forgiving enough for both, and watch leaderboard data.
10. **Hybrid wave timer.** Stacking waves on stragglers may overwhelm players. *Mitigation:* the straggler marking, and a cap on total active enemies.

### Open questions

- [ ] Original game facts: see [Appendix B](#appendix-b-original-game-verification-checklist).
- [ ] Tuning values: wave timers, special meter costs, vulnerability window lengths, performance budgets.
- [ ] Map scale and crossing time (prototype).
- [ ] Combo decay rate, the stand-still penalty, Flow thresholds and bonus values, and each boss's par time.
- [x] Story copy: final draft written (see the [Copy Deck](akane-ii-copy-deck.md)). Remaining: a last read of the lines marked [CHECK] by someone who knows the first game closely.

---

## 12. Post-launch and Scope

- **Plan:** free fixes and balance updates, then free events, and **one paid expansion** adding bosses, enemies and a new map area.
- **Multiplayer and co-op are out of scope.** The AI, attack-token and flanking systems and the one-hit-kill balance are designed around a single player.

---

## Appendix A: Decisions Log

| Decision | Outcome |
|---|---|
| Campaign / story mode | **Cut.** Does not fit the arcade design |
| Boss placement | Inside arcade mode, **every 10 waves**. Katsuro opens the rotation at wave 10 |
| Boss kills | Phased weak points: 2–3 phases, one clean hit per vulnerability window. Akane still dies in one hit |
| Wave advancement | Hybrid: clear or pressure timer. No timer on boss waves |
| Dragon Slayer | Clears standard enemies in a large radius. Counts as one phase-ending hit on a boss during a vulnerability window. Meter about 40 kills |
| Map | Vertical Mega-Tokyo tower district with 5 zones: Neon Plaza, Underpass Canals, Rooftop Signage, Shrine Heights, Hidden Network |
| Dash | 2 charges that recharge over time. A successful Ink Step refunds one |
| Ink Step | One dodge whose direction comes from input |
| Human shield | Absorbs 3 hits, then breaks. Standing still is softly punished through combo decay |
| Launch points | A few fixed updrafts and springs, plus the Updraft Fan gadget |
| Elite events | At waves 5, 15, 25 and so on |
| Art | Bold ink splashes on kills only. Shared ink base with one accent hue per zone. Ink wash is Akane's perception of the city |
| Cosmetics | Outfits, cigarette ink styles, sword trails and kill effects |
| Leaderboards | Score board plus waves-reached board, not split by input |
| Audio | Traditional instruments over an electronic pulse |
| Accessibility | Hold/toggle and one-handed layouts, telegraph visibility, hit-stop and shake sliders, assist options. All at launch |
| Post-launch | Free updates, then one paid expansion. No multiplayer or co-op |
| Elites | Named elites for eight enemy types, plus generic modifiers. Elite events at waves 5, 15, 25... draw from a pool of six themes |
| Run loop | Instant restart with the last loadout. A short, skippable run summary. The Armory is one button away |
| Scoring | Base points (50 x enemy cost) x Flow multiplier, plus flat style bonuses and wave, event and boss bonuses |
| Difficulty | One fair curve, plus Trials (optional challenge modifiers with score multipliers) unlocked after defeating Katsuro |
| Onboarding | As in the original: a separate optional Tutorial from the menu (the Dojo), plus arcade that can be started cold. First-encounter cards and teaching waves 1-9 |
| HUD | Minimal brush-drawn HUD with off-screen threat smears |
| Unlocks | Item-specific mastery challenges, one per item, in four tiers |
| Narrative delivery | Short text only: intro card, enemy and boss codex, lore fragments and a Dojo Memory flashback. No voice, no cutscenes |
| Review confirmations | Enemy unlock schedule kept (one new type per wave over waves 1-9, the rest over waves 12-38). Trials unlock after beating Katsuro once. Starting gadget: Cyber Gloves only, or none. Unlock challenges keep the mix of single-run and cumulative |
| Map scale and camera | About 6 x 5 screens, with a mid-zoom camera (about one screen plus look-ahead) |
| Map variation | Fixed geometry every run. Variety comes from waves, spawns, events and boss damage |
| Navigation | Optional minimap, off by default. Landmarks are the primary navigation |
| Environmental events | Three scripted, telegraphed events (Rain Shower, Blackout, Canal Surge) about every 6-8 waves from wave 7. No score bonus, and they can be turned off in accessibility options |
| Hazards | Falls are always safe. A few marked, rhythmic lethal hazards only |
| Anti-camping | Zone heat: staying in a zone redistributes the wave's spawns toward it, without adding budget |
| Destruction | Broad, in tiers: indestructible structure (including at least two cover pieces per space), major destructibles that stay broken for the run, and decor. Route connectivity is built on the structural tier only |
| Secrets | All 12 always available. Score rewards repeat each run, cosmetics and lore are one-time. Hints get subtler as the player finds more |
| Villain and copy | Oyabun Tsukumo confirmed. Text register is dry noir with a little bite. Tsukumo's respect for Akane grows across the four beats. The wave 100 beat is just the name and the confrontation. Item lines are wry one-liners. Lore fragments mix logs, notes and graffiti. All text is in the Copy Deck |
| Dojo tutorial | Framed as a flashback to the dojo, as the original's was. The Dojo Memory secret is a lesson about waiting |
| Visual production | Base resolution 640 x 360, Akane about 48 px tall. Hand-drawn pixel sprites with brush-stroke shading. Enemies are grouped by role shape with a shared Yakuza identity. Boss scale varies by boss. Regular-enemy telegraphs are white-hot (white with a black ink outline), red belongs to gore and the logo, and each boss has its own accent color (the Kite's is lime) |
| Environment quality | Take *Dead Cells'* environment quality bar, not its 3D-to-2D animation pipeline: dynamic lighting with hand-drawn normal maps, gradient maps for zone color and the night clock, 4 parallax layers (3 on Switch), fake volumetrics, dense particles and wet reflections. Telegraphs and hazards stay on an unlit layer so lighting never hurts readability |
| Kill animations | No finishers: kills never lock Akane. Sword kills use a procedural slice along the real cut angle. Gun kills are headshots (as in the original), with a head reaction and headgear gag per enemy. Context kills, enemy-specific beats and a rare gag about 1 kill in 20. Hit-stop is 2-3 frames |
| Gore and stains | Bright red gore as in the original, full gore only, with the same gore level and no gore option, just like the original, which shipped on every platform that way. Stains are painted on the background and rinsed away by the rain over 1-2 minutes, with pooled caps |
| Telegraph color change | Regular-enemy telegraphs changed from vermilion-and-white to **white-hot** so that red belongs to gore and the logo. Boss accents stay, except the Crimson Kite's, which is now lime |
| Kill sound ladder | Kill sounds step up a musical scale with the Flow combo, in the score's key, and reset when the combo breaks |
| Kill effects (cosmetic) | A small category of five alternate kill looks (Sakura, Glitch, Ash, Paper, Neon Ink), unlocked by milestones. Cosmetic only |
| Audio production | Layered adaptive stems with a motif per zone. A unique theme per boss with phase layers. Tactile organic SFX with a cyber edge. Non-verbal vocals only. Telegraph, rear-threat and boss cues are top priority in the mix |
| Win state | None. The game stays endless, with story beats at waves 25, 50, 75 and 100 and a rotation of short beats after 100 |
| Katsuro's rebuilds | A deliberate noir conceit. Never explained mechanically, in a one-night setting |
| Setting and timing | 2121, the same night as the first game, continuing after the Last Stand. The whole game is one night |
| Night clock | The night deepens across waves (late night, midnight, small hours, before dawn), and dawn never comes. Baseline weather is light rain |
| Original canon | Confirmed: the original has no definitive ending, and Akane is alive. The story says she survived, refers to the Last Stand only as "the Last Stand" or "earlier tonight," and invents nothing more |
| Akane's voice | Terse and dry, about 8 words per line at most, only at story beats |
| Endgame | Overdrive tiers from wave 50 (stacking modifiers and combined events). Bosses gain moves by wave tier instead, with Tiers 5 and 6 at waves 150+ and 200+ |
| Extra modes | Boss Rush and Time Attack, unlocked after defeating Katsuro once. No daily challenge |
| Cosmetics | About 25 unlockable items in four categories (outfits, sword trails, cigarette ink styles and kill effects), earned from beats, secrets, milestones and mode clears |
| Zone wake-up | **Removed.** It created free camping spots. Replaced by spawn-follow |
| Hue collisions | Low-saturation, static zone set dressing, plus dimming set-dressing accents during a boss fight if needed |
| Frame rate | PC: uncapped option plus caps and vsync. Both Switches: a stable 60 FPS target. Simulation is a fixed 60 Hz step with render interpolation, so gameplay and leaderboards are identical at any frame rate |
| Pixel rendering | Sub-pixel camera with a higher internal output: sprites drawn at output resolution with whole-number pixel scaling, positions interpolated to output-pixel precision, soft effects at base or half resolution. A pixel-snap toggle is available for purists |
| PC settings | High, Medium, Low and Potato presets, so the game runs on modest hardware. The target is a *Dead Cells*-class minimum spec (to be confirmed in testing). The CPU and memory are the pressure points, not the GPU |
| Platforms | PC, Nintendo Switch 2 (native, PC-mid budgets) and the original Nintendo Switch (the low tier and the floor for all content) |
| Input | Gamepad-first, with keyboard and mouse equally satisfying |
| Boss types | Roaming, arena-shifting and standard duel |
| Boss tone | Grounded Yakuza cyberpunk, with each boss testing one skill |
| Boss evolution | Only Katsuro, the Hunter and the Demolisher gain moves, by wave tier (10-29, 30-59, 60-99, 100-149, 150-199, 200+) and not by appearance count. Every run starts at Tier 1 |
| Combat toolkit | Original five slots (katana, gun, gadget, boots, cigarette), remixed: 7 katanas, 6 guns, 11 gadgets (one slot), 4 boots |
| Progression | No power progression. Items unlock as sidegrades through mastery challenges |
| Gadgets | One slot, 11 gadgets across four families (weapon augments, traversal, AI manipulation, human shield synergy), with 3 oddballs |
| Enemy roster | ~16 types, introduced progressively across arcade waves |
| Gadget mods | Six optional, swap-style mods on the gadget slot: four "Original Spec" mods echoing the original gadget effects, plus Swing Line (Grapple Anchor) and Overload (Hologram Decoy). Unlocked by challenges, mostly adapted from the original's unlock conditions |
| Story | Light continuity with the original. Akane's goal is to finish the fight against Oyabun Tsukumo, who never appears in a fight. Katsuro is the Nemesis, rebuilt and obsessed |

---

## Appendix B: Original-Game Verification Checklist

Original-game facts were checked online (the fan wiki, review summaries and achievement guides), not against the game itself. Because the items were **remixed on purpose,** their original behaviors don't need to match, so this appendix lists only facts we reference for continuity.

**Verified online** (sources are listed in the [Copy Deck](akane-ii-copy-deck.md) §13 and the [Loadout Detail](akane-ii-loadout.md) §3.1)

- [x] Setting: Mega-Tokyo, 2121, a rainy night. Akane's vehicle crashes in the intro, she is surrounded, and she makes a final stand.
- [x] **Ishikawa:** Akane's master (2098-2099), a Yakuza who killed five oyabuns including the Sugahara family. Akane trained under him for revenge and killed him in the Final Scene.
- [x] The Final Scene (unlocked by collecting all equipment) is a childhood flashback. The optional tutorial is also a flashback, about 23 years before the main game.
- [x] **Katsuro:** the boss, appearing every 100 kills, with a pink dash trail, a pistol, multi-dashes and a dying compliment. He levels up each time he is killed.
- [x] The four enemy types: Yakuza Guy, Shooter, Tank and Cyber Ninja.
- [x] Item names kept for continuity are real: katanas Rebi and Tadus, the six guns, and the five gadgets (Cyber Gloves, Stabilizer Bracelet, Adrenaline Shot, Magnetic Pulse Emitter, Katana Gun). The gadget effects are recorded in the loadout document.
- [x] Akane's fate: the original has no definitive ending, and she is alive.
- [x] Dragon Slash and Dragon Slayer are real special moves.

**Remaining (continuity only, not blocking)**

- [ ] The original allowed up to two gadgets (per search summaries). We chose one slot on purpose.
- [ ] Whether the original had elite variants of its four enemies.
- [ ] The exact wording of any original text we echoed.
- [ ] A last read of the lines marked **[CHECK]** in the [Copy Deck](akane-ii-copy-deck.md) by someone who knows the first game closely.

**Not needed by agreement**

- The original's katana, gun, boots and cigarette behaviors, and its other six gadget names: our versions are remixes or new designs.
