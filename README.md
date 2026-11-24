# Cloaks Mod

Minecraft **1.21.1** · **NeoForge** · requires **Curios API** (9.x for 1.21.1) and **Extended Crafting** (7.0.x).
All recipe ingredients are vanilla.

Seven cloaks worn in the Curios **Back** slot. Each one increases **all damage you deal** and your **max health** by the same percentage. Only the cloak you wear counts (they do not stack).

| # | Cloak | Bonus | Name color | Table | Recipe (center = previous cloak) |
|---|---|---|---|---|---|
| 1 | Wanderer's | +5% | white | Basic 3x3 | 4 leather, 4 white wool, 1 string |
| 2 | Adventurer's | +10% | green | Advanced 5x5 | cloak 1, 16 gold ingots, 8 iron blocks |
| 3 | Warrior's | +25% | blue | Advanced 5x5 | cloak 2, 4 diamond blocks, 4 lapis blocks |
| 4 | Champion's | +30% | purple | Elite 7x7 | cloak 3, 8 emerald blocks, 4 diamond blocks |
| 5 | Conqueror's | +40% | gold | Elite 7x7 | cloak 4, 4 netherite blocks, 4 nether stars |
| 6 | Sovereign's | +60% | pink | Ultimate 9x9 | cloak 5, 8 netherite blocks, 8 nether stars, 8 enchanted golden apples |
| 7 | Mythic | +75% | sky blue | Ultimate 9x9 | cloak 6, 16 netherite blocks, 16 nether stars, 8 enchanted golden apples |

Approximate total ProjectE-style EMC of the chain: ~0.5k, 52k, 350k, 1.8M, 4.5M, 11M, 23M (default ProjectE values, only an estimate).

Max health bonus is a percentage of base health (20): +75% = +15 HP (7.5 hearts).

## Build
Push to GitHub: the workflow builds the jar (Actions > build > Artifacts). Locally: Gradle 8.10+, Java 21, `gradle build`.
If Gradle cannot find Curios, change `curios_version` in `gradle.properties`.

## Textures
Replace the 16x16 PNGs in `src/main/resources/assets/cloakmod/textures/item/` (keep the file names).

## Settings
`ModItems.java` (bonus and name color per cloak), recipes in `src/main/resources/data/cloakmod/recipe/`.
