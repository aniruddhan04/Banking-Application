# Banking Application

A JavaFX–based GUI application for creating and managing bank accounts, performing transactions, and viewing account information.

---

## Features

- **Account Management**  
  - Create new checking and savings accounts  
  - Display account details (account number, balance, type)

- **Transactions**  
  - Deposit and withdraw funds  
  - Transfer money between accounts  
  - Input validation and balance checks

- **Documentation & Diagrams**  
  - Use-case and class diagrams included  
  - Detailed design and testing report

---

## Prerequisites

- **Java SE Development Kit (JDK)** — Java SE 8 or newer installed  
- **NetBeans IDE** — required to run and develop the project  
- **JavaFX SDK** — required for running the GUI 

> **Note:** You need to download and install the above tools separately.  
>  
> For configuring JavaFX with NetBeans (Non-modular from IDE setup), follow the official instructions here:  
> [JavaFX and NetBeans — Non-modular from IDE](https://openjfx.io/openjfx-docs/index.html#netbeans-non-modular)  
>  
> This includes setting up JavaFX SDK libraries and VM options properly to run the application.

---

## Getting Started

1. **Clone the repository**  
   ```bash
   git clone https://github.com/aniruddhan04/Banking-Application.git
   cd Banking-Application
   ```

2. **Open in NetBeans**  
   - File → Open Project → select the `Banking-Application` folder  
   - Run the `Main` class in the `coe528.project` package

> The project must be run through the IDE to ensure proper JavaFX SDK configuration.

---

## Project Structure

```
Banking-Application/
├── nbproject/                # (optional) NetBeans project files
├── src/
│   └── coe528/
│       └── project/          # Java source code
│           ├── Main.java
│           ├── Account.java
│           ├── Bank.java
│           └── …
├── build.xml                 # Ant build script
├── manifest.mf               # JAR manifest
├── useCaseDiagram.pdf        # UML use‐case diagram
├── classDiagram.pdf          # UML class diagram
├── finalReport.pdf           # Design & testing report
└── README.md                 # Project overview
```

---

## UML Diagrams

- **useCaseDiagram.pdf** – User interactions and workflows  
- **classDiagram.pdf** – Class relationships and object model  

---

## Documentation

See **finalReport.pdf** for:

- Requirements and design decisions  
- Test cases and results  
- Potential enhancements

---

## License

Released under the MIT License.

---

## Author

Aniruddha Nandy
