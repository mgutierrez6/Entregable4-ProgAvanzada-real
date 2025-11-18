#!/bin/bash
echo "==============================="
echo "   DEPLOY - MAC/LINUX"
echo "==============================="

# 1. Compilar proyecto
echo "Compilando..."
mvn clean package

# 2. Parar Tomcat
echo "Deteniendo Tomcat..."
/usr/local/tomcat/bin/shutdown.sh

# 3. Copiar WAR
echo "Copiando WAR a webapps..."
cp target/Entregable4GutierrezLena.war /usr/local/tomcat/webapps/

# 4. Iniciar Tomcat
echo "Iniciando Tomcat..."
/usr/local/tomcat/bin/startup.sh

echo "==============================="
echo "   DEPLOY COMPLETADO"
echo "==============================="
