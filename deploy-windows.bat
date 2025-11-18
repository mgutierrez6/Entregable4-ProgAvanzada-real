@echo off
echo ===============================
echo   DEPLOY - WINDOWS
echo ===============================

REM 1. Compilar con Maven
echo Compilando proyecto...
mvn clean package

REM 2. Parar Tomcat
echo Deteniendo Tomcat...
"C:\Tomcat-10.1.48\bin\shutdown.bat"

REM 3. Copiar el .war
echo Copiando WAR a webapps...
copy target\Entregable4GutierrezLena.war "C:\Tomcat-10.1.48\webapps\" /Y

REM 4. Iniciar Tomcat
echo Iniciando Tomcat...
"C:\Tomcat-10.1.48\bin\startup.bat"

echo ===============================
echo   DEPLOY COMPLETADO
echo ===============================
pause
