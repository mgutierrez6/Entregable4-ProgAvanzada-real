pipeline {
    agent any

    stages {

        stage('Clonar repositorio') {
            steps {
                git branch: 'main', url: 'https://github.com/mgutierrez6/Entregable4-ProgAvanzada-real.git'
            }
        }

        stage('Compilar') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deploy en Windows') {
            steps {
                bat 'deploy-windows.bat'
            }
        }
    }

    post {
        success {
            echo 'Pipeline ejecutado correctamente.'
        }
        failure {
            echo 'Falló la pipeline.'
        }
    }
}
