#!/bin/bash

# SCRIPT PARA COPIAR LOS .WAR DESDE LA CARPETA MOCKS/ HACIA LA CARPETA /TARGET

# Configuración de rutas
DIRECTORIO_MOCKS="mocks"
DIRECTORIO_DESTINO="target/server/standalone/deployments"

# Colores en consola
VERDE='\033[0;32m'
ROJO='\033[0;31m'
AMARILLO='\033[1;33m'
SIN_COLOR='\033[0m'

echo -e "${AMARILLO}=== Copiando Mocks al Servidor WildFly ===${SIN_COLOR}"

# Verificar existencia del directorio mocks
if [ ! -d "$DIRECTORIO_MOCKS" ]; then
    echo -e "${ROJO} Error: No se encontró el directorio '$DIRECTORIO_MOCKS'${SIN_COLOR}"
    exit 1
fi

# Verificar que el servidor WildFly está corriendo
if [ ! -d "$DIRECTORIO_DESTINO" ]; then
    echo -e "${ROJO} Error: No se encontró '$DIRECTORIO_DESTINO'${SIN_COLOR}"
    echo -e "${AMARILLO}   ¿Ya ejecutaste 'mvn wildfly:dev'? El servidor debe estar corriendo.${SIN_COLOR}"
    exit 1
fi

# Contar archivos WAR en el directorio mocks
CONTEO=$(ls "$DIRECTORIO_MOCKS"/*.war 2>/dev/null | wc -l)

if [ "$CONTEO" -eq 0 ]; then
    echo -e "${AMARILLO}⚠️  No se encontraron archivos .war en '$DIRECTORIO_MOCKS'${SIN_COLOR}"
    exit 0
fi

# Copiar cada WAR encontrado
echo -e "${VERDE}📦 Copiando $CONTEO archivo(s) WAR...${SIN_COLOR}"
for war in "$DIRECTORIO_MOCKS"/*.war; do
    if [ -f "$war" ]; then
        nombre=$(basename "$war")
        echo -n "   → $nombre ... "
        if cp "$war" "$DIRECTORIO_DESTINO/"; then
            echo -e "${VERDE} Copiado${SIN_COLOR}"
        else
            echo -e "${ROJO} Error al copiar${SIN_COLOR}"
        fi
    fi
done

echo ""
echo -e "${VERDE}=== ¡Listo! ${CONTEO} mock(s) copiado(s) a deployments ===${SIN_COLOR}"