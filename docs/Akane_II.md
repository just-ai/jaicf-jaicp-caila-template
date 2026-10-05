# Akane II — Design Document

**Studio:** Ludic Studios
**Protagonist:** Sugahara Akane
**Status:** Complete
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

- [x] The original allowed up to two gadgets (per search summaries). We chose one slot on purpose.
- [x] Whether the original had elite variants of its four enemies.
- [x] The exact wording of any original text we echoed.
- [x] A last read of the lines marked **[CHECK]** in the [Copy Deck](akane-ii-copy-deck.md) by someone who knows the first game closely.

**Not needed by agreement**

- The original's katana, gun, boots and cigarette behaviors, and its other six gadget names: our versions are remixes or new designs.

---

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

| Tier         | Warning time | Used for                                                                             |
| ------------ | ------------ | ------------------------------------------------------------------------------------ |
| **Quick**    | 0.35 s       | Low-commitment attacks (grunt slashes, skirmisher cuts, snap thrusts)                |
| **Standard** | 0.55 s       | Most attacks (shots, dashes, thrusts, bombs)                                         |
| **Heavy**    | 0.80 s       | Area attacks and slams (Tank slam, Archer volley, Hexer zones, Zipline Raider drops) |
| **Long**     | 1.00 s       | Lock-on attacks and ambushes (Sniper, Phantom)                                       |

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

| #   | Enemy          | Cost | Unlock wave | Set     |
| --- | -------------- | ---- | ----------- | ------- |
| 1   | Yakuza Guy     | 1    | 1           | Early   |
| 2   | Shooter        | 3    | 2           | Early   |
| 3   | Skirmisher     | 3    | 3           | Early   |
| 4   | Tank           | 5    | 4           | Early   |
| 5   | Lancer         | 3    | 6           | Early   |
| 6   | Archer         | 3    | 7           | Early   |
| 7   | Shieldbearer   | 4    | 8           | Early   |
| 8   | Cyber Ninja    | 4    | 9           | Early   |
| 9   | Bomber         | 4    | 12          | Trickle |
| 10  | Zipline Raider | 4    | 14          | Trickle |
| 11  | Banner Caller  | 5    | 17          | Trickle |
| 12  | Hexer          | 5    | 21          | Trickle |
| 13  | Drone Handler  | 6    | 23          | Trickle |
| 14  | Phantom        | 5    | 27          | Trickle |
| 15  | Duelist        | 7    | 32          | Trickle |
| 16  | Sniper         | 6    | 38          | Trickle |

**Change from the main doc:** introducing eight types in five waves was too fast to teach. The early eight now arrive over waves 1–9 (about one new type per wave) and the trickle eight arrive over waves 12–38. Boss waves (10, 20, 30) and elite events (5, 15, 25) never introduce a new type.

---

## 2. Group AI: roles, tokens and flanking

### 2.1 Roles

Each enemy fills one role in a group. Roles are assigned by the group director, not by the individual enemy.

| Role           | What it does                                            | Typical enemies                                            |
| -------------- | ------------------------------------------------------- | ---------------------------------------------------------- |
| **Anchor**     | Engages from the front and draws attention              | Yakuza Guy, Tank, Shieldbearer                             |
| **Pincer**     | Takes a side or rear route to attack from another angle | Skirmisher, Yakuza Guy (when the group is 4+), Cyber Ninja |
| **Suppressor** | Pressures from range and restricts positions            | Shooter, Archer, Sniper, Bomber                            |
| **Support**    | Buffs or controls, and stays behind the line            | Banner Caller, Hexer, Drone Handler                        |
| **Special**    | Arrives by an unusual route                             | Zipline Raider, Phantom, Duelist                           |

A healthy group has at least one Anchor, so that Pincers and Suppressors have something to work around.

### 2.2 Attack tokens

Only a limited number of enemies may **commit** to an attack at once. An enemy asks the director for a token before it starts a telegraph.

| Token            | Used by                      | Starting limit **[TBD]**                |
| ---------------- | ---------------------------- | --------------------------------------- |
| **Melee token**  | Any melee attack             | 2 in waves 1–10, rising to 4 by wave 40 |
| **Ranged token** | Shots, arrows, bombs, drones | 2 in waves 1–10, rising to 4 by wave 40 |
| **Heavy token**  | Heavy and Long tier attacks  | 1 at a time at all waves                |

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

| Problem                                          | Failsafe                                                              |
| ------------------------------------------------ | --------------------------------------------------------------------- |
| No progress for 3 s (blocked path)               | Re-path. If it fails again, take the nearest valid node               |
| No attack for 6 s while within range and visible | **Forced re-engagement:** the enemy requests a token with priority    |
| Stuck for 8 s                                    | Despawn out of sight, respawn at the nearest spawn point and re-enter |
| No valid attack position                         | Fall back to circling and re-evaluate every 1 s                       |
| Token never granted for 10 s                     | Escalate priority, then rotate another enemy out                      |
| Player stationary and out of reach               | Suppressors target, and Pincers go for the flank                      |

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

| Modifier     | Effect                          | Visual                   | Rules                                   |
| ------------ | ------------------------------- | ------------------------ | --------------------------------------- |
| **Hasted**   | +25% move speed                 | A speed-streak trail     | Telegraphs unchanged                    |
| **Armored**  | Takes one extra hit             | An ink plate on the body | Not on Tanks or Shieldbearers           |
| **Volatile** | Small explosion on death        | A glowing core           | Harms nearby enemies as well            |
| **Shrouded** | Partly cloaked                  | A shimmer                | Only on enemies without Long telegraphs |
| **Vengeful** | On death, hastes a nearby enemy | A hatched ink brand      | Never stacks on one target              |

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

| Band         | Waves | Template idea                                                                               |
| ------------ | ----- | ------------------------------------------------------------------------------------------- |
| **Intro**    | 1–9   | A new type plus Yakuza fodder. Teaches one thing per wave                                   |
| **Mix**      | 10–25 | Anchor + Suppressor + Pincer groups. Pairings (Shieldbearer + Shooter, Skirmisher + Archer) |
| **Pressure** | 26–40 | Large mixed groups, Support enemies, named elites in the budget                             |
| **Endless**  | 41+   | Full roster, modifiers on a growing share of enemies, tokens at their maximum               |

### 5.4 Enemy pairings to design for

| Pair                         | Why it works                                                      |
| ---------------------------- | ----------------------------------------------------------------- |
| Shieldbearer + Shooter       | The shield covers the shooter, so the player must flank or pierce |
| Banner Caller + Skirmishers  | Fast flankers, with an obvious priority target                    |
| Hexer + Archer               | The zones pin Akane under volleys                                 |
| Drone Handler + Yakuza crowd | Drones pick off the player during crowd fights                    |
| Sniper + Phantom             | Long and short lines of danger from different distances           |
| Duelist + anything           | The Duelist's stance controls where the player can go             |

---

## 6. Elite events (waves 5, 15, 25...)

An **elite event** replaces the normal wave at 5, 15, 25 and so on. It is short, has a theme, and gives a score bonus (see the run and scoring document).

| Event                | Theme               | Composition                                                                  |
| -------------------- | ------------------- | ---------------------------------------------------------------------------- |
| **Gold Coat Patrol** | Named elites        | 3 named elites (from unlocked types) + escorts. **Always the wave 5 event.** |
| **Ambush**           | Attacks from behind | Skirmishers and Phantoms from the Hidden Network. Rear cues are essential    |
| **Barrage**          | Ranged pressure     | Shooters, Archers and Shieldbearer cover. Tests deflecting                   |
| **Swarm**            | Numbers             | Many Yakuza Guys and a Drone Handler. A natural Dragon Slayer moment         |
| **Gauntlet**         | Modifiers           | Standard enemies with Hasted, Armored or Volatile modifiers                  |
| **Highwire**         | From above          | Zipline Raiders and Archers on high perches                                  |

**Rules**

- Events draw from the pool **without repeating until all have been seen** (only from events whose enemies are unlocked).
- An event never includes enemies that have not been unlocked.
- Event waves have a **normal pressure timer** (see §5.1 of the main doc), and clearing early gives a bonus.
- After wave 25, events may combine two themes.
- Events end with the usual breather.

---

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

| Boss             | Phase 1 | Phase 2 | Phase 3 | Notes                                          |
| ---------------- | ------- | ------- | ------- | ---------------------------------------------- |
| Katsuro          | 25 s    | 30 s    | 30 s    | Phases 2-3 appear as he evolves                |
| Debt Collector   | 30 s    | 35 s    | 30 s    |                                                |
| Hunter           | 45 s    | 45 s    | 35 s    | Longer, because stalking is part of the design |
| Crimson Kite     | 35 s    | 40 s    | 30 s    |                                                |
| Demolisher       | 40 s    | 45 s    | 35 s    | Includes time to reach the cab                 |
| Floodgate Warden | 40 s    | 40 s    | 35 s    | Tied to tide cycles                            |

---

## 3. Katsuro

*Standard Duel. Tests reading dashes and Ink Step. Gains moves by wave tier.*

### 3.1 Move list

| Move               | Phase | Telegraph                                                      | Description                                                                                                                    | Counter                                                                          | Window                                          |
| ------------------ | ----- | -------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------- | ----------------------------------------------- |
| **Blood Line**     | 1+    | Standard (0.55 s)                                              | A single dash along a pink line, ending in a slash                                                                             | Step off the line. **Perfect Ink Step** through it staggers him                  | The skid after a missed dash (1.0 s)            |
| **Return Cut**     | 1+    | Standard                                                       | A dash, then a reverse dash back along the same line                                                                           | Ink Step the first, dodge the second                                             | The end of the reverse dash                     |
| **Crescent Slash** | 1+    | Quick (0.35 s)                                                 | A close-range arc slash after a short step                                                                                     | Back off, or deflect                                                             | None (a pressure move)                          |
| **Pistol Volley**  | 2+    | Standard                                                       | Three shots in a fan, 0.3 s apart, from a distance                                                                             | Deflect (one at a time), or sidestep                                             | A reload after the third shot (1.0 s)           |
| **Quickstep**      | 2+    | Quick                                                          | A fast sidestep into a slash                                                                                                   | Hold ground and deflect                                                          | None                                            |
| **Seven Rivers**   | 3+    | The full path drawn first (1.2 s), then each dash 0.25 s apart | A **multi-dash:** four dashes along a pre-drawn star-like path, ending in a heavy slash. The path never reaches the arena edge | Stay in the gaps between lines. A perfect Ink Step through any line staggers him | The long recovery after the heavy slash (1.5 s) |
| **Mirror Step**    | 4+    | Standard                                                       | After a dash, he reappears behind Akane on a previously drawn line                                                             | Read the earlier line                                                            | After the reappear                              |

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

| Move                | Phase | Telegraph     | Description                                                | Counter                             | Window                                          |
| ------------------- | ----- | ------------- | ---------------------------------------------------------- | ----------------------------------- | ----------------------------------------------- |
| **Single Payment**  | 1+    | Standard      | One aimed coin round, flies slowly                         | **Deflect it back**                 | A deflected shot that hits him opens the window |
| **Installment Fan** | 1+    | Standard      | A fan of five rounds                                       | Weave between them, or deflect two  | The cylinder spin after the fan (2.0 s)         |
| **Late Fee**        | 2+    | Heavy (0.8 s) | Marks floor tiles that explode after a delay               | Leave the marked tiles              | None                                            |
| **Drone Audit**     | 2+    | Standard      | Ledger drones fire crossing lines                          | Destroy drones with a hit, or avoid | The pause when the drones are cut down (1.5 s)  |
| **Foreclosure**     | 3+    | Heavy         | A full-screen barrage in a readable rhythm, in three waves | Move between waves                  | The pause as his ledger empties (1.5 s)         |

### 4.2 Design notes

- A Rebi katana or deflect-focused loadout is rewarded, but dodging remains valid.
- The barrage is built from rhythm, so the player can learn it.

---

## 5. The Hunter

*Roaming. Tests awareness and positioning. Gains moves by wave tier.*

### 5.1 Move list

| Move                           | Phase | Telegraph                                                    | Description                                                            | Counter                                 | Window                                    |
| ------------------------------ | ----- | ------------------------------------------------------------ | ---------------------------------------------------------------------- | --------------------------------------- | ----------------------------------------- |
| **Shadow Lunge**               | 1+    | Long (1.0 s) with a shimmer and a whisper from his direction | A lunge from behind                                                    | Move off the line, or Ink Step          | The recovery pose after a miss (1.0 s)    |
| **Cloak Step**                 | 1+    | None (a reposition)                                          | He moves to a new position, fully cloaked                              | Listen for the whisper                  | None                                      |
| **Decoy Echo**                 | 2+    | Long                                                         | Cloaked decoys also lunge                                              | Find the real one, or **EMP** to reveal | A hit on the real one during its recovery |
| **Wire Sweep**                 | 3+    | Heavy (0.8 s)                                                | A wire whip sweeps a wide arc, cutting routes                          | Jump or dash through the gap            | The end of the sweep (1.0 s)              |
| **Wire Trap** *(Tier 2+)*      | any   | Standard                                                     | Strings wire across a zipline or corridor, which triggers when touched | Avoid or cut it                         | None                                      |
| **Spotter Drones** *(Tier 3+)* | any   | Standard                                                     | Releases drones that reveal Akane's location                           | Destroy the drones                      | None                                      |

### 5.2 Design notes

- He **never enters Shrine Heights,** so the player always has a place where the fight is on more even terms.
- Directional audio is required (see main doc §7.5).
- The pattern director never has him lunge twice within 2 seconds.

---

## 6. The Crimson Kite

*Roaming. Tests vertical movement. Does not evolve.*

### 6.1 Move list

| Move              | Phase | Telegraph                               | Description                                                   | Counter              | Window                                                  |
| ----------------- | ----- | --------------------------------------- | ------------------------------------------------------------- | -------------------- | ------------------------------------------------------- |
| **Strafing Dive** | 1+    | Heavy (0.8 s): a lime line from the sky | A dive along a marked line, crashing into the roof            | Step aside           | The **crash stagger** (1.5 s), when the core is exposed |
| **Rotor Sweep**   | 1+    | Standard                                | A low sweep across a rooftop                                  | Jump over, or dash   | None                                                    |
| **Mine Drop**     | 2+    | Standard                                | Drops mines that explode after 1.5 s                          | Leave the rings      | None                                                    |
| **Drone Release** | 2+    | Standard                                | Releases small drones                                         | Kill them in one hit | The Kite lands to recharge (1.5 s), at close range      |
| **Storm Dive**    | 3+    | Heavy                                   | Rapid dives across the map in readable lines (three in a row) | Move between lines   | The final dive's crash (2.0 s)                          |

### 6.2 Design notes

- The window is always on the ground (the crash), so players without vertical tools can still win.
- A **Grapple Anchor,** **Updraft Fan** or **Geta Springs** offers an additional angle during the perch.

---

## 7. The Demolisher

*Arena-shifting. Tests route planning. Gains moves by wave tier.*

### 7.1 Move list

| Move                          | Phase | Telegraph                                       | Description                                                   | Counter                          | Window                                                    |
| ----------------------------- | ----- | ----------------------------------------------- | ------------------------------------------------------------- | -------------------------------- | --------------------------------------------------------- |
| **Wrecking Swing**            | 1+    | Heavy (0.8 s): an orange circle and chain creak | A wrecking ball slams a marked area, destroying roof sections | Leave the circle                 | The ball **embeds** in the roof (1.5 s), exposing the cab |
| **Chain Sweep**               | 1+    | Standard                                        | A low horizontal sweep of the chain                           | Jump or dash                     | None                                                      |
| **Platform Collapse**         | 2+    | Heavy                                           | Collapses an entire platform after a warning rumble           | Leave the platform               | None                                                      |
| **Zipline Snap**              | 2+    | Standard                                        | Cuts a zipline                                                | Use another route                | None                                                      |
| **Cab Reload**                | 2+    | None                                            | The crane arm lowers to reload                                | Reach the cab (by zipline)       | The reload (2.0 s)                                        |
| **Last Swing**                | 3+    | Heavy                                           | Wide swings across what remains of the roof                   | Time the gaps                    | The arm sticks after a wide miss (1.5 s)                  |
| **Grabber Claw** *(Tier 2+)*  | 2+    | Standard                                        | A claw pulls a zipline down and drags Akane toward the ball   | Cut or dodge the claw            | The claw retracting                                       |
| **Scaffold Drop** *(Tier 3+)* | 3+    | Heavy                                           | Drops unstable scaffolding as hazard platforms                | Avoid, or use as footing briefly | None                                                      |

### 7.2 Design notes

- Persistent damage is the signature, so each destroyed section must keep at least two routes between zones (see the map spec §8).
- The cab is reached by zipline or grapple, never only by one route.

---

## 8. The Floodgate Warden

*Arena-shifting. Tests timing and rhythm. Does not evolve.*

### 8.1 Move list

| Move             | Phase | Telegraph                               | Description                                                    | Counter                                   | Window                                         |
| ---------------- | ----- | --------------------------------------- | -------------------------------------------------------------- | ----------------------------------------- | ---------------------------------------------- |
| **High Tide**    | 1+    | Heavy: a bell tone and a teal wave line | The channel floods, and water kills on contact when deep       | Take the walkways, slides, or high routes | The **drain** exposes the pump station (2.0 s) |
| **Pump Cannon**  | 1+    | Standard                                | A cannon shot along a marked line                              | Step aside, or deflect                    | None                                           |
| **Undertow**     | 2+    | Standard                                | Currents push Akane along the channel                          | Brace, or use debris                      | The pause between currents (1.5 s)             |
| **Debris Surge** | 2+    | Heavy                                   | Floating debris slams across the channel                       | Ride or avoid                             | None                                           |
| **Gate Cycle**   | 3+    | Heavy                                   | Gates open and close, reshaping routes. The tide cycles faster | Learn the rhythm                          | The moment all gates close (1.5 s)             |

### 8.2 Design notes

- The Warden is passive by design: the environment provides the pace.
- Every tide state is visible on his chest gauge, so the rhythm is readable.

---

## 9. Tier 5 and tier 6 moves (wave 150+ and 200+)

The three bosses that gain moves by tier (see the main doc §7.1 for the tier table) get one extra move each at **Tier 5** (waves 150-199) and **Tier 6** (waves 200+). Par times increase by 10% per tier from Tier 4. Tiers 5 and 6 are expected to be rare, and exist for the top of the leaderboards.

| Boss               | Tier 5 move                                                                   | Tier 6 move                                                                               |
| ------------------ | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| **Katsuro**        | **Twin Rivers:** two Seven Rivers paths drawn at once, with a shared safe gap | **Final Draw:** the full path is drawn in reverse after the strike, forcing a second read |
| **The Hunter**     | **Wire Web:** a net of wires strung across a zone, with a visible gap         | **Silent Pair:** a decoy Hunter that lunges in sync with the real one                     |
| **The Demolisher** | **Double Ball:** two wrecking balls with staggered swings                     | **Foundation Break:** a floor-wide collapse with a marked safe island                     |

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


| Boss                 | Epithet on the title card                  |
| -------------------- | ------------------------------------------ |
| Katsuro              | *"The one who never stays dead."*          |
| The Debt Collector   | *"Everything is owed."*                    |
| The Hunter           | *"You will not hear it twice."*            |
| The Crimson Kite     | *"The sky belongs to the Yakuza."*         |
| The Demolisher       | *"Nothing stands that he has not marked."* |
| The Floodgate Warden | *"The tide keeps the ledger."*             |

---

# Akane II — Map Design

Companion to [akane-ii-design.md](akane-ii-design.md) (§4 The Arena Map). This is the full map design write-up. It fixes the structure and rules of the level; exact geometry comes from a graybox pass by level designers.

> All sizes, speeds and budgets are **starting values for tuning** (marked **[TBD]** only where a decision is open). Names are working titles. Measurements: **1 tile** is roughly Akane's body width, and **1 screen** is about 32 x 18 tiles at standard zoom.

## Contents

1. [Design goals](#1-design-goals)
2. [Scale, camera and readability](#2-scale-camera-and-readability)
3. [Overall structure](#3-overall-structure)
4. [Zone designs](#4-zone-designs)
5. [Route graph](#5-route-graph)
6. [Traversal systems](#6-traversal-systems)
7. [Combat spaces and flanking](#7-combat-spaces-and-flanking)
8. [Spawning, spawn-follow and zone heat](#8-spawning-spawn-follow-and-zone-heat)
9. [Environment: hazards, destruction and events](#9-environment-hazards-destruction-and-events)
10. [Secrets](#10-secrets)
11. [Navigation aids](#11-navigation-aids)
12. [Boss arenas](#12-boss-arenas)
13. [AI navigation requirements](#13-ai-navigation-requirements)
14. [Art, audio and visual language](#14-art-audio-and-visual-language)
15. [Performance and platform budgets](#15-performance-and-platform-budgets)
16. [Graybox acceptance checklist](#16-graybox-acceptance-checklist)
17. [Risks and open questions](#17-risks-and-open-questions)

---

## 1. Design goals

The map is the centerpiece of *Akane II*. The original had a single floor. This is **one large, vertical, multi-path level** where enemies arrive in waves across the whole space.

1. **Many ways to do everything.** Every area has at least two approaches. There is no single correct route.
2. **Master the map.** The map is **fixed**, so players learn it, plan routes and develop favorite lines, as with a good arcade stage.
3. **Reward curiosity.** Secrets, shortcuts and alternate routes are always present. Exploration is never required, and always worth it.
4. **Use the whole map.** Enemies, zone heat and bosses push players through different zones, so no corner is permanently safe.
5. **Vertical and fast.** Ziplines, launch points and climbs let players cross the map quickly and fight at several heights.
6. **A fair world.** No cheap deaths from terrain. Falls are safe, and lethal hazards are marked and rhythmic.
7. **A world that changes.** Players can break much of the environment, bosses damage it permanently for the run, and scripted events shake things up.

---

## 2. Scale, camera and readability

### 2.1 Scale

- **About 6 screens wide and 5 screens tall** (roughly 192 x 90 tiles, not fully filled) **[TBD]**.
- A full vertical crossing from the Plaza to Shrine Heights takes about **30-45 seconds** with traversal tools and over a minute without them **[TBD]**.
- Zone footprints (approximate): Plaza 2.5 x 1 screens, Canals 3 x 1, Rooftops 3.5 x 2, Shrine Heights 1.5 x 1, with the Hidden Network threaded through the gaps.

### 2.2 Camera

- **Mid zoom:** the camera shows about one screen of the area around Akane, plus **look-ahead** (about 20% in the direction of travel).
- **Integer pixel scaling** keeps the pixel art crisp at every supported resolution.
- **Vertical bias:** the camera shifts up when climbing or launching, and down when dropping, so the player sees where they will land.
- **Zipline lead:** while riding, the camera leads ahead by about 25%.
- **Open arenas** (the Plaza square, the Shrine plateau, the Floodgate chamber) zoom out about 10% during boss fights so the whole arena is visible.
- **Telegraph first:** the camera never hides a telegraph. An off-screen threat is shown by a screen-edge smear (see the run and narrative document), not by zooming out.

### 2.3 Readability rules for geometry

- Silhouettes of walkable surfaces stay clear against backgrounds (hard ink edges for walkable surfaces, soft washes for backdrops).
- No straight sightline in an open combat area exceeds about **1.2 screens,** except from designated **sniper perches.**
- Each zone has a **landmark** visible from its neighbors, for orientation.
- Backdrops (skyline, distant towers) are visually quiet and never use telegraph colors.

---

## 3. Overall structure

A single connected **vertical district** of Mega-Tokyo. The ground is at the bottom, the shrine at the top, and a hidden network threads through all of it.

**Altitude bands (approximate)**

| Band  | Zone                   | Altitude                          |
| ----- | ---------------------- | --------------------------------- |
| -1    | Underpass Canals       | Below ground, about 12 tiles down |
| 0     | Neon Plaza             | Ground level                      |
| +1    | Rooftop Signage (low)  | 8-18 tiles up                     |
| +2    | Rooftop Signage (high) | 18-30 tiles up                    |
| +3    | Shrine Heights         | 30-40 tiles up                    |
| (all) | Hidden Network         | Threaded through every band       |

```
            [ Shrine Heights ]            <- top: shrine, plateau, bell
              /     |      \
     Z-A (rising zipline)  U-2 (updraft)  ladder   Z-E (return)
        /           |           \
   [ Rooftop Signage W ]--Z-B--[ Rooftop Signage E ]
        |  \          crane (Demolisher)   |   \
     Z-C   fire escape               service lift (one-way up, after use)
        |                                  |
   [ Neon Plaza W ]--[ Plaza center ]--[ Neon Plaza E ]   <- start
        |        \         |         /        |
     stairs    manhole   U-1        stairs   drop gate
        |                                     |
   [ Canals W ]--------[ Canals E ]------[ Floodgate chamber ]
        \                  |                 /
         =====  Hidden Network (vents, shafts, secret rooms)  =====
```

**Size classes for navigation (used by the AI and the level):**

| Class | Examples             | Can use                                                        |
| ----- | -------------------- | -------------------------------------------------------------- |
| **S** | Akane, most enemies  | Everything, including vents                                    |
| **M** | Shieldbearer, Lancer | Everything except vents and crawl spaces                       |
| **L** | Tank                 | Stairs, ramps and wide doors only. No ladders, vents or climbs |

---

## 4. Zone designs

Each zone lists: role, layout and sub-areas, combat character, traversal, hazards, destructibles, secrets, accent hue, landmark and ambience.

### 4.1 Neon Plaza (ground hub)

- **Role:** the starting area, the widest and the easiest to read. Players learn movement and the first enemies here. The Debt Collector's arena.
- **Accent hue:** neon pink. **Landmark:** a giant rotating neon fish sign. **Ambience:** crowd murmur, frying, neon hum.

**Sub-areas**

| Sub-area                 | Description                                      | Character                                                   |
| ------------------------ | ------------------------------------------------ | ----------------------------------------------------------- |
| **Fish Square**          | The central open square under the fish sign      | The main arena: open, with a few pieces of cover            |
| **Stall Row** (west)     | Dense market stalls and awnings                  | Heavy destructible cover. Great for Shooters and ambushes   |
| **Noodle Alley** (north) | A narrow, lit alley with the first updraft (U-1) | A funnel. Strong for Lancers and chokepoints                |
| **Arcade Block** (east)  | An arcade building with thin partitions          | Break-through walls open flank routes                       |
| **Pond Garden** (south)  | A koi pond and a garden                          | The manhole and the Koi Pond Wall secret. Quiet, with cover |
| **Stair Wells** (two)    | The staircases down to the Canals                | Chokepoints and drop-in spawns                              |

- **Traversal:** short zipline hops between signs (Z-D), the fire escape and ladders up, **U-1** to the lower Rooftops, **Z-H** (a motorized line up for new players), and a manhole down.
- **Hazards:** none lethal. Destroyed gas stalls burst into a flame zone for 4 seconds (marked and telegraphed).
- **Destructibles:** stalls, vending machines, glass signs, awnings and partitions.
- **Secrets:** Koi Pond Wall, Manhole Maze, Vending Machine Cache.
- **Signature encounters:** crowd fights (Swarm event) and Shooter and Shieldbearer lines across the Square.

### 4.2 Rooftop Signage (mid to upper)

- **Role:** the main combat and traversal playground, with the largest zipline network and the most verticality. The arena for the Demolisher, and a hunting ground for the Hunter and the Kite.
- **Accent hue:** electric blue. **Landmark:** a huge vertical kanji sign. **Ambience:** wind, distant traffic, electrical buzz.

**Sub-areas**

| Sub-area          | Description                                        | Character                                          |
| ----------------- | -------------------------------------------------- | -------------------------------------------------- |
| **West Rooftops** | Water tanks, antennas and a laundry yard           | Medium cover. The Z-C descent                      |
| **East Rooftops** | A helipad, vents and the Z-A and U-2 launch points | Open, with long sightlines                         |
| **The Span**      | Central bridges and the **crane**                  | The Demolisher's arena. A hub between the clusters |
| **Billboard Row** | Climbable sign frames and walkable billboards      | The most vertical area. Archers perch here         |
| **Helipad**       | A wide open platform with ladders                  | A spawn point and a small open arena               |

- **Traversal:** ziplines Z-A, Z-B, Z-C, Z-F, springs (S-1, S-2), climbable sign frames, U-2, and the service lift.
- **Hazards:** electrified sign wires (marked, cycling), steam vents (marked, cycling).
- **Destructibles:** glass sign panels, water tanks (flood a small area), antennas, thin partitions and rooftop cover. Billboards used as walkways are structural.
- **Secrets:** Neon Kanji, Broken Sign Roost, Roof Vent Drop.
- **Signature encounters:** Highwire events, Archer and Hexer pins from above, and the Hunter's stalks.

### 4.3 Shrine Heights (top)

- **Role:** the highest tier, quieter and more open. A rewarding but exposed place. The Katsuro arena, and a place the Kite visits (the Hunter never enters).
- **Accent hue:** jade green. **Landmark:** a red torii gate against the skyline. **Ambience:** wind, a distant bell, sparse strings.

**Sub-areas**

| Sub-area          | Description                                        | Character                                                                      |
| ----------------- | -------------------------------------------------- | ------------------------------------------------------------------------------ |
| **The Approach**  | Narrow stairs and a ladder up to the top           | A funnel, with a Sniper perch                                                  |
| **Torii Plateau** | A wide open plateau with a torii gate and lanterns | The main arena. Mostly open, with only the torii pillars and lanterns as cover |
| **Bell Terrace**  | A terrace with the shrine bell                     | The Bell of the Shrine secret                                                  |
| **Lantern Path**  | A path of stone lanterns                           | The Lantern Path secret                                                        |
| **Shrine Hall**   | A small shrine building                            | A compact interior fight space                                                 |
| **Overlook**      | The edge of the plateau                            | The start of Z-E (the return line)                                             |

- **Traversal:** Z-A (rising line in), U-2, a ladder, Z-E down, and the maintenance shaft to the Hidden Network (with a hidden ladder back up).
- **Hazards:** none lethal. Wind is visual and audio only.
- **Destructibles:** lanterns (ink splash only), paper screens in the Shrine Hall, and the bell (a secret trigger, not destructible).
- **Secrets:** Bell of the Shrine, Lantern Path.
- **Signature encounters:** Sniper duels, and open duels with Duelists.

### 4.4 Underpass Canals (below ground)

- **Role:** tight corridors, ambush spots and a rhythm of water. A contrast to the open Plaza and Rooftops. The Floodgate Warden's arena.
- **Accent hue:** jade-teal (low saturation, see §14). **Landmark:** the floodgate wheel at the end of the main channel. **Ambience:** dripping, echo, pumps.

**Sub-areas**

| Sub-area              | Description                                    | Character                                     |
| --------------------- | ---------------------------------------------- | --------------------------------------------- |
| **West Tunnels**      | Narrow tunnels with walkways                   | Tight. Strong for Skirmishers and Phantoms    |
| **Central Channel**   | The main water channel with bridges            | The main lane. The flood hazard passes here   |
| **East Tunnels**      | Another tunnel network                         | A mirror of the west, with a different layout |
| **Pump Room**         | Machinery and electrified rails                | Hazard-rich                                   |
| **Floodgate Chamber** | A large chamber with walkways at three heights | The Warden's arena                            |
| **Drain Mouths**      | Three spawn outlets                            | Enemies arrive here                           |

- **Traversal:** slides and chutes down, **U-3** up to the Plaza underside, the service lift (up to the Rooftops), Z-F (a line down from the Rooftops), narrow ladders, and water crossings by bridge.
- **Hazards:** deep flood water (lethal, cycling), electrified rails (marked, cycling), steam vents.
- **Destructibles:** pipes, thin walls (break-through routes), crates and walkway railings.
- **Secrets:** Flooded Shrine, Pipe Whisper.
- **Signature encounters:** Ambush events, flank fights in the tunnels, and Canal Surge moments.

### 4.5 Hidden Network (threaded through all zones)

- **Role:** secrets, shortcuts and ambush routes. It is never required, and is always an option.
- **Accent hue:** shares the hue of the nearest zone near the entrance, shifting to a dim white deeper in. **Landmark:** a lit server room door. **Ambience:** humming cables, faint static, distant voices.

**Sub-areas**

| Sub-area               | Description                  | Character                                           |
| ---------------------- | ---------------------------- | --------------------------------------------------- |
| **Vent Crawls**        | Low vents linking zones      | Size S only. A quick path for Akane and Skirmishers |
| **Maintenance Shafts** | Vertical shafts with ladders | Climbs and drops. Only size S and M                 |
| **Cable Hall**         | A hall of cables with Z-G    | A secret zipline                                    |
| **Server Room**        | A locked room with terminals | The lore secret                                     |
| **Old Dojo**           | A sealed dojo                | The Dojo Memory secret                              |
| **Junctions**          | Small hubs                   | Ambush nodes for Phantoms                           |

- **Traversal:** vents (crawl), shafts (climb and drop), Z-G, and a hidden ladder and updraft (U-4) back up.
- **Hazards:** electrical panels (marked, cycling).
- **Destructibles:** a few panels and grates. Most of the network is structural.
- **Secrets:** Server Room, The Old Dojo, and the entrances to the network from every zone.
- **Signature encounters:** Phantom ambushes, and tight Skirmisher fights.

---

## 5. Route graph

Direct connections between zones, with direction. "Both" means walkable either way.

| From - To                       | Routes                                                                                 |
| ------------------------------- | -------------------------------------------------------------------------------------- |
| Plaza - Rooftops                | Fire escape (both), ladders (both), **U-1 updraft** (up), **Z-H** (up), **Z-C** (down) |
| Plaza - Canals                  | Stairs west (both), stairs east (both), drop gate (down), **U-3** (up)                 |
| Plaza - Hidden Network          | Manhole (both)                                                                         |
| Rooftops - Shrine Heights       | **U-2 updraft** (up), ladder (both), **Z-A** (up), **Z-E** (down)                      |
| Rooftops - Hidden Network       | Roof vent (down), **U-4** secret updraft (up)                                          |
| Rooftops - Canals               | Service lift (up, after it is activated from below), **Z-F** (down)                    |
| Canals - Hidden Network         | Drain vents (both), maintenance ladder (both)                                          |
| Shrine Heights - Hidden Network | Maintenance shaft (down), hidden ladder from the old dojo (up)                         |

**Redundancy requirement.** Every pair of zones must have at least two routes that share no intermediate zone, in both directions, **at all times** (including after boss damage and player destruction).

| Pair              | Route 1                                     | Route 2                                               |
| ----------------- | ------------------------------------------- | ----------------------------------------------------- |
| Plaza - Rooftops  | Direct (fire escape, ladders)               | Via Canals (stairs, then service lift up or Z-F down) |
| Plaza - Canals    | Stairs west                                 | Stairs east                                           |
| Plaza - Hidden    | Manhole                                     | Via Canals (stairs, then vents)                       |
| Plaza - Shrine    | Via Rooftops (fire escape, then U-2)        | Via Hidden (manhole, then the old dojo ladder)        |
| Rooftops - Canals | Service lift / Z-F                          | Via Plaza (fire escape, then stairs)                  |
| Rooftops - Shrine | U-2 updraft                                 | Ladder (or Z-A)                                       |
| Rooftops - Hidden | Roof vent / U-4                             | Via Canals (Z-F, then vents)                          |
| Canals - Hidden   | Drain vents                                 | Maintenance ladder                                    |
| Canals - Shrine   | Via Hidden (vents, then the dojo ladder up) | Via Plaza and Rooftops (stairs, fire escape, U-2)     |
| Hidden - Shrine   | Maintenance shaft down / dojo ladder up     | Via Rooftops (roof vent or U-4, then U-2)             |

**Size-class check:** the redundancy must hold for size L (Tank) enemies too, using only stairs, ramps and wide doors. The Plaza - Canals stairs, the Plaza - Rooftops fire escape ramp, and the service lift must be size L accessible.

---

## 6. Traversal systems

### 6.1 Base movement

| Action      | Value **[TBD]**                                                          |
| ----------- | ------------------------------------------------------------------------ |
| Run speed   | 9 tiles / s                                                              |
| Jump height | 3 tiles (hold for the full height)                                       |
| Dash        | 3.5 tiles (see the loadout document for boots)                           |
| Climb speed | 5 tiles / s on ladders and climbable frames                              |
| Fall        | Always safe. A drop of over 8 tiles adds a short 0.15 s landing recovery |

### 6.2 Ziplines

Ziplines are the signature fast route. Most descend, and **upward travel uses launch points, climbs, motorized lines and gadgets.**

| ID      | Route                                      | Direction                           | Length | Notes                                                       |
| ------- | ------------------------------------------ | ----------------------------------- | ------ | ----------------------------------------------------------- |
| **Z-A** | Rooftop East (high) to the Shrine approach | One-way up (motorized rising cable) | Long   | The scenic line. Can be **cut** by the Hunter or Demolisher |
| **Z-B** | Rooftop West to Rooftop East               | Two-way (motorized)                 | Medium | Passes the crane. Cut by the Demolisher in phase 2          |
| **Z-C** | Rooftop West to Plaza West                 | One-way down                        | Medium | A fast escape to the ground                                 |
| **Z-D** | Plaza sign to sign (three short hops)      | Two-way                             | Short  | Used by Zipline Raiders to enter the Plaza                  |
| **Z-E** | Shrine plateau edge to Rooftop East        | One-way down                        | Medium | The return from the top. Z-A and Z-E form a loop            |
| **Z-F** | Rooftop East to a Canals roof opening      | One-way down                        | Short  | A shortcut into the Canals                                  |
| **Z-G** | Cable Hall in the Hidden Network           | Two-way                             | Short  | A secret line                                               |
| **Z-H** | Plaza East to Rooftop West low             | One-way up (motorized)              | Short  | A starter line for new players                              |

**Rules**

- Ride speed is about **14 tiles / s.** Akane can jump off at any time, can attack and shoot while riding, and can be hit.
- Every zipline has two **anchors** that can be cut by the sword or a shot. Cut lines **reform after 20 seconds** unless a boss event says otherwise.
- Zipline Raiders use Z-A, Z-B, Z-D and Z-E. Enemies can also be killed on a line.
- No zipline crosses a boss arena's central fighting space.

### 6.3 Launch points

| ID           | Location                | Takes Akane to              | Notes                               |
| ------------ | ----------------------- | --------------------------- | ----------------------------------- |
| **U-1**      | Plaza north alley       | Lower Rooftops              | The first launch point players find |
| **U-2**      | Rooftop East edge       | Shrine Heights approach     | An alternative to Z-A               |
| **U-3**      | Canals channel vent     | Plaza underside / drop gate | Returns players to the Plaza        |
| **U-4**      | Hidden Network (secret) | Rooftop vent                | A secret way up                     |
| **S-1, S-2** | Rooftop springs         | Higher signage              | Short hops over gaps                |

Updrafts are visible as upward ink streaks. Each launches Akane about 6-10 tiles. The **Updraft Fan** gadget adds temporary extra updrafts.

### 6.4 Other tools

- **Climbable surfaces:** ladders, sign frames, fire escapes, pipes (marked with a consistent grip-line ink).
- **Slides and chutes:** fast one-way descents in the Canals and between rooftops.
- **Drop gates and one-way doors:** one-way shortcuts, opened from the other side.
- **Service lift:** one-way up, activated from the Canals side. Takes 6 seconds, during which Akane is carried and can fight.

### 6.5 Traversal feel rules

- Entering and leaving a traversal tool must be responsive: no dead frames on grabbing a zipline or a ladder.
- All traversal tools are usable during combat, and cancelable.
- Every traversal route has a **purpose**: faster, safer, higher ground, or hidden.

---

## 7. Combat spaces and flanking

- **Every zone has at least one open combat space** wide enough for a full group to engage and flank.
- **Flank routes:** every open space has at least two side routes of different lengths (for example, a ground path and a ledge path), so the flanking AI has real choices.
- **Chokepoints:** funnel areas (Noodle Alley, the Approach, the Stair Wells, the tunnels) where small groups and Lancers shine.
- **Cover:** every open space has cover pieces. At least **two per space are indestructible** so cover never disappears completely (see §9.2).
- **High ground:** each space has a nearby perch for Archers and Snipers, reachable by Akane.
- **Pairing spaces:** the map offers the pairings listed in the enemy document (Shieldbearer and Shooter lines in Fish Square, Hexer and Archer pins in Billboard Row, and so on).

---

## 8. Spawning, spawn-follow and zone heat

### 8.1 Spawn points

Enemies spawn **out of sight,** at least 12 tiles from Akane, and weighted away from where she has been recently.

| Zone             | Ground spawns                                                           | Special spawns                          |
| ---------------- | ----------------------------------------------------------------------- | --------------------------------------- |
| Neon Plaza       | 3 (north, west and east alleys)                                         | 1 high anchor for Zipline Raiders (Z-D) |
| Rooftop Signage  | 3 (ladders, a helipad, a stairwell)                                     | 2 high anchors (Z-B, Z-A)               |
| Shrine Heights   | 2 (the approach stairs, and the back stairs from the maintenance shaft) | 1 perch for Snipers                     |
| Underpass Canals | 3 (drain mouths)                                                        | none                                    |
| Hidden Network   | 2 (vent exits)                                                          | 3 ambush nodes for Phantoms             |

### 8.2 Spawn-follow (every zone is always live)

Every zone can spawn enemies from wave 1. There is **no sleeping zone,** because a sleeping zone would be a free camping spot.

- **Spawn-follow rule:** each wave picks its spawn points from those that are **out of sight and within about 6-15 seconds of travel** from Akane, wherever she is. A player who runs to the top of the map is met at the top of the map.
- **Every zone can host a full wave.** Each zone has at least two ground spawn points and one special spawn point, so the rule works everywhere (Shrine Heights has two ground spawns for this reason).
- **Early game is taught by the enemy roster and wave size,** not by geography. Waves 1-9 introduce one new enemy per wave and use small budgets (see the enemy document), so the whole map is open but each wave is small and readable.
- **Spawn zones are weighted by zone affinity** (§8.3) and **zone heat** (§8.4), not locked.

### 8.3 Zone affinity

Enemy types prefer certain zones, which gives each zone a character without fixing where they appear.

| Zone           | Favored enemies                                                  |
| -------------- | ---------------------------------------------------------------- |
| Plaza          | Yakuza Guys, Shooters, Shieldbearers, Tanks, Bombers             |
| Rooftops       | Archers, Zipline Raiders, Hexers, Skirmishers, Snipers (perches) |
| Shrine Heights | Snipers, Duelists, Cyber Ninjas                                  |
| Canals         | Skirmishers, Lancers, Drone Handlers, Phantoms                   |
| Hidden Network | Phantoms, Skirmishers                                            |

Affinity is a weighting, not a rule: any enemy can appear elsewhere (a wave director may break affinity for variety).

### 8.4 Zone heat

To stop players from camping one safe spot, each zone has a **heat** value.

- **Heat rises** while Akane is in a zone (after an 8-second grace period) and **cools** while she is elsewhere.
- **Heat levels** and effects **[TBD]**:

| Level          | Heat  | Effect                                                                 |
| -------------- | ----- | ---------------------------------------------------------------------- |
| **Cool**       | 0-30  | Normal spawning                                                        |
| **Warm**       | 30-60 | +20% of the wave's spawns are placed in this zone                      |
| **Hot**        | 60-90 | +50% of the spawns, and the group director assigns one extra Pincer    |
| **Overheated** | 90+   | Pincers from two routes at once, and a Suppressor on the nearest perch |

- Heat **redistributes** the wave's budget. It does not add budget, so moving is not punished with a bigger wave, only a more focused one.
- Heat **pauses** during boss waves and the breather.
- **Feedback:** the zone's accent lighting pulses faintly as heat rises. The optional minimap tints the zone.
- Moving between zones keeps heat low, which rewards using the map and the traversal tools.

---

## 9. Environment: hazards, destruction and events

### 9.1 Hazards

**Falls are safe.** Only clearly marked hazards kill, and they behave rhythmically.

| Hazard                        | Zones                                       | Behavior                                                                                           |
| ----------------------------- | ------------------------------------------- | -------------------------------------------------------------------------------------------------- |
| **Deep flood water**          | Canals                                      | Lethal when deep. Rises and drains on a visible rhythm (the Warden's fight, the Canal Surge event) |
| **Electrified rails / wires** | Canals, Rooftops, Hidden                    | Cycle on and off with a visible arc and an audible hum                                             |
| **Flame zones**               | Plaza (gas stalls), anywhere (Fire Archers) | Burn for 4 seconds, marked with embers                                                             |
| **Steam vents**               | Rooftops, Canals                            | Cycle with a hiss                                                                                  |

**Rules**

- Every lethal hazard has a **warning of at least 0.8 seconds** before becoming lethal, with visual and audio cues.
- Hazards use a **hazard mark** (black-and-white ink hatching plus a hum) that is separate from telegraph and boss accent colors.
- **Enemies die to hazards too.** The Kusarigama, the Gravitational Beam and shield throws can use them.
- Hazards never cover a route entirely. Every hazardous route has a safe alternative.

### 9.2 Destruction

Destruction is **broad** (a deliberate choice), so the environment is part of every fight. To protect route mastery, cover, AI navigation and performance, destructibles are in tiers.

| Tier                       | What it covers                                                                                                                                             | Rule                                                                                          |
| -------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------- |
| **S: Structural**          | Zone-defining geometry, floors of key platforms, route spines, ladders, zipline anchors, boss arena floors, **at least two cover pieces per combat space** | **Indestructible** (except by scripted boss events)                                           |
| **A: Major destructibles** | Market stalls, glass signs, vending machines, thin walls and partitions, water tanks, most crates and cover, railings, ziplines (cuttable)                 | Break under sword, shots, explosions and impacts. Damage **persists for the rest of the run** |
| **B: Debris and decor**    | Lanterns, bottles, neon tubes, tables, paper screens                                                                                                       | Break for ink splash only, with no gameplay effect                                            |

**Rules**

- **Breakable walls are intended routes.** Thin walls (Tier A) are tagged by designers as "break-through routes" and open new flank paths. The AI uses them.
- **Redundancy holds regardless.** The route graph (§5) and the two-cover rule are built on Tier S only, so no amount of destruction can break map connectivity or strip a combat space of all cover.
- **Persistence:** destruction lasts until the run ends. The next run resets the whole map.
- **Enemy adaptation:** Shooters and Shieldbearers re-evaluate cover when it breaks. Cover pieces are never required to win a fight.
- **Explosive interactions:** Bomber charges, Volatile enemies, gas stalls and boss attacks destroy Tier A pieces and can chain.
- **Readability:** Tier A objects show a faint **crack-line ink mark.** Tier S objects have none. Destruction effects are bold ink splashes and short dust, and debris clears from the walking plane within 2 seconds.
- **Budget:** a cap on live destructibles and debris (see §15). Destruction uses pre-authored fracture pieces, not free-form physics.

### 9.3 Scripted events

Occasional, telegraphed environmental events add variety. Each is announced about 8 seconds ahead by a siren and a short text line.

| Event           | Effect                                                                                                                                 | Rules                                                                          |
| --------------- | -------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------ |
| **Rain Shower** | A downpour on top of the baseline drizzle. Slippery rooftops: dashes and landings slide a little further. Rain streaks add ink texture | Telegraph visibility unchanged. Affects the Rooftops and Shrine Heights        |
| **Blackout**    | Neon lights go dark in a zone. The background dims and enemy silhouettes are lit by their own accents                                  | **Telegraph contrast is increased,** never reduced. Affects one zone at a time |
| **Canal Surge** | The Canals' water rises for about 20 seconds, covering the lower channels                                                              | Lethal only in flagged lower channels, with the usual 0.8 s warning            |

- Events happen roughly **every 6-8 waves,** starting at wave 7.
- Events **never occur on boss waves or elite events,** and never overlap each other.
- The wave director picks events without repeating until all have been seen.
- Events carry **no score bonus.** They exist to change how the map plays, not to reward surviving them, so the accessibility toggle that turns them off costs players nothing on the leaderboards.
- Events can be turned off with an **accessibility toggle.**

---

## 10. Secrets

Twelve secrets. All rewards are **non-power** (score, cosmetics, lore). All 12 are **always available** every run. Score rewards can be earned again each run. Cosmetics and lore are one-time.

| #   | Secret                    | Zone     | How to find                                                     | Reward                                                     |
| --- | ------------------------- | -------- | --------------------------------------------------------------- | ---------------------------------------------------------- |
| 1   | **Koi Pond Wall**         | Plaza    | A cracked wall behind the pond. Hit it with the sword           | 300 score + lore fragment 1                                |
| 2   | **Manhole Maze**          | Plaza    | The manhole near the pond. Opens the Hidden Network             | Access, and 200 score on first entry each run              |
| 3   | **Vending Machine Cache** | Plaza    | Slash the vending machine three times (a coin sound)            | An ammo cache + 200 score                                  |
| 4   | **Flooded Shrine**        | Canals   | Time the drain gate and slip through before it closes           | Sword trail: *Rainwater*                                   |
| 5   | **Pipe Whisper**          | Canals   | Follow a faint whisper from the pipes to a hidden alcove        | Lore fragment 2                                            |
| 6   | **Neon Kanji**            | Rooftops | Shoot out the sign's characters in the order shown by a flicker | Sword trail: *Neon Brush*                                  |
| 7   | **Broken Sign Roost**     | Rooftops | A hard climb along a collapsed sign                             | Outfit piece: *Rooftop Scarf*                              |
| 8   | **Roof Vent Drop**        | Rooftops | Open the vent. A risky drop to the Hidden Network               | 300 score + a shortcut                                     |
| 9   | **Bell of the Shrine**    | Shrine   | Shoot the bell during a breather                                | 500 score + a chime, and the breather lasts 3 s longer     |
| 10  | **Lantern Path**          | Shrine   | Follow the unlit lanterns, lighting each with a gun shot        | Cigarette ink style: *Lantern Fire*                        |
| 11  | **Server Room**           | Hidden   | A locked room opened with a code found in the lore fragments    | Lore fragments 3 to 6 (four terminals)                     |
| 12  | **The Old Dojo**          | Hidden   | Needs all fragments. Opens a sealed room                        | Codex entry on Ishikawa, and the **Dojo Memory** flashback |

**Rules**

- Secrets are never required to clear a wave, and never give a power advantage.
- **Hint evolution:** each secret has a subtle environmental hint (an ink stain, a sound, an odd glow). The hints get **subtler as the player finds more secrets,** so veterans are rewarded for observation. An accessibility option keeps hints at their strongest.
- Because the map is destructible, **secret walls and doors are Tier S or special-tagged** so they can't be broken by accident, and cannot be bypassed by breaking something else.
- Some secrets are intentionally **risky** (the Roof Vent Drop) and some are **safe** (between waves).
- The Hidden Network is the main home of secrets, so it always has something worth finding.
- Progress toward secrets is shown in the minimap and the Armory: found secrets are marked, unfound ones are not.

---

## 11. Navigation aids

- **Optional minimap, off by default.** A small brush-drawn map the player can toggle. It shows the player's position, visited zones, known ziplines and launch points, found secrets, zone heat tint, and the **boss location** during roaming boss fights. It does **not** show enemies.
- **Full map** in the pause menu, with the same information and zone names.
- **Landmarks** are the primary navigation: each zone has an unmistakable silhouette.
- **Directional audio** gives zone ambience that tells players roughly where they are.
- **First visit hints:** the first time Akane enters a zone, a brief zone name card appears in brush lettering.

---

## 12. Boss arenas

| Boss                     | Arena                                        | Notes                                                                                                                     |
| ------------------------ | -------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| **Katsuro**              | The Shrine Heights plateau                   | Flat, open and walled by the skyline. No ziplines cross it. Cover is only the torii pillars and lanterns (Tier S pillars) |
| **The Debt Collector**   | The Neon Plaza central square                | Food stalls and low walls give cover and sightlines. Stalls (Tier A) can be destroyed by his shots                        |
| **The Hunter**           | Rooftops, Canals and the Hidden Network      | No fixed arena. A roaming range, bounded by these zones. He avoids Shrine Heights                                         |
| **The Crimson Kite**     | Rooftops and Shrine Heights                  | Open sky. Perches on signage and lanterns                                                                                 |
| **The Demolisher**       | The central crane in the Rooftops (The Span) | Platforms and ziplines Z-B and Z-C are destroyed during the fight. Damage persists for the run                            |
| **The Floodgate Warden** | The floodgate chamber in the Canals          | A large chamber with walkways at three heights and a central channel                                                      |

**Rules**

- **Arena floors are Tier S** (except where a boss event destroys them on purpose).
- **Boss waves pause** zone heat, scripted events and spawns from other zones.
- **Boss damage persists** (the Demolisher): the route graph in §5 must stay valid afterwards.
- Other enemies fade out at a boss's arrival (see the boss document).

---

## 13. AI navigation requirements

- **Every traversal tool needs a navigation link:** walk, climb, drop, zipline, launch, vent, break-through wall and service lift.
- **Dynamic updates:** when a Tier A object breaks, the affected navigation links update the same frame (cover nodes are removed, walls become routes).
- **Size classes** (§3) are respected: no L-class enemy is routed through a vent or a ladder.
- **Tags** placed by designers:
  - **Perch nodes:** high spots for Archers and Snipers (about 10).
  - **Ambush nodes:** hidden spots for Phantoms (about 6, mostly in the Hidden Network and signage).
  - **Staging points:** side-route waypoints for Pincers (about 2 per zone).
  - **Cover nodes:** spots for Shooters and Shieldbearer lines (dynamic with destruction).
  - **Zipline anchors:** linked to ride and cut logic.
  - **Boss anchors:** fixed locations for bosses.
- **Failsafes** (see the enemy document §2.4) must work in the destructible map: an enemy stuck behind new rubble re-paths or respawns.

---

## 14. Art, audio and visual language

**Lighting and atmosphere.** Environment lighting, fog, particles and reflections follow the quality bar in the [Visual Briefs](akane-ii-visual-briefs.md) §9 (dynamic lighting with normal maps, gradient maps for zone color and the night clock, layered parallax, wet reflections).

**Night and weather.** The map is a rainy 2121 night, with light rain as the baseline and the lighting deepening across wave bands (late night, midnight, small hours, before dawn, and then a night that never ends). Wet surfaces add reflections of neon. Lighting bands never reduce the contrast of walkable surfaces, hazard marks or telegraphs.

### 14.1 Zone accent hues

Each zone has one accent hue over the shared ink-wash base:

| Zone             | Hue                                                     |
| ---------------- | ------------------------------------------------------- |
| Neon Plaza       | Neon pink                                               |
| Rooftop Signage  | Electric blue                                           |
| Shrine Heights   | Jade green                                              |
| Underpass Canals | Jade-teal                                               |
| Hidden Network   | Dim white (with the nearest zone's hue at the entrance) |

**Collision rule.** Boss and telegraph accent colors are reserved for threats. Several boss colors resemble zone hues (the Warden's teal and the Canals, the Debt Collector's gold and Shrine lanterns). To keep them apart:

- Zone hues appear only in **set dressing,** at low-to-medium saturation (capped at about 60%), and **never animate** like a threat.
- Boss and telegraph colors are high-saturation and appear only on threats and projectiles.
- The Crimson Kite's **lime** attacks resemble Shrine Heights' jade set dressing. The collision rule applies: jade is low-saturation and static, lime is high-saturation and animated, and the arena dims set-dressing accents during the fight if needed.
- Katsuro's **hot pink** trail (from the original) resembles the Plaza's neon pink, but he fights only on the Shrine plateau, whose set dressing is jade.
- If a zone hue and a boss color are too close in a given fight, the arena dims its set-dressing accents during the fight **[TBD: review with art]**.

### 14.2 Visual language

- **Walkable surfaces:** hard ink edges.
- **Climbable surfaces:** a consistent grip-line mark.
- **Destructible (Tier A):** a faint crack-line mark.
- **Hazards:** black-and-white hatching and a hum.
- **Secrets:** subtle, unique per secret (see §10).
- **Ziplines and updrafts:** distinct, bright ink strokes so they read at a glance.

### 14.3 Audio

- Each zone has a distinct **ambience** (see §4) that also helps orientation.
- **Zone transitions** crossfade over about 2 seconds.
- **Spatial cues** matter: zipline whines, updraft whooshes, hazard hums, hidden-secret whispers and rear-threat footsteps.
- Events (siren, rain, blackout) have clear audio cues.

---

## 15. Performance and platform budgets

All values are starting budgets **[TBD]**, in three tiers. The **original Switch is the floor:** content must run there. Switch 2 targets PC-mid budgets.

| Budget             | PC                                | Switch 2                        | Original Switch                    |
| ------------------ | --------------------------------- | ------------------------------- | ---------------------------------- |
| Active zones       | Current zone plus neighbors       | Same                            | Same, with tighter streaming       |
| Active enemies     | About 40, simple AI beyond 20     | About 40, simple AI beyond 20   | About 30, simple AI beyond 15      |
| Live destructibles | About 120                         | About 100                       | About 70                           |
| Debris pieces      | About 150                         | About 120                       | About 80                           |
| Destruction state  | Compact bitfield per destructible | Same                            | Same                               |
| Navigation updates | Incremental, queued               | Same                            | Same, with a lower per-frame limit |
| Output             | Scaled from 640 x 360             | 1080p handheld, up to 4K docked | 720p handheld, 1080p docked        |
| Frame rate         | Uncapped option, with caps        | Stable 60 FPS                   | Stable 60 FPS                      |

Destruction uses pre-authored fracture pieces, not physics simulation. The simulation runs at a fixed 60 Hz step, independent of the render frame rate.

---

## 16. Graybox acceptance checklist

The graybox is complete when all of these hold (for level designers and QA):

- [ ] Every pair of zones has two independent routes in both directions (§5), including for size L enemies.
- [ ] After the Demolisher's full destruction, the route graph still holds.
- [ ] Each open combat space has at least two side routes and two indestructible cover pieces.
- [ ] No straight sightline in an open space exceeds about 1.2 screens (except sniper perches).
- [ ] Plaza to Shrine Heights takes about 30-45 seconds with tools, and over a minute without.
- [ ] Every zone has a landmark visible from its neighbors.
- [ ] Every zipline, launch point and climb has a navigation link.
- [ ] Every lethal hazard has a 0.8 s warning, a safe alternative route, and the hazard mark.
- [ ] All 12 secrets are present, none can be broken by accident or bypassed, and each has a hint.
- [ ] Spawn points are all out of sight of the nearest player start and each other zone's entrances.
- [ ] Perch, ambush, staging and cover tags are placed and counted.
- [ ] Boss arenas meet their rules (§12).
- [ ] Budgets (§15) hold on Switch hardware in the worst-case fight.

---

## 17. Risks and open questions

**Risks**

1. **Broad destruction vs. route mastery and AI.** The tier system protects connectivity and cover, but it adds navigation updates, rebuild costs and test combinations. *Mitigation:* Tier S backbone, dynamic nav links, strict budgets, and early testing.
2. **Switch performance** with a large map, many enemies and destruction. *Mitigation:* zone streaming, budgets, simpler Switch effects.
3. **Zone heat tuning.** Too strong feels like punishment, too weak is ignored. *Mitigation:* redistribute budget instead of adding to it, and tune levels in playtests.
4. **Readability in a destructible, colorful world.** *Mitigation:* the collision rule (§14.1), crack-line marks, debris clearing, and Blackout's higher contrast.
5. **Scale of level design.** Five zones with sub-areas, 12 secrets and boss hooks is a lot for a graybox. *Mitigation:* build the route graph first, then the zones in order of the player's path.
6. **Secrets and destruction clashing.** *Mitigation:* tagging rules in §10.

**Open questions**

- [x] Zone wake-up schedule: **removed** (it created free camping spots). Replaced by spawn-follow (§8.2).
- [x] Accent hue collisions with boss colors: the approach is decided (§14.1), art review confirms specific cases.
- [x] Event frequency (tune in playtests).
- [x] Exact map scale and crossing times (graybox).
- [x] Heat values and effects (tune).
- [x] Budgets for destructibles and debris (Switch tests).

---

# Akane II — Loadout Detail

Companion to [akane-ii-design.md](akane-ii-design.md) (§3.6 Loadout).

> All stats are **starting values for tuning**. Frames are at 60 FPS. Distances are in tiles (one tile is roughly Akane's body width). Item names for the original game's equipment are kept for continuity, but their behavior here is new.

## Contents

1. [Katanas](#1-katanas)
2. [Guns](#2-guns)
3. [Gadgets](#3-gadgets)
4. [Boots](#4-boots)
5. [Unlock challenges and order](#5-unlock-challenges-and-order)
   (gadget mods are in §3.2)
6. [Synergies and balance checks](#6-synergies-and-balance-checks)

---

## 1. Katanas

Sword rules shared by all katanas:

- A swing can **deflect bullets** during its active frames (the Rebi has a wider window).
- Sword kills **refill gun ammo** (see §2).
- Sword kills build the combo and Flow like any other kill.

| Katana         | Reach       | Startup | Active | Recovery | Combo       | Notes                                                                                                                                                                             |
| -------------- | ----------- | ------- | ------ | -------- | ----------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Kuro**       | 1.5         | 4 f     | 5 f    | 10 f     | 2-hit       | Baseline. Moves 0.3 tiles forward on each swing                                                                                                                                   |
| **Rebi**       | 1.4         | 6 f     | 8 f    | 14 f     | 1-hit       | 160° arc. **Deflect window 8 f** (twice Kuro's). Deflected bullets return at the shooter at double speed                                                                          |
| **Tadus**      | 1.3 (melee) | 5 f     | 5 f    | 12 f     | 1-hit       | **Throw:** range 8, flies 0.3 s, then waits. **Recall** returns it along a line in 0.8 s, killing on the way back. Akane has **no melee while it is out.** Auto-recalls after 3 s |
| **Nodachi**    | 2.6         | 9 f     | 7 f    | 18 f     | 1-hit       | Leaves a **lingering ink arc** for 0.3 s that kills anything passing through it                                                                                                   |
| **Twin Tantō** | 0.9         | 3 f     | 3 f    | 6 f      | 3-hit chain | Hits **both sides** at once. Each chain hit moves Akane slightly forward                                                                                                          |
| **Echo Blade** | 1.4         | 5 f     | 5 f    | 11 f     | 1-hit       | Each swing is repeated in place **1.0 s later** (same angle, same position). One echo at a time, and an echo kills like a swing                                                   |
| **Kusarigama** | 1.3 (melee) | 5 f     | 5 f    | 11 f     | 1-hit       | **Chain:** range 4. On a standard enemy it pulls the enemy to Akane. On an anchor point (ledge, pole) it pulls Akane there. 3 s cooldown. Does not pull Tanks or bosses           |

**Design notes**

- **Kuro** is intentionally good, not just a placeholder: it is the baseline every other katana must have a reason to be picked over.
- **Rebi** is the defensive choice. It pairs with Shooter-heavy waves and the Debt Collector.
- **Tadus** has a real cost (no melee while it's out), and a real reward (a long-range kill that can clear a line of enemies).
- **Nodachi** wants space and Anchor enemies (it punishes slow Tanks and Lancers).
- **Twin Tantō** is the crowd cutter, and weak to Lancers and anything with reach.
- **Echo Blade** is for planners: swing, then dash through the same spot as the echo lands.
- **Kusarigama** doubles as a mobility tool, and brings Archers and Snipers within reach.

---

## 2. Guns

Gun rules shared by all guns:

- **Sword kills refill ammo,** by the amount in the table.
- **Passive regeneration:** if empty, one round returns every 2.5 s (so Akane is never fully without a gun).
- Guns cannot be used while Akane holds a human shield, except to fire past the shield (a shield does not block Akane's own shots).
- Bullets are blocked by Shieldbearer fronts, except the Magnum.
- **Gun kills are headshots,** as in the original. Every bullet kill is a hit to the head, so the Tank is immune to bullets except the Magnum and a human shield covers the head. Kill reactions are in the [Visual Briefs](akane-ii-visual-briefs.md) §10.

| Gun                            | Ammo         | Fire rate                                | Sword-kill refill | Range        | Notes                                                                                                                                 |
| ------------------------------ | ------------ | ---------------------------------------- | ----------------- | ------------ | ------------------------------------------------------------------------------------------------------------------------------------- |
| **Patron v26**                 | 6            | 4 / s                                    | +1                | 12           | Accurate and forgiving. Single target                                                                                                 |
| **Inquisitor M103**            | 9 (3 bursts) | One burst of 3 in 0.25 s, then 0.4 s gap | +1                | 12           | Burst shots fan slightly, covering movement. Good against flyers like drones                                                          |
| **Vicious S36**                | 30           | 12 / s                                   | +4                | 8            | Sprays with spread. **Recoil pushes Akane back** 0.25 tiles per 3 shots, so firing while facing a wall is a mobility trick            |
| **Magnum XT5**                 | 3            | 1 shot per 1.2 s                         | +1                | 14           | **Pierces** every enemy in line, including through Shieldbearer shields and Tank armor. Slow                                          |
| **Double Barrel**              | 2            | 2 shots, then 0.8 s                      | +1                | 4 (40° cone) | Kills everything in the cone. **Knocks Akane back** 2 tiles                                                                           |
| **Gravitational Beam Emitter** | 4 charges    | Hold to aim, release to pull             | +1                | 7            | **Pulls** enemies in a 2-tile radius to a point over 0.6 s and **holds** them for 1.5 s. Does not kill. Does not pull Tanks or bosses |

**Design notes**

- **Patron** is the baseline. It's deliberately useful in every situation.
- **Inquisitor** is for players who like ranged control.
- **Vicious** rewards skilled recoil use and punishes spraying without a plan.
- **Magnum** is a plan-ahead weapon with a three-shot budget.
- **Double Barrel** is the panic button and a crowd clearer.
- **GBE** is a setup tool and needs a follow-up from the sword, a Dragon Slash or the Echo Blade.

---

## 3. Gadgets

One slot. All 11 gadgets are strong enough to carry a run. Charges and cooldowns refill during play, and **never** carry between waves in a way that lets them stockpile (cooldowns continue during breathers).

| Gadget                     | Type      | Behavior                                                                                                                                               | Numbers                              |
| -------------------------- | --------- | ------------------------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------ |
| **Cyber Gloves**           | Passive   | Human shields absorb **4 hits** (not 3). Akane can **dash while holding a shield.** Throws hit harder (the thrown enemy kills two more on impact)      | Passive                              |
| **Marionette Wire**        | Active    | Wire the held enemy. It becomes a **puppet** that walks ahead as a shield for 5 s (absorbs 3 hits), then detonates or drops on release                 | 25 s cooldown                        |
| **Katana Gun**             | Passive   | Every sword swing also fires a shot along the swing arc                                                                                                | Uses 1 ammo per swing                |
| **Magnetic Pulse Emitter** | Active    | EMP burst: disables cybernetic enemies for 3 s (Shooters, Cyber Ninjas, Drone Handlers and their drones), strips Tank plating, reveals cloaked enemies | Radius 3.5. Cooldown 25 s            |
| **Stabilizer Bracelet**    | Passive   | **Ink Step window +20%**, a successful Ink Step refunds **both** dash charges (the base rule refunds one), and gun recoil is removed                   | Passive                              |
| **Adrenaline Shot**        | Triggered | After an **8-kill chain within 6 s,** time slows to 40% for enemies for 3 s. Akane moves at normal speed                                               | Cooldown 20 s                        |
| **Grapple Anchor**         | Active    | Grapples a ledge, zipline anchor or standard enemy and pulls Akane there                                                                               | Range 9. 2 charges. 4 s recharge     |
| **Updraft Fan**            | Active    | Places a fan that launches Akane upward (about 6 tiles). Enemies can also be launched                                                                  | 2 fans out at once. Each lasts 25 s  |
| **Hologram Decoy**         | Active    | A fake Akane that enemies target for 4 s. Disrupts flanking and token assignment                                                                       | Cooldown 18 s                        |
| **Sumi Bomb**              | Active    | An ink cloud that blocks line of sight for 5 s. Enemies lose tracking                                                                                  | Radius 3.5. 2 charges. 14 s recharge |
| **Lure Beacon**            | Active    | Thrown device that attracts enemies for 5 s. Does not affect bosses                                                                                    | Cooldown 15 s                        |

**Design notes**

- **Passive gadgets** (Gloves, Katana Gun, Stabilizer) are always on, so they must be worth a slot without an activation. Their value is steady and skill-rewarding.
- **Active gadgets** give a go-to moment. Cooldowns are short enough to use several times per wave.
- Gadgets never grant invulnerability.

---

### 3.1 Original gadgets (verified) and what Akane II does with them

Checked online against the fan wiki and an achievements guide (see the sources below). The original had **11 gadgets.** We kept five names for continuity and redesigned their behavior; the other six Akane II gadgets are new designs, so the original's remaining names are not needed.

| Gadget                     | Original effect (verified)                     | Original unlock                                       | Akane II                                           |
| -------------------------- | ---------------------------------------------- | ----------------------------------------------------- | -------------------------------------------------- |
| **Cyber Gloves**           | More bullets per enemy killed                  | 30 kills with 100% katana accuracy                    | Human shield upgrade (no ammo effect)              |
| **Stabilizer Bracelet**    | Deflected bullets are divided in two           | Reach the first boss with a 50+ combo                 | Wider Ink Step window, full dash refund, no recoil |
| **Adrenaline Shot**        | Time slows in adrenaline mode                  | Defeat the boss with a katana special                 | Slow motion on an 8-kill chain                     |
| **Magnetic Pulse Emitter** | Kills one nearby enemy per second while aiming | Kill a Cyber Ninja with a katana special at 50+ combo | EMP that disables cyber enemies and strips armor   |
| **Katana Gun**             | Shoots after a special move                    | Defeat the boss at max level with a special           | A shot on every sword swing                        |

**Other original gadgets** (names only, not used): Scope Visor, Extended Magazine, Nano Watch, Magnetic Detractor, Smart Bullets, plus two not documented in the sources. None of Akane II's new gadgets (Marionette Wire, Grapple Anchor, Updraft Fan, Hologram Decoy, Sumi Bomb, Lure Beacon) reuse these names.

**Continuity nods (decided):** the four original effects return as optional **gadget mods** (see §3.2), as trade-offs, not add-ons.

**Sources**

- [Akane Fandom wiki: Gadget](https://akane.fandom.com/wiki/Gadget)
- [Steam guide: All Achievements and How to Unlock](https://steamcommunity.com/sharedfiles/filedetails/?id=2925585160)
- [Akane Fandom wiki: Cyber Gloves](https://akane.fandom.com/wiki/Cyber_Gloves)
- [Akane Fandom wiki: Katana Gun](https://akane.fandom.com/wiki/Katana_Gun)

### 3.2 Gadget mods

On the same loadout screen as the katana, gun and gadget, a **mod chip** appears on the gadget slot when the equipped gadget has a mod. It is off by default and can be toggled freely before a run. A mod is a **swap: it replaces part of the gadget with something else, and never adds power on top.** Only six gadgets have a mod.

| Gadget                     | Mod                    | The mod gives                                                                                                              | The mod takes away                                                                             | Unlock challenge                                                                    |
| -------------------------- | ---------------------- | -------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| **Cyber Gloves**           | **Original Spec**      | +1 ammo on every sword kill (on top of the gun's own refill)                                                               | The shield perks: shields hold 3 hits again, no dashing while holding one, base throw strength | Get **30 sword kills** in a row with no missed swings, in a single run              |
| **Stabilizer Bracelet**    | **Original Spec**      | Deflected bullets **split in two** (both can kill)                                                                         | The wider Ink Step window (the full dash refund and no recoil stay)                            | Reach the first boss (wave 10) with a **50+ combo**                                 |
| **Magnetic Pulse Emitter** | **Original Spec**      | While aiming the gun, **kills one weak enemy per second** within the radius (not Tanks, Shieldbearers, Duelists or bosses) | The armor stripping and the cloak reveal. Aiming speed is 30% slower while it is active        | Kill a **Cyber Ninja with Dragon Slash or Dragon Slayer** at a 50+ combo            |
| **Katana Gun**             | **Original Spec**      | After **Dragon Slash or Dragon Slayer**, fires a cone burst (about 4 tiles) that kills standard enemies                    | The per-swing shot                                                                             | Land the final phase-ending hit on a **Tier 3 or higher boss** with a Dragon Slayer |
| **Grapple Anchor**         | **Swing Line** *(new)* | Grapples anchor points and **swings like a pendulum**, keeping momentum and allowing attacks mid-swing                     | One charge instead of two, and it can no longer grapple enemies                                | Use the Grapple Anchor **100 times** in total                                       |
| **Hologram Decoy**         | **Overload** *(new)*   | The decoy **explodes at the end**, killing standard enemies within 3 tiles                                                 | The decoy lasts 2 seconds instead of 4, so it disrupts flanking for less time                  | Kill **25 enemies** that were targeting a Hologram Decoy, in total                  |

**Rules**

- A mod requires its gadget to be unlocked first. A mod's challenge can only be progressed with that gadget equipped (the three "total" challenges, Swing Line, Overload and the Katana Gun one, count only with the right gadget).
- Four mods are the original's effects ("Original Spec"), with unlock challenges adapted from the original's unlock conditions. Two are new designs for Akane II.
- Mods obey the loadout rules: **no strict upgrades, a clear cost, and no required mod** for any wave or boss.
- Leaderboards are not split by mod.
- More mods for the remaining gadgets are a candidate for later content (for example, the paid expansion).

**Design notes**

- **Original Spec (Cyber Gloves)** turns a shield-support gadget into an ammo engine. It is the best mod for gun-heavy loadouts, and it gives up the human shield perks.
- **Original Spec (Stabilizer)** swaps a defensive window for offensive deflects. It pairs with the Rebi katana and the Double Barrel unlock.
- **Original Spec (Pulse Emitter)** is an aimed crowd thinner. Since it only kills weak enemies, it still needs the sword and gun for armored ones.
- **Original Spec (Katana Gun)** rewards saving a special for a big finish.
- **Swing Line** is a movement sidegrade for players who like momentum over precision.
- **Overload** turns a defensive decoy into a bomb for crowd fights, at the cost of the longer flank disruption.

---

## 4. Boots

| Boots            | Dash length | Charges | Recharge | Notes                                                                                                  |
| ---------------- | ----------- | ------- | -------- | ------------------------------------------------------------------------------------------------------ |
| **Standard**     | 3.5         | 2       | 1.5 s    | Baseline                                                                                               |
| **Geta Springs** | 3.0         | 2       | 1.5 s    | Dash can angle **45° upward** (a leap). Strong on the vertical map                                     |
| **Rail Skates**  | 5.0         | 2       | 1.8 s    | A longer dash that **slides** for 0.4 s after, with reduced steering                                   |
| **Silent Tabi**  | 2.5         | 2       | 1.0 s    | Short, quiet and fast to recharge. Enemies that lose sight of Akane **forget her position 2 s sooner** |

**Design notes**

- The dash remains the safe, reliable tool. Boots choose its flavor.
- Silent Tabi is the one oddball. Its effect is subtle, so its real value is faster recharge and the AI interaction.

---

## 5. Unlock challenges and order

Every item (except starters) is unlocked by an **item-specific mastery challenge** that teaches the skill the item rewards. Challenges are completed in normal arcade runs. Progress counters for "total" challenges accumulate across runs. Unlocks never raise power (see the main doc §8).

**Starting items:** Kuro, Patron v26, Standard boots, Cyber Gloves (it teaches the new shield mechanic). Players may also choose **no gadget.**

| Item                           | Slot   | Tier  | Unlock challenge                                                                        | What it teaches     |
| ------------------------------ | ------ | ----- | --------------------------------------------------------------------------------------- | ------------------- |
| Kuro                           | Katana | Start | none                                                                                    | The baseline        |
| Patron v26                     | Gun    | Start | none                                                                                    | The baseline        |
| Standard boots                 | Boots  | Start | none                                                                                    | The baseline        |
| Cyber Gloves                   | Gadget | Start | none                                                                                    | Human shields       |
| **Rebi**                       | Katana | 1     | Deflect **15 bullets** in a single run                                                  | Deflecting          |
| **Twin Tantō**                 | Katana | 1     | Reach **Flow III** (30 combo) in a single run                                           | Chaining kills      |
| **Inquisitor M103**            | Gun    | 1     | Get **40 gun kills** in a single run                                                    | Gun use             |
| **Stabilizer Bracelet**        | Gadget | 1     | Land **25 perfect Ink Steps** in total                                                  | Ink Step            |
| **Adrenaline Shot**            | Gadget | 1     | Reach a **20-kill combo**                                                               | Keeping combos      |
| **Geta Springs**               | Boots  | 1     | Use **20 launch points** in total                                                       | The vertical map    |
| **Nodachi**                    | Katana | 2     | Kill **5 enemies with one swing**                                                       | Positioning         |
| **Tadus**                      | Katana | 2     | Kill **3 or more enemies with one throw,** 5 times                                      | Ranged melee        |
| **Double Barrel**              | Gun    | 2     | Kill **25 enemies by deflecting bullets** in a single run *(from the original)*         | Deflecting          |
| **Magnum XT5**                 | Gun    | 2     | Kill **3 enemies with one gun shot,** 5 times                                           | Lining up shots     |
| **Katana Gun**                 | Gadget | 2     | Alternate gun and sword kills for **20 kills** (no more than 2 of the same in a row)    | Mixing tools        |
| **Magnetic Pulse Emitter**     | Gadget | 2     | Kill **10 cybernetic enemies** (Shooters, Cyber Ninjas, Drone Handlers) in a single run | Cyber counters      |
| **Grapple Anchor**             | Gadget | 2     | Spend **60 seconds on ziplines** in a single run                                        | Traversal           |
| **Rail Skates**                | Boots  | 2     | Travel **5,000 tiles** by dashing in total                                              | Dash use            |
| **Echo Blade**                 | Katana | 3     | Kill **3 enemies with one Dragon Slash,** 3 times                                       | Setups              |
| **Kusarigama**                 | Katana | 3     | Get **15 kills from a zipline** in total                                                | Combat on the move  |
| **Vicious S36**                | Gun    | 3     | Get **30 gun kills within 2 seconds after a dash** in total                             | Moving and shooting |
| **Hologram Decoy**             | Gadget | 3     | Kill **20 Skirmishers** in total                                                        | Counter-flanking    |
| **Sumi Bomb**                  | Gadget | 3     | Kill **10 Shooters or Archers** in a single run                                         | Counter-ranged      |
| **Lure Beacon**                | Gadget | 3     | Kill **30 enemies with Dragon Slash or Dragon Slayer** in total                         | Specials            |
| **Updraft Fan**                | Gadget | 3     | Find **5 secrets** in total                                                             | Exploration         |
| **Silent Tabi**                | Boots  | 3     | Kill **30 enemies from behind** in total                                                | Positioning         |
| **Marionette Wire**            | Gadget | 3     | Kill **3 enemies with one thrown human shield,** 10 times                               | Shield mastery      |
| **Gravitational Beam Emitter** | Gun    | 4     | Defeat **Katsuro and one other boss** in a single run *(capstone)*                      | Late-game skill     |

**Rules**

- Challenges are chosen so that at least one is reachable in the first few runs, and the tier-4 capstone is a long-term goal.
- A challenge counter shows in the loadout screen so players know what to work on.
- No challenge requires an item that has not been unlocked.
- "In total" counters never reset. "In a single run" counters reset on death.

---

## 6. Synergies and balance checks

### 6.1 Strong, intended combinations

| Combo                                           | Why it works                                       |
| ----------------------------------------------- | -------------------------------------------------- |
| Kusarigama + Grapple Anchor                     | Two pull tools: fast through the whole map         |
| Twin Tantō + Adrenaline Shot                    | Crowd chains keep Flow III and trigger slow motion |
| Gravitational Beam + Echo Blade or Dragon Slash | Clump, then one kill covers many                   |
| Magnum + Shieldbearer waves                     | A single piercing shot cuts through                |
| Rebi + Stabilizer Bracelet                      | Defensive stack. Strong against Shooter waves      |
| Cyber Gloves + Tadus                            | Throw a shield, then a blade                       |

### 6.2 Combinations to watch for balance

| Combo                                  | Risk                              | Mitigation to check                                               |
| -------------------------------------- | --------------------------------- | ----------------------------------------------------------------- |
| **Rebi + Stabilizer + Sumi Bomb**      | Turtling                          | The combo decay and wave pressure timer make passivity cost score |
| **Double Barrel + Gravitational Beam** | Clump and cone clears every wave  | The beam's hold is short and ammo is low                          |
| **Vicious S36 + Rail Skates**          | Mobility spam with sustained fire | Short gun range, ammo burn                                        |
| **Tadus + Cyber Gloves**               | A safe ranged kill loop           | Tadus leaves Akane unarmed while it is out                        |
| **Echo Blade + Lure Beacon**           | Pre-set trap kills                | Beacon's short duration, echo timing                              |

### 6.3 Balance rules

- **No loadout trivializes a boss.** Each boss has two or more ways to land a hit, and none rely on a single item.
- **Top-run diversity:** in playtests, no single item should appear in more than about 25% of the top runs within its slot **[TBD]**.
- **Ammo economy:** no gun should sustain fire without sword kills for longer than a few seconds.
- **Gadget uptime:** the cooldown of an active gadget should not allow near-permanent coverage.
- **Every item has a weakness** that a specific enemy or boss exploits.

---

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
| --------- | -------------------- |
| None      | x1.0                 |
| Flow I    | x1.5                 |
| Flow II   | x2.0                 |
| Flow III  | x3.0                 |

### 2.2 Style bonuses (flat, not multiplied)

| Action                                          | Bonus **[TBD]** |
| ----------------------------------------------- | --------------- |
| Perfect Ink Step                                | +150            |
| Deflect kill                                    | +100            |
| Human shield kill                               | +100            |
| Thrown shield, per extra enemy beyond the first | +50             |
| Zipline kill                                    | +100            |
| Kill from behind                                | +50             |
| Dragon Slayer, per enemy beyond the fifth       | +25             |
| Boss phase break                                | +300            |

**Variety rule:** repeating the same style action within 5 seconds gives half the bonus each time, so players are rewarded for mixing tools and not spamming one.

### 2.3 Wave and event bonuses

| Event                                   | Bonus **[TBD]**                                                        |
| --------------------------------------- | ---------------------------------------------------------------------- |
| Early clear (before the pressure timer) | `100 x wave x (remaining timer fraction)`                              |
| Elite event cleared                     | Score from kills is **x1.5,** plus a flat 500 x event tier             |
| Boss defeated                           | `2,000 x boss number`, with a speed bonus for finishing under par time |
| Secret found                            | 200-500 (once per run)                                                 |

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

| Trial           | Effect                                                                  | Multiplier **[TBD]** |
| --------------- | ----------------------------------------------------------------------- | -------------------- |
| **Rush Hour**   | Enemies move 20% faster, and the pressure timer is 15% shorter          | x1.3                 |
| **Dry Chamber** | The gun starts with 3 rounds, and sword kills refill half as much       | x1.25                |
| **Glass Edge**  | The Ink Step window is 30% smaller                                      | x1.3                 |
| **Full House**  | The wave budget is 30% larger                                           | x1.4                 |
| **Blind Ink**   | A smaller vision radius with fog at the edges (telegraphs stay visible) | x1.2                 |
| **Bare Hands**  | No gadget allowed                                                       | x1.15                |

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

| Lesson       | Teaches                                      |
| ------------ | -------------------------------------------- |
| Movement     | Moving, jumping, dashing and charges         |
| Sword        | Slashing, combos and deflecting              |
| Gun          | Aiming, ammo and the refill rule             |
| Ink Step     | The precise dodge, its window and its reward |
| Human shield | Grabbing, hit limit, throwing                |
| Flow         | The combo, the tiers and the decay           |
| Specials     | Dragon Slash and Dragon Slayer               |
| Traversal    | Ziplines, launch points and climbing         |
| Gadgets      | Trying any gadget on a dummy                 |

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

| Element                                          | Location                                           | Behavior                                                        |
| ------------------------------------------------ | -------------------------------------------------- | --------------------------------------------------------------- |
| **Dash charges**                                 | Near Akane (two ink dots under her feet)           | Always visible while recharging; fade when full                 |
| **Flow tier and aura**                           | Around Akane, plus a small pip row at the top left | The aura grows with the tier. A pulse warns before a tier drops |
| **Special meters** (Dragon Slash, Dragon Slayer) | Left edge, two thin vertical ink strips            | Glow when ready                                                 |
| **Ammo**                                         | Bottom right, as ink drops                         | Shows the remaining rounds or charges                           |
| **Gadget**                                       | Bottom right, beside the ammo                      | The icon and cooldown ring                                      |
| **Score and combo**                              | Top right                                          | Small. The combo number appears only while a combo is active    |
| **Wave and pressure timer**                      | Top center                                         | A brush-stroke bar that shrinks. Hidden on boss waves           |
| **Boss phase pips**                              | Top center (replaces the timer)                    | Pips for the remaining phases                                   |
| **Boss name**                                    | Top center, briefly                                | At the start of the fight                                       |
| **Off-screen threat indicators**                 | Screen edges                                       | Ink smears (see 5.2)                                            |

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

---

# Akane II — Story, Endgame, Modes and Cosmetics

Companion to [akane-ii-design.md](akane-ii-design.md) (§9 Story and Presentation, §8 Progression and Scoring).

> Names are working titles. All text is **draft copy.** It builds on one confirmed fact: the original has no definitive ending, and **Akane is alive** (see §4).

## Contents

1. [Premise and tone](#1-premise-and-tone)
2. [Cast](#2-cast)
3. [Story structure: milestone beats](#3-story-structure-milestone-beats)
4. [Canon handling](#4-canon-handling)
5. [Voice rules](#5-voice-rules)
6. [Endgame: Overdrive tiers (wave 50+)](#6-endgame-overdrive-tiers-wave-50)
7. [Extra modes: Boss Rush and Time Attack](#7-extra-modes-boss-rush-and-time-attack)
8. [Cosmetics roster](#8-cosmetics-roster)
9. [Open items](#9-open-items)

---

## 1. Premise and tone

**Premise.** Mega-Tokyo, **2121,** the **same night** as the first game, continuing right after Akane's Last Stand. She was cornered by a horde and made her final stand in the rain-soaked neon streets, and she walked out alive. Now a single Yakuza head, **Oyabun Tsukumo,** controls the vertical district ahead of her: its roofs, its canals, its shrines and the infrastructure beneath. Akane has come to **finish the fight,** and the only way is up, through everything his organization sends. The whole game is **one night.**

**Tone.** Terse, dry and noir. Sparse text and no exposition dumps. The world tells the story (codex entries, logs, boss title cards) and Akane says almost nothing.

**Structure of the experience.** The game stays an **infinite arcade.** Story is delivered in short text beats at set waves (§3), plus the codex, lore fragments and the Dojo Memory. There are no cutscenes and no voiced lines.

**Why it fits the design.** The map climbs from the Canals to Shrine Heights, so "taking the district from the top down" is a spatial story: the higher Akane fights, the closer she gets to Tsukumo.

---

## 2. Cast

### Akane (Sugahara Akane)

- An **adult,** as she was during the first game's main gameplay. Her childhood appears only in flashbacks: the original's tutorial (about 23 years before the main game) and its Final Scene.
- The protagonist. Terse, dry, driven.
- **Goal:** finish the fight. The Yakuza will not stop, so she goes after the head of the district's hold on the city.
- Her relationship with the dojo and Ishikawa is the emotional undertone (see the lore fragments), never exposition.

### Katsuro (the Nemesis)

- **Rebuilt and obsessed.** Within this one night, the Yakuza's field engineers rebuild him with cybernetics each time he is beaten, and he **remembers every defeat.** His evolution across a run is the story: he is learning Akane.
- **The rebuilds are a noir conceit.** A man rebuilt within hours, again and again, is meant to read as myth, not engineering. The text never explains the mechanics, and nobody asks.
- He has no stated personal tie to her. That keeps him compatible with the original.
- In text he is treated like a force of nature: patient, relentless, and a little sad.

### Oyabun Tsukumo (the power behind the bosses)

- The head of the district, who **never appears in a fight.**
- Appears only as a **voice and an ink-silhouette** in the milestone beats (§3), in codex entries and in log fragments. His name is revealed at wave 100.
- Runs the district as a business: debts, contracts, construction, utilities. Every boss maps to one of his departments (below).
- *Tsukumo* is a working name. It echoes *tsukumogami* (objects that gain a spirit), a nod to the rebuilt and the cybernetic.

### The bosses as Tsukumo's departments

| Boss                 | Department                      |
| -------------------- | ------------------------------- |
| Katsuro              | Enforcement (rebuilt each time) |
| The Debt Collector   | Collections                     |
| The Hunter           | Contracts (assassination)       |
| The Crimson Kite     | Air Security                    |
| The Demolisher       | Construction                    |
| The Floodgate Warden | Utilities                       |

Codex entries and title cards may reference the department lightly, never in more than a line.

---

## 3. Story structure: milestone beats

Story beats appear at waves **25, 50, 75 and 100,** during the breather. Each is a short text card (readable in a few seconds), with an ink silhouette image, and awards a cosmetic (see §8). Beats can be skipped with one button, and are re-readable from the codex.

The final text for all four beats and the post-100 rotation is in the [Copy Deck](akane-ii-copy-deck.md) (§3 and §4), which is the source of truth for all player-facing text. Summary:

| Wave | Title       | Hour        | Tsukumo's stance                                                          | Reward          |
| ---- | ----------- | ----------- | ------------------------------------------------------------------------- | --------------- |
| 25   | The Count   | Midnight    | Businesslike, dismissive ("My accountants are impressed. I am not. Yet.") | Rain Coat       |
| 50   | The Ledger  | Small Hours | Treats her as an expensive problem and offers a price                     | Night Courier   |
| 75   | The Archive | Before Dawn | Admits Katsuro is learning from her, and that she is a good student too   | Gold Leaf trail |
| 100  | The Name    | Still Night | Gives his name and admits he has enjoyed the night                        | Gilded Jacket   |

His respect grows across the four beats, without ever turning sentimental. After wave 100 a short beat plays every 25 waves from an eight-line pool (copy deck §4).

### Delivery rules

- A beat appears only on the first time that wave is reached in a run.
- Beats never interrupt a fight: they play in the breather before the next wave.
- A beat's cosmetic is awarded once (see §8).
- Beats are skippable, and never block restarts.

---

### Night clock

The whole run is **one night,** and the night deepens as waves pass. **Dawn never comes.**

| Waves | Hour (story beat label) | Lighting and weather                                           |
| ----- | ----------------------- | -------------------------------------------------------------- |
| 1-24  | Late night              | Dusk-blue sky, steady drizzle, bright neon                     |
| 25-49 | Midnight                | Deep blue-black, heavier rain, neon strongest against the dark |
| 50-74 | Small hours             | The darkest band, with sparse lights and long reflections      |
| 75-99 | Before dawn             | A faint pale glow on the horizon, rain thinning                |
| 100+  | "Still night"           | The glow never grows. The hour never changes                   |

**Rules**

- Lighting shifts are gradual (over several waves), and the **zone accent hues and telegraph colors stay readable** in every band (the Blackout event raises telegraph contrast, and the same applies to the darkest band).
- The weather baseline is **light rain** ("rain-soaked neon streets" from the first game). The Rain Shower event is a **downpour,** not the first rain.
- Story beats are labeled with the hour (*Midnight*, *Small Hours*, *Before Dawn*, *Still Night*), so the text marks the night's progress.
- Past wave 100 the night simply does not end, which keeps the one-night framing and the endless arcade compatible.

---

## 4. Canon handling

**Confirmed canon:** the original *Akane* has **no definitive ending, and Akane is alive.** This story builds on exactly that and nothing more.

**Rules**

- **Akane is alive,** and the story can say so plainly.
- **It is the same night.** Akane II continues the first game's night in 2121 and covers one night only. Nothing is dated beyond that night, and no scene shows daylight.
- Do not add detail about how the Last Stand ended, or about what happened to anyone else in it. Refer to it as **"the Last Stand"** or **"earlier tonight."**
- Do not invent an ending for the original. The first game's open ending stays open.
- The original's **Final Scene** and its **tutorial** are real flashbacks to Akane's childhood (the tutorial is set about 23 years before the main game; the Final Scene shows her as a child facing Ishikawa). Treat both as history. **Akane was an adult during the first game's gameplay and is an adult in Akane II,** and nothing in the present story shows her as a child.
- Do not restage or retell the original's Final Scene. Akane II's own flashback (the Dojo Memory) shows a different moment.
- Never describe the original beyond what the Final Scene already shows.
- Akane II's story starts at a new point in time and does not need the first game's outcome beyond her survival.
- The Katsuro explanation (rebuilt, obsessed) works whether or not the first game defeated him.

**Text affected**

- The **intro card** states that the night is not over and that Akane walked out of the Last Stand alive (see the narrative document).
- **Lore fragment 3** reads "Status: alive."

---

## 5. Voice rules

**Akane**

- Short, flat and dry. At most **about 8 words** per line.
- Speaks only at the four story beats and in a few codex-style item lines.
- Never explains herself, never jokes openly, and never raises her voice in text.

**Tsukumo**

- Calm and businesslike. Speaks like someone reading a ledger.
- Talks about cost, debt and accounts, never about hatred.
- His respect for Akane grows across the four beats.

**Narrator / codex**

- Dry, noir, and observational. One or two lines per entry.

**Banned in text:** exposition about the first game's plot, jokes that break tone, and any statement of Akane's feelings.

---

## 6. Endgame: Overdrive tiers (wave 50+)

After wave 50 the run enters **Overdrive.** No new content is required: escalation comes from stacking existing systems. Each tier starts every 25 waves.

| Tier              | Waves | Escalation                                                                                                              |
| ----------------- | ----- | ----------------------------------------------------------------------------------------------------------------------- |
| **Overdrive I**   | 50-74 | Modifier chance on non-boss enemies rises to 30%. Elite events can combine two themes. Token limits reach their maximum |
| **Overdrive II**  | 75-99 | Modifier chance 45%. Environmental events can overlap with a normal wave (never with a boss)                            |
| **Overdrive III** | 100+  | Modifier chance 60%. Elite events combine three themes                                                                  |

**Bosses** are handled separately, by **boss tier** (see the main doc §7.1). Tier 4 starts at wave 100, Tier 5 at wave 150 and Tier 6 at wave 200. Their extra moves are listed in the boss document (§9).

**Rules**

- Escalation never shortens telegraphs, and never removes weak windows.
- Overdrive modifiers never apply to bosses.
- The Flow stall clock for bosses scales with the added moves (par times go up by 10% per tier from Tier 4).

---

## 7. Extra modes: Boss Rush and Time Attack

Both modes unlock after the player **defeats Katsuro once,** like Trials. Both use the player's own unlocked loadout. Both support the full set of accessibility options (assists mark a run as non-ranked).

### 7.1 Boss Rush

- **What:** fight all six bosses back to back in a fixed order: Katsuro, the Debt Collector, the Hunter, the Crimson Kite, the Demolisher, the Floodgate Warden.
- **Tier:** every boss is at Tier 1. The bosses do not gain moves in Boss Rush.
- **Structure:** a 10-second breather between bosses. Flow carries over. There are no normal waves and no events.
- **Death:** one hit ends the run, like arcade.
- **Score:** based on total time, plus bonuses for speed under par and for perfect Ink Steps. Leaderboard: **fastest clear.**
- **Trials:** not available.
- **Reward:** a cosmetic for the first clear (*Salt Wake* trail).

### 7.2 Time Attack

- **What:** reach **wave 30** (including the three boss waves) as fast as possible.
- **Rules:** the normal wave rules, but with no breathers beyond 3 seconds, and the pressure timer still applies. Early clears are the point.
- **Score:** a time. The leaderboard is **fastest to wave 30.**
- **Trials:** not available.
- **Reward:** a cosmetic for the first clear (*Moonlight* ink style).

### 7.3 Menu

The main menu adds Boss Rush and Time Attack once unlocked, beside Arcade, Tutorial, Armory, Codex, Leaderboards and Options.

---

## 8. Cosmetics roster

About **25 unlockable cosmetics** at launch (plus a default in each category), in four categories. Cosmetics never affect gameplay or readability. Unlock sources are story beats, secrets, milestones and mode clears.

### Outfits (6 unlockable)

| Outfit              | Unlock                               |
| ------------------- | ------------------------------------ |
| **Standard Jacket** | Default                              |
| **Rooftop Scarf**   | Secret: Broken Sign Roost            |
| **Rain Coat**       | Story beat: wave 25                  |
| **Night Courier**   | Story beat: wave 50                  |
| **Gilded Jacket**   | Story beat: wave 100                 |
| **Ashen Hoodie**    | Reach wave 20 with two Trials active |
| **Crimson Dojo Gi** | Watch the Dojo Memory                |

### Sword trails and kill effects (7 unlockable)

| Trail            | Unlock                           |
| ---------------- | -------------------------------- |
| **Plain Ink**    | Default                          |
| **Rainwater**    | Secret: Flooded Shrine           |
| **Neon Brush**   | Secret: Neon Kanji               |
| **Gold Leaf**    | Story beat: wave 75              |
| **Ember Stroke** | Score 100,000 in a single run    |
| **Violet Wisp**  | 5,000 total kills                |
| **Storm Gale**   | Reach Flow III 50 times in total |
| **Salt Wake**    | Clear Boss Rush                  |

### Cigarette ink styles (6 unlockable)

| Style            | Unlock                              |
| ---------------- | ----------------------------------- |
| **Ink Black**    | Default                             |
| **Wildfire**     | Reach wave 15                       |
| **Rain**         | Survive 5 Rain Shower events        |
| **Crimson**      | Defeat 10 bosses in total           |
| **Lantern Fire** | Secret: Lantern Path                |
| **Ash**          | Reach wave 50 with no Trials active |
| **Moonlight**    | Clear Time Attack                   |

### Kill effects (5 unlockable)

| Kill effect | Unlock |
|---|---|
| **Blood** | Default |
| **Sakura** | Reach wave 30 |
| **Glitch** | Kill 500 cyber enemies in total |
| **Ash** | Survive 5 Blackout events |
| **Paper** | Get 100 deflect kills in total |
| **Neon Ink** | Score 250,000 in a single run |

Kill effects change only the look of the kill splash (see the [Visual Briefs](akane-ii-visual-briefs.md) §10.10).

---

## 9. Open items

- [x] Original canon confirmed: no definitive ending, and Akane is alive.
- [x] The original's Final Scene is real and set in the past. Akane was an adult during the first game's gameplay and is an adult in Akane II.
- [x] The Dojo tutorial is framed as a flashback to the dojo, as the original's was (see the copy deck §9).
- [x] Final copy for beats, the post-100 rotation, the Tsukumo reveal and all other text: see the [Copy Deck](akane-ii-copy-deck.md).
- [x] Tsukumo's name is confirmed. Boss title cards carry a small department tag instead of Tsukumo's name.
- [x] A last read of the lines marked **[CHECK]** in the copy deck by someone who knows the first game closely.
- [x] Art for the ink silhouette beats.
- [x] Localization plan (the text is deliberately short).

---

# Akane II — Copy Deck (Final Draft)

Source of truth for all player-facing story and flavor text. Companion to [akane-ii-story-and-modes.md](akane-ii-story-and-modes.md) and [akane-ii-run-ui-narrative.md](akane-ii-run-ui-narrative.md).

> This is the **final draft** for review. Original-game lore was **checked against online sources** (fan wiki and review summaries, listed at the end). Lines marked **[CHECK]** still deserve a last look from someone who knows the first game closely, because the sources are summaries.

## Contents

1. [Style sheet](#1-style-sheet)
2. [Intro card](#2-intro-card)
3. [Story beats](#3-story-beats)
4. [Post-wave-100 rotation](#4-post-wave-100-rotation)
5. [Boss title cards and codex](#5-boss-title-cards-and-codex)
6. [Enemy codex and first-encounter cards](#6-enemy-codex-and-first-encounter-cards)
7. [Lore fragments](#7-lore-fragments)
8. [The Dojo Memory](#8-the-dojo-memory)
9. [Tutorial (the Dojo) lines](#9-tutorial-the-dojo-lines)
10. [Item flavor lines](#10-item-flavor-lines)
11. [World text: zone cards, events and death lines](#11-world-text-zone-cards-events-and-death-lines)
12. [Character codex entries](#12-character-codex-entries)
13. [Lore check against the original](#13-lore-check-against-the-original-summary)

---

## 1. Style sheet

**Decisions**

- **Villain:** Oyabun **Tsukumo.** Calm, businesslike, and growing in respect for Akane across the four beats, without ever turning sentimental.
- **Register:** **dry noir with a little bite.** Clipped, observational, with the occasional dark joke. No melancholy poetry, no exposition.
- **Wave 100:** just the name and the confrontation. No new backstory.
- **Item lines:** wry one-liners about the item.
- **Lore fragments:** a mix of server logs, hand-written notes and graffiti.

**Rules**

- **Akane:** at most about 8 words per line. Flat and dry. She never explains herself.
- **Tsukumo:** calm, speaks in terms of cost, debt and accounts. Never raises his voice, never says he hates her.
- **Codex and flavor:** one or two lines. Concrete nouns, no adjectives piling up.
- **Banned:** exposition about the first game, jokes that break tone, and stating Akane's feelings.
- **Canon:** it is **2121, one rainy night,** right after Akane's Last Stand. Akane is alive and an adult. Refer to the Last Stand as "the Last Stand" or "earlier tonight."

---

## 2. Intro card

> *Mega-Tokyo, 2121. The night is not over.*
> *Akane walked out of the Last Stand alive. The Yakuza are still counting.*
> *Oyabun Tsukumo owns what lies ahead. Katsuro has been sent again. So have the others.*
> *Akane remembers the city in ink. She intends to leave it in ink as well.*

---

## 3. Story beats

Four beats, shown in the breather before the next wave. Each has an ink-silhouette image, an hour label, and a cosmetic reward. They are skippable and re-readable in the codex.

### Wave 25: "The Count" (Midnight)

> **TSUKUMO:** "Twenty-five waves. My accountants are impressed. I am not. Yet."
> **AKANE:** "Add it to the bill."

*Reward: the Rain Coat outfit.*

### Wave 50: "The Ledger" (Small Hours)

> **TSUKUMO:** "You are the most expensive problem this district has ever had. Name a price."
> **AKANE:** "No price."

*Reward: the Night Courier outfit.*

### Wave 75: "The Archive" (Before Dawn)

> **TSUKUMO:** "Katsuro remembers every cut. Every rebuild is paid for with what you teach him."
> **AKANE:** "Then he's a good student."
> **TSUKUMO:** "He is. So are you."

*Reward: the Gold Leaf sword trail.*

### Wave 100: "The Name" (Still Night)

An ink silhouette stands on a balcony above the Shrine Heights plateau.

> **TSUKUMO:** "I am Tsukumo. This district has been mine for forty years. I have not enjoyed a night like this one in all of them."
> **AKANE:** "Then it's overdue."

*Reward: the Gilded Jacket outfit.* The silhouette does not fight. The run continues.

---

## 4. Post-wave-100 rotation

After wave 100, a short beat plays every 25 waves, drawn from this pool without repeating until all have been seen. No reward. The hour label is always **Still Night.**

1. *"Another crew. Another name."*
2. *"The district is his. The night is yours."*
3. *"Tsukumo has other districts. So do his friends."*
4. *"Rest is for the ones who finished."*
5. *"The sun is late. It is always late."*
6. **TSUKUMO:** *"You are still here. I have stopped being surprised."*
7. **TSUKUMO:** *"Send another."*
8. **AKANE:** *"Send them all."*

---

## 5. Boss title cards and codex

The title card appears for 2 seconds at the start of a fight: name, epithet, and a department tag in small type. The codex entry unlocks after the first defeat.

| Boss                     | Epithet                                    | Department tag | Codex entry                                                                                                                                           |
| ------------------------ | ------------------------------------------ | -------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Katsuro**              | *"The one who never stays dead."*          | Enforcement    | *He should be dead. The engineers keep rebuilding him, and every defeat teaches him something new. He has stopped being a weapon and become a habit.* |
| **The Debt Collector**   | *"Everything is owed."*                    | Collections    | *Every round is an invoice. He has never failed to collect, and he has never once been thanked.*                                                      |
| **The Hunter**           | *"You will not hear it twice."*            | Contracts      | *A Cyber Ninja who took the contract no one else would. He does not chase. He waits where you are going.*                                             |
| **The Crimson Kite**     | *"The sky belongs to the Yakuza."*         | Air Security   | *Piloted from a quiet room, a long way from the rain it patrols. The pilot has never been wet.*                                                       |
| **The Demolisher**       | *"Nothing stands that he has not marked."* | Construction   | *If a building is in the way, it has already been condemned. He signs the paperwork with a wrecking ball.*                                            |
| **The Floodgate Warden** | *"The tide keeps the ledger."*             | Utilities      | *He sold the canals years ago. Now he rents them back, by the minute.*                                                                                |

---

### Katsuro's dying words

In the original, Katsuro spends his dying breath complimenting Akane's swordsmanship while noting she still will not make it out alive. Akane II keeps that habit. One line is chosen at random each time he is defeated, and the lines grow warmer with the boss tier.

| Tier        | Lines (pool)                                                                            |
| ----------- | --------------------------------------------------------------------------------------- |
| 1           | "Fine blade work. You still won't leave this city." / "Good cut. It changes nothing."   |
| 2           | "You are better than last time. So am I." / "Remember this one. I will."                |
| 3 and above | "You are the only one who ever taught me anything." / "Again, then. I am almost ready." |

His **dash trail is hot pink,** as in the original.

---

## 6. Enemy codex and first-encounter cards

Each enemy has a **codex entry** (unlocked on first encounter) and a **first-encounter card** (shown the first time it appears: name, a one-line tip, and the telegraph color).

| Enemy              | Codex entry                                                      | First-encounter tip                                         |
| ------------------ | ---------------------------------------------------------------- | ----------------------------------------------------------- |
| **Yakuza Guy**     | *Cheap, loyal and expendable. There is always another.*          | "Slow and plentiful. Watch the raised blade."               |
| **Shooter**        | *Paid by the eye. The scope sees what the man would rather not.* | "The white line locks. Move off it, or deflect."            |
| **Skirmisher**     | *They don't fight. They arrive.*                                 | "Fast and from behind. Listen for the footsteps."           |
| **Tank**           | *Plating bought on credit. The back plate was an afterthought.*  | "Armored in front. Get behind it."                          |
| **Lancer**         | *Reach is a promise. Step inside and it breaks.*                 | "Long thrust, long recovery. Step in after it."             |
| **Archer**         | *Patient men with a view.*                                       | "Arrows arc in. Cover works. So does climbing."             |
| **Shieldbearer**   | *A wall that walks. Walls have sides.*                           | "Front blocked. Flank it, or pierce it."                    |
| **Cyber Ninja**    | *The body was sold first. The blade followed.*                   | "Guards from the front. Hit it from the side."              |
| **Bomber**         | *Nobody remembers his face, only the sound.*                     | "Leave the ring, or slash the charge back."                 |
| **Zipline Raider** | *The city is wired for rent. He rides it for free.*              | "Shot from above. Cut the line, or sidestep the landing."   |
| **Banner Caller**  | *Loud colors for quiet men.*                                     | "Makes others faster. Kill him first."                      |
| **Hexer**          | *He doesn't paint walls. He paints where you can't step.*        | "Ink zones slow you and block dashes. Kill the Hexer."      |
| **Drone Handler**  | *Three small eyes and one small man.*                            | "Kill the handler and the drones fall."                     |
| **Phantom**        | *If you hear it twice, you heard it too late.*                   | "A whisper, then a lunge. Move off the line."               |
| **Duelist**        | *He waits for you to be brave.*                                  | "Guard up means wait. Don't walk into the stance."          |
| **Sniper**         | *Seen only by the white line that finds you.*                    | "A long white line. Break it, or step off before it locks." |

---

## 7. Lore fragments

Twelve fragments found in the secrets. Types: dojo note (D), server log (L), civilian note (N), graffiti or sign (G).

| #   | Secret                 | Type | Text                                                                                                                            |
| --- | ---------------------- | ---- | ------------------------------------------------------------------------------------------------------------------------------- |
| 1   | Koi Pond Wall          | D    | *Carved into the stone: "Breathe. Wait. Cut." The third word is deeper than the others.*                                        |
| 2   | Pipe Whisper           | N    | *Scratched on a pipe by a canal worker: "The water remembers everything you drop in it. It gives back the heavy things first."* |
| 3   | Server Room terminal 1 | L    | *Contract 118. Subject: Sugahara A. Status: alive. Escalate. Authorized: T.*                                                    |
| 4   | Server Room terminal 2 | L    | *Katsuro reassigned. Third rebuild tonight. Second pistol requisitioned.*                                                       |
| 5   | Server Room terminal 3 | L    | *The Hunter requires no surveillance. He prefers to be the surveillance.*                                                       |
| 6   | Server Room terminal 4 | L    | *Dojo basement sealed after the Ishikawa incident. Do not reopen. Not for money.*                                               |
| 7   | Neon Kanji             | G    | *A burned shop sign: "Open all night. Closed to Yakuza."*                                                                       |
| 8   | Broken Sign Roost      | N    | *A note under a rock: "Whoever climbs this far, I hope you're running toward something."*                                       |
| 9   | Roof Vent Drop         | G    | *A maintenance tag: "Vent 4 goes down. Do not lean on it. Do not ask why."*                                                     |
| 10  | Bell of the Shrine     | N    | *The shrine keeper's note: "The bell rings for the departed. Tonight it rings for the ones who stayed."*                        |
| 11  | Lantern Path           | N    | *The shrine keeper's note: "Each lantern remembers someone. Lighting them is the only way out of the dark."*                    |
| 12  | The Old Dojo           | D    | *In Akane's hand, on the wall: "Master. I learned the lesson. You did not like the answer."*                                    |

---

## 8. The Dojo Memory

Unlocked by collecting all twelve fragments and opening the Old Dojo. About 60 seconds, as images with caption lines. It is a **lesson about waiting,** from Akane's apprenticeship, and it does not restage the original's tutorial flashback or Final Scene. In the original, Akane sought out Ishikawa to avenge her family and trained under him for about a year, so the scene's quiet is **double-edged:** he teaches patience, and she is spending it. **[CHECK]**

**Sequence**

1. *Image: a dojo at dusk, rain on paper walls. A young Akane kneels. Ishikawa stands with a brush.*

   > **ISHIKAWA:** "Three things. Breathe. Wait. Cut."
   > **YOUNG AKANE:** "I know the third."
   > **ISHIKAWA:** "Everyone knows the third."
   > *Caption: She had come for the third. She had been waiting a year to use it.*

2. *Image: a drop of rain gathers on the eave.*

   > **ISHIKAWA:** "Wait for it."

3. *Image: the drop falls. Ink spreads where it lands.*

   > **ISHIKAWA:** "Now."

4. *Image: her blade moves. The drop splits in two.*

5. *Cut to the present: Akane, an adult, in the same dojo in the dark, her hand on the wall. Rain at the window.*

   > **AKANE:** "He taught me to wait. He never taught me what to do after."

6. *Card: "Crimson Dojo Gi unlocked."*

---

## 9. Tutorial (the Dojo) lines

The tutorial is a flashback to the dojo. Each lesson opens with one line of text from **Ishikawa.** The practice range has no story text.

| Lesson       | Line                                                    |
| ------------ | ------------------------------------------------------- |
| Opening      | "Kneel. We begin."                                      |
| Movement     | "Feet first. The blade follows."                        |
| Sword        | "A cut is a decision."                                  |
| Gun          | "Metal answers faster than breath. Spend it sparingly." |
| Ink Step     | "Move when it arrives, not before."                     |
| Human shield | "Use what the room gives you."                          |
| Flow         | "Keep moving. The rhythm will keep you."                |
| Specials     | "Everything you have, once, at the right moment."       |
| Traversal    | "The city is a staircase. Climb."                       |
| Gadgets      | "A tool does not make the hand."                        |
| Closing      | "Again."                                                |

---

## 10. Item flavor lines

One wry line per item, shown in the Armory.

**Katanas**

| Item       | Line                                                    |
| ---------- | ------------------------------------------------------- |
| Kuro       | "Plain steel. It has never been the reason she lost."   |
| Rebi       | "It returns what it is given."                          |
| Tadus      | "A throw is a promise to come back for it."             |
| Nodachi    | "Too long for alleys. The alleys can adjust."           |
| Twin Tantō | "Two short answers to one long question."               |
| Echo Blade | "The first cut is a warning. The second is the lesson." |
| Kusarigama | "Distance is a courtesy. Pull it back."                 |

**Guns**

| Item                       | Line                                                     |
| -------------------------- | -------------------------------------------------------- |
| Patron v26                 | "Six rounds and a good reason for each."                 |
| Inquisitor M103            | "Three questions at a time. It rarely needs a fourth."   |
| Vicious S36                | "It kicks like it resents the target. Lean into it."     |
| Magnum XT5                 | "One answer, delivered through everyone in the way."     |
| Double Barrel              | "Diplomacy at close range."                              |
| Gravitational Beam Emitter | "It doesn't shoot. It reminds things where they belong." |

**Gadgets**

| Item                   | Line                                                               |
| ---------------------- | ------------------------------------------------------------------ |
| Cyber Gloves           | "A grip that doesn't tire of other people."                        |
| Marionette Wire        | "Everyone walks, once the strings are right."                      |
| Katana Gun             | "Why choose? The blade always wanted an opinion."                  |
| Magnetic Pulse Emitter | "It argues with the metal in men."                                 |
| Stabilizer Bracelet    | "Steady hands win quietly."                                        |
| Adrenaline Shot        | "The room slows down. She doesn't."                                |
| Grapple Anchor         | "A rooftop is just a street that hasn't been introduced."          |
| Updraft Fan            | "Cheap wind. Expensive confidence."                                |
| Hologram Decoy         | "A better Akane, for a few seconds. She tolerates the comparison." |
| Sumi Bomb              | "Ink for the eyes of men who stare."                               |
| Lure Beacon            | "Everyone follows the loudest thing in the room."                  |

**Gadget mods**

| Mod                                   | Line                                                      |
| ------------------------------------- | --------------------------------------------------------- |
| Cyber Gloves: Original Spec           | "The old model. Hungrier."                                |
| Stabilizer Bracelet: Original Spec    | "One bullet back becomes two. Nobody asked for the math." |
| Magnetic Pulse Emitter: Original Spec | "One a second. Politely."                                 |
| Katana Gun: Original Spec             | "A finishing move, with a finishing move."                |
| Grapple Anchor: Swing Line            | "Gravity, with a better attitude."                        |
| Hologram Decoy: Overload              | "She always did leave an impression."                     |

**Boots**

| Item         | Line                                                   |
| ------------ | ------------------------------------------------------ |
| Standard     | "Good soles. The city did the rest."                   |
| Geta Springs | "A higher vantage is only a decision away."            |
| Rail Skates  | "Momentum is a debt that pays in the right direction." |
| Silent Tabi  | "Quiet steps leave the longest silences."              |

---

## 11. World text: zone cards, events and death lines

### Zone name cards (first visit each run)

| Zone             | Card                                             |
| ---------------- | ------------------------------------------------ |
| Neon Plaza       | **Neon Plaza** — *Nothing here is quiet.*        |
| Underpass Canals | **Underpass Canals** — *Water keeps the ledger.* |
| Rooftop Signage  | **Rooftop Signage** — *Above the noise.*         |
| Shrine Heights   | **Shrine Heights** — *The last quiet place.*     |
| Hidden Network   | **Hidden Network** — *Behind the walls.*         |

### Event announcements (about 8 seconds ahead, with a siren)

| Event       | Text                               |
| ----------- | ---------------------------------- |
| Rain Shower | "A downpour. The roofs go slick."  |
| Blackout    | "The neon is dying in the [zone]." |
| Canal Surge | "The canals are rising."           |

### Death lines (one chosen at random on the run summary)

1. "One cut."
2. "Not tonight."
3. "The rain keeps falling."
4. "The bill comes due."
5. "Again."
6. "Wait. Then cut."
7. "Too slow."
8. "The night wins this one."
9. "Pay attention."
10. "Still night."

---

## 12. Character codex entries

| Entry              | Text                                                                                  |
| ------------------ | ------------------------------------------------------------------------------------- |
| **Sugahara Akane** | *Contract 118. Alive, which is the part that keeps costing them.*                     |
| **Oyabun Tsukumo** | *Keeps the district's books. Never seen in a fight. Always present in the account.*   |
| **Ishikawa**       | *Ishikawa taught her. She taught him one thing back. The dojo has been sealed since.* |

---

## 13. Lore check against the original (summary)

Checked online in this pass. Sources are search summaries and the fan wiki, not the game itself.

**Confirmed**

- The first game is set in **Mega-Tokyo, 2121,** in rain-soaked neon streets. Its intro has Akane's vehicle crash, and she is surrounded by Yakuza with no hope of running. She accepts her fate and fights her final stand.
- **Ishikawa** was her master (apprentice years 2098-2099). He is a **Yakuza** (tattoos shown in the Final Scene) who slaughtered five oyabuns in 2099, including the **Sugahara family.** Akane sought him out **for revenge,** trained under him for about a year, developed her own technique (**Dragon Slayer**) in secret, and killed him in a duel. He asked what the technique was, then died.
- The optional **tutorial** is a flashback about **23 years before** the main game, with Akane as a child under Ishikawa.
- **Katsuro** is the boss: he appears every 100 kills, dashes and slashes, shoots three times, does multi-dashes, has a **pink dash trail,** levels up each time he is killed, and **compliments Akane's swordsmanship with his dying breath.**
- Outside the tutorial and the boss, the original's story is minimal.

**Changes made because of this check**

- Fragment 12 and the Dojo Memory no longer suggest Akane came to the dojo seeking forgiveness or learning in good faith. She came for revenge.
- The Ishikawa codex entry now hints at how he died, in one oblique line.
- Katsuro's accent color is now **hot pink,** as in the original (it was red), and he now has dying words.

**Still unverified**

- Nothing blocking. The items were remixed on purpose, so their original behaviors don't need to match (Appendix B of the main doc).
- The exact wording of the original's text and the Final Scene.

**Sources**

- [Akane on the fan wiki: Sugahara Akane](https://akane.fandom.com/wiki/Sugahara_Akane)
- [Akane on the fan wiki: Ishikawa](https://akane.fandom.com/wiki/Ishikawa)
- [Akane on the fan wiki: Katsuro](https://akane.fandom.com/wiki/Katsuro)
- [Akane on TV Tropes](https://tvtropes.org/pmwiki/pmwiki.php/VideoGame/Akane)
- [Akane review on Finger Guns](https://fingerguns.net/games/2022/09/20/akane-review-ps4-kill-die-repeat/)
- [Akane on Steam](https://store.steampowered.com/app/884260)

---

# Akane II — Visual Briefs

Companion to [akane-ii-design.md](akane-ii-design.md) (§1 Art Direction). Briefs for concept artists and animators: foundations, characters, enemies, bosses, zones and effects.

> Sizes are in pixels at the **640 x 360 base resolution.** All numbers are starting values for the art team. Akane's base design follows the original character art; this document covers only what is new or adapted. Boss and telegraph colors are reserved for threats (see §1.3).

## Contents

1. [Foundations](#1-foundations)
2. [Akane](#2-akane)
3. [Enemies](#3-enemies)
4. [Bosses](#4-bosses)
5. [Zones and environment](#5-zones-and-environment)
6. [Effects and UI visuals](#6-effects-and-ui-visuals)
7. [Animation guidelines](#7-animation-guidelines)
8. [Asset inventory](#8-asset-inventory)
9. [Environment quality: lighting, volumetrics and particles](#9-environment-quality-lighting-volumetrics-and-particles)
10. [Kill animations](#10-kill-animations)

---

## 1. Foundations

### 1.1 Technical targets

| Item              | Target                                                                                            |
| ----------------- | ------------------------------------------------------------------------------------------------- |
| Base resolution   | **640 x 360,** scaled by whole numbers (720p, 1080p, 4K)                                          |
| Akane's height    | About **48 px**                                                                                   |
| Standard enemies  | 40-56 px tall (Tank about 72 px)                                                                  |
| Bosses            | Varied by boss (see §4), from human-scale to about 4x                                             |
| Production method | **Hand-drawn pixel sprites** with brush-stroke shading, lit by a dynamic lighting system (see §9) |
| Frame rate        | 60 FPS gameplay. Sprite animation at 12-24 frames per second of art, with smooth timing           |

### 1.2 The look

A highly unique blend of **Japanese ink wash (sumi-e)** and **modern pixel animation,** inspired in smoothness and impact by *Dead Cells*, set on a **rainy neon night in 2121.**

- **Sumi-e principles to use:** bold brush-stroke outlines on threats, tonal washes (bokashi gradients) for shading, deliberate negative space (*ma*), and one accent color at a time.
- **Pixel principles to use:** clean pixel clusters, no anti-aliased smears on sprites, strong silhouettes, high frame counts on key actions, and clear anticipation and follow-through.
- **How they meet:** sprites are drawn in pixel clusters that imitate brush stroke edges and wash tones. Backgrounds use dithered washes. Effects (kills, deflects, dashes) are brush-stroke animations.
- **Mood:** wet, dark and neon-lit. Rain and reflections add ink texture.

### 1.3 Color rules

- **Base palette:** shared across the whole game: ink black, a range of cool grays, and paper white.
- **Zone accent hue (set dressing only):** one per zone, low to medium saturation, static, never animated like a threat.

| Zone             | Accent        |
| ---------------- | ------------- |
| Neon Plaza       | Neon pink     |
| Rooftop Signage  | Electric blue |
| Shrine Heights   | Jade green    |
| Underpass Canals | Jade-teal     |
| Hidden Network   | Dim white     |

- **Regular-enemy threat language:** a **white-hot** (a bright white flare, line or ring with a black ink outline and no hue). This is the only look for regular-enemy telegraphs and projectiles. It is emissive and unlit, and never used on set dressing or gore. **Red belongs to gore and the logo,** never to telegraphs.
- **Boss accents** (high saturation, used only on that boss's attacks, trails and core):

| Boss                 | Accent                                                               |
| -------------------- | -------------------------------------------------------------------- |
| Katsuro              | Hot pink                                                             |
| The Debt Collector   | Coin gold                                                            |
| The Hunter           | Pale violet                                                          |
| The Crimson Kite     | Lime (its frame is red and white, but its attacks and core are lime) |
| The Demolisher       | Hazard orange                                                        |
| The Floodgate Warden | Deep teal                                                            |

- **Hazard marks:** black-and-white hatching (no color).
- **Destructibles:** a faint crack-line mark.
- **Night clock:** lighting deepens over the run (late night, midnight, small hours, before dawn). Telegraph colors and hazard marks keep their contrast in every lighting band.

### 1.4 Readability rules

- Threats are on a **higher-contrast layer** than backgrounds: hard ink edges on threats, soft washes on backgrounds.
- Each enemy has a **unique silhouette** at 1x scale in a crowd of 10+.
- Akane has a **distinct silhouette and reserved palette.**
- Telegraphs are visible in every lighting band and every weather state.
- Backdrops (skyline, distant towers) are visually quiet.

---

### 1.5 Pixel rendering

Chosen to give smooth motion at any refresh rate (including the uncapped PC option) without pixel shimmer.

- **Sprites and tiles are drawn at the output resolution,** with every art pixel scaled by a **whole number** (2x at 720p, 3x at 1080p, 4x at 1440p, 6x at 4K). Pixels stay square and uniform.
- **Positions and the camera are interpolated** between the fixed 60 Hz simulation steps, to **output-pixel precision.** There is no snapping to the art grid.
- **Soft effects** (dynamic lighting, fog, bloom, post-processing) render at the base or half resolution and are upscaled. They are soft by nature, and this keeps the original Switch within budget.
- **Non-whole-number displays** (for example, 1366 x 768 or windowed sizes) use a sharp-bilinear upscale so edges stay crisp without uneven pixels.
- **Pixel-snap option:** a toggle for purists that snaps to the art grid. Motion then steps at the simulation rate, with no smoothness gain from higher refresh rates.
- **Known trade-off:** different objects' pixel grids can sit offset from each other by a fraction of an art pixel. This is invisible in an ink-wash style and is the price of smooth motion.
- **Things to check in testing:** parallax layers, rain and particle streaks, normal-mapped lighting (lights also interpolate), and text and UI (which should snap to the grid).

### 1.6 Title lockup

The title is always **AKANE II** in all-caps, matching the original's logo: a **heavy, blocky sans** in **solid saturated red** with a **distressed, blood-spattered texture,** with the Roman numeral "II" set in the same face, red and texture. Red is the logo's brand color only. It is never used on the gameplay layer, where enemy telegraphs are white-hot (red belongs to the logo and to gore, never to telegraphs), and never on text cards or UI. Take the logo and display face from Ludic's original source files. Details are in the [gameplay trailer plan](akane-ii-gameplay-trailer.md) §2.

## 2. Akane

- **Base design:** follows the original character art. She is an **adult** swordswoman.
- **New in Akane II:**
  - **Standard Jacket:** the default outfit, dark and rain-wet, with a single accent shade so she reads at a glance.
  - **Wet look:** rain beads on the jacket and a subtle wet sheen (a shading pass, not a separate sprite set).
  - **Loadout visibility:** the equipped katana (7 designs) and gun (6 designs) are visible on her sprite. The gadget is a small visible detail where it makes sense (for example, a bracelet or gloves).
  - **Flow aura:** an ink aura around her that grows with the Flow tier (see §6).
  - **Outfits (6 unlockable):** Rooftop Scarf, Rain Coat, Night Courier, Gilded Jacket, Ashen Hoodie and Crimson Dojo Gi. Each keeps her silhouette and her reserved palette readable.
- **Cosmetics:** sword trails (7 unlockable plus the default) and cigarette ink styles for Dragon Slash and Dragon Slayer (7, including the default) are effects, not sprite changes.

---

## 3. Enemies

### 3.1 Shared language

- **Yakuza identity:** every enemy has at least one marker of the organization: dark suits, sunglasses, irezumi-style tattoos (drawn in ink), or cybernetic parts. Cybernetic parts increase with higher-cost enemies.
- **Role shapes** (the silhouette rule):

| Role           | Shape language                                                            |
| -------------- | ------------------------------------------------------------------------- |
| **Anchor**     | Wide, planted stance. A visible front (shield, plating, weapon)           |
| **Pincer**     | Lean, forward-leaning, asymmetrical. Built to look fast                   |
| **Suppressor** | Tall, still and vertical, with an obvious long weapon or optic            |
| **Support**    | Carries a tall object (banner, staff, controller) above the head line     |
| **Special**    | A distinctive unusual shape tied to how it arrives (harness, cloak, coat) |

- **Telegraph pose:** each attack has a clear wind-up pose that matches its telegraph tier (Quick 0.35 s, Standard 0.55 s, Heavy 0.8 s, Long 1.0 s). The pose must be readable even without the flare.
- **Elites** keep the base silhouette and add one clear marker (a gold coat trim, an extra weapon, a glow), so players read "stronger version" instantly.
- **Modifiers** (Hasted, Armored, Volatile, Shrouded, Vengeful) each have a fixed overlay: speed streaks, an ink plate, a glowing core, a shimmer, and a hatched ink brand. None use red or white-hot.

### 3.2 Enemy briefs

| #   | Enemy              | Role            | Height | Silhouette and key details                                                                                                   |
| --- | ------------------ | --------------- | ------ | ---------------------------------------------------------------------------------------------------------------------------- |
| 1   | **Yakuza Guy**     | Anchor / fodder | 44 px  | Standard suit and sunglasses. Short blade or baton, raised overhead in the wind-up. The "baseline" body that others build on |
| 2   | **Shooter**        | Suppressor      | 50 px  | Tall, still. Rifle arm and a glowing optic over one eye. White-hot laser line from the optic                                 |
| 3   | **Skirmisher**     | Pincer          | 42 px  | Lean, forward-leaning, light armor, twin short blades. Curved running stance and trailing coat                               |
| 4   | **Tank**           | Anchor          | 72 px  | Wide, heavy plating, a visible rear plate with a seam (the weak point). Slow turning animation                               |
| 5   | **Lancer**         | Anchor, reach   | 56 px  | Tall, narrow stance with a very long spear and a white sash. The spear defines the silhouette                                |
| 6   | **Archer**         | Suppressor      | 52 px  | Hooded cloak and a long bow, usually on high ground. Cloak sways in the rain                                                 |
| 7   | **Shieldbearer**   | Anchor          | 58 px  | Broad figure behind a large ink-black riot shield. The shield is the main shape                                              |
| 8   | **Cyber Ninja**    | Pincer          | 50 px  | Slim cybernetic body with a glowing seam. Long blade. A visible guard pose                                                   |
| 9   | **Bomber**         | Suppressor      | 46 px  | Hunched, a bandolier of glowing charges. The charges are the identity                                                        |
| 10  | **Zipline Raider** | Special         | 48 px  | Hook-and-wire harness and a short blade. Appears with a visible wire                                                         |
| 11  | **Banner Caller**  | Support         | 52 px  | Carries a tall banner with an ink-black emblem above the head line. The banner defines the shape                             |
| 12  | **Hexer**          | Support         | 52 px  | White mask, a brush-like staff. Draws ink zones                                                                              |
| 13  | **Drone Handler**  | Support         | 46 px  | Wrist controller, three small drones orbiting. The drones are the identity                                                   |
| 14  | **Phantom**        | Special         | 50 px  | Cloaked figure with a shimmer. Mostly negative space until it strikes                                                        |
| 15  | **Duelist**        | Special         | 54 px  | Poised, in a long coat with a straight blade. A distinctive counter stance                                                   |
| 16  | **Sniper**         | Suppressor      | 52 px  | Long rifle with a laser scope on a high perch. A steady, still pose                                                          |

### 3.3 Named elite markers

| Elite             | Marker                                  |
| ----------------- | --------------------------------------- |
| Enforcer          | Gold coat trim and a second weapon      |
| Marksman          | Twin optics                             |
| Siege Tank        | Shoulder-mounted ram plates             |
| Shadow Ninja      | A second glowing seam and a trail       |
| Naginata Master   | A longer curved polearm                 |
| Riot Guard        | A shield with crackling light           |
| Fire Archer       | An ember-lit bow                        |
| Demolition Bomber | A larger pack with a cluster of charges |

---

## 4. Bosses

Each boss is drawn at **high detail** with its own accent color (§1.3), a recognizable silhouette, and animations for **intro, idle, each move, phase break, and death.**

| Boss                     | Scale                                    | Silhouette                                         | Key visuals                                                                                               |
| ------------------------ | ---------------------------------------- | -------------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| **Katsuro**              | Human-scale, about 52 px                 | Lean figure in a dark coat                         | Hot pink dash trail. Cybernetic upgrades grow with the tier (see below). A sword that drags a line of ink |
| **The Debt Collector**   | Human-scale, about 56 px                 | Long coat, heavy shoulders                         | A floating ledger drone, a cybernetic arm cannon, gold coin-yellow shots                                  |
| **The Hunter**           | Human-scale, about 50 px                 | Nearly invisible: shimmering cloak lines           | Pale violet flicker. Fully visible only at the moment of a strike or when revealed                        |
| **The Crimson Kite**     | About 3x (aerial, wingspan about 150 px) | Winged drone-mech                                  | Red-and-white frame, long rotor blades like brush strokes. Lime dive lines and core                       |
| **The Demolisher**       | About 4x (a crane rig, about 200 px)     | Exo-suit pilot in a crane cab with a wrecking ball | Hazard orange impact circles. The cab is a clear target                                                   |
| **The Floodgate Warden** | About 3x (about 150 px)                  | Bulky waterproof exo-rig with a pump cannon        | Deep teal water gauge on his chest. The gauge shows the tide state                                        |

**Katsuro by tier** (visual progression, matching the move tiers)

| Tier | Waves      | Visual change                                                     |
| ---- | ---------- | ----------------------------------------------------------------- |
| 1    | 10-29      | Base design                                                       |
| 2    | 30-59      | Adds a holstered pistol and a second cybernetic part              |
| 3    | 60-99      | More visible cybernetics. A longer coat tear and a brighter trail |
| 4    | 100+       | A fully rebuilt look. The sword gains a glowing edge              |
| 5-6  | 150+, 200+ | Additional glowing seams. Trails take on a second layer           |

**Boss visual rules**

- Every attack has a pose, a flare in the boss's color, and a fixed telegraph time.
- The **vulnerability window** is shown by a clear visual state (an exposed core, a stagger pose, a glow), in the boss's color.
- **Phase pips** are shown in the boss's color on the HUD.
- **Intro:** the title card appears in brush lettering, with the boss's department tag in small type.
- **Death:** a freeze-frame ink slash, then the boss dissolves into an ink splash in its accent color.

---

## 5. Zones and environment

Each zone uses the shared ink base plus its accent (§1.3). Sub-areas are listed in the map design document.

| Zone                 | Mood                | Key materials and props                                        | Landmark                             |
| -------------------- | ------------------- | -------------------------------------------------------------- | ------------------------------------ |
| **Neon Plaza**       | Busy, wet, lit      | Food stalls, vending machines, awnings, neon signs, a koi pond | A giant rotating neon fish sign      |
| **Rooftop Signage**  | Windswept, electric | Water tanks, antennas, giant signs, a crane, bridges           | A huge vertical kanji sign           |
| **Shrine Heights**   | Quiet, open, cold   | Stone lanterns, a torii gate, a bell, a small shrine           | A red torii gate against the skyline |
| **Underpass Canals** | Dim, echoing, wet   | Pipes, tunnels, canals, floodgates, pump machinery             | The floodgate wheel                  |
| **Hidden Network**   | Cramped, humming    | Vents, cables, shafts, server racks, a sealed dojo             | A lit server room door               |

**Rules**

- Walkable surfaces: hard ink edges. Climbable surfaces: a consistent grip-line mark. Destructible objects: a faint crack-line mark.
- Backgrounds use soft, low-contrast washes and **dithering** for depth, with 3-4 parallax layers on PC and 2-3 on Switch.
- **Weather:** baseline light rain; the Rain Shower event is a downpour; reflections of neon on wet surfaces.
- **Night clock:** the sky and lighting shift over the run, from dusk-blue to a pale pre-dawn glow that never grows.
- The **Old Dojo** is a distinct, warm-lit secret space: paper walls and wood, in contrast with the rest of the city.

---

## 6. Effects and UI visuals

| Effect                       | Brief                                                                                                                 |
| ---------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| **Kill splash**              | A bold, bright red gore burst with brush-edged flecks (bold on kills only). See §10                                   |
| **Hit / deflect**            | A short brush-stroke spark. Deflect adds a white ring                                                                 |
| **Ink Step**                 | A brush-stroke afterimage of Akane and a very short slow-motion beat                                                  |
| **Dash**                     | A thin ink streak. Boots change its length and shape                                                                  |
| **Flow aura**                | An ink aura around Akane. Tier I faint, tier II brighter, tier III bold with drips. A pulse warns before a tier drops |
| **Dragon Slash**             | A bold ink streak along the dash path. Its color follows the equipped cigarette ink style                             |
| **Dragon Slayer**            | A screen-wide ink wash that kills in a large radius. Also follows the cigarette ink style                             |
| **Telegraphs**               | White-hot for regular enemies, and the boss's accent for bosses. Line, ring and flare shapes per telegraph tier       |
| **Hazards**                  | Black-and-white hatching with an arc or steam animation                                                               |
| **Destruction**              | Ink splash and short dust. Debris clears from the walking plane in 2 seconds                                          |
| **Zipline / updraft**        | A bright ink stroke for ziplines and upward ink streaks for updrafts                                                  |
| **Off-screen threat smears** | Ink smears at the screen edge, shaped by threat type (see the run and narrative document)                             |
| **HUD**                      | Minimal brush-drawn elements that hide when unused                                                                    |
| **Story beat card**          | An ink silhouette image with brush lettering                                                                          |

**Cigarette ink styles (7)** change the color and texture of the two specials: Ink Black, Wildfire, Rain, Crimson, Lantern Fire, Ash and Moonlight.

---

## 7. Animation guidelines

Starting frame counts for the art team. Responsiveness beats animation completeness: anticipation frames can be canceled by deliberate player actions.

| Animation                        | Frames **[TBD]**    | Notes                                                  |
| -------------------------------- | ------------------- | ------------------------------------------------------ |
| Idle                             | 8-10                | Subtle breathing and rain on the jacket                |
| Run                              | 10                  | A strong silhouette change per foot plant              |
| Jump / fall / land               | 4 / 4 / 3           | Short landing recovery only for long falls             |
| Dash                             | 5-6                 | Few frames, stretched and smeared                      |
| Sword attack (per katana)        | 6-9                 | Startup / active / recovery matching the katana tables |
| Gun fire (per gun)               | 3-5                 | Recoil visible, especially the Vicious S36             |
| Ink Step                         | 6                   | Reads even in slow motion                              |
| Human shield grab / hold / throw | 6 / 4 loop / 6      | Grab must be readable                                  |
| Zipline / climb / grapple        | 4 loop / 6 loop / 5 | Responsive entry and exit                              |
| Death (Akane)                    | 8                   | Short and clean, so restarts stay fast                 |

**Enemy and boss animation**

- Every enemy has: **idle, move, wind-up pose per attack, attack, recovery, hit and death.**
- Wind-up time matches the telegraph tier exactly.
- Recovery poses clearly show the **weak window.**
- Bosses add: intro, each move, phase break and death.

**Principles:** strong anticipation, short but visible follow-through, and smears or squash and stretch on fast moves (in the spirit of *Dead Cells*).

---

## 8. Asset inventory

Rough counts for scoping (animation sets, not individual frames).

| Group                       | Count                                                             | Notes                                                                                                                            |
| --------------------------- | ----------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------- |
| Akane core set              | 1                                                                 | About 20 animations                                                                                                              |
| Akane loadout overlays      | 7 katanas, 6 guns, 6 mod-capable gadgets (visible parts), 4 boots | Layered on the core set                                                                                                          |
| Outfits                     | 6 unlockable + default                                            | Recolor and detail passes                                                                                                        |
| Standard enemies            | 16                                                                | Each about 8-12 animations                                                                                                       |
| Named elites                | 8                                                                 | Base sprite plus markers and one extra animation each                                                                            |
| Modifier overlays           | 5                                                                 | Reused across enemies                                                                                                            |
| Bosses                      | 6                                                                 | Katsuro has 6 tier looks. The environmental bosses are large multi-part sprites                                                  |
| Zone tilesets and backdrops | 5                                                                 | Grayscale albedo, hand-drawn normal maps, emissive masks and gradient maps. The night clock is a gradient-map set, not extra art |
| Destructible sets           | About 15 object types                                             | With broken states                                                                                                               |
| Effects                     | About 25 distinct effects                                         | See §6                                                                                                                           |
| UI / HUD                    | About 30 elements                                                 | Brush-drawn                                                                                                                      |
| Story beat and codex images | 4 beat silhouettes, Dojo Memory (about 8 images)                  | Ink illustrations                                                                                                                |

---

## 9. Environment quality: lighting, volumetrics and particles

*Dead Cells* sets the bar for how a 2D pixel game can look and feel: its environments are lit, layered, foggy and full of moving particles, so a flat 2D scene reads as a real space. We take that **quality bar** (but not the 3D-to-2D animation pipeline, see §1.1). Our rainy neon ink-wash night is a very good fit: wet surfaces, glowing signs, steam, mist and drifting ink all benefit from this treatment.

**What *Dead Cells* does** (from published interviews and write-ups; the detailed articles could not be opened, so this is based on summaries)

- **Hand-drawn normal maps** for backgrounds and decorations, so a **dynamic 3D-style lighting system** lights pixel art from the correct direction while respecting base colors. Normal maps are drawn by hand, which also lets artists emphasize volumes and improve background readability.
- **Gradient maps** over grayscale parallax art, so changing one gradient map re-colors a whole biome without redrawing.
- **Parallax:** about four layers of background, with distant architecture fading into fog, plus **foreground clouds or fog** for depth.
- **Density of the air:** constant fog and particles moving in front of the camera, with biome-specific variables for lighting color, smoke, water, mist and vegetation density.
- **Analogous palettes** to add depth, with a saturated palette and a strong, coherent architectural theme.
- **Combat feedback:** many particles, hit-stop (a one-frame freeze on strong hits, followed by a slow-down) and other fighting-game techniques.

### 9.1 What we adopt, adapted to Akane II

| Technique                                        | How we use it                                                                                                                                                                                                                                                 |
| ------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Dynamic lighting with hand-drawn normal maps** | All environment art (tiles, props, backgrounds) is painted as grayscale albedo plus a **hand-drawn normal map** and an **emissive mask.** Lights: neon signs, lanterns, stall lamps, fluorescent strips, gunfire and explosion flashes, and Akane's Flow aura |
| **Gradient maps for color**                      | Grayscale parallax and tile art, colored by a **gradient map per zone.** The same gradient system drives the **night clock** (late night to pre-dawn) and the Blackout event, so lighting bands cost almost no extra art                                      |
| **Layered parallax**                             | **4 background layers on PC, 3 on Switch,** from distant skyline in fog to near silhouettes, plus **foreground mist and rain layers** that pass in front of the action at low opacity                                                                         |
| **Fake volumetrics**                             | Neon haze cones, searchlight shafts, steam and mist banks: additive, noise-textured light shapes and fog sprites that react to lights. Not a true volumetric simulation                                                                                       |
| **Air density and particles**                    | Rain (several depths), mist, steam, embers, dust, paper scraps, neon glints and moths around lights. Density is a per-zone variable, and rises or falls with the weather and the night clock                                                                  |
| **Wet surfaces**                                 | Reflections of neon on wet ground: a mirrored, distorted strip with specular from the normal map. Puddles ripple when Akane or enemies land                                                                                                                   |
| **Combat feedback**                              | Hit-stop (short, consistent, tunable), ink-splash particles on kills, brief slow-down on Ink Step, and screen shake (all adjustable in accessibility options)                                                                                                 |
| **Post-processing**                              | Subtle bloom on emissives, vignette, a slight paper-and-ink grain, and a brief chromatic shift on strong hits. All render at the **base resolution** to keep pixels crisp                                                                                     |

### 9.2 Ink-wash specific effects

These are what make the environment ours, not a *Dead Cells* copy:

- **Bokashi fog:** depth fog drawn as soft ink-wash gradients (graded washes), so far layers dissolve like a brush painting.
- **Ink in water:** ink drifts and blooms in puddles and canal water, and bleeds slowly when something lands in it.
- **Brush-stroke rain:** rain streaks drawn as thin brush strokes, in a few depths.
- **Ink dust on destruction:** broken objects release ink-colored dust and a short wash splash.
- **Paper and lantern light:** warm light through paper screens and lanterns in Shrine Heights and the Old Dojo, which contrasts with the cold neon elsewhere.
- **Negative space (*ma*):** some backgrounds deliberately leave empty washed areas, so the busy foreground reads clearly.

### 9.3 Zone lighting recipes

| Zone                 | Key lights                                                          | Volumetrics and air                                       | Surfaces                              |
| -------------------- | ------------------------------------------------------------------- | --------------------------------------------------------- | ------------------------------------- |
| **Neon Plaza**       | Pink and warm neon signs, stall lamps, vending machine glow         | Steam from stalls, haze under the fish sign, backlit rain | Wet ground reflecting neon, puddles   |
| **Rooftop Signage**  | Electric blue sign spill, red warning lights, sweeping searchlights | Wind-driven rain, cable sparks, shafts from signs         | Wet metal and glass, sign reflections |
| **Shrine Heights**   | Warm lanterns, a cold moon rim light, jade accents                  | Low mist, drifting paper scraps, soft rain                | Wet stone, lantern glow on the torii  |
| **Underpass Canals** | Teal lamp strips, flickering fluorescents                           | Dripping, steam from pipes, flood haze during Canal Surge | Water with ink bloom, wet concrete    |
| **Hidden Network**   | Dim fluorescent, server lights, a few warm bulbs                    | Dust motes, cable sparks                                  | Metal grates, flickering panels       |

### 9.4 Lighting rules for gameplay

Environment quality must never cost readability.

- **Telegraphs, hazard marks and Akane's silhouette are on an unlit, emissive layer.** Lighting, fog and bloom never dim or hide them.
- **Fog and particle density have a readability cap** in combat areas, and foreground layers stay low-opacity.
- **The night clock and the Blackout event** change the gradient maps and light levels, but must keep telegraph and walkable-surface contrast.
- **Lights from threats:** boss attacks and enemy telegraphs may add small glows in their own colors, but **never light the scene in a way that mimics another enemy's color.**
- **Hit-stop and shake** follow the accessibility sliders, and never mute audio.

### 9.5 Characters and normal maps

- **Akane and the six bosses:** hand-drawn normal maps, so they take light from neon and lanterns convincingly.
- **Standard enemies:** a lighter treatment (rim light and a tint from the nearest strong light), with no full normal maps, to keep the workload and the Switch budget in check.
- **Named elites** use their base enemy's treatment.

### 9.6 Budgets (starting values)

| Budget                    | PC                                  | Switch 2                               | Original Switch                 |
| ------------------------- | ----------------------------------- | -------------------------------------- | ------------------------------- |
| Dynamic lights per screen | About 12                            | About 10                               | About 6                         |
| Baked or static lights    | Most neon and signage               | Most neon and signage                  | Most neon and signage           |
| Parallax layers           | 4                                   | 4                                      | 3                               |
| Particles on screen       | About 800                           | About 600                              | About 300                       |
| Post-processing           | Bloom, vignette, grain              | Full-resolution bloom, vignette, grain | Half-resolution bloom, vignette |
| Reflections               | Per-strip reflections on wet ground | Per-strip reflections on wet ground    | Reduced to key surfaces         |
| Output                    | Scaled from 640 x 360               | 1080p handheld, up to 4K docked        | 720p handheld, 1080p docked     |
| Frame rate                | Uncapped option, with caps          | Stable 60 FPS                          | Stable 60 FPS                   |

When frame time runs over budget on console, cosmetic load drops first (particles, then lights, then fog layers). Telegraphs, hazard marks and gameplay entities are never reduced. The art style also looks good at low settings: the ink wash look does not depend on heavy effects.

The **original Switch is the floor:** every effect needs a cheaper fallback that keeps gameplay readability. Switch 2 runs near PC-mid budgets, and the extra headroom should go to the heaviest cases (large waves with broad destruction).

### 9.6b PC settings presets

The game renders at **640 x 360** and scales up, so the GPU cost stays low even at 4K. The pressure points are the **CPU** (flanking AI, navigation updates, destruction, particles) and **memory** (hand-drawn normal maps roughly double the texture data). PC settings let low-end machines run the game.

| Preset     | Lights            | Particles | Parallax | Post-processing       | Reflections             |
| ---------- | ----------------- | --------- | -------- | --------------------- | ----------------------- |
| **High**   | About 12          | About 800 | 4        | Full                  | Per-strip               |
| **Medium** | About 8           | About 500 | 4        | Bloom and vignette    | Per-strip, lower detail |
| **Low**    | About 4           | About 300 | 3        | Half-resolution bloom | Key surfaces only       |
| **Potato** | Baked lights only | About 100 | 2        | None                  | Off                     |

- **Minimum-spec target [TBD: confirm in testing]:** 60 FPS in typical fights on hardware comparable to *Dead Cells'* minimum requirements (an Intel i5-class CPU, 2-4 GB of RAM, and a GTX 450 or Radeon HD 5750-class GPU), using the Low or Potato preset.
- **Potato preset** also lowers the enemy simulation caps (see the map design document §15) and turns off the heaviest effects, but never removes telegraphs or any gameplay information.
- Individual settings can be changed separately from the presets.
- **Frame rate:** an uncapped option, plus caps and vsync. Gameplay runs on a fixed 60 Hz step, so frame rate never changes timings (see the main doc §10.1).

### 9.7 Production notes

- Each environment asset ships as: **grayscale albedo, normal map, emissive mask** (and an optional reflection mask).
- One **lighting artist role** owns light placement per zone, the gradient maps and the night clock.
- Hand-drawn normal maps are slower than generated ones, but they are what gave *Dead Cells* its look, so we budget for them on environments, Akane and bosses.
- A **lighting pass** is part of the graybox acceptance: readability checks in each lighting band, in rain, and with Blackout.

**Sources**

- [Interview With the Developers of Dead Cells (80.lv)](https://80.lv/articles/interview-with-the-developers-of-dead-cells)
- [Dead Cells: Your Next Favorite Pixelart Game (80.lv)](https://80.lv/articles/dead-cells-your-next-favorite-pixelart-game)
- [Art Design Deep Dive: Giving back colors to cryptic worlds in Dead Cells (Game Developer)](https://www.gamedeveloper.com/production/art-design-deep-dive-giving-back-colors-to-cryptic-worlds-in-i-dead-cells-i-)
- [Art Design Deep Dive: Using a 3D pipeline for 2D animation in Dead Cells (Game Developer)](https://www.gamedeveloper.com/production/art-design-deep-dive-using-a-3d-pipeline-for-2d-animation-in-i-dead-cells-i-)
- [The Visual Effects of Dead Cells (Unity forum)](https://discussions.unity.com/t/the-visual-effects-of-dead-cells/689349)

---

## 10. Kill animations

The point of *Akane* is that you kill someone and instantly keep going. Kills must be satisfying, varied and **brief.** There are no finishers, kill cams or camera cuts. Variety comes from the victim's side: how they come apart, what flies off, and what it sounds like.

### 10.1 Rules

- **A kill never locks Akane.** Input, dash and the next swing cancel out of it at once.
- **Hit-stop is tiny:** 2-3 frames for a normal kill, 4-5 for elites, longer only for bosses (see the boss document). It follows the accessibility hit-stop slider.
- **Bodies clear fast** (about 0.5-1 second). Stains stay on the background, bodies do not.
- **Every kill is three layers:** a **cut or impact signature** (weapon), a **reaction** (enemy type and direction) and **context modifiers** (airborne, on a zipline, deflected, thrown, and so on). A small authored set multiplies into hundreds of distinct-looking kills.
- **Gore is bright red, as in the original,** and **full gore only** (there is no gore setting). Red belongs to gore and the logo. Telegraphs are white-hot, so a splash is never mistaken for a threat.
- **Layering:** telegraphs and hazard marks sit on an unlit, emissive layer **above** all gore, gibs and stains. Gore never covers a telegraph.

### 10.2 Sword kills: the procedural slice

Sword kills **cut the enemy sprite along the actual slash angle** using a runtime mask. The two halves slide apart and rotate with simple physics. That gives every angle (flat, diagonal, vertical, a thrust) a different result on all 16 enemies with no extra frames.

- A **high horizontal cut at neck height** is a decapitation. The sword therefore covers heads as well.
- A multi-enemy arc (Nodachi, Dragon Slash) cuts each enemy along that arc relative to its own pose.
- **Edge quality:** the cut edge is drawn with a pixel-clean, brush-edged finish so it matches the hand-drawn art. **[TBD: art check on the cut edge. Fallback: 3-4 hand-authored cut angles per enemy.]**
- **Bosses and named elites** can have hand-authored finishing frames where it matters.

**Signature per katana**

| Katana | Kill signature |
|---|---|
| **Kuro** | A clean diagonal. The halves slide along the cut line and a thin ink line lingers |
| **Rebi** | Deflect kills: the returned bullet pops the shooter (see gun kills) |
| **Tadus** | The thrown blade pins the enemy to a wall or crate for half a second. The recall pulls it out with a spray |
| **Nodachi** | A huge horizontal arc. Upper bodies fly off, and the lingering ink arc stays for 0.3 s |
| **Twin Tantō** | A quick X cut. The enemy holds a beat, then comes apart in four |
| **Echo Blade** | The enemy stands untouched, then a second later the body separates where the echo lands |
| **Kusarigama** | The pulled enemy is clipped mid-air on the way in |

### 10.3 Gun kills: headshots

In the original, gun kills were always **headshots.** Akane II keeps that: every bullet kill is a hit to the head. There is no entry or exit geometry to author.

- Every enemy has a **head anchor** on its sprite, and each gun kill plays a **head reaction** at that anchor. The head and body snap away from where the shot came from.
- Armored enemies keep their rules: the **Tank** is immune to bullets except the Magnum, and a **Shieldbearer's** front blocks bullets (the Magnum pierces). A human shield covers the head.

**Per gun**

| Gun | Head kill |
|---|---|
| **Patron v26** | One clean pop |
| **Inquisitor M103** | The head snaps three times, a triple tap |
| **Vicious S36** | The head disintegrates into paper-like confetti |
| **Magnum XT5** | A clean through-line that connects every head in the row, lighting up each |
| **Double Barrel** | At close range the head vaporizes and the body is knocked back |
| **Gravitational Beam** | No kill. Enemies crumple into a dense ink ball when a follow-up hits them |
| **Deflected shots** | The returned bullet hits the shooter in the face, with its own sound. A Stabilizer split bullet takes two heads |

**Headgear gag per enemy** (what flies off or breaks when the head is hit)

| Enemy | Head gag |
|---|---|
| Yakuza Guy | Sunglasses spin off |
| Shooter | The optic over his eye shatters |
| Cyber Ninja | The visor cracks and spits sparks |
| Tank | A helmet-ping, then the plate drops |
| Hexer | The white mask splits |
| Archer | The hood is blown back |
| Banner Caller | The headband flies off |
| Phantom | The shimmer drops for one frame |
| Others | A matching small detail (Lancer's sash, Sniper's scope, Duelist's collar) |

### 10.4 Context kills

| Situation | Kill |
|---|---|
| **Ink Step counter** | The cut appears where Akane *was*: the afterimage does the killing |
| **Dragon Slash** | Everyone along the line stays standing until the streak ends, then all fall at once like dominoes |
| **Dragon Slayer** | Enemies flatten into ink-brush silhouettes and wash away instead of gibbing |
| **Electrified hazard** | A skeleton flash for one frame, then collapse |
| **Flood water** | They sink into black ink water |
| **Fire** | They burn out and crumble |
| **Zipline or airborne** | They drop with the rope, or hang for half a second |
| **Thrown human shield** | The thrown body hits like a bowling ball |
| **Cyber enemies** | Sparks and a flash of circuitry in the cut |
| **Wall pin (Tadus)** | Held for half a second |

### 10.5 Enemy-specific beats

| Enemy | Beat |
|---|---|
| Tank | The plate cracks first, then it collapses. A rear-plate kill bursts the plate |
| Bomber | The pack goes off and gibs its neighbors |
| Shieldbearer | The shield stays standing for a beat after they fall |
| Drone Handler | All the drones drop together |
| Banner Caller | The banner flutters down |
| Duelist | Still for a beat, then falls |
| Archer, Sniper | They fall from their perch |

### 10.6 Rare gags

About **1 kill in 20** gets a small extra: sunglasses spinning off, a cigarette arcing through the rain, a gold tooth glinting, a tattoo dissolving. Each is **12 frames or fewer,** purely cosmetic, never repeats within 5 kills, and never hides a telegraph.

### 10.7 Gore and stains

- **Gore** is bright red and matte, drawn as brush-edged flecks and splashes, on bodies and on the ground and wall layers.
- **Stains** are painted on the background as brush-stroke marks and are **rinsed away by the rain over about 1-2 minutes.** The arena is painted by the run and slowly washed.
- **Budgets (starting values):** stain decals at about 300 on PC, 200 on Switch 2 and 100 on the original Switch, pooled and oldest-first. Gibs use a pooled cap (see §9.6).
- Stains never sit on the telegraph layer, and never recolor walkable-surface edges or hazard marks.

### 10.8 Flow escalation

Kill visuals grow with the Flow tier. This is **cosmetic only** and has no gameplay effect.

| Flow tier | Kill presentation |
|---|---|
| None | Standard splash |
| I | A slightly larger splash |
| II | A bigger splash with a few drips |
| III | A bold splash with drips and one extra hit-stop frame |

### 10.9 Sound

Each kill is a cut or shot sound, a body sound and a splash. Kill sounds **step up a musical scale** with the Flow combo and reset when the combo breaks (see the audio document §3.2).

### 10.10 Cosmetic kill effects

A small separate cosmetic category (five items) that changes the **look** of the kill splash. They are optional, never change readability rules, and never use white-hot or a boss accent color.

| Kill effect | Look | Unlock |
|---|---|---|
| **Blood (default)** | The standard bright red gore | Default |
| **Sakura** | A burst of petals instead of gore | Reach wave 30 |
| **Glitch** | The enemy scatters into pixels, strongest on cyber enemies | Kill 500 cyber enemies in total |
| **Ash** | Gray ash flakes with no stain | Survive 5 Blackout events |
| **Paper** | The enemy tears like paper | Get 100 deflect kills in total |
| **Neon Ink** | A glowing cyan ink wash | Score 250,000 in a single run |

### 10.11 Asset estimate

| Group | Count |
|---|---|
| Enemy head reactions (2-3 per enemy) | About 40 |
| Katana signature effects | 7 |
| Gun head effects | 6 |
| Context kill variants | About 10 |
| Enemy-specific beats | About 8 |
| Rare gags | About 8 |
| Flow splash tiers | 4 |
| Cosmetic kill effects | 5 |
| Slice shader and physics | 1 system |

### 10.12 Not in scope

No finishers, kill cams, slow-motion kills, camera cuts, or per-enemy death scenes. Boss deaths use the freeze-frame ink slash described in the boss document.

---

# Akane II — Audio Design

Companion to [akane-ii-design.md](akane-ii-design.md) (§9 Story and Presentation, audio direction) and [akane-ii-visual-briefs.md](akane-ii-visual-briefs.md).

> Cue counts and levels are starting values for the audio team. Audio is a **gameplay system** here, not just atmosphere: telegraph cues, rear-threat cues and boss motifs carry information the player needs.

## Contents

1. [Pillars](#1-pillars)
2. [Music](#2-music)
3. [Sound effects](#3-sound-effects)
4. [Vocals](#4-vocals)
5. [Gameplay audio cues (information)](#5-gameplay-audio-cues-information)
6. [Mixing and implementation](#6-mixing-and-implementation)
7. [Accessibility](#7-accessibility)
8. [Asset inventory](#8-asset-inventory)

---

## 1. Pillars

1. **Information first.** Telegraphs, rear threats and boss moves are audible cues. A skilled player can play with the screen off for a moment.
2. **Traditional over electronic.** Shamisen, taiko and shakuhachi sit over a synth bass and drum pulse, matching the ink wash and cyberpunk blend.
3. **Tactile and organic, with a cyber edge.** Paper, brush, bamboo, wet steel and rain foley, layered with synth and digital hits.
4. **One rainy night.** Rain is the constant bed. The night deepens across the run, and dawn never comes.
5. **No spoken story.** Story is text. Voices are non-verbal only.

---

## 2. Music

### 2.1 Structure: layered stems with zone motifs

One adaptive score for the whole run, built from **layered stems** that rise and fall with intensity, plus a **short motif per zone** that plays over it.

**Stems (all in the same key and tempo family)**

| Layer                    | Content                                         | Enters when                                           |
| ------------------------ | ----------------------------------------------- | ----------------------------------------------------- |
| **Bed**                  | Rain-like texture, low drone, sparse shakuhachi | Always                                                |
| **Pulse**                | Synth bass and soft drum pulse                  | From wave 1                                           |
| **Taiko**                | Taiko drum patterns                             | Fights with 3+ active enemies                         |
| **Shamisen**             | Rhythmic shamisen phrases                       | Flow tier I and above                                 |
| **Strings / synth lead** | Driving strings or lead synth                   | Flow tier II and above                                |
| **Climax**               | Full-band hits, cymbal swells, choir-like synth | Flow tier III, Dragon Slayer, or a large threat count |

**Control inputs:** wave band (late night to still night), Flow tier, number of active enemies, token pressure and whether Akane is in combat or traveling.

**Wave bands** (matching the night clock)

| Waves | Band        | Character                                          |
| ----- | ----------- | -------------------------------------------------- |
| 1-24  | Late night  | Brighter, with neon synths                         |
| 25-49 | Midnight    | Deeper, heavier taiko                              |
| 50-74 | Small hours | Sparse, tense, long reverb tails                   |
| 75-99 | Before dawn | A pale, hopeful high pad that never resolves       |
| 100+  | Still night | The same material, never resolving, slowly layered |

**Transitions:** all layer changes are quantized to the bar (2 beats on fast changes), so music never stutters. The breather strips back to bed and pulse.

### 2.2 Zone motifs

A short motif (2-4 bars) plays over the score when the player enters a zone and repeats softly while they stay.

| Zone                 | Motif character                                           |
| -------------------- | --------------------------------------------------------- |
| **Neon Plaza**       | A bright, busy synth arpeggio with a market-like shamisen |
| **Rooftop Signage**  | A windy, soaring lead with a fast hi-hat feel             |
| **Shrine Heights**   | Sparse shakuhachi and temple bell, a quieter pulse        |
| **Underpass Canals** | Dripping percussion and a low, echoing drone              |
| **Hidden Network**   | A humming, glitchy texture with minimal rhythm            |

### 2.3 Boss themes

**A unique theme per boss,** built from stems that **add a layer for each phase,** with a **stinger at each phase break.** Themes keep the same tempo family as the main score so transitions are smooth.

| Boss                     | Theme character                                                  | Instruments to feature                |
| ------------------------ | ---------------------------------------------------------------- | ------------------------------------- |
| **Katsuro**              | A relentless duel: driving, rhythmic, with pink-hot synth        | Taiko, shamisen, distorted lead       |
| **The Debt Collector**   | Swaggering and mechanical, with a coin-like percussive pulse     | Pizzicato strings, snare, brass stabs |
| **The Hunter**           | Sparse and unsettling, with long silences and a whispering pulse | Shakuhachi, bowed textures, whispers  |
| **The Crimson Kite**     | Soaring and sharp, with fast diving figures                      | Strings, high synth, fast drums       |
| **The Demolisher**       | Heavy and industrial, with impact hits that sync with the swings | Anvil, low brass, taiko               |
| **The Floodgate Warden** | Cyclical and watery, with a rhythm that follows the tide         | Bells, marimba, low synth             |

**Rules**

- **Katsuro's theme evolves by tier** (more layers and a harder edge) to match his visuals.
- The **vulnerability window** gets a subtle musical cue (a drop in the mix, then a swell) so players can feel it without looking.
- **Phase breaks** play a short stinger and strip the music back for the 1.5 s transition.
- **Boss death:** a silence of about 0.4 s (matching the freeze-frame), then the score tally sound.

### 2.4 Other music

| Cue                 | Description                                                     |
| ------------------- | --------------------------------------------------------------- |
| **Main menu**       | A calm bed with a shakuhachi melody and rain                    |
| **Armory / Codex**  | A quieter, intimate version of the menu                         |
| **Dojo (tutorial)** | Very sparse: shakuhachi and a wood block, rain at a distance    |
| **Practice range**  | Neutral and light, with no intensity layers                     |
| **Story beats**     | A short, quiet stinger per beat, with a darker one for wave 100 |
| **Dojo Memory**     | A single shakuhachi line over rain, with a soft low drone       |
| **Run summary**     | A short, dry motif. Different for a personal best               |
| **Boss Rush**       | The six boss themes in order, with a short bridge between them  |
| **Time Attack**     | The normal adaptive score with a tighter, faster pulse          |

---

## 3. Sound effects

### 3.1 Palette

- **Ink wash side (organic):** brush on paper, rice paper tearing, bamboo knocks, wet steel, cloth, rain on metal and rooftops.
- **Cyberpunk side (digital):** servos, synth hits, glitches, neon hum, electrical arcs.
- Every important sound mixes **one organic layer and one digital layer.**

### 3.2 Akane

| Category         | Cues                                                                                                                                                                                                                                                      |
| ---------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Movement**     | Footsteps by surface (wet concrete, metal, wood, tile, grates), jump, land (short and long), climb, zipline ride (whine), updraft (whoosh), spring, dash (a short cloth-and-air whip, different per boots), slide                                         |
| **Sword**        | One swing sound per katana (7), hit, kill (a short "cut" plus a brush splash), deflect (a bright metallic ring), miss                                                                                                                                     |
| **Gun**          | One fire sound per gun (6), empty click, ammo gained (sword-kill tick), the Gravitational Beam's hum and pull                                                                                                                                             |
| **Defense**      | Ink Step success (a clean brush chime and a brief slow-motion sweep), Ink Step miss (dull), dash charge ready (soft tick)                                                                                                                                 |
| **Human shield** | Grab, hold (strain), absorbed hit (thud), shield break, throw                                                                                                                                                                                             |
| **Flow**         | Tier up (a rising shamisen strum), tier down warning (a falling note), tier lost                                                                                                                                                                          |
| **Kill chain**   | Kill sounds step up a **musical scale** with the Flow combo, in the **key of the current score,** and reset when the combo breaks. The ladder spans at most an octave and a half, then holds. Each kill is a cut or shot sound, a body sound and a splash |
| **Specials**     | Dragon Slash (a fast ink streak), Dragon Slayer (a rising wash and a huge release), meter ready                                                                                                                                                           |
| **Loadout**      | Gadget activate and ready cues (11 gadgets), mod toggle                                                                                                                                                                                                   |
| **Death**        | A short, clean cut sound (so restarts stay fast)                                                                                                                                                                                                          |

### 3.3 Enemy telegraph sounds (each unique)

Each enemy has **a signature wind-up sound** that matches its telegraph tier and can be identified without looking.

| Enemy          | Telegraph sound                                          |
| -------------- | -------------------------------------------------------- |
| Yakuza Guy     | Cloth rustle and a blade drawn                           |
| Shooter        | A rising servo whine as the laser locks, then a crack    |
| Skirmisher     | A quick light patter and a blade flick                   |
| Tank           | A deep mechanical groan and a heavy pre-slam hiss        |
| Lancer         | A long steel scrape, then a sharp thrust                 |
| Archer         | A bow creak and a whistling arrow arc                    |
| Shieldbearer   | A shield scrape and a grunt-like bash charge             |
| Cyber Ninja    | A rising electric hum, then a dash snap                  |
| Bomber         | A fuse hiss and a ticking charge                         |
| Zipline Raider | A whistling wire and a hook clink                        |
| Banner Caller  | Cloth snapping in the wind and a low war-drum beat       |
| Hexer          | A low brush-on-paper scrape and a dull hum from the zone |
| Drone Handler  | Beeps and high-pitched drone whines                      |
| Phantom        | A whisper and a faint pulse (long tier)                  |
| Duelist        | Slow breath, a sword drawn halfway and a parry ring      |
| Sniper         | A rising high tone as the laser locks                    |

**Deaths, hits and recoveries:** each has short matching sounds. Armor hits get a metallic ping.

### 3.4 Boss signature sounds

| Boss                 | Motif                                      |
| -------------------- | ------------------------------------------ |
| Katsuro              | A low drum hit marks each dash commit      |
| The Debt Collector   | A coin spin before each shot               |
| The Hunter           | A whisper from the direction of the strike |
| The Crimson Kite     | A rising whine before each dive            |
| The Demolisher       | A klaxon and the creak of the chain        |
| The Floodgate Warden | A bell tone that rises with the tide       |

### 3.5 World and environment

| Category          | Cues                                                                                                                                                                |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Rain**          | A constant bed (light), a heavier version for the Rain Shower event, different on metal, tile and water                                                             |
| **Zone ambience** | Plaza: crowd, frying, neon hum. Rooftops: wind, traffic, buzzing signs. Shrine: wind, a distant bell. Canals: dripping, echo, pumps. Hidden: humming cables, static |
| **Hazards**       | Electrified rails (arc crackle), steam vents (hiss), flood water (rising rumble), flames (crackle). All warn at least 0.8 s early                                   |
| **Destruction**   | Distinct break sounds by material (glass, wood, metal, paper). Debris settles quickly                                                                               |
| **Events**        | Siren for the announcement, the Blackout power-down, the Canal Surge rumble                                                                                         |
| **Secrets**       | A subtle unique cue for each of the 12 secrets (a whisper, a chime, a hum)                                                                                          |
| **Traversal**     | Zipline anchors cut, updraft launch, service lift                                                                                                                   |

### 3.6 UI

| Category            | Cues                                                                     |
| ------------------- | ------------------------------------------------------------------------ |
| **Menus**           | Ink-brush navigation, select and back sounds                             |
| **Wave**            | Wave start (soft), wave clear, early clear bonus, pressure timer warning |
| **Story and codex** | Beat card in and out, codex unlock                                       |
| **Unlocks**         | Item unlocked, cosmetic unlocked, challenge progress                     |
| **Run summary**     | Score tally, personal best                                               |

---

## 4. Vocals

**Minimal non-verbal sounds only.** No spoken dialogue.

| Source                    | Sounds                                                                                                                      |
| ------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| **Akane**                 | Exertion on slash and dash, short grunts on hits and landings, a final short cry on death. Sparing, so they don't fatigue   |
| **Standard enemies**      | Short barks on spotting Akane, on attacks and on death. A few variations per enemy type                                     |
| **Bosses**                | Katsuro's breathing and short exertion. The Hunter has none (silence is his sound). Others have grunts matching their scale |
| **Katsuro's dying words** | The text appears on screen (see the copy deck), with a **vocal sting:** a breath, then a low exhale, with no words          |
| **Crowds**                | Plaza crowd murmur as ambience                                                                                              |

---

## 5. Gameplay audio cues (information)

These cues carry information and have **top priority in the mix.**

| Information                       | Cue                                                                  |
| --------------------------------- | -------------------------------------------------------------------- |
| **A telegraph starting**          | The enemy's signature wind-up sound (§3.3), panned to its direction  |
| **A threat behind or off-screen** | Footsteps or a whisper from behind, matching the screen-edge smear   |
| **Lock-on (Shooter, Sniper)**     | A rising tone that locks at the start of the fire window             |
| **Vulnerability window (boss)**   | A musical drop, then a swell, plus a soft chime                      |
| **Phase break**                   | A short stinger and a strip-back                                     |
| **Flow tier change**              | A rising or falling shamisen note                                    |
| **Hazard about to become lethal** | A rising hum for at least 0.8 s                                      |
| **Event incoming**                | A siren about 8 seconds ahead                                        |
| **Ziplines / updrafts / launch**  | Distinct whine, whoosh and spring sounds so they can be found by ear |
| **Secrets**                       | A faint cue when Akane is near                                       |
| **Heat rising in a zone**         | A subtle change in the zone's ambience                               |

---

## 6. Mixing and implementation

### 6.1 Priority tiers (highest first)

1. Telegraph and threat cues, lock-on tones and hazard warnings.
2. Akane's feedback (Ink Step, deflect, Flow, specials).
3. Enemy hits and deaths.
4. Boss and zone motifs.
5. Music stems.
6. Ambience and rain.

Higher tiers **duck** lower ones briefly. Telegraph cues are never ducked.

### 6.2 Adaptive rules

- Music layers change on bar boundaries.
- Hit-stop never stretches or mutes audio.
- The night clock gradually changes ambience: more reverb and fewer crowd sounds toward the small hours.
- Zone transitions crossfade over about 2 seconds.
- Distance and direction matter: all enemy and boss cues are positional (panned and attenuated). Cues from behind are slightly brighter for clarity.

### 6.3 Platform notes

- **Original Switch (the floor):** streamed stems, compressed formats, and a cap on simultaneous voices (about 32). Priority tiers decide which sounds are dropped first (ambience and low-priority hits).
- **Switch 2:** the same assets with a higher voice cap (about 48) and richer ambience layers.
- **PC:** the same mix with higher quality assets.
- **Loudness:** consistent targets across music, SFX and UI, with a separate mix for headphones and speakers.

---

## 7. Accessibility

- **Visual substitutes for audio cues:** the screen-edge threat smears, telegraph flares and Flow pulse already replace the main audio cues, so players who can't hear them are not at a disadvantage.
- **Separate volume sliders:** master, music, SFX, voice (non-verbal), ambience, and a dedicated **audio cue** slider for telegraph and threat sounds.
- **Mono output** and a **reduced-dynamics** mode.
- **Subtitles** for any text that has a vocal sting (Katsuro's dying words are shown as text anyway).
- Options to lower the intensity of bass and low-frequency hits.

---

## 8. Asset inventory

Rough counts for scoping.

| Group                    | Count                   | Notes                                                              |
| ------------------------ | ----------------------- | ------------------------------------------------------------------ |
| Main score stems         | 6 layers x 5 wave bands | Shared tempo and key family                                        |
| Zone motifs              | 5                       | Short loops                                                        |
| Boss themes              | 6                       | Each with 3 phase stems and stingers. Katsuro has tier variations  |
| Menu and mode cues       | About 10                | See §2.4                                                           |
| Akane SFX                | About 140               | Including 7 katanas, 6 guns, 4 boots and 11 gadgets                |
| Enemy SFX                | About 16 x 6            | A telegraph, an attack, a hit, a death, a bark and a recovery each |
| Boss SFX                 | About 6 x 15            | Per move, per phase                                                |
| Environment and ambience | About 60                | Rain, zone beds, hazards, destruction, events, secrets             |
| UI                       | About 40                |                                                                    |
| Vocals                   | About 100               | Akane, enemies and bosses                                          |
