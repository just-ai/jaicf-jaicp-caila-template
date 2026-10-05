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

| Band | Zone | Altitude |
|---|---|---|
| -1 | Underpass Canals | Below ground, about 12 tiles down |
| 0 | Neon Plaza | Ground level |
| +1 | Rooftop Signage (low) | 8-18 tiles up |
| +2 | Rooftop Signage (high) | 18-30 tiles up |
| +3 | Shrine Heights | 30-40 tiles up |
| (all) | Hidden Network | Threaded through every band |

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

| Class | Examples | Can use |
|---|---|---|
| **S** | Akane, most enemies | Everything, including vents |
| **M** | Shieldbearer, Lancer | Everything except vents and crawl spaces |
| **L** | Tank | Stairs, ramps and wide doors only. No ladders, vents or climbs |

---

## 4. Zone designs

Each zone lists: role, layout and sub-areas, combat character, traversal, hazards, destructibles, secrets, accent hue, landmark and ambience.

### 4.1 Neon Plaza (ground hub)

- **Role:** the starting area, the widest and the easiest to read. Players learn movement and the first enemies here. The Debt Collector's arena.
- **Accent hue:** neon pink. **Landmark:** a giant rotating neon fish sign. **Ambience:** crowd murmur, frying, neon hum.

**Sub-areas**

| Sub-area | Description | Character |
|---|---|---|
| **Fish Square** | The central open square under the fish sign | The main arena: open, with a few pieces of cover |
| **Stall Row** (west) | Dense market stalls and awnings | Heavy destructible cover. Great for Shooters and ambushes |
| **Noodle Alley** (north) | A narrow, lit alley with the first updraft (U-1) | A funnel. Strong for Lancers and chokepoints |
| **Arcade Block** (east) | An arcade building with thin partitions | Break-through walls open flank routes |
| **Pond Garden** (south) | A koi pond and a garden | The manhole and the Koi Pond Wall secret. Quiet, with cover |
| **Stair Wells** (two) | The staircases down to the Canals | Chokepoints and drop-in spawns |

- **Traversal:** short zipline hops between signs (Z-D), the fire escape and ladders up, **U-1** to the lower Rooftops, **Z-H** (a motorized line up for new players), and a manhole down.
- **Hazards:** none lethal. Destroyed gas stalls burst into a flame zone for 4 seconds (marked and telegraphed).
- **Destructibles:** stalls, vending machines, glass signs, awnings and partitions.
- **Secrets:** Koi Pond Wall, Manhole Maze, Vending Machine Cache.
- **Signature encounters:** crowd fights (Swarm event) and Shooter and Shieldbearer lines across the Square.

### 4.2 Rooftop Signage (mid to upper)

- **Role:** the main combat and traversal playground, with the largest zipline network and the most verticality. The arena for the Demolisher, and a hunting ground for the Hunter and the Kite.
- **Accent hue:** electric blue. **Landmark:** a huge vertical kanji sign. **Ambience:** wind, distant traffic, electrical buzz.

**Sub-areas**

| Sub-area | Description | Character |
|---|---|---|
| **West Rooftops** | Water tanks, antennas and a laundry yard | Medium cover. The Z-C descent |
| **East Rooftops** | A helipad, vents and the Z-A and U-2 launch points | Open, with long sightlines |
| **The Span** | Central bridges and the **crane** | The Demolisher's arena. A hub between the clusters |
| **Billboard Row** | Climbable sign frames and walkable billboards | The most vertical area. Archers perch here |
| **Helipad** | A wide open platform with ladders | A spawn point and a small open arena |

- **Traversal:** ziplines Z-A, Z-B, Z-C, Z-F, springs (S-1, S-2), climbable sign frames, U-2, and the service lift.
- **Hazards:** electrified sign wires (marked, cycling), steam vents (marked, cycling).
- **Destructibles:** glass sign panels, water tanks (flood a small area), antennas, thin partitions and rooftop cover. Billboards used as walkways are structural.
- **Secrets:** Neon Kanji, Broken Sign Roost, Roof Vent Drop.
- **Signature encounters:** Highwire events, Archer and Hexer pins from above, and the Hunter's stalks.

### 4.3 Shrine Heights (top)

- **Role:** the highest tier, quieter and more open. A rewarding but exposed place. The Katsuro arena, and a place the Kite visits (the Hunter never enters).
- **Accent hue:** jade green. **Landmark:** a red torii gate against the skyline. **Ambience:** wind, a distant bell, sparse strings.

**Sub-areas**

| Sub-area | Description | Character |
|---|---|---|
| **The Approach** | Narrow stairs and a ladder up to the top | A funnel, with a Sniper perch |
| **Torii Plateau** | A wide open plateau with a torii gate and lanterns | The main arena. Mostly open, with only the torii pillars and lanterns as cover |
| **Bell Terrace** | A terrace with the shrine bell | The Bell of the Shrine secret |
| **Lantern Path** | A path of stone lanterns | The Lantern Path secret |
| **Shrine Hall** | A small shrine building | A compact interior fight space |
| **Overlook** | The edge of the plateau | The start of Z-E (the return line) |

- **Traversal:** Z-A (rising line in), U-2, a ladder, Z-E down, and the maintenance shaft to the Hidden Network (with a hidden ladder back up).
- **Hazards:** none lethal. Wind is visual and audio only.
- **Destructibles:** lanterns (ink splash only), paper screens in the Shrine Hall, and the bell (a secret trigger, not destructible).
- **Secrets:** Bell of the Shrine, Lantern Path.
- **Signature encounters:** Sniper duels, and open duels with Duelists.

### 4.4 Underpass Canals (below ground)

- **Role:** tight corridors, ambush spots and a rhythm of water. A contrast to the open Plaza and Rooftops. The Floodgate Warden's arena.
- **Accent hue:** jade-teal (low saturation, see §14). **Landmark:** the floodgate wheel at the end of the main channel. **Ambience:** dripping, echo, pumps.

**Sub-areas**

| Sub-area | Description | Character |
|---|---|---|
| **West Tunnels** | Narrow tunnels with walkways | Tight. Strong for Skirmishers and Phantoms |
| **Central Channel** | The main water channel with bridges | The main lane. The flood hazard passes here |
| **East Tunnels** | Another tunnel network | A mirror of the west, with a different layout |
| **Pump Room** | Machinery and electrified rails | Hazard-rich |
| **Floodgate Chamber** | A large chamber with walkways at three heights | The Warden's arena |
| **Drain Mouths** | Three spawn outlets | Enemies arrive here |

- **Traversal:** slides and chutes down, **U-3** up to the Plaza underside, the service lift (up to the Rooftops), Z-F (a line down from the Rooftops), narrow ladders, and water crossings by bridge.
- **Hazards:** deep flood water (lethal, cycling), electrified rails (marked, cycling), steam vents.
- **Destructibles:** pipes, thin walls (break-through routes), crates and walkway railings.
- **Secrets:** Flooded Shrine, Pipe Whisper.
- **Signature encounters:** Ambush events, flank fights in the tunnels, and Canal Surge moments.

### 4.5 Hidden Network (threaded through all zones)

- **Role:** secrets, shortcuts and ambush routes. It is never required, and is always an option.
- **Accent hue:** shares the hue of the nearest zone near the entrance, shifting to a dim white deeper in. **Landmark:** a lit server room door. **Ambience:** humming cables, faint static, distant voices.

**Sub-areas**

| Sub-area | Description | Character |
|---|---|---|
| **Vent Crawls** | Low vents linking zones | Size S only. A quick path for Akane and Skirmishers |
| **Maintenance Shafts** | Vertical shafts with ladders | Climbs and drops. Only size S and M |
| **Cable Hall** | A hall of cables with Z-G | A secret zipline |
| **Server Room** | A locked room with terminals | The lore secret |
| **Old Dojo** | A sealed dojo | The Dojo Memory secret |
| **Junctions** | Small hubs | Ambush nodes for Phantoms |

- **Traversal:** vents (crawl), shafts (climb and drop), Z-G, and a hidden ladder and updraft (U-4) back up.
- **Hazards:** electrical panels (marked, cycling).
- **Destructibles:** a few panels and grates. Most of the network is structural.
- **Secrets:** Server Room, The Old Dojo, and the entrances to the network from every zone.
- **Signature encounters:** Phantom ambushes, and tight Skirmisher fights.

---

## 5. Route graph

Direct connections between zones, with direction. "Both" means walkable either way.

| From - To | Routes |
|---|---|
| Plaza - Rooftops | Fire escape (both), ladders (both), **U-1 updraft** (up), **Z-H** (up), **Z-C** (down) |
| Plaza - Canals | Stairs west (both), stairs east (both), drop gate (down), **U-3** (up) |
| Plaza - Hidden Network | Manhole (both) |
| Rooftops - Shrine Heights | **U-2 updraft** (up), ladder (both), **Z-A** (up), **Z-E** (down) |
| Rooftops - Hidden Network | Roof vent (down), **U-4** secret updraft (up) |
| Rooftops - Canals | Service lift (up, after it is activated from below), **Z-F** (down) |
| Canals - Hidden Network | Drain vents (both), maintenance ladder (both) |
| Shrine Heights - Hidden Network | Maintenance shaft (down), hidden ladder from the old dojo (up) |

**Redundancy requirement.** Every pair of zones must have at least two routes that share no intermediate zone, in both directions, **at all times** (including after boss damage and player destruction).

| Pair | Route 1 | Route 2 |
|---|---|---|
| Plaza - Rooftops | Direct (fire escape, ladders) | Via Canals (stairs, then service lift up or Z-F down) |
| Plaza - Canals | Stairs west | Stairs east |
| Plaza - Hidden | Manhole | Via Canals (stairs, then vents) |
| Plaza - Shrine | Via Rooftops (fire escape, then U-2) | Via Hidden (manhole, then the old dojo ladder) |
| Rooftops - Canals | Service lift / Z-F | Via Plaza (fire escape, then stairs) |
| Rooftops - Shrine | U-2 updraft | Ladder (or Z-A) |
| Rooftops - Hidden | Roof vent / U-4 | Via Canals (Z-F, then vents) |
| Canals - Hidden | Drain vents | Maintenance ladder |
| Canals - Shrine | Via Hidden (vents, then the dojo ladder up) | Via Plaza and Rooftops (stairs, fire escape, U-2) |
| Hidden - Shrine | Maintenance shaft down / dojo ladder up | Via Rooftops (roof vent or U-4, then U-2) |

**Size-class check:** the redundancy must hold for size L (Tank) enemies too, using only stairs, ramps and wide doors. The Plaza - Canals stairs, the Plaza - Rooftops fire escape ramp, and the service lift must be size L accessible.

---

## 6. Traversal systems

### 6.1 Base movement

| Action | Value **[TBD]** |
|---|---|
| Run speed | 9 tiles / s |
| Jump height | 3 tiles (hold for the full height) |
| Dash | 3.5 tiles (see the loadout document for boots) |
| Climb speed | 5 tiles / s on ladders and climbable frames |
| Fall | Always safe. A drop of over 8 tiles adds a short 0.15 s landing recovery |

### 6.2 Ziplines

Ziplines are the signature fast route. Most descend, and **upward travel uses launch points, climbs, motorized lines and gadgets.**

| ID | Route | Direction | Length | Notes |
|---|---|---|---|---|
| **Z-A** | Rooftop East (high) to the Shrine approach | One-way up (motorized rising cable) | Long | The scenic line. Can be **cut** by the Hunter or Demolisher |
| **Z-B** | Rooftop West to Rooftop East | Two-way (motorized) | Medium | Passes the crane. Cut by the Demolisher in phase 2 |
| **Z-C** | Rooftop West to Plaza West | One-way down | Medium | A fast escape to the ground |
| **Z-D** | Plaza sign to sign (three short hops) | Two-way | Short | Used by Zipline Raiders to enter the Plaza |
| **Z-E** | Shrine plateau edge to Rooftop East | One-way down | Medium | The return from the top. Z-A and Z-E form a loop |
| **Z-F** | Rooftop East to a Canals roof opening | One-way down | Short | A shortcut into the Canals |
| **Z-G** | Cable Hall in the Hidden Network | Two-way | Short | A secret line |
| **Z-H** | Plaza East to Rooftop West low | One-way up (motorized) | Short | A starter line for new players |

**Rules**

- Ride speed is about **14 tiles / s.** Akane can jump off at any time, can attack and shoot while riding, and can be hit.
- Every zipline has two **anchors** that can be cut by the sword or a shot. Cut lines **reform after 20 seconds** unless a boss event says otherwise.
- Zipline Raiders use Z-A, Z-B, Z-D and Z-E. Enemies can also be killed on a line.
- No zipline crosses a boss arena's central fighting space.

### 6.3 Launch points

| ID | Location | Takes Akane to | Notes |
|---|---|---|---|
| **U-1** | Plaza north alley | Lower Rooftops | The first launch point players find |
| **U-2** | Rooftop East edge | Shrine Heights approach | An alternative to Z-A |
| **U-3** | Canals channel vent | Plaza underside / drop gate | Returns players to the Plaza |
| **U-4** | Hidden Network (secret) | Rooftop vent | A secret way up |
| **S-1, S-2** | Rooftop springs | Higher signage | Short hops over gaps |

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

| Zone | Ground spawns | Special spawns |
|---|---|---|
| Neon Plaza | 3 (north, west and east alleys) | 1 high anchor for Zipline Raiders (Z-D) |
| Rooftop Signage | 3 (ladders, a helipad, a stairwell) | 2 high anchors (Z-B, Z-A) |
| Shrine Heights | 2 (the approach stairs, and the back stairs from the maintenance shaft) | 1 perch for Snipers |
| Underpass Canals | 3 (drain mouths) | none |
| Hidden Network | 2 (vent exits) | 3 ambush nodes for Phantoms |

### 8.2 Spawn-follow (every zone is always live)

Every zone can spawn enemies from wave 1. There is **no sleeping zone,** because a sleeping zone would be a free camping spot.

- **Spawn-follow rule:** each wave picks its spawn points from those that are **out of sight and within about 6-15 seconds of travel** from Akane, wherever she is. A player who runs to the top of the map is met at the top of the map.
- **Every zone can host a full wave.** Each zone has at least two ground spawn points and one special spawn point, so the rule works everywhere (Shrine Heights has two ground spawns for this reason).
- **Early game is taught by the enemy roster and wave size,** not by geography. Waves 1-9 introduce one new enemy per wave and use small budgets (see the enemy document), so the whole map is open but each wave is small and readable.
- **Spawn zones are weighted by zone affinity** (§8.3) and **zone heat** (§8.4), not locked.

### 8.3 Zone affinity

Enemy types prefer certain zones, which gives each zone a character without fixing where they appear.

| Zone | Favored enemies |
|---|---|
| Plaza | Yakuza Guys, Shooters, Shieldbearers, Tanks, Bombers |
| Rooftops | Archers, Zipline Raiders, Hexers, Skirmishers, Snipers (perches) |
| Shrine Heights | Snipers, Duelists, Cyber Ninjas |
| Canals | Skirmishers, Lancers, Drone Handlers, Phantoms |
| Hidden Network | Phantoms, Skirmishers |

Affinity is a weighting, not a rule: any enemy can appear elsewhere (a wave director may break affinity for variety).

### 8.4 Zone heat

To stop players from camping one safe spot, each zone has a **heat** value.

- **Heat rises** while Akane is in a zone (after an 8-second grace period) and **cools** while she is elsewhere.
- **Heat levels** and effects **[TBD]**:

| Level | Heat | Effect |
|---|---|---|
| **Cool** | 0-30 | Normal spawning |
| **Warm** | 30-60 | +20% of the wave's spawns are placed in this zone |
| **Hot** | 60-90 | +50% of the spawns, and the group director assigns one extra Pincer |
| **Overheated** | 90+ | Pincers from two routes at once, and a Suppressor on the nearest perch |

- Heat **redistributes** the wave's budget. It does not add budget, so moving is not punished with a bigger wave, only a more focused one.
- Heat **pauses** during boss waves and the breather.
- **Feedback:** the zone's accent lighting pulses faintly as heat rises. The optional minimap tints the zone.
- Moving between zones keeps heat low, which rewards using the map and the traversal tools.

---

## 9. Environment: hazards, destruction and events

### 9.1 Hazards

**Falls are safe.** Only clearly marked hazards kill, and they behave rhythmically.

| Hazard | Zones | Behavior |
|---|---|---|
| **Deep flood water** | Canals | Lethal when deep. Rises and drains on a visible rhythm (the Warden's fight, the Canal Surge event) |
| **Electrified rails / wires** | Canals, Rooftops, Hidden | Cycle on and off with a visible arc and an audible hum |
| **Flame zones** | Plaza (gas stalls), anywhere (Fire Archers) | Burn for 4 seconds, marked with embers |
| **Steam vents** | Rooftops, Canals | Cycle with a hiss |

**Rules**

- Every lethal hazard has a **warning of at least 0.8 seconds** before becoming lethal, with visual and audio cues.
- Hazards use a **hazard mark** (black-and-white ink hatching plus a hum) that is separate from telegraph and boss accent colors.
- **Enemies die to hazards too.** The Kusarigama, the Gravitational Beam and shield throws can use them.
- Hazards never cover a route entirely. Every hazardous route has a safe alternative.

### 9.2 Destruction

Destruction is **broad** (a deliberate choice), so the environment is part of every fight. To protect route mastery, cover, AI navigation and performance, destructibles are in tiers.

| Tier | What it covers | Rule |
|---|---|---|
| **S: Structural** | Zone-defining geometry, floors of key platforms, route spines, ladders, zipline anchors, boss arena floors, **at least two cover pieces per combat space** | **Indestructible** (except by scripted boss events) |
| **A: Major destructibles** | Market stalls, glass signs, vending machines, thin walls and partitions, water tanks, most crates and cover, railings, ziplines (cuttable) | Break under sword, shots, explosions and impacts. Damage **persists for the rest of the run** |
| **B: Debris and decor** | Lanterns, bottles, neon tubes, tables, paper screens | Break for ink splash only, with no gameplay effect |

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

| Event | Effect | Rules |
|---|---|---|
| **Rain Shower** | A downpour on top of the baseline drizzle. Slippery rooftops: dashes and landings slide a little further. Rain streaks add ink texture | Telegraph visibility unchanged. Affects the Rooftops and Shrine Heights |
| **Blackout** | Neon lights go dark in a zone. The background dims and enemy silhouettes are lit by their own accents | **Telegraph contrast is increased,** never reduced. Affects one zone at a time |
| **Canal Surge** | The Canals' water rises for about 20 seconds, covering the lower channels | Lethal only in flagged lower channels, with the usual 0.8 s warning |

- Events happen roughly **every 6-8 waves,** starting at wave 7.
- Events **never occur on boss waves or elite events,** and never overlap each other.
- The wave director picks events without repeating until all have been seen.
- Events carry **no score bonus.** They exist to change how the map plays, not to reward surviving them, so the accessibility toggle that turns them off costs players nothing on the leaderboards.
- Events can be turned off with an **accessibility toggle.**

---

## 10. Secrets

Twelve secrets. All rewards are **non-power** (score, cosmetics, lore). All 12 are **always available** every run. Score rewards can be earned again each run. Cosmetics and lore are one-time.

| # | Secret | Zone | How to find | Reward |
|---|---|---|---|---|
| 1 | **Koi Pond Wall** | Plaza | A cracked wall behind the pond. Hit it with the sword | 300 score + lore fragment 1 |
| 2 | **Manhole Maze** | Plaza | The manhole near the pond. Opens the Hidden Network | Access, and 200 score on first entry each run |
| 3 | **Vending Machine Cache** | Plaza | Slash the vending machine three times (a coin sound) | An ammo cache + 200 score |
| 4 | **Flooded Shrine** | Canals | Time the drain gate and slip through before it closes | Sword trail: *Rainwater* |
| 5 | **Pipe Whisper** | Canals | Follow a faint whisper from the pipes to a hidden alcove | Lore fragment 2 |
| 6 | **Neon Kanji** | Rooftops | Shoot out the sign's characters in the order shown by a flicker | Sword trail: *Neon Brush* |
| 7 | **Broken Sign Roost** | Rooftops | A hard climb along a collapsed sign | Outfit piece: *Rooftop Scarf* |
| 8 | **Roof Vent Drop** | Rooftops | Open the vent. A risky drop to the Hidden Network | 300 score + a shortcut |
| 9 | **Bell of the Shrine** | Shrine | Shoot the bell during a breather | 500 score + a chime, and the breather lasts 3 s longer |
| 10 | **Lantern Path** | Shrine | Follow the unlit lanterns, lighting each with a gun shot | Cigarette ink style: *Lantern Fire* |
| 11 | **Server Room** | Hidden | A locked room opened with a code found in the lore fragments | Lore fragments 3 to 6 (four terminals) |
| 12 | **The Old Dojo** | Hidden | Needs all fragments. Opens a sealed room | Codex entry on Ishikawa, and the **Dojo Memory** flashback |

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

| Boss | Arena | Notes |
|---|---|---|
| **Katsuro** | The Shrine Heights plateau | Flat, open and walled by the skyline. No ziplines cross it. Cover is only the torii pillars and lanterns (Tier S pillars) |
| **The Debt Collector** | The Neon Plaza central square | Food stalls and low walls give cover and sightlines. Stalls (Tier A) can be destroyed by his shots |
| **The Hunter** | Rooftops, Canals and the Hidden Network | No fixed arena. A roaming range, bounded by these zones. He avoids Shrine Heights |
| **The Crimson Kite** | Rooftops and Shrine Heights | Open sky. Perches on signage and lanterns |
| **The Demolisher** | The central crane in the Rooftops (The Span) | Platforms and ziplines Z-B and Z-C are destroyed during the fight. Damage persists for the run |
| **The Floodgate Warden** | The floodgate chamber in the Canals | A large chamber with walkways at three heights and a central channel |

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

| Zone | Hue |
|---|---|
| Neon Plaza | Neon pink |
| Rooftop Signage | Electric blue |
| Shrine Heights | Jade green |
| Underpass Canals | Jade-teal |
| Hidden Network | Dim white (with the nearest zone's hue at the entrance) |

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

| Budget | PC | Switch 2 | Original Switch |
|---|---|---|---|
| Active zones | Current zone plus neighbors | Same | Same, with tighter streaming |
| Active enemies | About 40, simple AI beyond 20 | About 40, simple AI beyond 20 | About 30, simple AI beyond 15 |
| Live destructibles | About 120 | About 100 | About 70 |
| Debris pieces | About 150 | About 120 | About 80 |
| Destruction state | Compact bitfield per destructible | Same | Same |
| Navigation updates | Incremental, queued | Same | Same, with a lower per-frame limit |
| Output | Scaled from 640 x 360 | 1080p handheld, up to 4K docked | 720p handheld, 1080p docked |
| Frame rate | Uncapped option, with caps | Stable 60 FPS | Stable 60 FPS |

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
- [ ] Accent hue collisions with boss colors: the approach is decided (§14.1), art review confirms specific cases.
- [ ] Event frequency (tune in playtests).
- [ ] Exact map scale and crossing times (graybox).
- [ ] Heat values and effects (tune).
- [ ] Budgets for destructibles and debris (Switch tests).
