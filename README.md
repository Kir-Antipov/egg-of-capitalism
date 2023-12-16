![Logo](media/logo.png)

# Egg Of Capitalism

[![GitHub Build Status](https://img.shields.io/github/actions/workflow/status/Kir-Antipov/egg-of-capitalism/build-artifacts.yml?style=flat&logo=github&cacheSeconds=3600)](https://github.com/Kir-Antipov/egg-of-capitalism/actions/workflows/build-artifacts.yml)
[![Version](https://img.shields.io/github/v/release/Kir-Antipov/egg-of-capitalism?sort=date&style=flat&label=version&cacheSeconds=3600)](https://github.com/Kir-Antipov/egg-of-capitalism/releases/latest)
[![Modrinth](https://img.shields.io/badge/dynamic/json?color=00AF5C&label=Modrinth&query=title&url=https://api.modrinth.com/v2/project/egg-of-capitalism&style=flat&cacheSeconds=3600&logo=modrinth)](https://modrinth.com/mod/egg-of-capitalism)
[![CurseForge](https://img.shields.io/badge/dynamic/json?color=F16436&label=CurseForge&query=title&url=https://api.cfwidget.com/490007&cacheSeconds=3600&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/egg-of-capitalism)
[![License](https://img.shields.io/github/license/Kir-Antipov/egg-of-capitalism?style=flat&cacheSeconds=36000)](https://github.com/Kir-Antipov/egg-of-capitalism/blob/HEAD/LICENSE.md)

When you defeat the dragon in singleplayer, you acquire a unique trophy in the form of its egg. You cannot obtain a second one like it, but the one already in your inventory belongs to you and no one else. However, everything changes as soon as you join a multiplayer game:

![Our unique trophy](media/our.png)

Down with this communist nonsense! Welcome to the age of capitalism!

One of the simplest solutions to the problem would be to eliminate the "egg limit," allowing a new egg to appear after each re-summoned dragon is killed. Unlimited dragon eggs, yeah. But this way, the egg ceases to be unique and valuable.

This mod approaches the issue from a different angle: each person who **kills their first dragon** will **receive their own egg**. So, the trophy is unique to each player, not for the entire server.

----

## Installation

Requirements:
 - Minecraft `1.19.x`
 - Fabric Loader `>=0.14.0`

You can download the mod from:

 - [GitHub Releases](https://github.com/Kir-Antipov/egg-of-capitalism/releases/latest) <sup><sub>(recommended)</sub></sup>
 - [Modrinth](https://modrinth.com/mod/egg-of-capitalism)
 - [CurseForge](https://www.curseforge.com/minecraft/mc-mods/egg-of-capitalism)
 - [GitHub Actions](https://github.com/Kir-Antipov/egg-of-capitalism/actions/workflows/build-artifacts.yml) *(these builds may be unstable, but they represent the actual state of the development)*

## Building from sources

Requirements:
 - JDK `17`

### Linux/MacOS

```cmd
git clone https://github.com/Kir-Antipov/egg-of-capitalism.git
cd egg-of-capitalism

chmod +x ./gradlew
./gradlew build
cd build/libs
```
### Windows

```cmd
git clone https://github.com/Kir-Antipov/egg-of-capitalism.git
cd egg-of-capitalism

gradlew build
cd build/libs
```