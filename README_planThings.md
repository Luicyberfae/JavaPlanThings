# PlanTings - A Java OOP Game Project

## Project Overview

**PlanTings** is a plant care simulation game converted from Python to Java. Players adopt and nurture a virtual plant through different seasons, learning about OOP principles and design patterns through practical implementation.

This project demonstrates all key Object-Oriented Programming concepts and implements the **Factory Method Design Pattern**.

---

## 📋 OOP Principles Demonstrated

### 1. **Encapsulation** ✓
Private fields with public getters/setters ensure data security and controlled access:
- `Player.java`: Private `name` and `characterName` with getters/setters
- `Plant.java`: Private fields (`health`, `waterLevel`, `sunlightLevel`, `growthStage`, `isAlive`) with controlled access methods
- Example:
  ```java
  private int health;
  
  public int getHealth() {
      return health;
  }
  
  public void setHealth(int health) {
      this.health = Math.min(100, Math.max(0, health));
  }
  ```

### 2. **Inheritance** ✓
All plant types extend the abstract `Plant` base class:
- `Potato extends Plant`
- `Marigold extends Plant`
- `Tomato extends Plant`
- `Cucumber extends Plant`
- `Sunflower extends Plant`

This creates a hierarchy where shared behavior (grow, persistence) is defined once and reused across all plant types.

### 3. **Polymorphism** ✓
Each plant subclass implements abstract methods differently:
```java
// Each plant has different water needs
@Override
public void water() {
    // Potatoes: 25 units
    // Marigolds: 15 units (drought-tolerant)
    // Tomatoes: 35 units (water-hungry)
}

// Each plant has different sunlight needs
@Override
public void bask() {
    // Potatoes: 15 units (indirect)
    // Sunflowers: 40 units (sun-lovers!)
}
```

The main game calls these methods polymorphically - the correct behavior is determined at runtime based on the plant type.

### 4. **Abstraction** ✓
- `Plant.java`: Abstract class with abstract methods that must be implemented by subclasses
- `Season.java`: Enum representing abstract season concepts
- `SeasonDetector.java`: Encapsulates complex season detection logic

```java
public abstract class Plant {
    public abstract void water();      // What does each plant need?
    public abstract void bask();       // How much sun?
    public abstract String harvestProduct();  // What does it produce?
}
```

---

## 🎯 Design Pattern: Factory Method

### What is it?
The Factory Method design pattern provides an interface for creating objects without specifying their exact classes. Instead of:
```java
// ❌ Bad: Direct instantiation
Plant plant = new Potato();
Plant plant = new Marigold();
```

We use:
```java
// ✅ Good: Factory method
Plant plant = PlantFactory.createPlant("1"); // Returns Potato
Plant plant = PlantFactory.createPlant("2"); // Returns Marigold
```

### Implementation
The `PlantFactory` class encapsulates all plant creation logic:

```java
public static Plant createPlant(String choice) {
    switch (choice.trim()) {
        case "1":
            return new Potato();
        case "2":
            return new Marigold();
        // ... etc
        default:
            return null;
    }
}
```

### Why We Chose It
✅ **Flexibility**: Adding new plant types only requires changes to the factory, not the game logic  
✅ **Maintainability**: All plant creation in one place  
✅ **Validation**: Can validate input before creating objects  
✅ **Scalability**: Easy to add new plants without breaking existing code  
✅ **Single Responsibility**: Game logic doesn't need to know HOW to create plants

### How It Improves the Program
1. **Loose Coupling**: Game code doesn't directly reference Potato, Marigold, etc.
2. **Open/Closed Principle**: Open for extension (add new plants) but closed for modification
3. **Centralized Control**: All plant instantiation logic in one place
4. **Easy to Test**: Can mock the factory for unit tests

---

## 📁 File Structure

```
PlanTings/
├── Season.java              # Enum: Spring, Summer, Autumn, Winter
├── SeasonDetector.java      # Detects current season from date
├── Player.java              # Encapsulation: Player data
├── Plant.java               # Abstract base class for all plants
├── Potato.java              # Concrete plant: Peruna
├── Marigold.java            # Concrete plant: Calendula
├── Tomato.java              # Concrete plant: Lycopersicum
├── Cucumber.java            # Concrete plant: Cucumis
├── Sunflower.java           # Concrete plant: Helianthus
├── PlantFactory.java        # DESIGN PATTERN: Factory Method
├── PlanTings.java           # Main game controller
└── README.md                # This file
```

---

## 🎮 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed
- All `.java` files in the same directory

### Compilation
```bash
javac *.java
```

### Run the Game
```bash
java PlanTings
```

### Gameplay
1. **Enter your name** - Your character name will be generated
2. **Choose a plant** - Pick from 5 different plant types (1-5)
3. **Care for your plant** - Water it, give it sunlight, monitor growth
4. **Watch it grow** - Track health, water level, and growth stages
5. **Harvest** - When fully grown, harvest your plant's product
6. **Save progress** - Game state is automatically saved

### Game Menu Options
- **1. Water** - Increase plant's water level (+5 points)
- **2. Sunlight** - Increase plant's sunlight exposure (+5 points)
- **3. Status** - Check detailed plant information
- **4. Harvest** - Try to collect the plant's product (+50 bonus points if ready)
- **5. Pass Time** - Let the plant grow naturally (+2 points)
- **6. Quit** - End the game

---

## 🌱 Plant Types & Characteristics

| Plant | Type | Water Need | Sunlight Need | Growth Time | Harvest |
|-------|------|-----------|---------------|------------|---------|
| **Potato** (Peruna) | Tuber | Medium (25) | Low (15) | 5 stages | Potatoes |
| **Marigold** (Calendula) | Flower | Low (15) | High (30) | 4 stages | Seeds |
| **Tomato** (Lycopersicum) | Fruit | High (35) | High (35) | 6 stages | Tomatoes |
| **Cucumber** (Cucumis) | Fruit | Medium (28) | Medium (28) | 5 stages | Cucumbers |
| **Sunflower** (Helianthus) | Flower | Medium (20) | Very High (40) | 6 stages | Seeds |

---

## 💾 Game State Persistence

The game automatically saves to `planTings_game_state.txt`:
- Player name and character name
- Plant type and current health
- Growth stage and points earned
- Current season
- Plant status (alive/dead)

---

## 🔄 Conversion from Python to Java

### Key Changes

| Python | Java |
|--------|------|
| `def` functions | `public static void` / `public void` methods |
| Dictionary `{}` | Factory pattern + class hierarchy |
| `Enum` | Java `enum` keyword |
| `print()` | `System.out.println()` |
| `input()` | `Scanner` class |
| `time.sleep()` | `Thread.sleep()` |
| `os.system('cls')` | `ProcessBuilder` |
| File I/O | `FileWriter`/`FileReader` |

### Improvements Over Python Version
✅ Type safety - Java's static typing prevents many runtime errors  
✅ OOP structure - More organized class hierarchy  
✅ Design patterns - Cleaner code through Factory Method  
✅ Performance - Compiled Java is faster than interpreted Python  
✅ Encapsulation - Better data protection with private/public modifiers

---

## 📚 Learning Outcomes

By studying this project, you'll understand:

1. **Abstract Classes** - Defining common interfaces for related objects
2. **Inheritance Hierarchies** - Creating parent-child class relationships
3. **Polymorphic Behavior** - Same method call, different implementations
4. **Encapsulation** - Protecting data with private fields and public accessors
5. **Factory Pattern** - Creating objects without exposing creation logic
6. **File I/O** - Saving and loading game state
7. **User Input Handling** - Getting and validating user choices
8. **Game Loops** - Continuous game state updates

---

## 🚀 Future Enhancements

Potential features to add:
- **Observer Pattern** for notifications when plant needs attention
- **Decorator Pattern** to add special abilities/modifiers to plants
- **Multiple save slots** for different game sessions
- **Difficulty levels** affecting plant care requirements
- **Seasonal impacts** on plant growth rates
- **Multiplayer** comparison of plants
- **GUI** using JavaFX or Swing instead of console

---

## 📖 References & Patterns

- **Factory Method Pattern**: Gang of Four Design Patterns
- **OOP Principles**: SOLID principles (Single Responsibility, Open/Closed, etc.)
- **Java Best Practices**: Naming conventions, code organization

---

## ✅ Project Requirements Met

- ✅ Small app idea (Plant care simulation game)
- ✅ Encapsulation (private fields + getters/setters)
- ✅ Inheritance (Plant superclass + 5 subclasses)
- ✅ Polymorphism (method overriding in each plant)
- ✅ Abstraction (abstract Plant class + Season enum)
- ✅ Design Pattern (Factory Method in PlantFactory)
- ✅ Code comments explaining each class and method
- ✅ README explaining the design pattern and benefits
- ✅ Source code ready for submission

---

## 📝 Author Notes

This project demonstrates that you understand:
- How to structure complex applications using OOP
- When and why to use design patterns
- How to convert between programming languages
- Best practices for readable, maintainable code

The Factory Method pattern is just one of many you'll encounter. This project uses it effectively to reduce coupling and improve code organization.

---

**Game Status**: ✅ Complete  
**Last Updated**: December 12, 2025  
**Ready for Submission**: Yes
