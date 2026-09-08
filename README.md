# VoidProtection

**VoidProtection** is a lightweight Minecraft plugin that prevents players from dying or becoming permanently lost when they fall into the void.

When a player reaches the configured void threshold, the plugin safely teleports them back to their world's spawn.

## Features

- Detects players falling into the void
- Automatically teleports players to safety
- Uses the world's spawn as the recovery location
- Lightweight event-driven implementation
- Simple configuration
- Folia support

## How It Works

VoidProtection listens for player movement and checks whether the player has fallen below the configured safety threshold.

When the condition is met, the player is moved back to a safe location associated with their world.

This makes the plugin useful for survival, SMP, lobby, and custom-world servers where falling into the void should not result in a lost player or item recovery situation.

## Configuration

The plugin provides a `config.yml` for configuring its behaviour.

Adjust the configuration to match the worlds and void protection rules used by your server.

## Architecture

```text
me.shingas.voidProtection
├── listeners
│   └── VoidProtectionListener
└── VoidProtection.java
```

The plugin intentionally keeps its architecture small: the main class handles plugin lifecycle while the listener contains the void protection logic.

## Requirements

- Minecraft/Paper 26.2
- Java

The plugin declares Folia support and does not require additional plugins.

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

## Installation

1. Place `VoidProtection.jar` into the server's `plugins` directory.
2. Start the server.
3. Configure `config.yml` if required.
4. Restart the server after configuration changes.

## License

Check the repository for the applicable license.
