# Definiciones de etiquetas

## @Getter y @Setter (LOMBOK)
Estos dos en conjunto  realizara la creación de los getters 
con la etiqueta `@Getter` y los setters con la etiqueta `@Setter`.

```java
@Getter
@Setter
public class Person {
    private String name;
}
```

```java
public class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

## @AllArgsConstructor (LOMBOK)

## @NoArgsConstructor (LOMBOK)

