-- V2__change_role_column_to_varchar.sql
-- Изменяем тип колонки role с ENUM на VARCHAR для совместимости с Hibernate

-- 1. Удаляем значение по умолчанию (оно ссылается на ENUM тип)
ALTER TABLE users ALTER COLUMN role DROP DEFAULT;

-- 2. Изменяем тип колонки на VARCHAR(20)
ALTER TABLE users ALTER COLUMN role TYPE VARCHAR(20);

-- 3. Устанавливаем новое значение по умолчанию (теперь это строка)
ALTER TABLE users ALTER COLUMN role SET DEFAULT 'STUDENT';

-- 4. Удаляем кастомный ENUM тип (он больше не нужен)
DROP TYPE user_role;