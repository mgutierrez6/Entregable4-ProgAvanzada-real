pipeline {
    agent any

    stages {
        stage('Clonar repositorio') {
            steps {
                git branch: 'real', url: 'https://github.com/VickyLena/Entregable4-ProgAvanzada.git'
            }
        }

        stage('Compilar') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Desplegar en Tomcat') {
            steps {
                bat 'copy target\\Entregable4GutierrezLena.war "C:\\Tomcat-10.1.48\\webapps\\"'
            }
        }
    }

    post {
        success {
            echo 'Exito!'
        }
        failure {
            echo 'Error'
        }
    }
}
