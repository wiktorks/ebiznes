# --- !Ups

CREATE TABLE "user" (
    "id" INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
    "name" VARCHAR NOT NULL
);

CREATE TABLE "product"(
    "id" INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
    "name" VARCHAR NOT NULL,
    "description" TEXT NOT NULL,
    "category" INT NOT NULL,
    "user" INT NOT NULL,
    FOREIGN KEY(category) references category(id),
    FOREIGN KEY(user) references user(id)
);

CREATE TABLE "category" (
    "id" INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
    "name" VARCHAR NOT NULL
);

CREATE TABLE "rating" (
    "mark" SMALLINT NOT NULL,
    "comment" TEXT,
    "user" INT NOT NULL,
    "product" INT NOT NULL,
    FOREIGN KEY(user) references user(id),
    FOREIGN KEY(product) references product(id)
);

CREATE TABLE "basket" (
    "user" INT NOT NULL,
    "product" INT NOT NULL,
    "quantity" INT NOT NULL,
    FOREIGN KEY(user) references user(id),
    FOREIGN KEY(product) references product(id)
);

# --- !Downs

DROP TABLE "user";
DROP TABLE "product";
DROP TABLE "category";
DROP TABLE "rating";
DROP TABLE "basket";