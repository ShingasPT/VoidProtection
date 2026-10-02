# VoidProtection

**VoidProtection** is a lightweight Minecraft plugin that teleports players back to safety when they fall into the void.

Instead of letting players fall until they take lethal damage, the plugin monitors player movement and rescues anyone who drops below a configurable Y level, teleporting them to their current world's spawn.

It has no external dependencies and no database requirements.

## Features

- Automatic void rescue
- Configurable void height threshold per server
- Teleports players to their own world's spawn
- Configurable MiniMessage rescue message
- Ignores dead players
- Folia support

## How It Works

VoidProtection listens for player movement and checks whether the player has fallen below the configured safety threshold.

When the condition is met, the player is moved back to a safe location associated with their world, and the rescue is performed asynchronously via `teleportAsync` so the main thread is not blocked. Dead players are skipped.

This makes the plugin useful for survival, SMP, lobby, and custom-world servers where falling into the void should not result in a lost player or item recovery situation.

## Configuration

All settings live in `config.yml`:

| Setting | Default | Description |
|---|---|---|
| `min-height` | `-100` | Y level at (or below) which a player is teleported back to their world's spawn |
| `message` | `<prefix> <yellow>You have been teleported to safety.` | MiniMessage-formatted message sent after a successful rescue |

The message supports the `<prefix>` placeholder, which resolves to `[VoidProtection]`.

## Requirements

- Minecraft/Paper 26.2
- Java 25

The plugin declares Folia support.

## Dependencies

This plugin has **no external dependencies**.

`VoidProtection` is the only project in this set that depends on nothing beyond the Paper API. There is no PlaceholderAPI integration, no database layer, and no integration with GriefPrevention or LuckPerms.

| Scope | Dependency | Notes |
|---|---|---|
| Compile | `io.papermc.paper:paper-api` | Provided by the server; not shaded |
| Runtime (Minecraft) | PlaceholderAPI | Not used |
| Runtime (Minecraft) | GriefPrevention | Not used |
| Runtime (Minecraft) | LuckPerms | Not used; permissions resolve through Bukkit's built-in permission system |
| Runtime (library) | MySQL Connector/J, HikariCP, Gson | Not used; Gson is only reached transitively through `paper-api` and is not declared |

Because no library is declared as `implementation`, the produced JAR is a plain JAR with no bundled third-party classes. Paper supplies MiniMessage (`net.kyori.adventure`) at runtime.

## Architecture

```text
me.shingas.voidProtection
├── listeners
│   └── VoidProtectionListener
└── VoidProtection.java
```

The plugin intentionally keeps its architecture small: the main class handles plugin lifecycle while the listener contains the void protection logic.

- `VoidProtection.java` — plugin lifecycle; saves the default config and registers the listener
- `listeners/VoidProtectionListener.java` — handles `PlayerMoveEvent`, applies the height threshold, and performs the async teleport

The plugin is loaded `POSTWORLD` and registers a single listener.

## Building

```bash
./gradlew build
```

On Windows:

```bat
gradlew.bat build
```

The resulting plugin JAR will be generated in:

```text
build/libs/
```

A `runServer` Gradle task is also provided for testing against a real Paper server:

```bash
./gradlew runServer
```

## Installation

1. Place `VoidProtection.jar` into the server's `plugins` directory.
2. Start the server.
3. Adjust `min-height` and `message` in `config.yml` to suit your world.
4. Restart the server (or reload the plugin) to apply changes.

## License

This project is open source. Check the repository for the applicable license.
