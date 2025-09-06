# Proposal: Clean Architecture & Feature Modularization for Search (Android)

![Module Diagram](module-diagram.svg)
![Class Diagram](searcher_module_arch-class-diagram.svg)

## Overview

This repository presents a proposed solution leveraging Clean Architecture and feature-based modularization for implementing the search functionality in the PicPay app (Android, Kotlin).

- **Main goal:** Demonstrate how to structure an Android application's search feature using Clean Architecture principles, dividing responsibilities into distinct, maintainable modules.

## Architecture

- **Clean Architecture:** Layers separation (Presentation, Domain, Data) to promote testability, scalability, and independence of frameworks.
- **Feature Modularization:** Each feature (e.g., search) is encapsulated in its own module, facilitating independent development, testing, and scaling.

## Visual Guides

- `module-diagram.svg`: Illustrates the modular structure of the project.
- `searcher_module_arch-class-diagram.svg`: Shows the class relationships and dependencies within the search module.

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/jonathangsilveira/proposal-clean-arch-search-feature-modularization-android.git
   ```
2. **Open with Android Studio.**
3. **Build & Run:** The project is configured with Kotlin and Gradle.

## Project Structure

- `app/`: Main application module.
- Feature modules (see diagrams above) are organized for separation of concerns.

## Motivation

Applying Clean Architecture and modularization helps teams deliver robust, maintainable, and scalable Android apps. This proposal is tailored for feature search, but its principles are reusable across other features.

## Author

- [jonathangsilveira](https://github.com/jonathangsilveira)

---

> For more details, explore the diagrams above and review the module implementations.