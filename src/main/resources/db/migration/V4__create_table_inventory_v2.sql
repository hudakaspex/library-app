CREATE TABLE inventory (
    id BIGINT NOT NULL AUTO_INCREMENT,
    copies INT,
    location VARCHAR(225),
    PRIMARY KEY(id),
    FOREIGN KEY(book_id)
)

