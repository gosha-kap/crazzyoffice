
Drop Table if exists telegramuser;

Create TABLE telegramuser(
    id serial PRIMARY KEY ,
    userId bigint UNIQUE NOT NULL,
    chatId bigint DEFAULT NULL,
    autorised boolean DEFAULT false,
    first varchar DEFAULT 'unknown',
    last varchar DEFAULT 'unknown'
);






