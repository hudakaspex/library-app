ALTER TABLE book add COLUMN placement_id INT;

ALTER TABLE book
ADD CONSTRAINT fk_book_placement
FOREIGN KEY (placement_id)
REFERENCES placement(id)