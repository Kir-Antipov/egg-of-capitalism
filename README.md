![Logo](media/logo.png)

# Egg Of Capitalism (Fabric)
[![GitHub tag](https://img.shields.io/github/v/tag/Kir-Antipov/egg-of-capitalism.svg?cacheSeconds=3600&sort=date)](https://github.com/Kir-Antipov/egg-of-capitalism/releases/latest)
[![GitHub build status](https://img.shields.io/github/workflow/status/Kir-Antipov/egg-of-capitalism/build-artifacts/1.18.x/dev?cacheSeconds=3600)](https://github.com/Kir-Antipov/egg-of-capitalism/actions/workflows/build-artifacts.yml)
[![Modrinth](https://img.shields.io/badge/dynamic/json?color=5da545&label=Modrinth&query=title&url=https://api.modrinth.com/api/v1/mod/egg-of-capitalism&style=flat&cacheSeconds=3600&logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxMSAxMSIgd2lkdGg9IjE0LjY2NyIgaGVpZ2h0PSIxNC42NjciICB4bWxuczp2PSJodHRwczovL3ZlY3RhLmlvL25hbm8iPjxkZWZzPjxjbGlwUGF0aCBpZD0iQSI+PHBhdGggZD0iTTAgMGgxMXYxMUgweiIvPjwvY2xpcFBhdGg+PC9kZWZzPjxnIGNsaXAtcGF0aD0idXJsKCNBKSI+PHBhdGggZD0iTTEuMzA5IDcuODU3YTQuNjQgNC42NCAwIDAgMS0uNDYxLTEuMDYzSDBDLjU5MSA5LjIwNiAyLjc5NiAxMSA1LjQyMiAxMWMxLjk4MSAwIDMuNzIyLTEuMDIgNC43MTEtMi41NTZoMGwtLjc1LS4zNDVjLS44NTQgMS4yNjEtMi4zMSAyLjA5Mi0zLjk2MSAyLjA5MmE0Ljc4IDQuNzggMCAwIDEtMy4wMDUtMS4wNTVsMS44MDktMS40NzQuOTg0Ljg0NyAxLjkwNS0xLjAwM0w4LjE3NCA1LjgybC0uMzg0LS43ODYtMS4xMTYuNjM1LS41MTYuNjk0LS42MjYuMjM2LS44NzMtLjM4N2gwbC0uMjEzLS45MS4zNTUtLjU2Ljc4Ny0uMzcuODQ1LS45NTktLjcwMi0uNTEtMS44NzQuNzEzLTEuMzYyIDEuNjUxLjY0NSAxLjA5OC0xLjgzMSAxLjQ5MnptOS42MTQtMS40NEE1LjQ0IDUuNDQgMCAwIDAgMTEgNS41QzExIDIuNDY0IDguNTAxIDAgNS40MjIgMCAyLjc5NiAwIC41OTEgMS43OTQgMCA0LjIwNmguODQ4QzEuNDE5IDIuMjQ1IDMuMjUyLjgwOSA1LjQyMi44MDljMi42MjYgMCA0Ljc1OCAyLjEwMiA0Ljc1OCA0LjY5MSAwIC4xOS0uMDEyLjM3Ni0uMDM0LjU2bC43NzcuMzU3aDB6IiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIGZpbGw9IiM1ZGE0MjYiLz48L2c+PC9zdmc+)](https://modrinth.com/mod/egg-of-capitalism)
[![CurseForge](https://img.shields.io/badge/dynamic/json?color=%23f16436&label=CurseForge&query=title&url=https%3A%2F%2Fapi.cfwidget.com%2F490007)](https://www.curseforge.com/minecraft/mc-mods/egg-of-capitalism)
[![GitHub license](https://img.shields.io/github/license/Kir-Antipov/egg-of-capitalism.svg?cacheSeconds=36000)](https://github.com/Kir-Antipov/egg-of-capitalism#readme)

When you defeat the dragon in singleplayer, you get a unique trophy in the face of its egg. You cannot get a second one like that, but the one that's already in your inventory is yours and nobody else's. But everything changes as soon as you join a multiplayer game:

![Illustration](media/our.png)

Down with this communist nonsense! Welcome to the age of capitalism *(yep, there's where the name came from)*!

One of the simplest solutions to the problem would be to remove the "egg limit", so that after each re-summoned dragon is killed, a new egg would appear. Unlimited dragon eggs, yeah. But this way, the egg ceases to be unique and valuable.

This mod approaches the issue from a different side: each person who **kills their first dragon** will **receive their own egg**. So, the trophy is unique for each player, but not for the entire server.

----

## Installation

Requirements:
 - Minecraft `1.18.x`
 - Fabric Loader `>=0.12.0`

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