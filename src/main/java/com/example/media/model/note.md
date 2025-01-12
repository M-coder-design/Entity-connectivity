When we use Set or HashSet in entities, Spring Boot (with Hibernate) may throw unexpected errors if hashCode() and equals() aren’t overridden. This often happens in @OneToMany or @ManyToMany relationships. Why? Because HashSet relies on hashCode() and equals() for determining uniqueness.
Without properly defining these methods, Hibernate struggles to distinguish between entities, leading to potential issues like:
1) Duplicate entries
2) LazyInitializationException
3) Data inconsistency

 What’s Happening Behind the Scenes?
In Java, Set and HashSet depend on hashCode() and equals() to determine if two objects are the same. Without overriding these methods:

Uniqueness Checks Fail: Hibernate may not recognize identical entries, leading to duplicates or skipped entries.

Performance Issues: Hibernate often calls hashCode() to quickly locate entities in memory. A missing override impacts performance.

Data Integrity: Incorrect data may be retrieved or saved if the uniqueness constraints aren’t respected.

 Solution: Override hashCode() and equals()
To prevent these issues, explicitly override hashCode() and equals() in entities. Follow these best practices:

Base it on primary keys: Use unique identifiers like id for consistent hashing and equality.

Avoid business fields: Fields subject to change, like name or age, can lead to unpredictable behavior.

Example Code : 
@Entity
public class ExampleEntity {
 @Id
 private Long id;
 
 private String name;

 @Override
 public int hashCode() {
 return Objects.hash(id);
 }

 @Override
 public boolean equals(Object obj) {
 if (this == obj) return true;
 if (obj == null || getClass() != obj.getClass()) return false;
 ExampleEntity that = (ExampleEntity) obj;
 return Objects.equals(id, that.id);
 }
}

