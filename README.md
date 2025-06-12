# Banking Application

**A simple Java-based banking system**  
Application for creating and managing bank accounts, performing transactions, and viewing account information.

---

## Features

- **Account Management**  
  - Create new checking and savings accounts  
  - Display account details (account number, balance, type)

- **Transactions**  
  - Deposit and withdraw funds  
  - Transfer money between accounts  
  - Input validation and balance checks

- **Build & Run**  
  - Ant-based build script (`build.xml`)  
  - Optional NetBeans project metadata for IDE integration  

- **Documentation & Diagrams**  
  - Use-case and class diagrams included  
  - Detailed design and testing report

---

## Prerequisites

- Java SE 8 (or newer)  
- Apache Ant 1.x  
- (Optional) NetBeans IDE  

---

## Getting Started

1. **Clone the repository**  
   ```bash
   git clone https://github.com/aniruddhan04/Banking-Application.git
   cd Banking-Application
   ```

2. **Build and run with Ant**  
   ```bash
   ant compile
   ant run
   ```

3. **Or open in NetBeans**  
   - File → Open Project → select the `Banking-Application` folder  
   - Run the `Main` class in the `coe528.project` package

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

## Author

Aniruddha Nandy

---

## License

This project is released under the MIT License.
