DROP TABLE IF EXISTS "book";
DROP TABLE IF EXISTS "author";

CREATE TABLE "author"(
    "id"   bigint DEFAULT nextval('author_id_seq') NOT NULL,
    "name"  text,
    "age" integer,
    CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "book"(
    "isbn" text NOT NULL,
    "title" text,
    "author_id" bigint,
    CONSTRAINT "books_pkey" PRIMARY KEY ("isbn"),
    CONSTRAINT "fk_author" FOREIGN KEY ("author_id") REFERENCES "author"
);