# 🚀 Modularization Sample - Advanced Android Application

A comprehensive Android application demonstrating modern modularization patterns with **MVI + Clean Architecture**, featuring advanced media handling, Room database integration, and sophisticated permission management.

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Module Structure](#module-structure)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [Contributing](#contributing)

## 🎯 Overview

This project showcases a production-ready Android application built with modern development practices, featuring:

- **Complete Modularization**: Each feature is independently developed and testable
- **Clean Architecture**: Clear separation of concerns with Data, Domain, and Presentation layers
- **MVI Pattern**: Model-View-Intent architecture for predictable state management
- **Advanced Media Handling**: MediaStore integration with zoomable images and ExoPlayer videos
- **Room Database**: Local storage for favorites with proper data management
- **Permission Management**: Comprehensive permission handling for Android 6-16
- **Modern UI**: Material Design 3 with ViewBinding
- **Type-Safe Navigation**: Jetpack Navigation with compile-time safety

## 🏗️ Architecture

### Clean Architecture Layers

```
┌─────────────────────────────────────────────────────────────────┐
│                    Presentation Layer                           │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────┐ ┌────────────┐ │
│  │   Images    │ │   Videos    │ │ Favourites  │ │ Settings   │ │
│  │   Feature   │ │   Feature   │ │   Feature   │ │   Feature  │ │
│  └─────────────┘ └─────────────┘ └─────────────┘ └────────────┘ │
└─────────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────────┐
│                      Domain Layer                               │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────┐ ┌────────────┐ │
│  │   Images    │ │   Videos    │ │ Favourites  │ │ Settings   │ │
│  │   Domain    │ │   Domain    │ │   Domain    │ │   Domain   │ │
│  └─────────────┘ └─────────────┘ └─────────────┘ └────────────┘ │
└─────────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────────┐
│                       Data Layer                                │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────┐ ┌────────────┐ │
│  │   Images    │ │   Videos    │ │ Favourites  │ │ Settings   │ │
│  │    Data     │ │    Data     │ │    Data     │ │    Data    │ │
│  └─────────────┘ └─────────────┘ └─────────────┘ └────────────┘ │
└─────────────────────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────────────────────┐
│                      Core Modules                               │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────┐ ┌────────────┐ │
│  │    Core     │ │   Database  │ │ MediaStore  │ │ Navigation │ │
│  │   (Common)  │ │   (Room)    │ │   (Media)   │ │ (Abstract) │ │
│  └─────────────┘ └─────────────┘ └─────────────┘ └────────────┘ │
│  ┌─────────────┐                                                │
│  │   Theme     │                                                │
│  │ (Styling)   │                                                │
│  └─────────────┘                                                │
└─────────────────────────────────────────────────────────────────┘
```

### MVI Pattern Implementation

The application follows the **Model-View-Intent (MVI)** pattern for predictable state management:

- **Intent**: User actions and system events
- **Model**: Immutable state representing the UI
- **View**: Reactive UI that renders the current state

```
User Action → Intent → ViewModel → State → UI Update
```

## 📦 Module Structure

```
ModularizationSample/
├── :core/                     # Common utilities and base classes
├── :core:database/            # Room database setup and entities
├── :core:mediastore/         # MediaStore API wrapper and permissions
├── :core:navigation/          # Navigation abstraction and interfaces
├── :core:theme/               # Theme and styling resources
│
├── :data:images/              # Images data layer
├── :domain:images/            # Images domain layer  
├── :feature:images/           # Images presentation layer
│
├── :data:videos/              # Videos data layer
├── :domain:videos/            # Videos domain layer
├── :feature:videos/           # Videos presentation layer
│
├── :data:favourites/          # Favourites data layer (Room)
├── :domain:favourites/        # Favourites domain layer
├── :feature:favourites/       # Favourites presentation layer
│
├── :data:settings/            # Settings data layer
├── :domain:settings/          # Settings domain layer
├── :feature:settings/         # Settings presentation layer
│
├── :data:language/            # Language data layer
├── :domain:language/          # Language domain layer
├── :feature:language/         # Language presentation layer
│
├── :data:onboarding/          # Onboarding data layer
├── :domain:onboarding/        # Onboarding domain layer
├── :feature:onboarding/       # Onboarding presentation layer
│
├── :feature:entrance/         # Entrance screen
├── :feature:dashboard/        # Dashboard with 4 bottom navigation items
├── :feature:media-detail/     # Media detail screen (Images/Videos)
│
└── :app/                      # Main app module
```

## ✨ Features

### 🎯 Core Features

- **Entrance Screen**: App entry point with splash/loading
- **Language Selection**: Multi-language support with persistence
- **Onboarding Flow**: User onboarding experience
- **Dashboard**: Main screen with 4 bottom navigation tabs
- **Media Gallery**: Images and Videos from MediaStore
- **Favourites**: Local storage with Room database
- **Settings**: App configuration and preferences

### 🖼️ Media Features

- **Image Gallery**: 
  - MediaStore integration
  - Favourite toggle functionality
  - Zoomable image viewer with PhotoView
  - Memory-optimized loading
  
- **Video Gallery**:
  - MediaStore integration
  - Favourite toggle functionality
  - ExoPlayer with custom controls
  - Advanced video playback features

- **Media Detail Screen**:
  - Zoomable image viewer for images
  - ExoPlayer for video playback
  - Favourite toggle
  - Media information display

### ⭐ Favourites System

- **Room Database**: Local storage for favourites
- **Toggle Functionality**: Add/remove favourites from any media
- **Favourites Screen**: Dedicated screen showing all favourites
- **Real-time Updates**: Live updates across all screens
- **Data Persistence**: Favourites persist across app restarts

### 🔐 Permission Management

- **Android 6-16 Support**: Comprehensive permission handling
- **Limited Access Warning**: Android 14+ limited access detection
- **Settings Navigation**: Direct navigation to app settings
- **Permission Banner**: Visual feedback for permission status
- **Graceful Degradation**: App functionality with limited permissions

### 🎨 UI/UX Features

- **Material Design 3**: Modern design system
- **ViewBinding**: Type-safe view binding
- **Type-Safe Navigation**: Compile-time navigation safety
- **Responsive Design**: Adaptive layouts for different screen sizes
- **Dark/Light Theme**: Theme support
- **Smooth Animations**: Fluid user interactions

## 🛠️ Technology Stack

### Architecture Components
- **MVI Pattern**: Model-View-Intent architecture for predictable state management
- **Clean Architecture**: Separation of concerns
- **Repository Pattern**: Data abstraction layer
- **Use Cases**: Business logic encapsulation

### Core Libraries
- **Kotlin**: Modern programming language
- **ViewBinding**: Type-safe view binding
- **Jetpack Navigation**: Type-safe navigation
- **Room Database**: Local data persistence
- **Koin**: Dependency injection framework
- **Coroutines & Flow**: Asynchronous programming

### Media Libraries
- **MediaStore API**: System media access
- **ExoPlayer**: Advanced video playback
- **PhotoView**: Zoomable image viewer
- **Glide**: Image loading and caching

### Permission & Security
- **Accompanist Permissions**: Permission handling
- **Security Best Practices**: Secure data handling
- **Memory Management**: Optimized resource usage

## 📱 Supported Android Versions

- **Minimum SDK**: Android 6.0 (API 23)
- **Target SDK**: Android 16 (API 35)
- **Compile SDK**: Android 16 (API 35)
- **Tested Devices**: Various screen sizes and Android versions

## 🔧 Development Environment

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17 or later
- Android SDK 35
- Kotlin 1.9.0 or later
- Gradle 8.0 or later

### Setup Instructions
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the application

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📞 Support

For support and questions, please open an issue in the repository.

---

**Note**: This project demonstrates advanced Android development practices and serves as a reference implementation for modularized applications with Clean Architecture and MVI pattern.
