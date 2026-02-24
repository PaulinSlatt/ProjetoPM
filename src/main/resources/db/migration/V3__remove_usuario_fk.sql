-- V3: remove foreign key to `usuario` (se existir) e torna `policial.id` AUTO_INCREMENT
-- Idempotent: não lança erro se FK não existe e não tenta adicionar PRIMARY KEY se já houver uma

-- localizar FK que referencia usuario
SET @fk := (
  SELECT CONSTRAINT_NAME
  FROM information_schema.KEY_COLUMN_USAGE
  WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'policial'
    AND REFERENCED_TABLE_NAME = 'usuario'
  LIMIT 1
);

-- preparar instrução para dropar FK (ou selecionar mensagem se não existir)
SET @sql := IF(@fk IS NULL,
               'SELECT "no_fk_to_drop"',
               CONCAT('ALTER TABLE policial DROP FOREIGN KEY ', @fk)
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- verificar se a coluna id já é AUTO_INCREMENT
SET @is_ai := (
  SELECT IF(COUNT(*)>0,1,0)
  FROM information_schema.COLUMNS
  WHERE TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'policial'
    AND COLUMN_NAME = 'id'
    AND EXTRA LIKE '%auto_increment%'
);

-- se não for AUTO_INCREMENT, altera a coluna (sem declarar PRIMARY KEY)
SET @sql2 := IF(@is_ai = 1,
                'SELECT "already_auto_increment"',
                'ALTER TABLE policial MODIFY id BIGINT NOT NULL AUTO_INCREMENT'
);
PREPARE stmt2 FROM @sql2;
EXECUTE stmt2;
DEALLOCATE PREPARE stmt2;
