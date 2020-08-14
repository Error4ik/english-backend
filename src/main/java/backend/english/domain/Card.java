package backend.english.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "cards")
public class Card {

    /**
     * Id.
     */
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String word;

    /**
     * Category.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    private String transcription;

    private String translation;

    private String phrase;

    @Column(name = "translate_phrase")
    private String translatePhrase;

    public Card() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getTranslatePhrase() {
        return translatePhrase;
    }

    public void setTranslatePhrase(String translatePhrase) {
        this.translatePhrase = translatePhrase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id.equals(card.id) &&
                word.equals(card.word) &&
                category.equals(card.category) &&
                transcription.equals(card.transcription) &&
                translation.equals(card.translation) &&
                phrase.equals(card.phrase) &&
                translatePhrase.equals(card.translatePhrase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, category, transcription, translation, phrase, translatePhrase);
    }

    @Override
    public String toString() {
        return String.format(
                "Card {id = %s, word = %s, category = %s, transcription = %s, translation = %s, phrase = %s, translatePhrase = %s}",
                this.getId(),
                this.getWord(),
                this.getCategory().toString(),
                this.getTranscription(),
                this.getTranslation(),
                this.getPhrase(),
                this.getTranslatePhrase());
    }
}