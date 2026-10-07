# AGENTS.md: skillsjars-example-spring-ai

Example Spring AI application that uses SkillsJars both at runtime (a Skill on the classpath for a custom agent) and for coding agents (`com.skillsjars:maven-plugin`).

Follow the `zen-of-projects` Skill; this file records only project-specific facts and exceptions.

## MCP

`javadocs` (https://www.javadocs.dev/mcp), configured in `.mcp.json` / `.kiro/settings/mcp.json` and approved in `.claude/settings.json`. Use its `get_latest_version` for version lookups and its source/doc tools for API questions. In Claude Code its tools are deferred: load them with ToolSearch (search `javadocs`).

## Build & test

- Full validation: `./mvnw -B -ntp clean verify` (the same in CI).

## Maintenance routine

`.factory/MAINTENANCE.md` (weekly), following the `zen-of-projects` Skill.

## Exceptions to zen-of-projects

- **No `com.jamesward:skills` dependency:** it is an example whose Skills are part of what it demonstrates; maintainer Skills would muddy it. `.factory/MAINTENANCE.md` reads the published Skill from a clone of https://github.com/jamesward/skills instead. Don't add the dependency.
