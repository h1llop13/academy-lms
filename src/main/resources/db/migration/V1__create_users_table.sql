-- V1__create_users_table.sql

-- Создаем тип ENUM для ролей
CREATE TYPE user_role AS ENUM ('STUDENT', 'TEACHER', 'ADMIN');

-- Создаем таблицу пользователей
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role user_role NOT NULL DEFAULT 'STUDENT',
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Уникальный индекс с приведением к нижнему регистру
-- Это гарантирует, что Test@example.com и test@example.com считаются одним email
-- И обеспечивает ограничение уникальности на уровне БД
CREATE UNIQUE INDEX idx_users_email_unique ON users (LOWER(email));

-- Обычный индекс для ускорения поиска по email (опционально, но полезно)
CREATE INDEX idx_users_email_search ON users (email);