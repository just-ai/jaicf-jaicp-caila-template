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

| Layer | Content | Enters when |
|---|---|---|
| **Bed** | Rain-like texture, low drone, sparse shakuhachi | Always |
| **Pulse** | Synth bass and soft drum pulse | From wave 1 |
| **Taiko** | Taiko drum patterns | Fights with 3+ active enemies |
| **Shamisen** | Rhythmic shamisen phrases | Flow tier I and above |
| **Strings / synth lead** | Driving strings or lead synth | Flow tier II and above |
| **Climax** | Full-band hits, cymbal swells, choir-like synth | Flow tier III, Dragon Slayer, or a large threat count |

**Control inputs:** wave band (late night to still night), Flow tier, number of active enemies, token pressure and whether Akane is in combat or traveling.

**Wave bands** (matching the night clock)

| Waves | Band | Character |
|---|---|---|
| 1-24 | Late night | Brighter, with neon synths |
| 25-49 | Midnight | Deeper, heavier taiko |
| 50-74 | Small hours | Sparse, tense, long reverb tails |
| 75-99 | Before dawn | A pale, hopeful high pad that never resolves |
| 100+ | Still night | The same material, never resolving, slowly layered |

**Transitions:** all layer changes are quantized to the bar (2 beats on fast changes), so music never stutters. The breather strips back to bed and pulse.

### 2.2 Zone motifs

A short motif (2-4 bars) plays over the score when the player enters a zone and repeats softly while they stay.

| Zone | Motif character |
|---|---|
| **Neon Plaza** | A bright, busy synth arpeggio with a market-like shamisen |
| **Rooftop Signage** | A windy, soaring lead with a fast hi-hat feel |
| **Shrine Heights** | Sparse shakuhachi and temple bell, a quieter pulse |
| **Underpass Canals** | Dripping percussion and a low, echoing drone |
| **Hidden Network** | A humming, glitchy texture with minimal rhythm |

### 2.3 Boss themes

**A unique theme per boss,** built from stems that **add a layer for each phase,** with a **stinger at each phase break.** Themes keep the same tempo family as the main score so transitions are smooth.

| Boss | Theme character | Instruments to feature |
|---|---|---|
| **Katsuro** | A relentless duel: driving, rhythmic, with pink-hot synth | Taiko, shamisen, distorted lead |
| **The Debt Collector** | Swaggering and mechanical, with a coin-like percussive pulse | Pizzicato strings, snare, brass stabs |
| **The Hunter** | Sparse and unsettling, with long silences and a whispering pulse | Shakuhachi, bowed textures, whispers |
| **The Crimson Kite** | Soaring and sharp, with fast diving figures | Strings, high synth, fast drums |
| **The Demolisher** | Heavy and industrial, with impact hits that sync with the swings | Anvil, low brass, taiko |
| **The Floodgate Warden** | Cyclical and watery, with a rhythm that follows the tide | Bells, marimba, low synth |

**Rules**

- **Katsuro's theme evolves by tier** (more layers and a harder edge) to match his visuals.
- The **vulnerability window** gets a subtle musical cue (a drop in the mix, then a swell) so players can feel it without looking.
- **Phase breaks** play a short stinger and strip the music back for the 1.5 s transition.
- **Boss death:** a silence of about 0.4 s (matching the freeze-frame), then the score tally sound.

### 2.4 Other music

| Cue | Description |
|---|---|
| **Main menu** | A calm bed with a shakuhachi melody and rain |
| **Armory / Codex** | A quieter, intimate version of the menu |
| **Dojo (tutorial)** | Very sparse: shakuhachi and a wood block, rain at a distance |
| **Practice range** | Neutral and light, with no intensity layers |
| **Story beats** | A short, quiet stinger per beat, with a darker one for wave 100 |
| **Dojo Memory** | A single shakuhachi line over rain, with a soft low drone |
| **Run summary** | A short, dry motif. Different for a personal best |
| **Boss Rush** | The six boss themes in order, with a short bridge between them |
| **Time Attack** | The normal adaptive score with a tighter, faster pulse |

---

## 3. Sound effects

### 3.1 Palette

- **Ink wash side (organic):** brush on paper, rice paper tearing, bamboo knocks, wet steel, cloth, rain on metal and rooftops.
- **Cyberpunk side (digital):** servos, synth hits, glitches, neon hum, electrical arcs.
- Every important sound mixes **one organic layer and one digital layer.**

### 3.2 Akane

| Category | Cues |
|---|---|
| **Movement** | Footsteps by surface (wet concrete, metal, wood, tile, grates), jump, land (short and long), climb, zipline ride (whine), updraft (whoosh), spring, dash (a short cloth-and-air whip, different per boots), slide |
| **Sword** | One swing sound per katana (7), hit, kill (a short "cut" plus a brush splash), deflect (a bright metallic ring), miss |
| **Gun** | One fire sound per gun (6), empty click, ammo gained (sword-kill tick), the Gravitational Beam's hum and pull |
| **Defense** | Ink Step success (a clean brush chime and a brief slow-motion sweep), Ink Step miss (dull), dash charge ready (soft tick) |
| **Human shield** | Grab, hold (strain), absorbed hit (thud), shield break, throw |
| **Flow** | Tier up (a rising shamisen strum), tier down warning (a falling note), tier lost |
| **Kill chain** | Kill sounds step up a **musical scale** with the Flow combo, in the **key of the current score,** and reset when the combo breaks. The ladder spans at most an octave and a half, then holds. Each kill is a cut or shot sound, a body sound and a splash |
| **Specials** | Dragon Slash (a fast ink streak), Dragon Slayer (a rising wash and a huge release), meter ready |
| **Loadout** | Gadget activate and ready cues (11 gadgets), mod toggle |
| **Death** | A short, clean cut sound (so restarts stay fast) |

### 3.3 Enemy telegraph sounds (each unique)

Each enemy has **a signature wind-up sound** that matches its telegraph tier and can be identified without looking.

| Enemy | Telegraph sound |
|---|---|
| Yakuza Guy | Cloth rustle and a blade drawn |
| Shooter | A rising servo whine as the laser locks, then a crack |
| Skirmisher | A quick light patter and a blade flick |
| Tank | A deep mechanical groan and a heavy pre-slam hiss |
| Lancer | A long steel scrape, then a sharp thrust |
| Archer | A bow creak and a whistling arrow arc |
| Shieldbearer | A shield scrape and a grunt-like bash charge |
| Cyber Ninja | A rising electric hum, then a dash snap |
| Bomber | A fuse hiss and a ticking charge |
| Zipline Raider | A whistling wire and a hook clink |
| Banner Caller | Cloth snapping in the wind and a low war-drum beat |
| Hexer | A low brush-on-paper scrape and a dull hum from the zone |
| Drone Handler | Beeps and high-pitched drone whines |
| Phantom | A whisper and a faint pulse (long tier) |
| Duelist | Slow breath, a sword drawn halfway and a parry ring |
| Sniper | A rising high tone as the laser locks |

**Deaths, hits and recoveries:** each has short matching sounds. Armor hits get a metallic ping.

### 3.4 Boss signature sounds

| Boss | Motif |
|---|---|
| Katsuro | A low drum hit marks each dash commit |
| The Debt Collector | A coin spin before each shot |
| The Hunter | A whisper from the direction of the strike |
| The Crimson Kite | A rising whine before each dive |
| The Demolisher | A klaxon and the creak of the chain |
| The Floodgate Warden | A bell tone that rises with the tide |

### 3.5 World and environment

| Category | Cues |
|---|---|
| **Rain** | A constant bed (light), a heavier version for the Rain Shower event, different on metal, tile and water |
| **Zone ambience** | Plaza: crowd, frying, neon hum. Rooftops: wind, traffic, buzzing signs. Shrine: wind, a distant bell. Canals: dripping, echo, pumps. Hidden: humming cables, static |
| **Hazards** | Electrified rails (arc crackle), steam vents (hiss), flood water (rising rumble), flames (crackle). All warn at least 0.8 s early |
| **Destruction** | Distinct break sounds by material (glass, wood, metal, paper). Debris settles quickly |
| **Events** | Siren for the announcement, the Blackout power-down, the Canal Surge rumble |
| **Secrets** | A subtle unique cue for each of the 12 secrets (a whisper, a chime, a hum) |
| **Traversal** | Zipline anchors cut, updraft launch, service lift |

### 3.6 UI

| Category | Cues |
|---|---|
| **Menus** | Ink-brush navigation, select and back sounds |
| **Wave** | Wave start (soft), wave clear, early clear bonus, pressure timer warning |
| **Story and codex** | Beat card in and out, codex unlock |
| **Unlocks** | Item unlocked, cosmetic unlocked, challenge progress |
| **Run summary** | Score tally, personal best |

---

## 4. Vocals

**Minimal non-verbal sounds only.** No spoken dialogue.

| Source | Sounds |
|---|---|
| **Akane** | Exertion on slash and dash, short grunts on hits and landings, a final short cry on death. Sparing, so they don't fatigue |
| **Standard enemies** | Short barks on spotting Akane, on attacks and on death. A few variations per enemy type |
| **Bosses** | Katsuro's breathing and short exertion. The Hunter has none (silence is his sound). Others have grunts matching their scale |
| **Katsuro's dying words** | The text appears on screen (see the copy deck), with a **vocal sting:** a breath, then a low exhale, with no words |
| **Crowds** | Plaza crowd murmur as ambience |

---

## 5. Gameplay audio cues (information)

These cues carry information and have **top priority in the mix.**

| Information | Cue |
|---|---|
| **A telegraph starting** | The enemy's signature wind-up sound (§3.3), panned to its direction |
| **A threat behind or off-screen** | Footsteps or a whisper from behind, matching the screen-edge smear |
| **Lock-on (Shooter, Sniper)** | A rising tone that locks at the start of the fire window |
| **Vulnerability window (boss)** | A musical drop, then a swell, plus a soft chime |
| **Phase break** | A short stinger and a strip-back |
| **Flow tier change** | A rising or falling shamisen note |
| **Hazard about to become lethal** | A rising hum for at least 0.8 s |
| **Event incoming** | A siren about 8 seconds ahead |
| **Ziplines / updrafts / launch** | Distinct whine, whoosh and spring sounds so they can be found by ear |
| **Secrets** | A faint cue when Akane is near |
| **Heat rising in a zone** | A subtle change in the zone's ambience |

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

| Group | Count | Notes |
|---|---|---|
| Main score stems | 6 layers x 5 wave bands | Shared tempo and key family |
| Zone motifs | 5 | Short loops |
| Boss themes | 6 | Each with 3 phase stems and stingers. Katsuro has tier variations |
| Menu and mode cues | About 10 | See §2.4 |
| Akane SFX | About 140 | Including 7 katanas, 6 guns, 4 boots and 11 gadgets |
| Enemy SFX | About 16 x 6 | A telegraph, an attack, a hit, a death, a bark and a recovery each |
| Boss SFX | About 6 x 15 | Per move, per phase |
| Environment and ambience | About 60 | Rain, zone beds, hazards, destruction, events, secrets |
| UI | About 40 | |
| Vocals | About 100 | Akane, enemies and bosses |
