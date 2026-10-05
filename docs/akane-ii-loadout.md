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

| Katana | Reach | Startup | Active | Recovery | Combo | Notes |
|---|---|---|---|---|---|---|
| **Kuro** | 1.5 | 4 f | 5 f | 10 f | 2-hit | Baseline. Moves 0.3 tiles forward on each swing |
| **Rebi** | 1.4 | 6 f | 8 f | 14 f | 1-hit | 160° arc. **Deflect window 8 f** (twice Kuro's). Deflected bullets return at the shooter at double speed |
| **Tadus** | 1.3 (melee) | 5 f | 5 f | 12 f | 1-hit | **Throw:** range 8, flies 0.3 s, then waits. **Recall** returns it along a line in 0.8 s, killing on the way back. Akane has **no melee while it is out.** Auto-recalls after 3 s |
| **Nodachi** | 2.6 | 9 f | 7 f | 18 f | 1-hit | Leaves a **lingering ink arc** for 0.3 s that kills anything passing through it |
| **Twin Tantō** | 0.9 | 3 f | 3 f | 6 f | 3-hit chain | Hits **both sides** at once. Each chain hit moves Akane slightly forward |
| **Echo Blade** | 1.4 | 5 f | 5 f | 11 f | 1-hit | Each swing is repeated in place **1.0 s later** (same angle, same position). One echo at a time, and an echo kills like a swing |
| **Kusarigama** | 1.3 (melee) | 5 f | 5 f | 11 f | 1-hit | **Chain:** range 4. On a standard enemy it pulls the enemy to Akane. On an anchor point (ledge, pole) it pulls Akane there. 3 s cooldown. Does not pull Tanks or bosses |

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

| Gun | Ammo | Fire rate | Sword-kill refill | Range | Notes |
|---|---|---|---|---|---|
| **Patron v26** | 6 | 4 / s | +1 | 12 | Accurate and forgiving. Single target |
| **Inquisitor M103** | 9 (3 bursts) | One burst of 3 in 0.25 s, then 0.4 s gap | +1 | 12 | Burst shots fan slightly, covering movement. Good against flyers like drones |
| **Vicious S36** | 30 | 12 / s | +4 | 8 | Sprays with spread. **Recoil pushes Akane back** 0.25 tiles per 3 shots, so firing while facing a wall is a mobility trick |
| **Magnum XT5** | 3 | 1 shot per 1.2 s | +1 | 14 | **Pierces** every enemy in line, including through Shieldbearer shields and Tank armor. Slow |
| **Double Barrel** | 2 | 2 shots, then 0.8 s | +1 | 4 (40° cone) | Kills everything in the cone. **Knocks Akane back** 2 tiles |
| **Gravitational Beam Emitter** | 4 charges | Hold to aim, release to pull | +1 | 7 | **Pulls** enemies in a 2-tile radius to a point over 0.6 s and **holds** them for 1.5 s. Does not kill. Does not pull Tanks or bosses |

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

| Gadget | Type | Behavior | Numbers |
|---|---|---|---|
| **Cyber Gloves** | Passive | Human shields absorb **4 hits** (not 3). Akane can **dash while holding a shield.** Throws hit harder (the thrown enemy kills two more on impact) | Passive |
| **Marionette Wire** | Active | Wire the held enemy. It becomes a **puppet** that walks ahead as a shield for 5 s (absorbs 3 hits), then detonates or drops on release | 25 s cooldown |
| **Katana Gun** | Passive | Every sword swing also fires a shot along the swing arc | Uses 1 ammo per swing |
| **Magnetic Pulse Emitter** | Active | EMP burst: disables cybernetic enemies for 3 s (Shooters, Cyber Ninjas, Drone Handlers and their drones), strips Tank plating, reveals cloaked enemies | Radius 3.5. Cooldown 25 s |
| **Stabilizer Bracelet** | Passive | **Ink Step window +20%**, a successful Ink Step refunds **both** dash charges (the base rule refunds one), and gun recoil is removed | Passive |
| **Adrenaline Shot** | Triggered | After an **8-kill chain within 6 s,** time slows to 40% for enemies for 3 s. Akane moves at normal speed | Cooldown 20 s |
| **Grapple Anchor** | Active | Grapples a ledge, zipline anchor or standard enemy and pulls Akane there | Range 9. 2 charges. 4 s recharge |
| **Updraft Fan** | Active | Places a fan that launches Akane upward (about 6 tiles). Enemies can also be launched | 2 fans out at once. Each lasts 25 s |
| **Hologram Decoy** | Active | A fake Akane that enemies target for 4 s. Disrupts flanking and token assignment | Cooldown 18 s |
| **Sumi Bomb** | Active | An ink cloud that blocks line of sight for 5 s. Enemies lose tracking | Radius 3.5. 2 charges. 14 s recharge |
| **Lure Beacon** | Active | Thrown device that attracts enemies for 5 s. Does not affect bosses | Cooldown 15 s |

**Design notes**

- **Passive gadgets** (Gloves, Katana Gun, Stabilizer) are always on, so they must be worth a slot without an activation. Their value is steady and skill-rewarding.
- **Active gadgets** give a go-to moment. Cooldowns are short enough to use several times per wave.
- Gadgets never grant invulnerability.

---

### 3.1 Original gadgets (verified) and what Akane II does with them

Checked online against the fan wiki and an achievements guide (see the sources below). The original had **11 gadgets.** We kept five names for continuity and redesigned their behavior; the other six Akane II gadgets are new designs, so the original's remaining names are not needed.

| Gadget | Original effect (verified) | Original unlock | Akane II |
|---|---|---|---|
| **Cyber Gloves** | More bullets per enemy killed | 30 kills with 100% katana accuracy | Human shield upgrade (no ammo effect) |
| **Stabilizer Bracelet** | Deflected bullets are divided in two | Reach the first boss with a 50+ combo | Wider Ink Step window, full dash refund, no recoil |
| **Adrenaline Shot** | Time slows in adrenaline mode | Defeat the boss with a katana special | Slow motion on an 8-kill chain |
| **Magnetic Pulse Emitter** | Kills one nearby enemy per second while aiming | Kill a Cyber Ninja with a katana special at 50+ combo | EMP that disables cyber enemies and strips armor |
| **Katana Gun** | Shoots after a special move | Defeat the boss at max level with a special | A shot on every sword swing |

**Other original gadgets** (names only, not used): Scope Visor, Extended Magazine, Nano Watch, Magnetic Detractor, Smart Bullets, plus two not documented in the sources. None of Akane II's new gadgets (Marionette Wire, Grapple Anchor, Updraft Fan, Hologram Decoy, Sumi Bomb, Lure Beacon) reuse these names.

**Continuity nods (decided):** the four original effects return as optional **gadget mods** (see §3.2), as trade-offs, not add-ons.

**Sources**

- [Akane Fandom wiki: Gadget](https://akane.fandom.com/wiki/Gadget)
- [Steam guide: All Achievements and How to Unlock](https://steamcommunity.com/sharedfiles/filedetails/?id=2925585160)
- [Akane Fandom wiki: Cyber Gloves](https://akane.fandom.com/wiki/Cyber_Gloves)
- [Akane Fandom wiki: Katana Gun](https://akane.fandom.com/wiki/Katana_Gun)

### 3.2 Gadget mods

On the same loadout screen as the katana, gun and gadget, a **mod chip** appears on the gadget slot when the equipped gadget has a mod. It is off by default and can be toggled freely before a run. A mod is a **swap: it replaces part of the gadget with something else, and never adds power on top.** Only six gadgets have a mod.

| Gadget | Mod | The mod gives | The mod takes away | Unlock challenge |
|---|---|---|---|---|
| **Cyber Gloves** | **Original Spec** | +1 ammo on every sword kill (on top of the gun's own refill) | The shield perks: shields hold 3 hits again, no dashing while holding one, base throw strength | Get **30 sword kills** in a row with no missed swings, in a single run |
| **Stabilizer Bracelet** | **Original Spec** | Deflected bullets **split in two** (both can kill) | The wider Ink Step window (the full dash refund and no recoil stay) | Reach the first boss (wave 10) with a **50+ combo** |
| **Magnetic Pulse Emitter** | **Original Spec** | While aiming the gun, **kills one weak enemy per second** within the radius (not Tanks, Shieldbearers, Duelists or bosses) | The armor stripping and the cloak reveal. Aiming speed is 30% slower while it is active | Kill a **Cyber Ninja with Dragon Slash or Dragon Slayer** at a 50+ combo |
| **Katana Gun** | **Original Spec** | After **Dragon Slash or Dragon Slayer**, fires a cone burst (about 4 tiles) that kills standard enemies | The per-swing shot | Land the final phase-ending hit on a **Tier 3 or higher boss** with a Dragon Slayer |
| **Grapple Anchor** | **Swing Line** *(new)* | Grapples anchor points and **swings like a pendulum**, keeping momentum and allowing attacks mid-swing | One charge instead of two, and it can no longer grapple enemies | Use the Grapple Anchor **100 times** in total |
| **Hologram Decoy** | **Overload** *(new)* | The decoy **explodes at the end**, killing standard enemies within 3 tiles | The decoy lasts 2 seconds instead of 4, so it disrupts flanking for less time | Kill **25 enemies** that were targeting a Hologram Decoy, in total |

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

| Boots | Dash length | Charges | Recharge | Notes |
|---|---|---|---|---|
| **Standard** | 3.5 | 2 | 1.5 s | Baseline |
| **Geta Springs** | 3.0 | 2 | 1.5 s | Dash can angle **45° upward** (a leap). Strong on the vertical map |
| **Rail Skates** | 5.0 | 2 | 1.8 s | A longer dash that **slides** for 0.4 s after, with reduced steering |
| **Silent Tabi** | 2.5 | 2 | 1.0 s | Short, quiet and fast to recharge. Enemies that lose sight of Akane **forget her position 2 s sooner** |

**Design notes**

- The dash remains the safe, reliable tool. Boots choose its flavor.
- Silent Tabi is the one oddball. Its effect is subtle, so its real value is faster recharge and the AI interaction.

---

## 5. Unlock challenges and order

Every item (except starters) is unlocked by an **item-specific mastery challenge** that teaches the skill the item rewards. Challenges are completed in normal arcade runs. Progress counters for "total" challenges accumulate across runs. Unlocks never raise power (see the main doc §8).

**Starting items:** Kuro, Patron v26, Standard boots, Cyber Gloves (it teaches the new shield mechanic). Players may also choose **no gadget.**

| Item | Slot | Tier | Unlock challenge | What it teaches |
|---|---|---|---|---|
| Kuro | Katana | Start | none | The baseline |
| Patron v26 | Gun | Start | none | The baseline |
| Standard boots | Boots | Start | none | The baseline |
| Cyber Gloves | Gadget | Start | none | Human shields |
| **Rebi** | Katana | 1 | Deflect **15 bullets** in a single run | Deflecting |
| **Twin Tantō** | Katana | 1 | Reach **Flow III** (30 combo) in a single run | Chaining kills |
| **Inquisitor M103** | Gun | 1 | Get **40 gun kills** in a single run | Gun use |
| **Stabilizer Bracelet** | Gadget | 1 | Land **25 perfect Ink Steps** in total | Ink Step |
| **Adrenaline Shot** | Gadget | 1 | Reach a **20-kill combo** | Keeping combos |
| **Geta Springs** | Boots | 1 | Use **20 launch points** in total | The vertical map |
| **Nodachi** | Katana | 2 | Kill **5 enemies with one swing** | Positioning |
| **Tadus** | Katana | 2 | Kill **3 or more enemies with one throw,** 5 times | Ranged melee |
| **Double Barrel** | Gun | 2 | Kill **25 enemies by deflecting bullets** in a single run *(from the original)* | Deflecting |
| **Magnum XT5** | Gun | 2 | Kill **3 enemies with one gun shot,** 5 times | Lining up shots |
| **Katana Gun** | Gadget | 2 | Alternate gun and sword kills for **20 kills** (no more than 2 of the same in a row) | Mixing tools |
| **Magnetic Pulse Emitter** | Gadget | 2 | Kill **10 cybernetic enemies** (Shooters, Cyber Ninjas, Drone Handlers) in a single run | Cyber counters |
| **Grapple Anchor** | Gadget | 2 | Spend **60 seconds on ziplines** in a single run | Traversal |
| **Rail Skates** | Boots | 2 | Travel **5,000 tiles** by dashing in total | Dash use |
| **Echo Blade** | Katana | 3 | Kill **3 enemies with one Dragon Slash,** 3 times | Setups |
| **Kusarigama** | Katana | 3 | Get **15 kills from a zipline** in total | Combat on the move |
| **Vicious S36** | Gun | 3 | Get **30 gun kills within 2 seconds after a dash** in total | Moving and shooting |
| **Hologram Decoy** | Gadget | 3 | Kill **20 Skirmishers** in total | Counter-flanking |
| **Sumi Bomb** | Gadget | 3 | Kill **10 Shooters or Archers** in a single run | Counter-ranged |
| **Lure Beacon** | Gadget | 3 | Kill **30 enemies with Dragon Slash or Dragon Slayer** in total | Specials |
| **Updraft Fan** | Gadget | 3 | Find **5 secrets** in total | Exploration |
| **Silent Tabi** | Boots | 3 | Kill **30 enemies from behind** in total | Positioning |
| **Marionette Wire** | Gadget | 3 | Kill **3 enemies with one thrown human shield,** 10 times | Shield mastery |
| **Gravitational Beam Emitter** | Gun | 4 | Defeat **Katsuro and one other boss** in a single run *(capstone)* | Late-game skill |

**Rules**

- Challenges are chosen so that at least one is reachable in the first few runs, and the tier-4 capstone is a long-term goal.
- A challenge counter shows in the loadout screen so players know what to work on.
- No challenge requires an item that has not been unlocked.
- "In total" counters never reset. "In a single run" counters reset on death.

---

## 6. Synergies and balance checks

### 6.1 Strong, intended combinations

| Combo | Why it works |
|---|---|
| Kusarigama + Grapple Anchor | Two pull tools: fast through the whole map |
| Twin Tantō + Adrenaline Shot | Crowd chains keep Flow III and trigger slow motion |
| Gravitational Beam + Echo Blade or Dragon Slash | Clump, then one kill covers many |
| Magnum + Shieldbearer waves | A single piercing shot cuts through |
| Rebi + Stabilizer Bracelet | Defensive stack. Strong against Shooter waves |
| Cyber Gloves + Tadus | Throw a shield, then a blade |

### 6.2 Combinations to watch for balance

| Combo | Risk | Mitigation to check |
|---|---|---|
| **Rebi + Stabilizer + Sumi Bomb** | Turtling | The combo decay and wave pressure timer make passivity cost score |
| **Double Barrel + Gravitational Beam** | Clump and cone clears every wave | The beam's hold is short and ammo is low |
| **Vicious S36 + Rail Skates** | Mobility spam with sustained fire | Short gun range, ammo burn |
| **Tadus + Cyber Gloves** | A safe ranged kill loop | Tadus leaves Akane unarmed while it is out |
| **Echo Blade + Lure Beacon** | Pre-set trap kills | Beacon's short duration, echo timing |

### 6.3 Balance rules

- **No loadout trivializes a boss.** Each boss has two or more ways to land a hit, and none rely on a single item.
- **Top-run diversity:** in playtests, no single item should appear in more than about 25% of the top runs within its slot **[TBD]**.
- **Ammo economy:** no gun should sustain fire without sword kills for longer than a few seconds.
- **Gadget uptime:** the cooldown of an active gadget should not allow near-permanent coverage.
- **Every item has a weakness** that a specific enemy or boss exploits.
