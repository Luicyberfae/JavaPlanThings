# PlanTings - Functional Programming Features

**Project Enhanced:** JavaPlanThings  
**Date:** December 2025  
**Focus:** Integration of Functional Programming with OOP and Factory Pattern

---

## Overview

This project demonstrates the seamless integration of **Functional Programming** concepts with existing **Object-Oriented Programming (OOP)** principles and the **Factory Design Pattern**. The original OOP structure has been maintained while adding modern Java functional programming features.

---

## Functional Programming Features Added

### 1. **Lambdas (λ Expressions)**
Lambda expressions provide concise, functional-style code for performing operations.

#### Examples in the Code:

**PlantFactory.java:**
```java
// Method references (lambda shorthand) for plant creation
put("1", Potato::new);
put("2", Marigold::new);
```

**PlanTings.java:**
```java
// BiConsumer lambdas for game actions
put("1", (plant, pts) -> {
    plant.water();
    points += pts;
});
```

**Plant.java:**
```java
// Lambda for resource decay
Arrays.asList(
    (Runnable) () -> waterLevel = Math.max(0, waterLevel - 5),
    (Runnable) () -> sunlightLevel = Math.max(0, sunlightLevel - 3)
).forEach(Runnable::run);
```

---

### 2. **Streams API**
Java Streams provide a functional approach to processing collections with operations like `filter`, `map`, and `forEach`.

#### Examples:

**PlantFactory.java:**
```java
// Filter and map plant names
return PLANT_NAMES.entrySet().stream()
        .filter(entry -> !entry.getKey().equals(normalizedChoice))
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());

// Create all plants using stream
return PLANT_SUPPLIERS.values().stream()
        .map(Supplier::get)
        .collect(Collectors.toList());
```

**PlanTings.java:**
```java
// Display other plants using stream
PlantFactory.createAllPlants().stream()
        .map(Plant::getName)
        .filter(name -> !name.equals(currentPlant.getName()))
        .forEach(name -> System.out.println("  • " + name));
```

---

### 3. **Predicates**
Predicates are functional interfaces that test conditions and return boolean values.

#### Examples:

**PlantFactory.java:**
```java
// Validates plant choice
private static final Predicate<String> IS_VALID_CHOICE = 
    choice -> PLANT_SUPPLIERS.containsKey(choice.trim());
```

**PlanTings.java:**
```java
// Health check predicates
private static final Predicate<Plant> IS_HEALTHY = 
    plant -> plant.getHealth() > 70;

private static final Predicate<Plant> NEEDS_WATER = 
    plant -> plant.getWaterLevel() < 30;

private static final Predicate<Plant> IS_READY_TO_HARVEST = 
    plant -> plant.getGrowthStage() >= 5;
```

**Plant.java:**
```java
// Plant condition predicates
public static final Predicate<Plant> IS_THRIVING = 
    plant -> plant.health > 80 && plant.waterLevel > 50 && plant.sunlightLevel > 50;

public static final Predicate<Plant> NEEDS_IMMEDIATE_CARE = 
    plant -> plant.health < 30 || plant.waterLevel < 20 || plant.sunlightLevel < 20;
```

**Usage:**
```java
if (IS_HEALTHY.test(currentPlant)) {
    // Plant is healthy
}
```

---

### 4. **Optional**
Optional provides null-safe operations and helps avoid NullPointerExceptions.

#### Examples:

**PlantFactory.java:**
```java
// Safe plant creation
public static Optional<Plant> createPlantSafe(String choice) {
    return Optional.ofNullable(choice)
            .map(String::trim)
            .filter(IS_VALID_CHOICE)
            .map(PLANT_SUPPLIERS::get)
            .map(Supplier::get);
}

// Safe plant name retrieval
return Optional.ofNullable(choice)
        .map(String::trim)
        .map(PLANT_NAMES::get)
        .orElse("Unknown Plant");
```

**Plant.java:**
```java
// Safe growth operation
Optional.of(this)
        .filter(plant -> plant.isAlive)
        .ifPresentOrElse(
            plant -> performGrowth(),
            () -> System.out.println("Your " + name + " is no longer alive.")
        );
```

**PlanTings.java:**
```java
// Safe game over check
Optional.of(currentPlant)
        .filter(plant -> !plant.isAlive())
        .ifPresent(plant -> System.out.println("\nGame Over! Your plant has withered.\n"));
```

---

### 5. **Method References**
Method references are shorthand syntax for lambda expressions that call a specific method.

#### Examples:

```java
// Constructor reference
Potato::new  // equivalent to () -> new Potato()

// Static method reference
String::trim  // equivalent to s -> s.trim()

// Instance method reference
Map.Entry::getValue  // equivalent to entry -> entry.getValue()

// Method reference with forEach
.forEach(Runnable::run)  // equivalent to .forEach(r -> r.run())
```

---

### 6. **Functional Interfaces**

#### Supplier<T>
Supplies values without taking input.

```java
private static final Map<String, Supplier<Plant>> PLANT_SUPPLIERS = new HashMap<>() {{
    put("1", Potato::new);
    put("2", Marigold::new);
}};
```

#### Consumer<T>
Accepts input and performs an operation.

```java
private static final Consumer<Plant> UPDATE_HEALTH_STATUS = plant -> {
    int healthBoost = (plant.waterLevel / 2) + (plant.sunlightLevel / 3);
    plant.health = Math.min(100, plant.health + healthBoost / 10);
};

// Usage
UPDATE_HEALTH_STATUS.accept(this);
```

#### BiConsumer<T, U>
Accepts two inputs and performs an operation.

```java
private static final Map<String, BiConsumer<Plant, Integer>> GAME_ACTIONS = new HashMap<>() {{
    put("1", (plant, pts) -> {
        plant.water();
        points += pts;
    });
}};

// Usage
GAME_ACTIONS.get(choice).accept(currentPlant, actionPoints);
```

#### Predicate<T>
Tests a condition and returns boolean.

```java
Predicate<Plant> IS_HEALTHY = plant -> plant.getHealth() > 70;
boolean healthy = IS_HEALTHY.test(myPlant);
```

---

## How OOP and Functional Programming Work Together

### 1. **Factory Pattern with Lambdas**
The Factory pattern now uses `Supplier<Plant>` lambdas and method references:

```java
// Traditional approach (removed):
switch (choice) {
    case "1": return new Potato();
    case "2": return new Marigold();
}

// Functional approach (current):
private static final Map<String, Supplier<Plant>> PLANT_SUPPLIERS = new HashMap<>() {{
    put("1", Potato::new);
    put("2", Marigold::new);
}};

return PLANT_SUPPLIERS.get(choice).get();
```

### 2. **Polymorphism with Streams**
Polymorphic methods work seamlessly with stream operations:

```java
PlantFactory.createAllPlants().stream()
        .map(Plant::getName)  // Calls getName() on each polymorphic Plant
        .forEach(System.out::println);
```

### 3. **Encapsulation with Predicates**
Private fields are accessed through getters in lambda expressions:

```java
private static final Predicate<Plant> IS_HEALTHY = 
    plant -> plant.getHealth() > 70;  // Uses getter method
```

---

## Benefits of This Approach

### ✓ **Maintainability**
- Cleaner, more readable code
- Less boilerplate code
- Easier to add new features

### ✓ **Type Safety**
- Optional prevents NullPointerExceptions
- Predicates provide compile-time type checking

### ✓ **Reusability**
- Predicates can be reused across different contexts
- Functional interfaces can be composed

### ✓ **Testability**
- Predicates and functions are easy to unit test
- Stream operations are declarative and predictable

### ✓ **Performance**
- Lazy evaluation with streams
- Potential for parallel processing

---

## Functional Programming Concepts Demonstrated

| Concept | Location | Example |
|---------|----------|---------|
| **Lambda Expressions** | PlantFactory, PlanTings, Plant | `(plant, pts) -> { ... }` |
| **Method References** | PlantFactory, PlanTings | `Potato::new`, `Plant::getName` |
| **Stream API** | PlantFactory, PlanTings | `.stream().filter().map()` |
| **Predicates** | All classes | `plant -> plant.health > 70` |
| **Optional** | PlantFactory, Plant, PlanTings | `Optional.ofNullable(choice)` |
| **Functional Interfaces** | PlantFactory, Plant | `Supplier`, `Consumer`, `BiConsumer` |
| **Higher-Order Functions** | PlanTings | Functions that accept/return functions |
| **Immutability** | PlantFactory | `final` collections |

---

## Code Examples: Before and After

### Example 1: Plant Selection

**Before (Imperative):**
```java
String[] otherPlants = {"Potato", "Marigold", "Tomato", "Cucumber", "Sunflower"};
for (String plant : otherPlants) {
    if (!plant.equals(currentPlant.getName())) {
        System.out.println("  • " + plant);
    }
}
```

**After (Functional):**
```java
PlantFactory.createAllPlants().stream()
        .map(Plant::getName)
        .filter(name -> !name.equals(currentPlant.getName()))
        .forEach(name -> System.out.println("  • " + name));
```

### Example 2: Action Handling

**Before (Imperative):**
```java
switch (choice) {
    case "1":
        currentPlant.water();
        points += 5;
        break;
    case "2":
        currentPlant.bask();
        points += 5;
        break;
}
```

**After (Functional):**
```java
if (GAME_ACTIONS.containsKey(choice)) {
    int actionPoints = choice.equals("5") ? 2 : 5;
    GAME_ACTIONS.get(choice).accept(currentPlant, actionPoints);
}
```

### Example 3: Validation

**Before (Imperative):**
```java
Plant plant = null;
if (choice != null && choice.trim().length() > 0) {
    String normalized = choice.trim();
    if (PLANT_SUPPLIERS.containsKey(normalized)) {
        plant = PLANT_SUPPLIERS.get(normalized).get();
    }
}
return plant;
```

**After (Functional):**
```java
return Optional.ofNullable(choice)
        .map(String::trim)
        .filter(IS_VALID_CHOICE)
        .map(PLANT_SUPPLIERS::get)
        .map(Supplier::get)
        .orElse(null);
```

---

## Running the Game

### Compile:
```bash
javac *.java
```

### Run:
```bash
java PlanTings
```

---

## Key Takeaways

1. **OOP and Functional Programming are complementary**, not competing paradigms
2. **Factory Pattern** becomes more elegant with lambda expressions
3. **Streams** provide declarative data processing
4. **Predicates** enable reusable, testable condition logic
5. **Optional** prevents null-related bugs
6. **Functional interfaces** reduce boilerplate code

---

## Architecture Summary

```
┌─────────────────────────────────────────────────────┐
│                   PlanTings (Main)                  │
│  • Predicates for health checks                     │
│  • BiConsumer for game actions                      │
│  • Stream operations for display                    │
│  • Optional for safe operations                     │
└─────────────────────────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────┐
│              PlantFactory (Factory Pattern)         │
│  • Supplier<Plant> for plant creation              │
│  • Predicate for validation                         │
│  • Stream API for filtering                         │
│  • Optional for null-safe creation                  │
└─────────────────────────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────┐
│           Plant (Abstract Base Class)               │
│  • Predicates for plant conditions                  │
│  • Consumer for state updates                       │
│  • Optional for safe growth operations              │
│  • Stream-based diagnostics                         │
└─────────────────────────────────────────────────────┘
                          │
        ┌─────────────────┴─────────────────┐
        ▼                 ▼                  ▼
    ┌──────┐         ┌──────┐          ┌──────┐
    │Potato│         │Tomato│    ...   │Flower│
    └──────┘         └──────┘          └──────┘
    (Polymorphism - Each implements abstract methods)
```

---

## Conclusion

This project successfully integrates functional programming concepts into an existing OOP codebase while maintaining the Factory design pattern. The result is cleaner, more maintainable, and more expressive code that leverages the best of both programming paradigms.

**Key Achievement:** Demonstrated that OOP design patterns and functional programming are not mutually exclusive but can work together to create better software.
