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

| Item | Target |
|---|---|
| Base resolution | **640 x 360,** scaled by whole numbers (720p, 1080p, 4K) |
| Akane's height | About **48 px** |
| Standard enemies | 40-56 px tall (Tank about 72 px) |
| Bosses | Varied by boss (see §4), from human-scale to about 4x |
| Production method | **Hand-drawn pixel sprites** with brush-stroke shading, lit by a dynamic lighting system (see §9) |
| Frame rate | 60 FPS gameplay. Sprite animation at 12-24 frames per second of art, with smooth timing |

### 1.2 The look

A highly unique blend of **Japanese ink wash (sumi-e)** and **modern pixel animation,** inspired in smoothness and impact by *Dead Cells*, set on a **rainy neon night in 2121.**

- **Sumi-e principles to use:** bold brush-stroke outlines on threats, tonal washes (bokashi gradients) for shading, deliberate negative space (*ma*), and one accent color at a time.
- **Pixel principles to use:** clean pixel clusters, no anti-aliased smears on sprites, strong silhouettes, high frame counts on key actions, and clear anticipation and follow-through.
- **How they meet:** sprites are drawn in pixel clusters that imitate brush stroke edges and wash tones. Backgrounds use dithered washes. Effects (kills, deflects, dashes) are brush-stroke animations.
- **Mood:** wet, dark and neon-lit. Rain and reflections add ink texture.

### 1.3 Color rules

- **Base palette:** shared across the whole game: ink black, a range of cool grays, and paper white.
- **Zone accent hue (set dressing only):** one per zone, low to medium saturation, static, never animated like a threat.

| Zone | Accent |
|---|---|
| Neon Plaza | Neon pink |
| Rooftop Signage | Electric blue |
| Shrine Heights | Jade green |
| Underpass Canals | Jade-teal |
| Hidden Network | Dim white |

- **Regular-enemy threat language:** a **white-hot** (a bright white flare, line or ring with a black ink outline and no hue). This is the only look for regular-enemy telegraphs and projectiles. It is emissive and unlit, and never used on set dressing or gore. **Red belongs to gore and the logo,** never to telegraphs.
- **Boss accents** (high saturation, used only on that boss's attacks, trails and core):

| Boss | Accent |
|---|---|
| Katsuro | Hot pink |
| The Debt Collector | Coin gold |
| The Hunter | Pale violet |
| The Crimson Kite | Lime (its frame is red and white, but its attacks and core are lime) |
| The Demolisher | Hazard orange |
| The Floodgate Warden | Deep teal |

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

| Role | Shape language |
|---|---|
| **Anchor** | Wide, planted stance. A visible front (shield, plating, weapon) |
| **Pincer** | Lean, forward-leaning, asymmetrical. Built to look fast |
| **Suppressor** | Tall, still and vertical, with an obvious long weapon or optic |
| **Support** | Carries a tall object (banner, staff, controller) above the head line |
| **Special** | A distinctive unusual shape tied to how it arrives (harness, cloak, coat) |

- **Telegraph pose:** each attack has a clear wind-up pose that matches its telegraph tier (Quick 0.35 s, Standard 0.55 s, Heavy 0.8 s, Long 1.0 s). The pose must be readable even without the flare.
- **Elites** keep the base silhouette and add one clear marker (a gold coat trim, an extra weapon, a glow), so players read "stronger version" instantly.
- **Modifiers** (Hasted, Armored, Volatile, Shrouded, Vengeful) each have a fixed overlay: speed streaks, an ink plate, a glowing core, a shimmer, and a hatched ink brand. None use red or white-hot.

### 3.2 Enemy briefs

| # | Enemy | Role | Height | Silhouette and key details |
|---|---|---|---|---|
| 1 | **Yakuza Guy** | Anchor / fodder | 44 px | Standard suit and sunglasses. Short blade or baton, raised overhead in the wind-up. The "baseline" body that others build on |
| 2 | **Shooter** | Suppressor | 50 px | Tall, still. Rifle arm and a glowing optic over one eye. White-hot laser line from the optic |
| 3 | **Skirmisher** | Pincer | 42 px | Lean, forward-leaning, light armor, twin short blades. Curved running stance and trailing coat |
| 4 | **Tank** | Anchor | 72 px | Wide, heavy plating, a visible rear plate with a seam (the weak point). Slow turning animation |
| 5 | **Lancer** | Anchor, reach | 56 px | Tall, narrow stance with a very long spear and a white sash. The spear defines the silhouette |
| 6 | **Archer** | Suppressor | 52 px | Hooded cloak and a long bow, usually on high ground. Cloak sways in the rain |
| 7 | **Shieldbearer** | Anchor | 58 px | Broad figure behind a large ink-black riot shield. The shield is the main shape |
| 8 | **Cyber Ninja** | Pincer | 50 px | Slim cybernetic body with a glowing seam. Long blade. A visible guard pose |
| 9 | **Bomber** | Suppressor | 46 px | Hunched, a bandolier of glowing charges. The charges are the identity |
| 10 | **Zipline Raider** | Special | 48 px | Hook-and-wire harness and a short blade. Appears with a visible wire |
| 11 | **Banner Caller** | Support | 52 px | Carries a tall banner with an ink-black emblem above the head line. The banner defines the shape |
| 12 | **Hexer** | Support | 52 px | White mask, a brush-like staff. Draws ink zones |
| 13 | **Drone Handler** | Support | 46 px | Wrist controller, three small drones orbiting. The drones are the identity |
| 14 | **Phantom** | Special | 50 px | Cloaked figure with a shimmer. Mostly negative space until it strikes |
| 15 | **Duelist** | Special | 54 px | Poised, in a long coat with a straight blade. A distinctive counter stance |
| 16 | **Sniper** | Suppressor | 52 px | Long rifle with a laser scope on a high perch. A steady, still pose |

### 3.3 Named elite markers

| Elite | Marker |
|---|---|
| Enforcer | Gold coat trim and a second weapon |
| Marksman | Twin optics |
| Siege Tank | Shoulder-mounted ram plates |
| Shadow Ninja | A second glowing seam and a trail |
| Naginata Master | A longer curved polearm |
| Riot Guard | A shield with crackling light |
| Fire Archer | An ember-lit bow |
| Demolition Bomber | A larger pack with a cluster of charges |

---

## 4. Bosses

Each boss is drawn at **high detail** with its own accent color (§1.3), a recognizable silhouette, and animations for **intro, idle, each move, phase break, and death.**

| Boss | Scale | Silhouette | Key visuals |
|---|---|---|---|
| **Katsuro** | Human-scale, about 52 px | Lean figure in a dark coat | Hot pink dash trail. Cybernetic upgrades grow with the tier (see below). A sword that drags a line of ink |
| **The Debt Collector** | Human-scale, about 56 px | Long coat, heavy shoulders | A floating ledger drone, a cybernetic arm cannon, gold coin-yellow shots |
| **The Hunter** | Human-scale, about 50 px | Nearly invisible: shimmering cloak lines | Pale violet flicker. Fully visible only at the moment of a strike or when revealed |
| **The Crimson Kite** | About 3x (aerial, wingspan about 150 px) | Winged drone-mech | Red-and-white frame, long rotor blades like brush strokes. Lime dive lines and core |
| **The Demolisher** | About 4x (a crane rig, about 200 px) | Exo-suit pilot in a crane cab with a wrecking ball | Hazard orange impact circles. The cab is a clear target |
| **The Floodgate Warden** | About 3x (about 150 px) | Bulky waterproof exo-rig with a pump cannon | Deep teal water gauge on his chest. The gauge shows the tide state |

**Katsuro by tier** (visual progression, matching the move tiers)

| Tier | Waves | Visual change |
|---|---|---|
| 1 | 10-29 | Base design |
| 2 | 30-59 | Adds a holstered pistol and a second cybernetic part |
| 3 | 60-99 | More visible cybernetics. A longer coat tear and a brighter trail |
| 4 | 100+ | A fully rebuilt look. The sword gains a glowing edge |
| 5-6 | 150+, 200+ | Additional glowing seams. Trails take on a second layer |

**Boss visual rules**

- Every attack has a pose, a flare in the boss's color, and a fixed telegraph time.
- The **vulnerability window** is shown by a clear visual state (an exposed core, a stagger pose, a glow), in the boss's color.
- **Phase pips** are shown in the boss's color on the HUD.
- **Intro:** the title card appears in brush lettering, with the boss's department tag in small type.
- **Death:** a freeze-frame ink slash, then the boss dissolves into an ink splash in its accent color.

---

## 5. Zones and environment

Each zone uses the shared ink base plus its accent (§1.3). Sub-areas are listed in the map design document.

| Zone | Mood | Key materials and props | Landmark |
|---|---|---|---|
| **Neon Plaza** | Busy, wet, lit | Food stalls, vending machines, awnings, neon signs, a koi pond | A giant rotating neon fish sign |
| **Rooftop Signage** | Windswept, electric | Water tanks, antennas, giant signs, a crane, bridges | A huge vertical kanji sign |
| **Shrine Heights** | Quiet, open, cold | Stone lanterns, a torii gate, a bell, a small shrine | A red torii gate against the skyline |
| **Underpass Canals** | Dim, echoing, wet | Pipes, tunnels, canals, floodgates, pump machinery | The floodgate wheel |
| **Hidden Network** | Cramped, humming | Vents, cables, shafts, server racks, a sealed dojo | A lit server room door |

**Rules**

- Walkable surfaces: hard ink edges. Climbable surfaces: a consistent grip-line mark. Destructible objects: a faint crack-line mark.
- Backgrounds use soft, low-contrast washes and **dithering** for depth, with 3-4 parallax layers on PC and 2-3 on Switch.
- **Weather:** baseline light rain; the Rain Shower event is a downpour; reflections of neon on wet surfaces.
- **Night clock:** the sky and lighting shift over the run, from dusk-blue to a pale pre-dawn glow that never grows.
- The **Old Dojo** is a distinct, warm-lit secret space: paper walls and wood, in contrast with the rest of the city.

---

## 6. Effects and UI visuals

| Effect | Brief |
|---|---|
| **Kill splash** | A bold, bright red gore burst with brush-edged flecks (bold on kills only). See §10 |
| **Hit / deflect** | A short brush-stroke spark. Deflect adds a white ring |
| **Ink Step** | A brush-stroke afterimage of Akane and a very short slow-motion beat |
| **Dash** | A thin ink streak. Boots change its length and shape |
| **Flow aura** | An ink aura around Akane. Tier I faint, tier II brighter, tier III bold with drips. A pulse warns before a tier drops |
| **Dragon Slash** | A bold ink streak along the dash path. Its color follows the equipped cigarette ink style |
| **Dragon Slayer** | A screen-wide ink wash that kills in a large radius. Also follows the cigarette ink style |
| **Telegraphs** | White-hot for regular enemies, and the boss's accent for bosses. Line, ring and flare shapes per telegraph tier |
| **Hazards** | Black-and-white hatching with an arc or steam animation |
| **Destruction** | Ink splash and short dust. Debris clears from the walking plane in 2 seconds |
| **Zipline / updraft** | A bright ink stroke for ziplines and upward ink streaks for updrafts |
| **Off-screen threat smears** | Ink smears at the screen edge, shaped by threat type (see the run and narrative document) |
| **HUD** | Minimal brush-drawn elements that hide when unused |
| **Story beat card** | An ink silhouette image with brush lettering |

**Cigarette ink styles (7)** change the color and texture of the two specials: Ink Black, Wildfire, Rain, Crimson, Lantern Fire, Ash and Moonlight.

---

## 7. Animation guidelines

Starting frame counts for the art team. Responsiveness beats animation completeness: anticipation frames can be canceled by deliberate player actions.

| Animation | Frames **[TBD]** | Notes |
|---|---|---|
| Idle | 8-10 | Subtle breathing and rain on the jacket |
| Run | 10 | A strong silhouette change per foot plant |
| Jump / fall / land | 4 / 4 / 3 | Short landing recovery only for long falls |
| Dash | 5-6 | Few frames, stretched and smeared |
| Sword attack (per katana) | 6-9 | Startup / active / recovery matching the katana tables |
| Gun fire (per gun) | 3-5 | Recoil visible, especially the Vicious S36 |
| Ink Step | 6 | Reads even in slow motion |
| Human shield grab / hold / throw | 6 / 4 loop / 6 | Grab must be readable |
| Zipline / climb / grapple | 4 loop / 6 loop / 5 | Responsive entry and exit |
| Death (Akane) | 8 | Short and clean, so restarts stay fast |

**Enemy and boss animation**

- Every enemy has: **idle, move, wind-up pose per attack, attack, recovery, hit and death.**
- Wind-up time matches the telegraph tier exactly.
- Recovery poses clearly show the **weak window.**
- Bosses add: intro, each move, phase break and death.

**Principles:** strong anticipation, short but visible follow-through, and smears or squash and stretch on fast moves (in the spirit of *Dead Cells*).

---

## 8. Asset inventory

Rough counts for scoping (animation sets, not individual frames).

| Group | Count | Notes |
|---|---|---|
| Akane core set | 1 | About 20 animations |
| Akane loadout overlays | 7 katanas, 6 guns, 6 mod-capable gadgets (visible parts), 4 boots | Layered on the core set |
| Outfits | 6 unlockable + default | Recolor and detail passes |
| Standard enemies | 16 | Each about 8-12 animations |
| Named elites | 8 | Base sprite plus markers and one extra animation each |
| Modifier overlays | 5 | Reused across enemies |
| Bosses | 6 | Katsuro has 6 tier looks. The environmental bosses are large multi-part sprites |
| Zone tilesets and backdrops | 5 | Grayscale albedo, hand-drawn normal maps, emissive masks and gradient maps. The night clock is a gradient-map set, not extra art |
| Destructible sets | About 15 object types | With broken states |
| Effects | About 25 distinct effects | See §6 |
| UI / HUD | About 30 elements | Brush-drawn |
| Story beat and codex images | 4 beat silhouettes, Dojo Memory (about 8 images) | Ink illustrations |

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

| Technique | How we use it |
|---|---|
| **Dynamic lighting with hand-drawn normal maps** | All environment art (tiles, props, backgrounds) is painted as grayscale albedo plus a **hand-drawn normal map** and an **emissive mask.** Lights: neon signs, lanterns, stall lamps, fluorescent strips, gunfire and explosion flashes, and Akane's Flow aura |
| **Gradient maps for color** | Grayscale parallax and tile art, colored by a **gradient map per zone.** The same gradient system drives the **night clock** (late night to pre-dawn) and the Blackout event, so lighting bands cost almost no extra art |
| **Layered parallax** | **4 background layers on PC, 3 on Switch,** from distant skyline in fog to near silhouettes, plus **foreground mist and rain layers** that pass in front of the action at low opacity |
| **Fake volumetrics** | Neon haze cones, searchlight shafts, steam and mist banks: additive, noise-textured light shapes and fog sprites that react to lights. Not a true volumetric simulation |
| **Air density and particles** | Rain (several depths), mist, steam, embers, dust, paper scraps, neon glints and moths around lights. Density is a per-zone variable, and rises or falls with the weather and the night clock |
| **Wet surfaces** | Reflections of neon on wet ground: a mirrored, distorted strip with specular from the normal map. Puddles ripple when Akane or enemies land |
| **Combat feedback** | Hit-stop (short, consistent, tunable), ink-splash particles on kills, brief slow-down on Ink Step, and screen shake (all adjustable in accessibility options) |
| **Post-processing** | Subtle bloom on emissives, vignette, a slight paper-and-ink grain, and a brief chromatic shift on strong hits. All render at the **base resolution** to keep pixels crisp |

### 9.2 Ink-wash specific effects

These are what make the environment ours, not a *Dead Cells* copy:

- **Bokashi fog:** depth fog drawn as soft ink-wash gradients (graded washes), so far layers dissolve like a brush painting.
- **Ink in water:** ink drifts and blooms in puddles and canal water, and bleeds slowly when something lands in it.
- **Brush-stroke rain:** rain streaks drawn as thin brush strokes, in a few depths.
- **Ink dust on destruction:** broken objects release ink-colored dust and a short wash splash.
- **Paper and lantern light:** warm light through paper screens and lanterns in Shrine Heights and the Old Dojo, which contrasts with the cold neon elsewhere.
- **Negative space (*ma*):** some backgrounds deliberately leave empty washed areas, so the busy foreground reads clearly.

### 9.3 Zone lighting recipes

| Zone | Key lights | Volumetrics and air | Surfaces |
|---|---|---|---|
| **Neon Plaza** | Pink and warm neon signs, stall lamps, vending machine glow | Steam from stalls, haze under the fish sign, backlit rain | Wet ground reflecting neon, puddles |
| **Rooftop Signage** | Electric blue sign spill, red warning lights, sweeping searchlights | Wind-driven rain, cable sparks, shafts from signs | Wet metal and glass, sign reflections |
| **Shrine Heights** | Warm lanterns, a cold moon rim light, jade accents | Low mist, drifting paper scraps, soft rain | Wet stone, lantern glow on the torii |
| **Underpass Canals** | Teal lamp strips, flickering fluorescents | Dripping, steam from pipes, flood haze during Canal Surge | Water with ink bloom, wet concrete |
| **Hidden Network** | Dim fluorescent, server lights, a few warm bulbs | Dust motes, cable sparks | Metal grates, flickering panels |

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

| Budget | PC | Switch 2 | Original Switch |
|---|---|---|---|
| Dynamic lights per screen | About 12 | About 10 | About 6 |
| Baked or static lights | Most neon and signage | Most neon and signage | Most neon and signage |
| Parallax layers | 4 | 4 | 3 |
| Particles on screen | About 800 | About 600 | About 300 |
| Post-processing | Bloom, vignette, grain | Full-resolution bloom, vignette, grain | Half-resolution bloom, vignette |
| Reflections | Per-strip reflections on wet ground | Per-strip reflections on wet ground | Reduced to key surfaces |
| Output | Scaled from 640 x 360 | 1080p handheld, up to 4K docked | 720p handheld, 1080p docked |
| Frame rate | Uncapped option, with caps | Stable 60 FPS | Stable 60 FPS |

When frame time runs over budget on console, cosmetic load drops first (particles, then lights, then fog layers). Telegraphs, hazard marks and gameplay entities are never reduced. The art style also looks good at low settings: the ink wash look does not depend on heavy effects.

The **original Switch is the floor:** every effect needs a cheaper fallback that keeps gameplay readability. Switch 2 runs near PC-mid budgets, and the extra headroom should go to the heaviest cases (large waves with broad destruction).

### 9.6b PC settings presets

The game renders at **640 x 360** and scales up, so the GPU cost stays low even at 4K. The pressure points are the **CPU** (flanking AI, navigation updates, destruction, particles) and **memory** (hand-drawn normal maps roughly double the texture data). PC settings let low-end machines run the game.

| Preset | Lights | Particles | Parallax | Post-processing | Reflections |
|---|---|---|---|---|---|
| **High** | About 12 | About 800 | 4 | Full | Per-strip |
| **Medium** | About 8 | About 500 | 4 | Bloom and vignette | Per-strip, lower detail |
| **Low** | About 4 | About 300 | 3 | Half-resolution bloom | Key surfaces only |
| **Potato** | Baked lights only | About 100 | 2 | None | Off |

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
