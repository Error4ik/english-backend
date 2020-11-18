ALTER TABLE cards DROP COLUMN transcription;

CREATE OR REPLACE FUNCTION changes_card() RETURNS TRIGGER AS
$$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        UPDATE categories SET number_of_cards = number_of_cards - 1 where old.category_id = id;
        RETURN OLD;
    ELSIF (TG_OP = 'INSERT') THEN
        UPDATE categories SET number_of_cards = number_of_cards + 1 where new.category_id = id;
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER changes
    AFTER INSERT OR DELETE
    ON cards
    FOR EACH ROW
EXECUTE PROCEDURE changes_card();