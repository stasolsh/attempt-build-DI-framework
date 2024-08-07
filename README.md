# Attempt to build own DI implementation

## 🎯 Objective

Using the reflections library performs its realization of the Dependency Injection mechanism.

## 🏃 Example of usage
 - Bean definition by **Singleton** annotation: 
```java
@Singleton(qualifier = "dateFormatter")
public class DateFormatter
```
 - Bean injection by **InjectByType** annotation:
```java
@InjectByType(qualifier = "phoneNumberValidator")
private Validator<String> phoneNumberValidator;
```