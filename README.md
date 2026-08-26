🌐 [Português (BR)](README.pt_BR.md) | [Español](README.es.md)

# Soc Ops

Turn any in-person meetup into a fast, fun social bingo challenge.
Players find people who match the prompts and race to complete 5 in a row.

[🎮 Live Demo](https://copilot-dev-days.github.io/agent-lab-java/) • [📚 Workshop Guide](workshop/GUIDE.md)

---

## Why this project

- **Icebreaker-first**: 24 curated prompts for quick conversations.
- **Ready for workshops**: built to teach design-first and multi-agent Copilot workflows.
- **Simple stack**: Spring Boot + Thymeleaf + vanilla JavaScript + custom CSS utilities.

---

## Start in 60 seconds

### Prerequisites

- [Java 21 JDK](https://adoptium.net/) or higher
- Maven Wrapper included (`./mvnw`)

### Run locally

```bash
cd socops
./mvnw spring-boot:run
```

Open `http://localhost:8080`.

---

## Workshop path

| Part | What you'll do |
|------|-----------------|
| [**00**](workshop/00-overview.md) | Understand the challenge and checklist |
| [**01**](workshop/01-setup.md) | Set up context engineering |
| [**02**](workshop/02-design.md) | Redesign the frontend with a design-first approach |
| [**03**](workshop/03-quiz-master.md) | Create a custom quiz theme with an agent |
| [**04**](workshop/04-multi-agent.md) | Build features with multi-agent workflows |

> 📝 Full offline guides are available in [`workshop/`](workshop/).

---

## Build and test

```bash
cd socops
./mvnw clean package
./mvnw test
```

GitHub Pages deploys automatically on pushes to `main`.
