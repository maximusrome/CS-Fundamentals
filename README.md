# CS Fundamentals (CS2500)

A comprehensive collection of coursework from Northeastern University's CS2500: Fundamentals of Computer Science. This repository demonstrates progressive learning in Kotlin, covering functional programming, data structures, algorithms, and software design principles.

## Course Overview

This repository contains homework assignments, projects, and exercises that explore fundamental computer science concepts using Kotlin scripting (`.main.kts` files) and the Khoury teaching library (`khoury.jar`).

## Repository Structure

### Homework Assignments

#### HW1: Functions & Basic Operations
- Perfect square calculations
- Function design and testing
- String formatting and output

#### HW2: Predicates & Boolean Logic
- String manipulation (case-insensitive matching)
- Boolean predicates
- Test-driven design using `@EnabledTest`

#### HW3: File I/O & List Processing
- Reading and processing data from files (`names.txt`, `records.txt`)
- List constructors and transformations
- Data aggregation and formatting

#### HW4: Loops & Control Flow
- Imperative for-loops
- Data classes (`Student`)
- Palindrome detection algorithms
- ASCII art generation with nested loops

#### HW5: Loop Selection & User Input
- Do-while loops
- Console input handling
- Affirmation programs with user interaction

#### HW6: Mutation & In-Place Operations
- Mutable list operations
- Generic function design (`myInplaceMap`)
- In-place transformations

#### HW7: Algorithms & Abstractions
- TopK algorithm implementation
- Evaluation functions and sorting
- Levenshtein distance (natural language processing)
- Recursive algorithm design

### Projects

#### Project 1: Flash Card Study Application
A complete interactive flashcard study system featuring:
- **Data Design**: `FlashCard` and `Deck` data classes
- **File Operations**: Reading/writing cards in pipe-delimited format (`front|back`)
- **Dynamic Content**: Programmatically generated cards (perfect squares)
- **Interactive UI**: `reactConsole`-based menu system
- **Study Mode**: Self-paced studying with correctness tracking
- **Key Features**:
  - Multiple deck selection
  - Progress tracking
  - File-based card storage
  - Test-driven development with comprehensive test coverage

#### Project 2: Enhanced Flash Card Application
An advanced version introducing:
- **Tagged Cards**: `TaggedFlashCard` with arbitrary string tags
- **Interface Design**: Generic `IDeck` interface for deck abstraction
- **Advanced Features**:
  - Tag-based filtering (e.g., "hard", "science")
  - Card cycling (incorrect cards moved to end of deck)
  - Multiple study sessions in one run
  - Natural language processing for answer validation
  - Enum-based state management (`DeckState`)
- **Object-Oriented Design**: Implementing stateless classes with interfaces
- **Machine Learning Integration**: NLP for self-report correctness

#### Project Ethics Component
Critical analysis of ACM Code of Ethics principles applied to software development, demonstrating awareness of professional responsibilities in computing.

## Technologies & Concepts

### Programming Language
- **Kotlin** (scripting with `.main.kts`)

### Key Concepts Covered
- Functional programming paradigms
- Data classes and immutability
- Higher-order functions and lambdas
- List abstractions (map, filter, fold)
- Recursion and iteration
- File I/O operations
- Interactive console applications (`reactConsole`)
- Test-driven development (`@EnabledTest`, `testSame`)
- Mutation and mutable data structures
- Object-oriented design (interfaces, classes)
- Algorithm implementation (Levenshtein distance, TopK)
- Natural language processing basics

### Testing Framework
All code follows rigorous testing practices using the Khoury library's testing utilities:
- `@EnabledTest` annotations
- `testSame` assertions
- `captureResults` for interactive testing
- Comprehensive test coverage for all functions

## Running the Code

Prerequisites:
- Kotlin runtime
- `khoury.jar` (included in repository)

To run any homework or project file:
```bash
kotlin -cp khoury.jar <filename>.main.kts
```

## Code Quality

This repository demonstrates:
- **Clean Code**: Descriptive function names and clear documentation
- **Comments**: Every function includes purpose documentation
- **Testing**: Extensive test coverage for all functionality
- **Style Guide Compliance**: Consistent with course requirements
- **Design Patterns**: Task-driven and type-driven decomposition

## Learning Outcomes

Through this coursework, I developed proficiency in:
1. Writing clean, testable, and maintainable code
2. Applying functional programming principles
3. Designing interactive console applications
4. Implementing classic algorithms
5. Reading and processing file data
6. Test-driven development practices
7. Object-oriented design principles
8. Understanding ethical responsibilities in computing

## File Formats

- **Flash Card Files**: Pipe-delimited format (`front|back` or `front|back|tag1,tag2`)
- **Data Files**: CSV and plain text formats
- **Examples Included**: `example.txt`, `example_tagged.txt`

## Author

**Alexander Rome (Max)**  
GitHub: [@maximusrome](https://github.com/maximusrome)

---

*This repository represents work in foundational computer science, demonstrating growth from basic function design to complex application development.*

