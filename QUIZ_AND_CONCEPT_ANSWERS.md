# STEP SEM-3 · CodInClub | Powered by BridgeLabz
## Week 9 Category B Practice: Quiz & Concept Solutions

---

### Part 1: Quiz Answers

#### Question 1
A ticketing system manages various event types like concerts, plays, and sports matches. While all events have a title and a scheduled date, concerts also have a performer, and sports matches have competing teams. The system processes tickets for any event type through a common TicketProcessor.
Which OOP concept allows the TicketProcessor to interact with different event types uniformly?
- A Encapsulation
- B Inheritance
- **C Polymorphism** *(Correct Answer)*
- D Aggregation

**Explanation**: Polymorphism enables an interface or base class reference (such as `Event`) to be used to trigger behavior across different specialized subtypes (`Concert`, `Play`, `SportsMatch`) uniformly without needing custom conditional branches for each type.

---

#### Question 2
In a social media application, a UserProfile object has a dateOfBirth attribute. The application prevents direct modification of dateOfBirth from outside the UserProfile class, requiring any changes to be made through an updateProfileInfo method that includes validation logic to ensure the date is valid and reasonable.
Which OOP principle is being primarily enforced here?
- A Inheritance
- B Abstraction
- **C Encapsulation** *(Correct Answer)*
- D Composition

**Explanation**: Encapsulation hides the internal representation/state of an object behind private variables and restricts direct modification, requiring changes to pass through controlled methods with validation.

---

#### Question 3
A publishing house manages authors and their books. An author can write one or more books, but a book must always have exactly one author. If the author is removed from the system, their books still exist but might be reassigned to another author or marked as unauthored.
Which UML multiplicity best describes the relationship from Author to Book?
- A Author 1 --- 1 Book
- B Author 0..* --- 1 Book
- **C Author 1 --- 1..* Book** *(Correct Answer)*
- D Author 1..* --- 0..* Book

**Explanation**: The problem explicitly states: "An author can write one or more books (`1..*`), but a book must always have exactly one author (`1`)". Thus, the relationship multiplicity is `Author 1 --- 1..* Book`.

---

#### Question 4
A Shopping Cart object directly manages a collection of Cart Item objects. If the Shopping Cart is deleted, all its associated Cart Item objects are also destroyed, as they have no meaning or independent existence outside of that specific cart.
Which UML relationship accurately models this strong ownership and lifecycle dependency?
- A Association
- B Aggregation
- C Dependency
- **D Composition** *(Correct Answer)*

**Explanation**: Composition represents a strong "whole-part" relationship where the parts have a lifecycle dependency on the whole. When the container (`ShoppingCart`) is deleted, its constituent parts (`CartItem`) cease to exist.

---

#### Question 5
An online course platform defines a general Course concept. Specific types of courses, such as ProgrammingCourse and DesignCourse, extend this general Course by adding specialized attributes and behaviors while inheriting common ones. The specialized courses are essentially 'is-a' types of the general course.
Which UML relationship represents the connection between the general Course and ProgrammingCourse?
- A Realization
- B Dependency
- C Aggregation
- **D Generalization** *(Correct Answer)*

**Explanation**: Generalization is the UML term for inheritance (an "is-a" relationship) where child classes inherit state and behavior from a generalized superclass.

---

#### Question 6
A Printer class needs to interact with various Printable document types (e.g., PdfDocument, WordDocument). The Printer is designed to work with any object that adheres to a Printable contract, which defines a print() method, without needing to know the specific implementation details of each document type.
Which UML relationship describes the connection between PdfDocument and the Printable contract?
- A Generalization
- B Composition
- **C Realization** *(Correct Answer)*
- D Association

**Explanation**: Realization in UML describes the relationship between a class and an interface, indicating that the class implements or "realizes" the contract defined by the interface.

---

#### Question 7
A Customer places an Order. The Customer can exist without placing any orders, and an Order can be created and completed even if the Customer record is later archived (though it would still reference the customer ID). There is no strong ownership or lifecycle dependency between them; they are just related by a transaction.
Which UML relationship is most suitable for describing this scenario?
- A Composition
- **B Association** *(Correct Answer)*
- C Generalization
- D Dependency

**Explanation**: Association represents a structural relationship connecting instances of classes that collaborate or reference each other without strong lifecycle coupling or whole-part ownership.

---

#### Question 8
A task in a project management system has various statuses: Pending, InProgress, Completed, Blocked. A task can move from Pending to InProgress, from InProgress to Completed or Blocked, but a Completed task cannot return to Pending or InProgress. This behavior defines a specific flow of states and transitions.
Which UML diagram type is best suited to model the lifecycle and valid transitions of a Task object?
- A Class diagram
- B Sequence diagram
- **C State diagram** *(Correct Answer)*
- D Component diagram

**Explanation**: A UML State Machine / State diagram is specifically designed to model discrete states, lifecycle stages, and permitted transitions in response to events or guards.

---

#### Question 9
A reporting module generates various types of reports (e.g., Sales Summary, Inventory List, Employee Roster). The module interacts with a generic ReportGenerator interface, which defines a generate() method. This allows the module to request a report without needing to know the specific details of how each report type is created.
Which design concept is primarily demonstrated by using a generic ReportGenerator interface?
- A Encapsulation
- **B Abstraction** *(Correct Answer)*
- C Composition
- D Object identity

**Explanation**: Abstraction focuses on exposing essential behavior while hiding implementation complexity and specifics behind a clean interface contract (`ReportGenerator`).

---

#### Question 10
When a user logs into an application, the following sequence of actions occurs: the user enters credentials, the AuthenticationService receives these credentials, it then calls the UserRepository to verify them, the UserRepository returns the result, and finally, the AuthenticationService grants or denies access. This interaction involves multiple objects exchanging messages over time.
Which UML behavioral model is most appropriate for visualizing this specific order and flow of messages between objects during the login process?
- A Class diagram
- B Activity diagram
- **C Sequence diagram** *(Correct Answer)*
- D Use case diagram

**Explanation**: A UML Sequence diagram visualizes dynamic interactions between objects organized chronologically along lifelines, displaying messages exchanged step by step.

---

### Part 2: Concept Questions & Comprehensive Answers

#### Concept Question 1: Encapsulation in User Object Design
**Prompt**: A user registration system requires users to provide an email address. The User object has an email attribute. To ensure data integrity, the system prevents direct modification of the email attribute. Instead, any email changes must go through a `changeEmail` method that includes validation to check for a valid format and uniqueness before updating the email. Explain how encapsulation is applied in this User object design. Describe what state should be controlled by the User object, why unrestricted access to the email could lead to invalid states, and how controlled access through methods helps maintain valid object state.

**Answer**:
1. **Application of Encapsulation**: Encapsulation is the fundamental object-oriented design principle of bundling state (data fields) and behavior (methods) together into a single cohesive unit while restricting direct external access to internal state using private access modifiers (`private String email;`).
2. **State Controlled by the User Object**: The `User` object manages its internal identity attributes, specifically `email`, alongside verification flags, modification timestamps, or associated profile data. The integrity invariant is that the email must be syntactically valid (containing valid user, `@`, and domain components) and systemically unique.
3. **Risks of Unrestricted Access**: If external callers could directly mutate `user.email = "..."`, several invalid states could occur:
   - Malformed strings could be assigned (e.g., null, empty strings, strings without `@`).
   - Duplicate emails could overwrite identity boundaries, violating uniqueness across the user repository.
   - Downstream components (such as authentication tokens or password-reset handlers) would be desynchronized because no change events or security validations would be triggered.
4. **Maintaining Valid State via Controlled Access**: By routing all modifications through a guarded `changeEmail(String newEmail)` method, the system ensures that:
   - Input format validation is enforced before assignment.
   - System-wide uniqueness checks are performed.
   - Side effects (such as emitting an email confirmation token or logging an audit trail) can be executed atomically.
   - If validation fails, an exception is thrown or an error code is returned, preserving the object's prior valid state intact.

---

#### Concept Question 2: Inheritance vs. Composition in ReportingService
**Prompt**: Consider a ReportingService that can generate various types of reports. Some reports might require DataExport capabilities (e.g., export to CSV), while others might require DataVisualization capabilities (e.g., generate charts). Not every report needs all capabilities. The ReportingService itself has a core set of functionalities. Explain when inheritance might be considered and when composition would be more suitable for incorporating these optional DataExport and DataVisualization capabilities into the reporting system. Discuss how forcing all capabilities into a single inheritance hierarchy for all report types could lead to design rigidities and make the system harder to extend with new report features.

**Answer**:
1. **When Inheritance Might Be Considered**:
   - Inheritance is suitable when there is a true, stable "is-a" relationship and a clear hierarchical taxonomy where subclasses share the same core lifecycle and behavior of the parent (e.g., `FinancialReport extends BaseReport`).
   - However, using inheritance solely to inherit optional, cross-cutting utility features is an anti-pattern.
2. **Why Composition is More Suitable for Optional Capabilities**:
   - Optional capabilities like `DataExport` (CSV, PDF, JSON) and `DataVisualization` (bar charts, pie charts) represent orthogonal behaviors ("has-a" or "can-do" capabilities).
   - Through composition, a `Report` can hold references to pluggable strategy interfaces: `ExportStrategy` and `VisualizationStrategy`. A report can dynamically configure zero, one, or multiple strategies without altering its class definition.
3. **Rigidities of a Single Inheritance Hierarchy**:
   - **Combinatorial Explosion (Class Proliferation)**: If implemented via inheritance, you would need classes like `CsvReport`, `ChartReport`, `CsvAndChartReport`, `PdfAndChartReport`, leading to 2^N subclasses as capabilities grow.
   - **Multiple Inheritance Limits**: In languages like Java that only support single class inheritance, a class cannot inherit from both `DataExporter` and `DataVisualizer`.
   - **Tight Coupling & Violation of SRP/OCP**: Modifying an export algorithm would risk breaking unrelated report classes inheriting from the same base class. Adding a new export target would require modifying the base hierarchy. Composition adheres to the Open-Closed Principle (OCP) and Single Responsibility Principle (SRP).

---

#### Concept Question 3: Abstraction, Method Overriding, and Polymorphism in NotificationService
**Prompt**: An application has a NotificationService responsible for sending messages to users. It supports sending notifications via EmailNotification, SMSNotification, and PushNotification. The NotificationService should be able to trigger a notification without explicit conditional logic for each specific notification type. Explain how abstraction (e.g., an interface or abstract class), method overriding, and runtime polymorphism work together to achieve this flexible notification system. Describe what happens when a new notification implementation (e.g., InAppNotification) is introduced, and how the existing NotificationService remains largely unaffected.

**Answer**:
1. **How the Concepts Work Together**:
   - **Abstraction**: An interface `NotificationChannel` defines a clear contract: `void send(String recipient, String message);`. It abstracts away the complex communication protocols (SMTP, SMS gateways, APNS/FCM).
   - **Method Overriding**: Concrete subclasses (`EmailNotification`, `SMSNotification`, `PushNotification`) implement the interface and override `send(...)` with their specific transport logic.
   - **Runtime Polymorphism**: `NotificationService` interacts purely with `NotificationChannel` references. When `service.notifyUser(user, message)` executes, it calls `channel.send(...)`. The JVM dynamically resolves and invokes the appropriate subclass implementation at runtime based on the actual object instance.
2. **Introducing a New Implementation (`InAppNotification`)**:
   - A developer creates `InAppNotification implements NotificationChannel` and overrides `send(...)` to write to the user's database notification table.
   - **Zero Impact on `NotificationService`**: The `NotificationService` contains no `switch` or `if-else` type checks. It operates exclusively against the `NotificationChannel` abstraction. Thus, adding `InAppNotification` requires zero code changes to `NotificationService`, satisfying the Open-Closed Principle (open for extension, closed for modification).

---

#### Concept Question 4: Composition vs. Aggregation (Organization, Department, Employee)
**Prompt**: An Organization is composed of several Departments. Each Department has a collection of Employees. When an Organization ceases to exist, its Departments are also dissolved. However, if a Department is dissolved, its Employees might still exist and could be reassigned to other departments or become independent. Similarly, Employees can move between Departments throughout their career. Explain the differences between composition and aggregation based on this scenario. Justify which UML relationship is more appropriate for Organization and Department, and which is more appropriate for Department and Employee, based on their respective ownership and lifecycle dependencies.

**Answer**:
1. **Differences Between Composition and Aggregation**:
   - **Composition (Strong Ownership, Shared Lifecycle)**: Represented by a filled diamond in UML. The part cannot exist independently of the whole. When the container/whole is destroyed, the parts are destroyed with it.
   - **Aggregation (Weak Ownership, Independent Lifecycle)**: Represented by an open/hollow diamond in UML. The part can exist independently of the whole. The container merely aggregates references to the parts.
2. **Organization and Department -> Composition**:
   - A department is an intrinsic structural subunit of an organization. If the organization is liquidated or dissolved, its departments cease to exist. A department cannot exist without an owning organization. Therefore, **Composition** is the correct UML relationship.
3. **Department and Employee -> Aggregation**:
   - Employees are independent entities whose lifecycles are not tied to any single department. If a department is disbanded, the employees remain with the company and can be transferred to other departments. An employee can transition between departments or remain unassigned. Therefore, **Aggregation** is the correct UML relationship.

---

#### Concept Question 5: Multiplicity vs. Business Logic Constraints in Course Enrollment
**Prompt**: An educational platform has Courses and Students. A Course can be taken by many Students, and a Student can enroll in multiple Courses. A specific business rule states that a student cannot be enrolled in the same course more than once, meaning there should be no duplicate enrollment records for a student-course pair. Explain the UML multiplicity that correctly describes the relationship between Student and Course in this scenario. Furthermore, clarify why the business rule preventing duplicate enrollment is distinct from, and not directly expressed by, the multiplicity itself, but rather enforced by other mechanisms in the system's logic.

**Answer**:
1. **UML Multiplicity Between Student and Course**:
   - A `Course` can have 0 or more enrolled students (`0..*`).
   - A `Student` can enroll in 0 or more courses (`0..*`).
   - Hence, the relationship is a Many-to-Many (`0..*` to `0..*`) association, typically modeled using an association class or junction entity called `Enrollment`.
2. **Why the Business Rule is Distinct from Multiplicity**:
   - **Role of Multiplicity**: Multiplicity defines cardinality bounds (the minimum and maximum number of target instances that can be linked to a source instance). A multiplicity of `*` on both sides simply indicates that a Student can be linked to multiple Course instances and vice-versa.
   - **Why Multiplicity Cannot Express Uniqueness**: Standard UML multiplicity alone does not specify whether the set of links between two specific object instances is a set (unique pairs) or a multiset/bag (allowing duplicate links).
   - **Enforcement Mechanisms**:
     - In UML modeling, this constraint is expressed as a formal OCL (Object Constraint Language) invariant or annotation such as `{unique}` or `{no duplicate (student, course) pairs}` on the `Enrollment` association.
     - In database design, this is enforced by a composite primary key or unique constraint `UNIQUE(student_id, course_id)`.
     - In application business logic, this is validated prior to enrollment creation by querying existing active enrollments for the student and rejecting attempts with duplicate keys.
