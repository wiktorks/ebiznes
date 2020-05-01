# --- !Ups

CREATE TABLE "user" (
    "id" INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
    "name" VARCHAR NOT NULL
);

CREATE TABLE "userData" (
    "id" INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
    "email" VARCHAR,
    "surname" VARCHAR,
    "phone" INT,
    "country" VARCHAR,
    "city" VARCHAR,
    "street" VARCHAR,
    "user" INT NOT NULL,
    FOREIGN KEY(user) REFERENCES user(id)
);

CREATE TABLE "product"(
    "id" INTEGER NOT NULL  PRIMARY KEY AUTOINCREMENT,
    "name" VARCHAR NOT NULL,
    "description" TEXT NOT NULL,
    "price" INT NOT NULL,
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
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "mark" SMALLINT NOT NULL,
    "comment" TEXT,
    "user" INT NOT NULL,
    "product" INT NOT NULL,
    FOREIGN KEY(user) references user(id),
    FOREIGN KEY(product) references product(id)
);

CREATE TABLE "basket" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user" INT NOT NULL,
    "product" INT NOT NULL,
    "quantity" INT NOT NULL,
    FOREIGN KEY(user) references user(id),
    FOREIGN KEY(product) references product(id)
);

CREATE TABLE "payments" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "user" INT NOT NULL,
    "basket" INT NOT NULL,
    "amount" FLOAT NOT NULL,
    FOREIGN KEY(user) references user(id),
    FOREIGN KEY(basket) references basket(id)
)

CREATE TABLE "shipment" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "address" VARCHAR NOT NULL,
    "user" INT NOT NULL,
    FOREIGN KEY(user) references user(id)
)

CREATE TABLE "shipmentState" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "status" VARCHAR,
    "shipment" INT NOT NULL,
    FOREIGN KEY(shipment) references shipment(id)
)

INSERT INTO "products"("name") VALUES("sample1")
INSERT INTO "products"("name") VALUES("sample2")

# --- !Downs

DROP TABLE "user";
DROP TABLE "userData";
DROP TABLE "product";
DROP TABLE "category";
DROP TABLE "rating";
DROP TABLE "basket";