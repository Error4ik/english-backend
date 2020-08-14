package backend.english.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "categories")
public class Category {

    /**
     * Id.
     */
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /**
     * Category name.
     */
    private String name;

    /**
     * Number of nouns in the category.
     */
    @JoinColumn(name = "number_of_cards")
    private int numberOfCards;

    public Category() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return numberOfCards == category.numberOfCards &&
                id.equals(category.id) &&
                name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfCards);
    }

    @Override
    public String toString() {
        return String.format(
                "Category {id = %s, name = %s, numberOfCards = %s}",
                this.getId(), this.getName(), this.getNumberOfCards()
        );
    }
}
