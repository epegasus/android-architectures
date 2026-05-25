# Android Architectures

Reference implementations for **Clean Architecture**, **modularization**, and **SDK-style** Android project structure.

**Maintainer:** [Sohaib Ahmed](https://github.com/itssohaibahmed)

---

## Projects

| Project | Focus |
|---------|--------|
| [CleanArchitecture](CleanArchitecture) | Layered Clean Architecture with MVVM |
| [Modularization](Modularization) | Multi-module app boundaries and dependencies |
| [NextGenSdk](NextGenSdk) | SDK / library module patterns for Android |

---

## Principles demonstrated

- Separation of `presentation`, `domain`, and `data` layers
- Dependency inversion and testable use cases
- Feature vs. core module boundaries
- Gradle module graphs and API surface control

---

## Getting started

```bash
git clone https://github.com/epegasus/android-architectures.git
```

Open a project in Android Studio and inspect module `build.gradle` files and package structure.

---

## See also

- [AnimeHub](https://github.com/itssohaibahmed/AnimeHub) — production modular app
- [android-apps](https://github.com/epegasus/android-apps) — shipped-style applications
