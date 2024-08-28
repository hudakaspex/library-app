CREATE TABLE inventory (
    id BIGINT NOT NULL AUTO_INCREMENT,
    copies INT,
    book_id BIGINT,
    location VARCHAR(225),
    PRIMARY KEY(id),
    FOREIGN KEY(book_id) REFERENCES Book(book_id)
)

