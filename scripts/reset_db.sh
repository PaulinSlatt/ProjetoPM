#!/usr/bin/env zsh
# Script para backup, dropar e reaplicar migrations (Flyway)
# Uso: ./scripts/reset_db.sh
# Ele vai pedir confirmacao antes de dropar.

set -euo pipefail

# Default DB config (coincide com application.properties)
DB_HOST=${DB_HOST:-localhost}
DB_PORT=${DB_PORT:-3306}
DB_NAME=${DB_NAME:-policia}
DB_USER=${DB_USER:-root}
# DB_PASSWORD pode ser passado via env; se não, será solicitado no prompt
DB_PASSWORD=${DB_PASSWORD:-}

# Maven flyway properties (vai usar os mesmos credentials)
MVN_OPTS="-Dflyway.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME} -Dflyway.user=${DB_USER}"
if [[ -n "$DB_PASSWORD" ]]; then
  MVN_OPTS="$MVN_OPTS -Dflyway.password=${DB_PASSWORD}"
fi

echo "DB host: $DB_HOST, port: $DB_PORT, db: $DB_NAME, user: $DB_USER"

if [[ -z "$DB_PASSWORD" ]]; then
  echo -n "Senha do DB para $DB_USER (enter se vazio): "
  stty -echo
  read -r DB_PASSWORD
  stty echo
  echo
fi

# backup filename with timestamp
TS=$(date +%Y%m%d_%H%M%S)
BACKUP_FILE="./backup_${DB_NAME}_${TS}.sql"

echo "=> Fazendo dump do banco para $BACKUP_FILE"
if ! command -v mysqldump >/dev/null 2>&1; then
  echo "mysqldump não encontrado; instale mysql-client ou ajuste o script." >&2
  exit 1
fi

mysqldump -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" -p"$DB_PASSWORD" "$DB_NAME" > "$BACKUP_FILE"

echo "Backup criado: $BACKUP_FILE"

# Confirm destructive action
read -q "REPLY?Tem certeza que deseja DROPAR as tabelas gerenciadas pela migration (isso apaga dados)? (y/N): "
echo
if [[ "$REPLY" != [yY] && "$REPLY" != [sS] ]]; then
  echo "Abortando. Nenhuma alteração foi feita."
  exit 0
fi

# Drop tables in safe order (dependents first)
SQL_DROP="
DROP TABLE IF EXISTS atualizacao_ocorrencia;
DROP TABLE IF EXISTS ocorrencia_envolvido;
DROP TABLE IF EXISTS envolvido;
DROP TABLE IF EXISTS ocorrencia;
DROP TABLE IF EXISTS policial;
"

echo "=> Executando DROP das tabelas (se existirem)"
mysql -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" -p"$DB_PASSWORD" "$DB_NAME" -e "$SQL_DROP"

echo "Tabelas removidas."

# Run flyway repair then migrate using Maven plugin (fallback to flyway CLI if present)
if command -v ./mvnw >/dev/null 2>&1; then
  echo "=> Rodando 'flyway:repair' via Maven"
  ./mvnw $MVN_OPTS flyway:repair
  echo "=> Rodando 'flyway:migrate' via Maven"
  ./mvnw $MVN_OPTS flyway:migrate
else
  if command -v flyway >/dev/null 2>&1; then
    echo "=> Rodando flyway repair/migrate via flyway CLI"
    if [[ -n "$DB_PASSWORD" ]]; then
      flyway -url=jdbc:mysql://$DB_HOST:$DB_PORT/$DB_NAME -user=$DB_USER -password=$DB_PASSWORD repair
      flyway -url=jdbc:mysql://$DB_HOST:$DB_PORT/$DB_NAME -user=$DB_USER -password=$DB_PASSWORD migrate
    else
      flyway -url=jdbc:mysql://$DB_HOST:$DB_PORT/$DB_NAME -user=$DB_USER repair
      flyway -url=jdbc:mysql://$DB_HOST:$DB_PORT/$DB_NAME -user=$DB_USER migrate
    fi
  else
    echo "Nenhum mvnw ou flyway CLI encontrado. As migrations não foram aplicadas automaticamente." >&2
    echo "Você pode rodar: ./mvnw -Dflyway.url=jdbc:mysql://$DB_HOST:$DB_PORT/$DB_NAME -Dflyway.user=$DB_USER -Dflyway.password=<senha> flyway:repair" >&2
    echo "e então: ./mvnw -Dflyway.url=jdbc:mysql://$DB_HOST:$DB_PORT/$DB_NAME -Dflyway.user=$DB_USER -Dflyway.password=<senha> flyway:migrate" >&2
  fi
fi

echo "Concluído. Se ocorreram erros, restaure do backup: $BACKUP_FILE"

