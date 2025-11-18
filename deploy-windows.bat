@echo off
echo ===============================
echo   DEPLOY - WINDOWS
echo ===============================

REM Usar variable de entorno o ruta por defecto
IF "%TOMCAT_HOME%"=="" (
    SET TOMCAT_HOME=C:\Tomcat-10.1.48
)

REM 1. Compilar con Maven
echo Compilando proyecto...
mvn clean package

REM 2. Parar Tomcat  
echo Deteniendo Tomcat...
"%TOMCAT_HOME%\bin\shutdown.bat"

REM 3. Copiar el .war
echo Copiando WAR a webapps...
copy target\Entregable4GutierrezLena.war "%TOMCAT_HOME%\webapps\" /Y

REM 4. Iniciar Tomcat
echo Iniciando Tomcat...
"%TOMCAT_HOME%\bin\startup.bat"

echo ===============================
echo   DEPLOY COMPLETADO
echo ===============================
echo Tomcat usado: %TOMCAT_HOME%
pause